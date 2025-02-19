/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalsegundaoportunidad;

import Excepciones.CreacionException;
import clasesBase.Acuatico;
import clasesBase.Carne;
import clasesBase.Productos;
import clasesBase.prodElaborados;
import controllers.agregarYeditarController;
import enums.Cortes;
import enums.Especies;
import enums.Ingredientes;
import enums.Origen;
import enums.TiposCaptura;
import enums.TiposCarne;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author Jeronimo
 */
public class CreacionYEdicion {
    
    //Variables de creacion extraidas de agregarYeditarController
    public static TextField text1;
    public static TextField text2;
    public static ComboBox<String> comboBox1;
    public static ComboBox<String> comboBox2;
    public static ComboBox<String> comboOpcional1;
    public static ComboBox<String> comboOpcional2;
    public static TextField textFieldOpcional;
    public static ListView<String> listViewOpcional;
    
    private static void agregarProdYActualizar(Productos prodNuevo){
        if(agregarYeditarController.modoEdicion){
            while (SceneBuilder.gestion.hasNext()) {
                Productos data = SceneBuilder.gestion.next();
                
                if(data.nombreProducto.equals(agregarYeditarController.producto.nombreProducto)){
                    SceneBuilder.gestion.update(prodNuevo);
                }
                
            }
        }else{
            SceneBuilder.gestion.create(prodNuevo);
        }
        SceneBuilder.controladorPrincipal.actualizarLista();
    }
    
    
    private static void crearCarnesYAcuatico(int id,String precio, String nombre, String origen, String especie, String inputCombo1, String inputCombo2){
        if(agregarYeditarController.tipo.equals("carne")){    
            Carne prodNuevo = null;
            if(especie == null && inputCombo2 == null){
                 prodNuevo = new Carne(id, Origen.valueOf(origen), Integer.parseInt(precio), nombre, 0, TiposCarne.valueOf(inputCombo1));
            }else if(especie == null && inputCombo2 != null){
                 prodNuevo = new Carne(id, Origen.valueOf(origen), Integer.parseInt(precio), nombre, 0, Cortes.valueOf(inputCombo2), TiposCarne.valueOf(inputCombo1));
            }else if(especie != null && inputCombo2 == null){
                 prodNuevo = new Carne(id, Origen.valueOf(origen), Integer.parseInt(precio), nombre, Especies.valueOf(especie) ,0, TiposCarne.valueOf(inputCombo1));
            }else{
                 prodNuevo = new Carne(id, Origen.valueOf(origen), Integer.parseInt(precio), nombre, Especies.valueOf(especie), 0, Cortes.valueOf(inputCombo2), TiposCarne.valueOf(inputCombo1));
            }
            agregarProdYActualizar(prodNuevo);
        }else{
             if(especie == null && inputCombo2 == null){
                Acuatico prodNuevo = new Acuatico(id, Origen.valueOf(origen), Integer.parseInt(precio), nombre, 0, TiposCaptura.valueOf(inputCombo1));
                agregarProdYActualizar(prodNuevo);
            }else if(especie == null && inputCombo2 != null){
                Acuatico prodNuevo = new Acuatico(id, Origen.valueOf(origen), Integer.parseInt(precio), nombre, 0, TiposCaptura.valueOf(inputCombo1), Boolean.valueOf(inputCombo2));
                agregarProdYActualizar(prodNuevo);
            }else{
                 System.out.println("entre aca putos");
                Acuatico prodNuevo = new Acuatico(id, Origen.valueOf(origen), Integer.parseInt(precio), nombre, Especies.valueOf(especie), 0, TiposCaptura.valueOf(inputCombo1), Boolean.valueOf(inputCombo2));
                agregarProdYActualizar(prodNuevo);
            }
        }
    }
    
    private static void crearElaborados(ObservableList<String> listOpcional, String especie, int id, String origen, String precio, String nombre, String inputTexto3){
        ArrayList<Ingredientes> nuevaLista = new ArrayList();

                for(String obj : listOpcional){
                    nuevaLista.add(Ingredientes.valueOf(obj));
                }
                if(especie == null && listOpcional.isEmpty()){
                    prodElaborados prodNuevo = new prodElaborados(id, Origen.valueOf(origen), Integer.parseInt(precio), nombre, 0, Integer.parseInt(inputTexto3));
                    agregarProdYActualizar(prodNuevo);
                }else if(especie == null && !listOpcional.isEmpty()){
                    prodElaborados prodNuevo = new prodElaborados(id, Origen.valueOf(origen), Integer.parseInt(precio), nombre, 0, nuevaLista, Integer.parseInt(inputTexto3));
                    agregarProdYActualizar(prodNuevo);
                }else{
                    prodElaborados prodNuevo = new prodElaborados(id, Origen.valueOf(origen), Integer.parseInt(precio), nombre, Especies.valueOf(especie), 0, nuevaLista, Integer.parseInt(inputTexto3));
                    agregarProdYActualizar(prodNuevo);
                }
            
    }
    
    private static void verificacion(int opcion, String opcional, String precio, String nombre, String origen) throws CreacionException{
        if(precio.trim().equals("") || nombre.trim().equals("") || origen.trim().equals("")){
            throw new CreacionException("Llena las casillas obligatorias");
        }
        
        switch (opcion) {
            case 1 -> {
                if (opcional == null) {
                    throw new CreacionException("Llena las casillas obligatorias");
                }
            }
            case 2 ->{
                if(opcional.trim().equals("")){
                    throw new CreacionException("Llena las casillas obligatorias");
                }
            }
                
        }

        if(!utilidades.isNumber(precio)){
            throw new CreacionException("En la casilla de Precio debes ingresar un numero");
        }
    }
    
    public static void crear(){
        try {
            if(agregarYeditarController.tipo == null){
                throw new CreacionException("Debe elegir un tipo de producto");
            }
            int id = SceneBuilder.conteoProductos();
            String precio = text1.getText();
            String nombre = text2.getText();
            String origen = comboBox1.getValue();
            String especie = comboBox2.getValue();
            
            if(!"elaborados".equals(agregarYeditarController.tipo)){
                String inputCombo1 = comboOpcional1.getValue();
                String inputCombo2 = comboOpcional2.getValue();
                
                verificacion(1, inputCombo1, precio, nombre, origen);
                if("acuatico".equals(agregarYeditarController.tipo) && inputCombo2 != null){
                    switch (inputCombo2) {
                        case "Si" -> {
                            inputCombo2 = "true";
                        } 
                        case "No" -> {
                            inputCombo2 = "false";
                        } 
                    }
                }

                crearCarnesYAcuatico(id, precio, nombre, origen, especie, inputCombo1, inputCombo2);
                
            }else{
                String inputTexto3 = textFieldOpcional.getText();
                
                verificacion(2, inputTexto3, precio, nombre, origen);
                
                ObservableList<String> listaOpcional = listViewOpcional.getItems();

                crearElaborados(listaOpcional, especie, id, origen, precio, nombre, inputTexto3);
            }
            SceneBuilder.controladorActualStage.close();
        } catch (CreacionException e) {
            System.out.println(e.getMessage());
        }
    }
}
