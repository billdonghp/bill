function checkform(flagname)
{
	form = document.group;
	if (form.ETN_GROUP_CODE.value=="")
	{
		alert("请输入教育组代号");
		form.ETN_GROUP_CODE.focus();
		return false;
	}
	if (form.ETN_GROUP_NAME.value=="")
	{
		alert("请输入教育组名称");
		form.ETN_GROUP_NAME.focus();
		return false;
	}

	form.flag.value=flagname;
	form.submit();
}
