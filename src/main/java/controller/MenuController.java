package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController implements Initializable {
    @FXML
    private Label homeText;

    private static Stage currentStage;

    public static void setStage(Stage stage) {
        currentStage = stage;
    }

    @Override public void initialize(URL location, ResourceBundle resources) {}

    @FXML void irControleCr(MouseEvent event) throws IOException {
        changeScene("/view/Cr/VisualizacaoCR.fxml");
    }

    @FXML public void irCadastroCr(MouseEvent event) throws IOException {
        changeScene("/view/Cr/CadastroCR.fxml");
    }

    @FXML public void irCadastroCliente(MouseEvent event) {
        changeScene("/view/Cliente/CadastroCliente.fxml");
    }

    @FXML public void irControleCliente(MouseEvent event) {
        changeScene("/view/Cliente/ControleCliente.fxml");
    }

    @FXML public void irCadastroUsuario(MouseEvent event) {
        changeScene("/view/Usuario/CadastroUsuario.fxml");
    }

    @FXML public void irControleCrUsuario(MouseEvent event) {
        changeScene("/view/CrUsuario/GerenciamentoCRProjeto.fxml");
    }

    @FXML public void irLancamentoHora(MouseEvent event) {
        changeScene("/view/LancamentoHora/LancamentoHora.fxml");
    }
    
    @FXML public void irFeedBackHora(MouseEvent event) {
        changeScene("/view/FeedBack/FeedBack.fxml");
    }
    
    private void changeScene(String fxml) {
        Parent scene;
        try {
            scene = FXMLLoader.load(getClass().getResource(fxml));
            currentStage.getScene().setRoot(scene);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void irMenu() {
        MenuController menu = new MenuController();
        menu.changeScene("/view/Menu/Menu.fxml");
    }
}
