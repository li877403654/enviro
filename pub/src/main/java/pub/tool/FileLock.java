package pub.tool;

import pub.functions.StrFuncs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zzl.
 * Date: 2018-03-31
 */
public class FileLock {

    private static ConcurrentHashMap<String, Object> _lockMap = new ConcurrentHashMap<>();

    public static void lock(String filePath, Runnable action) {
        lock(new String[]{filePath}, action);
    }

    public static void lock(String[] filePaths, Runnable action) {
        List<String> filePathList = new ArrayList<String>();
        for(String filePath: filePaths) {
            filePathList.add(filePath.replace('\\', '/').toUpperCase());
        }
        filePathList.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        String key = StrFuncs.join(filePathList, '\n');
        Object lock = null;
        synchronized (_lockMap) {
            lock = _lockMap.get(key);
            if(lock == null) {
                lock = new Object();
                _lockMap.put(key, lock);
            }
        }
        synchronized (lock) {
            action.run();
        }
    }

}
