package exception;

public class ParserException extends CustomException{
    public ParserException() {
        setMessage("Во время работы с данными произошла ошибка");
    }
}
