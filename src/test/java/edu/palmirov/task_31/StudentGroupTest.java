package edu.palmirov.task_31;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static edu.palmirov.task_31.StudentGroupOps.*;
import static junit.framework.TestCase.assertEquals;

public class StudentGroupTest {

    private static List<Student> getTestList(){
        List<Student> list = new ArrayList<>();
        list.add(new Student(234, "Michael", 24));
        list.add(new Student(2, "Franck", 17));
        list.add(new Student(3, "Alfred", 37));
        list.add(new Student(77, "Bart", 53));
        list.add(new Student(69, "Carl", 33));
        list.add(new Student(1, "Jeffrey", 29));
        list.add(new Student(2005, "agrubtionxc", 13));
        return list;
    }

    @Test
    public void testSomeAction_sortById() throws Exception {
        List<Student> students = getTestList();

        List<Student> sortedStudents = new ArrayList<>();
        sortedStudents.add(new Student(2, "Franck", 17));
        sortedStudents.add(new Student(3, "Alfred", 37));
        sortedStudents.add(new Student(1, "Jeffrey", 29));
        assertEquals(sortedStudents, someAction(students, person -> person.getId() < 5));
    }

    @Test
    public void testSomeAction_sortByAge() throws Exception {
        List<Student> students = getTestList();

        List<Student> sortedStudents = new ArrayList<>();
        sortedStudents.add(new Student(2, "Franck", 17));
        sortedStudents.add(new Student(2005, "agrubtionxc", 13));
        assertEquals(sortedStudents, someAction(students, person -> person.getAge() < 19));
    }

    @Test
    public void testSomeAction_sortByFirstCharacter() throws Exception {
        List<Student> students = getTestList();

        List<Student> sortedStudetns = new ArrayList<>();
        sortedStudetns.add(new Student(3, "Alfred", 37));
        sortedStudetns.add(new Student(2005, "agrubtionxc", 13));
        assertEquals(sortedStudetns,
                someAction(students, person -> Character.toLowerCase(person.getName().charAt(0)) == 'a'));
    }
}