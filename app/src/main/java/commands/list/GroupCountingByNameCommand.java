package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import dao.LabWorkDAO;
import io.ConsoleManager;

public class GroupCountingByNameCommand extends CommandAbstract {

    public GroupCountingByNameCommand(){
        setTitle("group_counting_by_name");
        setDescription("group_counting_by_name : сгруппировать элементы коллекции по значению поля name, вывести количество элементов в каждой группе");
    }

    @Override
    public void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command) {

    }
}
