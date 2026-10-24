


import java.sql.SQLException;

import main.com.ClienteTelefonia.dao.ClienteDAO;
import main.com.ClienteTelefonia.model.ClienteTelefonia;

public class PruebaClienteDAO {

    public static void main(String[] args) {

        ClienteDAO dao = new ClienteDAO();

        ClienteTelefonia cliente = new ClienteTelefonia(
            "Carlos",
            "Prueba",
            "900123456",
            "carlos.prueba@example.com"
        );

        try {
            long id = dao.registrar(cliente);

            System.out.println("Cliente registrado correctamente.");
            System.out.println("ID generado: " + id);

            ClienteTelefonia encontrado =
                dao.buscarPorTelefono("900123456");

            if (encontrado != null) {
                System.out.println(
                    "Cliente encontrado: "
                    + encontrado.getNombre() + " "
                    + encontrado.getApellido()
                );
                System.out.println(
                    "Teléfono: " + encontrado.getTelefono()
                );
                System.out.println(
                    "Estado: " + encontrado.getEstado()
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al probar ClienteDAO.");
            System.err.println("Código SQL: " + e.getErrorCode());
            System.err.println("Detalle: " + e.getMessage());
        }
    }
}
