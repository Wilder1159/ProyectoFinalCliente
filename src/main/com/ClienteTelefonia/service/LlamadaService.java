
package main.com.ClienteTelefonia.service;


public class LlamadaService {
    
    //para crear las clases para el soporte de la API de llamada de un servicio externo y simular la llamada
    //se crea la simulacion para 3 mins de llamada y el cobro de 0.20 por min
    public static final int DURACION_SEGUNDOS = 180;
    public static final double TARIFA_MINUTO = 0.20;
    
    
    //metodo para el calculo del costro
    public double calcularCosto(int segundos){
        
        if(segundos <0 || segundos > DURACION_SEGUNDOS) {
            throw new IllegalArgumentException("Duracion de llamada invalida.");
        } 
        //cobro proporcional a los segundos utilizados
        return Math.round((segundos/60.0) * TARIFA_MINUTO * 100)/100.0;
    }
    
    
    //metodo del costo de la llamada
    public double CostoLlamadaCompleta(){
        return calcularCosto(DURACION_SEGUNDOS);
    }
}
