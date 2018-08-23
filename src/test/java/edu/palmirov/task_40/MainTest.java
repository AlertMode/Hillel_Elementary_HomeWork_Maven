package edu.palmirov.task_40;

import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Connection;

public class MainTest {

    public Connection connection = Main.getConnection();

    public MainTest() throws Exception {}

    @Test
    public void testOneMarqueModels() throws Exception{
        assertEquals(MySQLFunctions.fromDatabase(
                connection, "SELECT model FROM automobiles WHERE marque = 'Mercedes-Benz'").toString(),
                "[W123 300]");
    }

    @Test
    public void testVehiclesWithPriceLimitations() throws Exception {
        assertEquals(MySQLFunctions.fromDatabase(
                connection, "SELECT * FROM automobiles WHERE price < 4000").toString(),
                "[2, Волга, 3102, Manual Transmission, 1989-01-01, 2000.00, 156000, USSR," +
                        " 3, Mercedes-Benz, W123 300, Manual Transmission, 1982-01-01, 3000.00, 2397514, West Germany," +
                        " 5, АвтоВАЗ, ВАЗ-2108 Спутник, Manual Transmission, 1985-01-01, 1600.00, 884657, USSR]");
    }

    @Test
    public void testOneCountryOnlyProduction() throws Exception{
        assertEquals(MySQLFunctions.fromDatabase(connection,
                "SELECT marque, model FROM automobiles WHERE producing_country = 'Japan'").toString(),
                "[Toyota, Celica ZZT231]");
    }

    @Test
    public void testTheMostExpensiveAuto() throws Exception {
        assertEquals(MySQLFunctions.fromDatabase(connection,
                "SELECT * FROM automobiles WHERE price = (SELECT MAX(price) FROM automobiles)").toString(),
                "[1, Lamborghini, Countach LP400, Manual Transmission, 1974-01-01, 1272827.00, 158, Italy]");
    }

    @Test
    public void testTheCheapestExpensiveAuto() throws Exception {
        assertEquals(MySQLFunctions.fromDatabase(connection,
                "SELECT * FROM automobiles WHERE price = (SELECT MIN(price) FROM automobiles)").toString(),
                "[5, АвтоВАЗ, ВАЗ-2108 Спутник, Manual Transmission, 1985-01-01, 1600.00, 884657, USSR]");
    }
}