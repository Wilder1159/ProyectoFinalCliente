
package main.com.ClienteTelefonia.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import main.com.ClienteTelefonia.config.EnvConfig;

public class TelnyxLlamadaService {

    // Endpoint oficial de Telnyx
    private static final String API_URL = "https://api.telnyx.com/v2/calls";

    private final HttpClient httpClient;

    private final String apiKey;
    private final String connectionId;
    private final String numeroOrigen;

    // Constructor
    public TelnyxLlamadaService() {

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        // Cargar configuración desde .env
        this.apiKey = EnvConfig.obtener("TELNYX_API_KEY");

        this.connectionId = EnvConfig.obtener("TELNYX_CONNECTION_ID");

        this.numeroOrigen = EnvConfig.obtener("TELNYX_FROM_NUMBER");

        System.out.println(
                "Configuración de Telnyx cargada correctamente."
        );
    }

    public String realizarLlamada(String numeroDestino)
            throws Exception {

        //Validar número de destino
        if (numeroDestino == null || !numeroDestino.matches("^\\+[1-9]\\d{7,14}$")) {

            throw new IllegalArgumentException(
                    "El número debe estar en formato internacional E.164. "
                    + "Ejemplo: +519XXXXXXXX"
            );
        }

        //Construir JSON
        String json = """
                {
                    "connection_id": "%s",
                    "to": "%s",
                    "from": "%s"
                }
                """.formatted(
                        connectionId,
                        numeroDestino,
                        numeroOrigen
                );

        //Construir solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .timeout(Duration.ofSeconds(30))
                .header(
                        "Authorization",
                        "Bearer " + apiKey
                )
                .header(
                        "Content-Type",
                        "application/json"
                )
                .header(
                        "Accept",
                        "application/json"
                )
                .POST(
                        HttpRequest.BodyPublishers.ofString(
                                json,
                                StandardCharsets.UTF_8
                        )
                )
                .build();

        //Enviar solicitud a Telnyx
        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        System.out.println(
                "Código HTTP Telnyx: "
                + response.statusCode()
        );

        System.out.println(
                "Respuesta Telnyx: "
                + response.body()
        );

        //Validar respuesta
        if (response.statusCode() < 200
                || response.statusCode() >= 300) {

            throw new IllegalStateException(
                    "Telnyx rechazó la llamada. HTTP "
                    + response.statusCode()
                    + ": " + response.body()
            );
        }

        return response.body();
    }
}
