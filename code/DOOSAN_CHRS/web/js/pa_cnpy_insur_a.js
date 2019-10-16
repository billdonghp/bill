function CheckForm()
{
	if(document.add.proportion_to_salary.value=="")
	{
		alert("\n占工资比例未填写");
		document.add.proportion_to_salary.focus();
		return false;
	}
	if (isNaN(document.add.proportion_to_salary.value)){
		alert ("\n 占工资比例必须为数值!");
		document.add.proportion_to_salary.focus();
		return false;
	}
	
	if(document.add.insur_type.value=="")
	{
		alert("\n保险类型未填写");
		document.add.insur_type.focus();
		return false;
	}
	
	if(document.add.insur_type.value.length > 40)
	{
		alert("\n保险类型字符长度为40字，当前:"+document.add.insur_type.value.length);
		document.add.insur_type.focus();
		return false;
	}
	return true;
}
