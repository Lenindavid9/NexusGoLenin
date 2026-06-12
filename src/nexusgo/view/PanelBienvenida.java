/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusgo.view;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO
 */
public class PanelBienvenida extends JPanel{
    
    private JLabel titulo;
    private JLabel mensaje;
    private String rol;
    
    
   public PanelBienvenida(){
        setLayout(null);
        setBackground(Color.WHITE);

        titulo = new JLabel(
                "Hola, " + rol + " Bienvenid@ a Nexus GO"
        );

        titulo.setBounds(180, 40, 350, 30);

        mensaje = new JLabel("Espero que te encuentres super bien");
               

        mensaje.setBounds(150, 100, 350, 80);

        add(titulo);
        add(mensaje);
    }
    
    
    
    
    
    
    
    
}
