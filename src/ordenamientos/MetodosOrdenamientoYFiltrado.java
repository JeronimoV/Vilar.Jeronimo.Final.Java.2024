/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordenamientos;

import clasesBase.Productos;
import finalsegundaoportunidad.SceneBuilder;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Jeronimo
 */
public class MetodosOrdenamientoYFiltrado{
    public static void Ordenamiento(String opcion){
        System.out.println(opcion);
        switch (opcion) {
            case "Origen" -> {
                SceneBuilder.gestion.listaProductos.sort(new OrdenamientoPorOrigen<>());
                SceneBuilder.controladorPrincipal.actualizarLista();
            }
            case "Precio" -> {
                SceneBuilder.gestion.listaProductos.sort(new OrdenamientoPorPrecio<>());
                SceneBuilder.controladorPrincipal.actualizarLista();
            }
            case "Natural" -> {
                SceneBuilder.gestion.listaProductos.sort(((p1, p2) -> p1.compareTo(p2)));
                SceneBuilder.controladorPrincipal.actualizarLista();
            }
        }
    }
    
    public static <T extends Productos> void guardarFiltrado(String tipoFiltrado, String textoBuscado, ArrayList<T> data){
        try (BufferedWriter writer = new  BufferedWriter(new FileWriter("filtrado.txt"))) {
            writer.write("Se ha filtrado por " + tipoFiltrado +" y usando el texto " + textoBuscado + ". Estos fueron los resultados: ");
            for(Productos obj : data){
                writer.newLine();
                writer.write(obj.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static <T extends Productos> ArrayList<T> filtrar(String opcion, String busqueda){
        ArrayList<T> listaFiltrada = new ArrayList<>();
        while (SceneBuilder.gestion.hasNext()) {
            T data = (T) SceneBuilder.gestion.next();
            System.out.println("llegue aca");
            switch (opcion) {
                case "Id" -> { //id
                    if(data.id == Integer.parseInt(busqueda)){
                        listaFiltrada.add(data);
                    }
                    MetodosOrdenamientoYFiltrado.guardarFiltrado("id", busqueda, listaFiltrada);
                }
                case "Origen" -> { //origen
                    if(data.origen.name().toUpperCase().equals(busqueda.toUpperCase())){
                        listaFiltrada.add(data);
                    }
                    MetodosOrdenamientoYFiltrado.guardarFiltrado("Origen", busqueda, listaFiltrada);
                }
                case "Nombre" -> { //nombre
                    if(data.nombreProducto.toUpperCase().equals(busqueda.toUpperCase())){
                        listaFiltrada.add(data);
                    }
                    MetodosOrdenamientoYFiltrado.guardarFiltrado("Nombre", busqueda, listaFiltrada);
                }
                case "Especie" -> { //especie
                    if(data.especieAnimal.name().toUpperCase().equals(busqueda.toUpperCase())){
                        listaFiltrada.add(data);
                    }
                    MetodosOrdenamientoYFiltrado.guardarFiltrado("Especie", busqueda, listaFiltrada);
                }
            }             
        }
        return listaFiltrada;
    }
}
