package commands;

import commands.list.*;
import dao.LabWorkDAO;
import exception.NotFoundCommandException;
import io.ConsoleManager;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public final class CommandsManager {

    private final Map<String, CommandAbstract> commandsList = new LinkedHashMap<String, CommandAbstract>();
    private final ConsoleManager consoleManager;

    public CommandsManager(ConsoleManager consoleManager){
        this.consoleManager = consoleManager;
        addCommand(new HelpCommand());
        addCommand(new InfoCommand());
        addCommand(new ClearCommand());
        addCommand(new ExitCommand());
        addCommand(new SaveCommand());
        addCommand(new ExecuteScriptCommand());
        addCommand(new RemoveGreaterCommand());
        addCommand(new RemoveLowerCommand());
        addCommand(new RemoveKeyCommand());
        addCommand(new InsertCommand());
        addCommand(new ShowCommand());
        addCommand(new UpdateCommand());
        addCommand(new ReplaceIfLoweCommand());
        addCommand(new SumOfMinimalPointCommand());
        addCommand(new GroupCountingByNameCommand());
        addCommand(new FilterGreaterThanDescriptionCommand());
    }

    private void addCommand(CommandAbstract command){
        commandsList.put(command.getTitle(), command);
    }

    public Map<String, CommandAbstract> getCommandsList(){
        return new LinkedHashMap<>(this.commandsList);
    }

    public void inputCommand(LabWorkDAO labWorkDAO) {

        consoleManager.output("Введите команду: ");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        try{
            String commandName = command.split(" ")[0];
            if (commandsList.containsKey(commandName)){
                commandsList.get(commandName).execute(labWorkDAO, this, consoleManager, command);
            }
            else{
                throw new NotFoundCommandException();
            }
        } catch (NotFoundCommandException e){
            e.outputException();
        } catch (ArrayIndexOutOfBoundsException e){
            new NotFoundCommandException().outputException();
        }
    }

}
