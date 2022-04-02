package laba;
import commands.CommandsManager;
import dao.LabWorkDAO;
import files.DataFileManager;
import files.ExecuteFileManager;
import io.ConsoleManager;

import java.io.File;
import java.util.Scanner;

/**
 * Класс старта программы)
 */

public class App {

    private static boolean isRun = true;
    public static void exit(){
        isRun = false;
    }
    public static void run(Scanner scanner, String dataFileName, ConsoleManager consoleManager, LabWorkDAO labWorkDAO, boolean isMainFile){

        if (!isMainFile){
            consoleManager.warning("Внимание! Программа не смогла получить доступ к основному файлу из-за ограничений. Программа будет работать с временным файлом");
        }
        DataFileManager dataFileManager = new DataFileManager(dataFileName, consoleManager, isMainFile);
        labWorkDAO.initialMap(dataFileManager.readMap());
        ExecuteFileManager executeFileManager = new ExecuteFileManager(dataFileName, consoleManager);
        CommandsManager commandsManager = new CommandsManager(scanner, consoleManager, dataFileManager, executeFileManager);


        while (isRun){
            commandsManager.inputCommand(labWorkDAO);
        }
    }

    public static void main(String[] args) {

        ConsoleManager consoleManager = new ConsoleManager();
        LabWorkDAO labWorkDAO = new LabWorkDAO();
        Scanner scanner = new Scanner(System.in);

        String dataFileName = System.getenv("LABWORKS_FILE_PATH");
        boolean isMainFile = true;

        File file = new File(dataFileName);

        if (!file.canRead()){
            dataFileName = String.format("%s/lab_works_temp.json", System.getenv("TEMP"));
            isMainFile = false;
        }

        if (dataFileName == null || dataFileName.trim().isEmpty()){
            consoleManager.error("Ошибка настройки переменного окружения! Программа завершает работу...");
            App.exit();
        }
        run(scanner, dataFileName, consoleManager, labWorkDAO, isMainFile);

    }
}
