/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

/**
 *
 * @author Jeronimo
 */
public interface CRUDoperations<T extends Producto> {
    public void create(T producto);
    public void edit(T nuevoProducto);
    
}
