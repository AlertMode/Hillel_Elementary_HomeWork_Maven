package edu.palmirov.task_37;

import static org.junit.Assert.*;

import edu.palmirov.task_34.Gender;
import edu.palmirov.task_37.enteties.Employee;
import org.junit.Test;

import java.util.*;

public class ReadersTest {
    @Test
    public void testDecoder() throws Exception{

        List<Employee> testList = Arrays.asList(
                new Employee("Ivan Ivanov", 60, 23, Gender.male, false),
                new Employee("Katerina Ivanenko", 120, 29, Gender.female, true),
                new Employee("Natasha Pavlova", 40, 19, Gender.female, false),
                new Employee("Arseniy Tatarchuk", 150, 39, Gender.male, true)
        );

        assertEquals(testList, Readers.read("input.csv"));
    }
}