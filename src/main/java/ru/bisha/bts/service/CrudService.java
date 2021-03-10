package ru.bisha.bts.service;

import java.util.List;

public interface CrudService<T,  I> {

    List<T> findAll();

    T findById(I id);

    T save(T object);

    void delete(T object);

    void deleteById(I id);
}
