package files.file;

import files.file.FileCheck;

import java.io.Reader;
import java.io.Writer;

public abstract class FileManager implements FileCheck {

    private final String fileName;

    public String getFileName(){
        return this.fileName;
    }

    public FileManager(String fileName){
        this.fileName = fileName;
    }
}
