function CheckForm(name,reDateM)
{
	for(i=1;i<=3;i++){	

		// training inside validate
		if ( document.getElementById("courseCode_"+i).value == "" &&
			 document.getElementById("courseTypeCode_"+i).value == "" &&
			 document.getElementById("startDate_"+i).value == "" &&
			 document.getElementById("endDate_"+i).value == "" &&
			 document.getElementById("mark_"+i).value == "" &&
			 document.getElementById("place_"+i).value == "" ) {
			
			 // add identifier of null for the row
			 if(document.getElementById("insideSign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("insideSign").value += temp;
			
		} else {
						
				if (document.getElementById("courseCode_"+i).value == ""){
					//"课程名称为必输项"
					alert (name);
					document.getElementById("courseCode_"+i).focus();
					initData();
					return false;
				}
					
		}

		// training outside validate
		if ( document.getElementById("course_@"+i).value == "" &&
			 document.getElementById("courseTypeCode_@"+i).value == "" &&
			 document.getElementById("startDate_@"+i).value == "" &&
			 document.getElementById("endDate_@"+i).value == "" &&
			 document.getElementById("institutionName_@"+i).value == "" &&
			 document.getElementById("mark_@"+i).value == "" &&
			 document.getElementById("place_@"+i).value == "" ) {

			 // add identifier of null for the row
			 if(document.getElementById("outsideSign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("outsideSign").value += temp;
			
		} else {
						
				if (document.getElementById("course_@"+i).value == ""){
					//"课程名称为必输项"
					alert (name);
					document.getElementById("course_@"+i).focus();
					initData();
					return false;
				}
			
		}
			
		// research study validate
		/*
		if ( document.getElementById("researchTypeCode_%"+i).value == "" &&
			 document.getElementById("startDate_%"+i).value == "" &&
			 document.getElementById("endDate_%"+i).value == "" &&
			 document.getElementById("countryCode_%"+i).value == "" &&
			 document.getElementById("institutionName_%"+i).value == "" &&
			 document.getElementById("studyContents_%"+i).value == "" &&
			 document.getElementById("period_%"+i).value == "" &&
			 document.getElementById("expense_%"+i).value == "" &&
			 document.getElementById("transNo_%"+i).value == "" ) {
			  
			 // add identifier of null for the row
			 if(document.getElementById("studyingSign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("studyingSign").value += temp;
		} else {
		
			if (document.getElementById("researchTypeCode_%"+i).value == ""){
			
				alert ("研修类型为必选项");
				document.getElementById("researchTypeCode_%"+i).focus();
				initData();
				return false;
			}
			
			if (document.getElementById("startDate_%"+i).value == ""){
			
				alert ("研修开始日期为必输项");
				document.getElementById("startDate_%"+i).focus();
				initData();
				return false;
			}
		}  */
	}
	/*
	for(i=4;i<=6;i++){
	
		//  study abroad validate
		if ( document.getElementById("studyTypeCode_%"+i).value == "" &&
			 document.getElementById("startDate_%"+i).value == "" &&
			 document.getElementById("endDate_%"+i).value == "" &&
			 document.getElementById("countryCode_%"+i).value == "" &&
			 document.getElementById("institutionName_%"+i).value == "" &&
			 document.getElementById("subjectCode_%"+i).value == "" &&
			 document.getElementById("period_%"+i).value == "" &&
			 document.getElementById("expense_%"+i).value == "" &&
			 document.getElementById("transNo_%"+i).value == "" ) {
			  
			 // add identifier of null for the row
			 if(document.getElementById("studyingSign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("studyingSign").value += temp;
		} else {
		
			if (document.getElementById("studyTypeCode_%"+i).value == ""){
			
				alert ("留学名类型为必选项");
				document.getElementById("studyTypeCode_%"+i).focus();
				initData();
				return false;
			}
			
			if (document.getElementById("startDate_%"+i).value == ""){
			
				alert ("留学开始日期为必输项");
				document.getElementById("startDate_%"+i).focus();
				initData();
				return false;
			}
		}
	}*/
	//"开始日期不可以重复"
	// call validateRept function in "commFuncs.js" file 
	if(!validateRept("startDate", "_",reDateM ,"exist")) {
		
		initData();
		return false;
	}
	
	// call validateRept function in "commFuncs.js" file 
	if(!validateRept("startDate", "_%", reDateM,"exist")) {
		
		initData();
		return false;
	}
	
	// call validateRept function in "commFuncs.js" file 
	if(!validateRept("startDate", "_@", reDateM, "exist")){
		
		initData();
		return false;
	} else {
		
		return true;
	}

}

function initData() {

	document.getElementById("insideSign").value = "";
	document.getElementById("outsideSign").value = "";
	//document.getElementById("studyingSign").value = "";
	}
