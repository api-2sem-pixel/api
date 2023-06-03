package controller.Relatorio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import controller.MenuController;
import dao.ExtratoHoraDAO;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.ExtratoHoraModel;
import utils.mensagem_retorno.MensagemRetorno;

public class RelatorioController {

    @FXML
    private ComboBox<?> tuUsuario;

    @FXML
    private TextField tpProjeto;

    @FXML
    private DatePicker dataInicio;

    @FXML
    private DatePicker dataFim;

    @FXML
    public void GerarRelatorio(ActionEvent event) {
        String csvFilePath = "relatorio.csv";
        Connection connection = new ConnectionFactory().recuperarConexao();
        ExtratoHoraDAO extrato = new ExtratoHoraDAO(connection);

        var projeto = tpProjeto.getText();
        LocalDate datai = dataInicio.getValue();
        LocalDate dataF = dataFim.getValue();
        ArrayList<ExtratoHoraModel> dados = extrato.obterRelatorioGerente(datai, dataF, projeto, 1);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            writer.write("Projeto,Modalidade, Hora de Inicio, Hora Final, Motivo ");
            writer.newLine();

            for (ExtratoHoraModel extrat : dados) {
                String projet = extrat.getProjeto();
                String modalidade = extrat.getModalidade();
                LocalDateTime inicio = extrat.getDataHoraInicio();
                LocalDateTime fim = extrat.getDataHoraFim();
                String motivo = extrat.getMotivo();

                writer.write(projet + "," + modalidade + "," + inicio + "," + fim + "," + motivo);
                writer.newLine();
            }

            MensagemRetorno.RelatorioGerado();

            System.out.println("Arquivo CSV gerado com sucesso.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }

}
