package edu.palmirov.task_41.interfaces;

import edu.palmirov.task_41.enteties.CurrencyRate;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    boolean create(T item);

    Optional<CurrencyRate> findByAbbreviation(String abbr);

    List<T> findAll();

    void delete(T item);

    void deleteAll();

    void update(T item);
}