package exception;

public class IdNotFoundException extends CustomException{
    @Override
    public String toString() {
        return "id указанный в команде не найден.";
    }
}
