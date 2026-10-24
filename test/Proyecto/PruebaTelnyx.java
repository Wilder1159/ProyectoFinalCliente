

import main.com.ClienteTelefonia.service.TelnyxLlamadaService;

public class PruebaTelnyx {
    public static void main(String[] args) {
     TelnyxLlamadaService servicio = new TelnyxLlamadaService();

        String destino = "+51906771672";

        try {
            String respuesta = servicio.realizarLlamada(destino);
            System.out.println("Solicitud enviada a Telnyx.");
        } catch (Exception e) {
            System.err.println("Error al realizar la llamada: " + e.getMessage());
        }
    }
}