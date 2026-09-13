
package main.com.ClienteTelefonia.view;

import main.com.ClienteTelefonia.controller.ClienteTelefoniaController;


public class MenuOpciones extends javax.swing.JFrame {
    
    private ClienteTelefoniaController controller;

   //se agrega en el constructor vacio y otro para el parametro de volver
    public MenuOpciones() {
        initComponents();
        
        controller = new ClienteTelefoniaController(); 
    }
    
    //constructos con parametro
    public MenuOpciones(ClienteTelefoniaController controller){
        initComponents();
        
        this.controller = controller;
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCom1 = new javax.swing.JLabel();
        lblCom2 = new javax.swing.JLabel();
        lblMenuPrincipal = new javax.swing.JMenuBar();
        menuInicio = new javax.swing.JMenu();
        itemCrearCliente = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();
        menuHistorial = new javax.swing.JMenu();
        itemReporte = new javax.swing.JMenuItem();
        menuOperaciones = new javax.swing.JMenu();
        itemRecarga = new javax.swing.JMenuItem();
        itemConsumo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(0, 0, 0));

        lblCom1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCom1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCom1.setText("Welcome to the customer registration software");

        lblCom2.setText("Developers: SmithDev and Emanuel");

        lblMenuPrincipal.setAlignmentX(1.0F);
        lblMenuPrincipal.setAlignmentY(1.0F);
        lblMenuPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        menuInicio.setText("Inicio");

        itemCrearCliente.setText("Crear Cliente");
        itemCrearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCrearClienteActionPerformed(evt);
            }
        });
        menuInicio.add(itemCrearCliente);

        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        menuInicio.add(itemSalir);

        lblMenuPrincipal.add(menuInicio);

        menuHistorial.setText("Historial");

        itemReporte.setText("Reporte");
        menuHistorial.add(itemReporte);

        lblMenuPrincipal.add(menuHistorial);

        menuOperaciones.setText("Operaciones");

        itemRecarga.setText("Recarga");
        itemRecarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRecargaActionPerformed(evt);
            }
        });
        menuOperaciones.add(itemRecarga);

        itemConsumo.setText("Consumo");
        itemConsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConsumoActionPerformed(evt);
            }
        });
        menuOperaciones.add(itemConsumo);

        lblMenuPrincipal.add(menuOperaciones);

        setJMenuBar(lblMenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCom1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCom2)
                        .addGap(67, 67, 67))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(322, Short.MAX_VALUE)
                .addComponent(lblCom1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCom2)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        //para salir del programa en el btn de salir
        System.exit(0);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemCrearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCrearClienteActionPerformed
        // para llamar a la JCliente, se crea la accion
        
        JCliente viewCliente = new JCliente(controller);
        viewCliente.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_itemCrearClienteActionPerformed

    private void itemRecargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRecargaActionPerformed
        // se crea la accion de recarga
        if (!controller.existeCliente()) {
            javax.swing.JOptionPane.showConfirmDialog(this, "Primero debes de crear al cliente");
            return;
        }
        //se llama la instancia de viewRecarga
        JRecarga viewRecarga = new JRecarga(controller);
        viewRecarga.setVisible(true);
    
        this.dispose();
    }//GEN-LAST:event_itemRecargaActionPerformed

    private void itemConsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConsumoActionPerformed
        // crear el boton de consumo desde el menu de opciones
        if (!controller.existeCliente()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Crear primero al cliente para poder realizar la operacion.");
            return;
        } 
        //esta ob se trata al momento de dar click al consumo, nos da a la ventana de consumo
        JConsumo viewConsumo = new JConsumo(controller);
        viewConsumo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itemConsumoActionPerformed
 
  
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuOpciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuOpciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemConsumo;
    private javax.swing.JMenuItem itemCrearCliente;
    private javax.swing.JMenuItem itemRecarga;
    private javax.swing.JMenuItem itemReporte;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel lblCom1;
    private javax.swing.JLabel lblCom2;
    private javax.swing.JMenuBar lblMenuPrincipal;
    private javax.swing.JMenu menuHistorial;
    private javax.swing.JMenu menuInicio;
    private javax.swing.JMenu menuOperaciones;
    // End of variables declaration//GEN-END:variables
}
