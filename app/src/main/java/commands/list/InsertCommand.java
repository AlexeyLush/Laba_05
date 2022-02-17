package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import exception.*;
import io.ConsoleManager;
import models.Coordinates;
import models.Difficulty;
import models.LabWork;
import models.Person;
import models.service.GenerationID;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InsertCommand extends CommandAbstract {

    public InsertCommand() {
        setTitle("insert");
        setDescription("insert null {element}: добавить новый элемент с заданным ключом");
    }

    private void checkIdUser(String command, Scanner scanner, LabWork labWork, LabWorkDAO labWorkDAO, ConsoleManager consoleManager){

        int id;
        String commandTemp = command;

        String idStr = "";

        if (commandTemp.split(" ").length > 1){
            idStr = commandTemp.split(" ")[1];
        }

        while (true) {
            scanner = new Scanner(System.in);
            if (commandTemp.split(" ").length > 1) {

                try {
                    int tempId = Integer.parseInt(idStr);
                    if (labWorkDAO.getAll().containsKey(tempId)){
                        idStr = "";
                        throw new NotUniqueKeyException();
                    }
                    id = tempId;
                    if (!labWork.setId(id)){
                        idStr = "";
                        throw new NumberMinimalException(0);
                    }
                    break;
                } catch (NumberFormatException e) {

                    commandTemp = command.split(" ")[0];
                    new NotNumberException().outputException(consoleManager);

                    idStr = "";
                    while (idStr == null || idStr.isEmpty() || idStr.split(" ").length == 0 || idStr.split("\t").length == 0) {
                        try {
                            consoleManager.output("Введите ключ: ");
                            idStr = scanner.nextLine();
                        } catch (NoSuchElementException noSuchElementException) {
                            new NotNumberException().outputException(consoleManager);
                        }
                    }
                    commandTemp += String.format(" %s", idStr);
                } catch (NoSuchElementException noSuchElementException) {
                    new NotNumberException().outputException(consoleManager);
                } catch (NotUniqueKeyException notUniqueKeyException) {
                    notUniqueKeyException.outputException(consoleManager);
                } catch (NumberMinimalException numberMinimalException) {
                    numberMinimalException.outputException(consoleManager);
                }
            } else {
                int tempId = GenerationID.newId();
                while (labWorkDAO.getAll().containsKey(tempId)){
                    tempId = GenerationID.newId();
                }
                id = tempId;
                break;
            }


        }

        labWork.setId(id);
    }
    private void checkNameLab(Scanner scanner, LabWork labWork, ConsoleManager consoleManager){
        while (labWork.getName() == null){
            scanner = new Scanner(System.in);
            try{
                consoleManager.output("Введите название работы: ");
                if (!labWork.setName(scanner.nextLine())){
                    throw new EmptyFieldException();
                }
            } catch (EmptyFieldException emptyFieldException) {
                emptyFieldException.outputException(consoleManager);
            } catch (NoSuchElementException noSuchElementException){
                new EmptyFieldException().outputException(consoleManager);
            }
        }
    }
    private void checkCoordinates(Scanner scanner, LabWork labWork, ConsoleManager consoleManager){
        while (labWork.getCoordinates() == null){
            Coordinates coordinates = new Coordinates();
            while (coordinates.getX() == null){
                scanner = new Scanner(System.in);
                try {
                    consoleManager.output("Введите координату X: ");
                    long xCoord = Long.parseLong(scanner.nextLine());
                    if (!coordinates.setX(xCoord)){
                        throw new NumberLongerException(coordinates.getMaxCoordinateX());
                    }
                    coordinates.setX(xCoord);
                } catch (NumberFormatException | NoSuchElementException numberFormatException){
                    new NotNumberException().outputException(consoleManager);
                } catch (NumberLongerException numberLongerException) {
                    numberLongerException.outputException(consoleManager);
                }
            }

            while (true){
                scanner = new Scanner(System.in);
                try {
                    consoleManager.output("Введите координату Y: ");
                    int yCoord = Integer.parseInt(scanner.nextLine());
                    coordinates.setY(yCoord);
                    break;
                } catch (NumberFormatException | NoSuchElementException numberFormatException){
                    new NotNumberException().outputException(consoleManager);
                }
            }
            labWork.setCoordinates(coordinates);
        }
    }
    private void checkMinimalPoint(Scanner scanner, LabWork labWork, ConsoleManager consoleManager){
        while (labWork.getMinimalPoint() == null){
            scanner = new Scanner(System.in);
            try {
                consoleManager.output("Введите минимальную точку: ");
                if (!labWork.setMinimalPoint(Float.parseFloat(scanner.nextLine()))){
                    throw new NumberMinimalException(0);
                }
            } catch (NumberFormatException numberFormatException){
                new NotNumberException().outputException(consoleManager);
            } catch (NumberMinimalException numberMinimalException) {
                numberMinimalException.outputException(consoleManager);
            }

        }
    }
    private void checkDescription(Scanner scanner, LabWork labWork, ConsoleManager consoleManager){
        while (labWork.getDescription() == null){
            scanner = new Scanner(System.in);
            try{
                consoleManager.output("Введите описание: ");
                if (!labWork.setDescription(scanner.nextLine())){
                    throw new EmptyFieldException();
                }
            } catch (EmptyFieldException e) {
                e.outputException(consoleManager);
            } catch (NoSuchElementException noSuchElementException){
                new EmptyFieldException().outputException(consoleManager);
            }
        }
    }
    private void checkDifficulty(Scanner scanner, LabWork labWork, ConsoleManager consoleManager){
        Difficulty[] difficulties = Difficulty.values();
        for (int i = 0; i < difficulties.length; i++){
            consoleManager.warning(String.format("%d. %s", i + 1, difficulties[i]));
        }
        while (labWork.getDifficulty() == null){
            scanner = new Scanner(System.in);
            consoleManager.output(String.format("Выберите пункт (введите число от 1 до %d): ", difficulties.length));
            try {
                int index = Integer.parseInt(scanner.nextLine());
                Difficulty difficulty = difficulties[index - 1];
                labWork.setDifficulty(difficulty);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NoSuchElementException e){
                consoleManager.error(String.format("Введите число от 1 до %d", difficulties.length));
            }
        }
    }
    private void checkAuthor(Scanner scanner, LabWork labWork, ConsoleManager consoleManager){
        while (labWork.getAuthor() == null){
            Person author = new Person();
            while (author.getName() == null){
                scanner = new Scanner(System.in);
                try{
                    consoleManager.output("Введите имя: ");
                    if (!author.setName(scanner.nextLine())){
                        throw new EmptyFieldException();
                    }
                } catch (EmptyFieldException e) {
                    e.outputException(consoleManager);
                } catch (NoSuchElementException noSuchElementException){
                    new EmptyFieldException().outputException(consoleManager);
                }
            }

            while (true){
                scanner = new Scanner(System.in);
                try {
                    consoleManager.output("Введите вес: ");
                    long weight = Long.parseLong(scanner.nextLine());
                    if (!author.setWeight(weight)){
                        throw new NumberMinimalException(0);
                    }
                    break;
                } catch (NumberFormatException | NoSuchElementException numberFormatException){
                    new NotNumberException().outputException(consoleManager);
                } catch (NumberMinimalException numberMinimalException) {
                    numberMinimalException.outputException(consoleManager);
                }
            }

            while (author.getPassportID() == null){
                try{
                    consoleManager.output("Введите id паспорта: ");
                    if (!author.setPassportID(scanner.nextLine())){
                        throw new EmptyFieldException();
                    }
                } catch (EmptyFieldException e) {
                    e.outputException(consoleManager);
                } catch (NoSuchElementException noSuchElementException){
                    new EmptyFieldException().outputException(consoleManager);
                }
            }

            labWork.setAuthor(author);

        }
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {

        Scanner scanner = new Scanner(System.in);
        LabWork labWork = new LabWork();

        checkIdUser(command, scanner, labWork, labWorkDAO, consoleManager);
        checkNameLab(scanner, labWork, consoleManager);
        checkCoordinates(scanner, labWork, consoleManager);
        labWork.setCreationDate(ZonedDateTime.now());
        checkMinimalPoint(scanner, labWork, consoleManager);
        checkDescription(scanner, labWork, consoleManager);
        checkDifficulty(scanner, labWork, consoleManager);
        checkAuthor(scanner, labWork, consoleManager);

        labWorkDAO.create(labWork);

    }
}
