package files.file;

import java.util.Map;

public interface FileWork<K, V> {

    void write(V element);
    void writeMap(Map<K,V> elements);

    Map<K, V> readFile();

    void save();

}
