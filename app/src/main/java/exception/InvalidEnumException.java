package exception;

public class InvalidEnumException extends CustomException{
    @Override
    public String toString(){
        return "ввод не соответсвует перечислению";
    }
}
