
public class ClienteTelefonia {
    // Atributos privados
    private String nombre;
    private double saldoInicial;
    private double saldoFinal;
    private int recargasTarjeta;
    private int recargasEfectivo;
    private int totalConsumos;

    // Constructor
    public ClienteTelefonia(String nombre, double saldoInicial) {
        this.nombre = nombre;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoInicial;
        this.recargasTarjeta = 0;
        this.recargasEfectivo = 0;
        this.totalConsumos = 0;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public int getRecargasTarjeta() {
        return recargasTarjeta;
    }

    public int getRecargasEfectivo() {
        return recargasEfectivo;
    }

    public int getTotalConsumos() {
        return totalConsumos;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }


    // Método para realizar una recarga
    public void realizarRecarga(int tipo, double monto) {

        if (tipo == 1) {
            // Tarjeta: se descuenta 2%
            saldoFinal += monto * 0.98;
            recargasTarjeta++;

        } else if (tipo == 2) {
            // Efectivo: se agrega el monto completo
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




