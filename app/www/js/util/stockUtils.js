/**
 * js功能描述: <br>
 * (常用的工具方法)
 *
 * @Author: Mr. xyb
 * @Date: 2018/8/16 14:37
 * @version: 1.0.0
 */
(function () {

    var common = {};

    common.setupForm = function ($form) {
        $form.find('label').click(function () {
            var $next = $(this).next();
            if ($next.is('input,textarea')) {
                $next.focus();
            }
        });

        function togglePasswordVisibility($input) {
            if ($input.attr('type') === 'password') {
                $input.attr('type', 'text');
            }
            else {
                $input.attr('type', 'password');
            }
        }

        $form.find('.lnk-show-password').click(function () {
            togglePasswordVisibility($(this).parent().find('input'));
        });
    };

    common.setupTimeSelect = function ($timeSelect, $timeSheet) {
        var timeSheet;
        var mobiscroll;

        function selectTime() {
            var time = $timeSelect.find('.value').text();
            var values = $.trim(time).split(':');

            $timeSheet.find('.selects').remove();
            $timeSheet.append('<div class="selects"></div>');
            var $selects = $timeSheet.find('.selects');

            //
            var hours = [
                '06', '07', '08', '09', '10', '11', '12', '13', '14',
                '15', '16', '17', '18', '19', '20', '21', '22', '23'
            ];
            var hourValue = '10';
            if ($.inArray(values[0], hours) !== -1) {
                hourValue = values[0];
            }

            var minutes = [
                '00', '05', '10', '15', '20', '25', '30',
                '35', '40', '45', '50', '55'
            ];
            var minuteValue = minutes[0];
            if ($.inArray(values[1], minutes) !== -1) {
                minuteValue = values[1];
            }

            //
            $selects.mobiscroll().scroller({
                theme: 'mobiscroll',
                height: 34,
                showLabel: true,
                rows: 7,
                wheels: [
                    [{label: '时', data: hours}, {label: '分', data: minutes}]
                ],
                display: 'inline',
                layout: 'liquid',
                circular: false
            });
            mobiscroll = $selects.mobiscroll('getInst');
            $timeSheet.bind('close', function () {
                $selects.remove();
                setTimeout(function () {
                    mobiscroll.destroy();
                }, 0);
            });

            mobiscroll.setArrayVal([hourValue, minuteValue]);
            timeSheet.open();
        }

        $timeSelect.on('click', function (e) {
            e.preventDefault();
            selectTime();
        });

        timeSheet = $timeSheet.data("kendoMobileCustomSheet");
        $timeSheet.find('.lnk-ok').click(function (e) {
            e.preventDefault();
            $timeSelect.find('.value').text(mobiscroll.getVal().replace(/\s/, ':'));
            timeSheet.close();
        });
        $timeSheet.find('.lnk-cancel').click(function (e) {
            e.preventDefault();
            timeSheet.close();
        });

    };

    common.setupRegionSelect = function ($regionSelect, $regionSheet) {
        var regionSheet;
        var mobiscroll;

        function selectRegion() {
            var region = $regionSelect.find('.value').text();
            var values = $.trim(region).split(/\s+/);

            $regionSheet.find('.selects').remove();
            $regionSheet.append('<div class="selects"></div>');
            var $selects = $regionSheet.find('.selects');

            //
            var provinces = AreaData.getProvinces();
            var provinceValue = provinces[0];
            if ($.inArray(values[0], provinces) !== -1) {
                provinceValue = values[0];
            }

            var cities = AreaData.getCities(provinceValue);
            var cityValue = cities[0];
            if ($.inArray(values[1], cities) !== -1) {
                cityValue = values[1];
            }

            var districts = AreaData.getDistricts(provinceValue, cityValue);
            var districtValue = districts[0];
            if ($.inArray(values[2], districts) !== -1) {
                districtValue = values[2];
            }

            //
            $selects.mobiscroll().scroller({
                theme: 'mobiscroll',
                height: 34,
                rows: 7,
                wheels: [
                    [{data: provinces}, {data: cities}, {data: districts}]
                ],
                display: 'inline',
                layout: 'liquid',
                circular: false,
                validate: function (e, inst) {
                    if (inst.validating) {
                        return;
                    }
                    inst.validating = true;
                    console.log("validate.." + new Date());
                    if (e.index === 0) {
                        setTimeout(function () {
                            var province = e.values[0];
                            var cities = AreaData.getCities(province);
                            var districts = AreaData.getDistricts(province, cities[0]);
                            inst.changeWheel({
                                1: {data: cities},
                                2: {data: districts}
                            }, 3);
                            inst.setArrayVal([province, cities[0], districts[0]])
                        }, 0);
                    }
                    else if (e.index === 1) {
                        setTimeout(function () {
                            var province = e.values[0];
                            var city = e.values[1];
                            var districts = AreaData.getDistricts(province, city);
                            inst.changeWheel({
                                2: {data: districts}
                            }, 3);
                            inst.setArrayVal([province, city, districts[0]])
                        }, 0);
                    }
                    setTimeout(function () {
                        inst.validating = false;
                    }, 1);
                }
            });
            mobiscroll = $selects.mobiscroll('getInst');
            $regionSheet.bind('close', function () {
                $selects.remove();
                setTimeout(function () {
                    mobiscroll.destroy();
                }, 0);
            });

            mobiscroll.setArrayVal([provinceValue, cityValue, districtValue]);
            regionSheet.open();
        }

        $regionSelect.on('click', function (e) {
            e.preventDefault();
            selectRegion();
        });

        regionSheet = $regionSheet.data("kendoMobileCustomSheet");
        $regionSheet.find('.lnk-ok').click(function (e) {
            e.preventDefault();
            $regionSelect.find('.value').text(mobiscroll.getVal());
            regionSheet.close();
        });
        $regionSheet.find('.lnk-cancel').click(function (e) {
            e.preventDefault();
            regionSheet.close();
        });


    };

    common.setupTabs = function ($tabs, $sheetsContainer) {
        $tabs.on('click', 'a', function () {
            var $a = $(this);
            if ($a.hasClass('disabled')) {
                return;
            }
            $tabs.find('.selected').removeClass('selected');
            $a.addClass('selected');

            var index = $a.index();
            var $sheets = $sheetsContainer.find('.sheet');
            $sheets.filter(':visible').hide();
            $sheets.eq(index).show();
        });
    };

    common.showResultMsg = function (result) {
        var msg = result.msg;
        if (msg) {
            if (msg.length < 20) {
                app.showToast(msg);
            }
            else {
                app.alert(msg);
            }
        }
    };

    /**
     * add by Huang
     * 中文姓名校验
     */
    common.isName = function (name) {
        var pattern = /^[\u4E00-\u9FA5]{1,6}$/;
        return pattern.test(name);
    };

    /**
     * add by Huang
     * 电话号码校验
     */
    common.isMobile = function (moible) {
        var pattern = /^1[34578]\d{9}$/;
        return pattern.test(moible);
    };

    /**
     * add by Huang
     * 解决浮点数加法bug
     */
    common.add = function (a, b) {
        var c, d, e;
        try {
            c = a.toString().split(".")[1].length;
        } catch (f) {
            c = 0;
        }
        try {
            d = b.toString().split(".")[1].length;
        } catch (f) {
            d = 0;
        }
        return e = Math.pow(10, Math.max(c, d)), (common.mul(a, e) + common.mul(b, e)) / e;
    };

    /**
     * add by Huang
     * 解决浮点数减法bug
     */
    common.sub = function (a, b) {
        var c, d, e;
        try {
            c = a.toString().split(".")[1].length;
        } catch (f) {
            c = 0;
        }
        try {
            d = b.toString().split(".")[1].length;
        } catch (f) {
            d = 0;
        }
        return e = Math.pow(10, Math.max(c, d)), (common.mul(a, e) - common.mul(b, e)) / e;
    };

    /**
     * add by Huang
     * 解决浮点数乘法bug
     */
    common.mul = function (a, b) {
        var c = 0,
            d = a.toString(),
            e = b.toString();
        try {
            c += d.split(".")[1].length;
        } catch (f) {
        }
        try {
            c += e.split(".")[1].length;
        } catch (f) {
        }
        return Number(d.replace(".", "")) * Number(e.replace(".", "")) / Math.pow(10, c);
    };

    /**
     * add by Huang
     * 解决浮点数除法bug
     */
    common.p = function (a, b) {
        var c, d, e = 0,
            f = 0;
        try {
            e = a.toString().split(".")[1].length;
        } catch (g) {
        }
        try {
            f = b.toString().split(".")[1].length;
        } catch (g) {
        }
        return c = Number(a.toString().replace(".", "")), d = Number(b.toString().replace(".", "")), mul(c / d, Math.pow(10, f - e));
    };

    /**
     * 身份证验证
     * @param card
     * @returns {boolean}
     */
    common.isCardNo = function (card) {
        var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        return pattern.test(card);
    };

    //

    common.parseRepeatDayText = function (repeatDay) {
        if (!repeatDay) {
            return '';
        }
        if (repeatDay[0] === '1') {
            return '每天';
        }
        else {
            var result = '';
            var weekDayNames = ['一', '二', '三', '四', '五', '六', '日'];
            for (var n = 1; n <= 7; n++) {
                if (repeatDay[n] === '1') {
                    if (result) {
                        result += '、';
                    }
                    result += weekDayNames[n - 1];
                }
            }
            return result ? '周' + result : result;
        }
    };


    window.common = common;

})();