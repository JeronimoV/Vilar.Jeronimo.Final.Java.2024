/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

import java.util.Comparator;

/**
 *
 * @author Jeronimo
 */
public class ProductoOrdenamientoCantidad<T extends Producto> implements Comparator<T>{
    @Override
    public int compare(T obj1, T obj2){
        return Integer.compare(obj1.getCantidad(), obj2.getCantidad());
    }
}
