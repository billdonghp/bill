function CheckForm(size)
{
	var reg=/^\d{4}-\d{1,2}-\d{2}$/;	
	//工作经验
	for(i=0;i<size;i++){
		if (document.getElementById("leave_type"+i).value == "")
		{
			alert ("\n 请假类型未填写。");
			document.getElementById("leave_type"+i).focus();
			return false;
		}
		if (document.getElementById("leave_date"+i).value == "")
		{
			alert ("\n 请假日期未填写。");
			document.getElementById("leave_date"+i).focus();
			return false;
		}		
		if(document.getElementById("leave_date"+i).value!= "" && !document.getElementById("leave_date"+i).value.match(reg)){
			alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
			document.getElementById("leave_date"+i).focus();
			return false;
		}
	}
	return true;
}