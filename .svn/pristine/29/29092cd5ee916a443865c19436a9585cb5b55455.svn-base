function returnUrl(uri) {
    var url = _loadUrl(uri);
    window.location = url;
}

if (window.$) {
    $(function() {
        _saveUrl();
        _updateReturnUrlFields();
    });
}

function _updateReturnUrlFields() {
    $(':hidden[name=returnUrl]').each(function(index) {
        var uri = this.value;
        this.value = _loadUrl(uri);
    });
}

function _loadUrl(uri) {
    if (uri.charAt(0) != '/' && uri.indexOf('http:') == -1) {
        var path = location.pathname;
        uri = path.substring(0, path.lastIndexOf('/') + 1) + uri;
    }
    if (parent._urlSysDict) {
        var qs = (parent._urlSysDict[uri] || '');
        return uri + (qs ? '?' + qs : '');
    }
    var urls = sessionStorage.urls || '';//(top.window.name || '');
    var key = '\n' + uri + '?';
    var pos = urls.indexOf(key);
    if (pos == -1) {
        return uri;
    }
    var pos2 = urls.indexOf('\n', pos + key.length);
    if (pos2 == -1) {
        pos2 = urls.length;
    }
    var url = urls.substring(pos + 1, pos2);
    if(url.charAt(url.length - 1) == '?') {
        url = url.substr(0, url.length - 1);
    }
    return url;
}

function _saveUrl() {
    var qs = location.search;
    qs = qs && qs.substr(1);
    var uri = location.pathname;
    if (parent._urlSysDict) {
        parent._urlSysDict[uri] = qs;
        return;
    }
    var urls = sessionStorage.urls || '';// top.window.name || '';
    var key = '\n' + uri + '?';
    var pos = urls.indexOf(key);
    if (pos == -1) {
        urls += key + qs;
    }
    else {
        var pos2 = urls.indexOf('\n', pos + key.length);
        if (pos2 == -1) {
            pos2 = urls.length;
        }
        urls = urls.substring(0, pos) + key + qs + urls.substr(pos2);
    }
    //top.window.name = urls;
    sessionStorage.urls = urls;
}
