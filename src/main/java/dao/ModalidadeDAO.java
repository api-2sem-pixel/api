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
    
    public List<ModalidadeComboboxModel> obterCombobox(){
		String sql = "SELECT ID, NOME FROM CR";
		return executarQuery(sql, x -> {
			try {
				return new ModalidadeComboboxModel(x.getInt(0), x.getString(1));
			} catch (SQLException e) {
				return null;
			}
		});
	}
}
