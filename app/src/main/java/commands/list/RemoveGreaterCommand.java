package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import files.DataFileManager;
import files.ExecuteFileManager;
import io.ConsoleManager;

public class RemoveGreaterCommand extends CommandAbstract {

    public RemoveGreaterCommand(){
        setTitle("remove_greater");
        setDescription("remove_greater {element} : удалить из коллекции все элементы, превышающие заданный");
    }

    @Override
    public void execute(String command, LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, DataFileManager dataFileManager, ExecuteFileManager executeFileManager) {

    }
}
