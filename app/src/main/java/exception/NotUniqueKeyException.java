package exception;

/**
 * Исключение срабатывает, если ключ, введённый пользователем уже занят
 */

public class NotUniqueKeyException extends CustomException {
    public NotUniqueKeyException(int key) {
        setMessage(String.format("Ключ %d уже занят", key));
    }
}
