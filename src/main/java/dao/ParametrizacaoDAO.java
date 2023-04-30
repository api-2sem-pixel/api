package dao;

import java.sql.Connection;
import java.util.ArrayList;

import model.VerbaModel;

public class ParametrizacaoDAO extends BaseDAO {

    public ParametrizacaoDAO(Connection connection) {
        super(connection);
    }
    
    public ArrayList<VerbaModel> obterVerbas(){
        var sql = "SELECT * FROM Parametrizacao_Verba ORDER BY Verba ASC";

        return super.executarQuery(sql, x -> {
            try{
                return new VerbaModel(x.getInt(1), x.getDouble(2));
            } catch (Exception ex) {
                return null;
            }
        });
    }

    public boolean salvarVerbas(VerbaModel verba){
        String sql = "UPDATE Parametrizacao_Verba SET Multiplicador = " + verba.getMultiplicador() + 
                     " WHERE Verba = " + verba.getVerba();
        return executeUpdate(sql) > 0;
    }
}
