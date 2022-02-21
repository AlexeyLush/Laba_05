package exception;

public class ParserException extends CustomException{
    public ParserException() {
        setMessage("Во время парсинга данных произошла ошибка");
    }
}
