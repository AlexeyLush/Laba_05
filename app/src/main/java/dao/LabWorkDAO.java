package dao;

import commands.CommandAbstract;
import models.LabWork;
import models.service.GenerationID;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Класс для реализации работы с коллекцией LabWork
 */



public class LabWorkDAO implements DAO<String, LabWork>{


    private Map<String, LabWork> labWorkList = new LinkedHashMap<>();

    @Override
    public int create(String key, LabWork labWork) {
        labWork.setId(GenerationID.newId());
        labWorkList.put(key, labWork);
        return labWork.getId();
    }

    @Override
    public void update(String key, LabWork labWork) {

        LabWork labWorkInList = get(key);

        if (labWorkInList != null){
            labWorkList.replace(key, labWork);
        }

    }

    @Override
    public void delete(String key) {

        LabWork labWork = get(key);

        if (labWork != null){
            labWorkList.remove(key);
        }

    }

    @Override
    public void initialMap(Map<String, LabWork> elements) {
        this.labWorkList = elements;

    }

    @Override
    public void clear() {
        labWorkList = new LinkedHashMap<>();
    }

    @Override
    public LabWork get(String key) {
        return labWorkList.get(key);
    }

    @Override
    public Map<String, LabWork> getAll() {
        return new LinkedHashMap<>(labWorkList);
    }
}
