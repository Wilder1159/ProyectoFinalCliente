
package main.com.ClienteTelefonia.model;


public class ClienteTelefonia {
    //atributos privados
    private String nombre;
    private double saldoInicial;
    private double saldoFinal;
    private int recargasTarjeta;
    private int recargasEfectivo;
    private int totalConsumos;
    
    //constructor
    public ClienteTelefonia(String nombre, double saldoInicial) {
        this.nombre = nombre;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoInicial;
        this.recargasTarjeta = 0;
        this.recargasEfectivo = 0;
        this.totalConsumos = 0;
    }

    //getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    
    //metodos para el calculo de consumo y recarga
    public void realizarRecarga(int tipo, double monto) {
        if(tipo == 1) {
            //tarjeta = Descuento del 2%
            saldoFinal += monto * 0.98;
            recargasTarjeta++;
        } else if (tipo == 2){
            //efectivo =  se agrega el monto completo
            saldoFinal += monto;
            recargasEfectivo++;
        }
    }
    
    // Método para realizar consumos
    public boolean realizarConsumo(double monto) {

        if (monto <= saldoFinal) {
            saldoFinal -= monto;
            totalConsumos++;
            return true;
        }
        return false;
    }    
}
