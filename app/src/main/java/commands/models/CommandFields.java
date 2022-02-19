package commands.models;

import commands.CommandsManager;
import dao.LabWorkDAO;
import files.DataFileManager;
import files.ExecuteFileManager;
import io.ConsoleManager;

public class CommandFields {

    private final String command;
    private final LabWorkDAO labWorkDAO;
    private final CommandsManager commandsManager;
    private final ConsoleManager consoleManager;
    private final DataFileManager dataFileManager;
    private final ExecuteFileManager executeFileManager;

    public CommandFields(String command, LabWorkDAO labWorkDAO, CommandsManager commandsManager,
                         ConsoleManager consoleManager, DataFileManager dataFileManager, ExecuteFileManager executeFileManager) {
        this.command = command;
        this.labWorkDAO = labWorkDAO;
        this.commandsManager = commandsManager;
        this.consoleManager = consoleManager;
        this.dataFileManager = dataFileManager;
        this.executeFileManager = executeFileManager;
    }

    public String getCommand() {
        return command;
    }

    public LabWorkDAO getLabWorkDAO() {
        return labWorkDAO;
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }

    public ConsoleManager getConsoleManager() {
        return consoleManager;
    }

    public DataFileManager getDataFileManager() {
        return dataFileManager;
    }

    public ExecuteFileManager getExecuteFileManager() {
        return executeFileManager;
    }

}
