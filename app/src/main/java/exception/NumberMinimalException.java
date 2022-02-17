package exception;

public class NumberMinimalException extends CustomException{
    public NumberMinimalException(Number number){
        setMessage(String.format("Число должно быть больше %d", number));
    }
}
