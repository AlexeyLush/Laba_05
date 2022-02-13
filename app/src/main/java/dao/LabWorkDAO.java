package dao;

import models.LabWork;

import java.util.LinkedHashMap;
import java.util.Map;

public class LabWorkDAO implements DAO{

    private Map<Integer, LabWork> labWorkList = new LinkedHashMap<Integer, LabWork>();

    @Override
    public int create(LabWork labWork) {
        labWorkList.put(labWork.getId(), labWork);
        return labWork.getId();
    }

    @Override
    public void update(LabWork labWork) {

        LabWork labWorkInList = get(labWork.getId());

        if (labWorkInList != null){
            labWorkList.replace(labWorkInList.getId(), labWork);
        }

    }

    @Override
    public void delete(int id) {

        LabWork labWork = get(id);

        if (labWork != null){
            labWorkList.remove(id);
        }

    }

    @Override
    public LabWork get(int id) {
        return labWorkList.get(id);
    }

    @Override
    public Map<Integer, LabWork> getAll() {
        return new LinkedHashMap<>(labWorkList);
    }
}
