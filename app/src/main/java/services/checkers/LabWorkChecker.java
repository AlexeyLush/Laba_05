package services.checkers;

import dao.LabWorkDAO;
import exception.*;
import io.ConsoleManager;
import models.Coordinates;
import models.Difficulty;
import models.LabWork;
import models.Person;
import services.parsers.ParserJSON;

import java.util.NoSuchElementException;

public class LabWorkChecker extends Checker {

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
    public boolean checkUserKey(String json, String key, LabWorkDAO labWorkDAO, ConsoleManager consoleManager, boolean isUnique) {
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
        try {

            if (name == null || name.isEmpty() || name.replaceAll(" ", "").replaceAll("\t", "").length() == 0){
                throw new EmptyFieldException();
            }
            returnName = name;
        } catch (EmptyFieldException emptyFieldException){
            emptyFieldException.outputException();
        }

        return returnName;
    }
    public Long checkX(String x){
        Long returnX = null;
        Coordinates tempCoordinates = new Coordinates();
        try {
            returnX = Long.parseLong(x);
            if (returnX > tempCoordinates.getMaxCoordinateX()){
                returnX = null;
                throw new NumberLongerException(tempCoordinates.getMaxCoordinateX());
            }
        } catch (NumberFormatException numberFormatException){
            new NotNumberException().outputException();
        } catch (NumberLongerException numberLongerException) {
            numberLongerException.outputException();
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
    public Float checkMinimalPoint(String minimalPoint){
        Float returnMinimalPoint = null;
        try {
            if (minimalPoint == null){
                throw new NotNumberException();
            }
            returnMinimalPoint = Float.parseFloat(minimalPoint);
            if (returnMinimalPoint <= 0){
                returnMinimalPoint = null;
                throw new NumberMinimalException(0);
            }
        } catch (NotNumberException notNumberException){
            notNumberException.outputException();
        } catch (NumberFormatException numberFormatException){
            new NotNumberException().outputException();
        } catch (NumberMinimalException numberMinimalException) {
            numberMinimalException.outputException();
        }
        return returnMinimalPoint;
    }
    public String checkDescription(String description){
        String returnDescription;
        try {
            returnDescription = description;
            if (description == null || description.isEmpty() || description.replaceAll(" ", "").replaceAll("\t", "").length() == 0){
                throw new EmptyFieldException();
            }

        } catch (EmptyFieldException emptyFieldException){
            emptyFieldException.outputException();
            returnDescription = null;
        }
        return returnDescription;
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
    public String checkNamePerson(String name){
        String returnName = null;
        try {
            returnName = name;
            if (name == null || name.isEmpty() || name.replaceAll(" ", "").replaceAll("\t", "").length() == 0){
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
        try{
            if (weight == null){
                throw new EmptyFieldException();
            }
            returnWeight = Long.parseLong(weight);
            if (returnWeight <= 0){
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
        try {
            returnPassportId = passport;
            if (returnPassportId == null || returnPassportId.isEmpty() || returnPassportId.replaceAll(" ", "").replaceAll("\t", "").length() == 0){
                returnPassportId = null;
                throw new EmptyFieldException();
            }
        } catch (EmptyFieldException emptyFieldException) {
            emptyFieldException.outputException();
        }
        return returnPassportId;
    }
}
