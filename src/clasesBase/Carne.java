/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesBase;

import enums.Origen;
import enums.TiposCarne;
import enums.Especies;
import enums.Cortes;

/**
 *
 * @author Jeronimo
 */
public class Carne extends Productos implements Comparable<Productos>{
    public Cortes tipoCorte;
    public TiposCarne tipoCarne;

    public Carne(int id, Origen origen, int precio, String nombreProducto, Especies especieAnimal, int unidadesVendidas, Cortes tipoCorte, TiposCarne tipoCarne) {
        super(id, origen, precio, nombreProducto, especieAnimal, unidadesVendidas);
        this.tipoCorte = tipoCorte;
        this.tipoCarne = tipoCarne;
    }
    
    public Carne(int id, Origen origen, int precio, String nombreProducto, int unidadesVendidas, Cortes tipoCorte, TiposCarne tipoCarne) {
        this( id,  origen,  precio,  nombreProducto, Especies.DESCONOCIDO,  unidadesVendidas,  tipoCorte,  tipoCarne);
    }
    
    public Carne(int id, Origen origen, int precio, String nombreProducto, Especies especieAnimal, int unidadesVendidas, TiposCarne tipoCarne) {
        this( id,  origen,  precio,  nombreProducto, especieAnimal,  unidadesVendidas,  Cortes.SINCORTE,  tipoCarne);
    }
    
    public Carne(int id, Origen origen, int precio, String nombreProducto, int unidadesVendidas, TiposCarne tipoCarne) {
        this( id,  origen,  precio,  nombreProducto,  unidadesVendidas, Cortes.SINCORTE, tipoCarne);
    }
    
    @Override
    public String toString(){
        String texto = super.toString();
        texto = texto + "," + this.tipoCorte + "," + this.tipoCarne + "," + "Carne";
        return texto;
    }
    
}
