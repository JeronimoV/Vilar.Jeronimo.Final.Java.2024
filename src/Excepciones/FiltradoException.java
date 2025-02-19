/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Excepciones;

import javafx.scene.control.Alert;

/**
 *
 * @author Jeronimo
 */
public class FiltradoException extends Exception{


    public FiltradoException() {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText("Error al filtrar");
        alerta.setContentText(super.getMessage());
        alerta.showAndWait();
    }
    
    public FiltradoException(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText("Error al filtrar");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
