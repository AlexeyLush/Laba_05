package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import io.ConsoleManager;

public class SaveCommand extends CommandAbstract {

    public SaveCommand(){
        setTitle("save");
        setDescription("save : сохранить коллекцию в файл");
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {

    }
}
