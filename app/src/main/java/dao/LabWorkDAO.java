package dao;

import commands.CommandAbstract;
import models.LabWork;
import models.service.GenerationID;
import models.service.LabWorkComparator;

import java.util.*;

public class LabWorkDAO implements DAO<String, LabWork>{

    private Map<String, LabWork> labWorkList = new LinkedHashMap<>();



    @Override
    public int create(String key, LabWork labWork) {

        labWork.setId(GenerationID.newId());

        for (Map.Entry<String, LabWork> entry : labWorkList.entrySet()) {
            if (entry.getValue().getId().equals(labWork.getId())){
                labWork.setId(GenerationID.newId());
            }
        }

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
    public Map<String, LabWork> sort(Map<String, LabWork> map) {

        Map<String, LabWork> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<String,LabWork>> entries = new ArrayList<>(map.entrySet());

        entries.sort(new LabWorkComparator());

        for(Map.Entry<String, LabWork> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    @Override
    public LabWork get(String key) {
        return labWorkList.get(key);
    }

    @Override
    public Map<String, LabWork> getAll() {
        return new LinkedHashMap<>(sort(labWorkList));
    }
}
