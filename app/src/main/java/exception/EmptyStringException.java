package exception;

import com.sun.media.sound.InvalidDataException;

/**
 * Исключение срабатывает, когда строка оказывается пустой
 */

public class EmptyStringException extends CustomException {
    public EmptyStringException(){
        setMessage("Строка не может быть пустой.");
    }
}
