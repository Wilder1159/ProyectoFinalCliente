
package main.com.ClienteTelefonia.view;

import main.com.ClienteTelefonia.controller.ClienteTelefoniaController;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Image;
import main.com.ClienteTelefonia.model.ClienteTelefonia;

public class JRecargaEfectivo extends javax.swing.JFrame {

    private ClienteTelefoniaController controller;
   
    public JRecargaEfectivo(ClienteTelefoniaController controller) {
        initComponents();
        
        this.controller = controller;
        //obtener el mismo cliente
        ClienteTelefonia cliente = controller.obtenerClienteActual();
        if (cliente != null) {
            txtCliente.setText(cliente.getNombre());
            txtCliente.setEditable(false);
        }
    }   

    //para mostrar y llamar al código QR de mi Yape
    private void mostrarQR() {
        java.net.URL ruta = getClass().getResource("/main/com/ClienteTelefonia/images/Yape.png");
        
        if (ruta == null) {
            JOptionPane.showMessageDialog(this, "No se encontro el QR del Yape");
            return;
        }
        
        ImageIcon icono = new ImageIcon(ruta);
        int ancho = lblQR.getWidth();
        int alto = lblQR.getWidth();
        
        int lado = Math.min(ancho, alto);
        if (lado <= 0) {
            lado = 200;
        }
        
        Image imagen = icono.getImage().getScaledInstance(lado, lado, Image.SCALE_SMOOTH);
        lblQR.setIcon(new ImageIcon(imagen));
        lblQR.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRecarga = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        btnGenerarQR = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnRecargar = new javax.swing.JButton();
        lblQR = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblRecarga.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblRecarga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRecarga.setText("RECARGA EN EFECTIVO");
        lblRecarga.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel1.setText("CLIENTE");

        jLabel2.setText("MONTO DE RECARGA");

        btnGenerarQR.setText("Generar Código QR");
        btnGenerarQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarQRActionPerformed(evt);
            }
        });

        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnRecargar.setText("RECARGAR");
        btnRecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblRecarga, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnGenerarQR, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtMonto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRecargar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(lblQR, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(lblRecarga, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGenerarQR)
                .addGap(18, 18, 18)
                .addComponent(lblQR, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRecargar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarQRActionPerformed
        //CONECTARLO CON EL QR DE NUESTRO YAPE
        try {
            double monto = Double.parseDouble(txtMonto.getText().trim());
            
            if (!Double.isFinite(monto) || monto <= 0) {
                JOptionPane.showMessageDialog(this, "Ingrese un monto valido");
                return;
            }
            
            //se llama al metodo del QR
            mostrarQR();
            javax.swing.JOptionPane.showMessageDialog(this, "Escanee el código QR" + 
                                                            "Monto a pagar: S/ " + 
                                                            String.format("%.2f", monto));
            
            
            //se realiza las excepciones
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe de ingresar un monto valido mayor que cero.");
        }
    }//GEN-LAST:event_btnGenerarQRActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // vovler atras
        MenuOpciones menu = new MenuOpciones(controller);
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnRecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarActionPerformed
        // btn de recarga
       try {
        if (!controller.existeCliente()) {
            JOptionPane.showMessageDialog(
                this, "No existe un cliente registrado.");
            return;
        }

        double monto = Double.parseDouble(
            txtMonto.getText().trim()
        );

        if (!Double.isFinite(monto) || monto <= 0) {
            JOptionPane.showMessageDialog(
                this, "Ingrese un monto mayor que cero.");
            return;
        }

        // Confirmación manual para la práctica académica
        int confirmar = JOptionPane.showConfirmDialog(
            this,
            "¿Se verificó el pago de S/ "
            + String.format("%.2f", monto)
            + " en Yape?",
            "Confirmar pago",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmar != JOptionPane.YES_OPTION) {
            return;
        }

        // Tipo 2: recarga sin comisión
        controller.realizarRecarga(2, monto);

        JOptionPane.showMessageDialog(
            this,
            "RECARGA REGISTRADA"
            + "Cliente: " + controller.obtenerNombre()
            + "\nMonto: S/ " + String.format("%.2f", monto)
            + "\nComisión: S/ 0.00"
            + "\nSaldo actual: S/ "
            + String.format("%.2f",
                controller.obtenerSaldoActual())
        );

        MenuOpciones menu = new MenuOpciones(controller);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        this.dispose();

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(
            this, "Ingrese un monto numérico válido.");
    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }
    }//GEN-LAST:event_btnRecargarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarQR;
    private javax.swing.JButton btnRecargar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblQR;
    private javax.swing.JLabel lblRecarga;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
