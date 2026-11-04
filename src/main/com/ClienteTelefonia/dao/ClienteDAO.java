
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
                SELECT
                    c.id_cliente,
                    c.nombre,
                    c.apellido,
                    c.telefono,
                    c.correo,
                    c.estado AS estado_cliente,
                    cu.saldo,
                    cu.estado AS estado_cuenta
                FROM dbo.Cliente AS c
                INNER JOIN dbo.Cuenta AS cu
                    ON cu.id_cliente = c.id_cliente
                WHERE c.id_cliente = ?
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
                    resultado.getString("estado_cliente")
                );

                // Recuperar el saldo persistido en SQL Server.
                    double saldo = resultado.getDouble("saldo");
                    cliente.setSaldoInicial(saldo);
                    cliente.setSaldoFinal(saldo);

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





    //nuevo metodo para el registro de recarga por YAPE
    public double registrarRecargaYape(long idCliente, double monto)
            throws SQLException {

        if (idCliente <= 0) {
            throw new IllegalArgumentException("El cliente no es válido.");
        }

        if (!Double.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException(
                "El monto debe ser mayor que cero."
            );
        }

        // Trabajamos con dos decimales para importes monetarios.
        java.math.BigDecimal importe =
            java.math.BigDecimal.valueOf(monto)
                .setScale(2, java.math.RoundingMode.HALF_UP);

        Connection cn = null;

        try {
            cn = DatabaseConfig.getConnection();
            cn.setAutoCommit(false);

            long idCuenta;
            java.math.BigDecimal saldoAnterior;
            String estadoCuenta;

            // Bloquea la fila de la cuenta durante la transacción.
            String sqlCuenta =
                "SELECT c.id_cuenta, c.saldo, c.estado " +
                "FROM dbo.Cuenta c WITH (UPDLOCK, ROWLOCK) " +
                "INNER JOIN dbo.Cliente cl " +
                "ON cl.id_cliente = c.id_cliente " +
                "WHERE c.id_cliente = ? AND cl.estado = 'ACTIVO'";

            try (PreparedStatement ps = cn.prepareStatement(sqlCuenta)) {
                ps.setLong(1, idCliente);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException(
                            "No se encontró una cuenta para el cliente activo."
                        );
                    }

                    idCuenta = rs.getLong("id_cuenta");
                    saldoAnterior = rs.getBigDecimal("saldo");
                    estadoCuenta = rs.getString("estado");
                }
            }

            if (!"ACTIVA".equalsIgnoreCase(estadoCuenta)) {
                throw new SQLException("La cuenta no está activa.");
            }

            java.math.BigDecimal saldoNuevo =
                saldoAnterior.add(importe)
                    .setScale(2, java.math.RoundingMode.HALF_UP);

            // 1. Registrar la recarga confirmada manualmente.
            long idRecarga;
            String sqlRecarga =
                "INSERT INTO dbo.Recarga " +
                "(id_cuenta, monto, metodo, estado, fecha_confirmacion) " +
                "OUTPUT INSERTED.id_recarga " +
                "VALUES (?, ?, 'YAPE', 'CONFIRMADA', SYSDATETIME())";

            try (PreparedStatement ps = cn.prepareStatement(sqlRecarga)) {
                ps.setLong(1, idCuenta);
                ps.setBigDecimal(2, importe);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException(
                            "No se pudo obtener el ID de la recarga."
                        );
                    }
                    idRecarga = rs.getLong(1);
                }
            }

            // 2. Actualizar el saldo, sin comisión.
            String sqlActualizar =
                "UPDATE dbo.Cuenta " +
                "SET saldo = ? " +
                "WHERE id_cuenta = ? AND estado = 'ACTIVA'";

            try (PreparedStatement ps = cn.prepareStatement(sqlActualizar)) {
                ps.setBigDecimal(1, saldoNuevo);
                ps.setLong(2, idCuenta);

                if (ps.executeUpdate() != 1) {
                    throw new SQLException(
                        "No se pudo actualizar el saldo de la cuenta."
                    );
                }
            }

            // 3. Registrar el movimiento contable.
            String sqlMovimiento =
                "INSERT INTO dbo.MovimientoSaldo " +
                "(id_cuenta, id_recarga, tipo, monto, " +
                "saldo_anterior, saldo_posterior, descripcion) " +
                "VALUES (?, ?, 'RECARGA', ?, ?, ?, ?)";

            try (PreparedStatement ps = cn.prepareStatement(sqlMovimiento)) {
                ps.setLong(1, idCuenta);
                ps.setLong(2, idRecarga);
                ps.setBigDecimal(3, importe);
                ps.setBigDecimal(4, saldoAnterior);
                ps.setBigDecimal(5, saldoNuevo);
                ps.setString(6, "Recarga Yape confirmada manualmente");
                ps.executeUpdate();
            }

            // Los tres cambios se confirman juntos.
            cn.commit();

            return saldoNuevo.doubleValue();

        } catch (SQLException | RuntimeException ex) {
            if (cn != null) {
                try {
                    cn.rollback();
                } catch (SQLException rollbackEx) {
                    ex.addSuppressed(rollbackEx);
                }
            }
            throw ex;

        } finally {
            if (cn != null) {
                try {
                    cn.setAutoCommit(true);
                    cn.close();
                } catch (SQLException ex) {
                    // No ocultar el resultado de la transacción
                    // por un error al restaurar/cerrar la conexión.
                }
            }
        }
    }

    //metodo nuevo: registro de pago por TARJETA, SEA CREDITO O DEBITO
        public double registrarRecargaTarjeta(long idCliente, double monto)
                throws SQLException {

            if (idCliente <= 0) {
                throw new IllegalArgumentException("El cliente no es válido.");
            }

            if (!Double.isFinite(monto) || monto <= 0) {
                throw new IllegalArgumentException(
                    "El monto debe ser mayor que cero."
                );
            }

            java.math.BigDecimal importe =
                java.math.BigDecimal.valueOf(monto)
                    .setScale(2, java.math.RoundingMode.HALF_UP);

            java.math.BigDecimal tasaComision =
                new java.math.BigDecimal("0.02");

            java.math.BigDecimal importeNeto =
                importe.multiply(java.math.BigDecimal.ONE.subtract(tasaComision))
                    .setScale(2, java.math.RoundingMode.HALF_UP);

            java.math.BigDecimal comision = importe.subtract(importeNeto);

            Connection cn = null;

            try {
                cn = DatabaseConfig.getConnection();
                cn.setAutoCommit(false);

                long idCuenta;
                java.math.BigDecimal saldoAnterior;
                String estadoCuenta;

                String sqlCuenta =
                    "SELECT c.id_cuenta, c.saldo, c.estado " +
                    "FROM dbo.Cuenta c WITH (UPDLOCK, ROWLOCK) " +
                    "INNER JOIN dbo.Cliente cl " +
                    "ON cl.id_cliente = c.id_cliente " +
                    "WHERE c.id_cliente = ? AND cl.estado = 'ACTIVO'";

                try (PreparedStatement ps = cn.prepareStatement(sqlCuenta)) {
                    ps.setLong(1, idCliente);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            throw new SQLException(
                                "No se encontró una cuenta para el cliente activo."
                            );
                        }

                        idCuenta = rs.getLong("id_cuenta");
                        saldoAnterior = rs.getBigDecimal("saldo");
                        estadoCuenta = rs.getString("estado");
                    }
                }

                if (!"ACTIVA".equalsIgnoreCase(estadoCuenta)) {
                    throw new SQLException("La cuenta no está activa.");
                }

                java.math.BigDecimal saldoNuevo =
                    saldoAnterior.add(importeNeto)
                        .setScale(2, java.math.RoundingMode.HALF_UP);

                // Guardar el monto bruto de la recarga.
                String sqlRecarga =
                    "INSERT INTO dbo.Recarga " +
                    "(id_cuenta, monto, metodo, estado, fecha_confirmacion) " +
                    "OUTPUT INSERTED.id_recarga " +
                    "VALUES (?, ?, 'TARJETA', 'CONFIRMADA', SYSDATETIME())";

                long idRecarga;

                try (PreparedStatement ps = cn.prepareStatement(sqlRecarga)) {
                    ps.setLong(1, idCuenta);
                    ps.setBigDecimal(2, importe);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            throw new SQLException(
                                "No se pudo obtener el ID de la recarga."
                            );
                        }
                        idRecarga = rs.getLong(1);
                    }
                }

                // Acreditar el importe neto.
                String sqlActualizar =
                    "UPDATE dbo.Cuenta SET saldo = ? " +
                    "WHERE id_cuenta = ? AND estado = 'ACTIVA'";

                try (PreparedStatement ps = cn.prepareStatement(sqlActualizar)) {
                    ps.setBigDecimal(1, saldoNuevo);
                    ps.setLong(2, idCuenta);

                    if (ps.executeUpdate() != 1) {
                        throw new SQLException(
                            "No se pudo actualizar el saldo de la cuenta."
                        );
                    }
                }

                // Registrar el importe neto y documentar la comisión.
                String sqlMovimiento =
                    "INSERT INTO dbo.MovimientoSaldo " +
                    "(id_cuenta, id_recarga, tipo, monto, " +
                    "saldo_anterior, saldo_posterior, descripcion) " +
                    "VALUES (?, ?, 'RECARGA', ?, ?, ?, ?)";

                String descripcion =
                    "Recarga con tarjeta confirmada manualmente. " +
                    "Monto bruto: S/ " + importe.toPlainString() +
                    "; comisión 2%: S/ " + comision.toPlainString() +
                    "; monto acreditado: S/ " + importeNeto.toPlainString();

                try (PreparedStatement ps = cn.prepareStatement(sqlMovimiento)) {
                    ps.setLong(1, idCuenta);
                    ps.setLong(2, idRecarga);
                    ps.setBigDecimal(3, importeNeto);
                    ps.setBigDecimal(4, saldoAnterior);
                    ps.setBigDecimal(5, saldoNuevo);
                    ps.setString(6, descripcion);
                    ps.executeUpdate();
                }

                cn.commit();
                return saldoNuevo.doubleValue();

            } catch (SQLException | RuntimeException ex) {
                if (cn != null) {
                    try {
                        cn.rollback();
                    } catch (SQLException rollbackEx) {
                        ex.addSuppressed(rollbackEx);
                    }
                }
                throw ex;

            } finally {
                if (cn != null) {
                    try {
                        cn.setAutoCommit(true);
                        cn.close();
                    } catch (SQLException ex) {
                        // No ocultar el resultado de la transacción.
                    }
                }
            }
        }




    //metodo de buscar por ID
        public ClienteTelefonia buscarPorId(long idCliente) throws SQLException {
            String sql =
                "SELECT " +
                "cl.id_cliente, cl.nombre, cl.apellido, " +
                "cl.telefono, cl.correo, cl.estado, " +
                "c.saldo, c.estado AS estado_cuenta " +
                "FROM dbo.Cliente cl " +
                "INNER JOIN dbo.Cuenta c " +
                "ON c.id_cliente = cl.id_cliente " +
                "WHERE cl.id_cliente = ?";

            try (Connection cn = DatabaseConfig.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql)) {

                ps.setLong(1, idCliente);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        return null;
                    }

                    ClienteTelefonia cliente = new ClienteTelefonia();

                    cliente.setIdCliente(rs.getLong("id_cliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setEstado(rs.getString("estado"));

                    // Cargar el saldo que realmente está en SQL Server.
                    double saldoActual = rs.getBigDecimal("saldo").doubleValue();
                    cliente.setSaldoFinal(saldoActual);

                    return cliente;
                }
            }
        }

}
