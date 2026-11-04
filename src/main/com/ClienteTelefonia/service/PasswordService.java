
package main.com.ClienteTelefonia.service;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public final class PasswordService {
     private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 256;
    private static final int ITERACIONES = 600_000;

    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordService() {
    }

    public static byte[] generarSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public static byte[] generarHash(
            char[] password,
            byte[] salt,
            int iteraciones) throws GeneralSecurityException {

        if (password == null || password.length == 0) {
            throw new IllegalArgumentException(
                "La contraseña es obligatoria."
            );
        }

        if (salt == null || salt.length != SALT_LENGTH) {
            throw new IllegalArgumentException(
                "El salt no tiene una longitud válida."
            );
        }

        if (iteraciones <= 0) {
            throw new IllegalArgumentException(
                "El número de iteraciones no es válido."
            );
        }

        PBEKeySpec especificacion = new PBEKeySpec(
            password,
            salt,
            iteraciones,
            HASH_LENGTH
        );

        try {
            SecretKeyFactory fabrica =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            return fabrica.generateSecret(especificacion).getEncoded();

        } finally {
            especificacion.clearPassword();
        }
    }

    public static boolean verificarPassword(
            char[] password,
            byte[] salt,
            byte[] hashAlmacenado,
            int iteraciones) throws GeneralSecurityException {

        if (hashAlmacenado == null || hashAlmacenado.length != 32) {
            return false;
        }

        byte[] hashCalculado = generarHash(
            password,
            salt,
            iteraciones
        );

        try {
            return MessageDigest.isEqual(
                hashCalculado,
                hashAlmacenado
            );
        } finally {
            Arrays.fill(hashCalculado, (byte) 0);
        }
    }

    public static int obtenerIteraciones() {
        return ITERACIONES;
    }
}
