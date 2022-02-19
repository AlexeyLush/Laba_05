package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;

public class SaveCommand extends CommandAbstract {

    public SaveCommand(){
        setTitle("save");
        setDescription("save : сохранить коллекцию в файл");
    }

    @Override
    public void execute(CommandFields commandFields) {
        commandFields.getDataFileManager().save(commandFields.getLabWorkDAO().getAll());
    }
}
