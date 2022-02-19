package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import files.DataFileManager;
import files.ExecuteFileManager;
import io.ConsoleManager;

public class SaveCommand extends CommandAbstract {

    public SaveCommand(){
        setTitle("save");
        setDescription("save : сохранить коллекцию в файл");
    }

    @Override
    public void execute(String command, LabWorkDAO labWorkDAO, CommandsManager commandsManager,
                        ConsoleManager consoleManager, DataFileManager dataFileManager, ExecuteFileManager executeFileManager) {
        dataFileManager.save(labWorkDAO.getAll());
    }
}
