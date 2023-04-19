package dao;

import model.ExtratoHoraModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ExtratoHoraDAO extends BaseDAO {

    public ExtratoHoraDAO(Connection connection) {
        super(connection);
    }

    public ArrayList<ExtratoHoraModel> obterExtratosLancados(int userId){
        String sql = "select b.Nome Cr, " +
                        "a.Projeto, " + 
                        "c.Descricao Modalidade, " +
                        "a.DataHora_Inicio Inicio, " + 
                        "a.DataHora_Fim Fim, " +
                        "d.Descricao Motivo, " + 
                        "a.Id_Modalidade Id_Modalidade, " + 
                        "d.Possivel_Edicao PossivelEditar, " + 
                        "c.Descricao Modalidade " +
                    "from Extrato_Hora a  " + 
                    "inner join Cr b on a.Id_Cr = b.Id " + 
                    "inner join Modalidade c on c.Id = a.Id_Modalidade " + 
                    "inner join Motivo d on d.Id = a.Id_Motivo " +
                    "where a.Id_Usuario = " + userId; 

        return this.executarQuery(sql, resultSet -> {
            try {
                var model = new ExtratoHoraModel();
                model.setIdModalidade(resultSet.getInt(7));
                model.setModalidade(resultSet.getString(8));
                model.setCr(resultSet.getString(1));
                model.setProjeto(resultSet.getString(2));
                model.setModalidade(resultSet.getString(3));
                model.setMotivo(resultSet.getString(6));
            
                var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                
                var inicio = resultSet.getString(4);
                model.setDataHoraInicio(LocalDateTime.parse(inicio, formatter));
                
                var fim = resultSet.getString(5);
                model.setDataHoraFim(LocalDateTime.parse(fim, formatter));

                return model;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        });
    }

    public int lancarHora(ExtratoHoraModel model) {
        String sql = "INSERT INTO Extrato_Hora(Projeto, Id_Etapa_Extrato, Id_Cr, Id_Usuario, Id_Modalidade, Id_Motivo, DataHora_Inicio, DataHora_Fim, Justificativa) " +
                "VALUES ('" + model.getProjeto() + "'," 
                    + 1 + ","
                    + model.getIdCr() + "," 
                    + model.getIdUsuario() + "," 
                    + model.getIdModalidade() + "," 
                    + model.getIdMotivo() + ",'" 
                    + model.getDataHoraInicio().toString() + "','"
                    + model.getDataHoraFim().toString() + "'," 
                    + model.getJustificativa() + ")";

        return executeUpdate(sql);
    }
}
