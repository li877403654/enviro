<%@ page import="java.util.Map" %>
<%@ page import="java.util.Enumeration" %>
<%--
  Created by IntelliJ IDEA.
  User: zzl
  Date: 14-1-23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
    <script type="text/javascript" src="${home}/script/lib/jquery.js"></script>
    <script>
        $(function() {
            var foo = "foo";
            // eval的作用域是当前作用域(在这里就是local也就是function作用域),
            // 因此既能访问local也能访问global作用域
            eval("alert('eval: ' + foo)");
            $.globalEval("alert('globalEval: ' + foo)");

        });
    </script>
</head>
<body>
<h1>test</h1>
xx
</body>
</html>