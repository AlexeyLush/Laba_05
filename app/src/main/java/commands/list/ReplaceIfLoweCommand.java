package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;

public class ReplaceIfLoweCommand extends CommandAbstract {

    public ReplaceIfLoweCommand(){
        setTitle("replace_if_lowe");
        setDescription("replace_if_lowe null {element} : заменить значение по ключу, если новое значение меньше старого");
    }
    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, String command) {

    }
}
