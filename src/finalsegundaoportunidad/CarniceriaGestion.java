/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalsegundaoportunidad;

import clasesBase.Carne;
import clasesBase.Productos;
import enums.Cortes;
import enums.Origen;
import enums.TiposCarne;
import interfaces.CRUDmetodos;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jeronimo
 * @param <T>
 */
public class CarniceriaGestion<T extends Productos> implements CRUDmetodos<T>, Iterator<T>{
    public ArrayList<T> listaProductos;
    public int index = 0;
    

    public CarniceriaGestion() {
        this.listaProductos = new ArrayList<>();
        Carne carne1 = new Carne(0, Origen.ARGENTINA, 100, "Bife Chorizo", 0, Cortes.BIFE_DE_CHORIZO , TiposCarne.ROJA);
        listaProductos.add((T) carne1);
        Carne carne2 = new Carne(1, Origen.BRASIL, 200, "Tira de asado", 0, Cortes.ASADO_DE_TIRA , TiposCarne.ROJA);
        listaProductos.add((T) carne2);
        Carne carne3 = new Carne(2, Origen.BOLIVIA, 300, "Costillar Cerdo", 0, Cortes.COSTILLAR_DE_CERDO , TiposCarne.BLANCA);
        listaProductos.add((T) carne3);
    }
    
    
    @Override
    public void create(T producto){
        listaProductos.add(producto);
    }
    
    @Override
    public void update(T productoNuevo){
        listaProductos.set(index - 1, productoNuevo);
        
    }
    
    @Override
    public boolean hasNext(){ //Utilizado como read tambien
        if(listaProductos.size() <= index){
            index = 0;
            return false;
        }else{
            return true;
        }
    }
    
    @Override
    public T next(){
        T data = listaProductos.get(index);
        index++;
        return data;
    }
    
    @Override
    public void delete(){
        listaProductos.remove(index - 1);
    }
    
}
