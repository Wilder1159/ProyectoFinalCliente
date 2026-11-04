
package main.com.ClienteTelefonia.controller;

import java.security.GeneralSecurityException;
import java.sql.SQLException;

import main.com.ClienteTelefonia.dao.RegistroDAO;
import main.com.ClienteTelefonia.model.ClienteTelefonia;


public class RegistroController {
    
    //atributos
    private final RegistroDAO registroDAO;
    
    //constructor con parametro
    public RegistroController(RegistroDAO registroDAO) {
        this.registroDAO = registroDAO;
    }
    
    /**
     * Registra un cliente, su cuenta telefónica
     * y sus credenciales de acceso.
    */
    public long registrarCliente(String nombre, String apellido, String telefono, 
                                String correo, String username, char[] password)
                            throws SQLException, GeneralSecurityException {
        
        validarDatos(nombre, apellido, telefono, correo);
        
        ClienteTelefonia cliente = new ClienteTelefonia();
        cliente.setNombre(nombre.trim());
        cliente.setApellido(apellido.trim());
        cliente.setTelefono(telefono.trim());
        cliente.setCorreo(correo == null || correo.isBlank() ? null : correo.trim());
        
        return registroDAO.registrarClienteConUsuario(cliente, username, password);
    }
    
    //nuevo metodo: validar los datos
    private void validarDatos(String nombre, String apellido, String telefono, String correo) {
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("EL NOMBRE ES OBLIGATORIO.");
        }
        
        if(apellido == null || apellido.isBlank()){
            throw new IllegalArgumentException("EL APELLIDO ES OBLIGATORIO."); 
        }
        
        if (telefono == null || telefono.isBlank()){
            throw new IllegalArgumentException("EL TELEFONO ES OBLIGATORIO.");
        }
        
        if(correo != null && correo.length()> 255) {
            throw new IllegalArgumentException("EL CORREO NO PUEDE SUPERAR EL LARGO PERMITIDO.");
        }
    }
    
}
