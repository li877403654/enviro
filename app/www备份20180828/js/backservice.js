(function () {

    var backService = {
        requests: [],
        ajaxTimeout: 24000,
        tempAjaxTimeout: 0
    };

    backService.baseUrl = 'http://172.16.88.49:8080/app-server';

    backService.failureHandler = function (json) {
        if (json.status === 'abort') {
            //silent
            return;
        }
        else if (json.status === 'timeout') {
            app.alert('请求超时');
            return;
        }
        app.alert(json.msg);
        //alert(json.msg);
    };

    backService.get = function (url, data, callback, failure) {
		console.log(url);
        backService.ajax('GET', url, data, callback, failure);
    };

    backService.post = function (url, data, callback, failure) {
        backService.ajax('POST', url, data, callback, failure);
    };



    backService.requestToken = function (callback) {
        var lastLogin = JSON.parse(localStorage.lastLogin || 'null');
        if (!lastLogin) {
            alert('bad credential');
            return;
        }
        var url = backService.baseUrl + '/owner/request_token.json';
        $.ajax(url, {
            data: {
                account: lastLogin.id,
                password: lastLogin.pass
            },
            dataType: 'json',
            traditional: true,
            timeout: backService.ajaxTimeout,
            success: function (json) {
                backService.token = json.data;
                callback();
            },
            error: function (xhr, status, err) {
                alert(status + ': ' + err);
            }
        });
    };

    backService.ajax = function (method, url, data, callback, failure) {
        var ajaxArguments = arguments;

        if (typeof(data) === 'function') {
            failure = callback;
            callback = data;
        }
        if (failure === undefined) {
            failure = backService.failureHandler
        }
        if (url.indexOf('http:') === -1) {
            url = backService.baseUrl + url;
        }
        var completed = false;
        var showLoadingDelay = 500;
        if (backService.immediateLoading) {
            showLoadingDelay = 0;
            backService.immediateLoading = false;
        }
        var loadingCookie;
        if (backService.noLoading) {
            backService.noLoading = false;
        }
        else {
            setTimeout(function () {
                if (completed) {
                    return;
                }
                loadingCookie = app.showLoading();
            }, showLoadingDelay);
        }
        //++backService.loading;
        var headers = {};
        if (session.user && backService.token) {
            var ownerToken = backService.token + Number(session.user.id).toString(16);
            //headers['x-owner-token'] = ownerToken;
            headers['Accept'] = 'json-' + ownerToken;
        }

        var timeout = backService.ajaxTimeout;
        if (backService.tempAjaxTimeout) {
            timeout = backService.tempAjaxTimeout;
            backService.tempAjaxTimeout = 0;
        }
        var request = $.ajax(url, {
            method: method,
            data: data,
            dataType: 'json',
            traditional: true,
            timeout: timeout,
            headers: headers,
            xhrFields: {
                withCredentials: true
            },
            success: function (json) {
                completed = true;
                if (loadingCookie) {
                    app.hideLoading(loadingCookie);
                }
                if (json.ok) {
                    try {
                        callback && callback(json);
                    }
                    catch (e) {
                        if (inBrowser) {
                            throw e;
                        }
                        var loc = '[Ajax Callback Error]';
                        if (e.fileName) {
                            loc += '[' + e.fileName;
                            if (e.lineNumber) {
                                loc += ':' + e.lineNumber;
                            }
                            loc += ']';
                        }
                        console.log(loc + e.message);
                        if (app.debug && e.stack) {
                            console.log('>> ' + e.stack);
                        }
                    }
                }
                else {
                    if (json.error && json.msg == 'ERR::BAD_TOKEN' &&
                        !backService.ajax.inRequestTokenCallback) {

                        backService.requestToken(function () {
                            backService.ajax.inRequestTokenCallback = true;
                            backService.ajax.apply(this, ajaxArguments);
                        });
                    }
                    else {
                        failure(json);
                    }
                }
            },
            error: function (xhr, status, err) {
                failure({
                    ok: false,
                    status: status,
                    msg: status + ': ' + err
                });
            },
            complete: function (xhr, statusText) {
                backService.ajax.inRequestTokenCallback = false;
                //--backService.loading;
                for (var n = 0; n < backService.requests.length; n++) {
                    if (backService.requests[n] === request) {
                        backService.requests.splice(n, 1);
                        break;
                    }
                }
                if (!completed) {
                    completed = true;
                    if (loadingCookie) {
                        app.hideLoading(loadingCookie);
                    }
                }
            }
        });
        backService.requests.push(request);
    };

    backService.getFileUrl = function (id, fallback) {
        if (!id) {
            return fallback || 'img/nopic.gif';
        }
        return backService.baseUrl + '/file/get.do?id=' + id;
    };

    //
    backService.getIndexInfo = function (callback) {
        backService.get('/index/info.json', callback);
    };

    //
    var eventSource;
    backService.listenEvents = function() {
        if(eventSource) {
            try {
                eventSource.close();
            }
            catch (e) {
                console.log(e);
            }
        }
        var url = backService.baseUrl + '/event/listener?userId=' + session.user.id;
        eventSource = new EventSource(url);
        // eventSource.addEventListener('error', function(e) {
        //     if (e.readyState === EventSource.CLOSED) {
        //         setTimeout(function () {
        //             backService.listenEvents();
        //         }, 1000)
        //     }
        // }, false);
        eventSource.addEventListener('message', function(e) {
            var message = e.data;
            var pos = message.indexOf(':');
            var eventName = message.substr(0, pos);
            $(document).trigger('_event_' + eventName);
        }, false);

    };


    window.backService = backService;

})();
