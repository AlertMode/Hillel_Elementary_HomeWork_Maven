package edu.palmirov.task_41;

/*
* Task 41
* Используя данный URL получить xml-представление о всех курсах валют представленных НБУ.
* https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange
*
* Распарсить его и получить информацию о курсе американского доллара.
* Вывести на экран.
*
* По желанию*:
* Сформируйте класс CurrencyRate заполните его распарсенными значениями для всех типов валют.
* Создайте DAO слой и воспользуйтесь им для записи значений в базу данных.
* Также предусмотрите в DAO слое различные методы для удобной манипуляции с данными, по крайней мере, основные CRUD операции.
*/

import edu.palmirov.task_41.dbLogic.CurrencyRateDB;
import edu.palmirov.task_41.enteties.CurrencyRate;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args){
        CurrencyRateDB currencyRateDB = databaseConnection(getCurrencyRateList());
        List<CurrencyRate> currencyRateList = currencyRateDB.findAll();
        System.out.println("\nExtracting and converting records from database...");
        currencyRateList.forEach(System.out::println);
        System.out.println("\nDeleting the records...");
        currencyRateDB.deleteAll();
        currencyRateList = currencyRateDB.findAll();
        if(currencyRateList.isEmpty()){
            System.out.println("The list is empty!");
        } else {
            currencyRateList.forEach(System.out::println);
        }
    }

    public static List<CurrencyRate> getCurrencyRateList(){
        String URL_SOURCE =
                "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";
        CurrencyRateServiceHandler currencyRateServiceHandler = new CurrencyRateServiceHandler();
        try {
            URL urlObject = new URL(URL_SOURCE);
            InputStream inputStream = urlObject.openStream();
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(currencyRateServiceHandler);
            InputSource inputSource = new InputSource(inputStream);
            xmlReader.parse(inputSource);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return currencyRateServiceHandler.getCurrencyRateList();
    }

    public static CurrencyRateDB databaseConnection(List<CurrencyRate> currencyRateList){
        CurrencyRateDB currencyRateDB =
                new CurrencyRateDB(
                        "jdbc:mysql://127.0.0.1:3306/mysql" +
                                "?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC",
                        "root",
                        "root"
                );
        try {
            currencyRateDB.buildSchema();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Adding records to database...");
        currencyRateDB.buildTable();
        for(CurrencyRate currencyRate : currencyRateList){
            if(!currencyRateDB.hasRow(currencyRate)){
                currencyRateDB.create(currencyRate);
            }
        }
        return currencyRateDB;
    }
}