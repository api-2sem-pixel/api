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
	
	public List<String> listarNomeCR(){
		List<String> nomesCR = new ArrayList<String>();
		try {
			String sql = "SELECT Nome FROM Cr";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
				
				trasformarResultSetEmNomeCR(nomesCR, pstm);
			}
			return nomesCR;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<String> listarIntegrantesCR(){
		List<String> nomesCR = new ArrayList<String>();
		try {
			String sql = "Select usuario.Nome from Cr cr "
					+ "INNER JOIN Cr_Usuario crUsuario on cr.Id = crUsuario.Id_Cr "
					+ "INNER JOIN Usuario usuario on crUsuario.Id_Usuario = usuario.Id;";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
				
				trasformarResultSetEmNomeCR(nomesCR, pstm);
			}
			return nomesCR;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public List<CrComboboxModel> obterCombobox(){
		String sql = "SELECT Id, Nome FROM Cr";
		return executarQuery(sql, x -> {
			try {
				return new CrComboboxModel(x.getInt(1), x.getString(2));
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
			String sql = "SELECT Id, Nome, Sigla, Codigo_CR  FROM Cr";
			
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
			String sql = "INSERT INTO Cr (Nome, Sigla, Codigo_CR) VALUES (?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, cr.getNome());
				pstm.setString(2, cr.getSigla());
				pstm.setString(3, cr.getCodigo());
				
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
				CrDTO crDTO = new CrDTO(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4));
				cr.add(crDTO);
			}
		}
	}
	
	private void trasformarResultSetEmNomeCR(List<String> nomesCR, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				nomesCR.add(rst.getString(1));
			}
		}
	}
}
