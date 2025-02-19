/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesBase;

import enums.Origen;
import enums.Especies;
import java.io.Serializable;

/**
 *
 * @author Jeronimo
 */
public abstract class Productos implements Comparable<Productos>, Serializable {
    public int id;
    public Origen origen;
    public int precio;
    public String nombreProducto;
    public Especies especieAnimal;
    public int unidadesVendidas;
    
    

    public Productos(int id, Origen origen, int precio, String nombreProducto, Especies especieAnimal, int unidadesVendidas) {
        this.id = id;
        this.origen = origen;
        this.precio = precio;
        this.nombreProducto = nombreProducto;
        this.especieAnimal = especieAnimal;
        this.unidadesVendidas = unidadesVendidas;
    }
    
    public void setPrecio(int nuevoPrecio){
        this.precio = nuevoPrecio;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }
    
    @Override
    public int compareTo(Productos obj){
        return Integer.compare(obj.id, this.id);
    }
    
    @Override
    public String toString(){
        return this.id + "," + this.origen + "," + this.precio + "," + this.nombreProducto + "," + this.especieAnimal + "," +this.unidadesVendidas;
    }
    
    
}
