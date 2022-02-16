package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import io.ConsoleManager;

public class RemoveLowerCommand extends CommandAbstract {

    public RemoveLowerCommand(){
        setTitle("remove_lower");
        setDescription("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {

    }
}
