package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UsuarioDTO;
import model.Squad;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	
	public Integer getIdUsuario(String cpf) {
		Integer id = null;
		try {
			String sql = "Select Id From Usuario WHERE Cpf_Cnpj = ? ";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, cpf);
				pstm.execute();

				id = trasformarResultSetEmId(id, pstm);
			}
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Integer trasformarResultSetEmId(Integer id, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				id = rst.getInt(1);
			}
		}
		return id;
	}
	
	public List<UsuarioDTO> getNomeUsuarioAndId() {
		List<UsuarioDTO> usuario = new ArrayList<UsuarioDTO>();
		try {
			String sql = "Select Nome, Id From Usuario";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				trasformarResultSetEmUsuarioDTO(usuario, pstm);
			}
			return usuario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void trasformarResultSetEmUsuarioDTO(List<UsuarioDTO> usuario, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				UsuarioDTO usuarioDTO = new UsuarioDTO(rst.getString(1), rst.getInt(2));
				usuario.add(usuarioDTO);
			}
		}
	}
}
