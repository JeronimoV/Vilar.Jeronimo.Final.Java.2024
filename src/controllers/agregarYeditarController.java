/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;


import Excepciones.CreacionException;
import clasesBase.Acuatico;
import clasesBase.Carne;
import clasesBase.Productos;
import clasesBase.prodElaborados;
import enums.Cortes;
import enums.Especies;
import enums.Ingredientes;
import enums.Origen;
import enums.TiposCaptura;
import enums.TiposCarne;
import finalsegundaoportunidad.CreacionYEdicion;
import finalsegundaoportunidad.SceneBuilder;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Jeronimo
 */
public class agregarYeditarController implements Initializable {
    
    static public String tipo; 
    static public boolean modoEdicion = false;
    static public Productos producto = null;
    
    @FXML
    public ComboBox<String> comboBoxTipo;
    
    @FXML
    public Button boton;
    
    @FXML
    public TextField text1;
    
    @FXML
    public TextField text2;
    
    @FXML
    public ComboBox<String> comboBox1;
    
    @FXML
    public ComboBox<String> comboBox2;
    
    @FXML
    public Pane pane;
    
    ComboBox<String> comboOpcional1 = null;
    ComboBox<String> comboOpcional2 = null;
    ListView<String> listViewOpcional = null;
    ComboBox<String> comboOpcional3 = null;
    Button buttonOpcional = null;
    TextField textFieldOpcional = null;
    
   //Falta modularizar la mayoria de aqui
    
    @Override
    public void initialize(URL url, ResourceBundle rb){ //Metodo que se encarga de darle las opciones a los combo box y en caso de que se quiera editar la clase, llenara las casillas con su informacion
        //<ComboBox layoutX="225.0" layoutY="208.0" prefWidth="150.0" promptText="Tipo de Corte" />
        //<ComboBox layoutX="225.0" layoutY="247.0" prefWidth="150.0" promptText="Tipo de Carne" />
        ObservableList listaCombo = FXCollections.observableArrayList("carne", "acuatico", "elaborados");
        ObservableList listaCombo2 = FXCollections.observableArrayList();
        Origen[] origenLista = Origen.values();
        for(Origen obj : origenLista){
            listaCombo2.add(obj.name());
        }
        ObservableList listaCombo3 = FXCollections.observableArrayList();
        Especies[] especieLista = Especies.values();
        for(Especies obj : especieLista){
            listaCombo3.add(obj.name());
        }
        comboBoxTipo.setItems(listaCombo);
        comboBox1.setItems(listaCombo2);
        comboBox2.setItems(listaCombo3);
        
        setearDatosGuardados();
    }
    
    public void setearDatosGuardados(){
         if(modoEdicion == true && producto != null){ //Aca se fija si se quiere realizar una edicion del producto
            boton.setText("Editar");
            if(producto instanceof Carne){
                comboBoxTipo.setValue("carne");
                crearCamposPersonalizados("Tipo Carne", "Tipo corte (Opcional)", comboBoxTipo);
            }
            if(producto instanceof Acuatico){
                comboBoxTipo.setValue("acuatico");
                crearCamposPersonalizados("Tipo Captura", "Es fresco? (Opcional)", comboBoxTipo);
            }
            if(producto instanceof prodElaborados){
                comboBoxTipo.setValue("elaborados");
                crearCamposPersonalizados();
            }
            text1.setText(Integer.toString(producto.precio));
            text2.setText(producto.nombreProducto);
            comboBox1.setValue(producto.origen.name());
            comboBox2.setValue(producto.especieAnimal.name());
            cargarDatos();
        }
    }
    
