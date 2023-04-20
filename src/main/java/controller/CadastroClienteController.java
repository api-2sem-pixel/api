package controller;

import java.sql.Connection;
import dao.ClienteDAO;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Cliente;
import utils.mensagem_retorno.MensagemRetorno;

public class CadastroClienteController {

	private ClienteDAO clienteDAO;

	public CadastroClienteController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.clienteDAO = new ClienteDAO(connection);
	}

	@FXML
	private TextField tfRazaoSocial;
	
	@FXML
	private TextField tfCNPJ;
	
	private MensagemRetorno msg = new MensagemRetorno();
	
	public void cadastrarCliente(ActionEvent event) {
		try {
			this.clienteDAO.salvar(new Cliente(tfRazaoSocial.getText(), tfCNPJ.getText()));
			msg.sucesso();
			limpar();
		} catch (Exception e) {
			msg.erro();
		}
	}
	
	public void limpar() {
		tfRazaoSocial.clear();
		tfCNPJ.clear();
	}
}
