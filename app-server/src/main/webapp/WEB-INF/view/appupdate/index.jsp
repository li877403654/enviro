<%--
  Created by IntelliJ IDEA.
  User: zzl
  Date: 2015/12/30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport"
          content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width">

    <title>汉唐华盛App下载</title>
    <style>
        body {
            font-family: 'Microsoft YaHei', sans-serif;
            padding: 1.4em 0 0 1.2em;
        }
        .msg {
            color: #63b3fa;
            font-size: 20px;
            font-weight: bold;
        }
        h1 {
            font-size: 34px;
            font-weight: normal;
            margin: .4em 0;
        }
        h1 img {
            vertical-align: middle;
            position: relative;
            top: -2px;
        }
        h2 {
            font-size: 28px;
            font-weight: normal;
        }
        a {
            display: block;
            width: 10.6em;
            height: 3.2em;
            position: relative;
            font-size: 19px;
            /*font-weight: bold;*/
            margin-left: .1em;
            margin-top: 2em;
        }
        a::before {
            content: '';
            position: absolute;
            background: #63b3fa;
            width: 100%;
            height: 90%;
            z-index: 2;
            border-radius: .5em;
        }
        a::after {
            content: '';
            position: absolute;
            background: #4a90e2;
            width: 100%;
            height: 100%;
            z-index: 1;
            border-radius: .5em;
            border-bottom-left-radius: .8em;
            border-bottom-right-radius: .8em;
            visibility: hidden;
        }
        a label {
            position: absolute;
            left: 50%;
            width: 100%;
            margin-left: -4em;
            top: .8em;
            /*top: 50%;*/
            /*margin-top: -.5em;*/
            z-index: 3;
            color: white;
        }
        a:active, a.active {
            position: relative;
            /*top: 2px;*/
            top: 1px;
            left: 1px;
        }
        a:active::before, a.active::before {
            background-color: #5ca5e6;
        }
        a:active::after, a.active::after {
            height: 97%;
        }
        #btn-download {
            -webkit-touch-callout:none;
            -webkit-text-size-adjust:none;
            -webkit-tap-highlight-color:rgba(0,0,0,0);
            -webkit-user-select:none;
        }
    </style>
</head>
<body>
<div class="msg">新版发布</div>
<h1>
    <img src="${home}/image/app-icon.png" width="38"/>
    汉唐华盛
</h1>
<a id="btn-download" href="${url}"><label>立即下载（v${version}）</label></a>
<script>
    function _absorbEvent(event) {
        var e = event || window.event;
        e.preventDefault && e.preventDefault();
        e.stopPropagation && e.stopPropagation();
        e.cancelBubble = true;
        e.returnValue = false;
    }

    var btn = document.getElementById('btn-download');
    btn.addEventListener('touchstart', function(e) {
        btn.className = 'active';
        _absorbEvent(e);
    });
    btn.addEventListener('touchend', function(e) {
        btn.className = '';
//        _absorbEvent(e);

        var x = e.changedTouches[0].pageX;
        var y = e.changedTouches[0].pageY;

        var offset = {
            left: btn.offsetLeft,
            top: btn.offsetTop
        };
        if(x > offset.left && y > offset.top &&
            x < offset.left + btn.offsetWidth &&
            y < offset.top + btn.offsetHeight) {
            setTimeout(function() {
                btn.click();
            }, 10);
        }

    });
    //    btn.addEventListener('touchcancel', function(e) {
    //        btn.className = '';
    //        _absorbEvent(e);
    //    });
</script>
</body>
</html>
