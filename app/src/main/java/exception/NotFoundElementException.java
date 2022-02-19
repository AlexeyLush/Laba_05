package exception;

public class NotFoundElementException extends CustomException{
    public NotFoundElementException(){
        setMessage("Элемент не найден");
    }
}
