package controller.MenuFeedBack;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.MenuController;
import dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuFeedBackController implements Initializable {
    @FXML
    private Label homeText;

    private static Stage currentStage;

    public static void setStage(Stage stage) {
        currentStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    
    public static void irMenu() {
        MenuController menu = new MenuController();
        menu.irMenu();
    }
}
