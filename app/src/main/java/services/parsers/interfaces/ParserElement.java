package services.parsers.interfaces;

import java.util.Map;

public interface ParserElement<T> {

    T deserializeElement(String json);
    String serializeElement(T elements);

}
