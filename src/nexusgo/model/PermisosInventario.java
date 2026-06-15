/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusgo.model;

/**
 *
 * @author USUARIO
 */
public class PermisosInventario {
    
    private boolean ver;
    private boolean agregar;
    private boolean editar;
    private boolean salida;

    public PermisosInventario(boolean ver, boolean agregar, boolean editar, boolean salida) {
        this.ver = ver;
        this.agregar = agregar;
        this.editar = editar;
        this.salida = salida;
    }
   
    
    
}
