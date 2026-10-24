
package main.com.ClienteTelefonia.model;


public class ClienteTelefonia {
    //se modifica la clase prinicipal para agregar y modificar desde la base de datos
  // Identificación del cliente
    private long idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String estado;

    // Datos de la telefonia
    private double saldoInicial;
    private double saldoFinal;
    private int recargasTarjeta;
    private int recargasEfectivo;
    private int totalConsumos;

    // Constructor vacío: permite crear objetos desde el base de datos
    public ClienteTelefonia() {
        this.saldoInicial = 0;
        this.saldoFinal = 0;
        this.recargasTarjeta = 0;
        this.recargasEfectivo = 0;
        this.totalConsumos = 0;
        this.estado = "ACTIVO";
    }

    // Conservamos el constructor que ya utilizaba tu aplicación
    public ClienteTelefonia(String nombre) {
        this();
        this.nombre = nombre;
    }

    // Constructor para los datos de identidad
    public ClienteTelefonia(
            String nombre,
            String apellido,
            String telefono,
            String correo) {

        this();
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public int getRecargasTarjeta() {
        return recargasTarjeta;
    }

    public void setRecargasTarjeta(int recargasTarjeta) {
        this.recargasTarjeta = recargasTarjeta;
    }

    public int getRecargasEfectivo() {
        return recargasEfectivo;
    }

    public void setRecargasEfectivo(int recargasEfectivo) {
        this.recargasEfectivo = recargasEfectivo;
    }

    public int getTotalConsumos() {
        return totalConsumos;
    }

    public void setTotalConsumos(int totalConsumos) {
        this.totalConsumos = totalConsumos;
    }

    // METODO Recarga: 1 = tarjeta, 2 = efectivo
    public void realizarRecarga(int tipo, double monto) {

        if (!Double.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException(
                "El monto de la recarga debe ser mayor que cero."
            );
        }

        if (tipo == 1) {
            saldoFinal += monto * 0.98;
            recargasTarjeta++;
        } else if (tipo == 2) {
            saldoFinal += monto;
            recargasEfectivo++;
        } else {
            throw new IllegalArgumentException(
                "El tipo de recarga no es válido."
            );
        }
    }

    public boolean realizarConsumo(double monto) {

        if (!Double.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException(
                "El consumo debe ser mayor que cero."
            );
        }

        if (monto > saldoFinal) {
            return false;
        }

        saldoFinal -= monto;
        totalConsumos++;

        return true;
    }
}
