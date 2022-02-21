package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;
import commands.services.checkers.LabWorkChecker;
import commands.services.spliters.SplitCommandOnIdAndJSON;
import dao.LabWorkDAO;
import exception.EmptyFieldException;
import io.ConsoleManager;
import exception.NotEnteredKeyException;
import exception.NotFoundElementException;
import exception.NotNumberException;
import models.Coordinates;
import models.Difficulty;
import models.LabWork;
import models.Person;
import models.service.GenerationID;
import services.parsers.ParserJSON;

import java.lang.reflect.Method;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UpdateCommand extends CommandAbstract {

    public UpdateCommand() {
        setTitle("update");
        setDescription("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
    }

    private LinkedHashMap<Integer, Method> updateFields = new LinkedHashMap<>();

    private boolean choosePunct(String punct, ConsoleManager consoleManager, LabWorkChecker checker, Scanner scanner, LabWork labWork){
        boolean isUpdate = true;
        try {
            int punctInt = Integer.parseInt(punct);
            switch (punctInt){
                case 0: {
                    isUpdate = false;
                    break;
                }
                case 1: {
                    String tempName = null;
                    while (!checker.isUserNameLab(tempName, labWork)) {
                        consoleManager.output("Введите название лабораторной работы: ");
                        tempName = checker.checkUserNameLab(scanner.nextLine());
                    }
                    break;
                }
                case 2: {
                    Long tempX = null;
                    while (!checker.isCoordinates(tempX, labWork.getCoordinates().getY(), labWork)) {
                        consoleManager.output("Введите координату X: ");
                        tempX = checker.checkX(scanner.nextLine());
                    }
                    break;
                }
                case 3: {
                    Integer tempY = null;
                    while (!checker.isCoordinates(labWork.getCoordinates().getX(), tempY, labWork)) {
                        consoleManager.output("Введите координату Y: ");
                        tempY = checker.checkY(scanner.nextLine());
                    }
                    break;
                }
                case 4: {
                    Float tempMinimalFloat = null;
                    while (!checker.isMinimalPoint(tempMinimalFloat, labWork)){
                        consoleManager.output("Введите минимальную точку: ");
                        tempMinimalFloat = checker.checkMinimalPoint(scanner.nextLine());
                    }
                    break;
                }
                case 5: {
                    String tempDescription = null;
                    while (!checker.isDescription(tempDescription, labWork)) {
                        consoleManager.output("Введите описание лабораторной работы: ");
                        tempDescription = checker.checkDescription(scanner.nextLine());
                    }
                    break;
                }
                case 6: {

                    Difficulty tempDifficulty = null;
                    while (!checker.isDifficulty(tempDifficulty, labWork)){
                        Difficulty[] difficulties = Difficulty.values();
                        for (int i = 0; i < difficulties.length; i++){
                            consoleManager.warning(String.format("%s",difficulties[i]));
                        }
                        consoleManager.output("Введите сложность работы: ");
                        tempDifficulty = checker.checkDifficulty(scanner.nextLine());
                    }

                    break;
                }
                case 7: {
                    String tempAuthorName = null;

                    while (tempAuthorName == null){
                        consoleManager.output("Введите имя автора: ");
                        tempAuthorName = checker.checkNamePerson(scanner.nextLine());
                    }

                    break;
                }
                case 8: {
                    Long tempAuthorWeight = null;
                    while (tempAuthorWeight == null){
                        consoleManager.output("Введите вес: ");
                        tempAuthorWeight = checker.checkWeightPerson(scanner.nextLine());
                    }
                    break;
                }
                case 9: {
                    String tempAuthorPassportId = null;
                    while (tempAuthorPassportId == null){
                        consoleManager.output("Введите ID паспорта: ");
                        tempAuthorPassportId = checker.checkPassportIdPerson(scanner.nextLine());
                    }
                    break;
                }
                default:{
                    consoleManager.error("Введите число от 1 до 9");
                }
            }
        } catch (NumberFormatException numberFormatException){
            new NotNumberException().outputException();
        }
        return isUpdate;
    }

    private int counterFiled = 1;
    private void outputFiled(String field, ConsoleManager consoleManager, boolean isUpdateField) {

        if (isUpdateField) {
            consoleManager.outpunln(String.format("%d. %s", counterFiled, field));
            counterFiled++;
        } else {
            consoleManager.outpunln(String.format("%s", field));
        }
    }

    private void showLabWorkFields(LabWork labWork, ConsoleManager consoleManager) {
        outputFiled(String.format("ID: %s", labWork.getId()), consoleManager, false);
        outputFiled(String.format("Дата создания: %s", labWork.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))), consoleManager, false);

        outputFiled(String.format("Название работы: %s", labWork.getName()), consoleManager, true);
        outputFiled(String.format("Координата X: %d", labWork.getCoordinates().getX()), consoleManager, true);
        outputFiled(String.format("Координата Y: %d", labWork.getCoordinates().getY()), consoleManager, true);
        outputFiled(String.format("Минимальная точка: %f", labWork.getMinimalPoint()), consoleManager, true);
        outputFiled(String.format("Описание: %s", labWork.getDescription()), consoleManager, true);
        outputFiled(String.format("Сложность: %s", labWork.getDifficulty().getValue()), consoleManager, true);
        outputFiled(String.format("Имя автора: %s", labWork.getAuthor().getName()), consoleManager, true);
        outputFiled(String.format("Вес: %s", labWork.getAuthor().getWeight()), consoleManager, true);
        outputFiled(String.format("ID паспорта: %s", labWork.getAuthor().getPassportID()), consoleManager, true);
        consoleManager.warning("--------------------------------------------------------");
        counterFiled = 1;
    }


    @Override
    public void execute(CommandFields commandFields) {

        LabWorkChecker checker = new LabWorkChecker();

        Scanner scanner = new Scanner(System.in);
        LabWork labWork = null;

        String[] splitCommand = new SplitCommandOnIdAndJSON().splitedCommand(commandFields.getCommand());

        try {
            String id = splitCommand[0];
            String json = splitCommand[1];
            if (id == null) {
                throw new EmptyFieldException();
            }

            int idInt = Integer.parseInt(id);

            for (Map.Entry<String, LabWork> entry : commandFields.getLabWorkDAO().getAll().entrySet()) {
                if (entry.getValue().getId().equals(idInt)) {
                    labWork = entry.getValue();
                }
            }

            if (labWork == null) {
                throw new NotFoundElementException();
            }

            if (json != null) {

                LabWork labWorkTemp = new ParserJSON().deserializeElement(json);
                boolean isLabWork = true;

                String tempName = labWorkTemp.getName();

                Coordinates tempCoordinates = labWorkTemp.getCoordinates();
                Long tempX = null;
                Integer tempY = null;

                if (tempCoordinates != null) {
                    tempX = labWorkTemp.getCoordinates().getX();
                    tempY = labWorkTemp.getCoordinates().getY();
                }

                Float tempMinimalFloat = labWorkTemp.getMinimalPoint();

                String tempDescription = labWorkTemp.getDescription();
                Difficulty tempDifficulty = labWorkTemp.getDifficulty();

                Person tempAuthor = labWorkTemp.getAuthor();
                String tempAuthorName = null;
                Long tempAuthorWeight = null;
                String tempAuthorPassportId = null;

                if (tempAuthor != null) {
                    tempAuthorName = tempAuthor.getName();
                    tempAuthorWeight = tempAuthor.getWeight();
                    tempAuthorPassportId = tempAuthor.getPassportID();
                }

                if ((!checker.isUserNameLab(tempName, labWork) || !checker.isCoordinates(tempX, tempY, labWork)
                        || !checker.isMinimalPoint(tempMinimalFloat, labWork) || !checker.isDescription(tempDescription, labWork)
                        || !checker.isDifficulty(tempDifficulty, labWork) || !checker.isPerson(tempAuthorName, tempAuthorWeight, tempAuthorPassportId, labWork)) && commandFields.isUserInput()) {

                    commandFields.getConsoleManager().warning("Некоторые элементы пустые или введены неправильно. Пожалуйста, введите корректные данные для полей");

                } else if ((!checker.isUserNameLab(tempName, labWork) || !checker.isCoordinates(tempX, tempY, labWork)
                        || !checker.isMinimalPoint(tempMinimalFloat, labWork) || !checker.isDescription(tempDescription, labWork)
                        || !checker.isDifficulty(tempDifficulty, labWork) || !checker.isPerson(tempAuthorName, tempAuthorWeight, tempAuthorPassportId, labWork)) && !commandFields.isUserInput()) {
                    isLabWork = false;
                }

                if (isLabWork) {

                    while (!checker.isUserNameLab(tempName, labWork)) {
                        commandFields.getConsoleManager().output("Введите название лабораторной работы: ");
                        tempName = checker.checkUserNameLab(scanner.nextLine());
                    }

                    while (!checker.isCoordinates(tempX, tempY, labWork)) {
                        commandFields.getConsoleManager().output("Введите координату X: ");
                        tempX = checker.checkX(scanner.nextLine());
                        while (tempX == null) {
                            commandFields.getConsoleManager().output("Введите координату X: ");
                            tempX = checker.checkX(scanner.nextLine());
                        }

                        commandFields.getConsoleManager().output("Введите координату Y: ");
                        tempY = checker.checkY(scanner.nextLine());
                        while (tempY == null) {
                            commandFields.getConsoleManager().output("Введите координату Y: ");
                            tempY = checker.checkY(scanner.nextLine());
                        }
                    }

                    while (!checker.isMinimalPoint(tempMinimalFloat, labWork)) {
                        commandFields.getConsoleManager().output("Введите минимальную точку: ");
                        tempMinimalFloat = checker.checkMinimalPoint(scanner.nextLine());
                    }

                    while (!checker.isDescription(tempDescription, labWork)) {
                        commandFields.getConsoleManager().output("Введите описание лабораторной работы: ");
                        tempDescription = checker.checkDescription(scanner.nextLine());
                    }

                    while (!checker.isDifficulty(tempDifficulty, labWork)) {
                        Difficulty[] difficulties = Difficulty.values();
                        for (int i = 0; i < difficulties.length; i++) {
                            commandFields.getConsoleManager().warning(String.format("%s", difficulties[i]));
                        }
                        commandFields.getConsoleManager().output("Введите сложность работы: ");
                        tempDifficulty = checker.checkDifficulty(scanner.nextLine());
                    }

                    while (!checker.isPerson(tempAuthorName, tempAuthorWeight, tempAuthorPassportId, labWork)) {
                        while (tempAuthorName == null) {
                            commandFields.getConsoleManager().output("Введите имя автора: ");
                            tempAuthorName = checker.checkNamePerson(scanner.nextLine());
                        }

                        while (tempAuthorWeight == null) {
                            commandFields.getConsoleManager().output("Введите вес: ");
                            tempAuthorWeight = checker.checkWeightPerson(scanner.nextLine());
                        }

                        while (tempAuthorPassportId == null) {
                            commandFields.getConsoleManager().output("Введите ID паспорта: ");
                            tempAuthorPassportId = checker.checkPassportIdPerson(scanner.nextLine());
                        }
                    }

                } else {
                    commandFields.getLabWorkDAO().update(idInt, labWorkTemp);
                    commandFields.getConsoleManager().successfully("Команда update успешно выполнена");
                }


            }
            else if (commandFields.isUserInput()) {
                showLabWorkFields(labWork, commandFields.getConsoleManager());
                commandFields.getConsoleManager().output("Выберете пункт, который хотите изменить или введите 0, чтобы завершить обновление: ");
                while (choosePunct(scanner.nextLine(), commandFields.getConsoleManager(), checker, scanner, labWork)){
                    showLabWorkFields(labWork, commandFields.getConsoleManager());
                    commandFields.getConsoleManager().output("Выберете пункт, который хотите изменить или введите 0, чтобы завершить обновление: ");
                }

            } else{
                commandFields.getConsoleManager().error("Команда update была не выполнена из-за некорректных полей");
            }

        } catch (NullPointerException nullPointerException) {
            new EmptyFieldException().outputException();
        } catch (EmptyFieldException emptyFieldException) {
            emptyFieldException.outputException();
        } catch (NumberFormatException numberFormatException) {
            new NotNumberException().outputException();
        } catch (NotFoundElementException notFoundElementException) {
            notFoundElementException.outputException();
        }


    }
}
