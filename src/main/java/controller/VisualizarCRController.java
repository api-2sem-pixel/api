package controller;

import java.sql.Connection;
import java.util.List;

import dao.CrDAO;
import dao.CrUsuarioDAO;
import dto.CrDTO;
import dto.IntegrantesCrDTO;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class VisualizarCRController {

	private CrDAO crDAO;
	private CrUsuarioDAO crUsuarioDAO;

	public VisualizarCRController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.crDAO = new CrDAO(connection);
		this.crUsuarioDAO = new CrUsuarioDAO(connection);
	}

	@FXML
	private ComboBox comboCR;
	
	@FXML
	private TableView<IntegrantesCrDTO> tabela;
	
	@FXML
	private TableColumn<IntegrantesCrDTO, String> integrantesCol;
	
	@FXML
	private TableColumn<IntegrantesCrDTO, String> descricaoCol;
	
	private ObservableList<CrDTO> cr = FXCollections.observableArrayList();
	
	@FXML
	protected void initialize() {
		cr.addAll(crDAO.getIdGestorAndNomeCr());
		comboCR.setItems(cr);
		
		integrantesCol.setCellValueFactory(new PropertyValueFactory<>("integrante"));
		descricaoCol.setCellValueFactory(new PropertyValueFactory<>("descricao"));
	}
	
	public void buscar(ActionEvent event) {
		CrDTO cr = (CrDTO) comboCR.getSelectionModel().getSelectedItem();
		List<IntegrantesCrDTO> integrantes = crUsuarioDAO.listarIntegrantes(cr.getId());
		tabela.setItems(listaIntegrantes(integrantes));
	}
	
	private ObservableList<IntegrantesCrDTO> listaIntegrantes(List<IntegrantesCrDTO> integrantes){
		return FXCollections.observableArrayList(integrantes);
	}
	
    @FXML
    void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }
}
