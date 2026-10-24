
package main.com.ClienteTelefonia.service;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class TelnyxWebhookServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", 8081), 0
        );

        server.createContext("/telnyx/webhook", exchange -> {
            try {
                if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                    byte[] body = exchange.getRequestBody()
                            .readAllBytes();

                    String payload = new String(
                            body, StandardCharsets.UTF_8
                    );

                    System.out.println(
                            "Evento recibido desde Telnyx:"
                    );
                    System.out.println(payload);

                    byte[] response = "{\"received\":true}"
                            .getBytes(StandardCharsets.UTF_8);

                    exchange.getResponseHeaders().set(
                            "Content-Type", "application/json"
                    );

                    exchange.sendResponseHeaders(200, response.length);
                    exchange.getResponseBody().write(response);
                } else {
                    exchange.sendResponseHeaders(405, -1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                exchange.close();
            }
        });

        server.start();

        System.out.println("Servidor Telnyx iniciado.");
        System.out.println("Puerto: 8081");
        System.out.println("Ruta: /telnyx/webhook");
    }
}
