//jsex
(function () {
    var proto = String.prototype;
    if (!proto.startsWith) {
        proto.startsWith = function (str) {
            return this.indexOf(str, 0) === 0;
        };
    }
    if (!proto.endsWith) {
        proto.endsWith = function (str) {
            return this.indexOf(str, this.length - str.length) !== -1;
        };
    }
    if (!proto.trim) {
        proto.trim = function () {
            return this.replace(/^\s+|\s+$/g, '');
        };
    }
    if (!Number.isInteger) {
        Number.isInteger = function (n) {
            return (n % 1) === 0;
        }
    }

})();

window.pub = {


    textToParagraphedHtml: function (text) {
        if (!text) {
            return '<p></p>';
        }
        var ps = text.split(/\n/);
        var html = '';
        for (var n = 0; n < ps.length; n++) {
            html += '<p>' + kendo.htmlEncode(ps[n]) + '</p>';
        }
        return html;
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

    trimEnd: function (str, c) {
        for (var n = str.length - 1; n >= 0; --n) {
            if (str[n] !== c) {
                break;
            }
        }
        return str.substr(0, n + 1);
    },

    parseParams: function (str) {
        if (str === undefined) {
            var qs = window.location.search;
            if (qs) {
                str = qs.substring(1);
            }
        }
        if (typeof str !== 'string') {
            return {};
        }

        var qPos = str.indexOf('?');
        if (qPos != -1) {
            str = str.substr(qPos + 1);
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

    isMobile: function (mobile) {
        return /^1[34578]\d{9}$/.test(mobile);
    },

    date: {
        today: function () {
            var date = new Date();
            date.setHours(0, 0, 0, 0);
            return date;
        },
        fromNumber: function (nDate) {
            return nDate > 99990000 ? new Date(nDate) : pub.date.fromIntDate(nDate);
        },
        addDay: function (date, diff) {
            if (typeof date === 'number') {
                date = pub.date.fromNumber(date)
            }
            var date1 = new Date(date.getFullYear(), date.getMonth(), date.getDate() + diff);
            return date1;
        },
        toIntDate: function (date) {
            return date.getFullYear() * 10000 + (date.getMonth() + 1) * 100 + date.getDate();
        },
        toIntDateTime: function (date) {
            if (!date) {
                date = new Date();
            }
            return pub.date.toIntDate(date) * 1000000 + pub.date.toIntTime(date);
        },
        toIntTime: function (date) {
            return date.getHours() * 10000 + date.getMinutes() * 100 + date.getSeconds();
        },
        toIntHM: function (time) {
            var times = time.split(":");
            var hour = times[0] * 60;
            var mins = Number(hour) + Number(times[1]);
            return mins;
        },
        fromIntDate: function (nDate) {
            if (!nDate || nDate == 0) {
                return null;
            }
            var y = Math.floor(nDate / 10000);
            var m = Math.floor((nDate - y * 10000) / 100);
            var d = nDate % 100;
            return new Date(y, m - 1, d);
        },
        formatLocalDate: function (date) {
            if (typeof(date) === 'number') {
                date = pub.date.fromNumber(date)
            }
            return date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日';
        },
        formatLocal2Date: function (date) {
            if (typeof(date) === 'number') {
                date = pub.date.fromNumber(date)
            }
            return (date.getMonth() + 1) + '月' + date.getDate() + '日';
        },
        formatLocal3Date: function (date) {
            if (typeof(date) === 'number') {
                date = pub.date.fromNumber(date)
            }
            return date.getDate() + '日';
        },
        formatDate: function (date) {
            if (typeof(date) === 'number') {
                date = pub.date.fromNumber(date)
            }
            var m = (date.getMonth() + 1);
            if (m < 10) {
                m = '0' + m;
            }
            var d = date.getDate();
            if (d < 10) {
                d = '0' + d;
            }
            return date.getFullYear() + '-' + m + '-' + d;
        },
        getWeekDay: function (date) {
            if (typeof(date) === 'number') {
                date = pub.date.fromNumber(date)
            }
            var weekDayNames = ['天', '一', '二', '三', '四', '五', '六'];
            return '星期' + weekDayNames[date.getDay()];
        },
        getWeekDay2: function (date) {
            if (typeof(date) === 'number') {
                date = pub.date.fromNumber(date)
            }
            var weekDayNames = ['日', '一', '二', '三', '四', '五', '六'];
            return '周' + weekDayNames[date.getDay()];
        },
        formatTime: function (time, full) {
            if (!time || !time.getTime()) {
                return '';
            }
            var n = time.getMinutes();
            var sTime = time.getHours() + ':' + (n < 10 ? '0' : '') + n;
            if (full) {
                var s = time.getSeconds();
                sTime += ':' + (s < 10 ? '0' : '') + s;
            }
            return sTime;
        },
        formatDateTime: function (time, full) {
            var date = new Date(time);
            return pub.date.formatDate(date) + ' ' + pub.date.formatTime(date, full);
        },
        isSameDay: function (date1, date2) {
            return date1.getFullYear() == date2.getFullYear() &&
                date1.getMonth() == date2.getMonth() &&
                date1.getDate() == date2.getDate();
        },
        formatHM: function (mins) {
            var h = Math.floor(mins / 60);
            //h = h < 10 ? '0' + h : h;
            var m = mins % 60;
            m = m < 10 ? '0' + m : m;
            return '' + h + ':' + m;
        },
        formatHM2: function (hhmm) {
            var h = Math.floor(hhmm / 100);
            h = h < 10 ? '0' + h : h;
            var m = hhmm % 100;
            m = m < 10 ? '0' + m : m;
            return '' + h + ':' + m;
        },
        parse: function (str) {
            if (!str) {
                return new Date(0);
            }
            var pos = str.indexOf(' ');
            var sDate, sTime;
            if (pos != -1) {
                sDate = str.substr(0, pos);
                sTime = str.substr(pos + 1);
            }
            else {
                sDate = str;
            }
            var dateParts = sDate.split('-');
            if (dateParts.length != 3) {
                return new Date(0);
            }
            var y = Number(dateParts[0]) || 0;
            var m = Number(dateParts[1]) || 1;
            var d = Number(dateParts[2]) || 0;
            var h = 0, n = 0, s = 0, ms = 0;
            if (sTime) {
                pos = sTime.indexOf('.');
                if (pos != -1) {
                    ms = Number(sTime.substr(pos + 1)) || 0;
                    sTime = sTime.substr(0, pos);
                }
                var timeParts = sTime.split(':');
                if (timeParts.length >= 2) {
                    h = Number(timeParts[0]) || 0;
                    n = Number(timeParts[1]) || 0;
                    s = Number(timeParts[2]) || 0;
                }
            }
            return new Date(y, m - 1, d, h, n, s, ms);
        },
        returnDatePrevMonth: function (dt) {// 获的上个月的
            if (typeof dt == 'undefined') {
                dt = (new Date());
            }
            var y = (dt.getMonth() == 0) ? (dt.getFullYear() - 1) : dt.getFullYear();
            var m = (dt.getMonth() == 0) ? 11 : dt.getMonth() - 1;
            var preM = this.getDayOfMonth(y, m);
            var d = (preM < dt.getDate()) ? preM : dt.getDate();
            return new Date(y, m, 1);
        },
        returnDateNextMonth: function (dt) {
            if (typeof dt == 'undefined') {
                dt = (new Date());
            }
            var y = (dt.getMonth() == 11) ? (dt.getFullYear() + 1) : dt.getFullYear();
            var m = (dt.getMonth() == 11) ? 0 : dt.getMonth() + 1;
            return new Date(y, m, 1);
        },

        getDayOfMonth: function (y, Mm) {
            if (typeof y == 'undefined') {
                y = (new Date()).getFullYear();
            }
            if (typeof Mm == 'undefined') {
                Mm = (new Date()).getMonth();
            }
            var Feb = (y % 4 == 0) ? 29 : 28;
            var aM = new Array(31, Feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
            return aM[Mm];
        }
    },

    ///
    getCookie: function (sName) {
        sName = sName.toLowerCase();
        var oCrumbles = document.cookie.split(';');
        for (var i = 0; i < oCrumbles.length; i++) {
            var oPair = oCrumbles[i].split('=');
            var sKey = decodeURIComponent(oPair[0].trim().toLowerCase());
            var sValue = oPair.length > 1 ? oPair[1] : '';
            if (sKey == sName)
                return decodeURIComponent(sValue);
        }
        return '';
    },

    setCookie: function (sName, sValue, path) {
        var oDate = new Date();
        oDate.setYear(oDate.getFullYear() + 1);
        var sCookie = encodeURIComponent(sName) + '=' + encodeURIComponent(sValue) +
            ';expires=' + oDate.toGMTString();
        sCookie += ';path=' + (path || '') + '/';
        document.cookie = sCookie;
    },

    removeCookie: function (sName) {
        pub.setCookie(sName, '');
    },

    parseUrl: function (url) {
        var loc = document.createElement("a");
        loc.href = url;
        return {
            domain: loc.hostname,
            path: loc.pathname
        };
    },

    /**
     * add by zhb
     */
    utf16toEntities: function (str) {
        var patt = /[\ud800-\udbff][\udc00-\udfff]/g; // 检测utf16字符正则
        str = str.replace(patt, function (char) {
            var H, L, code;
            if (char.length === 2) {
                H = char.charCodeAt(0); // 取出高位
                L = char.charCodeAt(1); // 取出低位
                code = (H - 0xD800) * 0x400 + 0x10000 + L - 0xDC00; // 转换算法
                return "&#" + code + ";";
            } else {
                return char;
            }
        });
        return str;
    },

    replaceUtf16toEmpty: function (str) {
        var patt = /[\ud800-\udbff][\udc00-\udfff]/g; // 检测utf16字符正则
        str = str.replace(patt, function (char) {
            if (char.length === 2) {
                return "";
            } else {
                return char;
            }
        });
        return str;
    },

    //
    sendAjaxRequest: function (id, callback) {
        $.ajax({
            url: backService.baseUrl + '/file/get.do?id=' + id,
            type: 'head',
            dataType: 'text/html',
            complete: function (xhr, data) {
                var length = xhr.getResponseHeader('Content-Length');
                callback(length);
            }
        });
    },

    formatHHMM: function (date) {
        if (typeof(date) === 'number') {
            date = pub.date.fromNumber(date)
        }
        else if (!date) {
            return '';
        }
        var hh = date.getHours().toString();
        var nn = date.getMinutes().toString();
        var ss = date.getSeconds().toString();
        var sTime = (hh[1] ? hh : '0' + hh[0]) + ':' +
            (nn[1] ? nn : '0' + nn);

        return sTime;
    },

    birthConvertAge: function (birthday) {
        // 获得今天的时间
        var date = new Date();
        var startDate = new Date(birthday);
        var newDate = date.getTime() - startDate.getTime();
        // 向下取整  例如 10岁 20天 会计算成 10岁
        // 如果要向上取整 计算成11岁，把floor替换成 ceil
        var age = Math.ceil(newDate / 1000 / 60 / 60 / 24 / 365);
        if (isNaN(age)) {
            age = "";
        }
        return age;
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
    }
};