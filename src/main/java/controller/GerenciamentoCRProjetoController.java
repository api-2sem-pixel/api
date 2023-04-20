package controller;

import java.sql.Connection;
import java.util.List;

import dao.CrDAO;
import dao.CrUsuarioDAO;
import dao.UsuarioDAO;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.CrUsuario;

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
	protected void initialize() {
		List<String> nomeCR = crDAO.listarNomeCR();
		List<String> nomeUsuario = usuarioDAO.getNomeUsuario();
		comboSquads.getItems().addAll(nomeCR);
		comboNomeUsuario.getItems().addAll(nomeUsuario);
	}

	public void gerenciarCRProjeto(ActionEvent event) {
		int idUsuario = 0;// comboSquads.;
		int idGestor = 0;
		CrUsuario crUsuario = new CrUsuario(idUsuario, idGestor);
		crUsuarioDAO.salvar(crUsuario);
	}
}
