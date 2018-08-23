package edu.palmirov.task_31;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentGroupOps {
    /**
     * Returns selected by the input condition list.
     * @param condition An instruction how to check each list's element.
     * @param list A list of Student elements.
     * @return newList
     */
    public static List<Student> someAction(List<Student> list, FilterStudent condition){
        return list.stream().filter(condition::check).collect(Collectors.toList());
    }
}