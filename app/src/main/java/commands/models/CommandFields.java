package commands.models;

import commands.CommandsManager;
import dao.LabWorkDAO;
import exception.ParserException;
import files.DataFileManager;
import files.ExecuteFileManager;
import io.ConsoleManager;
import services.parsers.ParserJSON;

import javax.xml.crypto.Data;
import java.util.Scanner;

/**
 * Класс для моделей
 */

public class CommandFields {

    private final Scanner scanner;
    private final String command;
    private final LabWorkDAO labWorkDAO;
    private final CommandsManager commandsManager;
    private final ConsoleManager consoleManager;
    private final DataFileManager dataFileManager;

    public CommandFields(Scanner scanner, String command, LabWorkDAO labWorkDAO, CommandsManager commandsManager, DataFileManager dataFileManager,
                         ConsoleManager consoleManager) {
        this.scanner = scanner;
        this.command = command;
        this.labWorkDAO = labWorkDAO;
        this.commandsManager = commandsManager;
        this.dataFileManager = dataFileManager;
        this.consoleManager = consoleManager;
    }

    public Scanner getScanner(){
        return scanner;
    }

    public String getCommand() {
        return command;
    }

    public LabWorkDAO getLabWorkDAO() {
        return labWorkDAO;
    }

    public DataFileManager getDataFileManager() {
        return dataFileManager;
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }

    public ConsoleManager getConsoleManager() {
        return consoleManager;
    }

}
