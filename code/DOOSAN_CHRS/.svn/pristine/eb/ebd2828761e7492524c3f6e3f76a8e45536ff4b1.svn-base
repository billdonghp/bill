function CheckForm()
{
	var reg=/^\d{4}-\d{1,2}-\d{2}$/;
	if(document.save.year.value.length!=4)
	{
		alert ("\n 只能输入4个数字!");
		document.save.year.focus();
		return false;
	}
	
	if(!document.save.start_day.value.match(reg))
	{
		alert ("\n 日期格式不正确!");
		document.save.start_day.focus();
		return false;
	}
	
	if(!document.save.end_day.value.match(reg))
	{
		alert ("\n 日期格式不正确!");
		document.save.end_day.focus();
		return false;
	}
	return true;
}
