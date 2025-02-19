/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import clasesBase.Productos;
import finalsegundaoportunidad.CarniceriaGestion;
import finalsegundaoportunidad.SceneBuilder;
import finalsegundaoportunidad.utilidades;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import ordenamientos.MetodosOrdenamientoYFiltrado;

/**
 *
 * @author Jeronimo
 */
public class principalController implements Initializable {
    
    CarniceriaGestion<Productos> gestion = SceneBuilder.gestion;
    
    @FXML
    private ListView<String> listaProductos;
    
    @FXML
    private ChoiceBox ordenamiento;
    
    
    
    public String elSelecionado = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        actualizarLista();
        ObservableList listaOpciones = FXCollections.observableArrayList("Origen", "Precio", "Natural");
        ordenamiento.setItems(listaOpciones);
    }
    
    public void ordenamientoLista(){ //Se activa cuando la combobox cambia de valor
        MetodosOrdenamientoYFiltrado.Ordenamiento((String) ordenamiento.getSelectionModel().getSelectedItem());
    }
    
    public void actualizarLista(){ //actualiza los productos que estan en la lista
        ObservableList<String> lista = FXCollections.observableArrayList();
        
        while(SceneBuilder.gestion.hasNext()){
            lista.add(SceneBuilder.gestion.next().nombreProducto);
        }
        
        listaProductos.setItems(lista);
    }
    
    public void seleccionar(MouseEvent event){
        elSelecionado = listaProductos.getSelectionModel().getSelectedItem();
        
        if(event.getClickCount() >= 2){ //Si hay un doble click sobre un producto, se abrira su descripcion completa
            URL window = getClass().getResource("/ventanas/infoAdicional.fxml");
            try {
                SceneBuilder.openWindow(window);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    };
    
    public void agregar(){ //Al hacer click en el boton de agregar, se abre la ventana de creacion y setea los valores que indican una creacion en falso
        URL window = getClass().getResource("/ventanas/crearProducto.fxml");
        agregarYeditarController.modoEdicion = false;
        agregarYeditarController.producto = null;
        try {
            SceneBuilder.openWindow(window);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    };
    
    public void eliminar(){
        while(gestion.hasNext()){
            Productos data = gestion.next();
            if(data.nombreProducto.equals(elSelecionado)){
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setHeaderText("Seguro quieres eliminar el elemento: " + elSelecionado);
                alerta.setContentText("Si para eliminar, No para no eliminar");
                Optional<ButtonType> respuesta = alerta.showAndWait();
                if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
                    gestion.delete();
                }
                
            }
        }
        actualizarLista();
    };
    
    public void editar(){ // Al hacer click en editar, se abre la ventana de edicion y setea los valores de edicion en verdadero
        URL window = getClass().getResource("/ventanas/crearProducto.fxml");
        agregarYeditarController.modoEdicion = true;
        agregarYeditarController.producto = utilidades.buscarProducto(elSelecionado, utilidades.buscarPorNombre);
        try {
            SceneBuilder.openWindow(window);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    };
    
    public Consumer<Productos> incrementarVenta = producto -> {
        producto.unidadesVendidas = producto.unidadesVendidas + 1;
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("Producto Vendido");
        alerta.setContentText("El producto ahora tiene: " + producto.unidadesVendidas + " unidades vendidas");
        alerta.show();
    };

    public void vender(){
        if(elSelecionado != null){
            Productos producto = utilidades.buscarProducto(elSelecionado, utilidades.buscarPorNombre);
            incrementarVenta.accept(producto);
        }
    }
    
    public void guardar_cargar(){
        URL window = getClass().getResource("/ventanas/guardarVentana.fxml");
        try {
            SceneBuilder.openWindow(window);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    };
    
    public void encontrar(){
        URL window = getClass().getResource("/ventanas/filtradoVentana.fxml");
        try {
            SceneBuilder.openWindow(window);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    };
    
}
