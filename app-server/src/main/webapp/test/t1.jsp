<%--
  Created by IntelliJ IDEA.
  User: zzl
  Date: 2015/12/16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${home}/script/lib/jquery.js"></script>
</head>
<body>
<h1>t1</h1>
<%--<form method="post" action="${home}/owner/save_profile.json" enctype="multipart/form-data">--%>
    <%--<input type="hidden" name="photoFileData" value="xxx"/>--%>
    <%--<fieldset>--%>
        <%--<legend>请选择文件</legend>--%>
        <%--<div style="padding-left: 1em;">--%>
            <%--<input type="file" name="file" id="btnFile"/>--%>
        <%--</div>--%>
    <%--</fieldset>--%>
    <%--<input type="submit" value="submit"/>--%>
<%--</form>--%>
<iframe id="frame1" src="http://localhost:9082/ht-app-server/test/t2.jsp"
<%--<iframe id="frame1" src="http://localhost:9082/ht-app-server/test/t2.jsp"--%>
<%--<iframe id="frame1" src="t2.jsp"--%>
    style="width: 300px; height: 200px;"></iframe>
<div>
    <input type="button" value="test" onclick="test();"/>
</div>
<script>
    function test() {
        var $frame = $('#frame1');
        alert($frame.contents().find('input').val());
    }
</script>
</body>
</html>
