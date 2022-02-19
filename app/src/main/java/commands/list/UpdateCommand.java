package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;
import dao.LabWorkDAO;
import io.ConsoleManager;
import exception.NotEnteredKeyException;
import exception.NotFoundElementException;
import exception.NotNumberException;
import models.LabWork;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UpdateCommand extends CommandAbstract {

    public UpdateCommand(){
        setTitle("update");
        setDescription("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
    }

    private int counterFiled = 1;
    private LinkedHashMap<Integer, Method> updatedFields = new LinkedHashMap<>();
    private LabWork getLabWork(String command, Scanner scanner, LabWorkDAO labWorkDAO, ConsoleManager consoleManager){

        int id;
        String commandTemp = command;
        String idStr = "";
        LabWork labWorkForUpdate;

        if (commandTemp.split(" ").length > 1){
            idStr = commandTemp.split(" ")[1];
        }

        while (true){
            scanner = new Scanner(System.in);
            try {
                if (commandTemp.split(" ").length > 1){
                    try{
                        id = Integer.parseInt(idStr);
                        labWorkForUpdate = labWorkDAO.get(id);
                        if (labWorkForUpdate == null){
                            throw new NotFoundElementException();
                        }
                        break;

                    } catch (NumberFormatException numberFormatException) {
                        new NotNumberException().outputException();
                        commandTemp = command.split(" ")[0];
                        idStr = "";
                    }
                    catch (NotFoundElementException notFoundElementException) {
                        notFoundElementException.outputException();
                        idStr = "";
                    }
                }
                else{
                    throw new NotEnteredKeyException();
                }
            } catch (NotEnteredKeyException notEnteredKeyException){
                notEnteredKeyException.outputException();

                while (idStr.isEmpty() || idStr.split(" ").length == 0 || idStr.split("\t").length == 0) {
                    scanner = new Scanner(System.in);
                    try {
                        consoleManager.output("Введите ключ: ");
                        idStr = scanner.nextLine();
                        id = Integer.parseInt(idStr);
                        labWorkForUpdate = labWorkDAO.get(id);
                        if (labWorkForUpdate == null){
                            throw new NotFoundElementException();
                        }
                        return labWorkForUpdate;

                    } catch (NoSuchElementException | NumberFormatException noSuchElementException) {
                        new NotNumberException().outputException();
                        idStr = "";
                    } catch (NotFoundElementException notFoundElementException) {
                        notFoundElementException.outputException();
                        idStr = "";
                    }
                }
            }
        }
        return labWorkForUpdate;

    }
    private void outputFiled(String field, ConsoleManager consoleManager, boolean isUpdateField){
        if (isUpdateField){
            consoleManager.outpunln(String.format("%d. %s", counterFiled, field));
            counterFiled++;
        } else {
            consoleManager.outpunln(String.format("%s", field));
        }
    }
    private void updateLabWorkName(){

    }
    private void showLabWorkFields(LabWork labWork, ConsoleManager consoleManager){
        outputFiled(String.format("ID: %s", labWork.getId()), consoleManager, false);
        outputFiled(String.format("Дата создания: %s", labWork.getCreationDate()), consoleManager, false);
        outputFiled(String.format("Название работы: %s", labWork.getName()), consoleManager, true);
        outputFiled(String.format("Координата X: %s", labWork.getCoordinates().getX()), consoleManager, true);
        outputFiled(String.format("Координата Y: %s", labWork.getCoordinates().getY()), consoleManager, true);
    }


    @Override
    public void execute(CommandFields commandFields) {

        Scanner scanner = new Scanner(System.in);
        LabWork labWork = getLabWork(commandFields.getCommand(), scanner, commandFields.getLabWorkDAO(), commandFields.getConsoleManager());

    }
}
