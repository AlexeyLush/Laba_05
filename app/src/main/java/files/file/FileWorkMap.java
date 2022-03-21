package files.file;

import java.util.Map;

/**
 * Интерфейс для работы с файлом
 */

public interface FileWorkMap<K, V> {

    Map<K, V> readMap();
    void save(Map<K, V> elements);

}
