function CheckForm()
{
	var reg=/^\d{4}-\d{1,2}-\d{2}$/;
	if (document.arControlServlet.from_date.value == "") 
	{
		alert ("\n 开始日期未填写。");
		document.arControlServlet.from_date.focus();
		return false;
	}
	
	if (document.arControlServlet.to_date.value == "") 
	{
		alert ("\n 结束日期未填写。");
		document.arControlServlet.to_date.focus();
		return false;
	}
	
	if(!document.arControlServlet.from_date.value.match(reg))
	{
		alert ("\n 日期格式不正确!");
		document.arControlServlet.from_date.focus();
		return false;
	}
	
	if(!document.arControlServlet.to_date.value.match(reg))
	{
		alert ("\n 日期格式不正确!");
		document.arControlServlet.to_date.focus();
		return false;
	}
	return true;
}