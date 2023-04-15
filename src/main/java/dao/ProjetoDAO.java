package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.CR;
import model.Projeto;

public class ProjetoDAO {

	private Connection connection;

	public ProjetoDAO(Connection connection) {
		this.connection = connection;
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
