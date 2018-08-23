package edu.palmirov.task_32;

@FunctionalInterface
public interface Strategy {
    boolean sayStop(int currentValue);
}