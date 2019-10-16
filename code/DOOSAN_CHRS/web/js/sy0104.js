function CheckForm(msg) {
strEmpID = document.EM2Form.empID.value;
	if(document.EM2Form.check.value=="123")
	{
		alert(msg[0]);
		return false;
	}
	if(document.EM2Form.check.value=="不合格")
	{
		alert(msg[1]);
		return false;
	}
                         

	if(strEmpID=="")
	{
		alert(msg[2]);
		document.EM2Form.empID.focus();
		return false;
	}
	if(strEmpID.length > 20 )
	{
		alert(msg[3]);
		document.EM2Form.empID.focus();
		return false;
	}


strUserID = document.EM2Form.userID.value;
	if(strUserID=="")
	{
		alert(msg[4]);
		document.EM2Form.userID.focus();
		return false;
	}
	if(strUserID.length > 50 )
	{
		alert(msg[5]);
		document.EM2Form.userID.focus();
		return false;
	}

strPassWord = document.EM2Form.passWord.value;
	if(strPassWord=="")
	{
		alert(msg[6]);
		document.EM2Form.passWord.focus();
		return false;
	}
	if(strPassWord.length > 30 )
	{
		alert(msg[7]);
		document.EM2Form.passWord.focus();
		document.EM2Form.passWord.select();
		return false;
	}

	var flag=0;
	for(var i=0;i<EM2Form.elements.length;i++)
	{
		if(EM2Form.elements[i].type=="checkbox"&&EM2Form.elements[i].name=="radio")
		{
			if(EM2Form.elements[i].checked)
			flag=1;
		}
	}
	if(flag==0)
	{
		alert(msg[8]);
		return false;
	}
	
	var flag1=0;
	for(var i=0;i<EM2Form.elements.length;i++)
	{
		if(EM2Form.elements[i].type=="checkbox"&&EM2Form.elements[i].name=="dp")
		{
			if(EM2Form.elements[i].checked)
			flag1=1;
		}
	}
	if(flag1==0)
	{
		alert(msg[9]);
		return false;
	}
	return true;
}
