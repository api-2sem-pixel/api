package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.ComboboxModel.ModalidadeComboboxModel;

public class ModalidadeDAO extends BaseDAO {

	public ModalidadeDAO(Connection connection) {
		super(connection);
	}

	public List<ModalidadeComboboxModel> obterCombobox() {
		String sql = "SELECT Id, Descricao FROM Modalidade";
		return executarQuery(sql, resultSet -> {
			try {
				return new ModalidadeComboboxModel(resultSet.getInt(1), resultSet.getString(2));
			} catch (SQLException e) {
				return null;
			}
		});
	}
}
