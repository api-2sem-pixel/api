package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.ExtratoHoraModel;

public class LancamentoHoraController {

    @FXML
    private Button btn_lancar;

    @FXML
    private TableColumn<ExtratoHoraModel, ?> col_acoes;

    @FXML
    private TableColumn<ExtratoHoraModel, String> col_cr;

    @FXML
    private TableColumn<ExtratoHoraModel, LocalDateTime> col_fim;

    @FXML
    private TableColumn<ExtratoHoraModel, LocalDateTime> col_inicio;

    @FXML
    private TableColumn<ExtratoHoraModel, String> col_modalidade;

    @FXML
    private TableColumn<ExtratoHoraModel, String> col_motivo;

    @FXML
    private TableColumn<ExtratoHoraModel, String> col_projeto;

    @FXML
    private TableView<ExtratoHoraModel> table_lancamento;

    @FXML
    void lancarHoras(ActionEvent event) {
        
    }
}
