package commands;

import commands.list.*;
import dao.LabWorkDAO;
import exception.NotFoundCommandException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public final class CommandsManager {

    private final Map<String, CommandAbstract> commandsList = new LinkedHashMap<String, CommandAbstract>();

    public CommandsManager(){
        addCommand(new HelpCommand());
        addCommand(new InfoCommand());
        addCommand(new InsertCommand());
        addCommand(new ShowCommand());
        addCommand(new UpdateCommand());
    }

    private void addCommand(CommandAbstract command){
        commandsList.put(command.getTitle(), command);
    }

    public Map<String, CommandAbstract> getCommandsList(){
        return new LinkedHashMap<>(this.commandsList);
    }

    public void inputCommand(String command, LabWorkDAO labWorkDAO) {
        try{
            String commandName = command.split(" ")[0];
            if (commandsList.containsKey(commandName)){
                commandsList.get(commandName).execute(labWorkDAO, this, command);
            }
            else{
                throw new NotFoundCommandException();
            }
        } catch (NotFoundCommandException e){
            e.outputException();
        }
    }

}
