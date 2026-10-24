
package main.com.ClienteTelefonia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.com.ClienteTelefonia.config.DatabaseConfig;
import main.com.ClienteTelefonia.model.ClienteTelefonia;

public class ClienteDAO {

    public long registrar(ClienteTelefonia cliente)
            throws SQLException {
        // Implementación del método para registrar un cliente en la base de datos
        // Aquí iría la implementación para insertar el cliente en la base de datos
      String sqlCliente = """
        INSERT INTO dbo.Cliente (nombre, apellido, telefono, correo)
        VALUES (?, ?, ?, ?)
        """;

    String sqlCuenta = """
        INSERT INTO dbo.Cuenta (id_cliente, saldo, estado)
        VALUES (?, 0.00, 'ACTIVA')
        """;

    try (Connection conexion = DatabaseConfig.getConnection()) {

        conexion.setAutoCommit(false);

        try {
            long idCliente;

            // 1. Registrar el cliente.
            try (PreparedStatement sentencia = conexion.prepareStatement(
                    sqlCliente, Statement.RETURN_GENERATED_KEYS)) {

                sentencia.setString(1, cliente.getNombre());
                sentencia.setString(2, cliente.getApellido());
                sentencia.setString(3, cliente.getTelefono());

                if (cliente.getCorreo() == null
                        || cliente.getCorreo().isBlank()) {
                    sentencia.setNull(4, java.sql.Types.NVARCHAR);
                } else {
                    sentencia.setString(4, cliente.getCorreo().trim());
                }

                int filas = sentencia.executeUpdate();

                if (filas != 1) {
                    throw new SQLException(
                        "No se pudo insertar el cliente."
                    );
                }

                try (ResultSet claves = sentencia.getGeneratedKeys()) {
                    if (!claves.next()) {
                        throw new SQLException(
                            "No se pudo recuperar el ID del cliente."
                        );
                    }

                    idCliente = claves.getLong(1);
                }
            }

            // 2. Crear la cuenta asociada.
            try (PreparedStatement sentenciaCuenta =
                    conexion.prepareStatement(sqlCuenta)) {

                sentenciaCuenta.setLong(1, idCliente);

                int filasCuenta = sentenciaCuenta.executeUpdate();

                if (filasCuenta != 1) {
                    throw new SQLException(
                        "No se pudo crear la cuenta del cliente."
                    );
                }
            }

            // 3. Confirmar ambas operaciones.
            conexion.commit();

            cliente.setIdCliente(idCliente);

            return idCliente;

        } catch (SQLException | RuntimeException e) {

            try {
                conexion.rollback();
            } catch (SQLException errorRollback) {
                e.addSuppressed(errorRollback);
            }

            throw e;
        }
       }
    }

    public ClienteTelefonia buscarPorTelefono(String telefono)
            throws SQLException {

        String sql = """
            SELECT id_cliente, nombre, apellido,
                   telefono, correo, estado
            FROM dbo.Cliente
            WHERE telefono = ?
            """;

        try (Connection conexion = DatabaseConfig.getConnection();
             PreparedStatement sentencia =
                 conexion.prepareStatement(sql)) {

            sentencia.setString(1, telefono);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (!resultado.next()) {
                    return null;
                }

                ClienteTelefonia cliente = new ClienteTelefonia();

                cliente.setIdCliente(
                    resultado.getLong("id_cliente")
                );
                cliente.setNombre(
                    resultado.getString("nombre")
                );
                cliente.setApellido(
                    resultado.getString("apellido")
                );
                cliente.setTelefono(
                    resultado.getString("telefono")
                );
                cliente.setCorreo(
                    resultado.getString("correo")
                );
                cliente.setEstado(
                    resultado.getString("estado")
                );

                return cliente;
            }
        }
    }

    //metodo para eliminar un cliente de la base de datos
    public boolean eliminar(long idCliente) throws SQLException {

    String sqlVerificar = """
        SELECT COUNT(*)
        FROM dbo.Recarga r
        INNER JOIN dbo.Cuenta c ON c.id_cuenta = r.id_cuenta
        WHERE c.id_cliente = ?
        """;

    String sqlVerificarLlamadas = """
        SELECT COUNT(*)
        FROM dbo.Llamada l
        INNER JOIN dbo.Cuenta c ON c.id_cuenta = l.id_cuenta
        WHERE c.id_cliente = ?
        """;

    String sqlVerificarMovimientos = """
        SELECT COUNT(*)
        FROM dbo.MovimientoSaldo m
        INNER JOIN dbo.Cuenta c ON c.id_cuenta = m.id_cuenta
        WHERE c.id_cliente = ?
        """;

    try (Connection conexion = DatabaseConfig.getConnection()) {
        conexion.setAutoCommit(false);

        try {
            // No eliminar clientes con historial financiero.
            for (String sql : new String[]{
                    sqlVerificar,
                    sqlVerificarLlamadas,
                    sqlVerificarMovimientos
            }) {
                try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                    ps.setLong(1, idCliente);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next() && rs.getLong(1) > 0) {
                            throw new SQLException(
                                "No se puede eliminar el cliente porque "
                                + "tiene recargas, llamadas o movimientos."
                            );
                        }
                    }
                }
            }

            // Eliminar primero la cuenta.
            try (PreparedStatement ps = conexion.prepareStatement("""
                    DELETE FROM dbo.Cuenta
                    WHERE id_cliente = ?
                    """)) {
                ps.setLong(1, idCliente);
                ps.executeUpdate();
            }

            // Eliminar después el cliente.
            int filas;
            try (PreparedStatement ps = conexion.prepareStatement("""
                    DELETE FROM dbo.Cliente
                    WHERE id_cliente = ?
                    """)) {
                ps.setLong(1, idCliente);
                filas = ps.executeUpdate();
            }

            conexion.commit();
            return filas == 1;

        } catch (SQLException | RuntimeException e) {
            try {
                conexion.rollback();
            } catch (SQLException errorRollback) {
                e.addSuppressed(errorRollback);
            }
            throw e;
        }
    }
  }


  //metodo de verificar las consultas de cuentas antes de eliminar
  public java.util.List<ClienteTelefonia> listar()
        throws SQLException {

    String sql = """
        SELECT id_cliente, nombre, apellido,
               telefono, correo, estado
        FROM dbo.Cliente
        ORDER BY id_cliente DESC
        """;

    java.util.List<ClienteTelefonia> clientes =
            new java.util.ArrayList<>();

    try (Connection conexion = DatabaseConfig.getConnection();
         PreparedStatement sentencia =
                 conexion.prepareStatement(sql);
         ResultSet resultado = sentencia.executeQuery()) {

        while (resultado.next()) {
            ClienteTelefonia cliente = new ClienteTelefonia();

            cliente.setIdCliente(
                resultado.getLong("id_cliente")
            );
            cliente.setNombre(
                resultado.getString("nombre")
            );
            cliente.setApellido(
                resultado.getString("apellido")
            );
            cliente.setTelefono(
                resultado.getString("telefono")
            );
            cliente.setCorreo(
                resultado.getString("correo")
            );
            cliente.setEstado(
                resultado.getString("estado")
            );

            clientes.add(cliente);
        }
    }

        return clientes;
    }
}
