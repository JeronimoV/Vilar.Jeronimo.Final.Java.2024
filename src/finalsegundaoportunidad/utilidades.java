/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalsegundaoportunidad;

import clasesBase.Productos;
import enums.Especies;
import enums.Origen;
import java.util.function.BiFunction;

/**
 *
 * @author Jeronimo
 */
public class utilidades {
    public static boolean isNumber(String number){
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static BiFunction<String, Productos, Productos> buscarPorNombre = (nombre , producto) -> {
        Productos retorno = null;
        if(producto.nombreProducto.equals(nombre)){
            retorno = producto;
        }
        return retorno;
    };
    
    public static BiFunction<Integer, Productos, Productos> buscarPorId = (id , producto) -> {
        Productos retorno = null;
        if(Integer.toString(producto.id).equals(producto)){
            retorno = producto;
        }
        return retorno;
    };
    
    public static Productos buscarProducto(String busquedaParametro, BiFunction<String, Productos, Productos> busqueda){
        Productos retorno = null;
        while(SceneBuilder.gestion.hasNext()){
            Productos producto = SceneBuilder.gestion.next();
            
            if(producto.nombreProducto.equals(busquedaParametro)){
                retorno = producto;
            }
            
            busqueda.apply(busquedaParametro, producto);
        }
        
        return retorno;
    }
}
