package controller.Cliente;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MenuController;
import dao.ClienteDAO;
import factory.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.Cliente;

public class ControleClienteController implements Initializable {

    @FXML private TextField txtCnpj;
    @FXML private TextField txtRazaoSocial;
    @FXML private TableView<Cliente> tableCliente;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colRazaoSocial;
    @FXML private TableColumn<Cliente, String> colCnpj;
    @FXML private TableColumn<Cliente, Void> colAcoes;

    private ClienteDAO clienteDAO;

    @Override public void initialize(URL location, ResourceBundle resources) {
        var conn = new ConnectionFactory().recuperarConexao();
        clienteDAO = new ClienteDAO(conn);

        configurarLinhas();
        popularTabela();
    }

    private void configurarLinhas() {
        colId.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));
        colRazaoSocial.setCellValueFactory(new PropertyValueFactory<Cliente, String>("razaoSocial"));
        colCnpj.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cnpj"));

        var buttonDeletar = new Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>>() {
            @Override
            public TableCell<Cliente, Void> call(final TableColumn<Cliente, Void> param) {
                final TableCell<Cliente, Void> cell = new TableCell<Cliente, Void>() {
                    private final Button btn = new Button("Excluir");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            var row = getTableView().getItems().get(getIndex());
                            getTableView().getItems().remove(getIndex());
                            deletar(row);
                            popularTabela();
                        });
                    }

                    @Override public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) setGraphic(null);
                        else setGraphic(btn);
                    }
                };
                return cell;
            }
        };
        colAcoes.setCellFactory(buttonDeletar);
    }

    private void popularTabela() {
        var clientes = clienteDAO.buscarClientes(txtRazaoSocial.getText(), txtCnpj.getText());
        tableCliente.setItems(FXCollections.observableArrayList(FXCollections.observableArrayList(clientes)));
    }

    @FXML void buscar(ActionEvent event) {
        popularTabela();
    }

    private void deletar(Cliente cliente){
        clienteDAO.deletarCliente(cliente.getId());
    }

    @FXML void irCriarCliente(ActionEvent event) {
        MenuController mc = new MenuController();
        mc.irCadastroCliente(null);
    }

    @FXML void retornarMenu(MouseEvent event) {
        MenuController.irMenu();
    }
}
