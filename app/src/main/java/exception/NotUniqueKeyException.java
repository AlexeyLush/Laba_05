package exception;

public class NotUniqueKeyException extends CustomException {
    public NotUniqueKeyException() {
        setMessage("Этот ключ уже занят");
    }
}
