package controller;

import java.sql.Connection;
import dao.ProjetoDAO;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Projeto;

public class CadastroProjetoController {

	private ProjetoDAO projetoDAO;

	public CadastroProjetoController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.projetoDAO = new ProjetoDAO(connection);
	}

	@FXML
	private TextField txNomeProjeto;

	public void cadastrarProjeto(ActionEvent event) {
		this.projetoDAO.salvar(new Projeto(txNomeProjeto.getText()));
	}
}
