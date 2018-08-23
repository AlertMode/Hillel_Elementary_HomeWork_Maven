package edu.palmirov.task_38;

/* Task 38
 * Перед началом выполнения этого задания создайте папку с именем “res” в корне дерева вашего проекта.
 * Задача:
 * Прочитать данные из файла о сотрудниках. Получить List<Employee>. Используйте наработки из Task37.
 * Увеличьте зарплату для сотрудников по следующему критерию. Для тех кто старше 25 лет,
 * но младше 30 увеличить на 250, а для всех кто старше 30 увеличить на 500.
 * Кто не попадает под этот критерий оставить как есть.
 * Записать обновленные данные в файл по следующему пути:
 * “/res/output.csv” в том же формате как данные и хранились в оригинальном файле.
 *
 * Пример готового результата:
 * "Ivan Ivanov", 6000, 23, "male", "no"
 * "Katerina Ivanenko", 12250, 29, "female", "yes"
 * “Arseniy Tatarchuk”, 15500, 39, “male”, “yes”
 * “Natasha Pavlova”, 4000, 19, “female”, “no”
 *
 * Примечание:
 * Для того чтобы добавить к какой-то строке кавычки нужно применить так называемое экранирование “\”. Например,
 * String s = “\”Приехали…\” - вяло подумал Литовченко”;
 * System.out.println(s);
 */

import edu.palmirov.task_37.Readers;
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

        System.out.println("Hillel_Elementary_HomeWork_Maven\\src\\main\\resources\\input.csv");

        if(employees != null){
            employees.forEach(System.out::println);
        } else {
            System.err.println("No object is assigned to the link variable!");
        }

        Writers.writeToCSV(employees);



        List<Employee> employeesTest = null;
        try {
            employeesTest = Readers.read("output.csv");
        } catch (DataFieldsNumberException
                | InputValueException
                | URISyntaxException e) {
            e.getMessage();
            e.printStackTrace();
        }

        System.out.println("\nHillel_Elementary_HomeWork_Maven\\res\\output.csv");

        if(employeesTest != null){
            employeesTest.forEach(System.out::println);
        } else {
            System.err.println("No object is assigned to the link variable!");
        }
    }
}