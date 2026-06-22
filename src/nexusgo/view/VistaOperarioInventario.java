/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusgo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import nexusgo.model.GestorPermisos;
import nexusgo.model.PermisosInventario;
import nexusgo.model.Usuario;

/**
 *
 * @author USUARIO
 */
public class VistaOperarioInventario extends JPanel {

    public JTextField buscador;
    public JButton btnAgregarProducto, cerrarSesion, btnAgregarHerramienta;
    public JTable tablaProductos;
    public JTable tablaHerramientas;
    public JTabbedPane tabs;

    private JPanel panelSuperior, panelProductos, panelHerramientas;

    public VistaOperarioInventario() {
        // Al ser un JPanel, usamos BorderLayout para que ocupe todo el espacio asignado
        setLayout(new BorderLayout());

        // --- PANEL SUPERIOR (Buscador y Cerrar Sesión) ---
        panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buscador = new JTextField(25);
        cerrarSesion = new JButton("Cerrar Sesión");

        panelSuperior.add(buscador);
        panelSuperior.add(cerrarSesion);
        add(panelSuperior, BorderLayout.NORTH);

        // --- PESTAÑAS (TABS) ---
        tabs = new JTabbedPane();
        panelProductos = new JPanel(new BorderLayout());
        panelHerramientas = new JPanel(new BorderLayout());

        tabs.addTab("Productos", panelProductos);
        tabs.addTab("Herramientas", panelHerramientas);
        add(tabs, BorderLayout.CENTER);

        // --- SECCIÓN: PRODUCTOS ---
        btnAgregarProducto = new JButton("+ Agregar Producto");
        
        // Estructura de columnas igual a tu diseño
        String columnasProductos[] = {"Numero de referencia", "Nombre", "Precio", "Cantidad", "Proveedor"};
        DefaultTableModel modeloProductos = new DefaultTableModel(columnasProductos, 0);
        tablaProductos = new JTable(modeloProductos);

        panelProductos.add(btnAgregarProducto, BorderLayout.NORTH);
        panelProductos.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        // --- SECCIÓN: HERRAMIENTAS ---
        btnAgregarHerramienta = new JButton("+ Agregar Herramienta");
        
        String columnasHerramientas[] = {"Código", "Nombre", "Estado", "Cantidad"};
        DefaultTableModel modeloHerramientas = new DefaultTableModel(columnasHerramientas, 0);
        tablaHerramientas = new JTable(modeloHerramientas);

        panelHerramientas.add(btnAgregarHerramienta, BorderLayout.NORTH);
        panelHerramientas.add(new JScrollPane(tablaHerramientas), BorderLayout.CENTER);
    }
    
}


