package laba;
import commands.CommandsManager;
import dao.LabWorkDAO;
import files.DataFileManager;
import files.ExecuteFileManager;
import files.file.FileManager;
import io.ConsoleManager;
import models.LabWork;

import java.io.Console;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс старта программы)
 */

public class App {

    private static boolean isRun = true;
    public static void exit(){
        isRun = false;
    }
    public static void run(String dataFileName, ConsoleManager consoleManager, LabWorkDAO labWorkDAO){

        CommandsManager commandsManager = null;

        if (isRun){
            DataFileManager dataFileManager = new DataFileManager(dataFileName, consoleManager);
            labWorkDAO.initialMap(dataFileManager.readFile());
            ExecuteFileManager executeFileManager = new ExecuteFileManager(dataFileName, consoleManager);

            commandsManager = new CommandsManager(consoleManager, dataFileManager, executeFileManager);
        }
        while (isRun){
            commandsManager.inputCommand(labWorkDAO);
        }
    }

    public static void main(String[] args) {

        ConsoleManager consoleManager = new ConsoleManager();
        LabWorkDAO labWorkDAO = new LabWorkDAO();

        String dataFileName = System.getenv("LABWORKS_FILE_PATH");
        if (dataFileName == null || dataFileName.trim().isEmpty()){
            consoleManager.error("Ошибка настройки переменного окружения! Программа завершает работу...");
            App.exit();
        }
        run(dataFileName, consoleManager, labWorkDAO);

    }
}
