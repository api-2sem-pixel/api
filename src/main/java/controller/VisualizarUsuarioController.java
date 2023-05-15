package controller;

import java.sql.Connection;
import java.util.List;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import enums.TipoUsuario;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import utils.ChangeScene;

public class VisualizarUsuarioController {
	private UsuarioDAO usuarioDAO;

	public VisualizarUsuarioController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.usuarioDAO = new UsuarioDAO(connection);
	}
	
	@FXML ComboBox comboUsuario;
	@FXML private TableView<UsuarioDTO> tabelaUsuarios;
	@FXML private TableColumn<UsuarioDTO, String> colNome;
	@FXML private TableColumn<UsuarioDTO, String> colCPFCNPJ;
	@FXML private TableColumn<UsuarioDTO, String> colEmail;
	@FXML private TableColumn<UsuarioDTO, TipoUsuario> colTipo;
	@FXML private TableColumn<UsuarioDTO, Void> colAcoes;
		
	private ObservableList<UsuarioDTO> usuario = FXCollections.observableArrayList();
	
	@FXML
	protected void initialize() {
		carregarCombobox();
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colCPFCNPJ.setCellValueFactory(new PropertyValueFactory<>("cpf_cnpj"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colTipo.setCellValueFactory(new PropertyValueFactory<>("idTipoUsuario"));
		colAcoes.setCellValueFactory(new PropertyValueFactory<>(""));
		buscarUsuario(null);
	}
	
	public void carregarCombobox() {
		usuario.addAll(usuarioDAO.getNomeUsuarioAndId());
		comboUsuario.setItems(usuario);
	}
	
	public void buscarUsuario(ActionEvent event) {
		adicionarBotaoDeletar();
		UsuarioDTO usuario = (UsuarioDTO) comboUsuario.getSelectionModel().getSelectedItem();
		List<UsuarioDTO> usuarios = null;

		try {
			usuarios = UsuarioDAO.listarUsuarios(usuario != null ? usuario.getId() : null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tabelaUsuarios.setItems(listarUsuario(usuarios));

	}
		
	public void irCadastroUsuario(ActionEvent event) {
		ChangeScene cs = new ChangeScene();
		cs.irCadastroUsuario();
	}
	
	private ObservableList<UsuarioDTO> listarUsuario(List<UsuarioDTO> usuarios){
		return FXCollections.observableArrayList(usuarios);
	}
	
    @FXML
    void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }
    
    public void adicionarBotaoDeletar() {
        var buttonDeletar = new Callback<TableColumn<UsuarioDTO, Void>, TableCell<UsuarioDTO, Void>>() {
            @Override
            public TableCell<UsuarioDTO, Void> call(final TableColumn<UsuarioDTO, Void> param) {
                final TableCell<UsuarioDTO, Void> cell = new TableCell<UsuarioDTO, Void>() {

                    private final Button btn = new Button("Excluir");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            var row = getTableView().getItems().get(getIndex());
                            getTableView().getItems().remove(getIndex());
                            usuarioDAO.deletar(row.getId());          
                            return;
                         });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colAcoes.setCellFactory(buttonDeletar);
    }
}