package envir.web.app.listener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * describe: Created by IntelliJ IDEA.
 * @author zzl
 * @version 2010-2-9
 */
public class AppListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sc);

        sc.setAttribute("home", sc.getContextPath());

    }

    public void contextDestroyed(ServletContextEvent event) {
    }

}
