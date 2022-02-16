package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import io.ConsoleManager;

public class FilterGreaterThanDescriptionCommand extends CommandAbstract {

    public FilterGreaterThanDescriptionCommand(){
        setTitle("filter_greater_than_description");
        setDescription("filter_greater_than_description description : вывести элементы, значение поля description которых больше заданного");
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {

    }
}
