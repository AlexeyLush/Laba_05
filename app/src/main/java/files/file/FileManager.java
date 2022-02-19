package files.file;

import io.ConsoleManager;

public abstract class FileManager {

    private final ConsoleManager consoleManager;
    private final String fileName;

    public String getFileName(){
        return this.fileName;
    }

    public FileManager(String fileName, ConsoleManager consoleManager){
        this.consoleManager = consoleManager;
        this.fileName = fileName;
    }

    public ConsoleManager getConsoleManager(){
        return consoleManager;
    }
}
