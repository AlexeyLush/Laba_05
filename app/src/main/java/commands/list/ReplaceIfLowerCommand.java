package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;
import services.checkers.LabWorkChecker;
import services.elementProcces.LabWorkProcess;
import services.spliters.SplitCommandOnIdAndJSON;
import models.Coordinates;
import models.Difficulty;
import models.LabWork;
import models.Person;
import services.parsers.ParserJSON;

import java.time.ZonedDateTime;

/**
 * Команда замещения значений по ключу, если новое значение меньше старого
 */

public class ReplaceIfLowerCommand extends CommandAbstract {

    public ReplaceIfLowerCommand() {
        setTitle("replace_if_lower");
        setDescription("replace_if_lower null {element} : заменить значение по ключу, если новое значение меньше старого");
    }

    @Override
    public void execute(CommandFields commandFields) {

        LabWorkProcess labWorkProcess = new LabWorkProcess(commandFields.getConsoleManager(), commandFields.getScanner());
        LabWorkChecker checker = new LabWorkChecker();

        LabWork labWork = new LabWork();

        String[] splitCommand = new SplitCommandOnIdAndJSON().splitedCommand(commandFields.getCommand(), commandFields.getConsoleManager());

        String key = splitCommand[0];
        String json = splitCommand[1];

        if (json != null) {
            labWork = new ParserJSON(commandFields.getConsoleManager()).deserializeElement(json);
            labWork.setCreationDate(ZonedDateTime.now());
            labWork = labWorkProcess.getProcessedElementWithError(labWork, checker);
        } else {
            labWork = labWorkProcess.getProcessedElement(labWork, checker);
        }

        if (labWork.getDescription().length() < commandFields.getLabWorkDAO().get(key).getDescription().length()) {
            commandFields.getLabWorkDAO().create(key, labWork);
        }
        commandFields.getConsoleManager().successfully("Команда replace_if_lower успешно выполнена");
    }
}
