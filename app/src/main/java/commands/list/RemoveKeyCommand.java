package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;

/**
 * Команда удаления элементов из коллекции по его ключу
 */

public class RemoveKeyCommand extends CommandAbstract {

    public RemoveKeyCommand(){
        setTitle("remove_key");
        setDescription("remove_key null : удалить элемент из коллекции по его ключу");
    }

    @Override
    public void execute(CommandFields commandFields) {

    }
}
