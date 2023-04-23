package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.ComboboxModel.TipoUsuarioComboboxModel;

public class TipoUsuarioDAO extends BaseDAO {

    public TipoUsuarioDAO(Connection connection) {
        super(connection);
    }
    
    public List<TipoUsuarioComboboxModel> obterCombobox(){
		String sql = "SELECT Id, Descricao FROM Tipo_Usuario";
		return executarQuery(sql, x -> {
			try {
				return new TipoUsuarioComboboxModel(x.getInt(1), x.getString(2));
			} catch (SQLException e) {
				return null;
			}
		});
	}

}
