package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import io.ConsoleManager;

public class ReplaceIfLowerCommand extends CommandAbstract {

    public ReplaceIfLowerCommand(){
        setTitle("replace_if_lowe");
        setDescription("replace_if_lowe null {element} : заменить значение по ключу, если новое значение меньше старого");
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {

    }
}
