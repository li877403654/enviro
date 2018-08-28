package pub.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

/**
 * Created by zzl.
 * Date: 2017/10/17
 */
@Plugin(name = "SuppressibleFilter", category = Node.CATEGORY, elementType = Filter.ELEMENT_TYPE, printObject = true)
public class SuppressibleFilter extends AbstractFilter {

    private static final long serialVersionUID = 1L;

    //private final Level level;

    private SuppressibleFilter() {
        //super(onMatch, onMismatch);
        //this.level = level;
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object... params) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Object msg,
                         final Throwable t) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Message msg,
                         final Throwable t) {
        return filter(level);
    }

    @Override
    public Result filter(final LogEvent event) {
        return filter(event.getLevel());
    }

    private Result filter(final Level level) {
        return LogSuppressor._suppressFlag.get()? Result.DENY: Result.NEUTRAL;
    }

    /**
     * Create a SuppressibleFilter.
     * @return The created SuppressibleFilter.
     */
    @PluginFactory
    public static SuppressibleFilter createFilter() {
        return new SuppressibleFilter();
    }

}
