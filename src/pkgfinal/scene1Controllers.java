/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * 
 * @author Jeronimo
 */
public class scene1Controllers implements Initializable{
    Gestion gestion = new Gestion();
    @FXML
    private ListView<String> Leches;
    @FXML
    private ListView<String> Quesos;
    @FXML
    private ListView<String> Palmitos;
    @FXML
    private CheckBox eliminar;
    @FXML
    private CheckBox editar;
    @FXML
    private TextField texto;
    @FXML
    private CheckBox filter1;
    @FXML
    private CheckBox filter2;
    @FXML
    private CheckBox filter3;
    @FXML
    private ListView<String> listaFiltrada;
    @FXML
    private ListView<String> infoQueso;
    @FXML
    private ListView<String> infoPalmito;
    @FXML
    private ListView<String> infoLeche;
    @FXML
    public ListView<String> ultEd;
    
    boolean f1 = false;
    boolean f2 = false;
    boolean f3 = false;
    
    int idFilter;
    
    ObservableList lista;
    
    boolean seleccionEliminar = false;
    boolean seleccionEditar = false;
    
    public void seleccionarFiltro(ActionEvent event){
        Object source = event.getSource();
        if(source instanceof CheckBox){
            int id = Integer.parseInt(((CheckBox)source).getId());
            idFilter = id;
            switch (id) {
                case 1 -> {
                    f1 = filter1.isSelected();
                    filter2.setSelected(false);
                    filter3.setSelected(false);
                    f2 = false;
                    f3= false;
                    System.out.println(f1);
                    System.out.println(f2);
                    System.out.println(f3);
                }
                    
                case 2 -> {
                    f2 = filter2.isSelected();
                    filter1.setSelected(false);
                    filter3.setSelected(false);
                    f1 = false;
                    f3= false;
                    System.out.println(f1);
                    System.out.println(f2);
                    System.out.println(f3);
                }
                case 3 -> {
                    f3 = filter3.isSelected();
                    filter1.setSelected(false);
                    filter2.setSelected(false);
                    f2 = false;
                    f1= false;
                    System.out.println(f1);
                    System.out.println(f2);
                    System.out.println(f3);
                }
            }
        }
    }
    
    public void filtrarData(ActionEvent event){
        Object source = event.getSource();
        if(source instanceof Button){
            String dato = texto.getText();
            ArrayList<Producto> resultado = gestion.filtrado(idFilter, dato);
            System.out.println(resultado);
            lista = FXCollections.observableArrayList();
            listaFiltrada.setItems(lista);
            for(Producto obj : resultado){
                lista.add(obj.nombreProducto);
            }
        }
    }
    
    public void seleccionar(MouseEvent event) {
        String productoL = Leches.getSelectionModel().getSelectedItem();
        String productoQ = Quesos.getSelectionModel().getSelectedItem();
        String productoP = Palmitos.getSelectionModel().getSelectedItem();
        if(seleccionEliminar == true){
            while(gestion.hasNext()){
                Producto retorno = gestion.next();
                System.out.println(retorno instanceof Queso);
                if( productoL != null && retorno instanceof Leche && ((Leche)retorno).nombreProducto.equals(productoL)){
                    gestion.remove();
                }
                if(productoQ != null && retorno instanceof Queso && ((Queso)retorno).nombreProducto.equals(productoQ)){
                    gestion.remove();
                }
                if(productoP != null && retorno instanceof Palmito && ((Palmito)retorno).nombreProducto.equals(productoP)){
                    gestion.remove();
                }
            }
            this.actualizarLista();
        }
        
        if(seleccionEditar == true){
            while(gestion.hasNext()){
                Producto retorno = gestion.next();
                if( productoL != null && retorno instanceof Leche && ((Leche)retorno).nombreProducto.equals(productoL)){
                    scene2Controllers.seleccionado = retorno;
                    break;
                }
                if(productoQ != null && retorno instanceof Queso && ((Queso)retorno).nombreProducto.equals(productoQ)){
                    scene2Controllers.seleccionado = retorno;
                    break;
                }
                if(productoP != null && retorno instanceof Palmito && ((Palmito)retorno).nombreProducto.equals(productoP)){
                    scene2Controllers.seleccionado = retorno;
                    break;
                }
            }
        }
        
        if(seleccionEditar == false && seleccionEliminar == false){
            while(gestion.hasNext()){
                Producto retorno = gestion.next();
                ObservableList<String> productosInfo = FXCollections.observableArrayList();
                if( productoL != null && retorno instanceof Leche && ((Leche)retorno).nombreProducto.equals(productoL)){
                    productosInfo.add("Codigo Prod: " + Integer.toString(retorno.codigoProducto));
                    productosInfo.add("Nombre: " + retorno.nombreProducto);
                    productosInfo.add("Marca: " + retorno.marca.name());
                    productosInfo.add("Precio: " + Integer.toString(retorno.precio));
                    productosInfo.add("Cantidad: " + Integer.toString(retorno.cantidad));
                    productosInfo.add("Vendidos: " + Integer.toString(retorno.vendidos));
                    productosInfo.add("Peso: " + Integer.toString(retorno.peso));
                    
                    infoLeche.setItems(productosInfo);
                    break;
                }
                if(productoQ != null && retorno instanceof Queso && ((Queso)retorno).nombreProducto.equals(productoQ)){
                    productosInfo.add("Codigo Prod: " + Integer.toString(retorno.codigoProducto));
                    productosInfo.add("Nombre: " + retorno.nombreProducto);
                    productosInfo.add("Marca: " + retorno.marca.name());
                    productosInfo.add("Precio: " + Integer.toString(retorno.precio));
                    productosInfo.add("Cantidad: " + Integer.toString(retorno.cantidad));
                    productosInfo.add("Vendidos: " + Integer.toString(retorno.vendidos));
                    productosInfo.add("Peso: " + Integer.toString(retorno.peso));
                    
                    infoQueso.setItems(productosInfo);
                    break;
                }
                if(productoP != null && retorno instanceof Palmito && ((Palmito)retorno).nombreProducto.equals(productoP)){
                    productosInfo.add("Codigo Prod: " + Integer.toString(retorno.codigoProducto));
                    productosInfo.add("Nombre: " + retorno.nombreProducto);
                    productosInfo.add("Marca: " + retorno.marca.name());
                    productosInfo.add("Precio: " + Integer.toString(retorno.precio));
                    productosInfo.add("Cantidad: " + Integer.toString(retorno.cantidad));
                    productosInfo.add("Vendidos: " + Integer.toString(retorno.vendidos));
                    productosInfo.add("Peso: " + Integer.toString(retorno.peso));
                    
                    infoPalmito.setItems(productosInfo);
                    break;
                }
            }
        }
        
    }
    
