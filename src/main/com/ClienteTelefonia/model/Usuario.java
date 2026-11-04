
package main.com.ClienteTelefonia.model;


public class Usuario {
    //ATRIBUTOS PRIVADOS PARA LA REUTILAZICION DE LA CREACION EL REGISTRO
    private long idUsuario;
    private long idCliente;
    private String username;
    private byte[] passwordHash;
    private byte[] passwordSalt;
    private int iteraciones;
    private boolean activo;
    private String rol;
    


    //constructor vacio
    public Usuario(){
    }
    
    //getters and setters
    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public int getIteraciones() {
        return iteraciones;
    }

    public void setIteraciones(int iteraciones) {
        this.iteraciones = iteraciones;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
       public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

