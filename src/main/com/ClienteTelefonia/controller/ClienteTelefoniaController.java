
package main.com.ClienteTelefonia.controller;

import java.sql.SQLException;

import main.com.ClienteTelefonia.dao.ClienteDAO;
import main.com.ClienteTelefonia.model.ClienteTelefonia;

public class ClienteTelefoniaController {

    private ClienteTelefonia clienteActual;
    private final ClienteDAO clienteDAO;

    public ClienteTelefoniaController() {
        this.clienteActual = null;
        this.clienteDAO = new ClienteDAO();
    }

    // SE ACTUALIZ PARA crea un cliente solo en memoria.
    // Se conserva para no romper la demas ventanas que lo utilizan.
    public void crearCliente(String nombre) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                "El nombre es obligatorio."
            );
        }

        this.clienteActual = new ClienteTelefonia(nombre);
    }

    // Nuevo método: registra el cliente en SQL Server.
    public ClienteTelefonia registrarCliente(
            String nombre,
            String apellido,
            String telefono,
            String correo) throws SQLException {

        validarTexto(nombre, "El nombre es obligatorio.");
        validarTexto(apellido, "El apellido es obligatorio.");
        validarTexto(telefono, "El teléfono es obligatorio.");

        nombre = nombre.trim();
        apellido = apellido.trim();
        telefono = telefono.trim();

        if (nombre.length() > 160) {
            throw new IllegalArgumentException(
                "El nombre no puede superar los 160 caracteres."
            );
        }

        if (apellido.length() > 160) {
            throw new IllegalArgumentException(
                "El apellido no puede superar los 160 caracteres."
            );
        }

        if (telefono.length() > 16) {
            throw new IllegalArgumentException(
                "El teléfono no puede superar los 16 caracteres."
            );
        }

        if (correo != null && correo.isBlank()) {
            correo = null;
        } else if (correo != null) {
            correo = correo.trim();

            if (correo.length() > 300) {
                throw new IllegalArgumentException(
                    "El correo no puede superar los 300 caracteres."
                );
            }
        }

        ClienteTelefonia nuevoCliente =
            new ClienteTelefonia(
                nombre,
                apellido,
                telefono,
                correo
            );

        // Solo se establece como cliente actual
        // después de que SQL Server confirme el registro.
        clienteDAO.registrar(nuevoCliente);

        this.clienteActual = nuevoCliente;

        return nuevoCliente;
    }

    private void validarTexto(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    public ClienteTelefonia obtenerClienteActual() {
        return clienteActual;
    }

    public boolean existeCliente() {
        return clienteActual != null;
    }

    public void realizarRecarga(int tipo, double monto) {
        if (!existeCliente()) {
            throw new IllegalStateException(
                "Primero debe registrar un cliente."
            );
        }

        clienteActual.realizarRecarga(tipo, monto);
    }

    public boolean realizarConsumo(double monto) {
        if (!existeCliente()) {
            throw new IllegalStateException(
                "Primero debe registrar un cliente."
            );
        }

        return clienteActual.realizarConsumo(monto);
    }

    //nuevo metodo para eliminar al cliente, sin perder el historial de consumo y recarga
    public java.util.List<ClienteTelefonia> listarClientes()
        throws java.sql.SQLException {

        return clienteDAO.listar();
    }

    public boolean eliminarCliente(long idCliente)
            throws java.sql.SQLException {

        if (idCliente <= 0) {
            throw new IllegalArgumentException(
                "El ID del cliente no es válido."
            );
        }

        boolean eliminado = clienteDAO.eliminar(idCliente);

        if (eliminado
                && clienteActual != null
                && clienteActual.getIdCliente() == idCliente) {
            clienteActual = null;
        }

        return eliminado;
    }

    //metodos de obtener los datos del cliente para mostrarlos en el reporte
    public String obtenerNombre() {
        return clienteActual.getNombre();
    }

    public Double obtenerSaldoActual() {
        return clienteActual.getSaldoFinal();
    }

    public int obtenerRecargasTarjeta() {
        return clienteActual.getRecargasTarjeta();
    }

    public int obtenerRecargasEfectivo() {
        return clienteActual.getRecargasEfectivo();
    }

    public int obtenerTotalConsumo() {
        return clienteActual.getTotalConsumos();
    }
}
