function CheckForm(array)
{

	var reg=/^[1,2]\d{3}-\d{1,2}-\d{1,2}$/;

	//------------------------必添-----------------------------//
	//教育经历
	for(i=0;;i++){
		if(document.getElementById("education_institution"+i)==null) break;
		if ((document.getElementById("education_subject"+i).value != "" ||
			document.getElementById("education_start"+i).value != ""||
			document.getElementById("education_end"+i).value != ""||
			document.getElementById("education_degree"+i).value != ""||
			document.getElementById("degree_type"+i).value != "") &&
			document.getElementById("education_institution"+i).value == "") 
		{
			alert ("\n 学校／机构未填写。");
			document.getElementById("education_institution"+i).focus();
			return false;
		}
		
		if ((document.getElementById("education_institution"+i).value != "" ||
			document.getElementById("education_subject"+i).value != ""||
			document.getElementById("education_start"+i).value != ""||
			document.getElementById("education_degree"+i).value != ""||
			document.getElementById("degree_type"+i).value != "") &&
			document.getElementById("education_end"+i).value == "") 
		{
			alert ("\n 毕业日期未填写。");
			document.getElementById("education_end"+i).focus();
			return false;
		}
		if(document.getElementById("education_end"+i).value != "" && !document.getElementById("education_end"+i).value.match(reg)){
			alert ("\n 毕业日期格式不正确。\n\n正确格式：2005-04-05!");
			document.getElementById("education_end"+i).focus();
			return false;
		}
		
		if(document.getElementById("education_start"+i).value != "" && !document.getElementById("education_start"+i).value.match(reg)){
			alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
			document.getElementById("education_start"+i).focus();
			return false;
		}
		   
		if ((document.getElementById("education_institution"+i).value != "" ||
			document.getElementById("education_subject"+i).value != ""||
			document.getElementById("education_start"+i).value != ""||
			document.getElementById("education_end"+i).value != ""||
			document.getElementById("degree_type"+i).value != "") &&
			document.getElementById("education_degree"+i).value == "") 
		{
			alert ("\n 学历未填写。");
			document.getElementById("education_degree"+i).focus();
			return false;
		}
	}
	
	//外语
	for(i=0;;i++){
		if(document.getElementById("language_type"+i)==null) break;
		if ((document.getElementById("language_exam"+i).value != ""||
			document.getElementById("language_mark"+i).value != ""||
			document.getElementById("language_obtain"+i).value != ""||
			document.getElementById("language_level"+i).value != "") &&
			document.getElementById("language_type"+i).value == "") 
		{
			alert ("\n 语言种类未填写。");
			document.getElementById("language_type"+i).focus();
			return false;
		}
		
		if ((document.getElementById("language_type"+i).value != ""||
			document.getElementById("language_mark"+i).value != ""||
			document.getElementById("language_obtain"+i).value != ""||
			document.getElementById("language_exam"+i).value != "") &&
			document.getElementById("language_level"+i).value == "") 
		{
			alert ("\n 请选择语言等级！");
			document.getElementById("language_level"+i).focus();
			return false;
		}
		
		if ((document.getElementById("language_type"+i).value != ""||
			document.getElementById("language_mark"+i).value != ""||
			document.getElementById("language_obtain"+i).value != ""||
			document.getElementById("language_level"+i).value != "") &&
			document.getElementById("language_exam"+i).value == "") 
		{
			alert ("\n 考试种类未填写。");
			document.getElementById("language_exam"+i).focus();
			return false;
		}
		if(document.getElementById("language_obtain"+i).value != "" && !document.getElementById("language_obtain"+i).value.match(reg)){
			alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
			document.getElementById("language_obtain"+i).focus();
			return false;
		}
	}

	//兵役
	for(i=0;;i++){
		if(document.getElementById("military_type"+i)==null) break;
		if ((document.getElementById("military_type"+i).value != ""||
			document.getElementById("military_level"+i).value != ""||
			document.getElementById("military_area"+i).value != ""||
			document.getElementById("military_end"+i).value != "") &&
			document.getElementById("military_start"+i).value == "") 
		{
			alert ("\n 兵役开始日期未填写。");
			document.getElementById("military_start"+i).focus();
			return false;
		}
		if(document.getElementById("military_start"+i).value != "" && !document.getElementById("military_start"+i).value.match(reg)){
			alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
			document.getElementById("military_start"+i).focus();
			return false;
		}
		if(document.getElementById("military_end"+i).value != "" && !document.getElementById("military_end"+i).value.match(reg)){
			alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
			document.getElementById("military_end"+i).focus();
			return false;
		}	
	}
	//证书
	for(i=0;;i++){
		if(document.getElementById("qualification_name"+i)==null) break;
		if ((document.getElementById("qualification_grade"+i).value != ""||
			document.getElementById("qualification_mark"+i).value != ""||
			document.getElementById("qualification_institute"+i).value!= ""||
			document.getElementById("qualification_end"+i).value != "") &&
			document.getElementById("qualification_name"+i).value == "") 
		{
			alert ("\n 证书名称未填写。");
			document.getElementById("qualification_name"+i).focus();
			return false;
		}
		if(document.getElementById("qualification_end"+i).value != "" && !document.getElementById("qualification_end"+i).value.match(reg)){
			alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
			document.getElementById("qualification_end"+i).focus();
			return false;
		}	
	}
	//工作经验
	for(i=0;;i++){
		if(document.getElementById("experience_prevcpnyname"+i)==null) break;
		if (
			(
				document.getElementById("experience_prevcpnyname"+i).value != "" ||
				document.getElementById("experience_prevdeptname"+i).value != "" ||
				document.getElementById("experience_prevposition"+i).value != "" ||
				document.getElementById("experience_leavereason"+i).value != "" ||
				document.getElementById("experience_enddate"+i).value != ""
			) &&
			document.getElementById("experience_startdate"+i).value == "")
		{
			alert ("\n 任职开始期间未填写。");
			document.getElementById("experience_startdate"+i).focus();
			return false;
		}
		
		if(document.getElementById("experience_startdate"+i).value!= "" && !document.getElementById("experience_startdate"+i).value.match(reg)){
			alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
			document.getElementById("experience_startdate"+i).focus();
			return false;
		}
		
		if(document.getElementById("experience_enddate"+i).value!= "" && !document.getElementById("experience_enddate"+i).value.match(reg)){
			alert ("\n 日期格式不正确。\n\n正确格式：2005-04-05!");
			document.getElementById("experience_enddate"+i).focus();
			return false;
		}
	}
	return true;
}