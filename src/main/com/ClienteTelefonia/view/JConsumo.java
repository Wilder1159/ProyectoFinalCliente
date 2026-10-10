        
package main.com.ClienteTelefonia.view;

import main.com.ClienteTelefonia.controller.ClienteTelefoniaController;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import main.com.ClienteTelefonia.service.LlamadaService;
import main.com.ClienteTelefonia.service.TwilioLlamadaService;


public class JConsumo extends javax.swing.JFrame {

    //se crea la instancia del contoller y solo se muestra, los campos no debe de ser editables
    private ClienteTelefoniaController controller;
    
    //ATRIBUTO PARA EL SERVICIO DE LLAMADA SE INTEGRA
    private final LlamadaService llamadaService = new LlamadaService();
    private final TwilioLlamadaService twilioService = new TwilioLlamadaService();
    
    //se agregan los demás atributos
    private Timer cronometro;
    private int segundosTranscurridos = 0;
    private boolean llamadaActiva = false;
    
    
    //se configura el constructor con el sim de llamada
    public JConsumo(ClienteTelefoniaController controller) {
        initComponents();
        
        //se agregar la el numero de destino
        cmbTipoLlamada.setSelectedItem("SIMULADA");

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
    
    //metodo temporal para el reporte del consumo 
    private void finalizarLlamada() {

        if (!llamadaActiva) {
            return;
        }

        llamadaActiva = false;

        double costo = llamadaService.calcularCosto(
            segundosTranscurridos
        );

        // Reutilizar la lógica existente del controlador
        boolean realizado = controller.realizarConsumo(costo);

        btnConsumir.setEnabled(true);
        btnVolver.setEnabled(true);
        cmbTipoLlamada.setEnabled(true);
        lblEstadoLlamada.setText("Estado: Finalizado");

        if (realizado) {

            double saldoFinal = controller.obtenerSaldoActual();

            txtSaldo.setText(
                String.format("%.2f", saldoFinal)
            );

            JOptionPane.showMessageDialog(
                this,
                """
                LLAMADA FINALIZADA

                Duraci\u00f3n: """
                + String.format("%02d:%02d",
                    segundosTranscurridos / 60,
                    segundosTranscurridos % 60)
                + "\nCosto: S/ "
                + String.format("%.2f", costo)
                + "\nSaldo actual: S/ "
                + String.format("%.2f", saldoFinal)
            );

        } else {
            JOptionPane.showMessageDialog(
                this,
                "No se pudo registrar el consumo."
            );
        }
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
        cmbTipoLlamada = new javax.swing.JComboBox<>();
        lblTipoLlamada = new javax.swing.JLabel();
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

        btnVolver.setText("VOVLER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        lblCosto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCosto.setText("COSTO ESTIMADO");

        cmbTipoLlamada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIMULADA", "REAL" }));

        lblTipoLlamada.setText("TIPO DE LLAMADA");

        lblNumeroDestino.setText("NUMERO DE DESTINO");

        lblEstadoLlamada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstadoLlamada.setText("Estado: En espera");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTipoLlamada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTipoLlamada, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNumeroDestino, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNumeroDestino, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                        .addGap(0, 33, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(lblCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(btnConsumir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lblEstadoLlamada, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoLlamada, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoLlamada, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblNumeroDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstadoLlamada, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCosto)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsumir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
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
        // bnt para poder sim la llamada
        //código de la llamada
            if (llamadaActiva) {
            return;
        }

        String tipo = (String) cmbTipoLlamada.getSelectedItem();

        if (null == tipo) {
            JOptionPane.showMessageDialog(
                    this, "Seleccione un tipo de llamada."
            );
        } else switch (tipo) {
            case "SIMULADA" -> iniciarLlamadaSimulada();
            case "REAL" -> iniciarLlamadaReal();
            default -> JOptionPane.showMessageDialog(
                        this, "Seleccione un tipo de llamada."
                );
        }
    }//GEN-LAST:event_btnConsumirActionPerformed

    //METODOS PARA PODER REALIZAR LA LLAMADA SIM Y REAL
    //METOD PARA LA SIMULADA
        private void iniciarLlamadaSimulada() {

            if (llamadaActiva) {
                return;
            }

            if (!controller.existeCliente()) {
                JOptionPane.showMessageDialog(
                    this, "Primero debe registrar un cliente."
                );
                return;
            }

            double saldo = controller.obtenerSaldoActual();
            double costo = llamadaService.CostoLlamadaCompleta();

            if (saldo < costo) {
                JOptionPane.showMessageDialog(
                    this,
                    "Saldo insuficiente.\nCosto: S/ "
                    + String.format("%.2f", costo)
                    + "\nSaldo disponible: S/ "
                    + String.format("%.2f", saldo)
                );
                return;
            }

            segundosTranscurridos = 0;
            llamadaActiva = true;

            btnConsumir.setEnabled(false);
            btnVolver.setEnabled(false);
            cmbTipoLlamada.setEnabled(false);

            lblCronometro.setText("00:00");
            lblEstadoLlamada.setText("Estado: En llamada simulada");
            lblCosto.setText("Costo: S/ 0.00");
            txtMonto.setText("0.00");

            cronometro = new Timer(1000, e -> {

                segundosTranscurridos++;

                int minutos = segundosTranscurridos / 60;
                int segundos = segundosTranscurridos % 60;

                lblCronometro.setText(
                    String.format("%02d:%02d", minutos, segundos)
                );

                double costoActual =
                    llamadaService.calcularCosto(segundosTranscurridos);

                lblCosto.setText(
                    "Costo: S/ " + String.format("%.2f", costoActual)
                );

                txtMonto.setText(
                    String.format("%.2f", costoActual)
                );

                if (segundosTranscurridos >=
                        LlamadaService.DURACION_SEGUNDOS) {

                    cronometro.stop();
                    finalizarLlamada();
                }
            });

            cronometro.start();
        }

        //METODO PARA LA LLAMADA REAL
        private void iniciarLlamadaReal() {
            if (llamadaActiva) {
                return;
            }

            if (!controller.existeCliente()) {
                JOptionPane.showMessageDialog(
                    this, "Primero debe registrar un cliente."
                );
                return;
            }

            //EN LLAMADA SALIENTE QUE SEA +51 DE PERÚ
            String destino = txtNumeroDestino.getText()
                    .trim()
                    .replaceAll("[\\s-]", "");

            // Agregar +51 automáticamente a números peruanos
            if (destino.matches("9\\d{8}")) {
                destino = "+51" + destino;
            }

            // Validar celular peruano: +51 seguido de 9 dígitos
            if (!destino.matches("\\+519\\d{8}")) {
                JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un número celular peruano válido.\n"
                    + "Ejemplo: 9XXXXXXXX o +519XXXXXXXX"
                );
                return;
            }

            // Mostrar el número normalizado
            txtNumeroDestino.setText(destino);


                        double saldo = controller.obtenerSaldoActual();
                        double costo = llamadaService.CostoLlamadaCompleta();

                        if (saldo < costo) {
                            JOptionPane.showMessageDialog(
                                this,
                                "Saldo insuficiente.\n"
                                + "Se requiere S/ "
                                + String.format("%.2f", costo)
                            );
                            return;
                        }

            lblEstadoLlamada.setText("Estado: Preparada");

            //SE INTEGRA LA API DE TWILIO DE LLAMADA
            int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea iniciar una llamada real al número "
                    + destino + "?\n"
                    + "Esta operación puede generar cargos en Twilio.",
                "Confirmar llamada",
                JOptionPane.YES_NO_OPTION
            );

            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }

            llamadaActiva = true;
            btnConsumir.setEnabled(false);
            btnVolver.setEnabled(false);
            cmbTipoLlamada.setEnabled(false);

            lblEstadoLlamada.setText("Estado: Solicitando llamada...");

            // Guardar destino para utilizarlo en SwingWorker
            final String numeroDestino = destino;

            javax.swing.SwingWorker<String, Void> worker =
                    new javax.swing.SwingWorker<>() {

                @Override
                protected String doInBackground() throws Exception {
                    return twilioService.iniciarLlamada(numeroDestino);
                }

                @Override
                protected void done() {

                    try {
                        String respuesta = get();

                        lblEstadoLlamada.setText(
                            "Estado: Solicitud aceptada"
                        );

                        JOptionPane.showMessageDialog(
                            JConsumo.this,
                            "Twilio recibió la solicitud de llamada.\n"
                            + "Revisa tu celular y los registros de Twilio."
                        );

                        // La respuesta contiene información de la llamada.
                        // Todavía falta extraer y guardar el Call SID.

                    } catch (Exception ex) {

                        lblEstadoLlamada.setText(
                            "Estado: Error al solicitar llamada"
                        );

                        JOptionPane.showMessageDialog(
                            JConsumo.this,
                            "No se pudo solicitar la llamada.\n"
                            + ex.getMessage()
                        );

                    } finally {
                        llamadaActiva = false;
                        btnConsumir.setEnabled(true);
                        btnVolver.setEnabled(true);
                        cmbTipoLlamada.setEnabled(true);
                    }
                }
            };

            worker.execute();

        }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsumir;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbTipoLlamada;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblCronometro;
    private javax.swing.JLabel lblEstadoLlamada;
    private javax.swing.JLabel lblNumeroDestino;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblTipoLlamada;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNumeroDestino;
    private javax.swing.JTextField txtSaldo;
    // End of variables declaration//GEN-END:variables
}
