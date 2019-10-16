function CheckForm()
{

	if(document.add.taxed_pay_max.value=="")
	{
		alert("\n上限未填写");
		document.add.taxed_pay_max.focus();
		return false;
	}
	if (isNaN(document.add.taxed_pay_max.value)){
		alert ("\n 上限必须为数值!");
		document.add.taxed_pay_max.focus();
		return false;
	}
	
	if(document.add.taxed_pay_min.value=="")
	{
		alert("\n下限未填写");
		document.add.taxed_pay_min.focus();
		return false;
	}
	if (isNaN(document.add.taxed_pay_min.value)){
		alert ("\n 下限必须为数值!");
		document.add.taxed_pay_min.focus();
		return false;
	}
	
	if(document.add.tax_formular_percent.value=="")
	{
		alert("\n税率未填写!");
		document.add.tax_formular_percent.focus();
		return false;
	}
	if (isNaN(document.add.tax_formular_percent.value)){
		alert ("\n 税率必须为数值!");
		document.add.tax_formular_percent.focus();
		return false;
	}
	
	if(document.add.tax_add.value=="")
	{
		alert("\n减款未填写!");
		document.add.tax_add.focus();
		return false;
	}
	if (isNaN(document.add.tax_add.value)){
		alert ("\n 减款必须为数值!");
		document.add.tax_add.focus();
		return false;
	}
	return true;
}
