/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

import java.io.Serializable;

/**
 *
 * @author Jeronimo
 */
public class Leche extends Producto implements productosMetodos, Serializable {
    public String porcentajeGrasa;
    public TiposLeche tipo;
    
    public Leche(Marcas marca , int precio, int cantidad, int vendidos, String porcentajeGrasa, int codigoProducto, String nombreProducto, int peso, TiposLeche tipo) {
        super(precio, cantidad, vendidos, codigoProducto, nombreProducto, peso, marca);
        this.tipo = tipo;
        this.porcentajeGrasa = porcentajeGrasa;
        
    }
    
    
    
    public Leche(Marcas marca , int precio, int vendidos, String porcentajeGrasa, int codigoProducto, String nombreProducto, int peso, TiposLeche tipo) {
        this(marca, precio, 0, vendidos, porcentajeGrasa, codigoProducto, nombreProducto, peso, tipo);

    }

    public Leche(Marcas marca , int precio, String porcentajeGrasa, int codigoProducto, String nombreProducto, int peso, TiposLeche tipo) {
        this(marca, precio, 0, 0, porcentajeGrasa, codigoProducto, nombreProducto, peso, tipo);
    }
    
    @Override
    public void calcularGanancias(){
        int precioDeCompra = (int) (this.precio * 0.25);
        int ganancia = (this.precio - precioDeCompra) * this.vendidos;
        
        System.out.println("La ganancia neta fue de: " + ganancia);
    }
    
   
}
