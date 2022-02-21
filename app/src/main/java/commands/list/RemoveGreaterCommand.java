package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;

/**
 * Команда удаления из коллекции всех элементы, превышающие заданный
 */

public class RemoveGreaterCommand extends CommandAbstract {

    public RemoveGreaterCommand(){
        setTitle("remove_greater");
        setDescription("remove_greater {element} : удалить из коллекции все элементы, превышающие заданный");
    }

    @Override
    public void execute(CommandFields commandFields) {

    }
}
