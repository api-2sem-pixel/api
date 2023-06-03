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
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void irParametrizacao(MouseEvent event) {
        changeScene("/view/Parametrizacao/ParametrizacaoVerbas.fxml");
    }

    @FXML
    void irControleCr(MouseEvent event) throws IOException {
        changeScene("/view/Cr/VisualizacaoCR.fxml");
    }

    @FXML
    public void irCadastroCr(MouseEvent event) throws IOException {
        changeScene("/view/Cr/CadastroCR.fxml");
    }

    @FXML
    public void irCadastroCliente(MouseEvent event) {
        changeScene("/view/Cliente/CadastroCliente.fxml");
    }

    @FXML
    public void irControleCliente(MouseEvent event) {
        changeScene("/view/Cliente/ControleCliente.fxml");
    }

    @FXML
    public void irCadastroUsuario(MouseEvent event) {
        changeScene("/view/Usuario/CadastroUsuario.fxml");
    }

    @FXML
    public void irControleCrUsuario(MouseEvent event) {
        changeScene("/view/CrUsuario/GerenciamentoCRProjeto.fxml");
    }

    @FXML
    public void irLancamentoHora(MouseEvent event) {
        changeScene("/view/LancamentoHora/LancamentoHora.fxml");
    }

    @FXML
    void irFeedBackHora(MouseEvent event) {
        changeScene("/view/FeedBack/FeedBack.fxml");
    }

    @FXML
    void irMenuFeedBack(MouseEvent event) {
        changeScene("/view/Menu/MenuFeedBack.fxml");
    }

    @FXML
    void irLogin(MouseEvent event) {
        changeScene("/view/Login/Login.fxml");
    }

    @FXML
    void irVisualizacaoUsuario(MouseEvent event) {
        changeScene("/view/Usuario/VisualizacaoUsuario.fxml");
    }
    
    @FXML
    void irRelatorioGestor(MouseEvent event) {
        changeScene("/view/Relatorio/RelatorioGestor.fxml");
    }
    
    @FXML
    void irRelatorio(MouseEvent event) {
        changeScene("/view/Relatorio/Relatorio.fxml");
	}
    @FXML
    void irDashboard(MouseEvent event) {
    	changeScene("/view/Dashboard/DashboardColaborador.fxml");
    }

    void changeScene(String fxml) {
        Parent scene;
        try {
            scene = FXMLLoader.load(getClass().getResource(fxml));
            currentStage.getScene().setRoot(scene);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void irMenu() {
        if (UsuarioDAO.usuarioLogado.getIdTipoUsuario() == TipoUsuario.Administrador) {
            irMenuAdmin();
        }

        if (UsuarioDAO.usuarioLogado.getIdTipoUsuario() == TipoUsuario.Colaborador) {
            irMenuUsuario();
        }

        if (UsuarioDAO.usuarioLogado.getIdTipoUsuario() == TipoUsuario.Gestor) {
            irMenuGestor();
        }
    }

    public static void irMenuAdmin() {
        MenuController menu = new MenuController();
        menu.changeScene("/view/Menu/Menu.fxml");
    }

    public static void irMenuUsuario() {
        MenuController menu = new MenuController();
        menu.changeScene("/view/Menu/MenuUsuario.fxml");
    }

    public static void irMenuGestor() {
        MenuController menu = new MenuController();
        menu.changeScene("/view/Menu/MenuFeedback.fxml");
    }
    
    @FXML
    void irRelatorios(MouseEvent event) throws IOException {
        changeScene("/view/Relatorio/RelatorioVerba.fxml");
    }
}
