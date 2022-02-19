package files.file;

import files.file.FileCheck;

public abstract class FileManager implements FileCheck {

    private String fileName;

    public FileManager(String fileName){
        this.fileName = fileName;
    }
}
