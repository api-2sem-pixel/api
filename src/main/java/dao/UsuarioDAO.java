package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CadastroUsuario;
import model.Squad;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	public void cadastrar(CadastroUsuario usuario) {
		List<Squad> squads = new ArrayList<Squad>();
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


}
