package dao;

import model.ExtratoHoraModel;
import java.sql.Connection;
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
                        /* "d.Possivel_Edicao PossivelEditar, " +  */
                        "c.Descricao Modalidade, " +
                        "a.Id IdExtrato, " +
                        "e.Razao_Social NomeCliente, " +
                        "a.Justificativa Justificativa " +
                    "from Extrato_Hora a  " + 
                    "inner join Cr b on a.Id_Cr = b.Id " + 
                    "inner join Modalidade c on c.Id = a.Id_Modalidade " + 
                    "inner join Motivo d on d.Id = a.Id_Motivo " +
                    "inner join Cliente e on e.Id = a.Id_Cliente " +
                    "where a.Id_Usuario = " + userId; 

        return this.executarQuery(sql, resultSet -> {
            try {
                var model = new ExtratoHoraModel();
                
                model.setCr(resultSet.getString(1));
                model.setProjeto(resultSet.getString(2));
                model.setModalidade(resultSet.getString(3));
                
                var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                var inicio = resultSet.getString(4);
                model.setDataHoraInicio(LocalDateTime.parse(inicio, formatter));
                var fim = resultSet.getString(5);
                model.setDataHoraFim(LocalDateTime.parse(fim, formatter));
                
                model.setMotivo(resultSet.getString(6));
                model.setIdModalidade(resultSet.getInt(7));
                model.setModalidade(resultSet.getString(8));
                model.setId(resultSet.getInt(9));
                model.setCliente(resultSet.getString(10));
                model.setJustificativa(resultSet.getString(11));

                return model;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        });
    }

    public int lancarHora(ExtratoHoraModel model) {
        String sql = "INSERT INTO Extrato_Hora(Projeto, Id_Cliente, Id_Etapa_Extrato, Id_Cr, Id_Usuario, Id_Modalidade, Id_Motivo, DataHora_Inicio, DataHora_Fim, Justificativa) " +
                "VALUES ('" + model.getProjeto() + "'," 
                    + model.getIdCliente() + ","
                    + 1 + ","
                    + model.getIdCr() + "," 
                    + model.getIdUsuario() + "," 
                    + model.getIdModalidade() + "," 
                    + model.getIdMotivo() + ",'" 
                    + model.getDataHoraInicio().toString() + "','"
                    + model.getDataHoraFim().toString() + "','" 
                    + model.getJustificativa() + "')";

        return executeUpdate(sql);
    }
}
