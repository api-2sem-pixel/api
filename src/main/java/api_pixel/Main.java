package api_pixel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Parent root = null;
        
        var resource = getClass()
            .getResource("/view/Login/Login.fxml");
        
        try {
            root =  FXMLLoader.load(resource);
       
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Login");
        Scene scene = new Scene(root, 944, 609);
        stage.setScene(scene);
       
        stage.show();
      
    }

    public static void main(String[] args) {
        launch(args);
    }
}