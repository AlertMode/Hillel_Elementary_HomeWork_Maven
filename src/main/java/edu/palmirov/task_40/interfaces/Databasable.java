package edu.palmirov.task_40.interfaces;

public interface Databasable {
    String createTableQuery();
    String toDatabase();
    void fromDatabase(String sqlString);
}