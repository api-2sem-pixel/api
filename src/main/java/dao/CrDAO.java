package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.CrDTO;
import model.CR;
import model.SquadModel;
import model.ComboboxModel.CrComboboxModel;

public class CrDAO extends BaseDAO {
	private Connection connection;

	public CrDAO(Connection connection) {
		super(connection);
		this.connection = connection;
	}

	public List<SquadModel> listar(){
		String sql = "SELECT ID, NOME FROM SQUAD";
		return executarQuery(sql, x -> {
			try {
				return new SquadModel(x.getInt(1), x.getString(2));
			} catch (SQLException e) {
				
				return null;
			}
		});
	}

	public List<CrComboboxModel> obterCombobox(){
		String sql = "SELECT ID, NOME FROM api2sem.Cr";
		return executarQuery(sql, x -> {
			try {
				return new CrComboboxModel(x.getInt(0), x.getString(1));
			} catch (SQLException e) {
				return null;
			}
		});
	}

	public List<CrDTO> getIdGestorAndNomeCr() {
		// Lista nomeCR instanciada
		List<CrDTO> cr = new ArrayList<CrDTO>();
		try {
			// Buscando a informação do Banco de Dados
			String sql = "SELECT NOME, Id_Gestor FROM Cr";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
				
				//Chamando o método para transformar a busca em nomeCR
				trasformarResultSetEmCR(cr, pstm);
			}
			return cr;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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

	private void trasformarResultSetEmCR(List<CrDTO> cr, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				CrDTO crDTO = new CrDTO(rst.getString(1), rst.getInt(2));
				cr.add(crDTO);
			}
		}
	}
}
