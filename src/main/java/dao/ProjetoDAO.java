package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Projeto;
import model.ComboboxModel.ProjetoComboboxModel;

public class ProjetoDAO extends BaseDAO {

	private Connection connection;

    public ProjetoDAO(Connection connection) {
		super(connection);
		this.connection = connection;
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

	public void salvar(Projeto projeto) {
		try {
			String sql = "INSERT INTO Projeto (nome) VALUES (?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, projeto.getNome());
				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						projeto.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
