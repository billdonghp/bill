function checkform(flagname)
{
	form = document.etn;
	if (form.BRIEF_CODE.value=="")
	{
		alert("请输入大纲代号");
		form.BRIEF_CODE.focus();
		return false;
	}
	if (form.BRIEF_NAME.value=="")
	{
		alert("请输入大纲名称");
		form.BRIEF_NAME.focus();
		return false;
	}
	if (form.BRIEF_TYPE_CODE.value=="")
	{
			alert("请输入大纲类型");
			form.BRIEF_TYPE_CODE.focus();
			return false;
	}
	if (form.BRIEF_STARTDATE.value=="")
	{
			alert("请输入执行开始时间");
			form.BRIEF_STARTDATE.focus();
			return false;
	}
	if (form.BRIEF_ENDDATE.value=="")
	{
			alert("请输入执行结束时间");
			form.BRIEF_ENDATE.focus();
			return false;
	}
	form.flag.value=flagname;
	form.submit();
}
function UpImage(){
	window.open("/upImage?flag=add","","toolbar,resizable,scrollbars,dependent,width=600,height=300,left=250,top=250");
}