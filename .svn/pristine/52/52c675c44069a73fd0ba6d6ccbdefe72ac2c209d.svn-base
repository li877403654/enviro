package ext

/**
 * Created by zzl.
 * Date: 2018-04-16
 */
public fun <K, V> Iterable<Pair<K, V>>.toMutableMap(): MutableMap<K, V> {
    val map = mutableMapOf<K, V>()
    for(entry in this) {
        map.put(entry.first, entry.second)
    }
    return map
}