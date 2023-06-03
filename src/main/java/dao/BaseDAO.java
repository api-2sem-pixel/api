package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

public abstract class BaseDAO {
    protected Connection connection;

    public BaseDAO(Connection connection) {
        this.connection = connection;
    }

    protected <T> ArrayList<T> executarQuery(String sql, Function<ResultSet, T> mapper) {
        var data = new ArrayList<T>();

        try {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();

                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    var item = mapper.apply(resultSet);
                    data.add(item);
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception.getStackTrace());
        }

        return data;
    }

    protected int executeCount(String sql){
        try {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();

                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception.getStackTrace());
        }
        
        return 0;
    }

    protected int executeUpdate(String sql) {
        try {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                return statement.executeUpdate(sql);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getStackTrace());
            return -1;
        }
    }
}
