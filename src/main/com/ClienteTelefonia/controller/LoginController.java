
package main.com.ClienteTelefonia.controller;

import java.security.GeneralSecurityException;
import java.sql.SQLException;

import main.com.ClienteTelefonia.dao.UsuarioDAO;
import main.com.ClienteTelefonia.model.Usuario;
import main.com.ClienteTelefonia.service.PasswordService;

public class LoginController {
    private final UsuarioDAO usuarioDAO;

    public LoginController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario autenticar(
            String username,
            char[] password
    ) throws SQLException, GeneralSecurityException {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                "Ingresa tu nombre de usuario."
            );
        }

        if (password == null || password.length == 0) {
            throw new IllegalArgumentException(
                "Ingresa tu contraseña."
            );
        }

        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario == null || !usuario.isActivo()) {
            throw new IllegalArgumentException(
                "Credenciales incorrectas o cuenta inactiva."
            );
        }

        boolean passwordCorrecta =
            PasswordService.verificarPassword(
                password,
                usuario.getPasswordSalt(),
                usuario.getPasswordHash(),
                usuario.getIteraciones()
            );

        if (!passwordCorrecta) {
            throw new IllegalArgumentException(
                "Credenciales incorrectas o cuenta inactiva."
            );
        }

        if (!"ADMIN".equalsIgnoreCase(usuario.getRol())
                && !"CLIENTE".equalsIgnoreCase(usuario.getRol())) {

            throw new IllegalStateException(
                "El usuario tiene un rol no válido."
            );
        }

        return usuario;
    }
}
