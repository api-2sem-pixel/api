package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.ComboboxModel.ProjetoComboboxModel;

public class ProjetoDAO extends BaseDAO {

    public ProjetoDAO(Connection connection) {
        super(connection);
    }

    public List<ProjetoComboboxModel> obterCombobox(){
		String sql = "SELECT ID, NOME FROM CR";
		return executarQuery(sql, x -> {
			try {
				return new ProjetoComboboxModel(x.getInt(0), x.getString(1));
			} catch (SQLException e) {
				return null;
			}
		});
	}
    
}
