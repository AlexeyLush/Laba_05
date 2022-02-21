package exception;

public class EmptyStringException extends CustomException {
    public EmptyStringException(){
        setMessage("Строка не может быть пустой.");
    }
}
