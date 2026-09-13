
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

        lblMenuPrincipal = new javax.swing.JMenuBar();
        menuInicio = new javax.swing.JMenu();
        itemCrearCliente = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();
        menuHistorial = new javax.swing.JMenu();
        itemReporte = new javax.swing.JMenuItem();
        menuOperaciones = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        itemConsumo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jMenuItem2.setText("Recarga");
        menuOperaciones.add(jMenuItem2);

        itemConsumo.setText("Consumo");
        menuOperaciones.add(itemConsumo);

        lblMenuPrincipal.add(menuOperaciones);

        setJMenuBar(lblMenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
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
    private javax.swing.JMenuItem itemReporte;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuBar lblMenuPrincipal;
    private javax.swing.JMenu menuHistorial;
    private javax.swing.JMenu menuInicio;
    private javax.swing.JMenu menuOperaciones;
    // End of variables declaration//GEN-END:variables
}
