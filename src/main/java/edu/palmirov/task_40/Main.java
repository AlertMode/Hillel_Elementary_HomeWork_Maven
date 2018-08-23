package edu.palmirov.task_40;

/*
 * Task 40
 *
 * В БД хранится информация о автомобилях, продающихся в автосалоне.
 * Тип базы данных значения не имеет (mysql, postgres, oracle, mssql, db2 и пр.).
 *
 * Для авто необходимо хранить:
 * — марку (например: Mercedes, BMW, LADA);
 * — модель (например: E230, X5, Kalina);
 * — тип коробки передач (например: manual, auto, robot);
 * — Год выпуска;
 * — цену;
 * — количество;
 * — Страна производителя.
 *
 * Используя возможности JDBC:
 * • Вывести доступные модели по заданной марке автомобиля;
 * • Вывести всю информацию о автомобилях цена которых не превышает заданную сумму;
 * • Вывести марки и модели автомобилей изготовленных в заданной стране.
 * • Вывести всю доступную информацию о самом дорогом автомобиле.
 * • Вывести всю доступную информацию о самом бюджетном автомобиле.
 */

import edu.palmirov.task_40.enteties.Automobile;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection connection = getConnection();

        System.out.println("\nTest: getting information from BD to Class");
        List<String> list = MySQLFunctions.fromDatabase(connection, "SELECT * FROM automobiles");
        List<Automobile> automobiles = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            automobiles.add(new Automobile(list.get(i)));
        }
        automobiles.forEach(e -> System.out.println(e.toString()));

        System.out.println("\nTask #1: Get only one marques models' list:");
        MySQLFunctions.fromDatabase(connection, "SELECT model FROM automobiles WHERE marque = 'Mercedes-Benz'")
                .forEach(System.out::println);

        System.out.println("\nTask #2: Get vehicle's list where the prices is not more than 3000 $:");
        MySQLFunctions.fromDatabase(connection, "SELECT * FROM automobiles WHERE price < 4000")
                .forEach(System.out::println);

        System.out.println("\nTask #3: Get vehicle marque's and model's list where the producing country is Japan:");
        MySQLFunctions.fromDatabase(connection,
                "SELECT marque, model FROM automobiles WHERE producing_country = 'Japan'")
                .forEach(System.out::println);

        System.out.println("\nTask #4: Get the most expensive vehicle's info:");
        MySQLFunctions.fromDatabase(connection,
                "SELECT * FROM automobiles WHERE price = (SELECT MAX(price) FROM automobiles)")
                .forEach(System.out::println);

        System.out.println("\nTask #5: Get The cheapest vehicle's info:");
        MySQLFunctions.fromDatabase(connection,
                "SELECT * FROM automobiles WHERE price = (SELECT MIN(price) FROM automobiles)")
                .forEach(System.out::println);
    }

    public static List getAutomobileList(){
        return new ArrayList<>(Arrays.asList(
                new Automobile(
                        "Lamborghini",
                        "Countach LP400",
                        Automobile.Transmission.MT,
                        1974,
                        1_272_827,
                        158,
                        "Italy"),
                new Automobile(
                        "Волга",
                        "3102",
                        Automobile.Transmission.MT,
                        1989,
                        2000,
                        156_000,
                        "USSR"),
                new Automobile(
                        "Mercedes-Benz",
                        "W123 300",
                        Automobile.Transmission.MT,
                        1982,
                        3000,
                        2_397_514,
                        "West Germany"),
                new Automobile(
                        "Toyota",
                        "Celica ZZT231",
                        Automobile.Transmission.AT,
                        2006,
                        5000,
                        70_000,
                        "Japan"),
                new Automobile(
                        "АвтоВАЗ",
                        "ВАЗ-2108 Спутник",
                        Automobile.Transmission.MT,
                        1985,
                        1600,
                        884_657,
                        "USSR")
        ));
    }

    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/mysql" +
                "?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC";
        String username = "root";
        String password = "root";

        Connection connection = MySQLFunctions.connectToDB(
                url,
                username,
                password);
        String query = "CREATE DATABASE test_schema";
        Statement statement = connection.createStatement();
        if(!MySQLFunctions.hasDatabase(connection, "test_schema")){
            statement.executeUpdate(query);
        }
        statement.executeUpdate("USE test_schema");
        statement.executeUpdate(new Automobile().createTableQuery());
        statement.close();

        List<Automobile> automobileList = getAutomobileList();

        connection.setAutoCommit(false);

        for(int i = 0; i < automobileList.size(); i++){
            if(!MySQLFunctions.nextRecord(
                    connection,
                    "SELECT * FROM automobiles WHERE " +
                            automobileList.get(i).sqlRaw() + ";")){
                MySQLFunctions.toDatabase(connection, automobileList.get(i));
            }
        }
        return connection;
    }
}