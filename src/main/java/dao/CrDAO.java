package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.CR;

public class CrDAO {

	private Connection connection;

	public CrDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(CR cr) {
		try {
			String sql = "INSERT INTO Cr (ID_GESTOR, NOME) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setInt(1, cr.getId_gestor());
				pstm.setString(2, cr.getNomeCR());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						cr.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}


}
