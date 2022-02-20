package exception;

public class NotUniqueKeyException extends CustomException {
    public NotUniqueKeyException(int key) {
        setMessage(String.format("Ключ %d уже занят", key));
    }
}
