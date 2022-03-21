package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;
import models.LabWork;

import java.util.Map;

/**
 * Команда вывода в стандартный поток информации о коллекции (тип, дата инициализации, количество элементов и т.д.
 */

public class InfoCommand extends CommandAbstract {

    public InfoCommand(){
        setTitle("info");
        setDescription("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }

    @Override
    public void execute(CommandFields commandFields) {

        String[] commandSplited = commandFields.getCommand().split(" ");
        String description;
        if (commandSplited.length == 1){
            commandFields.getConsoleManager().output("Введите описание: ");
            description = commandFields.getScanner().nextLine();
            while ((description.isEmpty() || description.replaceAll(" ", "").replaceAll("\t", "").length() == 0)){
                commandFields.getConsoleManager().error("Описание не может быть пустым");
                commandFields.getConsoleManager().output("Введите описание: ");
                description = commandFields.getScanner().nextLine();
            }
        } else{
            commandSplited[0] = "";
            description = String.join(" ", commandSplited);
        }

        try{
            for (Map.Entry<String, LabWork> entry : commandFields.getLabWorkDAO().getAll().entrySet()) {
                if (entry.getValue().getDescription().length() > description.length()){
                    commandFields.getConsoleManager().outputln(entry.getValue().toString());
                }
            }
        } catch (NullPointerException nullPointerException){
            commandFields.getConsoleManager().error("Ошибка!");
        }
    }
}
