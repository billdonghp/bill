function CheckForm(message)
{
var temp;
for(i=1;i<=3;i++){	

		// ev information validate
		if ( document.getElementById("evPeriod_"+i).value == ""&&
		    document.getElementById("firMark_"+i).value == "" &&
			 document.getElementById("firOpinion_"+i).value == "" &&
			 document.getElementById("secMark_"+i).value == "" &&
			 document.getElementById("secOpinion_"+i).value == "" &&
			 document.getElementById("evMark_"+i).value == "" &&
			 document.getElementById("evGrade_"+i).value == ""         
			 ) {
			 if(document.getElementById("evSign").value=="")
			 	temp = i;        
			 else 
			 	temp = "," + i;
		     document.getElementById("evSign").value += temp;
			                                     
		} else { 
						
				if (document.getElementById("evPeriod_"+i).value == ""){          
					alert (message);
					document.getElementById("evPeriod_"+i).focus();     
					initData();
					return false;
				}   
					   
		}
					
	}
	
	return true;         
}

function initData() {

	document.getElementById("evSign").value = "";
}