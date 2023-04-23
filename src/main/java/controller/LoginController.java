package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.UsuarioDAO;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.mensagem_retorno.MensagemRetorno;

public class LoginController implements Initializable {   
	@FXML
	private TextField emailField;
	
	@FXML
	private PasswordField passwordField;
		
    @FXML
    private Button btn_entrar;

    private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var connection = new ConnectionFactory().recuperarConexao();
        usuarioDAO = new UsuarioDAO(connection);
    }

	@FXML
    private void login(ActionEvent stage)   {
		String email = emailField.getText();
    	String password = passwordField.getText();
		
        var usuario = usuarioDAO.getUsuarioBy(email);

        if(usuario == null){
            MensagemRetorno.erro("Email e/ou senha incorretos.");
            return;
        }

        if (email.trim().equals(usuario.getEmail().trim()) && password.equals(usuario.getCpf_cnpj().substring(0, 3))) {
            UsuarioDAO.usuarioLogado = usuario;
			MenuController.irMenu();
            return;
    	}
        
        MensagemRetorno.erro("Email e/ou senha incorretos.");
    }
}
    	
	
