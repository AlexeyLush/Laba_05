package exception;

public class ProblemWithFileException extends CustomException{
    public ProblemWithFileException(){
        setMessage("Во время работы возникла проблема с файлом");
    }
}
