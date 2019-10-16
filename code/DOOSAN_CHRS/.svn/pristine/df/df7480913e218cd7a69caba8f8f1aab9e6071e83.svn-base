
function CheckForm()
{
	var reg=/^\d{4}-\d{1,2}-\d{2}$/;
	if(document.add.bonus_amount.value=="")
	{
		alert("\n津贴金额未填写");
		document.add.bonus_amount.focus();
		return false;
	}
	if (isNaN(document.add.bonus_amount.value)){
		alert ("\n 津贴金额必须为数值!");
		document.add.bonus_amount.focus();
		return false;
	}
	
	if(document.add.issue_date.value=="")
	{
		alert("\n 日期未填写");
		document.add.issue_date.focus();
		return false;
	}
	
	if(document.add.issue_date.value.match(reg))
		{}
	else{
		alert ("\n 日期格式不正确!");
		document.add.issue_date.focus();
		return false;
	}
	if(document.add.bonus_reason.value=="")
	{
		alert("\n津贴发放原因未填写");
		document.add.bonus_reason.focus();
		return false;
	}
	if(document.add.bonus_reason.value.length >100)
	{
		alert("\n津贴发放原因超过100字，当前："+document.add.bonus_reason.value.length);
		document.add.bonus_reason.focus();
		return false;
	}
	
	return true;
	
}