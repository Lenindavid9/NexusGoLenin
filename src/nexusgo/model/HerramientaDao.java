/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusgo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
    public class HerramientaDao {

    private final Conexion conexion = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Inserta una nueva herramienta en MySQL.
     */
    public int agregar(Herramientas her) {
        String sql = "INSERT INTO herramientas (id_herramienta, nombre_herramienta, estado_actual) VALUES (?, ?, ?)";

        try {
            con = conexion.getConection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, her.getIdHerramienta());
            ps.setString(2, her.getNombreHerramienta());
            ps.setString(3, her.getEstadoActual());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error SQL al intentar registrar la herramienta: " + e.getMessage());
            return 0;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexiones: " + e.getMessage());
            }
        }
    }

    /**
     * Obtiene todos los registros para pintarlos en el JTable del panel de
     * inventario.
     */
    public List<Herramientas> listar() {
        List<Herramientas> lista = new ArrayList<>();
        String sql = "SELECT * FROM herramientas";

        try {
            con = conexion.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Herramientas her = new Herramientas();
                her.setIdHerramienta(rs.getInt("id_herramienta"));
                her.setNombreHerramienta(rs.getString("nombre_herramienta"));
                her.setEstadoActual(rs.getString("estado_actual"));
                lista.add(her);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar herramientas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar flujos: " + e.getMessage());
            }
        }
        return lista;
    }
    
    public boolean registrarSalidaStock(int idProducto, int cantidad) {
    //  Ajusta los nombres de las columnas y tabla según tu base de datos
    String sql = "UPDATE productos SET stock_actual = stock_actual - ? WHERE id_producto = ?";
    
    try (Connection con = getConnetion(); // Usando tu método de conexión habitual
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, cantidad);
        ps.setInt(2, idProducto);
        
        // Si las filas afectadas son mayores a 0, la operación fue un éxito
        return ps.executeUpdate() > 0;
        
    } catch (SQLException e) {
        System.err.println("Error SQL al registrar salida de stock: " + e.getMessage());
        return false;
    }
}
    
    

}


