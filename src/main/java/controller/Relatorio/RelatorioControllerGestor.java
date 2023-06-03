package controller.Relatorio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import dao.ExtratoHoraDAO;
import dao.UsuarioDAO;
import dto.UsuarioDTO;
import factory.ConnectionFactory;
import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import model.ExtratoHoraModel;
import utils.mensagem_retorno.MensagemRetorno;

public class RelatorioControllerGestor {

	private UsuarioDAO usuarioDAO;

	private ExtratoHoraDAO extratoHoraDAO;

	public RelatorioControllerGestor() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.usuarioDAO = new UsuarioDAO(connection);
		this.extratoHoraDAO = new ExtratoHoraDAO(connection);
	}

	@FXML
	private Button bt_gerar_relatorio;

	@FXML
	private ComboBox<UsuarioDTO> combo_usuario;

	@FXML
	private DatePicker cx_data_fim;

	@FXML
	private DatePicker cx_data_inicio;

	@FXML
	private TextField cx_projeto;

	private ObservableList<UsuarioDTO> usuario = FXCollections.observableArrayList();

	@FXML
	protected void initialize() {
		usuario.addAll(usuarioDAO.getNomeUsuarioAndId());
		combo_usuario.setItems(usuario);
	}

	@FXML
	public void GerarRelatorio(ActionEvent event) {
		UsuarioDTO usuarioDto = (UsuarioDTO) combo_usuario.getSelectionModel().getSelectedItem();

		var dado = extratoHoraDAO.obterRelatorioGerente(cx_data_inicio.getValue(), cx_data_fim.getValue(),
				cx_projeto.getText(), usuarioDto.getId());

		String csvFilePath = "relatorio.csv";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
			// Escreve os cabeçalhos das colunas
			writer.write("Projeto,Modalidade, Hora de Inicio, Hora Final, Motivo ");
			writer.newLine();

			for (ExtratoHoraModel extrat : dado) {
				String projet = extrat.getProjeto();
				String modalidade = extrat.getModalidade();
				LocalDateTime inicio = extrat.getDataHoraInicio();
				LocalDateTime fim = extrat.getDataHoraFim();
				String motivo = extrat.getMotivo();

				writer.write(projet + "," + modalidade + "," + inicio + "," + fim + "," + motivo);
				writer.newLine();

			}

			// gerar combobox

			/*
			 * ResultSet resultSet = extratos.executeQuery();
			 * 
			 * while (resultSet.next()) { String projeto = resultSet.getString("projeto");
			 * String modalidade = resultSet.getString("modalidade"); String horaInicio =
			 * resultSet.getString("hora_inicio"); String horaFinal =
			 * resultSet.getString("hora_final"); String motivo =
			 * resultSet.getString("motivo");
			 * 
			 * writer.write(projeto + "," + modalidade + "," + horaInicio + "," + horaFinal
			 * + "," + motivo); writer.newLine(); }
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

	private void carregarComboBox() {
		// TODO Auto-generated method stub

	}

	@FXML
	void retornarMenu(MouseEvent event) {
		MenuController.irMenu();
	}

}
