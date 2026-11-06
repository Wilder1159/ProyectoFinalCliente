
package main.com.ClienteTelefonia.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import main.com.ClienteTelefonia.controller.LoginController;
import main.com.ClienteTelefonia.model.Usuario;

public class TelnyxWebhookServer {

       private static final ObjectMapper JSON = new ObjectMapper();

    private static final LoginController LOGIN_CONTROLLER =
            new LoginController();

    private static final int MAX_BODY_BYTES = 16 * 1024;

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", 8081), 0
        );

        // WEBHOOK DE TELNYX
        server.createContext("/telnyx/webhook", exchange -> {
            try {
                if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                    byte[] body = exchange.getRequestBody().readAllBytes();

                    String payload = new String(
                            body, StandardCharsets.UTF_8
                    );

                    System.out.println("Evento recibido desde Telnyx:");
                    System.out.println(payload);

                    responderJson(
                            exchange,
                            200,
                            Map.of("received", true)
                    );

                } else {
                    exchange.getResponseHeaders().set("Allow", "POST");
                    responderJson(
                            exchange,
                            405,
                            Map.of("error", "Método no permitido")
                    );
                }

            } finally {
                exchange.close();
            }
        });

        //ESTADO DE LA API
        server.createContext("/api/health", exchange -> {
            try {
                if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {

                    System.out.println("Comprobación de estado de la API.");

                    responderJson(
                            exchange,
                            200,
                            Map.of(
                                    "status", "OK",
                                    "message", "Servidor disponible"
                            )
                    );

                } else {
                    exchange.getResponseHeaders().set("Allow", "GET");
                    responderJson(
                            exchange,
                            405,
                            Map.of("error", "Método no permitido")
                    );
                }

            } finally {
                exchange.close();
            }
        });

        //AUTENTICACIÓN DE USUARIOS
        server.createContext("/api/auth/login", exchange -> {
            manejarLogin(exchange);
        });

        // INICIAR SERVIDOR
        server.start();

        System.out.println("SERVIDOR INICIADO CORRECTAMENTE");
        System.out.println("Puerto: 8081");
        System.out.println("Webhook: /telnyx/webhook");
        System.out.println("API: /api/health");
        System.out.println("Login: /api/auth/login");
    }

    // PROCESAR AUTENTICACIÓN
    private static void manejarLogin(HttpExchange exchange)
            throws IOException {

        try {

            // A. VALIDAR MÉTODO HTTP
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                exchange.getResponseHeaders().set("Allow", "POST");

                responderJson(
                        exchange,
                        405,
                        Map.of("error", "Método no permitido")
                );

                return;
            }

            // B. VALIDAR el contenido sea de la solicitud
            String contentType = exchange.getRequestHeaders()
                    .getFirst("Content-Type");

            if (contentType == null
                    || !contentType.toLowerCase().startsWith("application/json")) {

                responderJson(
                        exchange,
                        415,
                        Map.of("error", "Se requiere application/json")
                );

                return;
            }

            // C. LEER JSON 
            byte[] body = exchange.getRequestBody()
                    .readNBytes(MAX_BODY_BYTES + 1);

            if (body.length > MAX_BODY_BYTES) {

                responderJson(
                        exchange,
                        413,
                        Map.of("error", "Solicitud demasiado grande")
                );

                return;
            }

            // D. CONVERTIR JSON
            JsonNode json;

            try {
                json = JSON.readTree(body);
            } catch (JsonProcessingException e) {

                responderJson(
                        exchange,
                        400,
                        Map.of("error", "JSON inválido")
                );

                return;
            }

            if (json == null || !json.isObject()) {

                responderJson(
                        exchange,
                        400,
                        Map.of("error", "Formato de solicitud inválido")
                );

                return;
            }

            JsonNode usernameNode = json.get("username");
            JsonNode passwordNode = json.get("password");

            //VALIDAR CAMPOS
            if (usernameNode == null || !usernameNode.isTextual()
                    || passwordNode == null || !passwordNode.isTextual()) {

                responderJson(
                        exchange,
                        400,
                        Map.of("error", "Usuario y contraseña son obligatorios")
                );

                return;
            }

            String username = usernameNode.asText().trim();
            String passwordTexto = passwordNode.asText();

            if (username.isBlank() || username.length() > 100
                    || passwordTexto.isEmpty()
                    || passwordTexto.length() > 256) {

                responderJson(
                        exchange,
                        400,
                        Map.of("error", "Datos de acceso inválidos")
                );

                return;
            }

            char[] password = passwordTexto.toCharArray();

            try {

                //  AUTENTICAR CONTRA SQL SERVER
                Usuario usuario = LOGIN_CONTROLLER.autenticar(
                        username,
                        password
                );

                // DEVOLVER SOLAMENTE DATOS SEGUROS
                Map<String, Object> respuesta = Map.of(
                        "autenticado", true,
                        "idUsuario", usuario.getIdUsuario(),
                        "idCliente", usuario.getIdCliente(),
                        "username", usuario.getUsername(),
                        "rol", usuario.getRol()
                );

                responderJson(exchange, 200, respuesta);

                System.out.println(
                        "Autenticación correcta para usuario: " + username
                );

            } catch (IllegalArgumentException e) {

                responderJson(
                        exchange,
                        401,
                        Map.of(
                                "autenticado", false,
                                "error", "Credenciales incorrectas o cuenta inactiva"
                        )
                );

            } catch (IllegalStateException e) {

                responderJson(
                        exchange,
                        403,
                        Map.of(
                                "autenticado", false,
                                "error", "No se puede autorizar esta cuenta"
                        )
                );

            } catch (SQLException | GeneralSecurityException e) {

                e.printStackTrace();

                responderJson(
                        exchange,
                        500,
                        Map.of(
                                "autenticado", false,
                                "error", "Error interno de autenticación"
                        )
                );

            } finally {

                // LIMPIAR CONTRASEÑA EN MEMORIA
                Arrays.fill(password, '\0');
            }

        } finally {
            exchange.close();
        }
    }

    // MÉTODO AUXILIAR PARA RESPONDER JSON
    private static void responderJson(
            HttpExchange exchange,
            int codigo,
            Object contenido
    ) throws IOException {

        byte[] response = JSON.writeValueAsBytes(contenido);

        exchange.getResponseHeaders().set(
                "Content-Type",
                "application/json; charset=UTF-8"
        );

        exchange.sendResponseHeaders(codigo, response.length);

        exchange.getResponseBody().write(response);
    }
}

