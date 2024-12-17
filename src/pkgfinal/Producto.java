/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Jeronimo
 */
public abstract class Producto implements Comparable<Producto>, Serializable{
    public int precio;
    public int cantidad;
    public int vendidos;
    public int codigoProducto;
    public String nombreProducto;
    public int peso;
    public Marcas marca;

    public Producto(int precio, int cantidad, int vendidos, int codigoProducto, String nombreProducto, int peso, Marcas marca) {
        this.precio = precio;
        this.cantidad = cantidad;
        this.vendidos = vendidos;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.peso = peso;
        this.marca = marca;
    }
    
    

    public int getPrecio() {
        return this.precio;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setPrecio(int precio) { //Este modifica un precio en especifico, el de Gestion es para aumentar porcentualmente todos los precios
        try {
            if (precio > 0) {
            this.precio = precio;
            }else{
                throw new numeroMenorA0Exception();
            }
        } catch (numeroMenorA0Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    

    
    public void vender(int cantidad){
        int precioCompra = cantidad * this.precio;
        this.cantidad =- cantidad;
        this.vendidos =+ cantidad;
       
        System.out.println("Vendiste " + cantidad + " a " + precioCompra + "Pesos");
    }
    
    public void restockear(int entrante){
        this.cantidad =+ entrante;
    }
    
    @Override
    public int compareTo(Producto obj){
        return Integer.compare( this.codigoProducto, obj.codigoProducto);
    }
    
    
    
}
