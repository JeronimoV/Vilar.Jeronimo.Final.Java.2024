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
public class Queso extends Producto implements productosMetodos, Serializable {
    public TipoQueso tipoQueso;
    public Procedencia procedencia;
    
    public Queso(Marcas marca , int precio, int cantidad, int vendidos, int codigoProducto, String nombreProducto, int peso, TipoQueso tipoQueso, Procedencia procedencia) {
        super(precio, cantidad, vendidos, codigoProducto, nombreProducto, peso, marca);
        this.tipoQueso = tipoQueso;
        this.procedencia = procedencia;
    }
    
    public Queso(Marcas marca , int precio, int vendidos, int codigoProducto, String nombreProducto, int peso, TipoQueso tipoQueso, Procedencia procedencia) {
        this(marca, precio, 0, vendidos, codigoProducto, nombreProducto, peso, tipoQueso, procedencia);

    }

    public Queso(Marcas marca , int precio, int codigoProducto, String nombreProducto, int peso, TipoQueso tipoQueso, Procedencia procedencia) {
        this(marca, precio, 0, 0, codigoProducto, nombreProducto, peso, tipoQueso, procedencia);
    }
    
    
    @Override
    public void calcularGanancias(){
        int precioDeCompra = (int) (this.precio * 0.25);
        int ganancia = (this.precio - precioDeCompra) * this.vendidos;
        
        System.out.println("La ganancia neta fue de: " + ganancia);
    }
   
}
