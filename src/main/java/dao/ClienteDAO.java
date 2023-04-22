package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Cliente;
import model.ComboboxModel.ClienteComboboxModel;

public class ClienteDAO extends BaseDAO {

	private Connection connection;

    public ClienteDAO(Connection connection) {
		super(connection);
		this.connection = connection;
    }

    public List<ClienteComboboxModel> obterCombobox(){
		String sql = "select Id, Razao_Social from Cliente";
		return executarQuery(sql, x -> {
			try {
				return new ClienteComboboxModel(x.getInt(1), x.getString(2));
			} catch (SQLException e) {
				return null;
			}
		});
	}

	public void salvar(Cliente cliente) {
		try {
			String sql = "INSERT INTO Cliente (Razao_Social, Cnpj) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, cliente.getRazaoSocial());
				pstm.setString(2, cliente.getCnpj());
				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						cliente.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}