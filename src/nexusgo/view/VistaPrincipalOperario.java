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
import nexusgo.model.Usuario;

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
    private JPanel contenido;
    Usuario lenin = new Usuario("Lenin","operario");
    
   
   

    public VistaPrincipalOperario() {
        super("principal Operario");
        setLayout(new BorderLayout());
        
        //barra lateral
        sidebar = new VistaBarraLateral();
        
        contenido = new JPanel();
        
        //Panel de bienvenida 
        bienvenida = new PanelBienvenida(lenin.getNombre(),lenin.getRol());
        
        add(bienvenida,BorderLayout.CENTER);

        //posicion de izquierda de la barra lateral
        add(sidebar, BorderLayout.WEST);
        
        add(contenido, BorderLayout.CENTER);
        
      

        //dimensiones de la ventana 
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
     public VistaBarraLateral getsidebar(){
        return sidebar;
        }
     
      

}
