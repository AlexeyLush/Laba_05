package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import io.ConsoleManager;

public class ClearCommand extends CommandAbstract {

    public ClearCommand(){
        setTitle("clear");
        setDescription("clear: очистить коллекцию");
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {

    }
}
