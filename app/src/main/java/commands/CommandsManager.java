package commands;

import commands.list.HelpCommand;
import exception.NotFoundCommandException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandsManager {

    private Map<String, CommandAbstract> commandsList = new LinkedHashMap<String, CommandAbstract>();

    public CommandsManager(){
        addCommand(new HelpCommand());
    }

    private void addCommand(CommandAbstract command){
        commandsList.put(command.getTitle(), command);
    }

    public void inputCommand(String commandTitle) {
        try{
            if (commandsList.containsKey(commandTitle)){
                System.out.println(commandsList.get(commandTitle).showInfoCommand());
            }
            else{
                throw new NotFoundCommandException();
            }
        } catch (NotFoundCommandException e){
            System.out.println(e);
        }
    }

}
