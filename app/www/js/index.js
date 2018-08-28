/**
 * js功能描述: <br>
 * ()
 *
 * @Author: Mr. xyb
 * @Date: 2018/8/13 10:41
 * @version: 1.0.0
 */
(function () {
    var pmTime = "";
    var sotime = "";
    $(function () {
        //
        /*$("#but1").click(function () {
            pmTime=$("#test1").val();
            getAirQuality('air-quality-map');
        });*/
		$("#searchDate1").change(function () {
			pmTime = $("#searchDate1").val();
			
			getAirQuality('airExcellent');
		});
		$("#searchDate2").change(function () {
			sotime = $("#searchDate2").val();
			getPollutant('contaminants');
		});
        // getRevenue('diagrammatize-revenue');
        getAirQuality('airExcellent');
        getPollutant('contaminants');
        getFactor('water');
		getRevenue("environmentalTax");
		getPollutionSource();
		getEnvironmentalTaxTevy();
    });

    function getRevenue(obj) {
		//alert("ok");
        var option = {
            tooltip: {
                trigger: 'axis',
                formatter: "{c}千万",
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
				y:10,
				x:100,
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
			axisLabel: {
				color: "#ff0" //刻度线标签颜色
			},
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: ['污水处理厂', '废水', '重金属', '危险废物', '废气']
            },
            series: [
                {
                    name: '2018年',
                    type: 'bar',
                    data: [1690, 1280, 900, 1800, 1560]
                }
            ]
        };
        setOptions(option, obj);
    }

    //空气质量优良天数占比,饼图显示
	function getAirQuality(obj) {

		backService.get("/environmentCredit/airqual.json", {
			time: pmTime

		}, function (res) {
			/*var option = {
				tooltip: {
					trigger: 'item',
					formatter: "{a} <br/>{b}: {c} ({d}%)"
				},
				series: [
					{
						name: '空气质量',
						type: 'pie',
						radius: ['50%', '70%'],
						avoidLabelOverlap: false,
						label: {
							normal: {
								show: true
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
						data: [
							{value: res.data.target, name: ''},//优良
							{value: res.data.typeAir, name: ''}//差
						]
					}
				]
			};
			*/
	var option = {
	    title : {
	        text: '',
	        subtext: '',
	        x:'center'
	    },
	    series: [
	        {
	            name:'',
	            type:'pie',
	            radius: ['40%', '55%'],
	            label: {
	                normal: {
	                    formatter: '{b}:{c}',
	                
	                    borderWidth: 1,
	                    borderRadius: 4,
	                    // shadowBlur:3,
	                    // shadowOffsetX: 2,
	                    // shadowOffsetY: 2,
	                    // shadowColor: '#999',
	                    // padding: [0, 7],
	                    rich: {
	                      
	                        // abg: {
	                        //     backgroundColor: '#333',
	                        //     width: '100%',
	                        //     align: 'right',
	                        //     height: 22,
	                        //     borderRadius: [4, 4, 0, 0]
	                        // },
	                       
	                    }
	                },
	                emphasis: {
	                    show: true,
	                   
	                }
	            },
	            data:[
	                {value:res.data.target, name:'优良'},
	                {value:res.data.typeAir, name:'差'},
	            ]
	        }
	    ]
	};
				setOptions(option, obj);
				
		
			}, function () {
	
			});
	
		}

	function getPollutant(obj) {
//		var option = {
//			tooltip: {
//				trigger: 'item',
//				formatter: "{a} <br/>{b} : {c} ({d}%)"
//			},
//			legend: {
//				orient: 'vertical',
//				left: 'right',
//				data: ['O3', 'PM2.5', 'NO2', 'PM10']
//			},
//			series: [
//				{
//					type: 'pie',
//					radius: '55%',
//					center: ['50%', '60%'],
//					data: [
//						{value: res.data.monitoringPoint, name: 'O3'},
//						{value: res.data.target, name: 'PM2.5'},
//						{value: res.data.level, name: 'NO2'},
//						{value: res.data.typeAir, name: 'PM10'}
////						{value: 56, name: 'O3'},
////						 {value: 16, name: 'PM2.5'},
////						 {value: 6, name: 'NO2'},
////						 {value: 29, name: 'PM10'}
//					],
//					itemStyle: {
//						emphasis: {
//							shadowBlur: 10,
//							shadowOffsetX: 0,
//							shadowColor: 'rgba(0, 0, 0, 0.5)'
//						}
//					}
//				}
//			]
//		};
		//setOptions(option, obj);
		backService.get("/environmentCredit/air.json", {
			date: sotime
		}, function (res) {
			var O3=res.data.monitoringPoint;
			var PM=res.data.target;
			var NO2=res.data.level;
			var PM10=res.data.typeAir;
			if(!O3){
				O3=56;
			}
			if(!PM){
				PM=30;
			}
			if(!NO2){
				NO2=46;
			}
			if(!PM10){
				PM10=20;
			}

			var option = {
				tooltip: {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					orient: 'vertical',
					left: 'right',
					data: ['O3', 'PM2.5', 'NO2', 'PM10']
				},
				series: [
					{
						type: 'pie',
						radius: '55%',
						center: ['50%', '60%'],
						data: [
							{value: O3, name: 'O3'},
							{value: PM, name: 'PM2.5'},
							{value: NO2, name: 'NO2'},
							{value: PM10, name: 'PM10'}
//							*//*{value: 56, name: 'O3'},
//							 {value: 6, name: 'PM2.5'},
//							 {value: 6, name: 'NO2'},
//							 {value: 6, name: 'PM10'},*//*
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
		}, function () {

		});

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
                    {name: '化学需氧量', value: 122},
                    {name: '生化需氧量', value: 112},
                    {name: '溶解氧', value: 333},
                    {name: '氨氮', value: 444}
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
   /**污染源监管程度第一个s数据**/
	function getPollutionSource(){
		backService.get("/environmentCredit/pullosourctrl.json", {
			time: pmTime
		}, function (res) {
		 var dbDate=res;
		 $("#gk").html(dbDate.data.linkedCaseInsensitiveMapList[0].country);
		 $("#prok").html(dbDate.data.linkedCaseInsensitiveMapList[0].pro);
	     $("#cityk").html(dbDate.data.linkedCaseInsensitiveMapList[0].city);
		}, function () {

		});
	}
	/**环保税征收情况**/
	function getEnvironmentalTaxTevy(){
		backService.get("/environmentCredit/pollutantsq.json", {
		}, function (res) {
			var dbDate=res;
			$("#qys").html(dbDate.data.hashMap.count);
			$("#zse").html(dbDate.data.hashMap.sum);
			$("#unit").html(dbDate.data.hashMap.unit+"<span>总税额</span>");
		}, function () {
		});
	}

})();