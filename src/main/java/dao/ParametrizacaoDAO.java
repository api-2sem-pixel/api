package dao;

import java.sql.Connection;
import java.util.ArrayList;

import model.ParametroModel;

public class ParametrizacaoDAO extends BaseDAO {

    public ParametrizacaoDAO(Connection connection) {
        super(connection);
    }
    
    public ArrayList<ParametroModel> obterVerbas(){
        var sql = "SELECT * FROM Parametrizacao ORDER BY Parametro ASC";

        return super.executarQuery(sql, x -> {
            try{
                return new ParametroModel(x.getString(1), x.getString(2));
            } catch (Exception ex) {
                return null;
            }
        });
    }

    public boolean salvarVerbas(ParametroModel verba){
        String sql = "UPDATE Parametrizacao SET Valor = '" + verba.getValor() + "'" +
                     " WHERE Parametro = '" + verba.getParametro() + "'";
        return executeUpdate(sql) > 0;
    }
}
