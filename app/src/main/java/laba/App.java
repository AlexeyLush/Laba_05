package laba;
import commands.CommandsManager;
import dao.LabWorkDAO;
import io.ConsoleManager;
import models.LabWork;

import java.io.Console;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class App {

    public static void main(String[] args) {

        ConsoleManager consoleManager = new ConsoleManager();
        CommandsManager commandsManager = new CommandsManager(consoleManager);
        LabWorkDAO labWorkDAO = new LabWorkDAO();

        while (true){
            commandsManager.inputCommand(labWorkDAO);
        }


    }
}
