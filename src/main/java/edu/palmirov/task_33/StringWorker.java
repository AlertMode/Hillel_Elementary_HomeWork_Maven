package edu.palmirov.task_33;

public class StringWorker {
    @SuppressWarnings("unchecked")
    public static <T>T act(DoOnlyAction action, String s){
        return (T) action.todo(s);
    }
}