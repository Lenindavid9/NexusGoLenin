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

  public VistaBarraLateral sidebar;
    public FlowLayout miflow;
    public JLabel titulo, mensaje;
    public JButton breporte, barranque;
    public PanelBienvenida bienvenida;
    
    // Este panel será el contenedor dinámico donde se meterán los módulos
    private JPanel contenido; 
    
    // Usuario de prueba para la sesión actual
    Usuario lenin = new Usuario("Isabella", "Supervisora");

    public VistaPrincipalOperario() {
        super("Sistema NexusGO - Panel de Operario");
        setLayout(new BorderLayout());
        
        // 1. Inicializar y posicionar la barra lateral a la izquierda (WEST)
        sidebar = new VistaBarraLateral();
        add(sidebar, BorderLayout.WEST);
        
        // 2. Inicializar el panel 'contenido' explícitamente con BorderLayout.
        contenido = new JPanel(new BorderLayout());
        
        // 3. Inicializar el Panel de Bienvenida inicial
        bienvenida = new PanelBienvenida(lenin.getNombre(), lenin.getRol());
        
        // 4. Meter la bienvenida DENTRO del panel de contenido
        contenido.add(bienvenida, BorderLayout.CENTER);

        // 5. Agregar el contenedor general 'contenido' al centro del JFrame
        add(contenido, BorderLayout.CENTER);

        // Dimensiones de la ventana optimizadas para que quepan bien las tablas de inventario
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Getter público para que el controlador pueda escuchar los botones del menú lateral.
     */
    public VistaBarraLateral getsidebar(){
        return sidebar;
    }
    
    /**
     * Getter público para el panel de contenido central dinámico.
     */
    public JPanel getContenido() {
        return contenido;
    }
      

}
