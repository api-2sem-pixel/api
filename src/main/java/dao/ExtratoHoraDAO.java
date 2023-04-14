package dao;

import model.ExtratoHoraModel;
import java.sql.Connection;

public class ExtratoHoraDAO extends BaseDAO {

    public ExtratoHoraDAO(Connection connection) {
        super(connection);
    }

    public void lancarHora(ExtratoHoraModel model) {
        String sql = "INSERT INTO Extrato_Hora  VALUES (" + model.getProjeto() + "," + model.getCr() + ","
                + model.getUsuario() + ","
                + model.getModalidade() + "," + model.getMotivo() + "," + model.getDataHoraInicio() + ","
                + model.getDataHoraFim() + "," + model.getJustificativa() + ")";

        executeUpdate(sql);
    }
}
