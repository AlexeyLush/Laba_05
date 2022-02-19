package files;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import commands.CommandAbstract;
import exception.ProblemWithFileException;
import files.file.FileCreator;
import files.file.FileManager;
import files.file.FileWork;
import models.Coordinates;
import models.Difficulty;
import models.LabWork;
import models.Person;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataFileManager extends FileManager implements FileWork<Integer, LabWork>, FileCreator {

    public DataFileManager(String fileName) {
        super(fileName);
        File data = new File(fileName);
        try {
            if (data.createNewFile()){
                createFile();
            }
        } catch (IOException ioException) {
            new ProblemWithFileException().outputException();
        }
    }


    @Override
    public Map<Integer, LabWork> readFile() {

        Map<Integer, LabWork> labWorkMap = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getFileName()))) {

            String s;
            Gson gson = new Gson();

            Type itemsMapType = new TypeToken<Map<Integer, LabWork>>() {}.getType();

            while((s=reader.readLine())!=null){
                LabWork labWork = gson.fromJson(s, itemsMapType);
                labWorkMap.put(labWork.getId(),labWork);
            }

        } catch (IOException e) {
            new ProblemWithFileException().outputException();
        }

        return labWorkMap;
    }

    @Override
    public void save(Map<Integer, LabWork> labWorkMap) {
        try (Writer writer = new BufferedWriter(new FileWriter(getFileName()))) {

            Gson gson = new Gson();
            String json = gson.toJson(labWorkMap);
            writer.write(json);

        } catch (IOException e) {
            new ProblemWithFileException().outputException();
        }
    }

    @Override
    public void createFile() {

        try (Writer writer = new BufferedWriter(new FileWriter(getFileName()))) {

            Map<Integer, LabWork> labWorkMap = new LinkedHashMap<>();

            LabWork labWork = new LabWork();

            labWork.setName("Лабораторная работа №1");

            Coordinates coordinates = new Coordinates();
            coordinates.setX(5L);
            coordinates.setY(3);

            labWork.setCoordinates(coordinates);
            labWork.setMinimalPoint(5f);
            labWork.setDescription("Это описание лабораторной работы №1");

            labWork.setDifficulty(Difficulty.NORMAL);

            Person author = new Person();
            author.setName("Alexey");
            author.setWeight(26L);
            author.setPassportID("336803");

            labWork.setAuthor(author);

            labWorkMap.put(labWork.getId(), labWork);

            Gson gson = new Gson();
            String json = gson.toJson(labWorkMap);

            writer.write(json);

        } catch (IOException e) {
            new ProblemWithFileException().outputException();
        }

    }
}
