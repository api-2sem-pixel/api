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
		String sql = "select id, nome from api2sem.Projeto";
		return executarQuery(sql, x -> {
			try {
				return new ProjetoComboboxModel(x.getInt("id"), x.getString("nome"));
			} catch (SQLException e) {
				return null;
			}
		});
	}
    
}
