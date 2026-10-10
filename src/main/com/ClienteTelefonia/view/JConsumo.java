        
package main.com.ClienteTelefonia.view;

import main.com.ClienteTelefonia.controller.ClienteTelefoniaController;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import main.com.ClienteTelefonia.service.LlamadaService;

public class JConsumo extends javax.swing.JFrame {

    //se crea la instancia del contoller y solo se muestra, los campos no debe de ser editables
    private ClienteTelefoniaController controller;
    
    
    //ATRIBUTO PARA EL SERVICIO DE LLAMADA SE INTEGRA
    private final LlamadaService llamadaService = new LlamadaService();
    
    //se agregan los demás atributos
    private Timer cronometro;
    private int segundosTranscurridos = 0;
    private boolean llamadaActiva = false;
    
    
    //se configura el constructor con el sim de llamada
    public JConsumo(ClienteTelefoniaController controller) {
        initComponents();
        
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CONSUMO");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConsumir, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 33, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCosto)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
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
        //primeroo evitar dos llamadas simultaneadas
        if (llamadaActiva){
            return;
        }
        
        //VERIFICAR AL CLIENTE
        if (!controller.existeCliente()){
            JOptionPane.showMessageDialog(this, "Primero debes de registrar a un cliente.");
            return;
        }
        
        double saldo = controller.obtenerSaldoActual();
        double costo = llamadaService.CostoLlamadaCompleta();
        
        //VALIDAR SALDO SUFICIENTE PARA LOS 3 MINS
        if(saldo < costo){
            javax.swing.JOptionPane.showMessageDialog(this, """
                                                            Saldo insuficiente para poder realizar la llamada.
                                                            Costo: S/""" + String.format("%.2f", costo) + 
                                                            "\nSlado Disponible: S/ " + String.format("%.2f", saldo));
            return;
        }
        
        segundosTranscurridos = 0;
        llamadaActiva = true;
        
        btnConsumir.setEnabled(false);
        btnVolver.setEnabled(false);
        
        lblCronometro.setText("00:00");
        lblCosto.setText("Costo: S/ 0.00");
        txtMonto.setText("0.00");
        
        cronometro = new Timer(1000, e ->{
            segundosTranscurridos++;
            
            int minutos = segundosTranscurridos / 60;
            int segundos = segundosTranscurridos % 60;
            
            lblCronometro.setText(
            String.format("%02d:%02d", minutos, segundos));
            
            double costoActual = llamadaService.calcularCosto(segundosTranscurridos);
            
            lblCosto.setText("Costo: S/ " + String.format("%.2f", costoActual));
            
            txtMonto.setText(String.format("%.2f", costoActual));
            
            //finalizar automaticamente a los 3 mins
            if(segundosTranscurridos >= LlamadaService.DURACION_SEGUNDOS){
                cronometro.stop();
                finalizarLlamada();
            }
        });
        
        cronometro.start();
    }//GEN-LAST:event_btnConsumirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsumir;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblCronometro;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtSaldo;
    // End of variables declaration//GEN-END:variables
}
