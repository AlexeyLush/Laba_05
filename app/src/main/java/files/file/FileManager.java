package files.file;

import io.ConsoleManager;

/**
 * Абстрактный класс для менеджера файла
 */

public abstract class FileManager {

    private final String fileName;

    public String getFileName(){
        return this.fileName;
    }

    public FileManager(String fileName){
        this.fileName = fileName;
    }

}
