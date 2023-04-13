package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.ProjetoDAO;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Projeto;

public class GerenciamentoCRProjetoController {

	//private ProjetoDAO projetoDAO;

	public GerenciamentoCRProjetoController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		//this.projetoDAO = new ProjetoDAO(connection);
	}

	@FXML
	private ComboBox combo;

	@FXML
	protected void initialize() {
		List<String> listTeste = new ArrayList<String>();
		listTeste.add("PIXEL");
		listTeste.add("NITENDO");
		listTeste.add("AVANADE");
		
		combo.getItems().addAll(listTeste);
	}
	
	public void cadastrarProjeto(ActionEvent event) {
		System.out.println(combo.getValue().toString());
	}
	
}
