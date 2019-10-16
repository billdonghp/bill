function CheckForm()
{
	if (document.add.area.value == "") 
	{
		alert ("\n 地区未填写。");
		document.add.area.focus();
		return false;
	}
	
	if (document.add.area.value.length >20) 
	{
		alert ("\n 地区字符长度为20，当前："+document.add.area.value.length);
		document.add.area.focus();
		return false;
	}
	
	if (document.add.year.value == "") 
	{
		alert ("\n 年份未填写。");
		document.add.year.focus();
		return false;
	}
	
	if (document.add.year.value.length !=4) 
	{
		alert ("\n 年份字符长度为4, 当前："+document.add.year.value.length);
		document.add.year.focus();
		return false;
	}
	
	if (isNaN(document.add.salary.value)){
		alert ("\n 最低工资必须为数值!");
		document.add.salary.focus();
		return false;
	}
	
	if (document.add.salary.value.length >10){
		alert ("\n 最低工资长度为10,当前:"+document.add.salary.value.length);
		document.add.salary.focus();
		return false;
	}
	return true;
}