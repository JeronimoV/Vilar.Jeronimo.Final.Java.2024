/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author Jeronimo
 */
public enum Cortes {
    SINCORTE(TiposCarne.DESCONOCIDA),
    BIFE_DE_CHORIZO(TiposCarne.ROJA),
    RIB_EYE(TiposCarne.ROJA),
    SOLOMILLO(TiposCarne.ROJA),
    T_BONE(TiposCarne.ROJA),
    ASADO_DE_TIRA(TiposCarne.ROJA),
    PECHUGA_DE_POLLO(TiposCarne.BLANCA),
    FILETE_DE_PESCADO(TiposCarne.BLANCA),
    LOMO_DE_CERDO(TiposCarne.BLANCA),
    COSTILLAR_DE_CERDO(TiposCarne.BLANCA),
    CORDERO(TiposCarne.BLANCA);
    
    private final TiposCarne tipo;
    
    private Cortes(TiposCarne tipo){
        this.tipo = tipo;
    }
    
    public String getTipoCarne(){
        return tipo.name();
    }
}
