package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;

public class ExitCommand extends CommandAbstract {

    public ExitCommand(){
        setTitle("exit");
        setDescription("exit : завершить программу (без сохранения в файл)");
    }
    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, String command) {

    }
}
