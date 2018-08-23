package edu.palmirov.task_36;

/*
 * Task 36
 * Написать метод принимающий на вход Stream<Employee> в качестве входного аргумента.
 * Метод должен вернуть среднее арифметическое заработных плат сотрудников.
 * Покройте этот метод unit-тестами используя необходимые для этого методы из класса Optional.
 * Для того чтобы реализовать это задание воспользуйтесь методом
 * mapToDouble() таким образом:
 * OptionalDouble average = streamEmployee.mapToDouble(value -> value.getSalary()).average();
 * Напишите тест, который проверяет вычисление среднего арифметического значения.
 */

import edu.palmirov.task_34.Employee;
import static edu.palmirov.task_35.Main.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        System.out.println(getArithmeticAverageSalary(getStream()).isPresent()
                ? new BigDecimal(getArithmeticAverageSalary(getStream()).getAsDouble())
                    .setScale(2, RoundingMode.HALF_UP)
                : OptionalDouble.empty());
    }

    public static OptionalDouble getArithmeticAverageSalary(Stream<Employee> employeeStream){
        return employeeStream.mapToDouble(Employee::getSalary).average();
    }
}