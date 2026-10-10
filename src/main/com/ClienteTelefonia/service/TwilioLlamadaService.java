
package main.com.ClienteTelefonia.service;

import java.util.Base64;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;



public class TwilioLlamadaService {
    //Utilizaremos HttpClient, disponible en Java 11 y posteriores. 
    //No necesitas instalar bibliotecas adicionales en Apache Ant.
    
     private final HttpClient clienteHTTP = HttpClient.newHttpClient();

    public String iniciarLlamada(String destino) throws Exception {

        String sid = TwilioConfig.obtener("TWILIO_ACCOUNT_SID");
        String token = TwilioConfig.obtener("TWILIO_AUTH_TOKEN");
        String origen = TwilioConfig.obtener("TWILIO_PHONE_NUMBER");

        String url = "https://api.twilio.com/2010-04-01/Accounts/"
                + sid + "/Calls.json";

        String twiml = "<Response>"
                + "<Say language=\"es-ES\">"
                + "Bienvenido al sistema de telefonia."
                + "</Say>"
                + "<Hangup/>"
                + "</Response>";

        String datos = "To=" + codificar(destino)
                + "&From=" + codificar(origen)
                + "&Twiml=" + codificar(twiml);

        String credenciales = sid + ":" + token;

        String autenticacion = Base64.getEncoder()
                .encodeToString(
                    credenciales.getBytes(StandardCharsets.UTF_8)
                );

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Basic " + autenticacion)
                .header("Content-Type",
                        "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(datos))
                .build();

        HttpResponse<String> respuesta = clienteHTTP.send(
                solicitud,
                HttpResponse.BodyHandlers.ofString()
        );

        if (respuesta.statusCode() < 200
                || respuesta.statusCode() >= 300) {

            throw new IllegalStateException(
                "Twilio rechazó la solicitud. HTTP "
                + respuesta.statusCode()
            );
        }

        return respuesta.body();
    }

    private String codificar(String valor) {
        return URLEncoder.encode(
                valor, StandardCharsets.UTF_8
        );
    }
    
    
    //metodo de consulta de estado de la llamada
    /*¿Qué hace?
    Consulta una llamada existente mediante su identificador Call SID, 
    que Twilio asigna cuando se crea la llamada.
    
    La API devuelve un JSON parecido a este ejemplo:
    {
      "sid": "CAxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
      "status": "completed",
      "duration": "180",
      "direction": "outbound-api"
    }
    */
    
    public String consultarLlamada(String callSid) throws Exception {

        // Validar identificador de llamada
        if (callSid == null ||
            !callSid.matches("CA[a-fA-F0-9]{32}")) {

            throw new IllegalArgumentException(
                "Call SID inválido."
            );
        }

        String sid = TwilioConfig.obtener("TWILIO_ACCOUNT_SID");
        String token = TwilioConfig.obtener("TWILIO_AUTH_TOKEN");

        String url = "https://api.twilio.com/2010-04-01/Accounts/"
                + sid + "/Calls/" + callSid + ".json";

        String credenciales = sid + ":" + token;

        String autenticacion = Base64.getEncoder()
                .encodeToString(
                    credenciales.getBytes(StandardCharsets.UTF_8)
                );

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Basic " + autenticacion)
                .GET()
                .build();

        HttpResponse<String> respuesta = clienteHTTP.send(
                solicitud,
                HttpResponse.BodyHandlers.ofString()
        );

        if (respuesta.statusCode() != 200) {
            throw new IllegalStateException(
                "Error al consultar Twilio. HTTP "
                + respuesta.statusCode()
            );
        }

        return respuesta.body();
    }
    
    //prueba sin realizar la llamada
    
    public boolean verificarConfiguracion() {

        String sid = TwilioConfig.obtener("TWILIO_ACCOUNT_SID");
        String token = TwilioConfig.obtener("TWILIO_AUTH_TOKEN");
        String numero = TwilioConfig.obtener("TWILIO_PHONE_NUMBER");

        return sid.matches("AC[a-fA-F0-9]{32}")
                && !token.isBlank()
                && numero.matches("\\+[1-9][0-9]{1,14}");
    }
    
    //metodo de prueba
    /*Antes de integrar el botón LLAMAR de JConsumo
    comprobaremos que Twilio acepte nuestras credenciales
    sin iniciar ninguna llamada ni generar consumo telefónico*/
    public boolean verificarConexion() throws Exception {

        String sid = TwilioConfig.obtener("TWILIO_ACCOUNT_SID");
        String token = TwilioConfig.obtener("TWILIO_AUTH_TOKEN");

        String credenciales = sid + ":" + token;

        String autenticacion = Base64.getEncoder()
                .encodeToString(
                    credenciales.getBytes(StandardCharsets.UTF_8)
                );

        String url = "https://api.twilio.com/2010-04-01/Accounts/"
                + sid + ".json";

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Basic " + autenticacion)
                .timeout(java.time.Duration.ofSeconds(15))
                .GET()
                .build();

        HttpResponse<String> respuesta = clienteHTTP.send(
                solicitud,
                HttpResponse.BodyHandlers.ofString()
        );

        if (respuesta.statusCode() == 200) {
            return true;
        }

        throw new IllegalStateException(
            "Error de conexión con Twilio. HTTP "
            + respuesta.statusCode()
        );
    }

    //metodo main para la test
    public static void main(String[] args) {
        TwilioLlamadaService servicio =
            new TwilioLlamadaService();

        try {
            System.out.println(
                "Configuración válida: "
                + servicio.verificarConfiguracion()
            );

            System.out.println(
                "Conexión con Twilio: "
                + servicio.verificarConexion()
            );

        } catch (Exception ex) {
            System.err.println(
                "Error: " + ex.getMessage()
            );
        }
    }

}
