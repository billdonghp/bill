function CheckForm()
{
	if(document.add.bonus_amount.value=="")
	{
		alert("\n 资格津贴未填写");
		document.add.bonus_amount.focus();
		return false;
	}
	if (isNaN(document.add.bonus_amount.value)){
		alert ("\n 资格津贴必须为数值!");
		document.add.bonus_amount.focus();
		return false;
	}
	
	if(document.add.bonus_type.value=="")
	{
		alert("\n资格金帖类型未填写");
		document.add.bonus_type.focus();
		return false;
	}
	if(document.add.bonus_type.value.length >20)
	{
		alert("\n资格金帖类型字符长度为20，当前："+document.add.bonus_type.value.length)
		document.add.bonus_type.focus();
		return false;
	}
	
	if(document.add.bonus_description.value=="")
	{
		alert("\n类型描述未填写");
		document.add.bonus_description.focus();
		return false;
	}
	
	if(document.add.bonus_description.value.length >40)
	{
		alert("\n类型描述字符长度为40,当前:"+document.add.bonus_description.value.length)
		document.add.bonus_description.focus();
		return false;
	}
	
	return true;
	
}
