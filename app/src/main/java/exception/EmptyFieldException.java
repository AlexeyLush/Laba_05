package exception;

public class EmptyNameException extends CustomException{
    public EmptyNameException() {
        setMessage("Вы не ввели имя");
    }
}
