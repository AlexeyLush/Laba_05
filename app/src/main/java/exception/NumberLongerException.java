package exception;

public class NumberLongerException extends CustomException {
    public NumberLongerException(Number number){
        setMessage(String.format("Максимальное значение: %d", number));
    }
}
