package edu.palmirov.task_37;

/*
 * Task 37
 * Создать текстовый док-т с  именем input.csv в папке resource вашего проекта:
 * Файл input.csv должен иметь такой формат:
 * Format: Text Number Number Text Text
 * Field: name salary age gender married
 *
 * Пример фрагмента данного файла:
 * "Ivan Ivanov", 6000, 23, "male", "no"
 * "Katerina Ivanenko", 12000, 29, "female", "yes"
 * “Arseniy Tatarchuk”, 15000, 39, “male”, “yes”
 * “Natasha Pavlova”, 4000, 19, “female”, “no”
 *
 * В качестве разделителя (delimiter) полей используется запятая (“,”).
 * Числовые типы данных записываются в виде арабских цифр.
 * Текстовые типы данных заключаются в двойные кавычки (“some_text”)
 * Примечание: значение в поле salary выражено в гривнях.
 *
 * Также имеется имеется класс с таким набором полей:
 * class Employee {
 *   private String name;
 *   private long salary; // it holds in copeecs(cents).
 *   private int age;
 *   private Gender;
 *   private boolean married;
 * }
 * и, естественно, enum вида:
 * enum Gender {male, female}
 * Задача:
 * Реализовать класс реализующий функционал построчного преобразования строк файла в привычные java-объекты.
 * Пример алгоритма:
 * Прочитать первую строку из файла и записать в объект типа String.
 * Получить массив строк по заданному разделителю и сохранить его в переменную.
 * Создать пустой объект типа Employee и засетить поля класса Employee преобразовывая/форматируя данные хранящиеся в массиве к нужному формату полей класса Employee.
 * Обратите внимание, что в поле salary класса Employee зарплата хранится в копейках. А в файле в гривнях.
 * Note: Пример алгоритма выше дан для некоторой подсказки. Поставленная задача может быть решена и другими  способами. Но в основе всех способов лежит этот базовый механизм!
 * Цель данного задания - научиться работать с основными файловыми потоками, а также лучше понимать, что из себя представляет процесс десериализации.
 */

import edu.palmirov.task_37.customExceptions.DataFieldsNumberException;
import edu.palmirov.task_37.customExceptions.InputValueException;
import edu.palmirov.task_37.enteties.Employee;

import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = null;
        try {
            employees = Readers.read("input.csv");
        } catch (DataFieldsNumberException
                | InputValueException
                | URISyntaxException e) {
            e.getMessage();
            e.printStackTrace();
        }

        if(employees != null){
            employees.forEach(System.out::println);
        } else {
            System.err.println("No object is assigned to the link variable!");
        }
    }
}