<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="${home}/style/enviromentCredit/css/flex.css" rel="stylesheet"/>
    <link href="${home}/style/enviromentCredit/css/style.css" rel="stylesheet"/>
    <script src="${home}/script/lib/jquery.js"></script>
    <script type="text/javascript" src="${home}/script/environmentCredit/js/vue.min.js"></script>
    <script type="text/javascript" src="${home}/script/environmentCredit/js/echarts.js"></script>
    <script type="text/javascript" src="${home}/script/environmentCredit/js/echarts-wordcloud.js"></script>
    <jsp:include page="/common/scripts.jsp"/>
    <style>
        body,html{
            padding: 0;
            margin: 0;
            width: 100%;
            height: 100%;
            box-sizing: border-box;
            overflow: auto;
        }

        h1, h3, h2, h4{
            padding: 0;
            margin: 0;
            font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
        }

        h4{
            font-size: 18px;
            font-weight: 700;
        }

        span.ct-title h4{
            display: inline-block;
        }

        .container{
            margin: 40px 20px;
            font-family: 'Arial Normal', 'Arial';
            font-size: 14px;
            font-weight: 400;
            color: #333333;
        }

        .title{
            text-align: center;
        }

        .s-box-title {
            margin: 5px 0;
        }

        .s-box-num, .font-bold {
            font-weight: 700;
        }

        .s-box-desc, .margin10 {
            margin-top: 10px;
        }

        .box-title-txt{
            font-size: 16px;
            font-weight: 700;
        }

        .title-desc{
            text-align: right;
        }

        .ct-left, .ct-right{
            width: 25%;
            float: left;
        }

        .ct-middle{
            width: 50%;
            min-height: 100px;
            float: left;
        }

        .ct-left-m, .ct-right-m{
            margin: 15px 0;
            padding: 10px 20px;
            background-color: rgba(242, 242, 242, 1);
            border: 1px solid rgba(121, 121, 121, 1);
        }

        .ct-right-m{
            background: #fff;
        }

        .ct-left-bottom > div{
            margin-top: 10px;
        }

        .ct-left-bottom-top{
            display: flex;
            justify-content: space-between;
        }

        .s-box{
            display: inline-block;
            padding: 10px;
            width: 30%;
            border: 1px solid rgba(121, 121, 121, 1);
            box-sizing: border-box;
            text-align: center;
            background: #fff;
        }

        .s-box-2w{
            width: 45%;
        }

        .b-box{
            padding: 10px;
            background: #fff;
            border: 1px solid;
        }

        .b-box-contents{
            position: relative;
            padding: 10px;
        }

        .circle {
            border: 1px solid;
            display: inline-block;
            border-radius: 100%;
            text-align: center;
            padding: 10px 20px;
        }

        .content-bottom:after{
            content: '.';
            display: block;
            visibility: hidden;
            height: 0;
            clear: both;
        }

        .air-quality-map, .pollutant-map{
            border-bottom: 1px dashed #333333;
            height: 200px;
            width: 100%
        }

        .pollutant-map{
            border-bottom: none;
        }

        .reachingStandard{
            margin-top: 10px;
            font-weight: 700;
            font-style: normal;
            font-size: 16px;
            color: #339933;
        }

        .factor{
            position: absolute;
            font-size: 26px;
            width: 100%;
            text-align: center;
            color: #999999;
        }

        .factor1{
            top: 50%;
            width: 100%;
            left: 50%;
            transform: translate(-50%,-50%);
        }

        .factor2{
            top: 16%;
            left: 16%;
            transform: scale(.6);
        }

        .factor3{
            top: 16%;
            left: -13%;
            transform: scale(.5);
        }

        .factor4{
            top: 59%;
            left: 2%;
            transform: scale(.6);
        }

        .fix-height{
            height: 120px;
        }


    </style>
</head>
<body>
<%--<div class="main">
   &lt;%&ndash; <div class="main_bg">

            <button type="button"  id="button1"
                    style="font-size: 20px; margin-top: 20px ;margin-left: 70px">点击
            </button>

    </div>&ndash;%&gt;

</div>--%>

