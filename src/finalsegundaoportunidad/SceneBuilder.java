package finalsegundaoportunidad;

import clasesBase.Productos;
import controllers.principalController;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jeronimo
 */
public class SceneBuilder extends Application {
    
    public static CarniceriaGestion<Productos> gestion = new CarniceriaGestion<>();
    public static principalController controladorPrincipal;
    public static Stage controladorActualStage;
    public static int conteoProductos = 0;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanas/ventanaPrincipal.fxml"));
        Parent root = loader.load();
        controladorPrincipal = loader.getController();
        Scene scene = new Scene(root);
        primaryStage.setTitle("JavaFX con SceneBuilder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
  
    public static int conteoProductos(){
        System.out.println(gestion.listaProductos.size());
        return gestion.listaProductos.size();
    }
    
    public static void main(String[] args) {

        SceneBuilder.launch(args);
    }
    
    public static void openWindow(URL window) throws Exception {
        FXMLLoader loader = new FXMLLoader(window);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("JavaFX con SceneBuilder");
        stage.setScene(scene);
        stage.show();
        controladorActualStage = stage;
        
    }
}
