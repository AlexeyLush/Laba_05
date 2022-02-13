package exception;

import io.ConsoleManager;

public abstract class CustomException extends Exception {

    private String message;

    protected void setMessage(String message){
        this.message = message;
    }

    public void outputException(){
        ConsoleManager manager = new ConsoleManager();
        manager.error(message);
    }

}
