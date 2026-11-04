
package main.com.ClienteTelefonia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import main.com.ClienteTelefonia.controller.ClienteTelefoniaController;


public class ReporteModerno extends JFrame {
    
    //atributos privados
    private static final Color FONDO = new Color(15, 23, 42);
    private static final Color TARJETA = new Color(30, 41, 59);
    private static final Color BLANCO = new Color(248, 250, 252);
    private static final Color GRIS = new Color(148, 163, 184);
    
    
    //controllador
    private ClienteTelefoniaController controller;
    
    //constructor
    public ReporteModerno(ClienteTelefoniaController controller){
        this.controller = controller;
        
        setTitle("Reporte del Cliente");
        setSize(900, 600);
        setMinimumSize(new Dimension(760, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        construirInterfaz();
    }

    //metodo nuevo: constuir la interfaz
    private void construirInterfaz(){
        JPanel principal = new JPanel(new BorderLayout(20, 25));
        principal.setBackground(FONDO);
        principal.setBorder(new EmptyBorder(30, 35, 30, 35));
        setContentPane(principal);
        
        //ENCABEZADO
        JPanel encabezado = new JPanel();
        encabezado.setOpaque(false);
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        
        JLabel titulo = new JLabel("REPORTE DEL CLIENTE");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(BLANCO);
        
        JLabel subTitulo = new JLabel("RESUMEN DE ACTIVIDAD Y MOVIMIENTOS");
        subTitulo.setFont(new Font("SEGEO UI", Font.PLAIN, 15));
        subTitulo.setForeground(GRIS);
        
        encabezado.add(titulo);
        encabezado.add(Box.createVerticalStrut(8));
        encabezado.add(subTitulo);
        
        
        principal.add(encabezado, BorderLayout.NORTH);
        
        //OBTENER DATOS REALES DEL CONTROLADOR
        String nombre = controller.obtenerNombre();
        Double saldo = controller.obtenerSaldoActual();
        
        int recargaTarjeta = controller.obtenerRecargasTarjeta();
        int recargaEfectivo = controller.obtenerRecargasEfectivo();
        
        //AJUSTAR EL METODO Y DIRIGIRLO AL CONTROLADOR
        int consumos = controller.obtenerTotalConsumo();
        
        //CONTENEDOR CENTRAL
        JPanel centro = new JPanel(new BorderLayout(20, 20));
        centro.setOpaque(false);
        
        JPanel informacion = new JPanel(new GridLayout(1, 2, 20, 0));
        informacion.setOpaque(false);
        
        informacion.add(crearTarjeta(
                    "CLIENTE", nombre,
                    new Color(96, 165, 250)
        ));
        
        informacion.add(crearTarjeta(
                        "SALDO ACTUAL", String.format("S/ %.2f", saldo), 
                new Color(74, 222, 128)
        ));
        
        centro.add(informacion, BorderLayout.NORTH);
        
        //indicadores
        JPanel indicadores = new JPanel(new GridLayout(1, 3, 18, 0));
        indicadores.setOpaque(false);
        
        indicadores.add(crearTarjeta(
                        "RECARGAS CON TARJETA",
                        String.valueOf(recargaTarjeta),
                        new Color(192, 132, 252)
        ));
        
        indicadores.add(crearTarjeta(
                        "RECARGAS EN EFECTIVO",
                        String.valueOf(recargaEfectivo),
                        new Color(251, 191, 36)
        ));
        
        indicadores.add(crearTarjeta(
                        "TOTAL DE CONSUMOS",
                        String.valueOf(consumos),
                        new Color(248, 113, 113)
        ));
        
        centro.add(indicadores, BorderLayout.CENTER);
        
        //DESCRIPCION
        JLabel descripcion = new JLabel("INFORMACIÓN DE MOVIMIENTOS DEL CLIENTE", SwingConstants.CENTER);
        descripcion.setForeground(GRIS);
        descripcion.setFont(new Font(
                        "SEGOE UI", Font.ITALIC, 13));
        
        centro.add(descripcion, BorderLayout.SOUTH);
        principal.add(centro, BorderLayout.CENTER);
        
        //BOTON PARA VOLVER
        JPanel inferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        inferior.setOpaque(false);
        
        JButton volver = new JButton("VOLVER");
        volver.setFont(new Font("SEGOE UI", Font.BOLD, 14));
        volver.setForeground(Color.WHITE);
        volver.setBackground(new Color(37, 99, 235));
        volver.setFocusPainted(false);
        volver.setBorder(new EmptyBorder(12, 30, 12, 30));
        volver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        volver.addActionListener(e -> dispose());
        
        inferior.add(volver);
        principal.add(inferior, BorderLayout.SOUTH);
    }
    
    //METODO NUEVO: CREAR LA TARJETA PARA EL EFECTO
    private JPanel crearTarjeta(String titulo, String valor, Color acento){
        JPanel tarjeta = new JPanel(new BorderLayout()) {

               @Override
               protected void paintComponent(Graphics g) {
                   super.paintComponent(g);
                   
                   Graphics2D g2 = (Graphics2D) g.create();
                   
                   g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                       RenderingHints.VALUE_ANTIALIAS_ON);
                   
                   //SOMBRA
                    g2.fill(new RoundRectangle2D.Double(6, 8,
                            getWidth() - 10,
                            getHeight() - 12, 25, 25));
                    
                    //FONDO
                    g2.setColor(TARJETA);
                    g2.fill(new RoundRectangle2D.Double(0,0,                                                 
                            getWidth() - 10,
                            getHeight() - 16, 25, 25)); 
                    
                    g2.dispose();
            }     
        };
        tarjeta.setOpaque(false);
        tarjeta.setBorder(new EmptyBorder(25, 22, 32, 30));
        
        //constructor sin parametros
        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        
        //en las letras de Label del reporte
        JLabel etiqueta = new JLabel(titulo);
        etiqueta.setForeground(GRIS);
        etiqueta.setFont(new Font("SEGOUE IU", Font.BOLD, 12));
        
        JLabel numero = new JLabel(valor);
        numero.setForeground(acento);
        numero.setFont(new Font("SEGOE UI", Font.BOLD, 27));
        
        contenido.add(etiqueta);
        contenido.add(Box.createVerticalStrut(HEIGHT));
        contenido.add(numero);
        
        tarjeta.add(contenido, BorderLayout.CENTER);
        
        return tarjeta;
    }
}

