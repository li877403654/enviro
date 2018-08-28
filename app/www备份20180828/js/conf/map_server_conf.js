/**
 * js功能描述: <br>
 * ()
 *
 * @Author: Mr. xyb
 * @Date: 2018/8/14 17:30
 * @version: 1.0.0
 */
(function () {
    var mapConf = {};

    var url = 'http://172.16.104.47:6080';

    mapConf.urls = {
        baseMapUrl: url + '/arcgis/rest/services/fs170615/MapServer'
    }
    mapConf.params = {
        obdMinLevel: 5
    }


    window.mapConf = mapConf;
})();