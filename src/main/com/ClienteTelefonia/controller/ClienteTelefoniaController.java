
package main.com.ClienteTelefonia.controller;

import main.com.ClienteTelefonia.model.ClienteTelefonia;


public class ClienteTelefoniaController {
    //atributos privados
    private ClienteTelefonia clienteActual;
    
    //constructor sin parametros con la intención que cliente se mantenga en secuencia
    public ClienteTelefoniaController() {
        this.clienteActual = null;
    }
    
    //METODOS PARA LAS OPERACIONES
    //primer metodo de crear el cliente
    public void crearCliente(String nombre, double saldoIncial) {
        clienteActual = new ClienteTelefonia(nombre, saldoIncial);
    }
    
    //2do metodo, obtener al cliente actual con los datos ingresados
    public ClienteTelefonia obtenerClienteActual(){
        return clienteActual;
    }
    
    //3er metodo, es realizar la recarga para obtener nuevo saldo final con el clienteActual
    //el controlador solo hace ""Cliente, realiza esta recarga." dado que los datos lo tenemos en la clase ClienteTelefonia"
    public void realizarRecarga(int tipo, double monto){
        clienteActual.realizarRecarga(tipo, monto);
    }
    
    //4to metodo, el consumo para poder determinar cuanto se consumio 
    public boolean realizarConsumo(double monto) {
        return clienteActual.realizarConsumo(monto);
    }
    
    //METODOS PARA LAS CONSULTAS DEL REPORTE FINAL
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
