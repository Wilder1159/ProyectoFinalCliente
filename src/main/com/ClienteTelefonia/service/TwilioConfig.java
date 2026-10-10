
package main.com.ClienteTelefonia.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class TwilioConfig {
    
    private static final Map<String, String> variables =
            cargarVariables();

    private static Map<String, String> cargarVariables() {

        Map<String, String> datos = new HashMap<>();

        Path ruta = Path.of(".env");

        if (!Files.exists(ruta)) {
            return datos;
        }

        try {
            for (String linea : Files.readAllLines(ruta)) {

                linea = linea.trim();

                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                String[] partes = linea.split("=", 2);

                if (partes.length == 2) {
                    datos.put(
                        partes[0].trim(),
                        partes[1].trim()
                    );
                }
            }

        } catch (IOException ex) {
            throw new IllegalStateException(
                "Error al leer la configuración de Twilio.",
                ex
            );
        }

        return datos;
    }

    public static String obtener(String clave) {

        String valor = System.getenv(clave);

        if (valor == null || valor.isBlank()) {
            valor = variables.get(clave);
        }

        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException(
                "Falta configurar: " + clave
            );
        }

        return valor;
    }
    
    //test para poder probar que si lee las credenciales de llamada
    public static void main(String[] args) {
        String sid = TwilioConfig.obtener("TWILIO_ACCOUNT_SID");

        System.out.println(
            "Configuración de Twilio cargada: "
            + (sid != null && !sid.isBlank())
        );
    }
    
}
