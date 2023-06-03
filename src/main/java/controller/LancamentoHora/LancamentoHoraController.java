package controller.LancamentoHora;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.MenuController;
import dao.CrDAO;
import dao.ExtratoHoraDAO;
import dao.ModalidadeDAO;
import dao.UsuarioDAO;
import enums.EtapaExtrato;
import enums.TipoUsuario;
import dao.ClienteDAO;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import model.ExtratoHoraModel;
import model.ComboboxModel.ClienteComboboxModel;
import model.ComboboxModel.CrComboboxModel;
import model.ComboboxModel.ModalidadeComboboxModel;
import utils.custom_cell_factories.RowColorFactory;
import utils.custom_cells.DateTimeCell;
import utils.mensagem_retorno.MensagemRetorno;

public class LancamentoHoraController implements Initializable {
    @FXML
    private TableColumn<ExtratoHoraModel, Integer> col_id;
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
    private TableColumn<ExtratoHoraModel, String> col_inicio;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_fim;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_motivo;
    @FXML
    private TableColumn<ExtratoHoraModel, Void> col_acoes;
    @FXML
    private TableView<ExtratoHoraModel> table_lancamento;
    @FXML
    private Button btn_lancar;
    @FXML
    private Button btn_adicionarLinha;

    private List<CrComboboxModel> comboBox_cr = new ArrayList<CrComboboxModel>();
    private List<ModalidadeComboboxModel> comboBox_modalidade = new ArrayList<ModalidadeComboboxModel>();
    private List<ClienteComboboxModel> comboBox_cliente = new ArrayList<ClienteComboboxModel>();

    private CrDAO crDAO;
    private ModalidadeDAO modalidaeDAO;
    private ClienteDAO clienteDAO;
    private ExtratoHoraDAO extratoHoraDao;

    public LancamentoHoraController() {
        super();
        Connection connection = new ConnectionFactory().recuperarConexao();
        crDAO = new CrDAO(connection);
        modalidaeDAO = new ModalidadeDAO(connection);
        clienteDAO = new ClienteDAO(connection);
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
                ""
        };

        carregarComboBox();
        configurarLinha(propertyNames);

