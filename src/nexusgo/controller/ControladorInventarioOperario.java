/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusgo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import nexusgo.view.VistaOperarioInventario;
import nexusgo.view.VistaPrincipalOperario;

/**
 *
 * @author USUARIO
 */
public class ControladorInventarioOperario implements ActionListener{
    
     private VistaPrincipalOperario vista;
     
     public ControladorInventarioOperario(VistaPrincipalOperario vista){
        this.vista=vista;
       
        this.vista.getsidebar().bCasa.addActionListener(this);
        this.vista.getsidebar().misCitas.addActionListener(this);
     
     }
     
      @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.getsidebar().bCasa) {

            abrirCasa();

        }
    }

    public void abrirCasa() {

        VistaPrincipalOperario principal =
                new VistaPrincipalOperario();

        principal.setVisible(true);
        //Cierra y guarda la variable vista
        principal.dispose();
    }
     
    

    
    
}
