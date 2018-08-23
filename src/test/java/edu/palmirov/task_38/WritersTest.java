package edu.palmirov.task_38;

import static org.junit.Assert.*;

import edu.palmirov.task_34.Gender;
import edu.palmirov.task_37.Readers;
import edu.palmirov.task_37.customExceptions.DataFieldsNumberException;
import edu.palmirov.task_37.customExceptions.InputValueException;
import edu.palmirov.task_37.enteties.Employee;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;


public class WritersTest {
    @Test
    public void testWrite(){
        List<Employee> testList = Arrays.asList(
                new Employee("Ivan Ivanov", 60, 23, Gender.male, false),
                new Employee("Katerina Ivanenko", 370, 29, Gender.female, true),
                new Employee("Natasha Pavlova", 40, 19, Gender.female, false),
                new Employee("Arseniy Tatarchuk", 650, 39, Gender.male, true)
        );

        List<Employee> employees = null;
        try {
            employees = Readers.read("input.csv");
        } catch (DataFieldsNumberException
                | InputValueException
                | URISyntaxException e) {
            e.getMessage();
            e.printStackTrace();
        }

        List<Employee> employeesTest = null;
        try {
            employeesTest = Readers.read("output.csv");
        } catch (DataFieldsNumberException
                | InputValueException
                | URISyntaxException e) {
            e.getMessage();
            e.printStackTrace();
        }
        assertEquals(testList, employeesTest);
    }
}