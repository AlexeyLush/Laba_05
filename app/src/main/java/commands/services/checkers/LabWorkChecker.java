package commands.services.checkers;

import dao.LabWorkDAO;
import exception.*;
import io.ConsoleManager;
import models.Coordinates;
import models.Difficulty;
import models.LabWork;
import models.Person;
import services.parsers.ParserJSON;

import java.util.NoSuchElementException;

public class LabWorkChecker {

    public boolean checkerKey(String key, LabWorkDAO labWorkDAO) {
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
    public boolean checkUserKey(String json, String key, LabWorkDAO labWorkDAO, LabWork labWork, ConsoleManager consoleManager, boolean isUnique) {
        boolean isTrue = true;
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
                if (isUnique){
                    isTrue = checkerKey(key, labWorkDAO);
                }
            }
        } else {
            if (isUnique){
                isTrue = checkerKey(key, labWorkDAO);
            }
            if (!(new ParserJSON(consoleManager).isDeserializeElement(json))) {
                isTrue = false;
            }
        }
        return isTrue;
    }
    public String checkUserNameLab(String name){
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
    public boolean isUserNameLab(String name, LabWork labWork){
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
    public Long checkX(String x){
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
    public Integer checkY(String y){
        Integer returnY = null;
        try {
            returnY = Integer.parseInt(y);
        } catch (NumberFormatException numberFormatException){
            new NotNumberException().outputException();
        }
        return returnY;
    }
    public boolean isCoordinates(Long x, Integer y, LabWork labWork){
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
    public Float checkMinimalPoint(String minimalPoint){
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
    public boolean isMinimalPoint(Float minimalPoint, LabWork labWork){
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

    public String checkDescription(String description){
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
    public boolean isDescription(String description, LabWork labWork){
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

    public Difficulty checkDifficulty(String difficultyString){
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
    public boolean isDifficulty(Difficulty difficulty, LabWork labWork){
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

    public String checkNamePerson(String name){

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
    public Long checkWeightPerson(String weight){

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
    public String checkPassportIdPerson(String passport){

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

    public boolean isPerson(String name, Long weight, String passportId, LabWork labWork){
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
}
