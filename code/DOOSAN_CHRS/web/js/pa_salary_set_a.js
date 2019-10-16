function CheckForm()
{
	var reg=/^\d{4}-\d{1,2}-\d{2}$/;
	//个人设置
	if(document.add.salary.value=="")
	{
		alert("\n标准月薪未填写");
		document.add.salary.focus();
		return false;
	}
	if(isNaN(document.add.salary.value))
	{
		alert("\n标准月薪必须为数值");
		document.add.salary.focus();
		return false;
	}
	
	//奖金
	if ((document.add.award_type.value <> "" or
		document.add.award_amount.value <> "") and
		document.add.award_date.value = "") 
	{
		alert ("\n 发放日期未填写。");
		document.add.award_date.focus();
		return false;
	}
	
	if (isNaN(document.add.award_amount.value)){
		alert ("\n 奖金金额必须为数值!");
		document.add.award_amount.focus();
		return false;
	}
	
	//其它扣款
	if ((document.add.deduction_type.value <> "" or
		document.add.deduction_amount.value <> "") and
		document.add.deduction_date.value = "") 
	{
		alert ("\n 扣款日期未填写。");
		document.add.deduction_date.focus();
		return false;
	}
	
	if ((document.add.deduction_type.value <> "" or
		document.add.deduction_date.value <> "") and
		document.add.deduction_amount.value = "") 
	{
		alert ("\n 扣款金额未填写。");
		document.add.deduction_amount.focus();
		return false;
	}
	
	if (isNaN(document.add.deduction_amount.value)){
		alert ("\n 扣款金额必须为数值!");
		document.add.deduction_amount.focus();
		return false;
	}
	
	//其他收入
	if ((document.add.income_type.value <> "" or
		document.add.income_amount.value <> "") and
		document.add.income_date.value = "") 
	{
		alert ("\n 收入日期未填写。");
		document.add.income_date.focus();
		return false;
	}
	
	if (isNaN(document.add.income_amount.value)){
		alert ("\n 收入金额必须为数值!");
		document.add.income_amount.focus();
		return false;
	}
	
	//津贴项
	if ((document.add.special_description.value <> "" or
		document.add.special_amount.value <> ""or
		document.add.q_allw_no.value <> ""or
		document.add.qual_start.value <> "") and
		document.add.special_start.value = "") 
	{
		alert ("\n 开始日期未填写。");
		document.add.special_start.focus();
		return false;
	}
	
	if ((document.add.special_description.value <> "" or
		document.add.special_start.value <> ""or
		document.add.special_amount.value <> ""or
		document.add.q_allw_no.value <> "") and
		document.add.qual_start.value = "") 
	{
		alert ("\n 信息未填写完全。");
		document.add.qual_start.focus();
		return false;
	}
	
	if (isNaN(document.add.special_amount.value)){
		alert ("\n 津贴金额必须为数值!");
		document.add.special_amount.focus();
		return false;
	}
	
	//Signing Bonus
	if ((document.add.signing_description.value <> "" or
		document.add.signing_end.value <> ""or
		document.add.signing_amount.value <> "") and
		document.add.signing_start.value = "") 
	{
		alert ("\n 开始日期未填写。");
		document.add.signing_start.focus();
		return false;
	}
	
	if ((document.add.signing_description.value <> "" or
		document.add.signing_start.value <> ""or
		document.add.signing_amount.value <> "") and
		document.add.signing_end.value = "") 
	{
		alert ("\n 结束日期未填写。");
		document.add.signing_end.focus();
		return false;
	}
	
	if (isNaN(document.add.signing_amount.value)){
		alert ("\n 津贴金额必须为数值!");
		document.add.signing_amount.focus();
		return false;
	}
	
	if(document.add.award_date.value.match(reg))
		{}
	else{
		alert ("\n 日期格式不正确!");
		document.add.award_date.focus();
		return false;
	}
	
	if(document.add.deduction_date.value.match(reg))
		{}
	else{
		alert ("\n 日期格式不正确!");
		document.add.deduction_date.focus();
		return false;
	}
	
	if(document.add.income_date.value.match(reg))
		{}
	else{
		alert ("\n 日期格式不正确!");
		document.add.income_date.focus();
		return false;
	}
	
	if(document.add.special_start.value.match(reg))
		{}
	else{
		alert ("\n 日期格式不正确!");
		document.add.special_start.focus();
		return false;
	}
	
	if(document.add.qual_start.value.match(reg))
		{}
	else{
		alert ("\n 日期格式不正确!");
		document.add.qual_start.focus();
		return false;
	}
	
	if(document.add.signing_start.value.match(reg))
		{}
	else{
		alert ("\n 日期格式不正确!");
		document.add.signing_start.focus();
		return false;
	}
	
	if(document.add.signing_end.value.match(reg))
		{}
	else{
		alert ("\n 日期格式不正确!");
		document.add.signing_end.focus();
		return false;
	}
	return true;
	
}