package laba;
import commands.CommandsManager;
import dao.LabWorkDAO;
import models.LabWork;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class App {

    public static void main(String[] args) {

        CommandsManager commandsManager = new CommandsManager();

        Scanner scanner = new Scanner(System.in);

        while (true){
            commandsManager.inputCommand(scanner.nextLine());
        }


    }
}
