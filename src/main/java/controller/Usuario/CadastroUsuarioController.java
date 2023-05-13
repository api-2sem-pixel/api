package controller.Usuario;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import controller.MenuController;
import dao.TipoUsuarioDAO;
import dao.UsuarioDAO;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.CadastroUsuario;
import model.ComboboxModel.TipoUsuarioComboboxModel;
import utils.ChangeScene;
import utils.mensagem_retorno.MensagemRetorno;

public class CadastroUsuarioController implements Initializable{

    @FXML private Button btn_cadastrar;
    @FXML private ComboBox<String> combo_Tipo;
    @FXML private TextField txt_Cpf;
    @FXML private TextField txt_Email;
    @FXML private TextField txt_Nome;
    @FXML private TextField txt_Tel;

    private UsuarioDAO usuarioDAO;
    private TipoUsuarioDAO tipoUsuarioDAO;
    private List<TipoUsuarioComboboxModel> tipoUsuarios;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection connection = new ConnectionFactory().recuperarConexao();
		this.usuarioDAO = new UsuarioDAO(connection);
        this.tipoUsuarioDAO = new TipoUsuarioDAO(connection);

        tipoUsuarios = this.tipoUsuarioDAO.obterCombobox();
        combo_Tipo.setItems(FXCollections.observableArrayList(tipoUsuarios.stream().map(x -> x.getDescricao()).toList()));
    }

    @FXML
    void cadastrarUsuario(ActionEvent event) {
        String email = txt_Email.getText();
        String nome = txt_Nome.getText();
        String cpf = txt_Cpf.getText();
        String tel = txt_Tel.getText();
        var tipo = tipoUsuarios.stream().filter(x -> x.getDescricao().trim() == combo_Tipo.getSelectionModel().getSelectedItem().trim()).findFirst();
        
        if(tipo.isEmpty()){
            MensagemRetorno.erro("Por favor, preencha todos os campos corretamente.");
            return;
        }

        CadastroUsuario usuario = new CadastroUsuario(email, nome, cpf, tel, tipo.get().getId());
        
        if (usuario.isValid()) {
            this.usuarioDAO.cadastrar(usuario);
            MensagemRetorno.sucesso("Usuário cadastrado com sucesso!");
            limparCampos(); 
        } else {
            MensagemRetorno.erro("Por favor, preencha todos os campos corretamente.");
        }
    }
    
    public void irVisualizacaoUsuario(ActionEvent event) {
		ChangeScene cs = new ChangeScene();
		cs.irVisualizacaoUsuario();
	}

    @FXML
    void voltar(MouseEvent event) {
        MenuController.irMenu();
    }

    private void limparCampos() {
        txt_Email.setText("");
        txt_Nome.setText("");
        txt_Cpf.setText("");
        txt_Tel.setText("");
    }
}