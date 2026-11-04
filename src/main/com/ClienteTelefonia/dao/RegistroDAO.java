
package main.com.ClienteTelefonia.dao;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.BigDecimal;
import java.util.Arrays;

import main.com.ClienteTelefonia.config.DatabaseConfig;
import main.com.ClienteTelefonia.model.ClienteTelefonia;
import main.com.ClienteTelefonia.service.PasswordService;


public class RegistroDAO {
    /**
     * Registra un cliente, su cuenta telefónica y su usuario.
     * Las tres operaciones se ejecutan dentro de una sola transacción.
     *
     * El registro público siempre crea usuarios con rol CLIENTE.
     */
    public long registrarClienteConUsuario(
            ClienteTelefonia cliente,
            String username,
            char[] password)
            throws SQLException, GeneralSecurityException {

        validarDatos(cliente, username, password);

        String usernameNormalizado = username.trim();

        // Generar las credenciales antes de iniciar la transacción.
        byte[] salt = PasswordService.generarSalt();

        int iteraciones = PasswordService.obtenerIteraciones();

        byte[] hash = PasswordService.generarHash(
                password,
                salt,
                iteraciones
        );

        String sqlCliente =
                "INSERT INTO dbo.Cliente "
                + "(nombre, apellido, telefono, correo) "
                + "VALUES (?, ?, ?, ?)";

        String sqlCuenta =
                "INSERT INTO dbo.Cuenta "
                + "(id_cliente, saldo, estado) "
                + "VALUES (?, ?, ?)";

        String sqlUsuario =
                "INSERT INTO dbo.Usuario "
                + "(id_cliente, username, password_hash, "
                + "password_salt, iteraciones, activo, "
                + "fecha_creacion, rol) "
                + "VALUES (?, ?, ?, ?, ?, ?, SYSDATETIME(), ?)";

        long idClienteGenerado = 0;

        try (Connection conexion = DatabaseConfig.getConnection()) {

            conexion.setAutoCommit(false);

            try {
                // 1. Insertar Cliente a la base
                try (PreparedStatement psCliente =
                        conexion.prepareStatement(
                                sqlCliente,
                                Statement.RETURN_GENERATED_KEYS)) {

                    psCliente.setString(1, cliente.getNombre());
                    psCliente.setString(2, cliente.getApellido());
                    psCliente.setString(3, cliente.getTelefono());
                    psCliente.setString(4, cliente.getCorreo());

                    int filasCliente = psCliente.executeUpdate();

                    if (filasCliente != 1) {
                        throw new SQLException(
                                "No se pudo registrar el cliente."
                        );
                    }

                    try (ResultSet rs = psCliente.getGeneratedKeys()) {

                        if (rs.next()) {
                            idClienteGenerado = rs.getLong(1);
                        } else {
                            throw new SQLException(
                                    "No se pudo obtener el ID del cliente."
                            );
                        }
                    }
                }

                // 2. Crear la cuenta telefónica
                try (PreparedStatement psCuenta =
                        conexion.prepareStatement(sqlCuenta)) {

                    psCuenta.setLong(1, idClienteGenerado);
                    psCuenta.setBigDecimal(2, BigDecimal.ZERO);
                    psCuenta.setString(3, "ACTIVA");

                    int filasCuenta = psCuenta.executeUpdate();

                    if (filasCuenta != 1) {
                        throw new SQLException(
                                "No se pudo crear la cuenta telefónica."
                        );
                    }
                }

                // 3. Crear las credenciales del usuario
                try (PreparedStatement psUsuario =
                        conexion.prepareStatement(sqlUsuario)) {

                    psUsuario.setLong(1, idClienteGenerado);
                    psUsuario.setString(2, usernameNormalizado);
                    psUsuario.setBytes(3, hash);
                    psUsuario.setBytes(4, salt);
                    psUsuario.setInt(5, iteraciones);
                    psUsuario.setBoolean(6, true);

                    // El registro público nunca se creara como admin
                    psUsuario.setString(7, "CLIENTE");

                    int filasUsuario = psUsuario.executeUpdate();

                    if (filasUsuario != 1) {
                        throw new SQLException(
                                "No se pudieron guardar las credenciales."
                        );
                    }
                }

                // 4. ACTUALIZAR EL MODELO SOLAMENTE DESPUES DE REALIZAR EL COMMIT
                conexion.commit();

                // Actualizar el modelo solamente después del commit, el commit es para escribir directamente en la consola de sql desde java
                cliente.setIdCliente(idClienteGenerado);

                return idClienteGenerado;

            } catch (SQLException | RuntimeException e) {

                try {
                    conexion.rollback();
                } catch (SQLException errorRollback) {
                    e.addSuppressed(errorRollback);
                }

                throw e;
            }

        } finally {
            // Limpiar el hash temporal de memoria.
            Arrays.fill(hash, (byte) 0);
        }
    }

    private void validarDatos(
            ClienteTelefonia cliente,
            String username,
            char[] password) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "Los datos del cliente son obligatorios."
            );
        }

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "El username es obligatorio."
            );
        }

        if (username.trim().length() > 50) {
            throw new IllegalArgumentException(
                    "El username no puede superar los 50 caracteres."
            );
        }

        if (password == null || password.length == 0) {
            throw new IllegalArgumentException(
                    "La contraseña es obligatoria."
            );
        }
    }
    
    //nuevo metodo para clientes que ya exiten
    public long registrarUsuarioExistente(
        long idCliente,
        String username,
        char[] password
            ) throws SQLException, GeneralSecurityException {

    if (idCliente <= 0) {
        throw new IllegalArgumentException("ID de cliente inválido.");
    }

    if (username == null || username.isBlank()) {
        throw new IllegalArgumentException("El username es obligatorio.");
    }

    if (password == null || password.length == 0) {
        throw new IllegalArgumentException("La contraseña es obligatoria.");
    }

    byte[] salt = PasswordService.generarSalt();
    byte[] hash = null;

    try {
        int iteraciones = PasswordService.obtenerIteraciones();

        hash = PasswordService.generarHash(
                password,
                salt,
                iteraciones
        );

        String sql = """
            INSERT INTO dbo.Usuario (
                id_cliente,
                username,
                password_hash,
                password_salt,
                iteraciones,
                activo,
                fecha_creacion,
                rol
            )
            VALUES (?, ?, ?, ?, ?, 1, SYSDATETIME(), 'CLIENTE')
            """;

        try (Connection cn = DatabaseConfig.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS
             )) {

            ps.setLong(1, idCliente);
            ps.setString(2, username.trim());
            ps.setBytes(3, hash);
            ps.setBytes(4, salt);
            ps.setInt(5, iteraciones);

            int filas = ps.executeUpdate();

            if (filas != 1) {
                throw new SQLException(
                        "No se pudo crear el usuario."
                );
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }

            throw new SQLException(
                    "No se pudo recuperar el ID del usuario."
            );
        }

        } finally {
            if (hash != null) {
                Arrays.fill(hash, (byte) 0);
            }

            Arrays.fill(salt, (byte) 0);
        }
    }
}        
