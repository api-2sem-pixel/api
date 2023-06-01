package controller.Relatorio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import controller.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import model.ExtratoHoraModel;
import utils.mensagem_retorno.MensagemRetorno;

public class RelatorioController {

    @FXML
    public void GerarRelatorio(ActionEvent event) {
        String csvFilePath = "relatorio.csv";

        ArrayList<ExtratoHoraModel> dados = null;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            writer.write("Projeto,Modalidade, Hora de Inicio, Hora Final, Motivo ");
            writer.newLine();

            for (ExtratoHoraModel extrato : dados) {
                String projeto = extrato.getProjeto();
                String modalidade = extrato.getModalidade();
                LocalDateTime inicio = extrato.getDataHoraInicio();
                LocalDateTime fim = extrato.getDataHoraFim();
                String motivo = extrato.getMotivo();

                writer.write(projeto + "," + modalidade + "," + inicio + "," + fim + "," + motivo);
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
