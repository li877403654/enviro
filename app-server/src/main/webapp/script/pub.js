/**
 * Created with IntelliJ IDEA.
 * User: zzl
 * Date: 2015/6/23
 * To change this template use File | Settings | File Templates.
 */
window.pub = {

    postJson0: function (url, data, success, failure) {

        $.ajax(url, {
            type: 'POST',
            dataType: 'json',
            data: data,
            success: success,
            error: failure,
            timeout: failure,
            traditional: true
        });

    },

    jsonFailure: function (json) {
        var msg = json.msg || '未知错误';
        if (msg.length > 256 || !window.app) {
            alert(msg);
        }
        else {
            app.alert(msg);
        }
    },

    postJson: function (url, data, success, failure) {
        return pub.ajax('POST', url, data, success, failure);
    },

    postJSON: function (url, data, success, failure) {
        return pub.ajax('POST', url, data, success, failure);
    },

    getJSON: function (url, data, success, failure) {
        if(typeof data === 'function') {
            failure = success;
            success = data;
        }
        return pub.ajax('GET', url, data, success, failure);
    },

    ajax: function (method, url, data, success, failure) {
        if (!failure) {
            failure = pub.jsonFailure
        }
        var $loading = $('#loading');
        $loading.show();
        $.ajax(url, {
            type: method,
            dataType: 'json',
            data: data,
            success: function (json) {
                if (json.ok) {
                    success(json);
                }
                else {
                    failure(json);
                }
            },
            error: function (xhr, status, err) {
                failure({
                    ok: false,
                    msg: status + ': ' + err
                });
            },
            traditional: true,
            complete: function () {
                $loading.hide();
            }
        });
    },

    ajaxFailure: function () {
        alert('failed..');
    },

    getParam: function (name) {
        return pub.parseParams()[name];
    },

    parseParams: function (str) {
        if (str === undefined) {
            str = window.location.search;
        }
        if (typeof str !== 'string') {
            return {};
        }

        str = str.trim().replace(/^(\?|#|&)/, '');

        if (!str) {
            return {};
        }

        return str.split('&').reduce(function (ret, param) {
            var parts = param.replace(/\+/g, ' ').split('=');
            var key = parts[0];
            var val = parts[1];

            key = decodeURIComponent(key);
            // missing `=` should be `null`:
            // http://w3.org/TR/2012/WD-url-20120524/#collect-url-parameters
            val = val === undefined ? null : decodeURIComponent(val);

            if (!ret.hasOwnProperty(key)) {
                ret[key] = val;
            } else if (Array.isArray(ret[key])) {
                ret[key].push(val);
            } else {
                ret[key] = [ret[key], val];
            }

            return ret;
        }, {});
    },

    parseParamsToArray: function (qs) {
        var result = [];
        var sParams = qs.split('&');
        for (var n = 0; n < sParams.length; n++) {
            var sParam = sParams[n];
            var parts = sParam.replace(/\+/g, ' ').split('=');
            var name = parts[0];
            if (!name) {
                continue;
            }
            var val = parts[1];

            name = decodeURIComponent(name);
            val = val === undefined ? null : decodeURIComponent(val);
            result.push({
                name: name,
                value: val
            });
        }
        return result;
    },

    setParams: function (url, newParams) {
        var hash = undefined;
        var hashPos = url.lastIndexOf('#');
        if (hashPos != -1) {
            hash = url.substr(hashPos + 1);
            url = url.substr(0, hashPos);
        }

        var qPos = url.indexOf('?');
        var qs = '';
        if (qPos == -1) {
            url += '?';
        }
        else {
            qs = url.substr(qPos + 1);
            url = url.substr(0, qPos + 1);
        }
        //var params = pub.parseParams(qs);
        //$.extend(params, newParams);
        //url += $.param(params);
        var params = pub.parseParamsToArray(qs);
        var n;
        for (var name in newParams) {
            if (!newParams.hasOwnProperty(name)) {
                continue;
            }
            var value = newParams[name];
            var replaced = false;
            var removed = false;
            //for(n = 0; n < params.length; n++) {
            for (n = params.length - 1; n >= 0; --n) {
                if (params[n].name == name) {
                    if (value === null) {
                        params.splice(n, 1);
                        removed = true;
                    }
                    else if (replaced) {
                        //unshift?
                    }
                    else {
                        params[n].value = value;
                        replaced = true;
                    }
                }
            }
            if (!replaced && !removed && value !== null) {
                params.push({
                    name: name,
                    value: value
                });
            }
        }
        var newQs = '';
        for (n = 0; n < params.length; n++) {
            if (n > 0) {
                newQs += '&';
            }
            var param = params[n];
            var sParam = param.name + '=' + encodeURIComponent(param.value);
            newQs += sParam;
        }
        url += newQs;
        if (hash !== undefined) {
            url += '#' + hash;
        }
        return url;
    },

    formatTime: function (date) {
        if (typeof(date) == 'number') {
            date = new Date(date);
        }
        var yyyy = date.getFullYear().toString();
        var mm = (date.getMonth() + 1).toString(); // getMonth() is zero-based
        var dd = date.getDate().toString();
        var sDate = yyyy + '-' + (mm[1] ? mm : '0' + mm[0]) + '-' +
            (dd[1] ? dd : '0' + dd[0]); // padding

        var hh = date.getHours().toString();
        var nn = date.getMinutes().toString();
        var ss = date.getSeconds().toString();
        var sTime = (hh[1] ? hh : '0' + hh[0]) + ':' +
            (nn[1] ? nn : '0' + nn) + ':' + (ss[1] ? ss : '0' + ss);

        return sDate + ' ' + sTime;
    },

    formatDate: function (date) {
        if (typeof(date) == 'number') {
            date = new Date(date);
        }
        var yyyy = date.getFullYear().toString();
        var mm = (date.getMonth() + 1).toString(); // getMonth() is zero-based
        var dd = date.getDate().toString();

        return yyyy + '-' + (mm[1] ? mm : '0' + mm[0]) + '-' +
            (dd[1] ? dd : '0' + dd[0]);
    },

    fillCombo: function (el, items, nullItem) {
        var fragment = document.createDocumentFragment();
        var opt;
        if (nullItem !== undefined) {
            opt = document.createElement('option');
            if (typeof(nullItem) === 'object') {
                opt.value = nullItem.id;
                opt.innerHTML = nullItem.text;
            }
            else {
                opt.value = '';
                opt.innerHTML = nullItem;
            }
            fragment.appendChild(opt);
        }
        for (var n = 0; n < items.length; n++) {
            var item = items[n];
            opt = document.createElement('option');
            if (typeof(item) === 'string') {
                opt.innerHTML = item;
                // opt.value = item;
            }
            else {
                opt.innerHTML = item.text;
                opt.value = item.id;
            }
            fragment.appendChild(opt);
        }
        el.innerHTML = '';
        el.appendChild(fragment);
    },

    lastOf: function (arr) {
        if (!arr || !arr.length) {
            return undefined;
        }
        return arr[arr.length - 1];
    },

    firstOf: function (arr) {
        if (!arr || !arr.length) {
            return undefined;
        }
        return arr[0];
    },

    trimRight: function (str, chars) {
        var pos = -1;
        for (var n = str.length - 1; n >= 0; --n) {
            if (chars.indexOf(str.charAt(n)) == -1) {
                pos = n;
                break;
            }
        }
        return str.substr(0, pos + 1);
    },

    trimLeft: function (str, chars) {
        for (var n = 0; n < str.length; n++) {
            if (chars.indexOf(str.charAt(n)) == -1) {
                return str.substr(n);
            }
        }
        return str;
    },

    trim: function (str, chars) {
        str = pub.trimLeft(str, chars);
        str = pub.trimRight(str, chars);
        return str;
    },

    formatDouble: function (value, fractionDigits) {
        fractionDigits = fractionDigits || 2;
        var multiplier = Math.pow(10, fractionDigits);
        value = Math.round(value * multiplier) / multiplier;
        return String(value);
    },

    toFixed: function (value, digitsAfterDot) {
        var pow = Math.pow(10, digitsAfterDot);
        return +(Math.round(value * pow) / pow);
    },

    na: function () {
        //x
    },

    showDialog: function (msg, callback, confirm) {
        if (!callback) {
            callback = pub.na;
        }
        var html = '<div class="dialog-layer" style="display: none;">' +
            '    <div class="dialog">' +
            '        <div class="header">' +
            '            <div class="title">提示</div>' +
            '            <a href="javascript:;" class="btn-close">' +
            '               <span class="aui-icon aui-icon-small aui-iconfont-close-dialog">Close</span>' +
            '            </a>' +
            '        </div>' +
            '        <div class="content">fdsafs fdsafd</div>' +
            '        <div class="footer">' +
            '            <button class="aui-button aui-button-primary btn-ok">确定</button>' +
            '            <button class="aui-button btn-cancel">取消</button>' +
            '        </div>' +
            '    </div>' +
            '</div>';
        var $layer = $('.dialog-layer');
        if (!$layer.length) {
            $(document.body).append(html);
            $layer = $('.dialog-layer');
        }
        var $dialog = $layer.find('.dialog');
        $dialog.find('.content').text(msg);
        $dialog.find('.btn-close').off('click').on('click', function () {
            $layer.hide();
            callback(0);
        });
        $dialog.find('.btn-ok').off('click').on('click', function () {
            $layer.hide();
            callback(1);
        });
        var $btnCancel = $dialog.find('.btn-cancel');
        $btnCancel.off('click').on('click', function () {
            $layer.hide();
            callback(2);
        });
        if (!confirm) {
            $btnCancel.hide();
        }
        $dialog.css('top', -1024);
        $layer.show();
        var layerW = $layer.width();
        var layerH = $layer.height();
        var w = $dialog.outerWidth();
        var h = $dialog.outerHeight();
        $dialog.css({
            display: 'none',
            left: (layerW - w) / 2,
            top: (layerH - h) / 2 * .64
        });
        $dialog.fadeIn('fast');
        $dialog.find('button:first').focus();
    },

    alert: function (msg, callback) {
        pub.showDialog(msg, callback, false);
    },

    confirm: function (msg, callback) {
        pub.showDialog(msg, function (buttonIndex) {
            if (buttonIndex == 1) {
                callback();
            }
        }, true);
    },

    remove: function (array, item) {
        var index = array.indexOf(item);
        if (index !== -1) {
            array.splice(index, 1);
        }
    },

    replace: function (array, item, itemNew) {
        var index = array.indexOf(item);
        if (index !== -1) {
            array[index] = itemNew;
        }
    },

    buildFullUrl: (function () {
        var a;

        return function (url) {
            if (!a) {
                a = document.createElement('a');
            }
            a.href = url;

            return a.href;
        };
    })(),

    translate: function (idTexts, value) {
        if (!idTexts || !idTexts.length) {
            return '';
        }
        for (var n = 0; n < idTexts.length; n++) {
            if (idTexts[n].id == value) {
                return idTexts[n].text;
            }
        }
        return '';
    },

    buildSelect: function (items) {
        var select = '<select>';
        for (var n = 0; n < items.length; n++) {
            var item = items[n];
            if (typeof(item) === 'object') {
                select += '<option value="' + item.id + '">' + item.text + '</option>';
            }
            else {
                select += '<option>' + item + '</option>';
            }
        }
        select += '</select>';
        return select;
    },

    byteLength: function (str) {
        if (!str) {
            return 0;
        }
        var result = 0;
        for (var n = 0; n < str.length; n++) {
            var c = str.charCodeAt(n);
            if (c > 0 && c < 255) {
                result += 1;
            }
            else {
                result += 2;
            }
        }
        return result;
    },

    fillForm: function ($form, data) {
        $form = $($form);
        for (var k in data) {
            if (!data.hasOwnProperty(k)) {
                continue;
            }
            $form.find('[name="' + k + '"]').val(data[k]);
        }
    },

    accMul: function (arg1, arg2) {
        var m = 0;
        s1 = arg1.toString();
        s2 = arg2.toString();
        try {
            m += s1.split(".")[1].length
        } catch (e) {
        }
        try {
            m += s2.split(".")[1].length
        } catch (e) {
        }
        return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
    },
    /**
     * Mr. xyb
     * 限制价格输入框
     *
     * @param obj
     */
    checkPrice: function (obj) {
        obj.value = obj.value.replace(/[^\d.]/g, ""); //清除"数字"和"."以外的字符
        obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字
        obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个, 清除多余的
        obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
        if (obj.value > 100000000) {
            app.showToast("您输入的价格过大，请重新输入!");
            obj.value = 1;
        }
    },
    /**
     * Mr. xyb
     * 限制整数输入框
     *
     * @param obj
     */
    checkNumber: function (obj) {
        if (!/^[0-9]\d*$/.test(obj.value)) {
            app.showToast('只能整数');
            obj.value = '';
        }
    }

};