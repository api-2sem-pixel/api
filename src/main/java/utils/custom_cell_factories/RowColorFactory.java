package utils.custom_cell_factories;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import model.ExtratoHoraModel;

public class RowColorFactory{
     public static void criarCoresStatus(TableView<ExtratoHoraModel> table) {
        table.setRowFactory(row -> new TableRow<ExtratoHoraModel>() {
            @Override
            protected void updateItem(ExtratoHoraModel item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null){
                    setStyle("");
                    return;
                }
                
                switch(item.getStatus()){
                    case EM_APROVACAO:  setStyle("-fx-background-color: #7f7d9c;"); break;
                    case APROVADA: setStyle("-fx-background-color: lightgreen;"); break;
                    case REPROVADA: setStyle("-fx-background-color: #fb3b1e;"); break;
                    case PENDENTE_CORRECAO:
                    case CRIACAO:
                    default:
                       break;
                }
            }
        });
    }
}