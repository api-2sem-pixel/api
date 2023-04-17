package dao;

import model.ExtratoHoraModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ExtratoHoraDAO extends BaseDAO {

    public ExtratoHoraDAO(Connection connection) {
        super(connection);
    }

    public ArrayList<ExtratoHoraModel> obterExtratosLancados(int userId){
        String sql = "select b.Nome Cr, " +
                        "a.Projeto, " + 
                        "c.Descricao Modalidade," +
                        "a.DataHora_Inicio Inicio," + 
                        "a.DataHora_Fim Fim," +
                        "d.Descricao Motivo," + 
                        "a.Id_Modalidade Id_Modalidade," + 
                        "d.Possivel_Edicao PossivelEditar" + 
                    "from Extrato_Hora a  " + 
                    "inner join Cr b on a.Id_Cr = b.Id " + 
                    "inner join Modalidade c on c.Id = a.Id_Modalidade " + 
                    "inner join Motivo d on d.Id = a.Id_Motivo " +
                    "where a.Id_Usuario = " + userId; 

        return this.executarQuery(sql, resultSet -> {
            
            try {
                var model = new ExtratoHoraModel();
                model.setCr(resultSet.getString(1));
                model.setProjeto(resultSet.getString(2));
                model.setModalidade(resultSet.getString(3));
                model.setDataHoraInicio(resultSet.getObject(4, LocalDateTime.class));
                model.setDataHoraFim(resultSet.getObject(5, LocalDateTime.class));
                model.setMotivo(resultSet.getString(6));
                model.setIdModalidade(resultSet.getInt(7));
                return model;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        });
    }

    public void lancarHora(ExtratoHoraModel model) {
        String sql = "INSERT INTO Extrato_Hora(Projeto, Id_Cr, Id_Usuario, Id_Modalidade, Id_Motivo, DataHora_Inicio, DataHora_Fim, Justificativa) " +
                "VALUES (" + model.getProjeto() + "," 
                    + model.getIdCr() + "," 
                    + model.getIdUsuario() + "," 
                    + model.getIdModalidade() + "," 
                    + model.getIdMotivo() + "," 
                    + model.getDataHoraInicio() + ","
                    + model.getDataHoraFim() + "," + model.getJustificativa() + ")";

        executeUpdate(sql);
    }
}
