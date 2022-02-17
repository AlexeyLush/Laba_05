package exception;

public class EmptyFieldException extends CustomException{
    public EmptyFieldException() {
        setMessage("Вы не ввели значение");
    }
}
