function CheckForm() {		
strEmpID = document.EM2Form.empID.value;
		if(strEmpID=="")
		{
			alert(请输入决裁类型!);
			document.EM2Form.empID.focus();
			return false;
		}
		if(strEmpID >=20)
		{
			alert(您输入决裁类型太长，请在20个字符以内!);
			document.EM2Form.empID.focus();
			return false;
		}
}
