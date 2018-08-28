<%--
	Created by IntelliJ IDEA.
	User: zzl
	Date: 2010-5-24
	Time: 15:34:55
	To change this template use File | Settings | File Templates.
--%>
<%%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String pageName = request.getServletPath();
    boolean show = false;
    boolean query = false;
    if (pageName.endsWith("query.jsp")) {
        query = true;
    }
    else if (pageName.endsWith("show.jsp")) {
        show = true;
    }
    String qs = "&" + request.getAttribute("javax.servlet.include.query_string") + "&";
    if(qs.contains("&show&")) {
        show = true;
    }
    else if(qs.contains("&query&")) {
        query = true;
    }
%>
<script>
    var _home = '${home}';
</script>

<script src="${home}/script/lib/jquery.js"></script>

<script src="${home}/script/pub.js"></script>
<script src="${home}/script/site.js"></script>

<% if (query) {%>
<script src="${home}/script/query.js"></script>
<%}%>
<% if (show) {%>
<script src="${home}/script/lib/jquery.form.js"></script>
<script src="${home}/script/lib/jquery.validate.js"></script>
<script src="${home}/script/lib/jquery.validate.ex.js"></script>
<script src="${home}/script/show.js"></script>
<%}%>
