package controller;

import java.sql.Connection;

import dao.CrDAO;
import dao.UsuarioDAO;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.CR;
import utils.ChangeScene;
import utils.mensagem_retorno.MensagemRetorno;

public class CadastroCRController {

	private CrDAO crDAO;
	
	private UsuarioDAO usuarioDAO;
	
	public CadastroCRController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.crDAO = new CrDAO(connection);
		this.usuarioDAO = new UsuarioDAO(connection);
	}
	
	@FXML
	private TextField tfNome;
	
	@FXML
	private TextField tfCodigo;
	
	@FXML
	private TextField tfSigla;
	
	public void inserirCR(ActionEvent event) {
		try {
			this.crDAO.salvar(new CR(tfNome.getText(), tfSigla.getText(), tfCodigo.getText()));
			MensagemRetorno.sucessoCadastro();
			limpar();
		} catch(Exception e) {
			MensagemRetorno.erroCadastro();
		}
		
	}
	
    public void voltarVisualizacao(ActionEvent event) {
		ChangeScene cs = new ChangeScene();
		cs.irVisualizacaoCR();
	}
	
	public void limpar() {
		this.tfNome.clear();
		this.tfCodigo.clear();
		this.tfSigla.clear();
	}
	
    @FXML
    void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }
}
