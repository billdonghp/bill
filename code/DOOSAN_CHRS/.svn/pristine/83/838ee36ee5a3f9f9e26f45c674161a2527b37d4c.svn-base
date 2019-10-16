function checkform(flagname)
{
	form = document.lesson;
	if (form.LESSON_CODE.value=="")
	{
		alert("请输入课程代号");
		form.LESSON_CODE.focus();
		return false;
	}
	if (form.LESSON_NAME.value=="")
	{
		alert("请输入课程名称");
		form.LESSON_NAME.focus();
		return false;
	}
	form.flag.value=flagname;
	form.submit();
}
