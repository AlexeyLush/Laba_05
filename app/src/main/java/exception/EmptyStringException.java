package exception;

import com.sun.media.sound.InvalidDataException;

public class EmptyStringException extends CustomException {
    public EmptyStringException(){
        setMessage("Строка не может быть пустой.");
    }
}
