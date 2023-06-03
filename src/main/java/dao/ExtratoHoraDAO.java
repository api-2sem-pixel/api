package dao;

import model.ExtratoHoraModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import enums.EtapaExtrato;

public class ExtratoHoraDAO extends BaseDAO {

    private String getQueryExtratoHoraModel() {
        return "select b.Nome Cr, " +
                "a.Projeto, " +
                "c.Descricao Modalidade, " +
                "a.DataHora_Inicio Inicio, " +
                "a.DataHora_Fim Fim, " +
                "a.Motivo Motivo, " +
                "a.Id_Modalidade Id_Modalidade, " +
                "c.Descricao Modalidade, " +
                "a.Id IdExtrato, " +
                "e.Razao_Social NomeCliente, " +
                "a.Justificativa Justificativa " +
                "from Extrato_Hora a  " +
                "inner join Cr b on a.Id_Cr = b.Id " +
                "inner join Modalidade c on c.Id = a.Id_Modalidade " +
                "inner join Cliente e on e.Id = a.Id_Cliente ";
    }

    public ExtratoHoraDAO(Connection connection) {
        super(connection);
    }

    public ArrayList<ExtratoHoraModel> obterExtratosLancados(int userId, String projeto) {
        String sql = getQueryExtratoHoraModel() +
                " where a.Id_Usuario = " + userId;

        if (projeto != null && !projeto.isEmpty())
            sql += " AND projeto like '%" + projeto + "%'";

        return this.executarQuery(sql, resultSet -> mapearParaExtratoHoraModel(resultSet));
    }

    public ArrayList<ExtratoHoraModel> obterExtratosParaAprovar(int userId, String projeto) {
        String sql = getQueryExtratoHoraModel() +
                " where (a.Id_Cr in (SELECT Id_Cr FROM Cr_Usuario where Id_Usuario = " + userId + ") or " +
                " Id_Usuario in (SELECT Id_Usuario FROM Usuario where Id_Tipo_Usuario = 3)) " +
                " and Id_Etapa_Extrato in (1,4)";

        if (projeto != null && !projeto.isEmpty())
            sql += " AND projeto like '%" + projeto + "%'";

        return this.executarQuery(sql, resultSet -> mapearParaExtratoHoraModel(resultSet));
    }

    private ExtratoHoraModel mapearParaExtratoHoraModel(ResultSet resultSet) {
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
    }

    public int lancarHora(ExtratoHoraModel model) {
        String sql = "INSERT INTO Extrato_Hora(Projeto, Id_Cliente, Id_Etapa_Extrato, Id_Cr, Id_Usuario, Id_Modalidade, DataHora_Inicio, DataHora_Fim, Justificativa) "
                +
                "VALUES ('" + model.getProjeto() + "',"
                + model.getIdCliente() + ","
                + model.getStatus().ordinal() + ","
                + model.getIdCr() + ","
                + model.getIdUsuario() + ","
                + model.getIdModalidade() + ",'"
                + model.getDataHoraInicio().toString() + "','"
                + model.getDataHoraFim().toString() + "','"
                + model.getJustificativa() + "')";

        return executeUpdate(sql);
    }

    public void aprovarHoraExtra(ExtratoHoraModel extratoHora) {
        try {
            String sql = "UPDATE extrato_hora SET Id_Etapa_Extrato = 2 WHERE Id = " + extratoHora.getId();
            executeUpdate(sql);
            extratoHora.setStatus(EtapaExtrato.APROVADA);

        } catch (Exception e) {
            e.addSuppressed(e);
        }

    }

    public void reprovarHoraExtra(ExtratoHoraModel extratoHora) {
        try {
            String sql = "UPDATE extrato_hora SET Id_Etapa_Extrato = 3 WHERE Id = " + extratoHora.getId();
            executeUpdate(sql);
            extratoHora.setStatus(EtapaExtrato.REPROVADA);

        } catch (Exception e) {
            e.addSuppressed(e);
        }
    }
    
    //Select para pegar as horas aprovadas do Colaborador
    public int qtdHoraAprovada(int idUser) {
        try {
           String sql = "SELECT COUNT(*) FROM Extrato_Hora eh inner join usuario user on eh.Id_Usuario = user.Id  WHERE  user.Id_Tipo_Usuario = 1  AND user.Id = " + idUser + " AND Id_Etapa_Extrato =" + EtapaExtrato.APROVADA.ordinal();
           return executeCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
   
    //Select para pegar as horas reprovadas do Colaborador
    public int qtdHoraReprovada(int idUser) {
        try {
            String sql = "SELECT COUNT(*) FROM Extrato_Hora eh inner join usuario user on eh.Id_Usuario = user.Id  WHERE  user.Id_Tipo_Usuario = 1 AND user.Id = " + idUser + " AND Id_Etapa_Extrato =" + EtapaExtrato.REPROVADA.ordinal();
            return executeCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    //Select para pegar as horas aprovadas do CR
    public int qtdHoraCrAprovada() {
        try {
           String sql = "SELECT COUNT(*) FROM extrato_hora WHERE  Id_Cr AND Id_Etapa_Extrato =" + EtapaExtrato.APROVADA.ordinal();
           return executeCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    //Select para pegar as horas reprovadas do CR
    public int qtdHoraCrReprovada() {
        try {
            String sql = "SELECT COUNT(*) FROM extrato_hora WHERE  Id_Cr AND Id_Etapa_Extrato =" + EtapaExtrato.REPROVADA.ordinal();
            return executeCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void inserirMotivo(String motivo, ExtratoHoraModel extratoHora) {
        String sql = "UPDATE extrato_Hora SET Motivo = '" + motivo + "' WHERE Id= " + extratoHora.getId();
        executeUpdate(sql);
    }

}
