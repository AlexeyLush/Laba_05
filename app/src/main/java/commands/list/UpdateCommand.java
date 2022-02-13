package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;

public class UpdateCommand extends CommandAbstract {

    public UpdateCommand(){
        setTitle("update id {element}");
        setDescription("обновить значение элемента коллекции, id которого равен заданному");
    }
    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager) {

    }
}
