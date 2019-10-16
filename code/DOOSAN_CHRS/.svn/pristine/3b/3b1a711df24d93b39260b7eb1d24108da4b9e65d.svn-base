function CheckForm(dateMessage,redateMessage)
{
		for(i=1;i<=3;i++){	      

		// family information validate
		if ( document.getElementById("physicalDate_"+i).value == "" &&
			 document.getElementById("height_"+i).value == "" &&
			 document.getElementById("weight_"+i).value == "" &&
			 document.getElementById("bloodTypeCode_"+i).value == "" &&
			 document.getElementById("leftEyesight_"+i).value == "" &&  
			 document.getElementById("rightEyesight_"+i).value == "" &&
			 document.getElementById("colorBlind_"+i).value == "" &&
			 document.getElementById("bloodPressureMax_"+i).value == "" &&
			 document.getElementById("bloodPressureMix_"+i).value == "" &&
			 document.getElementById("content_"+i).value == "" ) {
			 // add identifier of null for the row
			 if(document.getElementById("healthSign").value=="")
			 	temp = i;  
			 else                                               
			 	temp = "," + i;  
		     document.getElementById("healthSign").value += temp;  
			                  
		} else {
						
				if (document.getElementById("physicalDate_"+i).value == ""){
				//体检日期为必输项
					alert (dateMessage);
					document.getElementById("physicalDate_"+i).focus();    
					initData(); 
					return false;
				}
					
		}
					
	}
	//体检日期不可以重复
	if(!validateRept("physicalDate", "_", redateMessage, "exist")){
		
		initData();
		return false;
	} else {
		
		return true;
	}
	
}

function initData() {

	document.getElementById("healthSign").value = "";
}