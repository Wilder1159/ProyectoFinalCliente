package main.com.ClienteTelefonia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.com.ClienteTelefonia.config.DatabaseConfig;
import main.com.ClienteTelefonia.model.Usuario;

public class UsuarioDAO {
     public Usuario buscarPorUsername(String username) throws SQLException {

        String sql = """
            SELECT
                id_usuario,
                id_cliente,
                username,
                password_hash,
                password_salt,
                iteraciones,
                activo,
                rol
            FROM dbo.Usuario
            WHERE username = ?
        """;

        try (Connection conexion = DatabaseConfig.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, username.trim());

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    return null;
                }

                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getLong("id_usuario"));
                usuario.setIdCliente(rs.getLong("id_cliente"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPasswordHash(rs.getBytes("password_hash"));
                usuario.setPasswordSalt(rs.getBytes("password_salt"));
                usuario.setIteraciones(rs.getInt("iteraciones"));
                usuario.setActivo(rs.getBoolean("activo"));
                usuario.setRol(rs.getString("rol"));

                return usuario;
            }
        }
    }
}
