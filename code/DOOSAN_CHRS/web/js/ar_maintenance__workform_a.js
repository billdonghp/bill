function CheckForm()
{
	if(document.save.work_form_code.value=="")
	{
		alert("\n编号未填写");
		document.save.work_form_code.focus();
		return false;
	}
	if(document.save.work_form_code.value.length !=5)
	{
		alert("\n编号为5个字符");
		document.save.work_form_code.focus();
		return false;
	}
	if(document.save.work_form.value=="")
	{
		alert("\n班组名称未填写");
		document.save.work_form.focus();
		return false;
	}
	
	if(document.save.work_form.value.length > 20)
	{
		alert("\n班组名称字符长度最大为20,当前:"+document.save.work_form.value.length);
		document.save.work_form.focus();
		return false;
	}
	
	if(document.save.description.value=="")
	{
		alert("\n简单描述未填写");
		document.save.description.focus();
		return false;
	}
	
	if(document.save.description.value.length > 40)
	{
		alert("\n简单描述字符长度最大为40,当前:"+document.save.description.value.length);
		document.save.description.focus();
		return false;
	}
	
	if(document.save.start_work_time.value=="")
	{
		alert("\n开始工作时间未填写");
		document.save.start_work_time.focus();
		return false;
	}
	
	if(document.save.end_work_time.value=="")
	{
		alert("\n结束工作时间未填写");
		document.save.end_work_time.focus();
		return false;
	}
	return true;
	
	
}