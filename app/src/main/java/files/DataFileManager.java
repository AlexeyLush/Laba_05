package files;

import commands.CommandAbstract;
import files.file.FileCreator;
import files.file.FileManager;
import files.file.FileWork;

import java.util.Map;

public class DataFileManager extends FileManager implements FileWork<Integer, CommandAbstract>, FileCreator {

    public DataFileManager(String fileName) {
        super(fileName);
        if (!isCreatedFile()){
            createFile();
        }
    }

    @Override
    public boolean isCreatedFile() {
        return false;
    }


    @Override
    public void write(CommandAbstract element) {

    }

    @Override
    public void writeMap(Map<Integer, CommandAbstract> elements) {

    }

    @Override
    public Map<Integer, CommandAbstract> readFile() {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public void createFile() {

    }
}
