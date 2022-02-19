package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import files.DataFileManager;
import files.ExecuteFileManager;
import io.ConsoleManager;

public class FilterGreaterThanDescriptionCommand extends CommandAbstract {

    public FilterGreaterThanDescriptionCommand(){
        setTitle("filter_greater_than_description");
        setDescription("filter_greater_than_description description : вывести элементы, значение поля description которых больше заданного");
    }

    @Override
    public void execute(String command, LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, DataFileManager dataFileManager, ExecuteFileManager executeFileManager) {

    }
}
