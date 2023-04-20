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
        
        changeScene("/view/GerenciamentoCRProjeto.fxml");
    }

    @FXML
    void irControleUsuario(MouseEvent event) {
        changeScene("/view/CadastroUsuario.fxml");
    }

    @FXML
    void irLancamentoHora(MouseEvent event) {
        changeScene("/view/LancamentoHora/LancamentoHora.fxml");
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

    public static void retornarMenu(){
        MenuController menu = new MenuController();
        menu.changeScene("/view/Menu/Menu.fxml");
    }
}
