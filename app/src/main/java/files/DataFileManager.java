package files;

import com.google.gson.Gson;
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
import java.util.LinkedHashMap;
import java.util.Map;

public class DataFileManager extends FileManager implements FileWork<Integer, CommandAbstract>, FileCreator {

    public DataFileManager(String fileName) {
        super(fileName);
        File data = new File(fileName);
        try {
            if (data.createNewFile()){
                createFile();
            }
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    @Override
    public boolean isCreatedFile() {
        return false;
    }


    @Override
    public void write(CommandAbstract element) {

    }

    @Override
    public void writeMap(Map<Integer, CommandAbstract> elements) {

    }

    @Override
    public Map<Integer, CommandAbstract> readFile() {
        return null;
    }

    @Override
    public void save() {

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