<div class="container">
    <div class="main-contents">
        <div class="content-top">
            <div class="title"><h1>设计院智慧环保一张图</h1></div>
            <div class="title-desc">这个小时的空气质量表</div>
        </div>
        <div class="content-bottom">
            <!--左侧区域开始-->
            <div class="ct-left">
                <!--污染源监管程度-->
                <div class="ct-left-m">
                    <div class="ct-left-top"><span class="ct-title"><h4>污染源监管程度</h4></span></div>
                    <div class="ct-left-bottom">
                        <div class="ct-left-bottom-top">
                            <div class="s-box">
                                <div class="s-box-title box-title-txt">国控</div>
                                <div class="s-box-num box-title-txt">137</div>
                                <div class="s-box-unit">(家)</div>
                            </div>
                            <div class="s-box">
                                <div class="s-box-title box-title-txt">国控</div>
                                <div class="s-box-num box-title-txt">137</div>
                                <div class="s-box-unit">(家)</div>
                            </div>
                            <div class="s-box">
                                <div class="s-box-title box-title-txt">国控</div>
                                <div class="s-box-num box-title-txt">137</div>
                                <div class="s-box-unit">(家)</div>
                            </div>
                        </div>
                        <div class="ct-left-bottom-bottom">
                            <div class="b-box">
                                <div class="b-box-title">国控企业类型</div>
                                <div class="b-box-contents">
                                    <div class="s-circle circle">
                                        <div class="s-box-title">危险废物</div>
                                        <div class="s-box-num">11</div>
                                        <div class="s-box-unit">(家)</div>
                                    </div>
                                    <div class="b-circle circle">
                                        <div class="s-box-title">污水处理厂</div>
                                        <div class="s-box-num">42</div>
                                        <div class="s-box-unit">(家)</div>
                                    </div>
                                    <div class="m-circle circle">
                                        <div class="s-box-title">废水</div>
                                        <div class="s-box-num">12</div>
                                        <div class="s-box-unit">(家)</div>
                                    </div>
                                    <div class="m-circle circle">
                                        <div class="s-box-title">重金属</div>
                                        <div class="s-box-num">63</div>
                                        <div class="s-box-unit">(家)</div>
                                    </div>
                                    <div class="m-circle circle">
                                        <div class="s-box-title">废气</div>
                                        <div class="s-box-num">9</div>
                                        <div class="s-box-unit">(家)</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--污染排放总量-->
                <div class="ct-left-m">
                    <div class="ct-left-top"><div class="ct-title"><h4>污染排放总量</h4></div><div class="title-desc">注：累计2018年1月-7月</div></div>
                    <div class="ct-left-bottom">
                        <div class="ct-left-bottom-top">
                            <div class="s-box">
                                <div class="s-box-title box-title-txt">废水</div>
                                <div class="s-box-num box-title-txt">1366,789</div>
                                <div class="s-box-unit">(万吨)</div>
                                <div class="s-box-desc">同比<span class="rise">&uarr;1,110</span></div>
                            </div>
                            <div class="s-box">
                                <div class="s-box-title box-title-txt">废气</div>
                                <div class="s-box-num box-title-txt">1366,789</div>
                                <div class="s-box-unit ">(万吨)</div>
                                <div class="s-box-desc">同比<span class="rise">&uarr;1,110</span></div>
                            </div>
                            <div class="s-box">
                                <div class="s-box-title box-title-txt">固废</div>
                                <div class="s-box-num box-title-txt">1366,789</div>
                                <div class="s-box-unit">(万吨)</div>
                                <div class="s-box-desc">同比<span class="rise">&uarr;1,110</span></div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--环保税征收情况-->
                <div class="ct-left-m">
                    <div class="ct-left-top"><div class="ct-title"><h4>环保税征收情况</h4></div><div class="title-desc">注：累计2018年1月-7月</div></div>
                    <div class="ct-left-bottom">
                        <div class="ct-left-bottom-top">
                            <div class="s-box s-box-2w">
                                <div class="s-box-title box-title-txt">企业数</div>
                                <div class="s-box-num box-title-txt">137</div>
                                <div class="s-box-unit">(家)</div>
                            </div>
                            <div class="s-box s-box-2w">
                                <div class="s-box-title box-title-txt">总税额</div>
                                <div class="s-box-num box-title-txt">778899</div>
                                <div class="s-box-unit ">(万元)</div>
                            </div>
                        </div>
                        <div class="ct-left-bottom-bottom">
                            <div id="diagrammatize-revenue" style="height: 260px;width: 100%"></div>
                        </div>
                    </div>
                </div>

            </div>
            <!--左侧区域结束-->

            <!--中间区域开始-->
            <div class="ct-middle">

            </div>
            <!--中间区域结束-->

            <!--右侧区域开始-->
            <div class="ct-right">
                <div class="ct-right-m">
                    <!--空气质量优良天数占比开始-->
                    <div class="ct-right-m-i">
                        <div class="ct-left-top"><span class="ct-title"><h4>空气质量优良天数占比</h4></span><span class="font-bold">(2018年1月1日-8月10日)</span></div>
                        <div class="ct-left-bottom">
                            <div class="ct-left-bottom-top">
                                <div id="air-quality-map" class="air-quality-map">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--空气质量优良天数占比结束-->

                    <!--首要污染物天数占比开始-->
                    <div class="ct-right-m-i">
                        <div class="ct-left-top margin10"><span class="ct-title"><h4>首要污染物天数占比</h4></span><span class="font-bold">(2018年1月1日-8月10日)</span></div>
                        <div class="ct-left-bottom">
                            <div class="ct-left-bottom-top">
                                <div id="pollutant-map" class="pollutant-map">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--首要污染物天数占比结束-->

                </div>

                <!--水质检测超标数开始-->
                <div class="ct-right-m">
                    <div class="ct-left-top"><span class="ct-title"><h4>水质检测超标数</h4></span><span class="font-bold">(2018年7月)</span></div>
                    <div class="ct-left-bottom">
                        <div class="ct-left-bottom-top">
                            <div class="s-box">
                                <div class="s-box-title box-title-txt">江河水</div>
                                <div class="s-box-num box-title-txt">22个</div>
                                <div class="s-box-unit">占比20%</div>
                            </div>
                            <div class="s-box">
                                <div class="s-box-title box-title-txt">交界断面</div>
                                <div class="s-box-num box-title-txt">3个</div>
                                <div class="s-box-unit">占比20%</div>
                            </div>
                            <div class="s-box">
                                <div class="s-box-title box-title-txt">饮用水源地</div>
                                <div class="reachingStandard">全部达标</div>
                            </div>
                        </div>
                        <div class="ct-left-bottom-bottom">
                            <div class="b-box">
                                <div class="b-box-title">江河水主要超标因子</div>
                                <div id="factor" class="b-box-contents fix-height">
                                    <!--<div class="factor factor3">生化需氧量</div>-->
                                    <!--<div class="factor factor2">溶解氧</div>-->
                                    <!--<div class="factor factor1">化学需氧量</div>-->
                                    <!--<div class="factor factor4">氨氮</div>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--水质检测超标数结束-->

            </div>
            <!--右侧区域结束-->

        </div>
    </div>
