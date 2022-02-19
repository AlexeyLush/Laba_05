package files;

import files.file.FileManager;
import files.file.ReadCommand;

import java.util.Map;

public class ExecuteFileManager extends FileManager implements ReadCommand {

    public ExecuteFileManager(String fileName) {
        super(fileName);
    }

    @Override
    public Map getCommands() {
        return null;
    }


}
