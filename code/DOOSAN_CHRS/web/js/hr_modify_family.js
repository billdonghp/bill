function CheckForm(array)
{
	for(i=1;;i++){	
		
			if (document.getElementById("relationalTypeCode_"+i) == null) {
			
				break;
			}
			
			// family information validate		
			if (document.getElementById("relationalTypeCode_"+i) != null) {
					
					if (document.getElementById("relationalTypeCode_"+i).value == ""){
					//"关系为必输项"
						alert (array[0]);
						document.getElementById("relationalTypeCode_"+i).focus();
						return false;
					}
					if (document.getElementById("famName_"+i).value == ""){
					//"姓名为必输项"
						alert (array[1]);
						document.getElementById("famName_"+i).focus();
						return false;
					}
					/*
					if (document.getElementById("famBornDate_"+i).value == ""){
					
						alert ("出生日期为必输项");
						document.getElementById("famBornDate_"+i).focus();
						return false;
					}
					if (document.getElementById("famOccupationCode_"+i).value == ""){
					
						alert ("职业为必输项");
						document.getElementById("famOccupationCode_"+i).focus();
						return false;
					}
					if (document.getElementById("famIDCard_"+i).value == ""){
					
						alert ("身份证为必输项");
						document.getElementById("famIDCard_"+i).focus();
						return false;
					}
					if (document.getElementById("famDegreeCode_"+i).value == ""){
					
						alert ("学历为必输项");
						document.getElementById("famDegreeCode_"+i).focus();
						return false;
					} */
			}
		}
		if(document.getElementById("idcardNo").value == "") {
		//"身份证号为必输项"
			alert(array[2]);
			document.getElementById("idcardNo").focus();
			return false;
		}
		
		if(document.getElementById("maritalStatusCode").value == "") {
		//"婚姻状况为必输项"
			alert(array[3]);
			document.getElementById("maritalStatusCode").focus();
			return false;
		}
/*		
		if(document.getElementById("homePhone").value == "") {
		
			alert("家庭电话为必输项");
			document.getElementById("homePhone").focus();
			return false;
		}

		if(document.getElementById("email").value == "") {
		
			alert("Email为必输项");
			document.getElementById("email").focus();
			return false;
		}
*/
		if(document.getElementById("fileRelationCode").value == "") {
		//"档案关系为必输项"
			alert(array[4]);
			document.getElementById("fileRelationCode").focus();
			return false;
		}
		
		if(document.getElementById("homeAddress").value == "") {
		//"现住址为必输项"
			alert(array[5]);
			document.getElementById("homeAddress").focus();
			return false;
		}
		
		if(document.getElementById("regPlace").value == "") {
		//"户口所在地为必输项"
			alert(array[6]);
			document.getElementById("regPlace").focus();
			return false;
		}
		
		if(document.getElementById("regTypeCode").value == "") {
		//"户口性质为必输项"
			alert(array[7]);
			document.getElementById("regTypeCode").focus();
			return false;
		}
		
		// military service information validate
		if ( document.getElementById("militaryTypeCode").value == "" &&
			 document.getElementById("militaryLevelCode").value == "" &&
			 document.getElementById("militaryAreaCode").value == "" &&
			 document.getElementById("startTime").value == "" &&
			 document.getElementById("endTime").value == "" ) {
			
			 if (document.getElementById("militaryNo").value == "") {
			 	
			 	// add identifier of null for the row
			 	document.getElementById("militarySign").value = 1;
			 } else {
			 //"兵役信息为必输项"
			 	alert (array[8]);
				document.getElementById("militaryTypeCode").focus();
				return false;
			 }
	
		} else {
						
				if (document.getElementById("militaryTypeCode").value == ""){
				//"军别为必输项"
					alert (array[9]);
					document.getElementById("militaryTypeCode").focus();
					return false;
				}
				if (document.getElementById("militaryLevelCode").value == ""){
				//"军级为必输项"
					alert (array[10]);
					document.getElementById("militaryLevelCode").focus();
					return false;
				}
				if (document.getElementById("militaryAreaCode").value == ""){
				//"军区为必输项"
					alert (array[11]);
					document.getElementById("militaryAreaCode").focus();
					return false;
				}
				if (document.getElementById("startTime").value == ""){
				//"开始日期必输项"
					alert (array[12]);
					document.getElementById("startTime").focus();
					return false;
				}
				if (document.getElementById("endTime").value == ""){
				//"结束日期必输项"
					alert (array[13]);
					document.getElementById("endTime").focus();
					return false;
				}
					
		}		
		
		// call validateRept function in "commFuncs.js" file 
		//
		return validateRept("famName", "_", array[14]);
}