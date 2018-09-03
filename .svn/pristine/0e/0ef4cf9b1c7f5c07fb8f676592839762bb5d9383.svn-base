package envir.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zzl.
 * Date: 2017/9/4
 */
@Component
public class DebugLogger implements InitializingBean {

    private OutputStreamWriter out;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");

    @Override
    public void afterPropertiesSet() throws Exception {
        //String logFilePath = sc.getRealPath("/debug.log");
        String catalinaBase = System.getProperty("catalina.base");
        if(catalinaBase == null) {
            out = new OutputStreamWriter(System.out);
            return;
        }
        File logFile = new File(catalinaBase, "logs/envir.debug.log");
        if(logFile.length() > 1024 * 1024 * 144) { //144M
            File oldLogFile = new File(catalinaBase, "logs/envir.debug.log.old");
            if(oldLogFile.exists()) {
                oldLogFile.delete();
            }
            logFile.renameTo(oldLogFile);
        }
        out = new FileWriter(logFile, true);

        //
        //C:\Users\zzl\.IntelliJIdea2017.2\system\tomcat\Unnamed_ht-app-server\logs\envir.debug.log

        //
        out.write("[Debug log inited@" + new Date() + "]\n");
        out.flush();
    }

    public void log(String message) {
        log(null, message);
    }

    public void log(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.close();
        String message = "发生异常: " + "[" + e.getClass().getSimpleName() + "]" +
                e.getMessage() + "\n" + sw.toString();
        log(null, message);
    }

    public void log(String tag, String message) {
        try {
            StringBuilder sb = new StringBuilder();

//            sb.append('[').append(Thread.currentThread().getName()).append(']');
            sb.append('[').append(dateFormat.format(new Date())).append(']');
            if(tag != null) {
                sb.append('[').append(tag).append(']');
            }
            sb.append(message);
            sb.append('\n');

            out.write(sb.toString());
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Autowired
//    private ServletContext sc;

}
