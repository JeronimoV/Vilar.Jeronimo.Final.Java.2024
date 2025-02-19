/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import clasesBase.Productos;

/**
 *
 * @author Jeronimo
 * @param <T>
 */
public interface CRUDmetodos<T extends Productos> {
    public void create(T producto);
    public void update(T productoNuevo);
    public void delete();
}
