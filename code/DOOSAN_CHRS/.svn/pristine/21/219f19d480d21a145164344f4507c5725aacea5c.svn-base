function CheckForm(array)
{
	var reg=/^\d{4}-\d{1,2}-\d{2}$/;

	//------------------------必添-----------------------------//
	if ((document.save.education_subject.value != "" ||
		document.save.education_start.value != ""||
		document.save.education_end.value != ""||
		document.save.education_degree.value != ""||
		document.save.degree_type.value != "") &&
		document.save.education_institution.value == "") 
	{
		alert ("\n 学校／机构未填写。");
		document.save.education_institution.focus();
		return false;
	}
	
	if ((document.save.education_institution.value != "" ||
		document.save.education_subject.value != ""||
		document.save.education_start.value != ""||
		document.save.education_degree.value != ""||
		document.save.degree_type.value != "") &&
		document.save.education_end.value == "") 
	{
		alert ("\n 毕业日期未填写。");
		document.save.education_end.focus();
		return false;
	}
	if(document.save.education_end.value != "" && !document.save.education_end.value.match(reg)){
		alert ("\n 毕业日期格式不正确。\n\n正确格式：2005-04-05!");
		document.save.education_end.focus();
		return false;
	}
	
	if(document.save.education_start.value != "" && !document.save.education_start.value.match(reg)){
		alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
		document.save.education_start.focus();
		return false;
	}
	   
	if ((document.save.education_institution.value != "" ||
		document.save.education_subject.value != ""||
		document.save.education_start.value != ""||
		document.save.education_end.value != ""||
		document.save.degree_type.value != "") &&
		document.save.education_degree.value == "") 
	{
		alert ("\n 学历未填写。");
		document.save.education_degree.focus();
		return false;
	}
	
	//外语
	if ((document.save.language_exam.value != ""||
		document.save.language_mark.value != ""||
		document.save.language_obtain.value != ""||
		document.save.language_level.value != "") &&
		document.save.language_type.value == "") 
	{
		alert ("\n 语言种类未填写。");
		document.save.language_type.focus();
		return false;
	}
	
	if ((document.save.language_type.value != ""||
		document.save.language_mark.value != ""||
		document.save.language_obtain.value != ""||
		document.save.language_level.value != "") &&
		document.save.language_exam.value == "") 
	{
		alert ("\n 考试种类未填写。");
		document.save.language_exam.focus();
		return false;
	}
	if(document.save.language_obtain.value != "" && !document.save.language_obtain.value.match(reg)){
		alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
		document.save.language_obtain.focus();
		return false;
	}
	//兵役
	if ((document.save.military_type.value != ""||
		document.save.military_level.value != ""||
		document.save.military_area.value != ""||
		document.save.military_end.value != "") &&
		document.save.military_start.value == "") 
	{
		alert ("\n 开始日期未填写。");
		document.save.military_start.focus();
		return false;
	}
	if(document.save.military_start.value != "" && !document.save.military_start.value.match(reg)){
		alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
		document.save.military_start.focus();
		return false;
	}	
	//证书
	if ((document.save.qualification_grade.value != ""||
		document.save.qualification_mark.value != ""||
		document.save.qualification_institute.value != ""||
		document.save.qualification_end.value != "") &&
		document.save.qualification_name.value == "") 
	{
		alert ("\n 证书名称未填写。");
		document.save.qualification_name.focus();
		return false;
	}
	if(document.save.qualification_end.value != "" && !document.save.qualification_end.value.match(reg)){
		alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
		document.save.qualification_end.focus();
		return false;
	}	
	
	//工作经验
	if ((document.save.experience_prevcpnyname.value != "" ||
		document.save.experience_prevdeptname.value != ""||
		document.save.experience_prevposition.value != ""||
		document.save.experience_leavereason.value != ""||
		document.save.experience_enddate.value != "") &&
		document.save.experience_startdate.value == "") 
	{
		alert ("\n 任职开始期间未填写。");
		document.save.experience_startdate.focus();
		return false;
	}
	return true;
}