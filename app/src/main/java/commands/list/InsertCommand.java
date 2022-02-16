package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import exception.NotEnteredKeyException;
import io.ConsoleManager;
import models.LabWork;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InsertCommand extends CommandAbstract {

    public InsertCommand(){
        setTitle("insert");
        setDescription("insert null {element}: добавить новый элемент с заданным ключом");
        setArgumentsCount(1);
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {

        String commandTemp = command;
        Scanner scanner = new Scanner(System.in);

        while (true){
            try{
                if (commandTemp.split(" ").length - 1 < getArgumentsCount()){
                    throw new NotEnteredKeyException();
                }
                else{
                    try {
                        Integer.parseInt(commandTemp.split(" ")[1]);
                        break;
                    } catch (NumberFormatException e){
                        commandTemp = command.split(" ")[0];
                        consoleManager.error("Ключ должен быть числом");
                    }

                }
            } catch (NotEnteredKeyException e){
                e.outputException();
                try{
                    consoleManager.output("Введите ключ: ");
                    int index = scanner.nextInt();
                    commandTemp += String.format(" %d", index);
                } catch (InputMismatchException mismatch){
                    consoleManager.error("Ключ должен быть числом");
                    scanner = new Scanner(System.in);
                }
                catch (NoSuchElementException noSuchElementException){
                    consoleManager.error("Не используй эту херь");
                    scanner = new Scanner(System.in);
                }

            }

        }

        LabWork labWork = new LabWork();
        labWork.setName(scanner.nextLine());
    }
}
