package pkgfinal;

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
    
 @Override
    public void start(Stage primaryStage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("escena1.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("JavaFX con SceneBuilder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void openWindow(String fxml) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root));
        stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void main(String[] args) {

        SceneBuilder.launch(args);
    }
}
