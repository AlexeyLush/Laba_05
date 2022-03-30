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

    public boolean checkerKey(String key, LabWorkDAO labWorkDAO, ConsoleManager consoleManager, boolean withError) {
        boolean isTrue = true;
        try {
            if (labWorkDAO.getAll().containsKey(key)) {
                isTrue = false;
                throw new NotUniqueKeyException(key);
            }
        } catch (NoSuchElementException noSuchElementException) {
            if (withError){
                consoleManager.error("Введите число");
            }
            isTrue = false;
        } catch (NotUniqueKeyException notUniqueKeyException) {
            if (withError){
                notUniqueKeyException.outputException();
            }
        }
        return isTrue;
    }
    public boolean checkUserKey(String json, String key, LabWorkDAO labWorkDAO, ConsoleManager consoleManager, boolean isUnique, boolean withError) {
        boolean isTrue = true;
        if (key == null) {
            isTrue = false;
        } else if (key.isEmpty() || key.replaceAll(" ", "").replaceAll("\t", "").length() == 0) {
            if (withError){
                consoleManager.error("Ключ не должен содеражть пустые символы (пробелы, табуляцию)!");
            }
            isTrue = false;
        } else if (json == null) {
            if (key.contains(" ") || key.contains("\t")) {
                if (withError){
                    consoleManager.error("Ключ не должен содеражть пустые символы (пробелы, табуляцию)!");
                }
                isTrue = false;
            } else {
                if (isUnique){
                    isTrue = checkerKey(key, labWorkDAO, consoleManager, withError);
                }
            }
        } else {
            if (isUnique){
                isTrue = checkerKey(key, labWorkDAO, consoleManager, withError);
            }
            if (!(new ParserJSON(consoleManager).isDeserializeElement(json))) {
                isTrue = false;
            }
        }
        return isTrue;
    }
    public Integer checkId(String id, ConsoleManager consoleManager, boolean withError){
        Integer returnId = null;

        try{
            if (id == null || id.isEmpty() || id.replaceAll(" ", "").replaceAll("\t", "").length() == 0){
                throw new EmptyFieldException();
            }
            returnId = Integer.parseInt(id);
            if (returnId < 0){
                returnId = null;
                throw new NotPositiveNumberException();
            }
        } catch (EmptyFieldException e){
            if (withError){
                e.outputException();
            }
        } catch (NotPositiveNumberException e) {
            if (withError){
                e.outputException();
            }
        }

        return returnId;

    }
    public String checkUserNameLab(String name, ConsoleManager consoleManager, boolean withError){

        String returnName = null;
        try {
            returnName = name;
            if (name == null || name.isEmpty() || name.replaceAll(" ", "").replaceAll("\t", "").length() == 0){
                returnName = null;
                throw new EmptyFieldException();
            }
        } catch (EmptyFieldException emptyFieldException){
            if (withError){
                emptyFieldException.outputException();
            }
        }
        return returnName;
    }
    public Long checkX(String x, ConsoleManager consoleManager, boolean withError){
        Long returnX = null;
        Coordinates tempCoordinates = new Coordinates();
        try {
            returnX = Long.parseLong(x);
            if (returnX > tempCoordinates.getMaxCoordinateX()){
                returnX = null;
                throw new NumberLongerException(tempCoordinates.getMaxCoordinateX());
            }
        } catch (NumberFormatException numberFormatException){
            if (withError){
                consoleManager.error("Введите число");
            }
        } catch (NumberLongerException numberLongerException) {
            if (withError){
                numberLongerException.outputException();
            }
        }
        return returnX;
    }
    public Integer checkY(String y, ConsoleManager consoleManager, boolean withError){
        Integer returnY = null;
        try {
            returnY = Integer.parseInt(y);
        } catch (NumberFormatException numberFormatException){
            if (withError){
                consoleManager.error("Введите число");
            }
        }
        return returnY;
    }
    public Float checkMinimalPoint(String minimalPoint, ConsoleManager consoleManager, boolean withError){
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
            if (withError){
                notNumberException.outputException();
            }
        } catch (NumberFormatException numberFormatException){
            if (withError){
                consoleManager.error("Введите число");
            }
        } catch (NumberMinimalException numberMinimalException) {
            if (withError){
                numberMinimalException.outputException();
            }
        }
        return returnMinimalPoint;
    }
    public String checkDescription(String description, ConsoleManager consoleManager, boolean withError){
        String returnDescription;
        try {
            returnDescription = description;
            if (description == null || description.isEmpty() || description.replaceAll(" ", "").replaceAll("\t", "").length() == 0){
                returnDescription = null;
                throw new EmptyFieldException();
            }

        } catch (EmptyFieldException emptyFieldException){
            if (withError){
                emptyFieldException.outputException();
            }
            returnDescription = null;
        }
        return returnDescription;
    }
    public Difficulty checkDifficulty(String difficultyString, ConsoleManager consoleManager, boolean withError){
        Difficulty difficulty = Difficulty.isDifficulty(difficultyString);

        try{
            if (difficulty == null){
                throw new NotFoundEnumException();
            }
        } catch (NotFoundEnumException notFoundEnumException) {
            if (withError){
                notFoundEnumException.outputException();
            }
        }

        return difficulty;
    }
    public String checkNamePerson(String name, ConsoleManager consoleManager, boolean withError){
        String returnName = null;
        try {
            returnName = name;
            if (name == null || name.isEmpty() || name.replaceAll(" ", "").replaceAll("\t", "").length() == 0){
                returnName = null;
                throw new EmptyFieldException();
            }
        } catch (EmptyFieldException emptyFieldException) {
            if (withError){

                emptyFieldException.outputException();
            }
        }
        return returnName;
    }
    public Long checkWeightPerson(String weight, ConsoleManager consoleManager, boolean withError){

        Long returnWeight = null;
        try{
            if (weight == null){
                throw new EmptyFieldException();
            }
            returnWeight = Long.parseLong(weight);
            if (returnWeight <= 0){
                returnWeight = null;
                throw new NumberMinimalException(0);
            }
        } catch (EmptyFieldException emptyFieldException){
            if (withError){
                emptyFieldException.outputException();
            }
        } catch (NumberFormatException numberFormatException){
            if (withError){
                consoleManager.error("Введите число");
            }
        } catch (NumberMinimalException numberMinimalException) {
            if (withError){
                numberMinimalException.outputException();
            }
        }
        return returnWeight;
    }
    public String checkPassportIdPerson(String passport, ConsoleManager consoleManager, boolean withError){
        String returnPassportId = null;
        try {
            returnPassportId = passport;
            if (returnPassportId == null || returnPassportId.isEmpty() || returnPassportId.replaceAll(" ", "").replaceAll("\t", "").length() == 0){
                returnPassportId = null;
                throw new EmptyFieldException();
            }
        } catch (EmptyFieldException emptyFieldException) {
            if (withError){
                emptyFieldException.outputException();
            }
        }
        return returnPassportId;
    }
}
