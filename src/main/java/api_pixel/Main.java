package api_pixel;

import java.io.IOException;

import controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.ChangeScene;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Parent root = null;

        var resource = getClass()
                .getResource("/view/Login/Login.fxml");

        try {
            root = FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 944, 609);
        stage.setScene(scene);

        var stylesPath = getClass().getResource("/view/styles.css").toString();
        stage.getScene().getStylesheets().add(stylesPath);

        MenuController.setStage(stage);
        ChangeScene.setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
