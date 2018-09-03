(function () {

    var pub = {};

    pub.getJson = pub.getJSON = function(url, data, success, failure) {
        pub.ajax('GET', url, data, success, failure);
    };

    pub.postJson = pub.postJSON = function(url, data, success, failure) {
        pub.ajax('POST', url, data, success, failure);
    };

    pub.ajaxFailure = function(result) {
        alert(result.msg);
    };

    pub.ajax = function(method, url, data, success, failure) {
        failure = failure || pub.ajaxFailure;
        var $loading = $('#ajax-loading');
        $loading.show();
        $.ajax(url, {
            type: method,
            dataType: 'json',
            data: data,
            success: function(json) {
                if(json.ok) {
                    success(json);
                }
                else {
                    failure(json);
                }
            },
            error: function(xhr, status, err) {
                failure({
                    ok: false,
                    msg: status + ': ' + err
                });
            },
            traditional: true,
            complete: function() {
                $loading.hide();
            }
        });
    };

    pub.byteLength = function (str) {
        if(!str) {
            return 0;
        }
        var result = 0;
        for(var n = 0; n < str.length; n++) {
            var c = str.charCodeAt(n);
            if(c > 0 && c < 255) {
                result += 1;
            }
            else {
                result += 2;
            }
        }
        return result;
    };

    pub.buildFullUrl = function (url) {
        var link = $(document).data('_fullUrlLink');
        if(!link) {
            link = document.createElement('a');
            $(document).data('_fullUrlLink', link);
        }
        link.href = url;
        return link.href;
    };

    pub.fillCombo = function(el, items, nullItem, textProperty) {
        el = $(el)[0];
        textProperty = textProperty || 'text';
        var fragment = document.createDocumentFragment();
        var opt;
        if(nullItem !== undefined) {
            opt = document.createElement('option');
            if(typeof(nullItem) === 'object') {
                opt.value = nullItem.id;
                opt.innerHTML = nullItem[textProperty];
            }
            else {
                opt.value = '';
                opt.innerHTML = nullItem;
            }
            fragment.appendChild(opt);
        }
        for(var n = 0; n < items.length; n++) {
            var item = items[n];
            opt = document.createElement('option');
            opt.innerHTML = item[textProperty];
            opt.value = item.id;
            fragment.appendChild(opt);
        }
        el.innerHTML = '';
        el.appendChild(fragment);
    };

    //
    window.pub = pub;

})();