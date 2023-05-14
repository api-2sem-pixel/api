package utils.feedback_retorno;

import model.ExtratoHoraModel;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import dao.ExtratoHoraDAO;

public class FeedBackRetorno {

    public static void motivo(ExtratoHoraDAO dao, ExtratoHoraModel model) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Motivo");
        dialog.setHeaderText("Por favor, digite seus motivos:");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String motivo = result.get();
            dao.inserirMotivo(motivo, model);
        }
    }

}
