function CheckForm(message) {
strScreenGrantNo = document.EM2Form.screenGrantNo.value;
	
	if(document.EM2Form.check.value=="123")
	{
		//alert("请检测权限组号");
		alert(message[2]);
		return false;
	}
	if(document.EM2Form.check.value == "不合格" || document.EM2Form.check.value == "Fail!")
	{
		//alert("权限组号不合格");
		alert(message[3]);
		return false;
	}
	
	if (strScreenGrantNo=="" )
	{
		alert("请输入权限组号！");
		document.EM2Form.screenGrantNo.focus();
		return false;
	}
	if (strScreenGrantNo.length >20 )
	{
		alert("您输入的权限组号太长，请在20个字符以内！");
		document.EM2Form.screenGrantNo.focus();
		return false;
	}

strScreenGrantName = document.EM2Form.screenGrantName.value;
	if (strScreenGrantName=="" )
	{
		//alert("请输入权限组描述！");
		alert(message[0]);
		document.EM2Form.screenGrantName.focus();
		return false;
	}
	if (strScreenGrantName.length >50 )
	{
		//alert("您输入的权限组描述太长，请在50个字符以内！");
		alert(message[7]);
		document.EM2Form.screenGrantName.focus();
		return false;
	}
	
	var flag=0;
	for(var i=0;i<EM2Form.elements.length;i++)
	{
		if(EM2Form.elements[i].type=="checkbox")
		{
			if(EM2Form.elements[i].checked)
			flag=1;
		}
	}
	if(flag==0)
	{
		//alert("请选择权限");
		alert(message[6]);
		return false;
	}
	return true;
}


function RoleRight() {

for (var i=0;i<document.EM2Form.RInsert.length;i++)
    {
	if (document.EM2Form.RInsert[i].checked){
		document.EM2Form.InsertR.value=document.EM2Form.InsertR.value+","+document.EM2Form.RInsert[i].value;
	}
//        alert("content_r---"+document.EM2Form.InsertR.value);

	if (document.EM2Form.RUpdate[i].checked){
	document.EM2Form.UpdateR.value=document.EM2Form.UpdateR.value+","+document.EM2Form.RUpdate[i].value;
	}
	if (document.EM2Form.RDelete[i].checked){
	document.EM2Form.DeleteR.value=document.EM2Form.DeleteR.value+","+document.EM2Form.RDelete[i].value;
	}
	if (document.EM2Form.RFore[i].checked){
	document.EM2Form.ForeR.value=document.EM2Form.ForeR.value+","+document.EM2Form.RFore[i].value;
	}
	}

document.EM2Form.submit();
}
