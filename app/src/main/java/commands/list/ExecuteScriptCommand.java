package commands.list;

import commands.CommandAbstract;
import commands.CommandsManager;
import commands.models.CommandFields;
import io.ConsoleManager;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Команда считывания и исполнения скрипта из указанного файла
 */

public class ExecuteScriptCommand extends CommandAbstract {

    public ExecuteScriptCommand(){
        setTitle("execute_script");
        setDescription("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    @Override
    public void execute(CommandFields commandFields) {

    }
}
