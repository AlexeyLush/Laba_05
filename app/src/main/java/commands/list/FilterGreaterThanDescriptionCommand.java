package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;

/**
 * Команда вывова элементов, значение поля description которых больше заданного
 */

public class FilterGreaterThanDescriptionCommand extends CommandAbstract {

    public FilterGreaterThanDescriptionCommand(){
        setTitle("filter_greater_than_description");
        setDescription("filter_greater_than_description description : вывести элементы, значение поля description которых больше заданного");
    }

    @Override
    public void execute(CommandFields commandFields) {

    }
}
