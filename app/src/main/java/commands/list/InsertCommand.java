package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;

public class InsertCommand extends CommandAbstract {

    public InsertCommand(){
        setTitle("insert");
        setDescription("insert null {element}: добавить новый элемент с заданным ключом");
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, String command) {

    }
}
