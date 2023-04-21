package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.IntegrantesCrDTO;
import model.CrUsuario;

public class CrUsuarioDAO extends BaseDAO {
	
	private Connection connection;

	public CrUsuarioDAO(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	public void salvar(CrUsuario crUsuario, int temporario) {
		try {
			String sql = "INSERT INTO Cr_Usuario (Id_Usuario, Id_Cr, Temporario) VALUES (?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setInt(1, crUsuario.getIdUsuario());
				pstm.setInt(2, crUsuario.getIdCr());
				pstm.setInt(3, temporario);

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
	
	public List<IntegrantesCrDTO> listarIntegrantes(int id_Cr){
		List<IntegrantesCrDTO> integrantesCR = new ArrayList<IntegrantesCrDTO>();
		try {
			String sql = "SELECT usuario.Nome, tipoUsuario.descricao FROM Cr_Usuario crUsuario "
					+ "INNER JOIN Usuario usuario on crUsuario.Id_Usuario = usuario.Id "
					+ "INNER JOIN Tipo_Usuario tipoUsuario on usuario.Id_Tipo_Usuario = tipoUsuario.Id "
					+ "WHERE crUsuario.Id_Cr = ?";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, id_Cr);
				pstm.execute();
				
				trasformarResultSetEmIntegrantesCR(integrantesCR, pstm);
			}
			return integrantesCR;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void trasformarResultSetEmIntegrantesCR(List<IntegrantesCrDTO> integrantes, PreparedStatement pstm) 
			throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				IntegrantesCrDTO integrante = new IntegrantesCrDTO(rst.getString(1), rst.getString(2));
				integrantes.add(integrante);
			}
		}
	}
}
