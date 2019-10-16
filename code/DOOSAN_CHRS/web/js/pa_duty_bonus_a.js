function CheckForm()
{
	if(document.add.bonus_amount.value=="")
	{
		alert("\n职责津贴未填写");
		document.add.bonus_amount.focus();
		return false;
	}
	if (isNaN(document.add.bonus_amount.value))
	{
		alert ("\n 职责津贴必须为数值!");
		document.add.bonus_amount.focus();
		return false;
	}
	
	if(document.add.duty.value == "")
	{
		alert("职责未填写");
		document.add.duty.focus();
		return false;
	}
	
	if(document.add.bonus_description.value=="")
	{
		alert("备注未填写");
		document.add.bonus_description.focus();
		return false;
	}
	if(document.add.bonus_description.value.length >50)
	{
		alert("备注的字符长度为50，当前为:"+document.add.bonus_description.value.length);
		document.add.bonus_description.focus();
		return false;
		
	}
	return true;
}
