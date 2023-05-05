package controller.MenuFeedBack;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MenuFeedBackController implements Initializable {
    @FXML
    private Label homeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}


    public static void irMenu() {
        MenuController menu = new MenuController();
        
        menu.irMenu();
    }
}
