package controller;

import java.sql.Connection;
import java.util.List;

import dao.CrDAO;
import dao.CrUsuarioDAO;
import dto.CrDTO;
import dto.IntegrantesCrDTO;
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

public class VisualizarCRController {

	private CrDAO crDAO;
	private CrUsuarioDAO crUsuarioDAO;

	public VisualizarCRController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.crDAO = new CrDAO(connection);
		this.crUsuarioDAO = new CrUsuarioDAO(connection);
	}

	@FXML
	private ComboBox comboCR;
	
	@FXML
	private TableView<IntegrantesCrDTO> tabela;
	
	@FXML
	private TableColumn<IntegrantesCrDTO, String> integrantesCol;
	
	@FXML
	private TableColumn<IntegrantesCrDTO, String> descricaoCol;
	
	@FXML
	private TableColumn<IntegrantesCrDTO, Void> acoesCol;
	
	private ObservableList<CrDTO> cr = FXCollections.observableArrayList();
	
	@FXML
	protected void initialize() {
		cr.addAll(crDAO.getIdGestorAndNomeCr());
		comboCR.setItems(cr);
		
		integrantesCol.setCellValueFactory(new PropertyValueFactory<>("integrante"));
		descricaoCol.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		acoesCol.setCellValueFactory(new PropertyValueFactory<>(""));
	}
	
	public void buscar(ActionEvent event) {
		adicionarBotaoDeletar();
		listar();
	}
	
	public void listar() {
		CrDTO cr = (CrDTO) comboCR.getSelectionModel().getSelectedItem();
		List<IntegrantesCrDTO> integrantes = crUsuarioDAO.listarIntegrantes(cr != null ? cr.getId() : null);
		tabela.setItems(listaIntegrantes(integrantes));
	}
	
	private ObservableList<IntegrantesCrDTO> listaIntegrantes(List<IntegrantesCrDTO> integrantes){
		return FXCollections.observableArrayList(integrantes);
	}
	
    @FXML
    void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }
    
    public void adicionarBotaoDeletar() {
        var buttonDeletar = new Callback<TableColumn<IntegrantesCrDTO, Void>, TableCell<IntegrantesCrDTO, Void>>() {
            @Override
            public TableCell<IntegrantesCrDTO, Void> call(final TableColumn<IntegrantesCrDTO, Void> param) {
                final TableCell<IntegrantesCrDTO, Void> cell = new TableCell<IntegrantesCrDTO, Void>() {

                    private final Button btn = new Button("Excluir");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            var row = getTableView().getItems().get(getIndex());
                            crUsuarioDAO.deletar(row.getIdUsuario(), row.getIdCr());
                            getTableView().getItems().remove(getIndex());
                            listar();
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
        acoesCol.setCellFactory(buttonDeletar);

    }
    
    public void irCadastro(ActionEvent event) {
		ChangeScene cs = new ChangeScene();
		cs.irCadastroCR();
	}
    
    public void irAtribuicao(ActionEvent event) {
		ChangeScene cs = new ChangeScene();
		cs.irAtribuicaoCR();
	}
}
