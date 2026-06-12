/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusgo.view;



import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO
 */
public class VistaBarraLateral extends JPanel {

    public Container contenedor;
    public JPanel barraLateral,barraSuperior;
    public JButton bCasa,BInventario,misCitas;
    
    public VistaBarraLateral() {
        
        setLayout(new GridLayout(7, 1, 10, 10));

        bCasa = new JButton("Inicio");
        BInventario = new JButton("Inventario");
        misCitas = new JButton("Ventas");
        

        add(bCasa);
        add(BInventario);
        add(misCitas);
     
        
        

        
}
    
    
    
    
}