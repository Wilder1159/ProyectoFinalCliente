package Proyecto;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.com.ClienteTelefonia.config.DatabaseConfig;

public class PruebaConexionSQLServer {

    public static void main(String[] args) {

        String sql = """
            SELECT
                DB_NAME() AS base_datos,
                SUSER_SNAME() AS usuario_sql,
                @@SERVERNAME AS servidor
            """;

        try (Connection conexion =
                    DatabaseConfig.getConnection();
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {

            if (resultado.next()) {
                System.out.println(
                    "¡Conexión establecida correctamente!"
                );
                System.out.println(
                    "Base de datos: "
                    + resultado.getString("base_datos")
                );
                System.out.println(
                    "Usuario: "
                    + resultado.getString("usuario_sql")
                );
                System.out.println(
                    "Servidor: "
                    + resultado.getString("servidor")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión JDBC.");
            System.err.println("Código SQL: " + e.getErrorCode());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Detalle: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
