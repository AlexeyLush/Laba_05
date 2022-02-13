package commands;

import commands.list.HelpCommand;
import commands.list.InfoCommand;
import dao.LabWorkDAO;
import exception.NotFoundCommandException;

import java.util.LinkedHashMap;
import java.util.Map;

public final class CommandsManager {

    private final Map<String, CommandAbstract> commandsList = new LinkedHashMap<String, CommandAbstract>();

    public CommandsManager(){
        addCommand(new HelpCommand());
        addCommand(new InfoCommand());
    }

    private void addCommand(CommandAbstract command){
        commandsList.put(command.getTitle(), command);
    }

    public Map<String, CommandAbstract> getCommandsList(){
        return new LinkedHashMap<>(this.commandsList);
    }

    public void inputCommand(String commandTitle, LabWorkDAO labWorkDAO) {
        try{
            if (commandsList.containsKey(commandTitle)){
                commandsList.get(commandTitle).execute(labWorkDAO, this, commandTitle);
            }
            else{
                throw new NotFoundCommandException();
            }
        } catch (NotFoundCommandException e){
            e.outputException();
        }
    }

}
