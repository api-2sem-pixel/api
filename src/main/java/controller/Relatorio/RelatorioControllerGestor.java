package controller.Relatorio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import dao.ExtratoHoraDAO;
import controller.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import utils.mensagem_retorno.MensagemRetorno;

public class RelatorioControllerGestor {


	  @FXML
	    public void GerarRelatorio(ActionEvent event) {
		  
		  ExtratoHoraDAO extrato = new ExtratoHoraDAO(null);
		  
		  extrato.obterRelatorioGerente(null, null, null, 0);
		  

	        String csvFilePath = "relatorio.csv";
	        

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
	            // Escreve os cabeçalhos das colunas
	            writer.write("Projeto,Modalidade, Hora de Inicio, Hora Final, Motivo ");
	            writer.newLine();
	            

	            /*
	             * ResultSet resultSet = extratos.executeQuery();
	             * 
	             * while (resultSet.next()) {
	             * String projeto = resultSet.getString("projeto");
	             * String modalidade = resultSet.getString("modalidade");
	             * String horaInicio = resultSet.getString("hora_inicio");
	             * String horaFinal = resultSet.getString("hora_final");
	             * String motivo = resultSet.getString("motivo");
	             * 
	             * writer.write(projeto + "," + modalidade + "," + horaInicio + "," + horaFinal
	             * + "," + motivo);
	             * writer.newLine();
	             * }
	             */

	            // Escreve os dados dos registros
	            writer.write("");
	            writer.newLine();
	            writer.write("");
	            writer.newLine();
	            writer.write("");
	            writer.newLine();

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

