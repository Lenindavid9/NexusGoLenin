/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nexusgo;

import javax.swing.JFrame;
import nexusgo.controller.ControladorInventarioOperario;
import nexusgo.controller.ControladorPrincipalOperario;
import nexusgo.model.Conexion;
import nexusgo.view.PanelBienvenida;
import nexusgo.view.VistaBarraLateral;
import nexusgo.view.VistaOperarioInventario;
import nexusgo.view.VistaPrincipalOperario;

/**
 *
 * @author USUARIO
 */
public class NexusGo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    // Instanciamos la ventana marco que contiene el Sidebar y el fondo
        VistaPrincipalOperario view = new VistaPrincipalOperario();
        
        // Inicializamos el controlador pasándole la vista para activar las tablas y botones
        ControladorInventarioOperario controller = new ControladorInventarioOperario(view);
        
        // --- CONFIGURACIÓN DE LA VENTANA DESDE EL MAIN ---
        view.setVisible(true);
        
        // Ajustamos el tamaño óptimo para que tu tabla blanca de productos se estire bien
        view.setSize(1100, 680); 
        
        // Si quieres que aparezca en una posición fija usas setLocation, 
        // o si prefieres que se centre automáticamente en cualquier pantalla usas null:
        view.setLocationRelativeTo(null); 
        
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
