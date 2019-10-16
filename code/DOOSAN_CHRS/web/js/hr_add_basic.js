function CheckForm(array)
{
	for(i=1;i<=3;i++){	
		
		// education information validate
		if ( document.getElementById("institutionName_"+i).value == "" &&
			 document.getElementById("subjectCode_"+i).value == "" &&
			 document.getElementById("startDate_"+i).value == "" &&
			 document.getElementById("endDate_"+i).value == "" &&
			 document.getElementById("degreeCode_"+i).value == "") {
			
			 // add identifier of null for the row
			 if(document.getElementById("educationSign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("educationSign").value += temp;
			
		} else {
						
				if (document.getElementById("institutionName_"+i).value == ""){
				//"学校名为必输项"
					alert (array[0]);
					document.getElementById("institutionName_"+i).focus();
					initData();
					return false;
				}
				/*if (document.getElementById("subjectCode_"+i).value == ""){
				
					alert ("专业为必输项");
					document.getElementById("subjectCode_"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("startDate_"+i).value == ""){
				
					alert ("入学日期为必输项");
					document.getElementById("startDate_"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("endDate_"+i).value == ""){
				
					alert ("毕业日期为必输项");
					document.getElementById("endDate_"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("degreeCode_"+i).value == ""){
				
					alert ("学历为必输项");
					document.getElementById("degreeCode_"+i).focus();
					initData();
					return false;
				}*/
					
		}
		
		// language information validate
		if ( document.getElementById("languageTypeCode_"+i).value == "" &&
			 document.getElementById("examNameCode_"+i).value == "" &&
			 document.getElementById("languageLevelCode_"+i).value == "" &&
			 document.getElementById("mark_"+i).value == "" &&
			 document.getElementById("obtainedDate_"+i).value == "") {
			
			 // add identifier of null for the row
			 if(document.getElementById("languageSign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("languageSign").value += temp;
			
		} else {
						
				if (document.getElementById("languageTypeCode_"+i).value == ""){
				//"语言类型为必输项"
					alert (array[1]);
					document.getElementById("languageTypeCode_"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("examNameCode_"+i).value == ""){
				//"考试名称为必输项"
					alert (array[2]);
					document.getElementById("examNameCode_"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("languageLevelCode_"+i).value == ""){
				//"语言等级为必输项"
					alert (array[3]);
					document.getElementById("languageLevelCode_"+i).focus();
					initData();
					return false;
				}
				/*
				if (document.getElementById("mark_"+i).value == ""){
				
					alert ("分数为必输项");
					document.getElementById("mark_"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("obtainedDate_"+i).value == ""){
				
					alert ("取证日期为必输项");
					document.getElementById("obtainedDate_"+i).focus();
					initData();
					return false;
				}
				*/
					
		}
		
		// IT level information validate
		if ( document.getElementById("itExamNameCode_@"+i).value == "" &&
			 document.getElementById("itLevelCode_@"+i).value == "" &&
			 document.getElementById("itMark_@"+i).value == "" &&
			 document.getElementById("obtainedDate_@"+i).value == "" ) {
			
			 // add identifier of null for the row
			 if(document.getElementById("ITLeveSign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("ITLeveSign").value += temp;
			
		} else {
						
				if (document.getElementById("itExamNameCode_@"+i).value == ""){
				//"IT考试名为必输项"
					alert (array[4]);
					document.getElementById("itExamNameCode_@"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("itLevelCode_@"+i).value == ""){
				//"IT等级为必输项"
					alert (array[5]);
					document.getElementById("itLevelCode_@"+i).focus();
					initData();
					return false;
				}
				/*
				if (document.getElementById("itMark_@"+i).value == ""){
				
					alert ("考试分数为必输项");
					document.getElementById("itMark_@"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("obtainedDate_@"+i).value == ""){
				
					alert ("取证日期为必输项");
					document.getElementById("obtainedDate_@"+i).focus();
					initData();
					return false;
				}
				*/	
		}
		
		
	}
	
	return true;
}

function initData() {

	document.getElementById("educationSign").value = "";
	document.getElementById("languageSign").value = "";
	document.getElementById("ITLeveSign").value = "";
}