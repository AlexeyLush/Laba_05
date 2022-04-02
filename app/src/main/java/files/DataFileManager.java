package files;


import dao.LabWorkDAO;
import files.file.FileCreator;
import files.file.FileManager;
import files.file.FileWork;
import files.file.FileWorkMap;
import io.ConsoleManager;
import models.*;
import models.service.GenerationID;
import org.checkerframework.checker.units.qual.C;
import services.checkers.LabWorkChecker;
import services.elementProcces.LabWorkProcess;
import services.model.ModelParse;
import services.parsers.ParserJSON;

import java.io.*;
import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Класс для работы с данными файлов
 */

public class DataFileManager extends FileManager implements FileWorkMap<String, LabWork>, FileCreator, FileWork {

    private ConsoleManager consoleManager;
    private final String tempFileName;
    private boolean isMainFile;

    public DataFileManager(String fileName, String tempFileName, ConsoleManager consoleManager, boolean isMainFile) {
        super(fileName);
        this.consoleManager = consoleManager;
        this.isMainFile = isMainFile;
        this.tempFileName = tempFileName;
        initialFile(fileName);
    }
    public void initialFile(String fileName) {
        File data = new File(fileName);
        try {
            if (data.createNewFile()) {
                createFile();
            }
        } catch (IOException ioException) {
            consoleManager.error("Во время работы программы возникла проблема с файлом");
        }
    }

    @Override
    public Map<String, LabWork> readMap() {

        Map<String, LabWork> labWorkMap = null;
        LabWorkChecker labWorkChecker = new LabWorkChecker();

        try {
            ParserJSON parserJSON = new ParserJSON(consoleManager);
            BufferedReader reader = new BufferedReader(new FileReader(getFileName()));
            String s = "";
            String temp = "";

            while ((temp = reader.readLine()) != null) {
                s += temp;
            }

            if (ZonedDateTime.parse(parserJSON.getDataFromFile(s)) == null){
                throw new IOException();
            }

            labWorkMap = parserJSON.deserializeMap(s);
            LabWorkDAO labWorkDAO = new LabWorkDAO();

            int maxId = 0;

            List<Integer> listId = new ArrayList<>();
            for (Map.Entry<String, LabWork> entry : labWorkMap.entrySet()) {
                if (maxId < entry.getValue().getId()) {
                    maxId = entry.getValue().getId();
                }
                String key = labWorkChecker.checkUserKey(entry.getKey(), labWorkDAO, consoleManager, true, false );
                Integer id = labWorkChecker.checkId(entry.getValue().getId().toString(), consoleManager, false);
                ZonedDateTime dateTime = labWorkChecker.checkDate(entry.getValue().getCreationDate().toString(), consoleManager, false);
                String name = labWorkChecker.checkNamePerson(entry.getValue().getName(), consoleManager, false);
                Long coordX = labWorkChecker.checkX(entry.getValue().getCoordinates().getX().toString(), consoleManager, false);
                Integer coordY = labWorkChecker.checkY(entry.getValue().getCoordinates().getY().toString(), consoleManager, false);
                Float minimalPoint = labWorkChecker.checkMinimalPoint(entry.getValue().getMinimalPoint().toString(), consoleManager, false);
                String description = labWorkChecker.checkDescription(entry.getValue().getDescription(), consoleManager, false);
                Difficulty difficulty = labWorkChecker.checkDifficulty(entry.getValue().getDifficulty().toString(), consoleManager, false);
                String authorName = labWorkChecker.checkNamePerson(entry.getValue().getAuthor().getName(), consoleManager, false);
                Long authorWeight = labWorkChecker.checkWeightPerson(entry.getValue().getAuthor().getWeight().toString(), consoleManager, false);
                String authorPassportId = labWorkChecker.checkPassportIdPerson(entry.getValue().getAuthor().getPassportID(), consoleManager, false);

                if (listId.contains(id)){
                    throw new IOException();
                }

                if (key == null || id == null || dateTime == null || name == null || coordX == null || coordY == null
                        || minimalPoint == null || description == null || difficulty == null
                        || authorName == null || authorWeight == null || authorPassportId == null){
                    throw new IOException();
                }
                listId.add(id);
                LabWork labWork = new LabWork(id, name, new Coordinates(coordX, coordY), dateTime, minimalPoint, description, difficulty,
                        new Person(authorName, authorWeight, authorPassportId));
                labWorkDAO.create(key, labWork);
            }

            GenerationID.setId(maxId + 1);

            reader.close();

            if (isMainFile){
                consoleManager.successfully("Данные успешно считаны!");
            } else {
                consoleManager.successfully("Данные с временного файла успешно считаны!");
            }


        } catch (IOException | NullPointerException | DateTimeException e) {
            consoleManager.error("Во время работы произошла ошибка");
            createFile();
            labWorkMap = readMap();
        }

        return labWorkMap;
    }

    @Override
    public void save(Map<String, LabWork> labWorkMap) {
        String jsonWithDate = new ParserJSON(consoleManager).jsonForWrite(readFile(), labWorkMap);
        if (jsonWithDate != null){
            try (Writer writer = new BufferedWriter(new FileWriter(getFileName()))) {
                writer.write(jsonWithDate);
            } catch (IOException e) {
                consoleManager.error("Во время работы программы возникла проблема с файлом");
            }
        } else {
            consoleManager.error("Во время работы программы возникла проблема с файлом");
        }

    }

    @Override
    public void createFile() {

        ModelParse modelParse = new ModelParse();
        try {
            if (isMainFile){
                consoleManager.warning("Идёт создание файла...");
            }
            else{
                consoleManager.warning("Идёт создание временного файла...");
            }
            Writer writer = new BufferedWriter(new FileWriter(getFileName()));
            consoleManager.warning("Идёт запись значениями по умолчанию...");
            Map<String, LabWork> labWorkMap = new LinkedHashMap<>();
            LabWork labWork = new LabWork();
            modelParse.setDate(labWork.getCreationDate());
            labWork.setId(GenerationID.newId());

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

            labWorkMap.put("1", labWork);


            modelParse.setCollection(labWorkMap);
            String json = new ParserJSON(consoleManager).serializeModelParse(modelParse);
            writer.write(json);

            writer.close();

            if (isMainFile){
                consoleManager.successfully("Файл успешно создан!");
            }

            else{
                consoleManager.successfully("Временный файл успешно создан!");
            }

        } catch (IOException e) {
            consoleManager.error("Во время работы программы возникла проблема с файлом");
        }
    }

    @Override
    public String readFile() {

        String s = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(getFileName()))) {

            String temp = "";

            while ((temp = reader.readLine()) != null) {
                s += temp;
            }

        } catch (IOException | NullPointerException e) {
            consoleManager.error("Во время работы программы возникла проблема с файлом");
        }

        return s;
    }
}
