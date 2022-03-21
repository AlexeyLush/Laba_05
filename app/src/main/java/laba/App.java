package laba;
import commands.CommandsManager;
import dao.LabWorkDAO;
import files.DataFileManager;
import files.ExecuteFileManager;
import files.file.FileManager;
import io.ConsoleManager;
import models.LabWork;
import services.parsers.ParserJSON;

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
    public static void run(Scanner scanner, String dataFileName, ConsoleManager consoleManager, LabWorkDAO labWorkDAO){

        CommandsManager commandsManager = null;

        DataFileManager dataFileManager = new DataFileManager(dataFileName, consoleManager);
        labWorkDAO.initialMap(dataFileManager.readFile());
        ExecuteFileManager executeFileManager = new ExecuteFileManager(dataFileName, consoleManager);

        while (isRun){
            Scanner scannerT = new Scanner(System.in);
            scannerT.reset();
            commandsManager = new CommandsManager(scannerT, consoleManager, dataFileManager, executeFileManager);
            commandsManager.inputCommand(labWorkDAO);
        }
    }

    public static void main(String[] args) {

        ConsoleManager consoleManager = new ConsoleManager();
        LabWorkDAO labWorkDAO = new LabWorkDAO();
        Scanner scanner = new Scanner(System.in);

        String dataFileName = System.getenv("LABWORKS_FILE_PATH");
        if (dataFileName == null || dataFileName.trim().isEmpty()){
            consoleManager.error("Ошибка настройки переменного окружения! Программа завершает работу...");
            App.exit();
        }
        run(scanner, dataFileName, consoleManager, labWorkDAO);

    }
}
