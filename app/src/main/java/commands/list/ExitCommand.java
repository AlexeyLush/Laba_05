package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import io.ConsoleManager;
import laba.App;

public class ExitCommand extends CommandAbstract {

    public ExitCommand(){
        setTitle("exit");
        setDescription("exit : завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {
        App.exit();
    }
}
