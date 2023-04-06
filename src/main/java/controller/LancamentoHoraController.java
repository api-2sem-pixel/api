package controller;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    void criarNovaCelula(MouseEvent event) {
       /*  col_projeto.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(""));
        col_cr.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(""));
        col_modalidade.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(""));
        col_inicio.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, LocalDateTime>(null));
        col_fim.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, LocalDateTime>(null));
        col_motivo.setCellValueFactory(new PropertyValueFactory<ExtratoHoraModel, String>(""));
        col_acoes.setCellValueFactory(new PropertyValueFactory<>("")); */

        table_lancamento.setItems(FXCollections.observableArrayList(
            new ExtratoHoraModel(),
            new ExtratoHoraModel(),
            new ExtratoHoraModel(),
            new ExtratoHoraModel()
        ));
    }

    @FXML
    void lancarHoras(ActionEvent event) {
        var t = col_projeto.getCellData(0);
        
        System.out.println("");
    }
}
