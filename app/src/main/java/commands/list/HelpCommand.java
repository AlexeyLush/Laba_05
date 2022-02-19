package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;

import java.util.Map;

public class HelpCommand extends CommandAbstract {


    public HelpCommand(){
        setTitle("help");
        setDescription("help : вывести справку по доступным командам");
    }

    @Override
    public void execute(CommandFields commandFields) {
        for (Map.Entry<String, CommandAbstract> entry : commandFields.getCommandsManager().getCommandsList().entrySet()) {
            commandFields.getConsoleManager().outpunln(entry.getValue().getDescription());
        }
    }
}
