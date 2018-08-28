package pub.dao.jdbc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zzl.
 * Date: 2017-08-20
 */
public class LowercaseColumnMap<V> extends LinkedHashMap<String, V> {

    public LowercaseColumnMap() {
        super();
    }

    public LowercaseColumnMap(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public V put(String key, V value) {
        key = key.toLowerCase();
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> map) {
        //?super.putAll(map);
        for(String key: map.keySet()) {
            put(key, map.get(key));
        }
    }

    @Override
    public V putIfAbsent(String key, V value) {
        key = key.toLowerCase();
        return super.putIfAbsent(key, value);
    }

}
