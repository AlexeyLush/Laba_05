package commands;


public abstract class CommandAbstract {

    private String title;
    private String description;

    public abstract void execute();

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
