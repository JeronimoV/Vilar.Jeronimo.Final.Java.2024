/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Jeronimo
 */
public class scene3Controllers implements Initializable {
    
    public static Gestion gestionCopy;
    
    @FXML
    public Pane panel;
    @FXML
    public TextField nombre;
    @FXML
    public TextField codigo;
    @FXML
    public TextField peso;
    @FXML
    public TextField precio;
    @FXML
    public ChoiceBox<String> marca;
    @FXML
    public ChoiceBox<String> tipo;
    
    
    public void completar(){
        try {
            String nombreNuevo = this.nombre.getText();
            int codigoNuevo = Integer.parseInt(this.codigo.getText());
            int pesoNuevo = Integer.parseInt(this.peso.getText());
            int precioNuevo = Integer.parseInt(this.precio.getText());
            String marcaNueva = marca.getValue();
            String tipoNuevo = this.tipo.getValue();

            if(!nombreNuevo.equals("") && !Integer.toString(codigoNuevo).equals("") && !Integer.toString(pesoNuevo).equals("") && !Integer.toString(precioNuevo).equals("") && !marcaNueva.equals("")){
                switch (tipoNuevo) {
                case "Leche":
                    Leche nuevaLeche = new Leche(Marcas.valueOf(marcaNueva), precioNuevo, marcaNueva, codigoNuevo, nombreNuevo, pesoNuevo, TiposLeche.NORMAL);
                    gestionCopy.listaProductos.add(nuevaLeche);

                    break;
                case "Queso":
                    Queso nuevoQueso = new Queso(Marcas.valueOf(marcaNueva), precioNuevo, codigoNuevo, nombreNuevo, pesoNuevo, TipoQueso.CREMA, Procedencia.ARGENTINA);
                    gestionCopy.listaProductos.add(nuevoQueso);
                    break;
                case "Palmito":
                    Palmito nuevoPalmito = new Palmito(Marcas.valueOf(marcaNueva), precioNuevo, codigoNuevo, nombreNuevo, pesoNuevo, Procedencia.ARGENTINA, true);
                    gestionCopy.listaProductos.add(nuevoPalmito);
                    break;

            }
            Stage stage = (Stage) panel.getScene().getWindow();
            stage.close();
            }else{
                throw new faltanDatosException();
            }
        } catch (faltanDatosException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        tipo.getItems().addAll("Leche", "Queso", "Palmito");
        Marcas[] arregloMarcas = Marcas.values();
        for(Marcas obj: arregloMarcas){
            marca.getItems().add(obj.name());
        }
    }
}
