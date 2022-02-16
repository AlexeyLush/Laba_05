package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import io.ConsoleManager;

import java.util.Map;

public class HelpCommand extends CommandAbstract {

    public HelpCommand(){
        setTitle("help");
        setDescription("help : вывести справку по доступным командам");
    }


    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {
        for (Map.Entry<String, CommandAbstract> entry : commandsManager.getCommandsList().entrySet()) {
            consoleManager.outpunln(entry.getValue().getDescription());
        }
    }
}
