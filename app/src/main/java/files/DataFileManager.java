package files;


import files.file.FileCreator;
import files.file.FileManager;
import files.file.FileWork;
import io.ConsoleManager;
import models.*;
import models.service.GenerationID;
import services.parsers.ParserJSON;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс для работы с данными файлов
 */

public class DataFileManager extends FileManager implements FileWork<String, LabWork>, FileCreator {

    private ConsoleManager consoleManager;

    public DataFileManager(String fileName, ConsoleManager consoleManager) {
        super(fileName);
        this.consoleManager = consoleManager;
        initialFile(fileName);
    }

    public void initialFile(String fileName){
        File data = new File(fileName);
        try {
            if (data.createNewFile()){
                consoleManager.warning("Идёт создание файла...");
                createFile();
                consoleManager.successfully("Файл успешно создан!");
            }
        } catch (IOException ioException) {
            consoleManager.error("Во время работы программы возникла проблема с файлом");
        }
    }

    @Override
    public Map<String, LabWork> readFile() {


        Map<String, LabWork> labWorkMap = new LinkedHashMap<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(getFileName()))) {

            String s = "";
            String temp = "";

            while((temp=reader.readLine())!=null) {
                s += temp;
            }

            labWorkMap = new ParserJSON(consoleManager).deserializeMap(s);

            int maxId = 0;

            for (Map.Entry<String, LabWork> entry : labWorkMap.entrySet()) {

                if (maxId < entry.getValue().getId()){
                    maxId = entry.getValue().getId();
                }

                String tempName = entry.getValue().getName();

                Coordinates tempCoordinates = entry.getValue().getCoordinates();
                Long tempX = tempCoordinates.getX();
                Integer tempY = tempCoordinates.getY();


                Float tempMinimalFloat = entry.getValue().getMinimalPoint();

                String tempDescription = entry.getValue().getDescription();
                Difficulty tempDifficulty = entry.getValue().getDifficulty();

                Person tempAuthor = entry.getValue().getAuthor();
                String tempAuthorName = tempAuthor.getName();
                Long tempAuthorWeight = tempAuthor.getWeight();
                String tempAuthorPassportId = tempAuthor.getPassportID();
            }

            GenerationID.setId(maxId + 1);


        } catch (IOException | NullPointerException e) {
            consoleManager.error("Во время работы программы возникла проблема с файлом");
            consoleManager.warning("Идёт перезапись файла значениями по умолчанию...");
            createFile();
            consoleManager.successfully("Файл успешно перезаписан!");
            consoleManager.warning("Идёт повторное считывание данных...");
            labWorkMap = readFile();
            consoleManager.successfully("Данные успешно считаны!");
        }
        return labWorkMap;
    }

    @Override
    public void save(Map<String, LabWork> labWorkMap) {
        try (Writer writer = new BufferedWriter(new FileWriter(getFileName()))) {

            String json = new ParserJSON(consoleManager).serializeMap(labWorkMap);
            writer.write(json);

        } catch (IOException e) {
            consoleManager.error("Во время работы программы возникла проблема с файлом");
        }
    }

    @Override
    public void createFile() {

        try (Writer writer = new BufferedWriter(new FileWriter(getFileName()))) {

            Map<String, LabWork> labWorkMap = new LinkedHashMap<>();


            LabWork labWork = new LabWork();
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


            String json = new ParserJSON(consoleManager).serializeMap(labWorkMap);
            writer.write(json);


        } catch (IOException e) {
            consoleManager.error("Во время работы программы возникла проблема с файлом");
        }

    }
}
