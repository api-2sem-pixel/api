package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.ComboboxModel.MotivoComboboxModel;

public class MotivoDAO extends BaseDAO{

    public MotivoDAO(Connection connection) {
        super(connection);
    }
    
    public List<MotivoComboboxModel> obterCombobox(){
		String sql = "SELECT Id, Descricao FROM Motivo";
		return executarQuery(sql, x -> {
			try {
				return new MotivoComboboxModel(x.getInt(1), x.getString(2));
			} catch (SQLException e) {
				return null;
			}
		});
	}
}
