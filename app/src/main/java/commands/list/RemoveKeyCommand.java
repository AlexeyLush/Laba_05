package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;

public class RemoveKeyCommand extends CommandAbstract {

    public RemoveKeyCommand(){
        setTitle("remove_key");
        setDescription("remove_key null : удалить элемент из коллекции по его ключу");
    }
    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, String command) {

    }
}
