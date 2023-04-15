package controller.LancamentoHora;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.CrDAO;
import dao.ExtratoHoraDAO;
import dao.ModalidadeDAO;
import dao.MotivoDAO;
import dao.ProjetoDAO;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.DefaultStringConverter;
import model.ExtratoHoraModel;
import model.ComboboxModel.CrComboboxModel;
import model.ComboboxModel.ModalidadeComboboxModel;
import model.ComboboxModel.MotivoComboboxModel;
import model.ComboboxModel.ProjetoComboboxModel;
import utils.custom_cells.DateTimeCell;

public class LancamentoHoraController implements Initializable {
    @FXML
    private TableColumn<ExtratoHoraModel, Integer> col_id;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_projeto;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_cr;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_modalidade;
    @FXML
    private TableColumn<ExtratoHoraModel, LocalDateTime> col_inicio;
    @FXML
    private TableColumn<ExtratoHoraModel, LocalDateTime> col_fim;
    @FXML
    private TableColumn<ExtratoHoraModel, String> col_motivo;
    @FXML
    private TableColumn<ExtratoHoraModel, ?> col_acoes;
    @FXML
    private TableView<ExtratoHoraModel> table_lancamento;
    @FXML
    private Button btn_lancar;
    @FXML
    private Button btn_adicionarLinha;

    private List<ProjetoComboboxModel> comboBox_projeto = new ArrayList<ProjetoComboboxModel>();
    private List<CrComboboxModel> comboBox_cr = new ArrayList<CrComboboxModel>();
    private List<ModalidadeComboboxModel> comboBox_modalidade = new ArrayList<ModalidadeComboboxModel>();
    private List<MotivoComboboxModel> comboBox_motivo = new ArrayList<MotivoComboboxModel>();

    private CrDAO crDAO;
    private ModalidadeDAO modalidaeDAO;
    private MotivoDAO motivoDAO;
    private ProjetoDAO projetoDAO;
    private ExtratoHoraDAO extratoHoraDao;

    public LancamentoHoraController() {
        super();
        Connection connection = new ConnectionFactory().recuperarConexao();

        crDAO = new CrDAO(connection);
        modalidaeDAO = new ModalidadeDAO(connection);
        motivoDAO = new MotivoDAO(connection);
        projetoDAO = new ProjetoDAO(connection);
        extratoHoraDao = new ExtratoHoraDAO(connection);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final var propertyNames = new String[] {
                "id",
                "projeto",
                "cr",
                "modalidade",
                "dataHoraInicio",
                "dataHoraFim",
                "motivo",
                ""
        };

        carregarComboBox();
        configurarLinha(propertyNames);

        var extratos = obterExtratoHora();
        table_lancamento.getItems().addAll(extratos);
    }

    private void carregarComboBox() {

        this.comboBox_cr = crDAO.obterCombobox();
        this.comboBox_modalidade = modalidaeDAO.obterCombobox();
        this.comboBox_motivo = motivoDAO.obterCombobox();
        this.comboBox_projeto = projetoDAO.obterCombobox();
    }

    private void configurarLinha(final String[] propertyNames) {
        int index = 0;
        col_id.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, Integer>(propertyNames[index++]));
        col_projeto.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_cr.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_modalidade.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_inicio
                .setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, LocalDateTime>(propertyNames[index++]));
        col_fim.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, LocalDateTime>(propertyNames[index++]));
        col_motivo.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_acoes.setCellValueFactory(new PropertyValueFactory<>(propertyNames[index++]));

        col_projeto.setCellFactory(
                ComboBoxTableCell.forTableColumn(
                        new DefaultStringConverter(),
                        FXCollections.observableArrayList(comboBox_projeto.stream().map(x -> x.getNome()).toList())));

        col_cr.setCellFactory(
                ComboBoxTableCell.forTableColumn(
                        new DefaultStringConverter(),
                        FXCollections.observableArrayList(FXCollections
                                .observableArrayList(comboBox_cr.stream().map(x -> x.getNome()).toList()))));

        col_modalidade.setCellFactory(
                ComboBoxTableCell.forTableColumn(
                        new DefaultStringConverter(),
                        FXCollections.observableArrayList(
                                comboBox_modalidade.stream().map(x -> x.getDescricao()).toList())));

        /*
         * col_inicio.setCellFactory(col -> new DateTimeCell<ExtratoHoraModel>());
         * col_fim.setCellFactory(col -> new DateTimeCell<ExtratoHoraModel>());
         */

        col_motivo.setCellFactory(
                ComboBoxTableCell.forTableColumn(
                        new DefaultStringConverter(),
                        FXCollections
                                .observableArrayList(comboBox_motivo.stream().map(x -> x.getDescricao()).toList())));
    }

    private ArrayList<ExtratoHoraModel> obterExtratoHora() {
        var extratos = new ArrayList<ExtratoHoraModel>();

        var extrato = new ExtratoHoraModel();
        extrato.setCr("Teste");
        extrato.setDataHoraInicio(LocalDateTime.now());
        extrato.setDataHoraFim(LocalDateTime.now());
        extrato.setJustificativa("Teste");
        extrato.setModalidade("Teste");
        extrato.setMotivo("Teste");
        extrato.setProjeto("Teste");

        extratos.add(extrato);
        return extratos;
    }

    @FXML
    public void criarNovaLinha(ActionEvent event) {
        table_lancamento.getItems().add(ExtratoHoraModel.criarLinhaPadrao());
    }

    @FXML
    public void lancarHoras(ActionEvent event) {
        var rows = table_lancamento.getItems();

        for (ExtratoHoraModel extratoHoraModel : rows) {
            if (extratoHoraModel.getId() != 0) {
                // se precisar dar update
                // update()
                continue;
            }

            extratoHoraDao.lancarHora(extratoHoraModel);
        }
    }
}
