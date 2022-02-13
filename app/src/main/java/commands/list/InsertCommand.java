package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import exception.NotEnteredKeyException;
import io.ConsoleManager;

import java.util.Scanner;

public class InsertCommand extends CommandAbstract {

    public InsertCommand(){
        setTitle("insert");
        setDescription("insert null {element}: добавить новый элемент с заданным ключом");
        setArgumentsCount(1);
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, String command) {

        ConsoleManager consoleManager = new ConsoleManager();
        String commandTemp = command;
        Scanner scanner = new Scanner(System.in);

        while (true){
            try{
                if (commandTemp.split(" ").length - 1 < getArgumentsCount()){
                    throw new NotEnteredKeyException();
                }
            } catch (NotEnteredKeyException e){
                e.outputException();
            }

        }
    }
}
