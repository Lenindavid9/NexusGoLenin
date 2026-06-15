/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nexusgo.model;

/**
 *
 * @author USUARIO
 */
public class GestorPermisos {
    
    public static PermisosInventario obtenerPermisos(String rol){
    
    if(rol.equalsIgnoreCase("Operario")){
    return new PermisosInventario(true,true,false,true);
    
    
    }
    
    if(rol.equalsIgnoreCase("Peluquero")){
     return new PermisosInventario(true,false,false,true);
    }
    
    }
    
}
