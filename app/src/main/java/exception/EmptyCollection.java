package exception;

public class EmptyCollection extends CustomException{
    public EmptyCollection(){
        setMessage("коллекция пустая..........");
    }
}
