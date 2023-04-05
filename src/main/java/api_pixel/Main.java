package api_pixel;

import java.io.IOException;

import controller.LancamentoHora;
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
            .getResource("/view/LancamentoHora.fxml");
        
        try {
            var loader = new FXMLLoader(resource);
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
