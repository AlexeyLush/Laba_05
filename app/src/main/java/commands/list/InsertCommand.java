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

/**
 * Команда добавления нового элемента с заданным ключом
 */

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
        } else if (key.isEmpty() || key.split(" ").length == 0 || key.split("\t").length == 0) {
            consoleManager.error("Ключ не должен содеражть пустые символы (пробелы, табуляцию)!");
            isTrue = false;
        } else if (json == null) {
            if (key.contains(" ") || key.contains("\t")){
                consoleManager.error("Ключ не должен содеражть пустые символы (пробелы, табуляцию)!");
                isTrue = false;
            } else{
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

    private void checkNameLab(Scanner scanner, LabWork labWork, ConsoleManager consoleManager) {
        while (labWork.getName() == null) {
            scanner = new Scanner(System.in);
            try {
                consoleManager.output("Введите название работы: ");
                if (!labWork.setName(scanner.nextLine())) {
                    throw new EmptyFieldException();
                }
            } catch (EmptyFieldException emptyFieldException) {
                emptyFieldException.outputException();
            } catch (NoSuchElementException noSuchElementException) {
                new EmptyFieldException().outputException();
            }
        }
    }

    private void checkCoordinates(Scanner scanner, LabWork labWork, ConsoleManager consoleManager) {
        while (labWork.getCoordinates() == null) {
            Coordinates coordinates = new Coordinates();
            while (coordinates.getX() == null) {
                scanner = new Scanner(System.in);
                try {
                    consoleManager.output("Введите координату X: ");
                    long xCoord = Long.parseLong(scanner.nextLine());
                    if (!coordinates.setX(xCoord)) {
                        throw new NumberLongerException(coordinates.getMaxCoordinateX());
                    }
                    coordinates.setX(xCoord);
                } catch (NumberFormatException | NoSuchElementException numberFormatException) {
                    new NotNumberException().outputException();
                } catch (NumberLongerException numberLongerException) {
                    numberLongerException.outputException();
                }
            }

            while (true) {
                scanner = new Scanner(System.in);
                try {
                    consoleManager.output("Введите координату Y: ");
                    int yCoord = Integer.parseInt(scanner.nextLine());
                    coordinates.setY(yCoord);
                    break;
                } catch (NumberFormatException | NoSuchElementException numberFormatException) {
                    new NotNumberException().outputException();
                }
            }
            labWork.setCoordinates(coordinates);
        }
    }

    private void checkMinimalPoint(Scanner scanner, LabWork labWork, ConsoleManager consoleManager) {
        while (labWork.getMinimalPoint() == null) {
            scanner = new Scanner(System.in);
            try {
                consoleManager.output("Введите минимальную точку: ");
                if (!labWork.setMinimalPoint(Float.parseFloat(scanner.nextLine()))) {
                    throw new NumberMinimalException(0);
                }
            } catch (NumberFormatException numberFormatException) {
                new NotNumberException().outputException();
            } catch (NumberMinimalException numberMinimalException) {
                numberMinimalException.outputException();
            }

        }
    }

    private void checkDescription(Scanner scanner, LabWork labWork, ConsoleManager consoleManager) {
        while (labWork.getDescription() == null) {
            scanner = new Scanner(System.in);
            try {
                consoleManager.output("Введите описание: ");
                if (!labWork.setDescription(scanner.nextLine())) {
                    throw new EmptyFieldException();
                }
            } catch (EmptyFieldException emptyFieldException) {
                emptyFieldException.outputException();
            } catch (NoSuchElementException noSuchElementException) {
                new EmptyFieldException().outputException();
            }
        }
    }

    private void checkDifficulty(Scanner scanner, LabWork labWork, ConsoleManager consoleManager) {
        Difficulty[] difficulties = Difficulty.values();
        for (int i = 0; i < difficulties.length; i++) {
            consoleManager.warning(String.format("%d. %s", i + 1, difficulties[i]));
        }
        while (labWork.getDifficulty() == null) {
            scanner = new Scanner(System.in);
            consoleManager.output(String.format("Выберите пункт (введите число от 1 до %d): ", difficulties.length));
            try {
                int index = Integer.parseInt(scanner.nextLine());
                Difficulty difficulty = difficulties[index - 1];
                labWork.setDifficulty(difficulty);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NoSuchElementException e) {
                consoleManager.error(String.format("Введите число от 1 до %d", difficulties.length));
            }
        }
    }

    private void checkAuthor(Scanner scanner, LabWork labWork, ConsoleManager consoleManager) {
        while (labWork.getAuthor() == null) {
            Person author = new Person();
            while (author.getName() == null) {
                scanner = new Scanner(System.in);
                try {
                    consoleManager.output("Введите имя: ");
                    if (!author.setName(scanner.nextLine())) {
                        throw new EmptyFieldException();
                    }
                } catch (EmptyFieldException emptyFieldException) {
                    emptyFieldException.outputException();
                } catch (NoSuchElementException noSuchElementException) {
                    new EmptyFieldException().outputException();
                }
            }

            while (true) {
                scanner = new Scanner(System.in);
                try {
                    consoleManager.output("Введите вес: ");
                    long weight = Long.parseLong(scanner.nextLine());
                    if (!author.setWeight(weight)) {
                        throw new NumberMinimalException(0);
                    }
                    break;
                } catch (NumberFormatException | NoSuchElementException numberFormatException) {
                    new NotNumberException().outputException();
                } catch (NumberMinimalException numberMinimalException) {
                    numberMinimalException.outputException();
                }
            }

            while (author.getPassportID() == null) {
                try {
                    consoleManager.output("Введите id паспорта: ");
                    if (!author.setPassportID(scanner.nextLine())) {
                        throw new EmptyFieldException();
                    }
                } catch (EmptyFieldException e) {
                    e.outputException();
                } catch (NoSuchElementException noSuchElementException) {
                    new EmptyFieldException().outputException();
                }
            }

            labWork.setAuthor(author);

        }
    }

    @Override
    public void execute(CommandFields commandFields) {
        Scanner scanner = new Scanner(System.in);
        LabWork labWork = new LabWork();

        String[] splitCommand = new SplitCommandOnIdAndJSON().splitedCommand(commandFields.getCommand());

        String key = splitCommand[0];
        String json = splitCommand[1];

        if (commandFields.isUserInput()) {
            while (!checkUserKey(json, key, commandFields.getLabWorkDAO(), labWork, commandFields.getConsoleManager())) {
                commandFields.getConsoleManager().output("Введите ключ: ");
                key = scanner.nextLine();
            }
        }

//        checkIdUser(commandFields.getCommand(), scanner, labWork, commandFields.getLabWorkDAO(), commandFields.getConsoleManager());
//        checkNameLab(scanner, labWork, commandFields.getConsoleManager());
//        checkCoordinates(scanner, labWork, commandFields.getConsoleManager());
//        checkMinimalPoint(scanner, labWork, commandFields.getConsoleManager());
//        checkDescription(scanner, labWork, commandFields.getConsoleManager());
//        checkDifficulty(scanner, labWork, commandFields.getConsoleManager());
//        checkAuthor(scanner, labWork, commandFields.getConsoleManager());

        commandFields.getLabWorkDAO().create(key, labWork);

    }
}
