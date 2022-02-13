package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;

public class HelpCommand extends CommandAbstract {

    public HelpCommand() {
        setTitle("help");
        setDescription("вывести справку по доступным командам");
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager) {

    }
}
