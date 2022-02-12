package commands.list;

import commands.CommandAbstract;

public class HelpCommand extends CommandAbstract {

    public HelpCommand() {
        setTitle("help");
        setDescription("вывести справку по доступным командам");
    }

    @Override
    public void execute() {
        System.out.println("Отработана команда help");
    }
}
