/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusgo.controller;

import nexusgo.model.Herramientas;
import nexusgo.model.HerramientaDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import nexusgo.model.Producto;
import nexusgo.model.ProductoDao;
import nexusgo.view.PanelBienvenida;
import nexusgo.view.VistaAgregarProducto;
import nexusgo.view.VistaOperarioInventario;
import nexusgo.view.VistaPrincipalOperario;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import nexusgo.view.VistaAgregarHerramienta;

/**
 *
 * @author USUARIO
 */
public class ControladorInventarioOperario implements ActionListener{
    
  // Componentes de las vistas (V)
    private final VistaPrincipalOperario vistaPrincipal;
    private VistaOperarioInventario panelInventario;
    private VistaAgregarProducto panelFormulario; 
    private VistaAgregarHerramienta panelFormularioHerramienta; 
    
    // Componentes del modelo / Acceso a datos (M) - Inicializados directamente para cumplir la regla 'final'
    private final ProductoDao productoDao = new ProductoDao();
    private final HerramientaDao herramientaDao = new HerramientaDao();
    private DefaultTableModel modeloTablaProductos;
    private DefaultTableModel modeloTablaHerramientas;

    /**
     * Constructor del controlador. Acopla las vistas y arranca los listeners.
     */
    public ControladorInventarioOperario(VistaPrincipalOperario vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        
        try {
            this.panelInventario = new VistaOperarioInventario();
            this.panelFormulario = new VistaAgregarProducto(); 
            this.panelFormularioHerramienta = new VistaAgregarHerramienta(); 

            // Enlazar escuchadores de eventos
            inicializarListeners();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error crítico al inicializar los módulos del sistema: " + e.getMessage(), 
                "Error Crítico de Arranque", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Une los botones y componentes que disparan eventos con este controlador.
     */
    private void inicializarListeners() {
        try {
            // Listeners del Sidebar (Menú lateral izquierdo de la vista principal)
            this.vistaPrincipal.getsidebar().bCasa.addActionListener(this);
            this.vistaPrincipal.getsidebar().misCitas.addActionListener(this);
            
            // Listeners de la pestaña de tablas generales (Inventario)
            this.panelInventario.btnAgregarProducto.addActionListener(this);
            this.panelInventario.btnAgregarHerramienta.addActionListener(this);
            this.panelInventario.cerrarSesion.addActionListener(this);
            
            // Listeners del formulario de adición/edición de Productos
            this.panelFormulario.btnVolver.addActionListener(this);
            this.panelFormulario.btnEditar.addActionListener(this);
            this.panelFormulario.btnImagen.addActionListener(this);
            
            // Listeners del formulario de adición/edición de Herramientas
            this.panelFormularioHerramienta.btnVolver.addActionListener(this);
            this.panelFormularioHerramienta.btnEditar.addActionListener(this); 
            this.panelFormularioHerramienta.btnImagen.addActionListener(this); 
            
        } catch (NullPointerException npe) {
            System.err.println("Error al enlazar listeners. Verifique los getters de las vistas: " + npe.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
           
            // NAVEGACIÓN GENERAL DEL SIDEBAR
          
            if (e.getSource() == vistaPrincipal.getsidebar().bCasa) {
                cambiarPanelCentral(new PanelBienvenida("Isabella", "Supervisora"));
            }

            if (e.getSource() == vistaPrincipal.getsidebar().misCitas) {
                cambiarPanelCentral(this.panelInventario);
                listarProductosEnTabla(); 
                listarHerramientasEnTabla(); 
            }

            if (e.getSource() == panelInventario.cerrarSesion) {
                ejecutarCerrarSesion();
            }

       
            // ACCIONES DEL MÓDULO PRODUCTOS
         
            if (e.getSource() == panelInventario.btnAgregarProducto) {
                limpiarCamposFormularioProducto(); 
                panelFormulario.btnEditar.setText("Guardar"); 
                cambiarPanelCentral(this.panelFormulario); 
            }

            if (e.getSource() == panelFormulario.btnImagen) {
                buscarYCopiarImagen("producto");
            }

            if (e.getSource() == panelFormulario.btnEditar) {
                if (panelFormulario.btnEditar.getText().equals("Guardar")) {
                    registrarNuevoProducto(); 
                }
            }

            if (e.getSource() == panelFormulario.btnVolver) {
                cambiarPanelCentral(this.panelInventario); 
                listarProductosEnTabla(); 
            }

          
            // ACCIONES DEL MÓDULO HERRAMIENTAS
         
            if (e.getSource() == panelInventario.btnAgregarHerramienta) {
                limpiarCamposFormularioHerramienta();
                panelFormularioHerramienta.btnEditar.setText("Guardar");
                cambiarPanelCentral(this.panelFormularioHerramienta);
            }

            if (e.getSource() == panelFormularioHerramienta.btnImagen) {
                buscarYCopiarImagen("herramienta");
            }

            if (e.getSource() == panelFormularioHerramienta.btnEditar) {
                if (panelFormularioHerramienta.btnEditar.getText().equals("Guardar")) {
                    registrarNuevaHerramienta();
                }
            }

            if (e.getSource() == panelFormularioHerramienta.btnVolver) {
                cambiarPanelCentral(this.panelInventario);
                listarHerramientasEnTabla();
            }
            
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(vistaPrincipal, 
                "Error de referencia: Un componente visual o dato requerido no se cargó correctamente.", 
                "Error de Software", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vistaPrincipal, 
                "Ocurrió un comportamiento inesperado en la interfaz: " + ex.getMessage(), 
                "Error General", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Copia la imagen seleccionada de manera limpia a la carpeta del proyecto.
     */
    private void buscarYCopiarImagen(String tipoModulo) {
        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "jpeg", "png");
        selector.setFileFilter(filtro);
        
        JPanel panelPadre = tipoModulo.equals("producto") ? panelFormulario : panelFormularioHerramienta;
        int resultado = selector.showOpenDialog(panelPadre);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                File archivoSeleccionado = selector.getSelectedFile();
                String nombreOriginal = archivoSeleccionado.getName();
                
                String prefijo = tipoModulo.equals("producto") ? "prod_" : "herr_";
                String nombreLimpio = System.currentTimeMillis() + "_" + prefijo + nombreOriginal.replaceAll("\\s+", "_");
                
                Path destino = Paths.get("src/nexusgo/img/" + nombreLimpio);
                Files.createDirectories(destino.getParent());
                Files.copy(archivoSeleccionado.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
                
                if (tipoModulo.equals("producto")) {
                    panelFormulario.lblNombreImagen.setText(nombreLimpio);
                } else {
                    panelFormularioHerramienta.lblNombreImagen.setText(nombreLimpio);
                }
                
                JOptionPane.showMessageDialog(panelPadre, "Imagen cargada y vinculada correctamente al recurso.");
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panelPadre, 
                    "Fallo en la transferencia de archivos físicos: " + ex.getMessage(), 
                    "Error de Archivo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void listarProductosEnTabla() {
        try {
            modeloTablaProductos = (DefaultTableModel) panelInventario.tablaProductos.getModel();
            modeloTablaProductos.setRowCount(0); 
            
            List<Producto> lista = productoDao.listar(); 
            if (lista == null) throw new NullPointerException("La lista de productos está inaccesible.");

            Object[] fila = new Object[5];
            for (Producto p : lista) {
                fila[0] = p.getIdProducto();
                fila[1] = p.getNombreProducto();
                fila[2] = p.getPrecioCompra();
                fila[3] = p.getStockActual();
                fila[4] = "Insumo Interno"; 
                modeloTablaProductos.addRow(fila); 
            }
        } catch (Exception e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
    }

    public void listarHerramientasEnTabla() {
        try {
            modeloTablaHerramientas = (DefaultTableModel) panelInventario.tablaHerramientas.getModel();
            modeloTablaHerramientas.setRowCount(0); 
            
            List<Herramientas> lista = herramientaDao.listar(); 
            if (lista == null) throw new NullPointerException("La tabla de herramientas no devolvió registros.");

            Object[] fila = new Object[4];
            for (Herramientas h : lista) {
                fila[0] = h.getIdHerramienta();
                fila[1] = h.getNombreHerramienta();
                fila[2] = h.getEstadoActual();
                fila[3] = "Activo"; 
                modeloTablaHerramientas.addRow(fila);
            }
        } catch (Exception e) {
            System.err.println("Error al listar herramientas: " + e.getMessage());
        }
    }

    private void registrarNuevoProducto() {
        if (panelFormulario.txtNombre.getText().trim().isEmpty() || 
            panelFormulario.txtCantidad.getText().trim().isEmpty() || 
            panelFormulario.txtPrecio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(panelFormulario, "Llene los campos obligatorios.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return; 
        }

        try {
            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombreProducto(panelFormulario.txtNombre.getText().trim());
            nuevoProducto.setDescripcion(panelFormulario.txtDescripcion.getText().trim());
            nuevoProducto.setStockActual(Integer.parseInt(panelFormulario.txtCantidad.getText().trim()));
            nuevoProducto.setStockMinimo(panelFormulario.txtStockMinimo.getText().trim().isEmpty() ? 0 : Integer.parseInt(panelFormulario.txtStockMinimo.getText().trim()));
            
            String precioLimpio = panelFormulario.txtPrecio.getText().replace("$", "").replace(".", "").trim();
            nuevoProducto.setPrecioCompra(Double.parseDouble(precioLimpio));
            nuevoProducto.setUrlImagen(panelFormulario.lblNombreImagen.getText());

            int resultado = productoDao.agregar(nuevoProducto);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(panelFormulario, "¡Producto registrado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cambiarPanelCentral(this.panelInventario); 
                listarProductosEnTabla(); 
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelFormulario, "Error al guardar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarNuevaHerramienta() {
        if (panelFormularioHerramienta.txtNombre.getText().trim().isEmpty() || 
            panelFormularioHerramienta.txtIdHerramienta.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(panelFormularioHerramienta, "Por favor, ingrese el ID y el Nombre de la herramienta.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Herramientas nuevaHerramienta = new Herramientas();
            
            // Conversión estricta de String de la vista a int del Modelo
            int idConvertido = Integer.parseInt(panelFormularioHerramienta.txtIdHerramienta.getText().trim());
            nuevaHerramienta.setIdHerramienta(idConvertido);
            
            nuevaHerramienta.setNombreHerramienta(panelFormularioHerramienta.txtNombre.getText().trim());
            nuevaHerramienta.setEstadoActual("Excelente"); 

            //  Llama al método agregar() que ya existe en el DAO
            int resultado = herramientaDao.agregar(nuevaHerramienta);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(panelFormularioHerramienta, "¡Herramienta registrada exitosamente en Sistema NEXUS!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cambiarPanelCentral(this.panelInventario);
                listarHerramientasEnTabla();
            } else {
                JOptionPane.showMessageDialog(panelFormularioHerramienta, "La base de datos rechazó el registro de la herramienta.", "Error Servidor", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(panelFormularioHerramienta, "El ID de la herramienta debe ser exclusivamente un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panelFormularioHerramienta, "Error al registrar la herramienta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void limpiarCamposFormularioProducto() {
        panelFormulario.txtNombre.setText("");
        panelFormulario.txtDescripcion.setText("");
        panelFormulario.txtCantidad.setText("");
        panelFormulario.txtPrecio.setText("");
        panelFormulario.txtStockMinimo.setText("");
        panelFormulario.lblNombreImagen.setText("ningún archivo seleccionado");
    }

    private void limpiarCamposFormularioHerramienta() {
        panelFormularioHerramienta.txtIdHerramienta.setText("");
        panelFormularioHerramienta.txtNombre.setText("");
        panelFormularioHerramienta.lblNombreImagen.setText("ningún archivo seleccionado");
    }

    private void ejecutarCerrarSesion() {
        int confirmar = JOptionPane.showConfirmDialog(vistaPrincipal, "¿Desea cerrar sesión?", "Sistema NEXUS", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) vistaPrincipal.dispose();
    }

    private void cambiarPanelCentral(JPanel panelNuevo) {
        try {
            vistaPrincipal.getContenido().removeAll(); 
            vistaPrincipal.getContenido().add(panelNuevo, java.awt.BorderLayout.CENTER); 
            vistaPrincipal.revalidate(); 
            vistaPrincipal.repaint(); 
        } catch (Exception e) {
            System.err.println("Error en navegación: " + e.getMessage());
        }
    }
}
