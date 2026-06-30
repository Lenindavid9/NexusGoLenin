/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nexusgo.model;

import java.util.List;

/**
 *
 * @author USUARIO
 */
public interface Crud<T> {
    public List<T> listar();
    public int agregar(T objeto);
    public int editar(T t);
    public int eliminar(int id);
    
}
