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
import model.DadosRelatorio;

public class RelatorioDAO extends BaseDAO {
	
	private Connection connection;

	public RelatorioDAO(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	
	public List<DadosRelatorio> listarDadosParaRelatorio(Integer idCr){
		List<DadosRelatorio> dados = new ArrayList<DadosRelatorio>();
		try {
			String sql = "select distinct user.Cpf_Cnpj, user.Nome, eh.DataHora_Inicio, eh.DataHora_Fim , user.Id from cr cr "
					+ " INNER JOIN cr_usuario crUser on cr.Id = crUser.Id_Cr "
					+ " INNER JOIN usuario user on crUser.Id_Usuario = user.Id "
					+ " INNER JOIN extrato_hora eh on user.Id = eh.Id_Usuario "
					+ " WHERE eh.Id_Cr =  " + idCr  
					+ " ORDER BY user.Id ";
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();			
				trasformarResultSetEmIntegrantesCR(dados, pstm);
			}
			return dados;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void trasformarResultSetEmIntegrantesCR(List<DadosRelatorio> dados, PreparedStatement pstm) 
			throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				DadosRelatorio dado = new DadosRelatorio(rst.getString(1), rst.getString(2), rst.getTimestamp(3), rst.getTimestamp(4), rst.getInt(5));
				dados.add(dado);
			}
		}
	}
	
}
