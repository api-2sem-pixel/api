package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UsuarioDTO;
import enums.TipoUsuario;
import model.CadastroUsuario;
import model.UsuarioModel;

public class UsuarioDAO extends BaseDAO {

	private Connection connection;

	public static UsuarioModel usuarioLogado;

	public UsuarioDAO(Connection connection) {
		super(connection);
		this.connection = connection;
	}

	public UsuarioModel getUsuarioBy(String email){
		String sql = "SELECT Id, Nome, Email, Cpf_Cnpj, Id_Tipo_Usuario from Usuario where Email = '" + email.trim() + "'" ;

		var usuarioModel = executarQuery(sql, x -> {
			try {
				return new UsuarioModel(
					x.getInt(1), 
					x.getString(2), 
					x.getString(3), 
					x.getString(4), 
					TipoUsuario.values()[x.getInt(5)]
				);
			} catch (SQLException e) {
				return null;
			}
		});

		return usuarioModel.isEmpty() ? null : usuarioModel.get(0);
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
	
	public void cadastrar(CadastroUsuario usuario) {
		try {
			String sql = "INSERT INTO Usuario(Cpf_Cnpj,Id_Tipo_Usuario, Telefone, Nome, Email ) "
					+ "VALUES (?,?,?,?,?)";
			

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, usuario.getCpf());
				pstm.setInt(2, usuario.getId());
				pstm.setString(3, usuario.getTel());
				pstm.setString(4, usuario.getNome());
				pstm.setString(5, usuario.getEmail());
				pstm.execute();			
			}
			
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
