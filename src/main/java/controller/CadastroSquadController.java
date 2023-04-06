package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroSquadController {

	@FXML
	private TextField tfTitle;
	
	@FXML
	public void btnOKClicked(ActionEvent eVent) {
		Stage mainWindow = (Stage) tfTitle.getScene().getWindow();
		String title = tfTitle.getText();
		mainWindow.setTitle(title);
	}
	
}
