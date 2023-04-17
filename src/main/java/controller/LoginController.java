package controller;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {

    
	@FXML
	private TextField emailField;
	
	@FXML
	private PasswordField passwordField;
	
	
    @FXML
    private static Button btn_entrar;

	@FXML
    private void entrarUsuario(Stage stage)   {
		
		String email = emailField.getText();
    	String password = passwordField.getText();
		String logado =  btn_entrar.getId();
		System.out.println(logado);
    	if (email.equals("cliente") && password.equals("senha")) {
    		try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Menu/Menu.fxml"));
                if(logado == "btn_entrar"){
					Parent home = loader.load();
				    Scene scene = new Scene(home, 944, 609);
                    stage.setScene(scene);
			     	stage.show();
			  
				}
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}
		else{
			//percorrer o banco com usuarios cadastrados
		}
    	
    	

	}
    }
    	
		
    	
  


