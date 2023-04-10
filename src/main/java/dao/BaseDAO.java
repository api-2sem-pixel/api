package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

public abstract class BaseDAO {
    private Connection connection;

    public BaseDAO(Connection connection){
        this.connection = connection;
    }

	protected <T> ArrayList<T> executarQuery(String sql, Function<ResultSet,T> mapper){
		var data = new ArrayList<T>();
		
        try{
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
                
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    var item = mapper.apply(resultSet);
                    data.add(item);
                }
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getStackTrace());
        }
		

		return data;
	}
}
