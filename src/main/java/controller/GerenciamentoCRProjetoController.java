package controller;

import java.sql.Connection;

import dao.CrDAO;
import dao.CrUsuarioDAO;
import dao.UsuarioDAO;
import dto.CrDTO;
import dto.UsuarioDTO;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.CrUsuario;
import utils.mensagem_retorno.MensagemRetorno;

public class GerenciamentoCRProjetoController {

	private CrDAO crDAO;
	private UsuarioDAO usuarioDAO;
	private CrUsuarioDAO crUsuarioDAO;

	public GerenciamentoCRProjetoController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.crDAO = new CrDAO(connection);
		this.usuarioDAO = new UsuarioDAO(connection);
		this.crUsuarioDAO = new CrUsuarioDAO(connection);
	}

	@FXML
	private ComboBox comboSquads;

	@FXML
	private TextField txProjeto;

	@FXML
	private ComboBox comboNomeUsuario;
	
	@FXML
	private CheckBox temporario;

	private ObservableList<CrDTO> cr = FXCollections.observableArrayList();
	
	private ObservableList<UsuarioDTO> usuario = FXCollections.observableArrayList();

	@FXML
	protected void initialize() {
		cr.addAll(crDAO.getIdGestorAndNomeCr());
		usuario.addAll(usuarioDAO.getNomeUsuarioAndId());
		comboSquads.setItems(cr);
		comboNomeUsuario.setItems(usuario);

	}

	public void gerenciarCRProjeto(ActionEvent event) {
		try {
			CrDTO crDto = (CrDTO) comboSquads.getSelectionModel().getSelectedItem();
			UsuarioDTO usuarioDto = (UsuarioDTO) comboNomeUsuario.getSelectionModel().getSelectedItem();
			CrUsuario crUsuario = new CrUsuario(usuarioDto.getId(), crDto.getId());
			
			int temp = temporario.isSelected() ? 1 : 0;
			crUsuarioDAO.salvar(crUsuario, temp);
			MensagemRetorno.sucessoCadastro();
			
			limpar();
			initialize();

		} catch(Exception e) {
			MensagemRetorno.erroCadastro();
		}
	}
	
	public void limpar() {
		this.comboSquads.getItems().clear();
		this.comboNomeUsuario.getItems().clear();
		this.temporario.setSelected(false);
	}
	
    @FXML
    void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }
	
	@FXML
    void voltar(ActionEvent event) {
		MenuController.irMenu();
    }
}
