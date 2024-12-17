/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;

/**
 *
 * @author Jeronimo
 */
public class Gestion implements CRUDoperations<Producto>, Iterator<Producto> {
    public ArrayList<Producto> listaProductos;
    public int posicion;
    
    public Gestion(){
        this.listaProductos = new ArrayList<>();
        this.posicion = -1;
    }
    
    public void ordenNatural(){
        this.listaProductos.sort((p1, p2) -> p1.compareTo(p2));
    }
    
    public void ordenComplejo(Comparator<Producto> comparador){
        this.listaProductos.sort(comparador);
    }
    
    public ArrayList<Producto> filtrado(int opcion, String dato){
        ArrayList<Producto> listaFiltrada = new ArrayList<>();
        
        switch (opcion) {
            case 1:         
                for(Producto obj : this.listaProductos){
                    if(obj.nombreProducto.equals(dato)){
                        listaFiltrada.add(obj);
                    }
                }
                break;
            case 2:       
                for(Producto obj : this.listaProductos){
                    if(obj.marca.name().equals(dato)){
                        listaFiltrada.add(obj);
                    }
                }
                break;
            case 3:       
                for(Producto obj : this.listaProductos){
                    if(obj.codigoProducto == Integer.parseInt(dato)){
                        listaFiltrada.add(obj);
                    }
                }
                break;
        }
        return listaFiltrada;
    }
    
    public ArrayList<Producto> getAll(){
        return listaProductos;
    }
    
    @Override
    public void create(Producto producto){
        listaProductos.add(producto);
        System.out.println("Producto agregado");
    }
    
    @Override
    public void edit(Producto nuevoProducto){
         listaProductos.set(posicion, nuevoProducto);
        }
    
    @Override
    public boolean hasNext(){
        if((this.posicion + 1 < listaProductos.size()) == false){
            this.reiniciarBucle();
            return false;
        }
        return this.posicion < listaProductos.size();
        
    }
    
    @Override
    public void remove(){
        listaProductos.remove(posicion);
    }
    
    @Override
    public Producto next(){
        posicion++;
        Producto producto = listaProductos.get(posicion);
        return producto;
    }
    
    public void reiniciarBucle(){
        this.posicion = -1;
    }
    
    public void serializar(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("productos.dat"))) {
            out.writeObject(listaProductos);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void desSerializar(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("productos.dat"))) {
           ArrayList<Producto> dataDeserializada = (ArrayList<Producto>) in.readObject();
           listaProductos = dataDeserializada;
        }catch(Exception e){
            System.out.println(e.getMessage());
            
        }
    }
    
    private void guardarOleerDataJson(String filePath, ArrayList data){ //Guardar
        
        Gson gson = new Gson();
        
            try(FileWriter writer = new FileWriter(filePath)) {
                String json = gson.toJson(data);
                writer.write(json);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
    
    private void guardarOleerDataJson(String filePath, String clase){ //Leer
        Gson gson = new Gson();
        try(FileReader reader = new FileReader(filePath)) {
            ArrayList<Leche> json = null;
            if(clase == "leche"){
                json = gson.fromJson(reader, new TypeToken<ArrayList<Leche>>() {}.getType());
            }
            if(clase == "queso"){
                json = gson.fromJson(reader, new TypeToken<ArrayList<Queso>>() {}.getType());
            }
            if(clase == "palmito"){
                json = gson.fromJson(reader, new TypeToken<ArrayList<Palmito>>() {}.getType());
            }
            System.out.println("llegue1");
            for(Producto obj : json){
                System.out.println("llegue2");
                listaProductos.add(obj);
            }
            System.out.println(listaProductos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void serializacionJSON(){
        
        ArrayList<Leche> lecheGrupo = new ArrayList<>();
        ArrayList<Queso> quesoGrupo = new ArrayList<>();
        ArrayList<Palmito> palmitoGrupo = new ArrayList<>();
        
        while(hasNext()){
            Producto obj = next();
            if(obj instanceof Leche){
                lecheGrupo.add((Leche)obj);
            }
            if(obj instanceof Queso){
                quesoGrupo.add((Queso)obj);
            }
            if(obj instanceof Palmito){
                palmitoGrupo.add((Palmito)obj);
            }
        }
        
        String[] filepaths = {"leche.json", "queso.json", "palmito.json"};
        guardarOleerDataJson(filepaths[0], lecheGrupo);
        guardarOleerDataJson(filepaths[1], quesoGrupo);
        guardarOleerDataJson(filepaths[2], palmitoGrupo);
        
    }
    
    public void deSerializacionJSON(){
        
        
        String[] filepaths = {"leche.json", "queso.json", "palmito.json"};
        
        listaProductos.clear();
        guardarOleerDataJson(filepaths[0], "leche");
        guardarOleerDataJson(filepaths[1], "queso");
        guardarOleerDataJson(filepaths[2], "palmito");
        
    }
    
}
