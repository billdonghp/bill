function CheckForm(size)
{
	var reg=/^\d{4}-\d{1,2}-\d{2}$/;	
	//工作经验
	for(i=0;i<size;i++){
		if (
			(
				document.getElementById("start_date"+i).value != "" ||
				document.getElementById("end_date"+i).value != ""
			) &&
			document.getElementById("work_form"+i).value == "")
		{
			alert ("\n 班次未填写。");
			document.getElementById("work_form"+i).focus();
			return false;
		}
		if (
			(
				document.getElementById("start_date"+i).value != "" ||
				document.getElementById("work_form"+i).value != ""
			) &&
			document.getElementById("end_date"+i).value == "")
		{
			alert ("\n 结束日期未填写。");
			document.getElementById("end_date"+i).focus();
			return false;
		}		if (
			(
				document.getElementById("work_form"+i).value != "" ||
				document.getElementById("end_date"+i).value != ""
			) &&
			document.getElementById("start_date"+i).value == "")
		{
			alert ("\n 开始日期未填写。");
			document.getElementById("start_date"+i).focus();
			return false;
		}		
		if(document.getElementById("start_date"+i).value!= "" && !document.getElementById("start_date"+i).value.match(reg)){
			alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
			document.getElementById("start_date"+i).focus();
			return false;
		}
		
		if(document.getElementById("end_date"+i).value!= "" && !document.getElementById("end_date"+i).value.match(reg)){
			alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
			document.getElementById("end_date"+i).focus();
			return false;
		}
	}
	return true;
}