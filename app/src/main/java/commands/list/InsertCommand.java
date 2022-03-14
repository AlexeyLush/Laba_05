package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;
import commands.services.checkers.LabWorkChecker;
import commands.services.spliters.SplitCommandOnIdAndJSON;
import models.Coordinates;
import models.Difficulty;
import models.LabWork;
import models.Person;
import services.parsers.ParserJSON;

import java.util.Scanner;

/**
 * Команда добавления нового элемента с заданным ключом
 */

public class InsertCommand extends CommandAbstract {


    public InsertCommand() {
        setTitle("insert");
        setDescription("insert null {element}: добавить новый элемент с заданным ключом");
    }

    @Override
    public void execute(CommandFields commandFields) {

        LabWorkChecker checker = new LabWorkChecker();

        LabWork labWork = new LabWork();

        String[] splitCommand = new SplitCommandOnIdAndJSON().splitedCommand(commandFields.getCommand());

        String key = splitCommand[0];
        String json = splitCommand[1];

        LabWork labWorkTemp = new LabWork();

        if (json != null){
            labWorkTemp = new ParserJSON().deserializeElement(json);
        }

        boolean isLabWork = true;

        while (!checker.checkUserKey(json, key, commandFields.getLabWorkDAO(), labWork, commandFields.getConsoleManager())) {
            if (commandFields.isUserInput()) {
                commandFields.getConsoleManager().output("Введите ключ: ");
                key = commandFields.getScanner().nextLine();
            } else {
                isLabWork = false;
                break;
            }
        }


        String tempName = labWorkTemp.getName();
        while (!checker.isUserNameLab(tempName, labWork)){
            if (commandFields.isUserInput()) {
                commandFields.getConsoleManager().output("Введите название лабораторной работы: ");
                tempName = checker.checkUserNameLab(commandFields.getScanner().nextLine());
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

        while (!checker.isCoordinates(tempX, tempY, labWork)){
            if (commandFields.isUserInput()) {

                commandFields.getConsoleManager().output("Введите координату X: ");
                tempX = checker.checkX(commandFields.getScanner().nextLine());
                while (tempX == null){
                    commandFields.getConsoleManager().output("Введите координату X: ");
                    tempX = checker.checkX(commandFields.getScanner().nextLine());
                }

                commandFields.getConsoleManager().output("Введите координату Y: ");
                tempY = checker.checkY(commandFields.getScanner().nextLine());
                while (tempY == null){
                    commandFields.getConsoleManager().output("Введите координату Y: ");
                    tempY = checker.checkY(commandFields.getScanner().nextLine());
                }


            } else {
                isLabWork = false;
                break;
            }
        }


        Float tempMinimalFloat = labWorkTemp.getMinimalPoint();

        while (!checker.isMinimalPoint(tempMinimalFloat, labWork)){
            if (commandFields.isUserInput()){
                commandFields.getConsoleManager().output("Введите минимальную точку: ");
                tempMinimalFloat = checker.checkMinimalPoint(commandFields.getScanner().nextLine());
            } else {
                isLabWork = false;
                break;
            }
        }


        String tempDescription = labWorkTemp.getDescription();
        while (!checker.isDescription(tempDescription, labWork)){
            if (commandFields.isUserInput()){
                commandFields.getConsoleManager().output("Введите описание лабораторной работы: ");
                tempDescription = checker.checkDescription(commandFields.getScanner().nextLine());
            }  else {
                isLabWork = false;
                break;
            }
        }

        Difficulty tempDifficulty = labWorkTemp.getDifficulty();
        while (!checker.isDifficulty(tempDifficulty, labWork)){
            if (commandFields.isUserInput()){
                Difficulty[] difficulties = Difficulty.values();
                for (int i = 0; i < difficulties.length; i++){
                    commandFields.getConsoleManager().warning(String.format("%s",difficulties[i]));
                }
                commandFields.getConsoleManager().output("Введите сложность работы: ");
                tempDifficulty = checker.checkDifficulty(commandFields.getScanner().nextLine());
            }  else {
                isLabWork = false;
                break;
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


        while (!checker.isPerson(tempAuthorName, tempAuthorWeight, tempAuthorPassportId, labWork)){
            if (commandFields.isUserInput()){

                while (tempAuthorName == null){
                    commandFields.getConsoleManager().output("Введите имя автора: ");
                    tempAuthorName = checker.checkNamePerson(commandFields.getScanner().nextLine());
                }

                while (tempAuthorWeight == null){
                    commandFields.getConsoleManager().output("Введите вес: ");
                    tempAuthorWeight = checker.checkWeightPerson(commandFields.getScanner().nextLine());
                }

                while (tempAuthorPassportId == null){
                    commandFields.getConsoleManager().output("Введите ID паспорта: ");
                    tempAuthorPassportId = checker.checkPassportIdPerson(commandFields.getScanner().nextLine());
                }

            } else {
                isLabWork = false;
                break;
            }
        }

        if (!isLabWork){
            commandFields.getConsoleManager().error("Команда insert была не выполнена из-за некорректных полей");
        } else{
            commandFields.getLabWorkDAO().create(key, labWork);
            commandFields.getConsoleManager().successfully("Команда insert успешно выполнена");
        }

    }
}
