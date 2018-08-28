/**
 * js功能描述: <br>
 * ()
 *
 * @Author: Mr. xyb
 * @Date: 2018/8/13 10:30
 * @version: 1.0.0
 */
layui.config({
    base: "../layui"
}).use(['layer', 'jquery'], function () {
    var app = {};

    var app = {
        version: '1.0.1',
        debug: false
    };

    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    app.showLoading = function (time) {
      //  layer.load(2, {shade: false, time: time * 1000 || 2 * 1000})
    }

    app.hideLoading = function () {
        layer.close();
    }


    app.alert = function (msg, title) {
        if (!msg) {
            msg = "玩命提示"
        }
        layer.alert(msg, {
            skin: 'layui-layer-molv' //样式类名
            , closeBtn: 0,
            title: title || '提示'
        });
    }

    app.showToast = function (msg) {
        layer.msg(msg | "玩命提示中...");
    }

    window.app = app;
});
