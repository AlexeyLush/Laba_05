package exception;

public class NotNumberException extends CustomException{
    public NotNumberException() {
        setMessage("Введите число");
    }
}
