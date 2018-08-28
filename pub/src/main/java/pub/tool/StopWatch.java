package pub.tool;

/**
 * Created by zzl.
 * Date: 2018-03-04
 */
public class StopWatch {

    private long tick0;

    public StopWatch() {
        this(true);
    }

    public StopWatch(boolean startNow) {
        if(startNow) {
            start();
        }
    }

    public void start() {
        tick0 = System.currentTimeMillis();
    }

    public void reset() {
        tick0 = System.currentTimeMillis();
    }

    public void showElapsed() {
        showElapsed("", true);
    }

    public void showElapsed(String msg) {
        showElapsed(msg, true);
    }

    public void showElapsed(String msg, boolean reset) {
        long tick = System.currentTimeMillis();
        System.out.println(msg + "[Ticks:" + (tick - tick0) + "]");
        if(reset) {
            tick0 = tick;
        }
    }
}
