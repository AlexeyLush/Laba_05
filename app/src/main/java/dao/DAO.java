package dao;

import models.LabWork;

/**
 * Интерфейс для работы DAO
 */

import java.util.Map;

public interface DAO<K,V> {
    int create(K key, V labWork);
    void update(K key, V labWork);
    void delete(K key);
    void initialMap(Map<K, V> elements);
    void clear();
    V get(K key);
    Map<K, V> getAll();
}
