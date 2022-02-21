package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;
import commands.services.SplitCommandOnIdAndJSON;
import dao.LabWorkDAO;
import exception.*;
import io.ConsoleManager;
import models.Coordinates;
import models.Difficulty;
import models.LabWork;
import models.Person;
import models.service.GenerationID;
import services.parsers.ParserJSON;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InsertCommand extends CommandAbstract {


    public InsertCommand() {
        setTitle("insert");
        setDescription("insert null {element}: добавить новый элемент с заданным ключом");
    }

    private boolean checkerKey(String key, LabWorkDAO labWorkDAO) {
        boolean isTrue = true;
        try {
            if (labWorkDAO.getAll().containsKey(key)) {
                throw new NotUniqueKeyException(key);
            }
        } catch (NoSuchElementException noSuchElementException) {
            new NotNumberException().outputException();
            isTrue = false;
        } catch (NotUniqueKeyException notUniqueKeyException) {
            notUniqueKeyException.outputException();
            isTrue = false;
        }
        return isTrue;
    }
    private boolean checkUserKey(String json, String key, LabWorkDAO labWorkDAO, LabWork labWork, ConsoleManager consoleManager) {
        boolean isTrue;
        if (key == null) {
            isTrue = false;
        } else if (key.isEmpty() || key.replaceAll(" ", "").replaceAll("\t", "").length() == 0) {
            consoleManager.error("Ключ не должен содеражть пустые символы (пробелы, табуляцию)!");
            isTrue = false;
        } else if (json == null) {
            if (key.contains(" ") || key.contains("\t")) {
                consoleManager.error("Ключ не должен содеражть пустые символы (пробелы, табуляцию)!");
                isTrue = false;
            } else {
                isTrue = checkerKey(key, labWorkDAO);
            }
        } else {
            isTrue = checkerKey(key, labWorkDAO);
            if (!(new ParserJSON().isDeserializeElement(json))) {
                isTrue = false;
            }
        }

        if (isTrue) {
            consoleManager.successfully(String.format("Ключ %s был успешно установлен!", key));
        }
        return isTrue;
    }
    private String checkUserNameLab(String name){
        String returnName = null;

        LabWork labWork = new LabWork();
        try {
            if (name == null){
                throw new EmptyFieldException();
            }

            if (!labWork.setName(name)){
                throw new EmptyFieldException();
            }
            returnName = name;
        } catch (EmptyFieldException emptyFieldException){
            emptyFieldException.outputException();
        }

        return returnName;
    }
    private boolean isUserNameLab(String name, LabWork labWork){
        boolean isTrue = true;
        try{
            if (!labWork.setName(name)){
                throw new EmptyFieldException();
            }
        } catch (EmptyFieldException emptyFieldException){
            isTrue = false;
        }
        return isTrue;
    }

    private Long checkX(String x){
        Long returnX = null;
        Coordinates tempCoordinates = new Coordinates();
        try {
            returnX = Long.parseLong(x);
            if (!tempCoordinates.setX(returnX)){
                throw new NumberLongerException(tempCoordinates.getMaxCoordinateX());
            }
        } catch (NumberFormatException numberFormatException){
            new NotNumberException().outputException();
            returnX = null;
        } catch (NumberLongerException numberLongerException) {
            numberLongerException.outputException();
            returnX = null;
        }
        return returnX;
    }
    private Integer checkY(String y){
        Integer returnY = null;
        try {
            returnY = Integer.parseInt(y);
        } catch (NumberFormatException numberFormatException){
            new NotNumberException().outputException();
        }
        return returnY;
    }
    private boolean isCoordinates(Long x, Integer y, LabWork labWork){
        boolean isTrue = true;
        Coordinates coordinates = new Coordinates();
        try {
            if (x == null || y == null){
                throw new NotNumberException();
            }
            coordinates.setX(x);
            coordinates.setY(y);
            labWork.setCoordinates(coordinates);
        } catch (NotNumberException ignored){
            isTrue = false;
        }
        return isTrue;
    }
    private Float checkMinimalPoint(String minimalPoint){
        Float returnMinimalPoint = null;
        LabWork tempLabWork = new LabWork();
        try {
            if (minimalPoint == null){
                throw new NotNumberException();
            }

            returnMinimalPoint = Float.parseFloat(minimalPoint);
            if (!tempLabWork.setMinimalPoint(returnMinimalPoint)){
                throw new NumberMinimalException(0);
            }
        } catch (NotNumberException notNumberException){
            notNumberException.outputException();
        } catch (NumberFormatException numberFormatException){
            new NotNumberException().outputException();
        } catch (NumberMinimalException numberMinimalException) {
            numberMinimalException.outputException();
            returnMinimalPoint = null;
        }
        return returnMinimalPoint;
    }
    private boolean isMinimalPoint(Float minimalPoint, LabWork labWork){
        boolean isTrue = true;
        try {
            if (minimalPoint == null){
                throw new NotNumberException();
            }
            labWork.setMinimalPoint(minimalPoint);
        } catch (NotNumberException ignored){
            isTrue = false;
        }
        return isTrue;
    }

    private String checkDescription(String description){
        String returnDescription = null;
        LabWork tempLabWork = new LabWork();

        try {
            if (description == null){
                throw new EmptyFieldException();
            }
            returnDescription = description;
            if (!tempLabWork.setDescription(returnDescription)){
                throw new EmptyFieldException();
            }

        } catch (EmptyFieldException emptyFieldException){
            emptyFieldException.outputException();
            returnDescription = null;
        }
        return returnDescription;
    }
    private boolean isDescription(String description, LabWork labWork){
        boolean isTrue = true;
        try {
            if (description == null){
                throw new EmptyFieldException();
            }
            isTrue = labWork.setDescription(description);

            if (!isTrue){
                throw new EmptyFieldException();
            }
        } catch (EmptyFieldException emptyFieldException){
            isTrue = false;
        }
        return isTrue;
    }

    private Difficulty checkDifficulty(String difficultyString){
        Difficulty difficulty = Difficulty.isDifficulty(difficultyString);

        try{
            if (difficulty == null){
                throw new NotFoundEnumException();
            }
        } catch (NotFoundEnumException notFoundEnumException) {
            notFoundEnumException.outputException();
        }

        return difficulty;
    }
    private boolean isDifficulty(Difficulty difficulty, LabWork labWork){
        boolean isTrue = true;

        try {
            if (difficulty == null){
                throw new NotFoundEnumException();
            }
            labWork.setDifficulty(difficulty);
        } catch (NotFoundEnumException notFoundEnumException) {
            isTrue = false;
        }
        return isTrue;
    }

    private String checkNamePerson(String name){

        String returnName = null;
        Person person = new Person();
        try {
            if (name == null){
                throw new EmptyFieldException();
            }
            returnName = name;
            if (!person.setName(returnName)){
                returnName = null;
                throw new EmptyFieldException();
            }
        } catch (EmptyFieldException emptyFieldException) {
            emptyFieldException.outputException();
        }
        return returnName;
    }
    private Long checkWeightPerson(String weight){

        Long returnWeight = null;
        Person person = new Person();
        try{
            if (weight == null){
                throw new EmptyFieldException();
            }
            returnWeight = Long.parseLong(weight);
            if (!person.setWeight(returnWeight)){
                throw new NumberMinimalException(0);
            }
        } catch (EmptyFieldException emptyFieldException){
            emptyFieldException.outputException();
        } catch (NumberFormatException numberFormatException){
            new NotNumberException().outputException();
        } catch (NumberMinimalException numberMinimalException) {
            numberMinimalException.outputException();
        }
        return returnWeight;
    }
    private String checkPassportIdPerson(String passport){

        String returnPassportId = null;
        Person person = new Person();
        try {
            if (passport == null){
                throw new EmptyFieldException();
            }
            returnPassportId = passport;
            if (!person.setPassportID(returnPassportId)){
                returnPassportId = null;
                throw new EmptyFieldException();
            }
        } catch (EmptyFieldException emptyFieldException) {
            emptyFieldException.outputException();
        }
        return returnPassportId;
    }

    private boolean isPerson(String name, Long weight, String passportId, LabWork labWork){
        boolean isTrue = true;

        try {
            if (name == null || weight == null || passportId == null){
                throw new EmptyFieldException();
            }

            Person author = new Person();
            author.setName(name);
            author.setWeight(weight);
            author.setPassportID(passportId);

            labWork.setAuthor(author);

        } catch (EmptyFieldException emptyFieldException){
            isTrue = false;
        }
        return isTrue;
    }


    @Override
    public void execute(CommandFields commandFields) {

        Scanner scanner = new Scanner(System.in);
        LabWork labWork = new LabWork();

        String[] splitCommand = new SplitCommandOnIdAndJSON().splitedCommand(commandFields.getCommand());

        String key = splitCommand[0];
        String json = splitCommand[1];

        LabWork labWorkTemp = new LabWork();

        if (json != null){
            labWorkTemp = new ParserJSON().deserializeElement(json);
        }

        boolean isLabWork = true;

        while (!checkUserKey(json, key, commandFields.getLabWorkDAO(), labWork, commandFields.getConsoleManager())) {
            if (commandFields.isUserInput()) {
                commandFields.getConsoleManager().output("Введите ключ: ");
                key = scanner.nextLine();
            } else {
                isLabWork = false;
                break;
            }
        }

        String tempName = labWorkTemp.getName();
        while (!isUserNameLab(tempName, labWork)){
            if (commandFields.isUserInput()) {
                commandFields.getConsoleManager().output("Введите название лабораторной работы: ");
                tempName = checkUserNameLab(scanner.nextLine());
            } else {
                isLabWork = false;
                break;
            }
        }

        Coordinates tempCoordinates = labWorkTemp.getCoordinates();
        Long tempX = null;
        Integer tempY = null;

        if (tempCoordinates != null){
            tempX = labWorkTemp.getCoordinates().getX();
            tempY = labWorkTemp.getCoordinates().getY();
        }

        while (!isCoordinates(tempX, tempY, labWork)){
            if (commandFields.isUserInput()) {

                commandFields.getConsoleManager().output("Введите координату X: ");
                tempX = checkX(scanner.nextLine());
                while (tempX == null){
                    commandFields.getConsoleManager().output("Введите координату X: ");
                    tempX = checkX(scanner.nextLine());
                }

                commandFields.getConsoleManager().output("Введите координату Y: ");
                tempY = checkY(scanner.nextLine());
                while (tempY == null){
                    commandFields.getConsoleManager().output("Введите координату Y: ");
                    tempY = checkY(scanner.nextLine());
                }


            } else {
                isLabWork = false;
                break;
            }
        }


        Float tempMinimalFloat = labWorkTemp.getMinimalPoint();

        while (!isMinimalPoint(tempMinimalFloat, labWork)){
            if (commandFields.isUserInput()){
                commandFields.getConsoleManager().output("Введите минимальную точку: ");
                tempMinimalFloat = checkMinimalPoint(scanner.nextLine());
            }
        }


        String tempDescription = labWorkTemp.getDescription();
        while (!isDescription(tempDescription, labWork)){
            if (commandFields.isUserInput()){
                commandFields.getConsoleManager().output("Введите описание лабораторной работы: ");
                tempDescription = checkDescription(scanner.nextLine());
            }
        }

        Difficulty tempDifficulty = labWorkTemp.getDifficulty();
        while (!isDifficulty(tempDifficulty, labWork)){
            if (commandFields.isUserInput()){
                Difficulty[] difficulties = Difficulty.values();
                for (int i = 0; i < difficulties.length; i++){
                    commandFields.getConsoleManager().warning(String.format("%s",difficulties[i]));
                }
                commandFields.getConsoleManager().output("Введите сложность работы: ");
                tempDifficulty = checkDifficulty(scanner.nextLine());
            }
        }


        Person tempAuthor = labWorkTemp.getAuthor();
        String tempAuthorName = null;
        Long tempAuthorWeight = null;
        String tempAuthorPassportId = null;

        if (tempAuthor != null){
            tempAuthorName = tempAuthor.getName();
            tempAuthorWeight = tempAuthor.getWeight();
            tempAuthorPassportId = tempAuthor.getPassportID();
        }


        while (!isPerson(tempAuthorName, tempAuthorWeight, tempAuthorPassportId, labWork)){
            if (commandFields.isUserInput()){

                while (tempAuthorName == null){
                    commandFields.getConsoleManager().output("Введите имя автора: ");
                    tempAuthorName = checkNamePerson(scanner.nextLine());
                }

                while (tempAuthorWeight == null){
                    commandFields.getConsoleManager().output("Введите вес: ");
                    tempAuthorWeight = checkWeightPerson(scanner.nextLine());
                }

                while (tempAuthorPassportId == null){
                    commandFields.getConsoleManager().output("Введите ID паспорта: ");
                    tempAuthorPassportId = checkPassportIdPerson(scanner.nextLine());
                }

            }
        }

        commandFields.getLabWorkDAO().create(key, labWork);

    }
}
