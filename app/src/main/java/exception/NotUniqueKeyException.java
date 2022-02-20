package exception;

public class NotUniqueKeyException extends CustomException {
    public NotUniqueKeyException(String  key) {
        setMessage(String.format("Ключ %s уже занят", key));
    }
}
