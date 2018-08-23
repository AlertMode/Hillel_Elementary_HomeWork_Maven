package edu.palmirov.task_31;

/*
 * Task 31
 *
 * Создать interface (functional interface) вида :
 *
 * @FunctionalInterface
 * public interface FilterStudent {
 *   boolean check(Student student);
 * }
 *
 * public class Student {
 *   private int id;
 *   private String name;
 *   private int age;
 *   // getters, setters and constructors
 * ….
 * }
 *
 * Создать List <Student> в количестве n.
 *
 * Написать методы для отбора списка студентов по следующим критериям используя lambda-выражения:
 * - все студенты id, которых меньше 5.
 * - все студенты младше 19 лет;
 * - все студенты с именами начинающимися на букву «A»;
 *
 * Покрыть методы unit-тестами.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static edu.palmirov.task_31.StudentGroupOps.*;

public class Main {
    public static void main(String[] args){
        List<Student> students = new ArrayList<>();
        students.add(new Student(234, "Michael", 24));
        students.add(new Student(2, "Franck", 17));
        students.add(new Student(3, "Alfred", 37));
        students.add(new Student(77, "Bart", 53));
        students.add(new Student(69, "Carl", 33));
        students.add(new Student(1, "Jeffrey", 29));
        students.add(new Student(2005, "agrubtionxc", 13));

        System.out.println("1. id < 5 ----");
        someAction(students, person -> person.getId() < 5)
                .forEach(System.out::println);
        System.out.println("\n2. age < 19 ----");
        someAction(students, person -> person.getAge() < 19)
                .forEach(System.out::println);
        System.out.println("\n3. first character == 'a'/'A' ----");
        someAction(students, person -> person.getName().toLowerCase().charAt(0) == 'a')
                .forEach(System.out::println);;
    }
}