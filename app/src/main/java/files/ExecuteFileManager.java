package files;

import files.file.FileManager;
import files.file.ReadCommand;

import java.util.Map;

public class ExecuteFileManager extends FileManager implements ReadCommand {

    public ExecuteFileManager(String fileName) {
        super(fileName);
        if (!isCreatedFile()){

        }
    }

    @Override
    public Map getCommands() {
        return null;
    }

    @Override
    public boolean isCreatedFile() {
        return false;
    }

}
