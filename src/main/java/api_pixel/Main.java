package api_pixel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {


    @Override
    public void start(Stage stage) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciamentoCRProjeto.fxml"));
			Scene scene = new Scene(root);
			
			stage.setTitle("Cadastro de Projeto");
			stage.setScene(scene);
			stage.show();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        launch();
    }
}
