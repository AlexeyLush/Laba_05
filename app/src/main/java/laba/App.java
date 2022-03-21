package laba;
import commands.CommandsManager;
import dao.LabWorkDAO;
import files.DataFileManager;
import files.ExecuteFileManager;
import io.ConsoleManager;

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

        DataFileManager dataFileManager = new DataFileManager(dataFileName, consoleManager);
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
        if (dataFileName == null || dataFileName.trim().isEmpty()){
            consoleManager.error("Ошибка настройки переменного окружения! Программа завершает работу...");
            App.exit();
        }
        run(scanner, dataFileName, consoleManager, labWorkDAO);

    }
}
