package controller;

import dao.ExtratoHoraDAO;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.sql.Connection;

public class DashboardController {

    @FXML
    private PieChart dashboard;


    @FXML
    protected void initialize() {
        Connection conn = new ConnectionFactory().recuperarConexao();
        ExtratoHoraDAO extratoHoraDAO = new ExtratoHoraDAO(conn);


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Adicione os dados da lista aprovada ao gráfico de pizza, adicionando a legenda de "Aprovado" e a quantidade aprovada construção para extrair o tipo de usuario talvez necessario?
        int qtdAprovada = extratoHoraDAO.qtdHoraAprovada();
        if (qtdAprovada > 0) {
            pieChartData.add(new PieChart.Data("Aprovado", qtdAprovada));
        }

        // Adicione os dados da lista reprovada ao gráfico de pizza, adicionando a legenda de "Reprovado" e a quantidade reprovada
        int qtdReprovada = extratoHoraDAO.qtdHoraReprovada();
        if (qtdReprovada > 0) {
            pieChartData.add(new PieChart.Data("Reprovado", qtdReprovada));
        }

        dashboard.setData(pieChartData);
    }
}
