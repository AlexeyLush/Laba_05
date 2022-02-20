package commands.services;

import commands.services.interfaces.SplitCommand;
import exception.NotNumberException;

public class SplitCommandOnIdAndJSON implements SplitCommand{

    @Override
    public String[] spitedCommand(String command) {

        String[] splitCommand = command.split(" ");

        String id = null;
        String json = null;

        if (splitCommand.length == 2 && splitCommand[1].length() <= 10){
            try {
                id = splitCommand[1];
            } catch (NumberFormatException numberFormatException){
                new NotNumberException().outputException();
            }
        } else if (splitCommand.length == 2){
            splitCommand[0] = "";
            json = String.join(" ", splitCommand);
        } else if (splitCommand.length > 2){
            splitCommand[0] = "";
            if (splitCommand[1].length() <= 10){
                id = splitCommand[1];
            }
            splitCommand[1] = "";
            json = String.join(" ", splitCommand);
        }

        return new String[] {id, json};
    }

}
