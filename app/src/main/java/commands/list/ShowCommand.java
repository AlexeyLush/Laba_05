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
<<<<<<< HEAD
            for (Map.Entry<Integer, LabWork> entry : commandFields.getLabWorkDAO().getAll().entrySet()) {
                commandFields.getConsoleManager().outpunln(entry.getValue().toString());
=======
            for (Map.Entry<String, LabWork> entry : commandFields.getLabWorkDAO().getAll().entrySet()) {
                commandFields.getConsoleManager().outpunln(entry.getValue().getCreationDate().toString());
>>>>>>> 30d96752c192af6c04cf70f02ab13d45d7f42e49
            }
        }
        catch (NullPointerException nullPointerException){
            commandFields.getConsoleManager().error("Ошибка!");
        }
    }

}
