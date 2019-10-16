
	function checkForm(){
		//每天休假的小时数
		var leaveHours = document.form.leaveHours.value;
		if(leaveHours==''){
			alert("每天的休息时间");
			document.form.leaveHours.focus();
			return false;
		}
		if(leaveHours > 8){	
			alert("每天的休假长度不应该大于8小时");
			document.form.leaveHours.focus();
			return;
		}	
		
//验证日期的正则表达式，包含格式和是否存在此日期
		var format = /^((([0-9]{2}([02468][048])|([13579][26])))(-)(2|02)(-)(([0][1-9])|([1-2][0-9])))|((([0-9]{2}([02468][123579])|([13579][01345789])))(-)(2|02)(-)(([0][1-9])|([1][0-9])([2][0-8])))|(([0-9]{4})(-)((([0]{0,1}(1|3|5|7|8))|(10|12))(-)(([0][1-9])|([1-2][0-9])|30|31))|(([0-9]{4})(-)((([0]{0,1}(4|6))|11))(-)(([0][1-9])|([1-2][0-9])|30)))$/g; 
		//休假开始时间
		var startDateStr = document.form.startDate.value;
		if(startDateStr==''){
			alert("请选择开始时间");
			document.form.startDate.focus();
			return false;
		}
		if(!startDateStr.match(format)){
			alert ("请使用正确格式\n并确保是存在的日期。\n格式:\nYYYY-MM-MM");	
			document.form.startDate.focus();
			return;
		}
		
//休假结束时间
		var endDateStr = document.form.endDate.value;
		if(endDateStr==''){
			alert("请选择结束时间");
			document.form.endDate.focus();
			return false;
		}
		if(!endDateStr.match(format)){
			alert ("请使用正确格式\n并确保是存在的日期。\n格式:\nYYYY-MM-MM");	
			document.form.endDate.focus();
			return false;
		}

//最后向用户确认是否保存
		if(!window.confirm("确认保存吗？")){
			return false;
		}
		
		return true;
	}
	
//打开选择人员的页面
	function searchEmployee(){
		var searchVal = document.form.empSelectText.value;
		if(searchVal != null && searchVal != ""){
			empList.location.href = 'searchEmployeeOfVacation.jsp?value='+searchVal;
		}
	}
	
//save
	function save(){
		if(checkForm()){
			document.form.submit();
		}
	}