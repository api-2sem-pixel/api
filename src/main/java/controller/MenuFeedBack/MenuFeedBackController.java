package controller.MenuFeedBack;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void irFeedBackHora(MouseEvent event) throws IOException {
        changeScene("/view/FeedBack/FeedBack.fxml");
    }

    private void changeScene(String fxml) {
        Parent scene;
        try {
            scene = FXMLLoader.load(getClass().getResource(fxml));
            currentStage.getScene().setRoot(scene);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void irMenu() {
        MenuFeedBackController menu = new MenuFeedBackController();
        menu.changeScene("/view/Menu/Menu.fxml");
    }

    public static void irFeedBack() {
    }
}
