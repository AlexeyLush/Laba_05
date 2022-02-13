package commands;


import dao.LabWorkDAO;

public abstract class CommandAbstract {

    private String title;
    private String description;
    private int argumentsCount = 0;

    public abstract void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager);

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

    public int getArgumentsCount() {
        return argumentsCount;
    }

    public void setArgumentsCount(int argumentsCount) {
        this.argumentsCount = argumentsCount;
    }
}
