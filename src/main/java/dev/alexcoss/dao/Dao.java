package dev.alexcoss.dao;

import java.util.List;

public interface Dao<T> {
    void addItem(T item);

    T getItem(int id);

    void updateItem(int id, T item);

    void deleteItem(int id);

    List<T> index();
}
