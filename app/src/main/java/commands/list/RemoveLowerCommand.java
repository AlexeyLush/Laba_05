package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;

/**
 * команда удаления из коллекции всех элементов, меньших, чем заданный
 */

public class RemoveLowerCommand extends CommandAbstract {

    public RemoveLowerCommand(){
        setTitle("remove_lower");
        setDescription("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
    }

    @Override
    public void execute(CommandFields commandFields) {

    }
}
