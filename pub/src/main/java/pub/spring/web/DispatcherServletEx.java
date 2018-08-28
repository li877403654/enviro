package pub.spring.web;

import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.DispatcherServlet;
import pub.spring.bean.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * describe: Created by IntelliJ IDEA.
 * @author zzl
 * @version 12-4-11
 */
public class DispatcherServletEx extends DispatcherServlet {

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BeanUtils.setThreadApplicationContext(getWebApplicationContext());
        try {
            super.doDispatch(request, response);
        }catch (MultipartException e){
            //jyf文件过大判断
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().write("<h1>上传失败，文件内容过大</h1>");
        }
        finally {
            BeanUtils.setThreadApplicationContext(null);
        }
    }
}
