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
		String sql = "select Id, Razao_Social from Cliente where Ativo = 1";
		return executarQuery(sql, x -> {
			try {
				return new ClienteComboboxModel(x.getInt(1), x.getString(2));
			} catch (SQLException e) {
				return null;
			}
		});
	}

	public List<Cliente> buscarClientes(String razaoSocial, String cnpj){
		String sql = "select Id, Razao_Social, Cnpj from Cliente where Ativo = 1";

		if(razaoSocial != null && !razaoSocial.isEmpty()){
			sql += " AND Razao_Social like '%" + razaoSocial + "%'";
		}

		if(cnpj != null && !cnpj.isEmpty()){
			sql += " AND Cnpj = '" + cnpj + "'";
		}
		
		return executarQuery(sql, x -> {
			try {
				return new Cliente(x.getInt(1), x.getString(2), x.getString(3));
			} catch (SQLException e) {
				return null;
			}
		});
	}

	public Integer deletarCliente(Integer id){
		String sql = "UPDATE CLIENTE SET Ativo = 0 WHERE Id = " + id;
		return super.executeUpdate(sql);
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
