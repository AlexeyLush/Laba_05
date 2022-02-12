package exception;

public class NotFoundCommandException extends Exception {
    @Override
    public String toString() {
        return "Команда не найдена.";
    }
}
