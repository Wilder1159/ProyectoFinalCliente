        
package main.com.ClienteTelefonia.view;

import main.com.ClienteTelefonia.controller.ClienteTelefoniaController;
import javax.swing.JOptionPane;
import main.com.ClienteTelefonia.service.TelnyxLlamadaService;


public class JConsumo extends javax.swing.JFrame {

    //se crea la instancia del contoller y solo se muestra, los campos no debe de ser editables
    private ClienteTelefoniaController controller;
    
    //ATRIBUTO PARA EL SERVICIO DE LLAMADA SE INTEGRA
    private final TelnyxLlamadaService telnyxService = new TelnyxLlamadaService();
    
    //se agregan los demás atributos
    private boolean llamadaActiva = false;
    
    
    //se configura el constructor con el sim de llamada
    public JConsumo(ClienteTelefoniaController controller) {
        initComponents();
        
        //se agregar la el numero de destino
        txtNumeroDestino.setEnabled(true);

        lblEstadoLlamada.setText("Estado: En espera");

        //
        this.controller = controller;
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        if(controller.obtenerClienteActual() != null) {
            txtCliente.setText(controller.obtenerNombre());
        
            txtSaldo.setText(String.format("%.2f", controller.obtenerSaldoActual()));
        }
        
        txtCliente.setEditable(false);
        txtSaldo.setEditable(false);
        txtMonto.setEditable(false);
        
        txtMonto.setText("0.00");
        lblCronometro.setText("00:00");
        lblCosto.setText("Costo: S/ 0.00");
    }
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtSaldo = new javax.swing.JTextField();
        btnConsumir = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        lblCosto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        lblCronometro = new javax.swing.JLabel();
        lblNumeroDestino = new javax.swing.JLabel();
        txtNumeroDestino = new javax.swing.JTextField();
        lblEstadoLlamada = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CONSUMO TELEFONICO");

        lblCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCliente.setText("CLIENTE");

        lblSaldo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSaldo.setText("SALDO DISPONIBLE");

        txtCliente.setEditable(false);

        btnConsumir.setText("LLAMAR");
        btnConsumir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsumirActionPerformed(evt);
            }
        });

        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        lblCosto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCosto.setText("COSTO ESTIMADO");

        lblNumeroDestino.setText("NUMERO DE DESTINO");

        lblEstadoLlamada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstadoLlamada.setText("Estado: En espera");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lblEstadoLlamada, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNumeroDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNumeroDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(107, 107, 107)
                        .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsumir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstadoLlamada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCosto))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsumir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // VOVLER ATRAS MEJORADA
        
        if(llamadaActiva){
            javax.swing.JOptionPane.showMessageDialog(this, "Espere que termine la llamada.");
            return;
        }
        
        MenuOpciones menu = new MenuOpciones(controller);
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnConsumirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsumirActionPerformed
        //btn de llamda, esta pasara directo a real
            if (llamadaActiva) {
            return;
        }

        iniciarLlamadaReal();

    }//GEN-LAST:event_btnConsumirActionPerformed

    //METODOS PARA PODER REALIZAR LA LLAMADA SIM Y REAL
        //METODO PARA LA LLAMADA REAL
        private void iniciarLlamadaReal() {

            // 1. Evitar solicitudes duplicadas
            if (llamadaActiva) {
                return;
            }

            // 2. Verificar que exista un cliente
            if (!controller.existeCliente()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Primero debe registrar un cliente."
                );
                return;
            }

            // 3. Obtener y limpiar el número ingresado
            String destino = txtNumeroDestino.getText()
                    .trim()
                    .replaceAll("[\\s-]", "");

            // Aceptar numeros peruanos
            if (destino.matches("9\\d{8}")) {
                destino = "+51" + destino;
            }

            // Validar formato internacional E.164
            if (!destino.matches("^\\+[1-9]\\d{7,14}$")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un número válido en formato internacional.\n"
                        + "Ejemplo peruano: +519XXXXXXXX"
                );
                return;
            }

            // 4. Verificar saldo
            double saldo = controller.obtenerSaldoActual();

            if (saldo <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "No tiene saldo disponible. Realice una recarga."
                );
                return;
            }

            txtNumeroDestino.setText(destino);

            // 5. Confirmar la llamada antes de enviarla
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea llamar al número " + destino + "?\n"
                    + "La llamada puede generar cargos reales.",
                    "Confirmar llamada",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion != JOptionPane.YES_OPTION) {
                lblEstadoLlamada.setText("Estado: En espera");
                return;
            }

            // 6. Bloquear controles mientras se envía la solicitud
            llamadaActiva = true;
            btnConsumir.setEnabled(false);
            btnVolver.setEnabled(false);


            final String numeroDestino = destino;

            javax.swing.SwingWorker<String, Void> worker =
                    new javax.swing.SwingWorker<>() {

                @Override
                protected String doInBackground() throws Exception {
                    return telnyxService.realizarLlamada(numeroDestino);
                }

                @Override
                protected void done() {
                    try {
                        String respuesta = get();

                        System.out.println(
                                "Respuesta de la solicitud: " + respuesta
                        );

                        lblEstadoLlamada.setText(
                                "Estado: Solicitud aceptada"
                        );

                        JOptionPane.showMessageDialog(
                                JConsumo.this,
                                "Telnyx aceptó la solicitud.\n"
                                + "Destino: " + numeroDestino + "\n"
                                + "Esto no confirma que la llamada haya sido contestada."
                        );

                    } catch (Exception ex) {
                        lblEstadoLlamada.setText(
                                "Estado: Error al solicitar llamada"
                        );

                        ex.printStackTrace();

                        JOptionPane.showMessageDialog(
                                JConsumo.this,
                                "No se pudo solicitar la llamada.\n"
                                + ex.getMessage(),
                                "Error de Telnyx",
                                JOptionPane.ERROR_MESSAGE
                        );

                    } finally {
                        // Liberar la interfaz al terminar la solicitud HTTP
                        llamadaActiva = false;

                        btnConsumir.setEnabled(true);   
                        btnVolver.setEnabled(true);
                        txtNumeroDestino.setEnabled(true);

                        lblEstadoLlamada.setText("Estado: ");
                    }
                }
            };

            worker.execute();
        }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsumir;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblCronometro;
    private javax.swing.JLabel lblEstadoLlamada;
    private javax.swing.JLabel lblNumeroDestino;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNumeroDestino;
    private javax.swing.JTextField txtSaldo;
    // End of variables declaration//GEN-END:variables

 
}
