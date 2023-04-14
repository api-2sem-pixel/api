package controller;

import java.sql.Connection;

import dao.CrDAO;
import dao.SquadDAO;
import dao.UsuarioDAO;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.CR;

public class CadastroCRController {

	private CrDAO crDAO;
	
	private UsuarioDAO usuarioDAO;
	
	public CadastroCRController() {
		Connection connection = null;//new ConnectionFactory().recuperarConexao();
		this.crDAO = new CrDAO(connection);
		this.usuarioDAO = new UsuarioDAO(connection);
	}
	
	@FXML
	private TextField txSquad;
	
	@FXML
	private TextField txGestor;
	
	
	public void inserirCR(ActionEvent event) {
		Integer id_gestor = this.usuarioDAO.getIdUsuario(txGestor.getText());
		this.crDAO.salvar(new CR(id_gestor, txSquad.getText()));
	}
}
