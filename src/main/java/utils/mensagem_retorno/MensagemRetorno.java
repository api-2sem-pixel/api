package utils.mensagem_retorno;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MensagemRetorno {

	
	public MensagemRetorno() {
		super();
	}

	public static void sucesso() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Cadastro efetuado com sucesso");
		alert.show();
	}
	
	public static void erro() {
		erro("Não foi possível efetuar o cadastro");
	}

	public static void erro(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.show();
	}
}
