function CheckForm()
{
		for(i=1;i<=3;i++){	

		// family information validate
		if ( document.getElementById("rewardDate_"+i).value == "" &&
			 document.getElementById("rewardTypeCode_"+i).value == "" &&
			 document.getElementById("rewardSortCode_"+i).value == "" &&
			 document.getElementById("rewardAmount_"+i).value == "" &&
			 document.getElementById("achievementTypeCode_"+i).value == "" &&
			 document.getElementById("rewardContents_"+i).value == "" &&
			 document.getElementById("rewardBonus_"+i).value == "" ) {
			
			 // add identifier of null for the row
			 if(document.getElementById("rewardSign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("rewardSign").value += temp;
			
		} else {
						
				if (document.getElementById("rewardDate_"+i).value == ""){
				
					alert ("奖励日期为必输项");
					document.getElementById("rewardDate_"+i).focus();
					initData();
					return false;
				}
					
		}
					
	}
	
	// call validateRept function in "commFuncs.js" file 
	if(!validateRept("rewardDate", "_", "奖励日期不可以重复", "exist")){
		
		initData();
		return false;
	} else {
		
		return true;
	}
	
	
}

function initData() {

	document.getElementById("rewardSign").value = "";
}