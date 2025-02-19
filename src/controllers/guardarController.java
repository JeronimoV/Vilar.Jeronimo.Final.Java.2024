/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import clasesBase.Acuatico;
import clasesBase.Carne;
import clasesBase.Productos;
import clasesBase.prodElaborados;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import enums.Cortes;
import enums.Especies;
import enums.Ingredientes;
import enums.Origen;
import enums.TiposCaptura;
import enums.TiposCarne;
import finalsegundaoportunidad.SceneBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Jeronimo
 */
public class guardarController {
        
    public void serializar(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("productos.dat"))) {
            out.writeObject(SceneBuilder.gestion.listaProductos);
            System.out.println("entre");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
  
    public void deserializar(){
        try (ObjectInputStream inp = new ObjectInputStream(new FileInputStream("productos.dat"))) {
            ArrayList<Productos> listaNueva = (ArrayList<Productos>) inp.readObject();
            SceneBuilder.gestion.listaProductos = listaNueva;
            System.out.println("entre2");
            SceneBuilder.controladorPrincipal.actualizarLista();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void guardarCSV(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Productos.csv"))){
            
            writer.write("id,origen,precio,nombre,especie,unidades vendidas, Opcional 1, Opcional 2, tipo");
            writer.newLine();
            
            for(Productos prod : SceneBuilder.gestion.listaProductos){
                
                writer.write(prod.toString());
                System.out.println(prod.toString());
                writer.newLine();
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void cargarCSV(){
        try (BufferedReader reader = new BufferedReader(new FileReader("productos.csv"))) {
            boolean primeraLinea = true;
            String linea = reader.readLine();
            ArrayList<Productos> listaCargada = new ArrayList<>();
            
            while(linea != null){
                if(!primeraLinea){
                    String[] lineaDividida = linea.split(",");
                    int size = lineaDividida.length;
                        switch (lineaDividida[size-1]) {
                            case "Carne" -> {
                                listaCargada.add( new Carne(Integer.parseInt(lineaDividida[0]), Origen.valueOf(lineaDividida[1]), Integer.parseInt(lineaDividida[2]), lineaDividida[3], Especies.valueOf(lineaDividida[4]), Integer.parseInt(lineaDividida[5]), Cortes.valueOf(lineaDividida[6]), TiposCarne.valueOf(lineaDividida[7])));
                            }
                            case "Acuatico" -> {
                                listaCargada.add( new Acuatico(Integer.parseInt(lineaDividida[0]), Origen.valueOf(lineaDividida[1]), Integer.parseInt(lineaDividida[2]), lineaDividida[3], Especies.valueOf(lineaDividida[4]), Integer.parseInt(lineaDividida[5]), TiposCaptura.valueOf(lineaDividida[6]), Boolean.valueOf(lineaDividida[7])));
                            }
                            case "Elaborados" -> {
                                String[] ingredientes = linea.split("\\[|\\]")[1].split(",");
                                ArrayList<Ingredientes> nuevosIngredientes = new ArrayList<>();
                                for(String obj: ingredientes){
                                    nuevosIngredientes.add(Ingredientes.valueOf(obj.trim()));
                                }
                                

                                listaCargada.add( new prodElaborados(Integer.parseInt(lineaDividida[0]), Origen.valueOf(lineaDividida[1]), Integer.parseInt(lineaDividida[2]), lineaDividida[3], Especies.valueOf(lineaDividida[4]), Integer.parseInt(lineaDividida[5]), nuevosIngredientes, Integer.parseInt(lineaDividida[size-2]) ));
                            }
                        }
                   
                    
                }else{
                    primeraLinea = false;
                    System.out.println("entre");
                }
                linea = reader.readLine();
                
            }
            SceneBuilder.gestion.listaProductos.clear();
            for (Productos productos : listaCargada) {
                SceneBuilder.gestion.listaProductos.add(productos);
                System.out.println(productos.nombreProducto);
            }
            SceneBuilder.controladorPrincipal.actualizarLista();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void JSONwriter(String path, String tipo, ArrayList data){
        Gson gson = new Gson();
            try(FileWriter writer = new FileWriter(path)) {
                switch (tipo) {
                    case "carne" -> {
                        gson.toJson(data, new TypeToken<ArrayList<Carne>>(){}.getType(), writer);
                    }
                    case "acuatico" -> {
                        gson.toJson(data, new TypeToken<ArrayList<Acuatico>>(){}.getType(), writer);
                    }
                    case "elaborados" -> {
                        gson.toJson(data, new TypeToken<ArrayList<prodElaborados>>(){}.getType(), writer);
                    }
                    
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
    
    private void JSONreader(String filepath, String tipo){
        try (FileReader reader = new FileReader(filepath)) {
            Gson gson = new Gson();
            ArrayList<Productos> listaCargada = new ArrayList<>();
            
            if("carne".equals(tipo)){
                ArrayList<Carne> listaCarnes = gson.fromJson(reader, new TypeToken<ArrayList<Carne>>() {}.getType());
                
                for(Carne obj : listaCarnes){
                    listaCargada.add((Productos) obj);
                }
            }
            if("acuatico".equals(tipo)){
                ArrayList<Acuatico> listaAcuaticos = gson.fromJson(reader, new TypeToken<ArrayList<Acuatico>>() {}.getType());
                
                for(Acuatico obj : listaAcuaticos){
                    listaCargada.add((Productos) obj);

                }
            }
            if("elaborados".equals(tipo)){
                ArrayList<prodElaborados> listaElaborados = gson.fromJson(reader, new TypeToken<ArrayList<prodElaborados>>() {}.getType());
                
                for(prodElaborados obj : listaElaborados){
                    listaCargada.add((Productos) obj);

                }
            }
            for (Productos productos : listaCargada) {
                SceneBuilder.gestion.listaProductos.add(productos);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cargarJSON(){
        String[] filepaths = {"carne.json", "acuatico.json", "elaborados.json"};
        
        SceneBuilder.gestion.listaProductos.clear();
        JSONreader(filepaths[0], "carne");
        JSONreader(filepaths[1], "acuatico");
        JSONreader(filepaths[2], "elaborados");
        SceneBuilder.controladorPrincipal.actualizarLista();
    }
    
    public void guardarJSON(){
        String[] filepaths = {"carne.json", "acuatico.json", "elaborados.json"};
        ArrayList<Carne> carnes = new ArrayList<>();
        ArrayList<Acuatico> acuaticos = new ArrayList<>();
        ArrayList<prodElaborados> elaborados = new ArrayList<>();
        
        for (Productos prod : SceneBuilder.gestion.listaProductos) {
            if(prod instanceof Carne){
                carnes.add((Carne)prod);
            }
            if(prod instanceof Acuatico){
                acuaticos.add((Acuatico)prod);
            }
            if(prod instanceof prodElaborados){
                elaborados.add((prodElaborados)prod);
            }
        }
        
  
            JSONwriter(filepaths[0], "carne", carnes);
            JSONwriter(filepaths[1], "acuatico", acuaticos);
            JSONwriter(filepaths[2], "elaborados", elaborados);
    }
    
}
