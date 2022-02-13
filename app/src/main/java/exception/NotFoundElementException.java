package exception;

public class NotFoundElementException extends Exception{
    @Override
    public String toString() {
        return "Элемент не найден.";
    }
}
