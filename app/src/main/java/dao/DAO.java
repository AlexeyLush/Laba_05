package dao;

import models.LabWork;

import java.util.Map;

public interface DAO<K,V> {
    int create(V labWork);
    void update(V labWork);
    void delete(int id);
    void initialMap(Map<K, V> elements);
    void clear();
    V get(int id);
    Map<K, V> getAll();
}
