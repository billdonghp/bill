function CheckForm(a) {
		if (document.sf.param_id.value == "") 	{
		//"\n 请填写参数ID！"
			alert (a[0]);
			document.sf.param_id.focus();
			return false;
		}

		if (document.sf.param_name.value == "") 	{
		//"\n 请填写参数名称！"
			alert (a[1]);
			document.sf.param_name.focus();
			return false;
		}
		
		if (document.sf.param_kor_name.value == "") 	{
		//"\n 请填写参数名称！"
			alert (a[2]);
			document.sf.param_kor_name.focus();
			return false;
		}

		if (document.sf.param_name.value.length > 25) 	{
		//"\n param_name字数不能大于25，请修改！"
			alert (a[3]);
			document.sf.param_name.focus();
			return false;
		}		
		
		return true;
}
