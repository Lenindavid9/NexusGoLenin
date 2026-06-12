/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusgo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO
 */
public class VistaPrincipalOperario extends JFrame {

    private VistaBarraLateral sidebar;
    private FlowLayout miflow;
    private JLabel titulo, mensaje;
    private JButton breporte, barranque;
    private PanelBienvenida bienvenida;
   

    public VistaPrincipalOperario() {
        //titulo
        super("Inventario");
        //Mensaje de bienvenida
        titulo = new JLabel("Hola, Operari@ Bienvenido a Nexus GO");
        titulo.setBounds(120, 30, 400, 30);
        setLayout(new BorderLayout());

        //barra lateral
        sidebar = new VistaBarraLateral();
        breporte = new JButton("reportes");
        sidebar.add(breporte);
        
        //Panel de bienvenida 
        bienvenida = new PanelBienvenida();
        
        add(bienvenida,BorderLayout.CENTER);

        //posicion de izquierda de la barra lateral
        add(sidebar, BorderLayout.WEST);
        
        
         

       

     

        //dimensiones de la ventana 
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
