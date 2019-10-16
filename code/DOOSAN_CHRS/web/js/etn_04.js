function checkform(flagname)
{
	form = document.teacher;
	if (form.teachercode.value=="")
	{
		alert("请输入讲师号");
		form.teachercode.focus();
		return false;
	}
	if (form.teachername.value=="")
	{
		alert("请输入讲师姓名");
		form.teachername.focus();
		return false;
	}
	if (form.teachertype.value=="")
	{
		alert("请输入讲师类型");
		form.teachertype.focus();
		return false;
	}
	if (form.company.value=="")
	{
		alert("请输入讲师所属公司");
		form.company.focus();
		return false;
	}
	if (form.address.value=="")
	{
		alert("请输入通信地址");
		form.address.focus();
		return false;
	}
	if (form.post.value=="")
	{
		alert("请输入邮政编码");
		form.post.focus();
		return false;
	}
	if (form.post.value=="")
	{
		alert("请输入邮政编码");
		form.post.focus();
		return false;
	}
	if (form.tel.value=="")
	{
		alert("请输入联系电话");
		form.tel.focus();
		return false;
	}
	if (form.money.value=="")
	{
		alert("请输入课时费");
		form.money.focus();
		return false;
	}
	if (form.money.value!="")
	{
		if (IsInteger(form.money.value,'+')){}
			else{
			alert ("\n 课时费必须为正整数");
			form.money.focus();
			return false;
			}
	}
	if (form.note.value=="")
	{
		alert("请输入备注");
		form.note.focus();
		return false;
	}
	form.flag.value=flagname;
	form.submit();
}
function IsInteger(string ,sign)
{ 
var integer;
if ((sign!=null) && (sign!='-') && (sign!='+'))
{
alert('IsInter(string,sign)的参数出错：\nsign为null或"-"或"+"');
return false;
}
integer = parseInt(string);
if (isNaN(integer))
{
return false;
}
else if (integer.toString().length==string.length)
{ 
if ((sign==null) || (sign=='-' && integer<0) || (sign=='+' && integer>0))
{
return true;
}
else
return false; 
}
else
return false;
}
