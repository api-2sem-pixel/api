package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.UsuarioDAO;
import enums.TipoUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;;
public class MenuController implements Initializable {
    @FXML
    private Label homeText;

    private static Stage currentStage;

    public static void setStage(Stage stage) {
        currentStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    void irControleCr(MouseEvent event) throws IOException {
        changeScene("/view/Cr/VisualizacaoCR.fxml");
    }

    @FXML
    void irCadastroCr(MouseEvent event) throws IOException {
        changeScene("/view/Cr/CadastroCR.fxml");
    }

    @FXML
    void irCadastroCliente(MouseEvent event) {
        changeScene("/view/Cliente/CadastroCliente.fxml");
    }

    @FXML
    void irCadastroUsuario(MouseEvent event) {
        changeScene("/view/Usuario/CadastroUsuario.fxml");
    }

    @FXML
    void irControleCrUsuario(MouseEvent event) {
        changeScene("/view/CrUsuario/GerenciamentoCRProjeto.fxml");
    }

    @FXML
    void irLancamentoHora(MouseEvent event) {
        changeScene("/view/LancamentoHora/LancamentoHora.fxml");
    }

    @FXML
    void irFeedBackHora(MouseEvent event) {
        changeScene("/view/FeedBack/FeedBack.fxml");
    }
    @FXML
    void irMenuFeedBack(MouseEvent event) {
    	changeScene("/view/MenuFeedBack/MenuFeedBack.fxml");
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
        if (UsuarioDAO.usuarioLogado.getIdTipoUsuario() == TipoUsuario.Gestor ) {
            MenuController menu = new MenuController();
            menu.changeScene("/view/MenuFeedBack/MenuFeedBack.fxml");
        }  
        if(UsuarioDAO.usuarioLogado.getIdTipoUsuario() == TipoUsuario.Colaborador){ 
            MenuController menu = new MenuController();
            menu.changeScene("/view/Menu/Menu.fxml");
        }
    }

    
    public static void ir() {
        MenuController menu = new MenuController();
        menu.changeScene("/view/MenuFeedBack/MenuFeedBack.fxml");
    }

    public static void irMenuUsuario(){
        MenuController menu = new MenuController();
        menu.changeScene("/view/MenuUsuario.fxml");
    }


}
