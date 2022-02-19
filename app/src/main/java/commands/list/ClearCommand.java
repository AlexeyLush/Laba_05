package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;

public class ClearCommand extends CommandAbstract {

    public ClearCommand(){
        setTitle("clear");
        setDescription("clear: очистить коллекцию");
    }

    @Override
    public void execute(CommandFields commandFields) {
        commandFields.getLabWorkDAO().clear();
    }
}
