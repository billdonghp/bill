
	function checkForm(){
	
//验证日期的正则表达式，包含格式和是否存在此日期
		var format = /^((([0-9]{2}([02468][048])|([13579][26])))(-)(2|02)(-)(([0][1-9])|([1-2][0-9])))|((([0-9]{2}([02468][123579])|([13579][01345789])))(-)(2|02)(-)(([0][1-9])|([1][0-9])([2][0-8])))|(([0-9]{4})(-)((([0]{0,1}(1|3|5|7|8))|(10|12))(-)(([0][1-9])|([1-2][0-9])|30|31))|(([0-9]{4})(-)((([0]{0,1}(4|6))|11))(-)(([0][1-9])|([1-2][0-9])|30)))$/g; 
		//加班日期
		var overtimeDateStr = document.form.overtimeDate.value;
		if(overtimeDateStr==''){
			alert("请选择加班日期");
			document.form.overtimeDate.focus();
			return false;
		}
		if(!overtimeDateStr.match(format)){
			alert ("请使用正确格式\n并确保是存在的日期。\n格式:\nYYYY-MM-MM");	
			document.form.overtimeDate.focus();
			return;
		}

//加班类型确认
		var overtimeApplyType = document.form.overtimeApplyType.value;
		if(overtimeApplyType==''){
			alert("请选择加班类型");
			document.form.overtimeApplyType.focus();
			return false;
		}

//加班类别确认
		var overtimeApplySort = document.form.overtimeApplySort.value;
		if(overtimeApplySort==''){
			alert("请选择加班类型");
			document.form.overtimeApplySort.focus();
			return false;
		}
		
//加班事由确认
		var causeStr = document.form.causeTextArea.value;
		if(causeStr==''){
			if(!window.confirm("没有填写加班事由，是否继续？")){
				return false;
			}
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
			empList.location.href = 'searchEmployeeOfOvertime.jsp?value='+searchVal;
		}
	}
	
//save
	function save(){
		if(checkForm()){
			document.form.submit();
		}
	}