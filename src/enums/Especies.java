/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author Jeronimo
 */
public enum Especies {
    DESCONOCIDO(TiposCarne.DESCONOCIDA),
    VACA(TiposCarne.ROJA),
    CERDO(TiposCarne.BLANCA),
    POLLO(TiposCarne.BLANCA),
    PESCADO(TiposCarne.BLANCA),
    MARISCOS(TiposCarne.BLANCA),
    CORDERO(TiposCarne.ROJA);
    
    private final TiposCarne productos;
    
    private Especies(TiposCarne productos){
        this.productos = productos;
    }
    
    public String getCarne(){
        return productos.name();
    }
}
