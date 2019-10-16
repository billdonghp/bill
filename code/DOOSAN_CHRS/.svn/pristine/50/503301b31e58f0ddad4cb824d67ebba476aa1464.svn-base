function CheckForm(msg) {
strMenuCode=document.EM2Form.menuCode.value;
	if(strMenuCode=="")
	{
		alert(msg[0]);
		document.EM2Form.menuCode.focus();
		return false;
	}
	if(strMenuCode.length>50)
	{
		alert(msg[1]);  
		document.EM2Form.menuCode.focus();
		return false;
	}
strMenuIntro=document.EM2Form.menuIntro.value;
	if(strMenuIntro=="")
	{
		alert(msg[2]);
		document.EM2Form.menuIntro.focus();
		return false;
	}
	if(strMenuIntro.length>250)
	{
		alert(msg[3]);
		document.EM2Form.menuIntro.focus();
		return false;
	}

strMenuParentCode=document.EM2Form.menuParentCode.value;
    if(strMenuParentCode=="")
	{
		alert(msg[4]);
		document.EM2Form.menuParentCode.focus();
		return false;
	}
	if(strMenuParentCode.length>10)
	{
		alert(msg[5]);                      
		document.EM2Form.menuParentCode.focus();
		return false;
	}

		return true;

}


