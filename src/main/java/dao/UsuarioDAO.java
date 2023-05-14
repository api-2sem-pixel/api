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
import model.ComboboxModel.UsuarioComboboxModel;

public class UsuarioDAO extends BaseDAO {

	private static Connection connection;

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
			String sql = "Select Nome, Id From Usuario WHERE Ativo = 1";

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
				UsuarioDTO usuarioDTO = new UsuarioDTO(rst.getString(1), rst.getInt(2), null, null, 0);
				usuario.add(usuarioDTO);
			}
		}
	}
	
	public List<UsuarioComboboxModel> obterCombobox(){
		String sql = "SELECT Id, Nome FROM Usuario WHERE Ativo = 1";
		return executarQuery(sql, x -> {
			try {
				return new UsuarioComboboxModel(x.getInt(1), x.getString(2));
			} catch (SQLException e) {
				return null;
			}
		});
	}
	
	public static List<UsuarioDTO> listarUsuarios(Integer id){
		List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		try {
			String sql = "SELECT usuario.Nome, usuario.Id, usuario.Cpf_Cnpj, usuario.Email, tipoUsuario.Id FROM Usuario usuario "
					+ "INNER JOIN Tipo_Usuario tipoUsuario on usuario.Id_Tipo_Usuario = tipoUsuario.Id "
					+ "WHERE usuario.Ativo = 1";
			
			if(id != null){
				sql += " AND usuario.Id = " + id;
			}

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
				trasformarResultSetEmUsuarios(usuarios, pstm);
			}
			return usuarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void trasformarResultSetEmUsuarios(List<UsuarioDTO> usuario, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				UsuarioDTO usuarioDTO = new UsuarioDTO(rst.getString(1), rst.getInt(2), rst.getString(4), rst.getString(3), rst.getInt(5));
				usuario.add(usuarioDTO);
			}
		}
	}
	
	public void deletar(int id) {
		try {
			String sql = "UPDATE Usuario SET Ativo = 0 WHERE Id = ?";
			

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, id);
				pstm.execute();			
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
