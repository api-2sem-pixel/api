package controller.Cliente;

import java.sql.Connection;

import controller.MenuController;
import dao.ClienteDAO;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

	public void cadastrarCliente(ActionEvent event) {
		try {
			this.clienteDAO.salvar(new Cliente(tfRazaoSocial.getText(), tfCNPJ.getText()));
			MensagemRetorno.sucessoCadastro();
			limpar();
		} catch (Exception e) {
			MensagemRetorno.erroCadastro();
		}
	}

	public void limpar() {
		tfRazaoSocial.clear();
		tfCNPJ.clear();
	}

	@FXML
	void irControleCliente(ActionEvent event) {
		MenuController mc = new MenuController();
		mc.irControleCliente(null);
	}

	@FXML
	void retornarMenu(MouseEvent event) {
		MenuController.irMenu();
	}
}
