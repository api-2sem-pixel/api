package controller;

import java.sql.Connection;
import java.util.List;

import dao.CrDAO;
import dao.CrUsuarioDAO;
import dto.CrDTO;
import dto.IntegrantesCrDTO;
import enums.EtapaExtrato;
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
import model.ExtratoHoraModel;
import utils.mensagem_retorno.MensagemRetorno;

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
		
		//adicionarBotaoDeletar();
	}
	
	public void buscar(ActionEvent event) {
		adicionarBotaoDeletar();
		CrDTO cr = (CrDTO) comboCR.getSelectionModel().getSelectedItem();
		List<IntegrantesCrDTO> integrantes = crUsuarioDAO.listarIntegrantes(cr.getId());
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
                            getTableView().getItems().remove(getIndex());
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
}
