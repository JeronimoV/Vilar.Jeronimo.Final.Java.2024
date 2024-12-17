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
public class Palmito extends Producto implements productosMetodos, Serializable{
   public Procedencia pais;
   public boolean enlatado;
    
     public Palmito(Marcas marca , int precio, int cantidad, int vendidos, int codigoProducto, String nombreProducto, int peso, Procedencia pais, Boolean enlatado) {
        super(precio, cantidad, vendidos, codigoProducto, nombreProducto, peso, marca);
        this.pais = pais;
        this.enlatado = enlatado;
        
    }
    
    public Palmito(Marcas marca , int precio, int vendidos, int codigoProducto, String nombreProducto, int peso, Procedencia pais, Boolean enlatado) {
        this(marca, precio, 0, vendidos, codigoProducto, nombreProducto, peso, pais, enlatado);

    }

    public Palmito(Marcas marca , int precio, int codigoProducto, String nombreProducto, int peso, Procedencia pais, Boolean enlatado) {
        this(marca, precio, 0, 0, codigoProducto, nombreProducto, peso, pais, enlatado);
    }

    @Override
    public void calcularGanancias(){
        int precioDeCompra = (int) (this.precio * 0.25);
        int ganancia = (this.precio - precioDeCompra) * this.vendidos;
        
        System.out.println("La ganancia neta fue de: " + ganancia);
    }
   
}
