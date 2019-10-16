function checkform(flagname)
{
	form = document.etn;
	if (form.PROTOCOL_CODE.value=="")
	{
		alert("请输入协议代号");
		form.BRIEF_CODE.focus();
		return false;
	}
	if (form.PROTOCOL_NAME.value=="")
	{
		alert("请输入协议名称");
		form.PROTOCOL_NAME.focus();
		return false;
	}
	if (form.ETN_ACCESORIES_NAME.value=="")
	{
		alert("请输入附件名称");
		form.ETN_ACCESORIES_NAME.focus();
		return false;
	}
	form.flag.value=flagname;
	form.submit();
}
function UpImage(){
	window.open("/upImage?flag=add","","toolbar,resizable,scrollbars,dependent,width=600,height=300,left=250,top=250");
}