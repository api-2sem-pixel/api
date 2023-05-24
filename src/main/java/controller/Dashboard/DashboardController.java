package controller.Dashboard;

import java.sql.Connection;

import dao.ExtratoHoraDAO;
import enums.EtapaExtrato;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class DashboardController {

    @FXML
    private PieChart dashboard;

    private ObservableList<ExtratoHoraDAO> aprovada = FXCollections.observableArrayList();
    private ObservableList<ExtratoHoraDAO> reprovada = FXCollections.observableArrayList();
    
    public DashboardController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
	
	}

    @FXML
    protected void initialize() {
    	ExtratoHoraDAO extratohora;
    	if (EtapaExtrato.APROVADA == extratohora.qtdHoraAprovada()) {
            aprovada.add(extratohora.qtdHoraAprovada());
        }
        if (EtapaExtrato.REPROVADA == extratohora.qtdHoraReprovada()) {
            reprovada.add(extratohora.qtdHoraReprovada());
        }
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();


    }
}
