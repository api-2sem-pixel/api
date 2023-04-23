package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.CadastroUsuario;

import java.sql.Connection;

import dao.UsuarioDAO;
import factory.ConnectionFactory;

public class CadastroUsuarioController {

	private UsuarioDAO usuarioDAO;
	
    @FXML
    private TextField tfemailField;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfcpfField;

    @FXML
    private TextField tftelField;
       
    @FXML
    private Button btnVoltar;//botão voltar
    
    @FXML
    private Button tfcadastrarButton;

    public CadastroUsuarioController() {
    	Connection connection = new ConnectionFactory().recuperarConexao();
		this.usuarioDAO = new UsuarioDAO(connection);
    }

    @FXML
    public void cadastrarUsuario(ActionEvent event) {
        String email = tfemailField.getText();
        String nome = tfNome.getText();
        String cpf = tfcpfField.getText();
        String tel = tftelField.getText();

        CadastroUsuario usuario = new CadastroUsuario(email, nome, cpf, tel, 1);

        if (usuario.isValid()) {
          this.usuarioDAO.cadastrar(usuario);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Usuário cadastrado com sucesso!");
            alert.showAndWait();
            limparCampos();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, preencha todos os campos corretamente.");
            alert.showAndWait();
        }
    }
    @FXML
    public void voltar(ActionEvent event) {
        MenuController.irMenu();
    	// Código para voltar
    	}

    private void limparCampos() {
        tfemailField.setText("");
        tfNome.setText("");
        tfcpfField.setText("");
        tftelField.setText("");
    }

}