package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController {
    private static Stage currentStage;

    public static void setStage(Stage stage){
        currentStage = stage;
    }

    @FXML
    void irControleCr(MouseEvent event) throws IOException {
        
        changeScene("");
    }

    @FXML
    void irControleUsuario(MouseEvent event) {
        changeScene("");
    }

    @FXML
    void irLancamentoHora(MouseEvent event) {
        changeScene("/view/LancamentoHora.fxml");
    }

    private void changeScene(String fxml){
        Parent scene;
        try {
            scene = FXMLLoader.load(getClass().getResource(fxml));
            currentStage.getScene().setRoot(scene);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
