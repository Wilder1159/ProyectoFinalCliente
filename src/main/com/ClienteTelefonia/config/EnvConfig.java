package main.com.ClienteTelefonia.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class EnvConfig {
    private static final Properties propiedades = new Properties();

    static {
        Path ruta = Path.of(".env");

        if (!Files.exists(ruta)) {
            throw new IllegalStateException(
                "No se encontró el archivo .env en: "
                + ruta.toAbsolutePath()
            );
        }

        try (InputStream entrada = Files.newInputStream(ruta)) {
            propiedades.load(entrada);
        } catch (IOException e) {
            throw new IllegalStateException(
                "Error al leer el archivo .env", e
            );
        }
    }

    public static String obtener(String clave) {

        String valor = propiedades.getProperty(clave);

        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException(
                "No se configuró la propiedad: " + clave
            );
        }

        return valor.trim();
    }
}
