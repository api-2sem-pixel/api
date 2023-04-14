package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CrDAO;
import dao.ProjetoDAO;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Projeto;
import model.Squad;

public class GerenciamentoCRProjetoController {

	private CrDAO crDAO;

	public GerenciamentoCRProjetoController() {
		//Connection connection = new ConnectionFactory().recuperarConexao();
		//this.crDAO = new CrDAO(connection);
	}

	@FXML
	private ComboBox comboSquads;
	
	@FXML
	private ComboBox comboProjeto;
	
	@FXML
	private ComboBox comboNomeUsuario;

	@FXML
	protected void initialize() {
		List<String> nomeCR = crDAO.listarNomeCR();
		
		comboSquads.getItems().addAll(nomeCR);
	}
	
	public void cadastrarProjeto(ActionEvent event) {
		System.out.println(comboSquads.getValue().toString());
	}
}
