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

		if (document.sf.param_name.value.length > 25) 	{
		//"\n param_name字数不能大于25，请修改！"
			alert (a[3]);
			document.sf.param_name.focus();
			return false;
		}		
		
		/*
		if (document.sf.distinct_field.value == "EMPID" && document.sf.distinct_field_2nd.value != "") 	{
		//"\n 已选择工号为区分项目，不能选择其它区分项目！"
			alert ('已选择工号为区分项目，不能选择其它区分项目');
			document.sf.distinct_field_2nd.options[0].selected = true ;
			return false;
		}
		*/
		return true;
}
