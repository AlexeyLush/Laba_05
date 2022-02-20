package exception;

/**
 * Исключение срабатывает, если пользователь ввёл значение поля, меньше 0
 */

public class NotPositiveNumberException extends CustomException{
    @Override
    public String toString() {
        return "Значение поля должно быть больше 0";
    }
}
