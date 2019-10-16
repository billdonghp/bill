function CheckForm() {
		if (document.sf.expense_type.value == "") 	{
			alert ("\n 请填写汇总部门！");
			document.sf.expense_type.focus();
			return false;
		}

		if (document.sf.expense_type.value.length > 25) 	{
			alert ("\n 汇总部门字数不能大于25，请修改！");
			document.sf.expense_type.focus();
			return false;
		}		

		if (document.sf.debitcredit.value == "") 	{
			alert ("\n 请填写借贷方！");
			document.sf.debitcredit.focus();
			return false;
		}

		if (document.sf.debitcredit.value.length > 25) 	{
			alert ("\n 借贷方字数不能大于25，请修改！");
			document.sf.debitcredit.focus();
			return false;
		}		

		return true;
}
