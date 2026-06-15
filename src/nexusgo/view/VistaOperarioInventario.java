/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusgo.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import nexusgo.model.GestorPermisos;
import nexusgo.model.PermisosInventario;
import nexusgo.model.Usuario;

/**
 *
 * @author USUARIO
 */
public class VistaOperarioInventario extends JFrame {
    
     private VistaBarraLateral sidebar;
     private JButton btnPrueba;
     PermisosInventario permisos =GestorPermisos.obtenerPermisos(usuario.getUsari);
     Usuario usuario = new Usuario("Lenin","operario");
     
     
     public VistaOperarioInventario(){
         
         super("Inventario");
         //barra lateral
     
        sidebar = new VistaBarraLateral();
        
        btnPrueba = new JButton("boton de prueba");
        sidebar.add(btnPrueba);
        
          add(sidebar, BorderLayout.WEST);
        
       
        
            
        
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
     
     
     
     }
    
}
