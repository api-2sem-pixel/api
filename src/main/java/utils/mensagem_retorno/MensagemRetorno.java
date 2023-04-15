package utils.mensagem_retorno;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MensagemRetorno {

	
	public MensagemRetorno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void sucesso() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Cadastro efetuado com sucesso");
		alert.show();
	}
	
	public void erro() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText("Não foi possível efetuar o cadastro");
		alert.show();
	}
}
