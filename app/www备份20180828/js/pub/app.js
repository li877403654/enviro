/**
 * Created by zzl.
 * Date: 2018/8/10
 */
(function () {

    var app = {};

    app.alert = function(msg, title, callback) {
        top.fx.dialog.alert(msg, title, callback);
    };

    app.confirm = function(msg, title, callback, cancelCallback) {
        top.fx.dialog.confirm(msg, title, callback, cancelCallback);
    };

    app.showToast = function(msg, timeout, moreConfig) {
        return top.fx.showToast(msg, timeout, moreConfig);
    };

    app.openTab = function(url, title, iconUrl, callback) {
        top.fx.tabMan.addTab(url, title, iconUrl, callback);
    };

    app.closeTab = function(tabWindow) {
        tabWindow = tabWindow || window;
        top.fx.tabMan.removeTabByFrameWindow(tabWindow);
    };

    window.app = app;

})();