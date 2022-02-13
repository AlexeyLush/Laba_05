package commands;


import dao.LabWorkDAO;

public abstract class CommandAbstract {

    private String title;
    private String description;

    public abstract void execute(LabWorkDAO labWorkDAO, CommandsManager commandsManager);

    public String showInfoCommand(){
        return String.format("%s : %s", title, description);
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
