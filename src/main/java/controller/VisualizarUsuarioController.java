package controller;

import java.sql.Connection;
import java.util.List;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import enums.TipoUsuario;
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

public class VisualizarUsuarioController {
	private UsuarioDAO usuarioDAO;

	public VisualizarUsuarioController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.usuarioDAO = new UsuarioDAO(connection);
	}
	
	@FXML ComboBox comboUsuario;
	@FXML private TableView<UsuarioDTO> tabelaUsuarios;
	@FXML private TableColumn<UsuarioDTO, String> colNome;
	@FXML private TableColumn<UsuarioDTO, String> colCPFCNPJ;
	@FXML private TableColumn<UsuarioDTO, String> colEmail;
	@FXML private TableColumn<UsuarioDTO, TipoUsuario> colTipo;
		
	private ObservableList<UsuarioDTO> usuario = FXCollections.observableArrayList();
	
	@FXML
	protected void initialize() {
		usuario.addAll(usuarioDAO.getNomeUsuarioAndId());
		comboUsuario.setItems(usuario);
		
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colCPFCNPJ.setCellValueFactory(new PropertyValueFactory<>("cpf_cnpj"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colTipo.setCellValueFactory(new PropertyValueFactory<>("idTipoUsuario"));
	}
	
	public void buscarUsuario(ActionEvent event) {
		UsuarioDTO usuario = (UsuarioDTO) comboUsuario.getSelectionModel().getSelectedItem();
		List<UsuarioDTO> usuarios = null;
		try {
			usuarios = UsuarioDAO.listarUsuarios(usuario.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabelaUsuarios.setItems(listarUsuario(usuarios));
	}
	
	private ObservableList<UsuarioDTO> listarUsuario(List<UsuarioDTO> usuarios){
		return FXCollections.observableArrayList(usuarios);
	}
	
    @FXML
    void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }
}