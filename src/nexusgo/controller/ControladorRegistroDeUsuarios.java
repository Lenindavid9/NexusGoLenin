/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusgo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import nexusgo.model.Usuario;
import nexusgo.model.UsuarioDao;
import nexusgo.view.VistaInicioSesion;
import registro.VistaRegistroDeUsuario;
import nexusgo.view.VistaPrincipalCliente;
/**
 *
 * @author USUARIO
 */


public class ControladorRegistroDeUsuarios implements ActionListener {

   private final VistaRegistroDeUsuario vistaRegistro;
    private final UsuarioDao usuarioDao;

    /**
     * Constructor que enlaza la vista de registro y prepara el acceso a la base
     * de datos.
     */
    public ControladorRegistroDeUsuarios(VistaRegistroDeUsuario vistaRegistro) {
        this.vistaRegistro = vistaRegistro;
        this.usuarioDao = new UsuarioDao(); // Instancia del DAO que contiene las consultas SQL

        inicializarListeners();
    }

    /**
     * Enlaza los botones físicos de la interfaz con este controlador.
     */
    private void inicializarListeners() {
        this.vistaRegistro.btnRegistrarse.addActionListener(this);
        this.vistaRegistro.btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Usamos estructura else if limpia para evitar que hilos duplicados cancelen el evento
        if (e.getSource() == vistaRegistro.btnRegistrarse) {
            System.out.println("¡Botón Registrarse detectado correctamente!");
            ejecutarRegistro();
        } else if (e.getSource() == vistaRegistro.btnVolver) {
            System.out.println("¡Botón Volver detectado correctamente!");
            regresarAlLogin();
        }
    }

    /**
     * Captura, valida y procesa la inserción del nuevo usuario en el sistema.
     */
    private void ejecutarRegistro() {
        // 1. Capturamos los datos de la tarjeta izquierda (Datos personales)
        String nombre = vistaRegistro.tNombre.getText().trim();
        String apellido = vistaRegistro.tApellido.getText().trim();
        int indiceDocumento = vistaRegistro.miTipoDocumento.getSelectedIndex();
        String identificacion = vistaRegistro.tNroIdentificacion.getText().trim();

        // 2. Capturamos los datos de la tarjeta derecha (Credenciales)
        String correo = vistaRegistro.tCorreo.getText().trim();
        String contrasena = new String(vistaRegistro.tContrasena.getPassword()).trim();
        String confirmarContrasena = new String(vistaRegistro.tConfirmar.getPassword()).trim();

        // --- VALIDACIÓN 1: Comprobar campos obligatorios vacíos ---
        if (nombre.isEmpty() || apellido.isEmpty() || identificacion.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(vistaRegistro,
                    "Por favor, complete todos los campos obligatorios del formulario.",
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // --- VALIDACIÓN 2: Comprobar si seleccionó el placeholder del ComboBox ---
        if (indiceDocumento == 0) {
            JOptionPane.showMessageDialog(vistaRegistro,
                    "Por favor, seleccione un tipo de documento válido.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // --- VALIDACIÓN 3: Comprobar si las contraseñas coinciden ---
        if (!contrasena.equals(confirmarContrasena)) {
            JOptionPane.showMessageDialog(vistaRegistro,
                    "Las contraseñas ingresadas no coinciden. Verifíquelas nuevamente.",
                    "Error de Seguridad", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Empaquetamos los datos dentro de un objeto de tu modelo Usuario
        // Se dividen nombre y apellido de forma independiente para engranar con las columnas SQL
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setIdentificacion(identificacion);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setContrasena(contrasena);

        // 4. Enviamos el objeto al DAO
        int resultado = usuarioDao.registrar(nuevoUsuario);

        if (resultado > 0) {
            JOptionPane.showMessageDialog(vistaRegistro,
                    "¡Registro exitoso! Bienvenido a Nexus Go!",
                    "Nexus Go! Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Cerramos de inmediato la ventana de registro actual
            this.vistaRegistro.dispose();

            // Redirección Directa: Abrimos la interfaz principal del cliente amarrada a su controlador
            VistaPrincipalCliente vistaCliente = new VistaPrincipalCliente("","");//////////POR SOLUCIONAR///////////
            new ControladorPrincipalCliente(vistaCliente);
            vistaCliente.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(vistaRegistro,
                    "No se pudo completar el registro. Es posible que la identificación o el correo ya existan.",
                    "Error de Inserción", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método encargado de cerrar la vista de registro y restablecer el flujo
     * del login inicial.
     */
    private void regresarAlLogin() {
        this.vistaRegistro.dispose();

        VistaInicioSesion loginVista = new VistaInicioSesion();
        new ControladorInicioSesion(loginVista);

        loginVista.setLocationRelativeTo(null);
        loginVista.setVisible(true);
    }

}
