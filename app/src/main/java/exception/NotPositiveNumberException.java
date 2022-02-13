package exception;

public class NotPositiveNumberException extends CustomException{
    @Override
    public String toString() {
        return "Значение поля должно быть больше 0";
    }
}
