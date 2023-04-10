package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.SquadModel;
import model.ComboboxModel.CrComboboxModel;

public class CrDAO extends BaseDAO {

	public CrDAO(Connection connection) {
		super(connection);
	}

	public List<SquadModel> listar(){
		String sql = "SELECT ID, NOME FROM SQUAD";
		return executarQuery(sql, x -> {
			try {
				return new SquadModel(x.getInt(1), x.getString(2));
			} catch (SQLException e) {
				
				return null;
			}
		});
	}
	
	public List<CrComboboxModel> obterCombobox(){
		String sql = "SELECT ID, NOME FROM api2sem.Cr";
		return executarQuery(sql, x -> {
			try {
				return new CrComboboxModel(x.getInt(0), x.getString(1));
			} catch (SQLException e) {
				return null;
			}
		});
	}
}
