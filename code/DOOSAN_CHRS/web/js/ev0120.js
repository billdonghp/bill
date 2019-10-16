function CheckForm() {
strEva_Grade=document.EM2Form.eva_grade.value;
	if(strEva_Grade=="")
	{
		alert("请输入评价等级！");
		document.EM2Form.eva_grade.focus();
		return false;
	}
	if(strEva_Grade.length>2)
	{
		alert("您输入的评价等级太长，请在2个字符以内！");
		document.EM2Form.eva_grade.focus();
		return false;
	}

/*
strEva_Content_No=document.EM2Form.eva_content_no.value;
	if(strEva_Content_No=="")
	{
		alert("请输入评价内容号！");
		document.EM2Form.eva_content_no.focus();
		return false;
	}
	if(strEva_Content_No.length>10)
	{
		alert("您输入的评价内容号太长，请在10个字符以内！");
		document.EM2Form.eva_content_no.focus();
		return false;
	}
number = new Number(strEva_Content_No);
   if (isNaN(number))
   {
	   alert("您所输入包含非数字字符，请输入数字！");
	   document.EM2Form.eva_content_no.focus();
      return false;
   }

*/

strEva_Grade_Standard=document.EM2Form.eva_grade_standard.value;
    if(strEva_Grade_Standard=="")
	{
		alert("请输入等级评价基准！");
		document.EM2Form.eva_grade_standard.focus();
		return false;
	}
	if(strEva_Grade_Standard.length>100)
	{
		alert("您输入的等级评价基准太长，请在100个字符以内！");
		document.EM2Form.eva_grade_standard.focus();
		return false;
	}
	
		return true;

}


