package exception;

public class NotFoundCommandException extends CustomException {
    public NotFoundCommandException(){
        setMessage("Команда не найдена");
    }
}
