package edu.palmirov.task_36;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.OptionalDouble;

import static edu.palmirov.task_35.Main.getStream;
import static edu.palmirov.task_36.Main.getArithmeticAverageSalary;

public class MainTest {
    @Test
    public void testArithmeticAverageSalary() throws Exception {
        assertEquals(OptionalDouble.of(79376.92307692308), getArithmeticAverageSalary(getStream()));
    }
}