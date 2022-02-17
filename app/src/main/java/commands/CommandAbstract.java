package commands;


import dao.LabWorkDAO;
import io.ConsoleManager;

public abstract class CommandAbstract {

    private String title;
    private String description;


    public abstract void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager, ConsoleManager consoleManager, String command);

    public String showInfoCommand(){
        return getDescription();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
