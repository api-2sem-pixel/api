package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.CrUsuario;

public class CrUsuarioDAO {
	
	private Connection connection;

	public CrUsuarioDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(CrUsuario crUsuario) {
		try {
			String sql = "INSERT INTO Cr (Id_Usuario, Id_Cr) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setInt(1, crUsuario.getIdUsuario());
				pstm.setInt(2, crUsuario.getIdCr());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						//cr.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
}
