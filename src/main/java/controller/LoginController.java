package controller;

import java.net.URL;
import java.util.ResourceBundle;
import dao.TipoUsuarioDAO;
import dao.UsuarioDAO;
import enums.TipoUsuario;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.mensagem_retorno.MensagemRetorno;
import controller.MenuFeedBack.*;

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

        var tipo_usuarioADM = TipoUsuario.values().equals(TipoUsuario.Administrador);
        var tipo_usuarioGESTOR = TipoUsuario.values().equals(TipoUsuario.Gestor);
        
        if(usuario == null){
            MensagemRetorno.erro("Email e/ou senha incorretos.");
            return;
        }

        if (email.trim().equals(usuario.getEmail().trim()) && password.equals(usuario.getCpf_cnpj().substring(0, 3))) {
            if(tipo_usuarioADM == true || tipo_usuarioGESTOR == true){
            UsuarioDAO.usuarioLogado = usuario;
			MenuFeedBackController.irFeedBackHora();
            return;
            }else{
                UsuarioDAO.usuarioLogado = usuario;
                MenuController.irMenu();
                   
            }
    	}
        
        MensagemRetorno.erro("Email e/ou senha incorretos.");
    }
}
    	
	
