package files.file;

import java.util.Map;

public interface ReadCommand<K,V> {
    Map<K,V> getCommands();
}
