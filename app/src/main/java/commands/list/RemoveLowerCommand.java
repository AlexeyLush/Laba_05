package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;

public class RemoveLowerCommand extends CommandAbstract {

    public RemoveLowerCommand(){
        setTitle("remove_lower");
        setDescription("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
    }

    @Override
    public void execute(CommandFields commandFields) {

    }
}
