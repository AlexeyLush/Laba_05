package files.file;

import java.util.Map;

/**
 * Интерфейс для работы с файлом
 */

public interface FileWork<K, V> {

    Map<K, V> readFile();
    void save(Map<K, V> elements);

}
