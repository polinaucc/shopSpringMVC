package com.example.lab2.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> findAll();
    T findById(int id);
    T create (T t);
    boolean updateById(T t, int id);
    boolean deleteById(int id);
}
