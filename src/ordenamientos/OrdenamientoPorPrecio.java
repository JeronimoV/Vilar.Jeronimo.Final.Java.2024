/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordenamientos;

import clasesBase.Productos;
import java.util.Comparator;

/**
 *
 * @author Jeronimo
 */
public class OrdenamientoPorPrecio<T extends Productos> implements Comparator<T> {
    @Override
    public int compare(T obj1, T obj2){
        return Integer.compare(obj1.precio, obj2.precio);
    }
}
