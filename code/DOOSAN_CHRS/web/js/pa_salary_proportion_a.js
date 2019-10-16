function CheckForm()
{
	if(document.add.base_part.value=="")
	{
		alert("\n基本金比例未填写");
		document.add.base_part.focus();
		return false;
	}
	
	if (isNaN(document.add.base_part.value)){
		alert ("\n 基本金比例必须为数值!");
		document.add.base_part.focus();
		return false;
	}
	
	if(document.add.ability_part.value=="")
	{
		alert("\n能力工资比例未填写");
		document.add.ability_part.focus();
		return false;
	}
	if (isNaN(document.add.ability_part.value)){
		alert ("\n 能力工资比例必须为数值!");
		document.add.ability_part.focus();
		return false;
	}
	return true;
}
