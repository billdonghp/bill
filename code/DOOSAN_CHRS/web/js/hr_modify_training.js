function CheckForm(name,reDateM)
{            

	for(i=1;;i++){	
		
			if (document.getElementById("courseCode_"+i) == null &&
				document.getElementById("course_@"+i) == null &&
				document.getElementById("researchTypeCode_%"+i) == null &&
				document.getElementById("studyTypeCode_%"+i) == null) {
			
				break;
			}
			
			// training inside information validate		
			if (document.getElementById("courseCode_"+i) != null) {
					
					if (document.getElementById("courseCode_"+i).value == ""){
					//"课程名称为必输项"
						alert (name);
						document.getElementById("courseCode_"+i).focus();
						return false;
					}
			}
			
			// training outside information validate		
			if (document.getElementById("course_@"+i) != null) {
					
					if (document.getElementById("course_@"+i).value == ""){
					//"课程名称为必输项"
						alert (name);
						document.getElementById("course_@"+i).focus();
						return false;
					}
			}
			
			
			// research study  validate		
			/*
			if (document.getElementById("researchTypeCode_%"+i) != null) {
					
					if (document.getElementById("researchTypeCode_%"+i).value == ""){
					
						alert ("研修类型为必选项");
						document.getElementById("researchTypeCode_%"+i).focus();
						return false;
					}
					
					if (document.getElementById("startDate_%"+i).value == ""){
					
						alert ("开始日期为必输项");
						document.getElementById("startDate_%"+i).focus();
						return false;
					}
			}*/
			
			// study abroad  validate	
			/*	
			if (document.getElementById("studyTypeCode_%"+i) != null) {
					
					if (document.getElementById("studyTypeCode_%"+i).value == ""){
					
						alert ("留学名称为必选项");
						document.getElementById("studyTypeCode_%"+i).focus();
						return false;
					}
					if (document.getElementById("startDate_%"+i).value == ""){
					
						alert ("开始日期为必输项");
						document.getElementById("startDate_%"+i).focus();
						return false;
					}
			}*/
			
		}
	//"开始日期不可以重复"
	// call validateRept function in "commFuncs.js" file 
	if(!validateRept("startDate", "_", reDateM))
		return false;
	
	// call validateRept function in "commFuncs.js" file 
	if(!validateRept("startDate", "_@", reDateM))
		return false;
	
	// call validateRept function in "commFuncs.js" file 
	return validateRept("startDate", "_%", reDateM);
	
}