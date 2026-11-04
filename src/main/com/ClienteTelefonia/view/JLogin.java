
package main.com.ClienteTelefonia.view;

import java.sql.SQLException;
import java.security.GeneralSecurityException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.com.ClienteTelefonia.controller.ClienteTelefoniaController;
import main.com.ClienteTelefonia.controller.LoginController;
import main.com.ClienteTelefonia.controller.RegistroController;
import main.com.ClienteTelefonia.dao.RegistroDAO;
import main.com.ClienteTelefonia.model.Usuario;


public class JLogin extends javax.swing.JFrame {

    //ATRIBUTOS
    private final LoginController loginController;
    
    public JLogin(LoginController loginController) {
        initComponents();
        
        this.loginController = loginController;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JFormattedTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblPress = new javax.swing.JLabel();
        lblPres = new javax.swing.JLabel();
        btnCrearCuenta = new javax.swing.JButton();
        btnIniciarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BIENVENIDO ");

        lblUsuario.setText("Usuario");

        lblPassword.setText("Password");

        txtPassword.setText("jPasswo");

        lblPress.setText("No tienes una cuenta.    ");

        lblPres.setText("PRESIONA EL BOTON!");

        btnCrearCuenta.setText("CREAR UNA CUENTA");
        btnCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCuentaActionPerformed(evt);
            }
        });

        btnIniciarSesion.setText("INICIAR SESION");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblPress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(lblPres, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCrearCuenta))
                            .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPres)
                    .addComponent(btnCrearCuenta))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCuentaActionPerformed
        // BTN DE CREAR UNA CUENTA, SE CREA EL METODO DE REGISTRODAO
        RegistroDAO dao = new RegistroDAO();
        
        RegistroController controller = new RegistroController(dao);
        
        JRegistro formulario = new JRegistro(controller);
        formulario.setVisible(true);        
    }//GEN-LAST:event_btnCrearCuentaActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        // btn de inicio de sesion
        String username = txtUsuario.getText().trim();
        char[] password = txtPassword.getPassword();

        try {

            // 1. Autenticar al usuario
            Usuario usuario = loginController.autenticar(username, password
            );

            // 2. Crear el controlador del cliente
            ClienteTelefoniaController clienteController = new ClienteTelefoniaController();

            // 3. Recuperar el cliente asociado a la cuenta
            clienteController.seleccionarCliente(usuario.getIdCliente()
            );

            // 4. Abrir el menú según su rol
            MenuOpciones menu = new MenuOpciones(
                clienteController,
                usuario.getRol()
            );

            menu.setVisible(true);

            // 5. Cerrar el login
            this.dispose();

            } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Validación",
                JOptionPane.WARNING_MESSAGE
            );

            } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Error al consultar la base de datos: " + ex.getMessage(),
                "Error de base de datos",
                JOptionPane.ERROR_MESSAGE
            );

            } catch (GeneralSecurityException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Error al verificar la contraseña.",
                "Error de seguridad",
                JOptionPane.ERROR_MESSAGE
            );

            } finally {

                java.util.Arrays.fill(password, '\0');
        }
    }//GEN-LAST:event_btnIniciarSesionActionPerformed



 
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               LoginController controller = new LoginController();
               JLogin login = new JLogin(controller);
               login.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearCuenta;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPres;
    private javax.swing.JLabel lblPress;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JFormattedTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
