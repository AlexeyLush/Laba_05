package files;

import files.file.FileManager;
import files.file.ReadCommand;
import io.ConsoleManager;

import java.util.Map;

/**
 * Класс для запуска файла со скриптами
 */

public class ExecuteFileManager extends FileManager implements ReadCommand {

    private ConsoleManager consoleManager;

    public ExecuteFileManager(String fileName, ConsoleManager consoleManager) {
        super(fileName);
        this.consoleManager = consoleManager;
    }

    @Override
    public Map getCommands() {
        return null;
    }


}
