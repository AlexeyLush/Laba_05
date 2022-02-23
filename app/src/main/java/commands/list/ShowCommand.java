package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;
import dao.DAO;
import exception.NotFoundCommandException;
import exception.NotNumberException;
import models.LabWork;

import java.util.Map;

/**
 * Команда вывода в стандарный поток всех элементов коллекции в строковом представлении
 */

public class ShowCommand extends CommandAbstract {

    public ShowCommand(){
        setTitle("show");
        setDescription("show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public void execute(CommandFields commandFields) {
        try{

            for (Map.Entry<String, LabWork> entry : commandFields.getLabWorkDAO().getAll().entrySet()) {
                commandFields.getConsoleManager().outpunln(entry.getValue().toString());
            }
        }
        catch (NullPointerException nullPointerException){
            commandFields.getConsoleManager().error("Ошибка!");
        }
    }

}
