package controller.Relatorio;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import controller.MenuController;
import dao.CrDAO;
import dao.CrUsuarioDAO;
import dao.RelatorioDAO;
import dto.CrDTO;
import dto.IntegrantesCrDTO;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import model.DadosRelatorio;
import model.RelatorioModel;
import relatorio.CalcularVerba;
import relatorio.GerarRelatorio;

public class RelatorioVerbaController {

	private CrDAO crDAO;
	
	private RelatorioDAO relatorioDAO;

	public RelatorioVerbaController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.crDAO = new CrDAO(connection);
		this.relatorioDAO = new RelatorioDAO(connection);
	}

	@FXML
	private ComboBox comboCR;
	
	@FXML
	private ComboBox comboVerba;
	
	private ObservableList<CrDTO> cr = FXCollections.observableArrayList();
	
	private ObservableList<String> verbas = FXCollections.observableArrayList();
	
	private List<String> verba = new ArrayList<String>() {
		{
			add("1601");
			add("1602");
			add("3000");
			add("3001");
			add("1809");
		}
	};
	
	private CalcularVerba calcularVerba = new CalcularVerba();
	
	private int idAnterior = 0;
	
	private DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
	
	private GerarRelatorio gerarRelatorio = new GerarRelatorio();
	
	@FXML
	protected void initialize() {
		cr.addAll(crDAO.getIdGestorAndNomeCr());
		verbas.addAll(verba);
		comboCR.setItems(cr);
		comboVerba.setItems(verbas);
		
	}
	
	public void gerarRelatorio(ActionEvent event) throws IOException {
		CrDTO cr = (CrDTO) comboCR.getSelectionModel().getSelectedItem();
		String verba = (String) comboVerba.getSelectionModel().getSelectedItem();
		List<RelatorioModel> relatorioModel = new ArrayList<RelatorioModel>();
		List<DadosRelatorio> dados = relatorioDAO.listarDadosParaRelatorio(cr.getId());
		for(DadosRelatorio dado : dados) {
			if(relatorioModel.isEmpty() || idAnterior != dado.getUserId()){
				RelatorioModel relatorio = new RelatorioModel();
				relatorio.setMatricula(dado.getCpf());
				relatorio.setNome(dado.getNome());
				relatorio.setVerba(verba);
				relatorio.setQuantidadeHoras(calcularVerba.calculaQuantidadeHrsPorVerba(verba, convertToDateTime(dado.getDtIni()), convertToDateTime(dado.getDtFim())));
				idAnterior = dado.getUserId();
				relatorioModel.add(relatorio);
			}else {
				double qntHr = relatorioModel.get(relatorioModel.size()-1).getQuantidadeHoras();
				relatorioModel.get(relatorioModel.size()-1).setQuantidadeHoras(qntHr + calcularVerba.calculaQuantidadeHrsPorVerba(verba, convertToDateTime(dado.getDtIni()), convertToDateTime(dado.getDtFim())));
			}
		}
		gerarRelatorio.geraXls(relatorioModel);
	}
	
    @FXML void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }
    
    public LocalDateTime convertToDateTime(Date data) {
    	String dataFormatada = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(data);
    	return dtf.parseDateTime(dataFormatada).toLocalDateTime();
    }
}
