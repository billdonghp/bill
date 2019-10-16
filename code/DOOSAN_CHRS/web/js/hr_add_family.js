function CheckForm(msg)
{
for(i=1;i<=3;i++){	
		
		// family information validate
		if ( document.getElementById("relationalTypeCode_"+i).value == "" &&
			 document.getElementById("famName_"+i).value == "" &&
			 document.getElementById("famBornDate_"+i).value == "" &&
			 document.getElementById("famOccupationCode_"+i).value == "" &&
			 document.getElementById("famIDCard_"+i).value == "" &&
			 document.getElementById("famDegreeCode_"+i).value == "") {
			
			 // add identifier of null for the row
			 if(document.getElementById("familySign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("familySign").value += temp;
			
		} else {
						
				if (document.getElementById("relationalTypeCode_"+i).value == ""){
				
					alert (msg[0]);
					document.getElementById("relationalTypeCode_"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("famName_"+i).value == ""){
				
					alert (msg[1]);
					document.getElementById("famName_"+i).focus();
					initData();
					return false;
				}
				/*
				if (document.getElementById("famBornDate_"+i).value == ""){
				
					alert ("出生日期为必输项");
					document.getElementById("famBornDate_"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("famOccupationCode_"+i).value == ""){
				
					alert ("职业为必输项");
					document.getElementById("famOccupationCode_"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("famIDCard_"+i).value == ""){
				
					alert ("身份证为必输项");
					document.getElementById("famIDCard_"+i).focus();
					initData();
					return false;
				}
				if (document.getElementById("famDegreeCode_"+i).value == ""){
				
					alert ("学历为必输项");
					document.getElementById("famDegreeCode_"+i).focus();
					initData();
					return false;
				} */
					
		}
					
}

	// military service information hava exist
	if (document.getElementById("militaryTypeCode") == null) {
	
		document.getElementById("militarySign").value = -1;
		return true;
	}
	
	// military service information validate
	if ( document.getElementById("militaryTypeCode").value == "" &&
		 document.getElementById("militaryLevelCode").value == "" &&
		 document.getElementById("militaryAreaCode").value == "" &&
		 document.getElementById("startTime").value == "" &&
		 document.getElementById("endTime").value == "") {
		
		 // add identifier of null for the row
	     document.getElementById("militarySign").value = 1;

	} else {
					
			if (document.getElementById("militaryTypeCode").value == ""){
			
				alert (msg[2]);
				document.getElementById("militaryTypeCode").focus();
				initData();
				return false;
			}
			if (document.getElementById("militaryLevelCode").value == ""){
			
				alert (msg[3]);
				document.getElementById("militaryLevelCode").focus();
				initData();
				return false;
			}
			if (document.getElementById("militaryAreaCode").value == ""){
			
				alert (msg[4]);
				document.getElementById("militaryAreaCode").focus();
				initData();
				return false;
			}
			if (document.getElementById("startTime").value == ""){
			
				alert (msg[5]);
				document.getElementById("startTime").focus();
				initData();
				return false;
			}
			if (document.getElementById("endTime").value == ""){
			
				alert (msg[6]);
				document.getElementById("endTime").focus();
				initData();
				return false;
			}
				  
	}
		
	// call validateRept function in "commFuncs.js" file 
	if(!validateRept("famName", "_", msg[7], "exist")){
		
		initData();
		return false;
	} else {
		
		return true;
	}
}

function initData() {

	document.getElementById("familySign").value = "";
	document.getElementById("militarySign").value = "";
}