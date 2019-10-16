function CheckForm()
{   
    //社内信息 户口性质
    var reg=/^\d{4}-\d{1,2}-\d{2}$/;
	if (document.form1.chinesename.value == "") 
	{
		alert ("\n 姓名未填写。");
		document.form1.chinesename.focus();
		return false;
	}
	
	if (document.form1.englishname.value == "") 
	{
		alert ("\n 姓名未填写。");
		document.form1.englishname.focus();
		return false;
	}
	
	if (document.form1.date_start.value == "") 
	{
		alert ("\n 入社日期未填写。");
		document.form1.date_start.focus();
		return false;
	}
	
	if(!document.form1.date_start.value.match(reg))
	{
		alert ("\n 入社日期格式不正确!");
		document.form1.date_start.focus();
		return false;
	}
	
	if (document.form1.endprobationdate.value == "") 
	{
		alert ("\n 预转正日期未填写。");
		document.form1.date_start.focus();
		return false;
	}
	
	if(!document.form1.endprobationdate.value.match(reg))
	{
		alert ("\n 预转正日期格式不正确!");
		document.form1.date_start.focus();
		return false;
	}
	
	if (document.form1.empID.value == "") 
	{
		alert ("\n 工号未填写。");
		document.form1.empID.focus();
		return false;
	}
	
		if (document.form1.statuscode.value == "") 
	{
		alert ("\n 工作状态未填写。");
		document.form1.statuscode.focus();
		return false;
	}		
	
	if (document.form1.deptid.value == "") 
	{
		alert ("\n 部门未填写。");
		document.form1.deptid.focus();
		return false;
	}
	
	if (document.form1.position_code.value == "") 
	{
		alert ("\n 职位未填写。");
		document.form1.position_code.focus();
		return false;
	}
		
	if (document.form1.join_code.value == "") 
	{
		alert ("\n 入社类型未填写。");
		document.form1.join_code.focus();
		return false;
	}	
	
	if (document.form1.grade_code.value == "") 
	{
		alert ("\n 职级未填写。");
		document.form1.grade_code.focus();
		return false;
	}	
		
	if (document.form1.regtype_code.value == "") 
	{
		alert ("\n 户口性质未填写。");
		document.form1.regtypeCode.focus();
		return false;
	}

	if (document.form1.id_card_no.value == "" && document.form1.passportno.value == "") 
	{
		alert ("\n 身份证号码或护照号至少要填写一项。");
		document.form1.id_card_no.focus();
		return false;
	}
	
	if (document.form1.id_card_no.value != "" && 
		document.form1.id_card_no.value.length != 15  && document.form1.id_card_no.value.length != 18){
		alert ("\n 身份证号码应为15或18位!");
		document.form1.id_card_no.focus();
		return false;
	}
		
	//教育信息
	/*
	if ((document.form1.education_subject.value != "" ||
		document.form1.education_start.value != ""||
		document.form1.education_end.value != ""||
		document.form1.education_degree.value != "") &&
		document.form1.education_institution.value == "") 
	{
		alert ("\n 学校／机构未填写。");
		document.form1.education_institution.focus();
		return false;
	}
		
	if ((document.form1.education_institution.value != "" ||
		document.form1.education_subject.value != ""||
		document.form1.education_start.value != ""||
		document.form1.education_degree.value != "") &&
		document.form1.education_end.value == "") 
	{
		alert ("\n 毕业日期未填写。");
		document.form1.education_end.focus();
		return false;
	}
	*/
	
	return true;
}