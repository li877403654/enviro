package envir.web.app.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zzl on 2015/7/4.
 */
public class UrlFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UrlFilter.class);
    private static AtomicInteger nextReqId = new AtomicInteger(1);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String uri = request.getRequestURI();

        //
        int lastDotPos = uri.lastIndexOf('.');
        boolean logUrl = false;
        if(lastDotPos == -1) {
            logUrl = true;
        }
        else {
            String ext = uri.substring(lastDotPos + 1);
            if(ext.equals("html") || ext.equals("json") || ext.equals("jsp")
                    || ext.equals("do") || ext.equals("xls")) {
                logUrl = true;
            }
        }
        int reqId = -1;

        String logMsg = null;
        long millis0 = System.currentTimeMillis();
        if(logUrl) {
//            reqId = nextReqId.incrementAndGet();
            String url = uri;
            String qs = request.getQueryString();
            if(qs != null && qs.length() > 0) {
                url = url + "?" + qs;
            }
            logMsg = "[REQ][" + request.getMethod() + "][" + url + "]";
        }

        try {
            //
            if (uri.charAt(uri.length() - 1) == '/') {
                uri = uri.substring(request.getContextPath().length()) + "index.html";
                request.getRequestDispatcher(uri).forward(request, servletResponse);
                return;
            }

            chain.doFilter(request, servletResponse);

        }
        finally {
            if(logUrl) {
                logMsg += "[" + (System.currentTimeMillis() - millis0) + "ms]";
                logger.debug(logMsg);

            }
        }
    }

    @Override
    public void destroy() {

    }
}
