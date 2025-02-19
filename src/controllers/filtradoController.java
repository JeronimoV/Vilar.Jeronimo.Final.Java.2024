/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import Excepciones.FiltradoException;
import clasesBase.Productos;
import finalsegundaoportunidad.SceneBuilder;
import finalsegundaoportunidad.utilidades;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import ordenamientos.MetodosOrdenamientoYFiltrado;

/**
 *
 * @author Jeronimo
 */
public class filtradoController implements Initializable {
    @FXML
    public ListView<String> listview;
    @FXML
    public ComboBox<String> combobox;
    @FXML
    public TextField textfield;
    @FXML
    public Button boton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){ //Rellena de opciones la ocmbobox para que puedas elegir el tipo de filtrado
        ObservableList<String> listaFiltrado = FXCollections.observableArrayList("Id", "Origen", "Nombre", "Especie");
        combobox.setItems(listaFiltrado);
    }
    
    public void seleccion(MouseEvent event){   
        SceneBuilder.controladorPrincipal.elSelecionado = listview.getSelectionModel().getSelectedItem();
        URL window = getClass().getResource("/ventanas/infoAdicional.fxml");
        try {
            SceneBuilder.openWindow(window);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public <T extends Productos> void buscar(){ //Se encarga de la busqueda de productos teniendo en cuenta el tipo de filtrado
        try {
            String texto;
            String opcion;
            
            if("".equals(textfield.getText()) || combobox.getValue() == null){
                throw new FiltradoException("Uno o mas campos estan vacios y pendientes de completar");
            }else{
                texto = textfield.getText();
                opcion = combobox.getValue();
            }
            
            if(opcion.equals("Id")){
                boolean retorno = utilidades.isNumber(texto);
                if (!retorno) {
                    throw new FiltradoException("El id a buscar debe ser un numero");
                }
            }

            ArrayList<T> retorno = MetodosOrdenamientoYFiltrado.filtrar(opcion, texto);
            ObservableList nuevaLista = FXCollections.observableArrayList();
            for (T object : retorno) {
                nuevaLista.add(object.nombreProducto);
            }
            listview.setItems(nuevaLista);
        } catch (FiltradoException e) {
            System.out.println(e.getCause());
        }
       
    }
}
