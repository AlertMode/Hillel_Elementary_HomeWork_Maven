package edu.palmirov.task_34;

/*
 * Task 34
 *
 * Дано:
 * enum Gender (male, female);
 *
 * Имеется класс Employee, который содержит 5 полей:
 * String name, Long Salary, int age, Gender gender, boolean married.
 *
 * А также некоторый список сотрудников List<Employee>. Некоторые сотрудники имеет могут иметь одинаковые имена.
 *
 * Получить stream и используя Stream API отобрать сотрудников по критериям:
 * - получить первого женатого мужчину + вывести его имя в консоль;
 * - получить любую незамужнюю женщину + вывести её имя в консоль;
 * - >45 лет и только мужчин + вывести их имена в консоль;
 * - <30 лет и только женщин + вывести их имена в консоль;
 * - между 33 и 37 годами как мужчин так и женщин + вывести их имена в консоль;
 * - точное количество незамужних женщин + вывести это кол-во в консоль;
 * - получить только зарплаты мужчин старше 30 лет + вывести каждую ЗП в консоль;
 * - получить сотрудников только с уникальными именами + вывести в консоль их имена.
 *
 * Note: покрывать unit-тестами код не надо.
*/

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){

        //Displays the first married man's name that has been found in the <Employee> list.
        System.out.println("1. >> Married man:");
        selector(getList(), e -> e.getGender() == Gender.male && e.isMarried())
                .findFirst().ifPresent(e -> System.out.println(e.getName()));

        //Displays any unmarried woman's name found in <Employee> list.
        System.out.println("\n2. >> Unmarried woman:");
        selector(getList(), e-> e.getGender() == Gender.female && !e.isMarried())
                .findAny().ifPresent(e -> System.out.println(e.getName()));

        //Displays only men's names over 45 years of age.
        System.out.println("\n3. >> Men over 45 years of age:");
        selector(getList(), e -> e.getGender() == Gender.male && e.getAge() > 45)
                .forEach(e -> System.out.println(e.getName()));

        //Displays only women's names under 30 years of age.
        System.out.println("\n4. >> Women under 30 years of age:");
        selector(getList(), e -> e.getGender() == Gender.female && e.getAge() < 30)
                .forEach(e -> System.out.println(e.getName()));

        //Displays employees' names between 33 and 37 years of age.
        System.out.println("\n5. >> Employees between 33 and 37 years of age:");
        selector(getList(), e -> e.getAge() >= 33 && e.getAge() <= 37)
                .forEach(e -> System.out.println(e.getName()));

        //Displays the quantity of unmarried woman.
        System.out.println("\n6. >> Unmarried women's quantity:\n" +
        selector(getList(), e -> e.getGender() == Gender.female && !e.isMarried()).count());

        //Displays only salaries of men over 30 years of age.
        System.out.println("\n7. >> Over 30 years of age men's salaries:");
        selector(getList(), e -> e.getGender() == Gender.male && e.getAge() > 30)
                .forEach(e -> System.out.println(e.getSalary()));

        //Displays only unique names from <Employee> list.
        System.out.println("\n8. Unique names:");
//        Set<String> set = new HashSet<>(getList().size());
//        selector(getList(), p -> set.add(p.getName())).forEach(e -> System.out.println(e.getName()));
        getList().stream().map(Employee::getName).distinct().forEach(System.out::println);
    }

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

    /**
     * Returns selected by the input condition list.
     * @param condition An instruction how to check each list's element.
     * @param list A list of <Employee> elements.
     * @return newList
     */
    public static Stream<Employee> selector(List<Employee> list, Predicate<Employee> condition){
        return list.stream().filter(condition);
    }
}