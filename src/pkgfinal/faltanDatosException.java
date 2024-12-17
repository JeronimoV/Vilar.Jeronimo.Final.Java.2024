/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package pkgfinal;

/**
 *
 * @author Jeronimo
 */
public class faltanDatosException extends Exception {

    public faltanDatosException() {
        super("Faltan datos o el tipo de dato es erroneo, reintentar");
    }
}
