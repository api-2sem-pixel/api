package controller.Relatorio;

import java.awt.Button;
import java.awt.TextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;

import dao.ExtratoHoraDAO;
import factory.ConnectionFactory;
import controller.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import utils.mensagem_retorno.MensagemRetorno;

public class RelatorioControllerGestor {

	@FXML
	private Button bt_gerar_relatorio;

	@FXML
	private ComboBox<String> combo_usuario;

	@FXML
	private DatePicker cx_data_fim;

	@FXML
	private DatePicker cx_data_inicio;

	@FXML
	private TextField cx_projeto;

	@FXML
	public void GerarRelatorio(ActionEvent event) {

		Connection connection = new ConnectionFactory().recuperarConexao();

		ExtratoHoraDAO extrato = new ExtratoHoraDAO(connection);

		extrato.obterRelatorioGerente(cx_data_inicio.getValue(), cx_data_fim.getValue(), cx_projeto.getText(),
				combo_usuario.getValue());

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
