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
            .getResource("/view/CadastroUsuario.fxml");
        
        try {
            root =  FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Cadastro de novo usuario");
        Scene scene = new Scene(root, 944, 609);
        stage.setScene(scene);
        
        var stylesPath = getClass().getResource("/view/stylesCadastroUsuario.css").toString();
        stage.getScene().getStylesheets().add(stylesPath);
        
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}