    private void cargarDatos(){ // Se usa este metodo para cargad los datos especificos de cada tipo, por ejemplo si el producto es Carne, se carga la informacion guardada de ese tipo
       
        if(producto instanceof Carne && tipo.equals("carne")){
            System.out.println("entre putos de mierda");
            Carne productoCarne = (Carne) producto;
            comboOpcional2.setValue(productoCarne.tipoCorte.name());
            comboOpcional1.setValue(productoCarne.tipoCarne.name());
        }
        if(producto instanceof Acuatico && tipo.equals("acuatico")){
            Acuatico productoAcuatico = (Acuatico) producto;
            comboOpcional1.setValue(productoAcuatico.tipoCaptura.name());
            comboOpcional2.setValue(Boolean.toString( productoAcuatico.fresco));
        }
        if(producto instanceof prodElaborados && tipo.equals("elaborados")){
            prodElaborados productoElaborados = (prodElaborados) producto;
            ObservableList listaNueva = FXCollections.observableArrayList();
            for(Ingredientes obj: productoElaborados.ingredientes){
                listaNueva.add(obj.name());
            }
            listViewOpcional.setItems(listaNueva);
            textFieldOpcional.setText(Integer.toString( productoElaborados.añoVencimiento));
        }
    }
    
        
    public void agregarIngrediente(ActionEvent event){    //Este metodo se encarga de que cuando se apriete el boton de agregar ingredientes, este se agregue a la lista
        try {
            ObservableList listaNueva = FXCollections.observableArrayList();
        
            boolean ingExistente = false;
            String ingredienteNuevo = (String) comboOpcional3.getValue();

            if(ingredienteNuevo == null){
                throw new CreacionException("Antes de agregar un ingrediente, debes seleccionar uno");
            }

            for (Object object : listViewOpcional.getItems()) {
                listaNueva.add((String)object);
                if(((String) object).equals(ingredienteNuevo)){
                    ingExistente = true;
                }
            }

            if(ingExistente != true){ // A menos que el ingrediente ya exista en la lista
                listaNueva.add(comboOpcional3.getValue());
                listViewOpcional.setItems(listaNueva);
            }
        } catch (CreacionException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void eliminarIngrediente(MouseEvent event){ //Al hacer click en un ingrediente de la lista, se elimina
        String seleccionado = listViewOpcional.getSelectionModel().getSelectedItem();
        listViewOpcional.getItems().remove(seleccionado);
        SceneBuilder.controladorPrincipal.actualizarLista();
    }
    
    public void limpiarVentana(){ //Limpia las casillas de todo tipo de informacion (teniendo en cuenta su tipo) para evitar errores
        if("elaborados".equals(tipo)){
            pane.getChildren().remove(comboOpcional3);
            pane.getChildren().remove(listViewOpcional);
            pane.getChildren().remove(buttonOpcional);
            pane.getChildren().remove(textFieldOpcional);
        }else{
            pane.getChildren().remove(comboOpcional1);
            pane.getChildren().remove(comboOpcional2);
        }
        comboOpcional1 = null;
        comboOpcional2 = null;
        listViewOpcional = null;
        comboOpcional3 = null;
        buttonOpcional = null;
        textFieldOpcional = null;
    }
    
    public void cambiarPorTipoCarne(Event event){ //Se encarga de modifiar el combobox de tipos de cortes, para solo poner los relacionados con el tipo de carne seleccionado
        if ("carne".equals(tipo)) {     
            ObservableList listaNueva = FXCollections.observableArrayList();
            
            System.out.println(Arrays.toString(Cortes.values()));
            for (Cortes obj : Cortes.values()) {
                if(obj.getTipoCarne().equals(comboOpcional1.getValue())){
                    listaNueva.add(obj.name());
                }
            }
            
            comboOpcional2.setItems(listaNueva);
        }
    }
    
    public void cambiarPorEspecie(Event event){ //Se encarga de cambiar el tipo de carne, teniendo en cuenta la especie
        if ("carne".equals(tipo)) {
            String inputCombo1 = comboBox2.getValue();
            comboOpcional1.setValue(Especies.valueOf(inputCombo1).getCarne());
            
        }
    }

    public void crearCamposPersonalizados(){ //Crea las casillas para el tipo "prodElaborados"
        tipo = "elaborados";
        ListView list = new ListView();
        ComboBox combo1 = new ComboBox();
        Button boton = new Button();
        TextField field = new TextField();

        list.setLayoutY(40);
        list.setLayoutX(400);
        list.setPrefWidth(150);
        list.setPrefHeight(266);
        list.setId("opcional3");
        list.setOnMouseClicked(this::eliminarIngrediente);

        combo1.setLayoutY(210);
        combo1.setLayoutX(225);
        combo1.setPrefWidth(150);
        combo1.setPromptText("Ingredientes (Opcional)");
        combo1.setId("opcional4");

        boton.setLayoutY(235);
        boton.setLayoutX(239);
        boton.setText("Agregar Ingrediente");
        boton.setId("opcional5");
        boton.setOnAction(this::agregarIngrediente);

        field.setLayoutY(274);
        field.setLayoutX(225);
        field.setPrefWidth(150);
        field.setPrefHeight(25);
        field.setPromptText("Año de vencimiento");
        field.setId("opcional6");

        pane.getChildren().add(combo1);
        pane.getChildren().add(list);
        pane.getChildren().add(field);
        pane.getChildren().add(boton);

        ObservableList listaCombo1 = FXCollections.observableArrayList();
        Ingredientes[] ingredientesListas = Ingredientes.values();
        for(Ingredientes obj : ingredientesListas){
            listaCombo1.add(obj.name());
        }

        combo1.setItems(listaCombo1);
        
        listViewOpcional = (ListView) pane.lookup("#opcional3");
        comboOpcional3 = (ComboBox) pane.lookup("#opcional4");
        buttonOpcional = (Button) pane.lookup("#opcional5");
        textFieldOpcional = (TextField) pane.lookup("#opcional6");

    }
    
    public void crearCamposPersonalizados(String opcional1, String opcional2, ComboBox eventoCombo){ ////Crea las casillas para el tipo "Carne" y "Acuatico"
        tipo = (String)eventoCombo.getValue();
        ComboBox combo1 = new ComboBox();
        ComboBox combo2 = new ComboBox();

        combo1.setLayoutY(208);
        combo1.setLayoutX(225);
        combo1.setPrefWidth(150);
        combo1.setPromptText(opcional1);
        combo1.setId("opcional1");

        combo2.setLayoutY(247);
        combo2.setLayoutX(225);
        combo2.setPrefWidth(150);
        combo2.setPromptText(opcional2);
        combo2.setId("opcional2");
        
        combo1.setOnAction(this::cambiarPorTipoCarne);

        pane.getChildren().add(combo1);
        pane.getChildren().add(combo2);

        if("carne".equals(tipo)){
            ObservableList listaCombo1 = FXCollections.observableArrayList();
            Cortes[] cortesLista = Cortes.values();
            for(Cortes obj : cortesLista){
                listaCombo1.add(obj.name());
            }

            combo2.setItems(listaCombo1);

            ObservableList listaCombo2 = FXCollections.observableArrayList();
            TiposCarne[] tipoCarneLista = TiposCarne.values();
            for(TiposCarne obj : tipoCarneLista){
                listaCombo2.add(obj.name());
            }

            combo1.setItems(listaCombo2);
        }else{
            ObservableList listaCombo1 = FXCollections.observableArrayList();
            TiposCaptura[] capturasLista = TiposCaptura.values();
            for(TiposCaptura obj : capturasLista){
                listaCombo1.add(obj.name());
            }

            combo1.setItems(listaCombo1);

            ObservableList listaCombo2 = FXCollections.observableArrayList("Si", "No");

            combo2.setItems(listaCombo2);
        }
        
        comboOpcional1 = (ComboBox) pane.lookup("#opcional1");
        comboOpcional2 = (ComboBox) pane.lookup("#opcional2");
        
    }
    
    public void seleccionarTipo(ActionEvent event){//Te permite cambiar el tipo del producto sin errores
        limpiarVentana();
        
        ComboBox eventoCombo = (ComboBox) event.getSource();
        
        if (eventoCombo.getValue().equals("elaborados")) {
            crearCamposPersonalizados();
        }else if(eventoCombo.getValue().equals("carne")){
            crearCamposPersonalizados("Tipo Carne", "Tipo corte (Opcional)", eventoCombo);
        }else{
            crearCamposPersonalizados("Tipo de captura", "Es fresco? (opcional)", eventoCombo);
        }
        
        if(modoEdicion == true && producto != null){
            if(eventoCombo.getValue().equals(tipo)){
                cargarDatos();
            }
        }
    }
    
    public void crear(){ //Se activa al apretar el boton "crear" o "editar", este llama una funcion dedicada a la creacion, verificacion y control de errores
        CreacionYEdicion.text1 = text1;
        CreacionYEdicion.text2 = text2;
        CreacionYEdicion.comboBox1 = comboBox1;
        CreacionYEdicion.comboBox2 = comboBox2;
        CreacionYEdicion.comboOpcional1 = comboOpcional1;
        CreacionYEdicion.comboOpcional2 = comboOpcional2;
        CreacionYEdicion.textFieldOpcional = textFieldOpcional;
        CreacionYEdicion.listViewOpcional = listViewOpcional;
        CreacionYEdicion.crear();
    }
    
}
