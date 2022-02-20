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
    }
}