    public void casosOrdenButton(ActionEvent event){
        Object source = event.getSource();
        if(source instanceof Button){
            String id = ((Button) source).getId();
            System.out.println(id);
            switch (id) {
                case "natural":
                    gestion.ordenNatural();
                    break;
                case "cantidad":
                    gestion.ordenComplejo(new ProductoOrdenamientoCantidad<>());
                    break;
                case "precio":
                    gestion.ordenComplejo(new ProductoOrdenamientoPrecio<>());
                    break;
            }
        }
        for(Producto obj : gestion.listaProductos){
            System.out.println(obj);
        }
        this.actualizarLista();
        
    }
    
    public void manipularDataCheck(ActionEvent event){
        Object source = event.getSource();
        if(source instanceof CheckBox){
            String id = ((CheckBox) source).getId();
            switch (id) {
                case "eliminar" -> {
                    seleccionEliminar = eliminar.isSelected();
                    editar.setSelected(false);
                    seleccionEditar = false;
                }
                    
                case "editar" -> {
                    seleccionEditar = editar.isSelected();
                    eliminar.setSelected(false);
                    seleccionEliminar = false;
                }
            }
            System.out.println(seleccionEliminar);
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        
    }
    
    public void actualizarLista(){
        ArrayList<Producto> lista = gestion.getAll();
        
        ObservableList<String> leches = FXCollections.observableArrayList();
        ObservableList<String> quesos = FXCollections.observableArrayList();
        ObservableList<String> palmitos = FXCollections.observableArrayList();
        
        for(Producto obj: lista){
            if(obj instanceof Leche){
                leches.add(((Leche) obj).nombreProducto);
            }
            if(obj instanceof Queso){
                quesos.add(((Queso) obj).nombreProducto);
            }
            if(obj instanceof Palmito){
                palmitos.add(((Palmito) obj).nombreProducto);
            }
        }
       
        Leches.setItems(null);
        Quesos.setItems(null);
        Palmitos.setItems(null);
        
        Leches.setItems(leches);
        Quesos.setItems(quesos);
        Palmitos.setItems(palmitos);
    } 
    
    
    public void openAgregar(ActionEvent event){
        SceneBuilder builder = new SceneBuilder();
        try {
             builder.openWindow("escenaAgregar.fxml");
             scene3Controllers.gestionCopy = gestion;
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
    }
    
    public void openEdit(ActionEvent event){
        SceneBuilder builder = new SceneBuilder();
        try {
             if(scene2Controllers.seleccionado == null){
                 System.out.println("Primero selecciona un producto");
             }else{
                 builder.openWindow("escenaEditar.fxml");
             }
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
    }
    
    public void saveDataBinary(){
        gestion.serializar();
    }
    
    public void downloadDataBinary(){
        gestion.desSerializar();
        actualizarLista();
    }
    
    public void saveDataJson(){
        gestion.serializacionJSON();
    }
    
    public void downloadDataJson(){
        gestion.deSerializacionJSON();
        actualizarLista();
    }
    
}
