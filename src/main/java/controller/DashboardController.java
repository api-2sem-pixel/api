package controller;

import dao.ExtratoHoraDAO;
import dao.UsuarioDAO;
import enums.TipoUsuario;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;

public class DashboardController {

    @FXML
    private PieChart dashboard;

    private static Stage currentStage;

    public static void setStage(Stage stage) {
        currentStage = stage;
    }

    @FXML
    protected void initialize() {
        Connection conn = new ConnectionFactory().recuperarConexao();
        ExtratoHoraDAO extratoHoraDAO = new ExtratoHoraDAO(conn);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        //Validação para exibir o dashboard do usuário
        if (UsuarioDAO.usuarioLogado.getIdTipoUsuario() == TipoUsuario.Colaborador) {
        	// Adicione os dados da lista aprovada ao gráfico de pizza, adicionando a legenda de "Aprovado" e a quantidade aprovada construção para extrair o tipo de usuario talvez necessario?
        	int qtdAprovada = extratoHoraDAO.qtdHoraAprovada(UsuarioDAO.usuarioLogado.getId());
        	if (qtdAprovada > 0) {
        		pieChartData.add(new PieChart.Data("Aprovado", qtdAprovada));
        	}
        
        	// Adicione os dados da lista reprovada ao gráfico de pizza, adicionando a legenda de "Reprovado" e a quantidade reprovada
        	int qtdReprovada = extratoHoraDAO.qtdHoraReprovada(UsuarioDAO.usuarioLogado.getId());
        	if (qtdReprovada > 0) {
        		pieChartData.add(new PieChart.Data("Reprovado", qtdReprovada));
        	}

        dashboard.setData(pieChartData);
        }
        
        //Validação para exibir o dashboard do gestor ou admin
        if (UsuarioDAO.usuarioLogado.getIdTipoUsuario() == TipoUsuario.Gestor || UsuarioDAO.usuarioLogado.getIdTipoUsuario() == TipoUsuario.Administrador) {
        	// Adicione os dados da lista aprovada ao gráfico de pizza, adicionando a legenda de "Aprovado" e a quantidade aprovada construção para extrair o tipo de usuario talvez necessario?
        	int qtdAprovada = extratoHoraDAO.qtdHoraCrAprovada();
        	if (qtdAprovada > 0) {
        		pieChartData.add(new PieChart.Data("Aprovado", qtdAprovada));
        	}
        
        	// Adicione os dados da lista reprovada ao gráfico de pizza, adicionando a legenda de "Reprovado" e a quantidade reprovada        
        	int qtdReprovada = extratoHoraDAO.qtdHoraCrReprovada();
        	if (qtdReprovada > 0) {
        		pieChartData.add(new PieChart.Data("Reprovado", qtdReprovada));
        	}

        dashboard.setData(pieChartData);
        }
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

    /*
    @FXML
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
    */
    
    @FXML
    void irMenu(MouseEvent event) {
        MenuController.irMenu();
    }
}