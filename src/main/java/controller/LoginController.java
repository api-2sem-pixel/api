<<<<<<< HEAD
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
    private  Button btn_entrar;

	@FXML
    private void entrarUsuario(Stage stage)   {
		
		String email = emailField.getText();
    	String password = passwordField.getText();
		
		if (email.equals("cliente") && password.equals("senha")) {
			Parent root = null;
        
        var resource = getClass()
            .getResource("/view/Menu/Menu.fxml");
        
        try {
            root =  FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Scene scene = new Scene(root, 944, 609);
        stage.setScene(scene);
        
        var stylesPath = getClass().getResource("/view/styles.css").toString();
        stage.getScene().getStylesheets().add(stylesPath);
        
        MenuController.setStage(stage);
        stage.show();
    	}
		else{
			//percorrer o banco com usuarios cadastrados
		}
    	
    	

	}
}
    	
		
    	
  
=======
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
    private  Button btn_entrar;

	@FXML
    private void entrarUsuario(Stage stage)   {
		
		String email = emailField.getText();
    	String password = passwordField.getText();
		
		if (email.equals("cliente") && password.equals("senha")) {
			Parent root = null;
        
        var resource = getClass()
            .getResource("/view/Menu/Menu.fxml");
        
        try {
            root =  FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Scene scene = new Scene(root, 944, 609);
        stage.setScene(scene);
        
        var stylesPath = getClass().getResource("/view/styles.css").toString();
        stage.getScene().getStylesheets().add(stylesPath);
        
        MenuController.setStage(stage);
        stage.show();
    	}
		else{
			//percorrer o banco com usuarios cadastrados
		}
    	
    	

	}
}
    	
		
    	
  
>>>>>>> 7886db5f59396e7084657e96ea315544b50e32f1
