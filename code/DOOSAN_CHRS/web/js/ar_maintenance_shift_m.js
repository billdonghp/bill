function CheckForm(size)
{
	var reg=/^\d{4}-\d{1,2}-\d{2}$/;	
	//工作经验
	for(i=0;i<size;i++){
		if (document.getElementById("work_form"+i).value == "")
		{
			alert ("\n 班次未填写。");
			document.getElementById("work_form"+i).focus();
			return false;
		}
	}
	return true;
}