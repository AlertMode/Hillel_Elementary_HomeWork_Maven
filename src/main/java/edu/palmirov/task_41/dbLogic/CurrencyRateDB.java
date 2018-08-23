package edu.palmirov.task_41.dbLogic;

import edu.palmirov.task_41.enteties.CurrencyRate;
import edu.palmirov.task_41.interfaces.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CurrencyRateDB implements DAO<CurrencyRate> {
    private Connection connection;

    public CurrencyRateDB(String url, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("The connection to the DB has been established!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect to the DB!");
        }
    }

    public void buildSchema() throws SQLException {
        String query = "CREATE DATABASE test_schema";
        Statement statement = connection.createStatement();
        if(!hasDatabase()){
            statement.executeUpdate(query);
        }
        statement.executeUpdate("USE test_schema");
    }

    public void buildTable() {
        String sql = "CREATE TABLE IF NOT EXISTS currency_rate" +
                "(id INT PRIMARY KEY auto_increment," +
                " r030 INT," +
                " currency_name VARCHAR(50)," +
                " rate DECIMAL(15, 10)," +
                " abbreviation VARCHAR(50)," +
                " exchange_date DATE)";

        try  {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean create(CurrencyRate currencyRate) {
        String sql = "INSERT INTO currency_rate (r030, currency_name, rate, abbreviation, exchange_date)" +
                "VALUES (?, ?, ?, ?, ?)";
        int rowsInserted = -1;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, currencyRate.getR030());
            statement.setString(2, currencyRate.getCurrencyName());
            statement.setDouble(3, currencyRate.getRate());
            statement.setString(4, currencyRate.getAbbreviation());
            statement.setDate(5,
                    java.sql.Date.valueOf(currencyRate.getDate()));
            rowsInserted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsInserted == 1;
    }

    @Override
    public Optional<CurrencyRate> findByAbbreviation(String currencyAbbreviation) {
        String sql =
                "SELECT r030, currency_name, rate, abbreviation, exchange_date" +
                        " FROM currency_rate" +
                        " WHERE abbreviation = '" + currencyAbbreviation + "'";

        CurrencyRate currencyRate = null;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int r030 = resultSet.getInt("r030");
                String currency_name = resultSet.getString("currency_name");
                double rate = resultSet.getDouble("rate");
                String abbreviation = resultSet.getString("abbreviation");
                LocalDate exchange_date = resultSet.getDate("exchange_date")
                        .toLocalDate();
                currencyRate =
                        new CurrencyRate(r030, currency_name, rate, abbreviation, exchange_date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(currencyRate);
    }

    @Override
    public List<CurrencyRate> findAll() {
        String sql = "SELECT r030, currency_name, rate, abbreviation, exchange_date FROM currency_rate";
        List<CurrencyRate> products = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int r030 = resultSet.getInt("r030");
                String currency_name = resultSet.getString("currency_name");
                double rate = resultSet.getDouble("rate");
                String abbreviation = resultSet.getString("abbreviation");
                LocalDate exchange_date = resultSet.getDate("exchange_date")
                        .toLocalDate();
                CurrencyRate currencyRate =
                        new CurrencyRate(r030, currency_name, rate, abbreviation, exchange_date);
                products.add(currencyRate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (products.isEmpty()) {
            return Collections.emptyList();
        } else {
            return products;
        }
    }

    @Override
    public void delete(CurrencyRate currencyRate) {
        String sql =
                "DELETE FROM currency_rate WHERE r303 = ?" +
                        " AND currency_name = ?" +
                        " AND rate = ?" +
                        " AND abbreviation = ?" +
                        " AND exchange_date = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(2, currencyRate.getR030());
            statement.setString(3, currencyRate.getCurrencyName());
            statement.setDouble(4, currencyRate.getRate());
            statement.setString(5, currencyRate.getAbbreviation());
            statement.setDate(6,
                    java.sql.Date.valueOf(currencyRate.getDate()));
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        String sql =
                "DELETE FROM currency_rate";
        try (Statement statement = connection.createStatement()) {
            int rowsDeleted = statement.executeUpdate(sql);
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CurrencyRate currencyRate) {
        String sql =
                "UPDATE currency_rate SET r303 = ?" +
                        " AND currency_name = ?" +
                        " AND rate = ?" +
                        " AND abbreviation = ?" +
                        " AND exchange_date = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(2, currencyRate.getR030());
            statement.setString(3, currencyRate.getCurrencyName());
            statement.setDouble(4, currencyRate.getRate());
            statement.setString(5, currencyRate.getAbbreviation());
            statement.setDate(6,
                    java.sql.Date.valueOf(currencyRate.getDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize () throws Throwable {
        connection.close();
        super.finalize();
    }

    public boolean hasDatabase() throws SQLException{
        String databaseName = "test_schema";
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

    public boolean hasRow(CurrencyRate currencyRate) {
        boolean result = false;
        String sql =
                "SELECT * FROM currency_rate WHERE" +
                        " r030 = ?" +
                        " AND currency_name = ?" +
                        " AND rate = ?" +
                        " AND abbreviation = ?" +
                        " AND exchange_date = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, currencyRate.getR030());
            statement.setString(2, currencyRate.getCurrencyName());
            statement.setDouble(3, currencyRate.getRate());
            statement.setString(4, currencyRate.getAbbreviation());
            statement.setDate(5,
                    java.sql.Date.valueOf(currencyRate.getDate()));
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}