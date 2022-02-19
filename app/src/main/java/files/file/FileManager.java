package files.file;

import files.file.FileCheck;
import io.ConsoleManager;

import java.io.Reader;
import java.io.Writer;

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
