package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import io.ConsoleManager;

public class ShowCommand extends CommandAbstract {

    public ShowCommand(){
        setTitle("show");
        setDescription("show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {

    }
}
