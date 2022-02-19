package commands;

import commands.list.*;
import commands.models.CommandFields;
import dao.LabWorkDAO;
import exception.NotFoundCommandException;
import files.DataFileManager;
import files.ExecuteFileManager;
import io.ConsoleManager;
import org.checkerframework.checker.units.qual.C;

import java.util.*;

public final class CommandsManager {

    private final Map<String, CommandAbstract> commandsList = new LinkedHashMap<String, CommandAbstract>();
    private final ConsoleManager consoleManager;
    private final DataFileManager dataFileManager;
    private final ExecuteFileManager executeFileManager;

    public CommandsManager(ConsoleManager consoleManager, DataFileManager dataFileManager, ExecuteFileManager executeFileManager){
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

    public void inputCommand(LabWorkDAO labWorkDAO) {

        consoleManager.output("Введите команду: ");
        Scanner scanner = new Scanner(System.in);

        try{
            String command = scanner.nextLine();
            String commandName = command.split(" ")[0];
            if (commandsList.containsKey(commandName)){

                CommandFields commandFields = new CommandFields(command, labWorkDAO,
                        this, consoleManager, dataFileManager, executeFileManager);
                commandsList.get(commandName).execute(commandFields);
                consoleManager.successfully(String.format("Команда %s успешно выполнена", commandName));
            }
            else{
                throw new NotFoundCommandException();
            }
        } catch (NotFoundCommandException e){
            e.outputException();
        } catch (ArrayIndexOutOfBoundsException | NoSuchElementException e){
            new NotFoundCommandException().outputException();
        }
    }

}
