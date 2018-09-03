package pub.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class HttpUtils {

    private HttpUtils() {
    }

    public static void printAndClose(HttpServletResponse resp, String json) throws Exception {


        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw;
        try {
            pw = resp.getWriter();
            pw.println(json);
            pw.flush();
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new Exception("put json to client error");
        }

    }

    public static String get(String url) {
        return get(url, 0);
    }

    public static String get(String url, int timeout) {
        try {
            return _get(url, timeout);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String _get(String url, int timeout) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        if(timeout != 0) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .setSocketTimeout(timeout)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            httpGet.setConfig(requestConfig);
        }
        CloseableHttpResponse httpResponse = httpclient.execute(httpGet);

        String result = null;
        try {
//            System.out.println(httpResponse.getStatusLine());
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "utf-8");
        }
        finally {
            httpResponse.close();
        }
        return result;
    }

}
