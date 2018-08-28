package pub.logging;

import java.util.function.Supplier;

/**
 * Created by zzl on 2016-11-22.
 */
public final class LogSuppressor {

    public static ThreadLocal<Boolean> _suppressFlag = ThreadLocal.withInitial(new Supplier<Boolean>() {
        @Override
        public Boolean get() {
            return false;
        }
    });

    public static void suppress(boolean suppress) {
        _suppressFlag.set(suppress);
    }

}
