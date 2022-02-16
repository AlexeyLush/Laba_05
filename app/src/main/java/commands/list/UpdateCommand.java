package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import io.ConsoleManager;

public class UpdateCommand extends CommandAbstract {

    public UpdateCommand(){
        setTitle("update id {element}");
        setDescription("обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {

    }
}
