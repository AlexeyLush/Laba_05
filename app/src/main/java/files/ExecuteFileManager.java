package files;

import files.file.FileManager;
import files.file.ReadCommand;
import io.ConsoleManager;

import java.util.Map;

public class ExecuteFileManager extends FileManager implements ReadCommand {

    public ExecuteFileManager(String fileName, ConsoleManager consoleManager) {
        super(fileName, consoleManager);
    }

    @Override
    public Map getCommands() {
        return null;
    }


}
