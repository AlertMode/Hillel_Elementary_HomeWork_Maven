package edu.palmirov.task_35;

/*
 * Task 35
 * Написать метод принимающий на вход Stream<Employee> в качестве входного аргумента.
 * Метод должен вернуть тип List<Employee>.
 */


import edu.palmirov.task_34.Employee;
import edu.palmirov.task_34.Gender;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        List<Employee> employeeList = convert(getStream());
        employeeList.forEach(System.out::println);
    }

    public static List<Employee> convert(Stream<Employee> stream){
        return stream.collect(Collectors.toList());
    }

    public static Stream<Employee> getStream(){
        Stream<Employee> employeeStream = Stream.of(
                new Employee("John Barton", 120000L, 47, Gender.male, false),
                new Employee("Frank Landon", 90000L, 27, Gender.male, true),
                new Employee("Liza Kimberly", 10000L, 25, Gender.female, false),
                new Employee("Mike Bing", 45000L, 23, Gender.male, false),
                new Employee("Ann Landon", 60000L, 27, Gender.female, true),
                new Employee("Claire Paxton", 90000L, 36, Gender.female, true),
                new Employee("Helen Kimberly", 130000L, 35, Gender.female, true),
                new Employee("Bryan Copeland", 92000L, 48, Gender.male, false),
                new Employee("Agnes Byth", 100000L, 33, Gender.female, false),
                new Employee("Claire Paxton", 50000L, 26, Gender.female, false),
                new Employee("Mike Ramsey", 87000L, 39, Gender.male, true),
                new Employee("Liza Barton", 123000L, 43, Gender.female, true),
                new Employee("Agnes Byth", 34900L, 41, Gender.female, false));
                return employeeStream;
    }
}