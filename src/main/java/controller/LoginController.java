package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {

    
	@FXML
	private TextField emailField;
	
	@FXML
	private PasswordField passwordField;
	
	
    @FXML
    private Button btn_entrar;

	@FXML
    private void entrarUsuario()   {
		
		String email = emailField.getText();
    	String password = passwordField.getText();
    	if (email.equals("admin") && password.equals("123")) {
    		System.out.println(email);
    	}
    	
    	

	}
    }
    	
		
    	
  


