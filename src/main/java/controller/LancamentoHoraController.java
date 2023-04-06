package controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.ExtratoHoraModel;

public class LancamentoHoraController implements Initializable {
    @FXML private TableColumn<ExtratoHoraModel, Integer> col_id;
    @FXML private TableColumn<ExtratoHoraModel, String> col_projeto;
    @FXML private TableColumn<ExtratoHoraModel, String> col_cr;
    @FXML private TableColumn<ExtratoHoraModel, String> col_modalidade;
    @FXML private TableColumn<ExtratoHoraModel, LocalDateTime> col_inicio;
    @FXML private TableColumn<ExtratoHoraModel, LocalDateTime> col_fim;
    @FXML private TableColumn<ExtratoHoraModel, String> col_motivo;
    @FXML private TableColumn<ExtratoHoraModel, ?> col_acoes;
    @FXML private TableView<ExtratoHoraModel> table_lancamento;
    @FXML private Button btn_lancar;

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

        configurarLinha(propertyNames); 

        //carregarComboBox();

        var extratos = obterExtratoHora();
        popularTabela(extratos);
    }

    private void configurarLinha(final String[] propertyNames) {
        int index = 0;
        col_id.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, Integer>(propertyNames[index++]));
        col_projeto.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_cr.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_modalidade.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_inicio.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, LocalDateTime>(propertyNames[index++]));
        col_fim.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, LocalDateTime>(propertyNames[index++])); 
        col_motivo.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(propertyNames[index++]));
        col_acoes.setCellValueFactory(new PropertyValueFactory<>(propertyNames[index++]));
    }

    private ArrayList<?> obterExtratoHora(){
        return null;
    }

    private void popularTabela(ArrayList<?> extratos) {
    }

    @FXML
    public void criarNovaLinha(MouseEvent event) {
        table_lancamento.getItems().add(new ExtratoHoraModel());
    }

    @FXML
    public void lancarHoras(ActionEvent event) {
       
    }
}
