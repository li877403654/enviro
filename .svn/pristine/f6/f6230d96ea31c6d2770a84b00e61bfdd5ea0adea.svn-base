/**
 * 引用JS和CSS头文件
 */

window.session = {};

var rootPath = getRootPath(); //项目路径

var inBrowser = location.href.substr(0, 5) === 'http:';

/**
 * 动态加载CSS和JS文件
 */
var dynamicLoading = {
    meta: function () {

        document.write('<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">');
        document.write('<meta http-equiv="Access-Control-Allow-Origin" content="*">');
        document.write('<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />');
        document.write('<meta name="apple-mobile-web-app-status-bar-style" content="black">');
        document.write('<meta name="apple-mobile-web-app-capable" content="yes">');

    },
    css: function (path) {
        if (!path || path.length === 0) {
            throw new Error('argument "path" is required!');
        }
        document.write('<link rel="stylesheet" type="text/css" href="' + path + '">');
    },
    js: function (path, charset) {
        if (!path || path.length === 0) {
            throw new Error('argument "path" is required!');
        }
        document.write('<script type="text/javascript" charset="' + (charset ? charset : "utf-8") + '" src="' + path + '"></script>');
    }
};

/**
 * 取得项目路径
 * @author wul
 */
function getRootPath() {
    //取得当前URL
    var path = window.document.location.href;
    var ua = window.navigator.userAgent.toLowerCase();
    if (/android|adr/gi.test(ua)) { //安卓

        var post = path.indexOf("www");
        //取得主机地址
        var hostPath = path.substring(0, post + 3);
        //取得项目名
        var name = hostPath.substring(0, hostPath.substr(1).indexOf("/") + 1);
        return hostPath + "/";
    } else if (/\(i[^;]+;( U;)? CPU.+Mac OS X/gi.test(ua)) { //苹果
        //取得主机地址后的目录
        var pathName = window.document.location.pathname;
        // 得到目录的位置
        var post = path.indexOf(pathName);
        //截取主机地址
        var host = path.substr(0, post);
        //获取项目名
        var name = pathName.substr(0, pathName.substr(1).indexOf("/") + 1);
        console.log("iphone-chrome");
        return host + name + "/";
    } else if (isBrowser(ua) && /mozilla/i.test(ua)) {
        //取得主机地址后的目录
        var host = window.document.location.host;
        //获取项目名
        var name = host.substr(0, host.substr(1).indexOf("/") + 1);
        return name + "/";
    }
}

/**
 * 判断是否在浏览器中
 * @param UserAgent
 * @returns {*}
 */
function isBrowser(UserAgent) {
    var value = {
        isUc: /ucweb/.test(UserAgent), // UC浏览器
        isChrome: /chrome/.test(UserAgent.substr(-33, 6)), // Chrome浏览器
        isFirefox: /firefox/.test(UserAgent), // 火狐浏览器
        isOpera: /opera/.test(UserAgent),  // Opera浏览器
        isSafire: /safari/.test(UserAgent) && !/chrome/.test(UserAgent), // safire浏览器
        is360: /360se/.test(UserAgent), // 360浏览器
        isBaidu: /bidubrowser/.test(UserAgent), // 百度浏览器
        isSougou: /metasr/.test(UserAgent), // 搜狗浏览器
        isIE6: /msie 6.0/.test(UserAgent), // IE6
        isIE7: /msie 7.0/.test(UserAgent), // IE7
        isIE8: /msie 8.0/.test(UserAgent), // IE8
        isIE9: /msie 9.0/.test(UserAgent), // IE9
        isIE10: /msie 10.0/.test(UserAgent), // IE10
        isIE11: /rv:11.0/.test(UserAgent), // IE11
        isLB: /lbbrowser/.test(UserAgent), // 猎豹浏览器
        isWX: /micromessenger/.test(UserAgent), // 微信内置浏览器
        isQQ: /qqbrowser/.test(UserAgent) // QQ浏览器
    };

    for (var key in value) {
        if (value[key]) {
            return true;
        }
    }
    return false;
}


//动态生成meta
dynamicLoading.meta();

//动态加载项目 JS文件
dynamicLoading.js(rootPath + "layui/layui.js", "utf-8");
dynamicLoading.js(rootPath + "js/lib/jquery-3.3.1.min.js", "utf-8");
dynamicLoading.js(rootPath + "js/app.js", "utf-8");
dynamicLoading.js(rootPath + "js/pub.js", "utf-8");
dynamicLoading.js(rootPath + "js/backservice.js", "utf-8");
dynamicLoading.js(rootPath + "js/test.local.js", "utf-8");


//动态加载项目 CSS文件
dynamicLoading.css(rootPath + "layui/css/layui.css");