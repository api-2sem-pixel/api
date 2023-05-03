package controller;

import java.net.URL;
import java.util.ResourceBundle;

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
    private void login(ActionEvent stage) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            MensagemRetorno.erro("Por favor, preencha todos os campos.");
            return;
        }

        var usuario = usuarioDAO.getUsuarioBy(email);

        if (usuario == null) {
            MensagemRetorno.erro("Email e/ou senha incorretos.");
            return;
        }

        if (email.trim().equalsIgnoreCase(usuario.getEmail().trim()) && password.equals(usuario.getCpf_cnpj().substring(0, 3))) {
            var tipo_usuario = usuario.getIdTipoUsuario();

            if (tipo_usuario == TipoUsuario.Administrador.id || tipo_usuario == TipoUsuario.Gestor.id) {
                UsuarioDAO.usuarioLogado = usuario;
                MenuFeedBackController.irFeedBack();
            } else {
                UsuarioDAO.usuarioLogado = usuario;
                MenuController.irMenu();
            }
        } else {
            MensagemRetorno.erro("Email e/ou senha incorretos.");
        }
    }
}
