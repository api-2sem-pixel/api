package api_pixel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Router extends Application {
    private Stage stage;
	@Override
    public void start(Stage stage) throws IOException {
    	this.stage=stage;
    	showCadastroUsuarioScreen(stage);
    	showLancamentoHoraScreen(stage);
    }
    public void showCadastroUsuarioScreen(Stage stage) throws IOException {
    
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastroUsuario.fxml"));
    	Parent root = loader.load();

        Scene scene = new Scene(root, 944, 609);
        stage.setScene(scene);
        

        var stylesPath = getClass().getResource("/view/stylesCadastroUsuario.css").toString();
        stage.getScene().getStylesheets().add(stylesPath);
       
        
    	stage.show();
    }
    public void showLancamentoHoraScreen(Stage stage){
        
    
    Parent root = null;
        
        var resource = getClass()
            .getResource("/view/LancamentoHora.fxml");
        
        try {
            root =  FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Scene scene = new Scene(root, 944, 609);
        stage.setScene(scene);
        
        var stylesPath = getClass().getResource("/view/styles.css").toString();
        stage.getScene().getStylesheets().add(stylesPath);
        
        stage.show();
    }
    
    
    

    public static void main(String[] args) {
        launch();
    }
}
