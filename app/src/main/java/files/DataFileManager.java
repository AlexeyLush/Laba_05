package files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import commands.CommandAbstract;
import exception.ProblemWithFileException;
import files.file.FileCreator;
import files.file.FileManager;
import files.file.FileWork;
import io.ConsoleManager;
import models.*;
import models.service.GenerationID;
import services.parsers.ParserJSON;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataFileManager extends FileManager implements FileWork<String, LabWork>, FileCreator {

    public DataFileManager(String fileName, ConsoleManager consoleManager) {
        super(fileName, consoleManager);
        File data = new File(fileName);
        try {
            if (data.createNewFile()){
                getConsoleManager().warning("Идёт создание файла...");
                createFile();
                getConsoleManager().successfully("Файл успешно создан!");
            }
        } catch (IOException ioException) {
            new ProblemWithFileException().outputException();
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

            labWorkMap = new ParserJSON().deserializeMap(s);



            for (Map.Entry<String, LabWork> entry : labWorkMap.entrySet()) {

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


        } catch (IOException | NullPointerException e) {
            new ProblemWithFileException().outputException();
            getConsoleManager().warning("Идёт перезапись файла значениями по умолчанию...");
            createFile();
            getConsoleManager().successfully("Файл успешно перезаписан!");
            getConsoleManager().warning("Идёт повторное считывание данных...");
            labWorkMap = readFile();
            getConsoleManager().successfully("Данные успешно считаны!");
        }
        return labWorkMap;
    }

    @Override
    public void save(Map<String, LabWork> labWorkMap) {
        try (Writer writer = new BufferedWriter(new FileWriter(getFileName()))) {

            String json = new ParserJSON().serializeMap(labWorkMap);
            writer.write(json);

        } catch (IOException e) {
            new ProblemWithFileException().outputException();
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


            String json = new ParserJSON().serializeMap(labWorkMap);
            writer.write(json);


        } catch (IOException e) {
            new ProblemWithFileException().outputException();
        }

    }
}
