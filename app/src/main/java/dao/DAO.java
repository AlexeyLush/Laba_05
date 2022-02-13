package dao;

import models.LabWork;

import java.util.Map;

public interface DAO {
    int create(LabWork labWork);
    void update(LabWork labWork);
    void delete(int id);
    LabWork get(int id);
    Map<Integer, LabWork> getAll();
}
