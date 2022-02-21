package dao;

import models.LabWork;

/**
 * Интерфейс для работы DAO
 */

import java.util.Map;

public interface DAO<K extends Comparable,V extends Comparable> {
    int create(K key, V labWork);
    void update(int id, V labWork);
    void delete(K key);
    void initialMap(Map<K, V> elements);
    void clear();
    Map<K, V> sort(Map<K, V> map);
    V get(K key);
    Map<K, V> getAll();
}
