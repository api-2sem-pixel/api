package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.ComboboxModel.ModalidadeComboboxModel;
import model.ComboboxModel.MotivoComboboxModel;

public class ModalidadeDAO extends BaseDAO {

	public ModalidadeDAO(Connection connection) {
		super(connection);
	}

	public List<ModalidadeComboboxModel> obterCombobox() {
		String sql = "SELECT ID, NOME FROM api2sem.Modalidade";
		return executarQuery(sql, resultSet -> {
			try {
				return new ModalidadeComboboxModel(resultSet.getInt(0), resultSet.getString(1));
			} catch (SQLException e) {
				return null;
			}
		});
	}
}
