function CheckForm(a) {
		if (document.sf.item_id.value == "") 	{
		//"\n 请填写工资项目ID！"
			alert (a[0]);
			document.sf.item_id.focus();
			return false;
		}
		if (document.sf.item_name.value == "") 	{
		//"\n 请填写工资项目名称！"
			alert (a[1]);
			document.sf.item_name.focus();
			return false;
		}

		if (document.sf.item_name.value.length > 25) 	{
		//"\n 工资项目名称字数不能大于25，请修改！"
			alert (a[3]);
			document.sf.item_name.focus();
			return false;
		}		


		if (document.sf.pricision.value == "") 	{
		//"\n 请填写精度！"
			alert (a[4]);
			document.sf.pricision.focus();
			return false;
		}

		if (document.sf.pricision.value.length > 25) 	{
		//"\n 精度字数不能大于25，请修改！"
			alert (a[5]);
			document.sf.pricision.focus();
			return false;
		}		

		return true;
}
