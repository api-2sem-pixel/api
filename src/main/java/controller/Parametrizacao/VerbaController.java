package controller.Parametrizacao;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MenuController;
import dao.ParametrizacaoDAO;
import factory.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.VerbaModel;
import utils.mensagem_retorno.MensagemRetorno;

public class VerbaController implements Initializable{

    @FXML private TableView<VerbaModel> table;
    @FXML private TableColumn<VerbaModel, Integer> colVerba;
    @FXML private TableColumn<VerbaModel, Double> colMultiplicador;
    @FXML private TextField fieldVerba;
    @FXML private TextField fieldMultiplicador;

    private ParametrizacaoDAO parametrizacaoDAO;

    public VerbaController() {
        var connection = new ConnectionFactory().recuperarConexao();
        parametrizacaoDAO = new ParametrizacaoDAO(connection);
    }

    @Override public void initialize(URL location, ResourceBundle resources) {
        configurarLinhas();
        carregarLinhas();
    }

    private void carregarLinhas() {
        var verbas = parametrizacaoDAO.obterVerbas();
        table.getItems().remove(0, table.getItems().size());
        table.getItems().addAll(verbas);
    }

    private void configurarLinhas() {
        colVerba.setCellValueFactory(
            new PropertyValueFactory<VerbaModel, Integer>("verba")
        );

        colMultiplicador.setCellValueFactory(
            new PropertyValueFactory<VerbaModel, Double>("multiplicador")
        );

        table.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue == null) {
                    return;
                }

                fieldVerba.setText(newValue.getVerba().toString());
                fieldMultiplicador.setText(newValue.getMultiplicador().toString());
            }
        );
    }

    @FXML void salvar(ActionEvent event) {
        var verbaModel = new VerbaModel();
        var verba = tryParseInteger(fieldVerba.getText());
        var multiplicador = tryParseDouble(fieldMultiplicador.getText());
       
        if(verba == null || multiplicador == null){
            MensagemRetorno.erro("Insira valores numéricos nos campos Verba/Multiplicador");
            return;
        }

        verbaModel.setVerba(verba);
        verbaModel.setMultiplicador(multiplicador);
        var sucesso = parametrizacaoDAO.salvarVerbas(verbaModel);
        if(sucesso){
            MensagemRetorno.sucesso("Dados salvos com sucesso");
            limpar();
            carregarLinhas();
            return;
        }

        MensagemRetorno.erro("Algo deu errado, tente novamente mais tarde.");
    }
    
    @FXML void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }

    private void limpar(){
        fieldMultiplicador.setText(null);
        fieldVerba.setText(null);
    }

    private Integer tryParseInteger(String value){
        try{
            return Integer.parseInt(value);
        }
        catch(Exception ex){
            return null;
        }
    }
  
    private Double tryParseDouble(String value){
        try{
            return Double.parseDouble(value);
        }
        catch(Exception ex){
            return null;
        }
    }   
}
