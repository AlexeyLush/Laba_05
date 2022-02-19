package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;

public class ReplaceIfLowerCommand extends CommandAbstract {

    public ReplaceIfLowerCommand(){
        setTitle("replace_if_lowe");
        setDescription("replace_if_lowe null {element} : заменить значение по ключу, если новое значение меньше старого");
    }

    @Override
    public void execute(CommandFields commandFields) {

    }
}
