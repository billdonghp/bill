function CheckForm(msg) {
strBasicCode = document.EM2Form.basicCode.value;
	if(strBasicCode=="")
	{
		//alert("请输入基本代码！");
		alert(msg[0]);				
		document.EM2Form.basicCode.focus();
		return false;
	}
	if(strBasicCode.length>20)
	{
		//alert("您输入基本代码太长，请在20个字符以内！");
		alert(msg[1]);
		document.EM2Form.basicCode.focus();
		return false;
	}

strBasicName = document.EM2Form.basicName.value;
	if(strBasicName=="")
	{
		//alert("请输入基本代码名称！");
		alert(msg[2]) ;
		document.EM2Form.basicName.focus();
		return false;
	}
	if(strBasicName.length>50)
	{
		//alert("您输入基本代码名称太长，请在50个字符以内！");
		alert(msg[3]) ;
		document.EM2Form.basicName.focus();
		return false;
	}

strParentCode = document.EM2Form.parentCode.value;
	if(strParentCode=="")
	{
		alert("请输入父级代码名称！");
		document.EM2Form.parentCode.focus();
		return false;
	}
	if(strParentCode.length>50)
	{
		alert("您输入父级代码名称太长，请在50个字符以内！");
		document.EM2Form.parentCode.focus();
		return false;
	}
	return true;
}
