function CheckForm() {
strSupervisor_ID=document.EM2Form.Supervisor1st_ID.value;
	if(strSupervisor_ID=="")
	{
		alert("请输入一次评价者！");
		document.EM2Form.Supervisor1st_ID.focus();
		return false;
	}
	if(strSupervisor_ID.length>20)
	{
		alert("您输入的评价者太长，请在20个字符以内！");
		document.EM2Form.Supervisor1st_ID.focus();
		return false;
	}


strEmpID=document.EM2Form.EmpID.value;
    if(strEmpID=="")
	{
		alert("请输入被评价者！");
		document.EM2Form.EmpID.focus();
		return false;
	}
	if(strEmpID.length>20)
	{
		alert("您输入的被评价者太长，请在20个字符以内！");
		document.EM2Form.EmpID.focus();
		return false;
	}

strEva_times=document.EM2Form.Supervisor2nd_ID.value;
    if(strEva_times=="")
	{
		alert("请输入二次评价者！");
		document.EM2Form.Supervisor2nd_ID.focus();
		return false;
	}
	if(strEva_times.length>20)
	{
		alert("您输入的二次评价者，请在1个字符以内！");
		document.EM2Form.Supervisor2nd_ID.focus();
		return false;
	}


		return true;

}


