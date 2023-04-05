package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LancamentoHoraController {

    @FXML
    private Button btn_lancar;

    @FXML
    private TableColumn<?, ?> col_acoes;

    @FXML
    private TableColumn<?, ?> col_cr;

    @FXML
    private TableColumn<?, ?> col_fim;

    @FXML
    private TableColumn<?, ?> col_inicio;

    @FXML
    private TableColumn<?, ?> col_modalidade;

    @FXML
    private TableColumn<?, ?> col_motivo;

    @FXML
    private TableColumn<?, ?> col_projeto;

    @FXML
    private TableView<?> table_lancamento;

    @FXML
    void lancarHoras(ActionEvent event) {
        
    }
}
