/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

import javafx.scene.control.Alert;

/**
 *
 * @author Jeronimo
 */
public class CreacionException extends Exception{
    public CreacionException(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText("Error en la creacion del producto");
        alerta.setContentText(super.getMessage());
        alerta.showAndWait();
    }
    public CreacionException(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText("Error en la creacion del producto");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
