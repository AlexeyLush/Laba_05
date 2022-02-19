package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;

public class InfoCommand extends CommandAbstract {

    public InfoCommand(){
        setTitle("info");
        setDescription("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }

    @Override
    public void execute(CommandFields commandFields) {

    }
}
