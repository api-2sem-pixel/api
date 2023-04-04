package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Squad;

public class SquadDAO {

	private Connection connection;

	public SquadDAO(Connection connection) {
		this.connection = connection;
	}

	
	public List<Squad> listar() {
		List<Squad> squads = new ArrayList<Squad>();
		try {
			String sql = "SELECT ID, NOME FROM SQUAD";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				trasformarResultSetEmSquad(squads, pstm);
			}
			return squads;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void trasformarResultSetEmSquad(List<Squad> squads, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Squad squad = new Squad(rst.getInt(1), rst.getString(2));

				squads.add(squad);
			}
		}
	}
}
