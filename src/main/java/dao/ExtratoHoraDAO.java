package dao;

import model.ExtratoHoraModel;
import java.sql.Connection;

public class ExtratoHoraDAO extends BaseDAO {

    public ExtratoHoraDAO(Connection connection) {
        super(connection);
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
