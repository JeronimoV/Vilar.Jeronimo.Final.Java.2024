/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import clasesBase.Acuatico;
import clasesBase.Carne;
import clasesBase.Productos;
import clasesBase.prodElaborados;
import enums.Ingredientes;
import finalsegundaoportunidad.SceneBuilder;
import finalsegundaoportunidad.utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Jeronimo
 */
public class infoAdicionalController implements Initializable{
    @FXML
    public Label text_id;
    @FXML
    public Label id;
    @FXML
    public Label origen;
    @FXML
    public Label precio;
    @FXML
    public Label nombre;
    @FXML
    public Label especie;
    @FXML
    public Label vendidos;
    @FXML
    public Label op1;
    @FXML
    public Label op2;
    
    @FXML
    public Label opcion1;
    @FXML
    public Label opcion2;
    
    @FXML
    private Label tipo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        Productos data = utilidades.buscarProducto(SceneBuilder.controladorPrincipal.elSelecionado, utilidades.buscarPorNombre);
        
        id.setText(Integer.toString(data.id));
        origen.setText(data.origen.name());
        precio.setText(Integer.toString(data.precio));
        nombre.setText(data.nombreProducto);
        especie.setText(data.especieAnimal.name());
        vendidos.setText(Integer.toString(data.unidadesVendidas));
        
        if(data instanceof Carne){
            Carne obj = (Carne) data;
            opcion1.setText("Tipo Carne");
            opcion2.setText("Tipo Corte");
            op1.setText(obj.tipoCarne.name());
            op2.setText(obj.tipoCorte.name());
            tipo.setText("Producto Carne");
        }
        
        if(data instanceof Acuatico){
            Acuatico obj = (Acuatico) data;
            opcion1.setText("Tipo Captura");
            opcion2.setText("Es fresco?");
            op1.setText(obj.tipoCaptura.name());
            if(obj.fresco == true){
                op2.setText("Si");
            }else{
                op2.setText("No");
            }
            
            tipo.setText("Producto Acuatico");
        }
        
        if(data instanceof prodElaborados){
            prodElaborados obj = (prodElaborados) data;
            String textoIngredientes = "";
            opcion1.setText("Ingredientes");
            opcion2.setText("Año vencimiento");
            tipo.setText("Producto Elaborado");
            
            boolean primerIngrediente = true;
            for (Ingredientes ingrediente : obj.ingredientes) {
                if(primerIngrediente){
                    textoIngredientes = textoIngredientes + ingrediente.name();
                    primerIngrediente = false;
                }else{
                    textoIngredientes = textoIngredientes + ", " + ingrediente.name();
                }
                
            }
            
            op1.setText(textoIngredientes);
            op2.setText(Integer.toString(obj.añoVencimiento));
        }
    }
    
}
