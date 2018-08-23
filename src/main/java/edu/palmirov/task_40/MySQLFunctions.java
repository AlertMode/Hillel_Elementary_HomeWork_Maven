package edu.palmirov.task_40;

import edu.palmirov.task_40.interfaces.Databasable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLFunctions {
    public static Connection connectToDB(String url, String username, String password) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("The connection to the DB nextRecord been established!");
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect to the DB!");
        }
    }

    public static boolean hasDatabase(Connection connection, String databaseName) throws SQLException{
        List<String> list = new ArrayList<>();
        ResultSet resultSet = connection.getMetaData().getCatalogs();
        while(resultSet.next()){
            String name = resultSet.getString(1);
            list.add(name);
        }
        boolean result = list.contains(databaseName);
        resultSet.close();
        return result;
    }

    public static void toDatabaseAll(Connection connection, List<? extends Databasable> list ) throws SQLException {
        Statement statement = connection.createStatement();
        for(int i = 0; i < list.size(); i++){
            statement.executeUpdate(list.get(i).toDatabase());
        }
        statement.close();
        connection.commit();
    }

    public static void toDatabase(Connection connection, Databasable databasable ) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(databasable.toDatabase());
        statement.close();
        connection.commit();
    }

    public static List<String> fromDatabase(Connection connection, String sql) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metadata = resultSet.getMetaData();
        int columnCount = metadata.getColumnCount();
        List<String> strings = new ArrayList<>();
        while(resultSet.next()){
            StringBuilder value = new StringBuilder();
            for(int i = 1; i <= columnCount; i++){
                value.append(resultSet.getString(i) +
                        (columnCount >= i + 1 ? ", " : ""));
            }
            strings.add(value.toString());
        }
        resultSet.close();
        return strings;
    }

    public static boolean nextRecord(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet.next();
    }
}