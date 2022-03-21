package commands.list;

import commands.CommandAbstract;

import commands.models.CommandFields;

import exception.NotFoundScriptFileException;
import files.ExecuteFileManager;


import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Команда считывания и исполнения скрипта из указанного файла
 */

public class ExecuteScriptCommand extends CommandAbstract {

    private static Map<String,Integer> scriptsCompleted = new LinkedHashMap<>();

    public ExecuteScriptCommand(){
        setTitle("execute_script");
        setDescription("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    @Override
    public void execute(CommandFields commandFields) {

        String[] commandSplited = commandFields.getCommand().split(" ");
        String fileName = "";
        if (commandSplited.length == 1){
            commandFields.getConsoleManager().output("Введите имя файла (файл должен находится в папке script): ");
            fileName = commandFields.getScanner().nextLine();
        }
        else{
            fileName = commandSplited[1];
        }

        File executeFile = new File(String.format("scripts/%s.txt", fileName));

        try {

            if (!executeFile.isFile()){
                throw new NotFoundScriptFileException();
            }

            if (ExecuteScriptCommand.scriptsCompleted.containsKey(fileName)){
                Integer oldVal = ExecuteScriptCommand.scriptsCompleted.get(fileName);
                ExecuteScriptCommand.scriptsCompleted.put(fileName, oldVal + 1);
            } else{
                ExecuteScriptCommand.scriptsCompleted.put(fileName, 0);
            }

            ExecuteFileManager executeFileManager = new ExecuteFileManager(executeFile.getAbsolutePath(), commandFields.getConsoleManager());
            List<String> scripts = executeFileManager.readFile();

            if (ExecuteScriptCommand.scriptsCompleted.get(fileName) == 0){
                for (String command: scripts){
                    commandFields.getCommandsManager().executeCommand(command, commandFields.getLabWorkDAO());
                }
            } else{
                commandFields.getConsoleManager().warning(String.format("Файл %s уже был исполнен", fileName));
            }

            ExecuteScriptCommand.scriptsCompleted.remove(fileName);

        } catch (NotFoundScriptFileException e) {
            e.outputException();
        }

    }
}
