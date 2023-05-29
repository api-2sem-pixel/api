package utils.mensagem_retorno;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MensagemRetorno {

	public MensagemRetorno() {
		super();
	}

	public static void RelatorioGerado() {
		sucesso("Relatório Gerado com Sucesso ");
	}

	public static void sucessoCadastro() {
		sucesso("Cadastro efetuado com sucesso");
	}

	public static void sucesso(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(message);
		alert.show();
	}

	public static void erroCadastro() {
		erro("Não foi possível efetuar o cadastro");
	}

	public static void erro(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.show();
	}
}
