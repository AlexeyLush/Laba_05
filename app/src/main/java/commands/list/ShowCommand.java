package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;
import models.LabWork;

import java.util.Map;

public class ShowCommand extends CommandAbstract {

    public ShowCommand(){
        setTitle("show");
        setDescription("show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public void execute(CommandFields commandFields) {
        try{
            for (Map.Entry<String, LabWork> entry : commandFields.getLabWorkDAO().getAll().entrySet()) {
                commandFields.getConsoleManager().outpunln(entry.getValue().getCreationDate().toString());
            }
        } catch (NullPointerException nullPointerException){
            commandFields.getConsoleManager().error("dfsfsdfsdfsdf");
        }


    }
}
