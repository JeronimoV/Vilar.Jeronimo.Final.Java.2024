/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

import java.awt.Panel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Jeronimo
 */
public class scene2Controllers implements Initializable {
    static Producto seleccionado;
    @FXML
    private Pane panel;
    @FXML
    private TextField nombre;
    @FXML
    private TextField codigo;
    @FXML
    private TextField precio;
    @FXML
    private TextField cantidad;
    @FXML
    private TextField opcional1;
    @FXML
    private TextField opcional2;
    @FXML
    private Label titulo;
    
    
    
    public void completar(ActionEvent event){
        String nuevoNombre = this.nombre.getText();
        String nuevoCodigo = this.codigo.getText();
        String nuevoPrecio = this.precio.getText();
        String nuevaCantidad = this.cantidad.getText();
        String nuevoOpcional = this.opcional1.getText();
        String nuevoOpcional2 = this.opcional2.getText();
        
        try {
            if(nuevoNombre.length() > 0 && nuevoCodigo.length() > 0 && nuevoPrecio.length() > 0 && nuevaCantidad.length() > 0 && nuevoOpcional.length() > 0 && nuevoOpcional2.length() > 0 ){
                if (seleccionado instanceof Producto) {
                    ((Producto)seleccionado).setNombreProducto(nuevoNombre);
                    ((Producto)seleccionado).setCodigoProducto(Integer.parseInt(nuevoCodigo));
                    ((Producto)seleccionado).setPrecio(Integer.parseInt(nuevoPrecio));
                    ((Producto)seleccionado).setCantidad(Integer.parseInt(nuevaCantidad));
                     if (seleccionado instanceof Leche) {
                        ((Leche) seleccionado).tipo = TiposLeche.valueOf(nuevoOpcional);
                        ((Leche) seleccionado).porcentajeGrasa = nuevoOpcional2;
                    } 

                    if (seleccionado instanceof Queso) {
                        ((Queso) seleccionado).tipoQueso = TipoQueso.valueOf(nuevoOpcional);
                        ((Queso) seleccionado).procedencia = Procedencia.valueOf(nuevoOpcional2);;
                    } 

                    if (seleccionado instanceof Palmito) {
                        ((Palmito) seleccionado).pais = Procedencia.valueOf(nuevoOpcional);
                        ((Palmito) seleccionado).enlatado = Boolean.parseBoolean(nuevoOpcional2);
                    }
                }
            }else{
                throw new faltanDatosException();
            }
            Stage stage = (Stage) panel.getScene().getWindow();
            stage.close();
        } catch (faltanDatosException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    
        @Override
        public void initialize(URL url, ResourceBundle rb) { //Llena las casillas con la informacion actual, para poder editarla
        // Configuración básica
        titulo.setText(seleccionado.nombreProducto + " - " + seleccionado.codigoProducto);
        nombre.setText(seleccionado.nombreProducto);
        codigo.setText(String.valueOf(seleccionado.codigoProducto));
        precio.setText(String.valueOf(seleccionado.precio));
        cantidad.setText(String.valueOf(seleccionado.cantidad));

        if (seleccionado instanceof Leche) {
            opcional1.setText(((Leche)seleccionado).tipo.name());
            opcional2.setText(((Leche)seleccionado).porcentajeGrasa);
        } 

        if (seleccionado instanceof Queso) {
            opcional1.setText(((Queso)seleccionado).tipoQueso.name());
            opcional2.setText(((Queso)seleccionado).procedencia.name());
        } 

        if (seleccionado instanceof Palmito) {
            opcional1.setText(((Palmito)seleccionado).pais.name());
            opcional2.setText(String.valueOf(((Palmito)seleccionado).enlatado));
        }
    }
}
