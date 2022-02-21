package exception;

/**
 * Исключение срабатывает, когда id, указанный в команде, не найдем
 */

public class IdNotFoundException extends CustomException{
    @Override
    public String toString() {
        return "id указанный в команде не найден.";
    }
}
