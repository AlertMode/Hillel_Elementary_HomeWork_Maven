package edu.palmirov.task_33;

@FunctionalInterface
public interface DoOnlyAction<T> {
    T todo(String s);
}