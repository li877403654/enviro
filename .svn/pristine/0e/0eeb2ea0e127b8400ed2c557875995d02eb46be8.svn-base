layui.config({
    base: "js/"
}).use(['form', 'element', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        element = layui.element,
        $ = layui.jquery;

});
require(["esri/map", "esri/layers/ArcGISTiledMapServiceLayer",
    "esri/layers/GraphicsLayer", "esri/layers/FeatureLayer", "esri/dijit/Popup", "dijit/TooltipDialog", "esri/symbols/SimpleMarkerSymbol",
    "esri/symbols/SimpleLineSymbol", "esri/symbols/SimpleFillSymbol", "esri/symbols/TextSymbol", "esri/renderers/ClassBreaksRenderer",
    "esri/renderers/SimpleRenderer", "esri/InfoTemplate", "esri/SnappingManager", "esri/sniff",
    "esri/Color", "esri/graphic", "esri/tasks/query",
    "esri/tasks/QueryTask", "esri/geometry/geodesicUtils", "esri/units",
    "esri/geometry/Polyline", "esri/toolbars/draw", "esri/toolbars/edit",
    "dojo/keys", "dojo/on", "dojo/request", "dojo/_base/lang", "dojo/dom", "dojo/dom-attr", "dojo/dom-construct", "dojo/string",
    "esri/layers/LabelClass", "esri/geometry/Point", "dojo/domReady!"], function (Map, ArcGISTiledMapServiceLayer, GraphicsLayer, FeatureLayer, Popup, TooltipDialog, SimpleMarkerSymbol,
                                                                                  SimpleLineSymbol, SimpleFillSymbol, TextSymbol, ClassBreaksRenderer, SimpleRenderer, InfoTemplate, SnappingManager,
                                                                                  has, Color, Graphic, Query, QueryTask, geodesicUtils, Units, Polyline, Draw, Edit, keys, on, request, lang, dom,
                                                                                  domAttr, domConstruct, string, LabelClass, Point) {
    //定义地图
    var map = new Map("index_map", {
        showLabels: true,
        logo: false,
        sliderStyle: "large",
        center: [113.147, 23.036],
        zoom: mapConf.params.obdMinLevel
    });
    var tiled = new ArcGISTiledMapServiceLayer(mapConf.urls.baseMapUrl);
    map.addLayer(tiled);
});