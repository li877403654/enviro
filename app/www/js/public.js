function initDate(){
	/**时间控件赋初始值**/
	var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六');
	var now = new Date();
	//格式化日，如果小于9，前面补0
	var day = ("0" + now.getDate()).slice(-2);
	//格式化月，如果小于9，前面补0
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	//拼装完整日期格式
	var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
    return today;
}

function initLabelDate(){
	/**时间控件赋初始值**/
	var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六');
	var now = new Date();
	//格式化日，如果小于9，前面补0
	var day = ("0" + now.getDate()).slice(-2);
	//格式化月，如果小于9，前面补0
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	//拼装完整日期格式
	var today = now.getFullYear()+"年"+(month)+"月"+(day)+"日" ;
    return today;
}

function initTitleDate(){
	/**时间控件赋初始值**/
	var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六');
	var now = new Date();
	//格式化日，如果小于9，前面补0
	var day = ("0" + now.getDate()).slice(-2);
	//格式化月，如果小于9，前面补0
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	//拼装完整日期格式
	var today = now.getFullYear()+"年"+(month)+"月";
    return today;
}