package exception;

public class NotEnteredKeyException extends CustomException{
    public NotEnteredKeyException(){
        setMessage("Вы не ввели ключ");
    }
}
