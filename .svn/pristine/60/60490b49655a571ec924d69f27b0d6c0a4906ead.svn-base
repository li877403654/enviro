<%@ page import="org.apache.http.impl.client.HttpClients" %>
<%@ page import="org.apache.http.util.EntityUtils" %>
<%@ page import="org.apache.http.impl.client.CloseableHttpClient" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.client.methods.CloseableHttpResponse" %>
<%@ page import="org.apache.http.HttpEntity" %><%--
  Created by IntelliJ IDEA.
  User: zzl
  Date: 2018-07-13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<pre>
<%
    CloseableHttpClient httpclient = HttpClients.createDefault();

    HttpPost httpPost = new HttpPost("https://sms.yunpian.com/v2/sms/single_send.json");
    ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
    nvps.add(new BasicNameValuePair("apikey", "8560fa348456c81b046d73f9b8a66010"));
    nvps.add(new BasicNameValuePair("text", "【汉唐华盛】test1"));
    nvps.add(new BasicNameValuePair("mobile", "13822181650"));
    httpPost.setEntity(new UrlEncodedFormEntity(nvps));
    CloseableHttpResponse response2 = httpclient.execute(httpPost);

    try {
        out.println(response2.getStatusLine());

        HttpEntity entity2 = response2.getEntity();
        // do something useful with the response body
        // and ensure it is fully consumed
//            EntityUtils.consume(entity2)
        String xx = EntityUtils.toString(entity2);
        out.println(xx);
    } finally {
        response2.close();
    }
%>
    </pre>
</body>
</html>
