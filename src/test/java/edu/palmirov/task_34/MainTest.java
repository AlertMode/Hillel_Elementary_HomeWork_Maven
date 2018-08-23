package edu.palmirov.task_34;

import org.junit.Test;
import static edu.palmirov.task_34.Main.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MainTest {
    private static List<Employee> getList(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("John Barton", 120000L, 47, Gender.male, false));
        employeeList.add(new Employee("Frank Landon", 90000L, 27, Gender.male, true));
        employeeList.add(new Employee("Liza Kimberly", 10000L, 25, Gender.female, false));
        employeeList.add(new Employee("Mike Bing", 45000L, 23, Gender.male, false));
        employeeList.add(new Employee("Ann Landon", 60000L, 27, Gender.female, true));
        employeeList.add(new Employee("Claire Paxton", 90000L, 36, Gender.female, true));
        employeeList.add(new Employee("Helen Kimberly", 130000L, 35, Gender.female, true));
        employeeList.add(new Employee("Bryan Copeland", 92000L, 48, Gender.male, false));
        employeeList.add(new Employee("Agnes Byth", 100000L, 33, Gender.female, false));
        employeeList.add(new Employee("Claire Paxton", 50000L, 26, Gender.female, false));
        employeeList.add(new Employee("Mike Ramsey", 87000L, 39, Gender.male, true));
        employeeList.add(new Employee("Liza Barton", 123000L, 43, Gender.female, true));
        employeeList.add(new Employee("Agnes Byth", 34900L, 41, Gender.female, false));
        return employeeList;
    }

    @Test
    public void testGetMarriedMan() throws Exception {
        assertTrue(selector(getList(), e -> e.getGender() == Gender.male && e.isMarried())
                        .findFirst()
                        .filter(employee ->
                                employee.equals(new Employee("Frank Landon", 90000L, 27, Gender.male, true)))
                        .isPresent());
    }


    @Test
    public void testGetUnmarriedWoman() throws Exception {
        assertTrue(selector(getList(), e -> e.getGender() == Gender.female && !e.isMarried())
                .findAny()
                .filter(employee ->
                        employee.equals(new Employee("Liza Kimberly", 10000L, 25, Gender.female, false)))
                .isPresent());
    }

    @Test
    public void testGetMenOverFortyFive() throws Exception {
        assertEquals(2, selector(getList(), e -> e.getGender() == Gender.male && e.getAge() > 45).count());
    }

    @Test
    public void testGetWomenUnderThirty() throws Exception {
        assertEquals(3, selector(getList(), e -> e.getGender() == Gender.female && e.getAge() < 30).count());
    }

    @Test
    public void testGetEmployeeBetweenThirtyThreeAndThirtySeven() throws Exception {
        assertEquals(3, selector(getList(), e -> e.getAge() > 30 && e.getAge() < 37).count());
    }

    @Test
    public void testGetUnmarriedWomenQuantity() throws Exception {
        assertEquals(4, selector(getList(), e -> e.getGender() == Gender.female && !e.isMarried())
                .count());
    }

    @Test
    public void testGetOverThirtyYearsMenSalaries() throws Exception {
        assertEquals(3, selector(getList(), e -> e.getGender() == Gender.male && e.getAge() > 30).count());
    }

    @Test
    public void testGetUniqueNames() throws Exception {
        /*List<Employee> testList = new ArrayList<>();
        testList.add(new Employee("John Barton", 120000L, 47, Gender.male, false));
        testList.add(new Employee("Frank Landon", 90000L, 27, Gender.male, true));
        testList.add(new Employee("Liza Kimberly", 10000L, 25, Gender.female, false));
        testList.add(new Employee("Mike Bing", 45000L, 23, Gender.male, false));
        testList.add(new Employee("Ann Landon", 60000L, 27, Gender.female, true));
        testList.add(new Employee("Claire Paxton", 90000L, 36, Gender.female, true));
        testList.add(new Employee("Helen Kimberly", 130000L, 35, Gender.female, true));
        testList.add(new Employee("Bryan Copeland", 92000L, 48, Gender.male, false));
        testList.add(new Employee("Agnes Byth", 100000L, 33, Gender.female, false));
        testList.add(new Employee("Mike Ramsey", 87000L, 39, Gender.male, true));
        testList.add(new Employee("Liza Barton", 123000L, 43, Gender.female, true));

        Set<String> set = new HashSet<>(getList().size());
        assertEquals(testList, selector(getList(), p -> set.add(p.getName())));*/

        List<String> namesList = new ArrayList<>();
        namesList.add("John Barton");
        namesList.add("Frank Landon");
        namesList.add("Liza Kimberly");
        namesList.add("Mike Bing");
        namesList.add("Ann Landon");
        namesList.add("Claire Paxton");
        namesList.add("Helen Kimberly");
        namesList.add("Bryan Copeland");
        namesList.add("Agnes Byth");
        namesList.add("Mike Ramsey");
        namesList.add("Liza Barton");

        assertEquals(namesList,
                getList().stream().map(Employee::getName).distinct().collect(Collectors.toList()));
    }
}