
package main.com.ClienteTelefonia.view;

import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.com.ClienteTelefonia.controller.RegistroController;


public class JRegistro extends javax.swing.JFrame {
    
     //atributos
    private final RegistroController registroController;

    
    
    public JRegistro(RegistroController registroController) {
        
        this.registroController = registroController;
        
        initComponents();
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        btnVolver = new javax.swing.JToggleButton();
        btnRegistrar = new javax.swing.JToggleButton();
        lblConfirmar = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        txtConfirmarPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTitulo.setText("BIENVENIDO AL REGISTRO DE TU CUENTA");

        lblNombre.setText("NOMBRE");

        lblApellido.setText("APELLIDO");

        lblTelefono.setText("TELEFONO");

        lblCorreo.setText("CORREO");

        lblUsername.setText("USERNAME");

        lblPassword.setText("CONTRASEÑA");

        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        lblConfirmar.setText("CONFIRMAR CONTRASEÑA");

        txtPassword.setText("jPass");

        txtConfirmarPassword.setText("jPass");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConfirmar)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPassword)
                                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(txtConfirmarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btnVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRegistrar)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblTitulo)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblTelefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(lblUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblConfirmar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // CONFIGURAR EL BTON DE REGISTRAR AL USUARIO
        
        char[] password = txtPassword.getPassword();
        char[] confirmarPassword = txtConfirmarPassword.getPassword();
        
        try {
            //Obtener datos del formulario
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String correo = txtCorreo.getText().trim();
            String username = txtUsername.getText().trim();
            
            //VALIDAR CAMPOS OBLIGATORIOS PARA QUE NO HAYA PROBLEMAS EN LA BASE DE DATOS
            if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || username.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "COMPLETA TODOS LOS CAMPOS OBLIGATORIOS.",
                                                                "Validacion", JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
            //VALIDAR CONTRASEÑA SEA CORRECTA
            if (password.length == 0){
                JOptionPane.showMessageDialog(this, "LA CONTRASEÑA ES OBLIGATORIA.", 
                            "VALIDACION", JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
            //confirmar que la contraseña coincidan
            if (!Arrays.equals(password, confirmarPassword)){
                JOptionPane.showMessageDialog(this, "LAS CONTRASEÑAS NO COINCIDEN.", "VALIDACION", JOptionPane.WARNING_MESSAGE);
                
                txtPassword.setText("");
                txtConfirmarPassword.setText("");
                
                return;
            }
            
            //METODO PARA ENVIAR LOS DATOS AL CONTROLADOR Y HACER EL COMMIT AL SQL
            long idCliente = registroController.registrarCliente(nombre, apellido, telefono, correo.isEmpty() ? null : correo, username, password);
            
            //CONFIRMAR EL REGISTRO
            JOptionPane.showMessageDialog(this, "¡REGISTRO EXITOSO!\n" + "TU CÓDIGO DE CLIENTE ES: " + idCliente,
                                        "REGISTRO COMPLETADO.", JOptionPane.INFORMATION_MESSAGE);
            
            //LIMPIAR TODAS LAS CELDAS PARA NUEVAMENTE REALIZAR EL REGISTRO
            txtNombre.setText("");
            txtApellido.setText("");
            txtTelefono.setText("");
            txtCorreo.setText("");
            txtUsername.setText("");
            txtPassword.setText("");
            txtConfirmarPassword.setText("");
            
            //VOLVER AL LOGIN PARA EL INICIO
            setVisible(true);
            this.dispose();
            
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "DATOS INCORRECTOS.", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e){
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo completar el registro. "
                        + "Verifica que el username no esté registrado "
                        + "y que la base de datos esté disponible.\n"
                        + "Detalle: " + e.getMessage(),
                        "Error de base de datos",
                        JOptionPane.ERROR_MESSAGE
                );
        } catch (GeneralSecurityException e){
            JOptionPane.showMessageDialog(this, "Ocurrió un problema al proteger la contraseña.", "Erro de seguridad", JOptionPane.ERROR_MESSAGE);
        } finally {
            //eliminar las contraseñas temporales que se guardan en memoria
            Arrays.fill(password, '\0');
            Arrays.fill(confirmarPassword, '\0');
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // volver al login
       int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas volver al login?", 
               "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       
       if (opcion == JOptionPane.YES_OPTION){
           setVisible(true);
           this.dispose();
       }
    }//GEN-LAST:event_btnVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnRegistrar;
    private javax.swing.JToggleButton btnVolver;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblConfirmar;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtConfirmarPassword;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
