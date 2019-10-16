function CheckForm(message)
{
for(i=1;i<=3;i++){	

		// other qualification validate
		if ( document.getElementById("qualificationNameCode_"+i).value == "" &&
			 document.getElementById("qualificationCardNo_"+i).value == "" &&
			 document.getElementById("qualificationGrade_"+i).value == "" &&
			 document.getElementById("qualificationLocation_"+i).value == "" &&
			 document.getElementById("obtainedDate_"+i).value == "" ) {
			
			 // add identifier of null for the row
			 if(document.getElementById("qualificationSign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("qualificationSign").value += temp;
			
		} else {
						
				if (document.getElementById("qualificationNameCode_"+i).value == ""){
				//"资格证名称为必输项"
					alert (message[0]);
					document.getElementById("qualificationNameCode_"+i).focus();
					initData();
					return false;
				}
					
		}

		// other experience validate
		if ( document.getElementById("prevCpnyName_"+i).value == "" &&
			 document.getElementById("startDate_"+i).value == "" &&
			 document.getElementById("endDate_"+i).value == "" &&
			 document.getElementById("prevDeptName_"+i).value == "" &&
			 document.getElementById("prevPosition_"+i).value == "" &&
			 document.getElementById("leaveReason_"+i).value == "" ) {

			 // add identifier of null for the row
			 if(document.getElementById("experienceSign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("experienceSign").value += temp;
			
		} else {
						
				if (document.getElementById("startDate_"+i).value == ""){
				//"开始日期为必输项"
					alert (message[1]);
					document.getElementById("startDate_"+i).focus();
					initData();
					return false;
				}
			
		}
					
	}
	
	// call validateRept function in "commFuncs.js" file 
	//"资格证名称不可以重复"
	if(!validateRept("qualificationNameCode", "_",message[2] ,"exist")) {
		
		initData();
		return false;
	}
	
	// call validateRept function in "commFuncs.js" file 
	//"开始日期不可以重复"
	if(!validateRept("startDate", "_",message[3] , "exist")){
		
		initData();
		return false;
	} else {
		
		return true;
	}
}

function initData() {

	document.getElementById("experienceSign").value = "";
	document.getElementById("qualificationSign").value = "";
}
