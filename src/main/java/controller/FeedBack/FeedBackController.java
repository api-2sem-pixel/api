package controller.FeedBack;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import controller.MenuController;
import dao.ExtratoHoraDAO;
import dao.UsuarioDAO;
import enums.EtapaExtrato;
import enums.TipoUsuario;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.ExtratoHoraModel;
import utils.feedback_retorno.FeedBackRetorno;

public class FeedBackController implements Initializable {
    @FXML
    private TableColumn<ExtratoHoraModel, Integer> col_id;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_solicitante;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_projeto;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_cr;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_cliente;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_justificativa;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_modalidade;
    @FXML
    private TableColumn<ExtratoHoraModel, LocalDateTime> col_inicio;
    @FXML
    private TableColumn<ExtratoHoraModel, LocalDateTime> col_fim;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_motivo;
    @FXML
    private TableColumn<ExtratoHoraModel, Void> col_acoes;
    @FXML
    private TableView<ExtratoHoraModel> table_lancamento;
    @FXML
    private TextField tfFiltro;
    @FXML
    private Button bnt_filtro;

    private ExtratoHoraDAO extratoHoraDao;

    public FeedBackController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        extratoHoraDao = new ExtratoHoraDAO(connection);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final var propertyNames = new String[] {
                "id",
                "projeto",
                "cr",
                "cliente",
                "modalidade",
                "dataHoraInicioS",
                "dataHoraFimS",
                "motivo",
                "justificativa",
                "solicitante",
                ""
        };

        configurarLinha(propertyNames);

        carregarExtratos();
    }

    private void carregarExtratos() {
        var projeto = tfFiltro.getText();
        var extratos = extratoHoraDao.obterExtratosParaAprovar(UsuarioDAO.usuarioLogado.getId(), projeto);
        table_lancamento.getItems().clear();
        table_lancamento.setItems(FXCollections.observableArrayList(extratos));
    }

    private void configurarLinha(final String[] propertyNames) {
        int index = 0;

        col_id.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, Integer>(propertyNames[index++]));
        col_projeto.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_cr.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_cliente.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_modalidade.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_inicio.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, LocalDateTime>(propertyNames[index++]));
        col_fim.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, LocalDateTime>(propertyNames[index++]));
        col_motivo.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_justificativa.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_solicitante.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_acoes.setCellValueFactory(new PropertyValueFactory<>(propertyNames[index++]));

        var cellFactory = new Callback<TableColumn<ExtratoHoraModel, Void>, TableCell<ExtratoHoraModel, Void>>() {
            @Override
            public TableCell<ExtratoHoraModel, Void> call(TableColumn<ExtratoHoraModel, Void> param) {
                return new TableCell<ExtratoHoraModel, Void>() {
                    private final Button btnAprovar = new Button("Aprovar");
                    private final Button btnReprovar = new Button("Reprovar");
                    private final boolean show = false;

                    {
                        btnAprovar.setOnAction((ActionEvent event) -> {
                            ExtratoHoraModel extratoHora = getTableView().getItems().get(getIndex());
                            extratoHora.setStatus(EtapaExtrato.APROVADA);

                            if (EtapaExtrato.APROVADA == extratoHora.getStatus()) {
                                extratoHoraDao.aprovarHoraExtra(extratoHora);
                                carregarExtratos();
                            }
                        });

                    }
                    {
                        btnReprovar.setOnAction((final ActionEvent event) -> {
                            final ExtratoHoraModel extratoHora = getTableView().getItems().get(getIndex());
                            extratoHora.setStatus(EtapaExtrato.REPROVADA);

                            if (EtapaExtrato.REPROVADA == extratoHora.getStatus()) {
                                extratoHoraDao.reprovarHoraExtra(extratoHora);
                                FeedBackRetorno.motivo(extratoHoraDao, extratoHora);
                                carregarExtratos();

                            }
                        });

                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            var r = getTableView().getItems().get(getIndex());
                            if(r.getStatus() == EtapaExtrato.EM_APROVACAO || r.getStatus() == EtapaExtrato.PENDENTE_CORRECAO){
                                HBox container = new HBox(btnAprovar, btnReprovar);
                                setGraphic(container);
                            }

                        }
                    }

                };
            }
        };
        col_acoes.setCellFactory(cellFactory);
    }

    @FXML
    private void fltrarProjeto() {
        carregarExtratos();
    }

    @FXML
    void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }

    @FXML
    void retornar(MouseEvent event) {
        if (UsuarioDAO.usuarioLogado.getIdTipoUsuario() == TipoUsuario.Colaborador) {
            MenuController.irMenuGestor();
        }
        MenuController.irMenuAdmin();
    }
}