        carregarExtratos();
    }

    private void carregarExtratos() {
        var extratos = extratoHoraDao.obterExtratosLancados(UsuarioDAO.usuarioLogado.getId(), null);
        table_lancamento.getItems().addAll(extratos);
    }

    private void carregarComboBox() {
        this.comboBox_cr = crDAO.obterCombobox();
        this.comboBox_modalidade = modalidaeDAO.obterCombobox();
        this.comboBox_cliente = clienteDAO.obterCombobox();
    }

    private void configurarLinha(final String[] propertyNames) {
        int index = 0;

        col_id.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, Integer>(propertyNames[index++]));
        col_projeto.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_cr.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_cliente.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_modalidade.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_inicio.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_fim.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_motivo.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_justificativa
                .setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_acoes.setCellValueFactory(new PropertyValueFactory<>(propertyNames[index++]));

        col_projeto.setCellFactory(TextFieldTableCell.forTableColumn());
        col_projeto.setOnEditCommit(event -> {
            var row = event.getTablePosition().getRow();
            var model = event.getTableView().getItems().get(row);
            model.setProjeto(event.getNewValue());
        });

        col_justificativa.setCellFactory(TextFieldTableCell.forTableColumn());
        col_justificativa.setOnEditCommit(event -> {
            var row = event.getTablePosition().getRow();
            var model = event.getTableView().getItems().get(row);
            model.setJustificativa(event.getNewValue());
        });

        col_cr.setCellFactory(
                ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), FXCollections.observableArrayList(
                        FXCollections.observableArrayList(comboBox_cr.stream().map(x -> x.getNome()).toList()))));
        col_cr.setOnEditCommit(event -> {
            var row = event.getTablePosition().getRow();
            var model = event.getTableView().getItems().get(row);

            var cr = comboBox_cr.stream()
                    .filter(x -> x.getNome().equals(event.getNewValue()))
                    .findFirst();

            if (cr.isEmpty())
                return;

            model.setIdCr(cr.get().getId());
            model.setCr(cr.get().getNome());
        });

        col_cliente.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),
                FXCollections.observableArrayList(comboBox_cliente.stream().map(x -> x.getRazaoSocial()).toList())));
        col_cliente.setOnEditCommit(event -> {
            var row = event.getTablePosition().getRow();
            var model = event.getTableView().getItems().get(row);

            var cliente = comboBox_cliente.stream()
                    .filter(x -> x.getRazaoSocial().equals(event.getNewValue()))
                    .findFirst();
            if (cliente.isEmpty())
                return;

            model.setCliente(cliente.get().getRazaoSocial());
            model.setIdCliente(cliente.get().getId());
        });

        col_modalidade.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),
                FXCollections.observableArrayList(comboBox_modalidade.stream().map(x -> x.getDescricao()).toList())));
        col_modalidade.setOnEditCommit(event -> {
            var row = event.getTablePosition().getRow();
            var model = event.getTableView().getItems().get(row);

            var modalidade = comboBox_modalidade.stream()
                    .filter(x -> x.getDescricao().equals(event.getNewValue()))
                    .findFirst();
            if (modalidade.isEmpty())
                return;

            model.setModalidade(modalidade.get().getDescricao());
            model.setIdModalidade(modalidade.get().getId());
        });

        col_inicio.setCellFactory(TextFieldTableCell.forTableColumn());
        col_inicio.setOnEditCommit(event -> {
            var row = event.getTablePosition().getRow();
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
            var valor = LocalDateTime.parse(event.getNewValue(), formatter);
            event.getTableView().getItems().get(row).setDataHoraInicio(valor);
        });

        col_fim.setCellFactory(TextFieldTableCell.forTableColumn());
        col_fim.setOnEditCommit(event -> {
            var row = event.getTablePosition().getRow();
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
            var valor = LocalDateTime.parse(event.getNewValue(), formatter);
            event.getTableView().getItems().get(row).setDataHoraFim(valor);
        });
        var buttonDeletar = new Callback<TableColumn<ExtratoHoraModel, Void>, TableCell<ExtratoHoraModel, Void>>() {
            @Override
            public TableCell<ExtratoHoraModel, Void> call(final TableColumn<ExtratoHoraModel, Void> param) {
                final TableCell<ExtratoHoraModel, Void> cell = new TableCell<ExtratoHoraModel, Void>() {

                    private final Button btn = new Button("Excluir");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            var row = getTableView().getItems().get(getIndex());
                            if (row.getStatus() == EtapaExtrato.CRIACAO) {
                                getTableView().getItems().remove(getIndex());
                                return;
                            }

                            MensagemRetorno.erro("Apenas linhas não lançadas podem ser excluídas");
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
        col_acoes.setCellFactory(buttonDeletar);

        RowColorFactory.criarCoresStatus(table_lancamento);
    }

    @FXML
    public void criarNovaLinha(ActionEvent event) {
        table_lancamento.getItems().add(ExtratoHoraModel.criarLinhaPadrao());
    }

    @FXML
    void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }

    @FXML
    public void lancarHoras(ActionEvent event) {
        var rows = table_lancamento.getItems();

        for (ExtratoHoraModel extratoHoraModel : rows) {
            if (extratoHoraModel.getId() != 0) {
                continue;
            }
            extratoHoraModel.setIdUsuario(UsuarioDAO.usuarioLogado.getId());

            if (UsuarioDAO.usuarioLogado.getIdTipoUsuario() == TipoUsuario.Administrador || UsuarioDAO.usuarioLogado.getIdTipoUsuario() == TipoUsuario.Gestor)
                extratoHoraModel.setStatus(EtapaExtrato.APROVADA);
            else
                extratoHoraModel.setStatus(EtapaExtrato.EM_APROVACAO);

            var rowsModified = extratoHoraDao.lancarHora(extratoHoraModel);
            if (rowsModified <= 0) {
                MensagemRetorno.erroCadastro();
                return;
            }
        }

        MensagemRetorno.sucessoCadastro();
    }
}
