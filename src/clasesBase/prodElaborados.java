/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesBase;

import enums.Origen;
import enums.Especies;
import enums.Ingredientes;
import java.util.ArrayList;

/**
 *
 * @author Jeronimo
 */
public class prodElaborados extends Productos implements Comparable<Productos>{
    public ArrayList<Ingredientes> ingredientes;
    public int añoVencimiento;

    public prodElaborados(int id, Origen origen, int precio, String nombreProducto, Especies especieAnimal, int unidadesVendidas, ArrayList<Ingredientes> ingredientes, int añoVencimiento) {
        super(id, origen, precio, nombreProducto, especieAnimal, unidadesVendidas);
        this.ingredientes = ingredientes;
        this.añoVencimiento = añoVencimiento;
    }
    
    public prodElaborados(int id, Origen origen, int precio, String nombreProducto, int unidadesVendidas, ArrayList<Ingredientes> ingredientes, int añoVencimiento) {
        this( id,  origen,  precio,  nombreProducto, Especies.DESCONOCIDO,  unidadesVendidas, ingredientes,  añoVencimiento);
    }
    
    public prodElaborados(int id, Origen origen, int precio, String nombreProducto, int unidadesVendidas, int añoVencimiento) {
        this(id,  origen,  precio,  nombreProducto, unidadesVendidas, new ArrayList<Ingredientes>(),  añoVencimiento);
    }
    
    @Override
    public String toString(){
        String texto = super.toString();
        texto = texto + "," + this.ingredientes + "," + this.añoVencimiento + "," + "Elaborados";
        return texto;
    }
    
}
