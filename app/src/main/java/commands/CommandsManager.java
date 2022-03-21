package commands;

import commands.list.*;
import commands.models.CommandFields;
import dao.LabWorkDAO;
import exception.NotFoundCommandException;
import files.DataFileManager;
import files.ExecuteFileManager;
import io.ConsoleManager;

import java.util.*;

/**
 * Класс менеджер команд
 */

public final class CommandsManager {

    private final Map<String, CommandAbstract> commandsList = new LinkedHashMap<String, CommandAbstract>();
    private final Scanner scanner;
    private final ConsoleManager consoleManager;
    private final DataFileManager dataFileManager;
    private final ExecuteFileManager executeFileManager;

    public CommandsManager(Scanner scanner, ConsoleManager consoleManager, DataFileManager dataFileManager, ExecuteFileManager executeFileManager){
        this.scanner = scanner;
        this.dataFileManager = dataFileManager;
        this.executeFileManager = executeFileManager;
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
        addCommand(new ReplaceIfLowerCommand());
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

    public void executeCommand(String command, LabWorkDAO labWorkDAO){
        String commandName = command.split(" ")[0].toLowerCase();
        try{
            if (commandsList.containsKey(commandName)){
                boolean isUser = true;
                if (commandName.equals("execute_script")){
                    isUser = false;
                }
                CommandFields commandFields = new CommandFields(scanner, command, labWorkDAO,
                        this, dataFileManager, consoleManager, isUser);
                commandsList.get(commandName).execute(commandFields);
            }
            else{
                throw new NotFoundCommandException();
            }
        } catch (NotFoundCommandException e){
            e.outputException();
        } catch (ArrayIndexOutOfBoundsException | NoSuchElementException e){
            consoleManager.error("Команда не найдена");
        }


    }

    public void inputCommand(LabWorkDAO labWorkDAO) {

        consoleManager.output("Введите команду (help - показать список команд): ");
        String command = scanner.nextLine();
        executeCommand(command, labWorkDAO);
    }

}