</div>


</body>

</html>

<script>

    var vm=new Vue({
      el:"#right1",
        data:{
          temps:"123"
        },
        methods:{
          right1:function () {
              $.ajax({
                  url: "init.json",
                  async: false,
                  dataType: "json",
                  data: {param:""},
                  success: function (result) {
                      vm.temps=result.data;
                      console.log(vm.temps);//
                      var right1Chart = echarts.init(document.getElementById('right-box-1'));
                      var option = {
                          tooltip: {
                              trigger: 'item',
                              formatter: "{a} <br/>{b}: {c} ({d}%)"
                          },

                          series: [
                              {
                                  name:'空气质量',
                                  type:'pie',
                                  radius: ['50%', '70%'],
                                  avoidLabelOverlap: false,
                                  label: {
                                      normal: {
                                          show: false,
                                          position: 'center'
                                      },
                                      emphasis: {
                                          show: true,
                                          textStyle: {
                                              fontSize: '30',
                                              fontWeight: 'bold'
                                          }
                                      }
                                  },
                                  labelLine: {
                                      normal: {
                                          show: false
                                      }
                                  },
                                  data:[
                                      {value:33, name:'PM2.5'},
                                      {value:200, name:'SO2'},


                                  ]
                              }
                          ]
                      };
                      right1Chart.setOption(option);

                  }

              })
          }
        }

    });
    vm.right1();


    $(function () {
        getRevenue('diagrammatize-revenue');
        getAirQuality('air-quality-map');
        getPollutant('pollutant-map');
        getFactor('factor');
    })

    function getRevenue(obj) {
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: ['污水处理厂','废水','重金属','危险废物','废气']
            },
            series: [
                {
                    name: '2018年',
                    type: 'bar',
                    data: [1500, 1100, 900, 800, 600]
                }
            ]
        };
        setOptions(option, obj);
    }

    //空气质量优良天数占比,饼图显示
    function getAirQuality(obj) {
        var option= {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
            },
            series: [
                {
                    name:'访问来源',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: true,
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:[
                        {value:189, name:'优良'},
                        {value:16, name:'差'},
                    ]
                }
            ]
        };
        setOptions(option, obj);
    }

    function getPollutant(obj) {
        var option = {
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'right',
                data: ['O3','PM2.5','NO2','PM10']
            },
            series : [
                {
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:335, name:'O3'},
                        {value:310, name:'PM2.5'},
                        {value:234, name:'NO2'},
                        {value:135, name:'PM10'},
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        setOptions(option, obj);
    }

    //主要超标因子,字符云式显示
    function getFactor(obj) {
        var option = {
            tooltip: {},
            series: [{
                type: 'wordCloud',
                shape: 'square',
                left: 'center',
                top: 'center',
                width: '70%',
                height: '80%',
                right: null,
                bottom: null,
                sizeRange: [12, 28],
                rotationRange: [0, 0],
                rotationStep: 45,
                gridSize: 8,
                drawOutOfBound: true,

                textStyle: {
                    normal: {
                        fontFamily: 'sans-serif',
                        fontWeight: 'bold',
                        color: function () {
                            return 'rgb(' + [
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160)
                            ].join(',') + ')';
                        }
                    },
                    emphasis: {
                        shadowBlur: 10,
                        shadowColor: '#333'
                    }
                },

                data: [
                    {name: '化学需氧量', value: 122,},
                    {name: '生化需氧量', value: 112,},
                    {name: '溶解氧', value: 333,},
                    {name: '氨氮', value: 444,},
                ]
            }]
        };
        setOptions(option, obj);
    }

    function setOptions(option, obj) {
        var dom = document.getElementById(obj);
        var myChart = echarts.init(dom);
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }



    /*$(function () {
       /!* $("#button1").click(function () {
            alert("这是一个按钮，，，，，，，，，，")

        });*!/
        function init() {
            $.ajax({
                url: "init.json",
                async: false,
                dataType: "json",
                data: {param:"123"},
                success: function (result) {
                    alert("hh");
                }

            })
        }
        init();
    });*/
</script>