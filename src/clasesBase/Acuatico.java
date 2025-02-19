/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesBase;

import enums.Origen;
import enums.TiposCaptura;
import enums.Especies;

/**
 *
 * @author Jeronimo
 */
public class Acuatico extends Productos implements Comparable<Productos>{
    public TiposCaptura tipoCaptura;
    public boolean fresco;

    public Acuatico(int id, Origen origen, int precio, String nombreProducto, Especies especieAnimal, int unidadesVendidas, TiposCaptura tipoCaptura, Boolean fresco) {
        super(id, origen, precio, nombreProducto, especieAnimal, unidadesVendidas);
        this.tipoCaptura = tipoCaptura;
        this.fresco = fresco;
    }
    
    public Acuatico(int id, Origen origen, int precio, String nombreProducto, int unidadesVendidas, TiposCaptura tipoCaptura ,Boolean fresco) {
        this( id,  origen,  precio,  nombreProducto,  Especies.DESCONOCIDO,  unidadesVendidas, tipoCaptura,  fresco);
    }
    
    public Acuatico(int id, Origen origen, int precio, String nombreProducto, int unidadesVendidas, TiposCaptura tipoCaptura) {
        this( id,  origen,  precio,  nombreProducto,  Especies.DESCONOCIDO, unidadesVendidas, tipoCaptura ,true);
    }
    
    @Override
    public String toString(){
        String texto = super.toString();
        texto = texto + "," + this.tipoCaptura + "," + this.fresco+ "," + "Acuatico";
        return texto;
    }
    
    
}
