function CheckForm(message)
{
for(i=1;i<=3;i++){	

		// append information validate
		if ( document.getElementById("eventDate_"+i).value == "" &&
			 document.getElementById("infoTypeCode_"+i).value == "" &&
			 document.getElementById("detatls_"+i).value == "" ) {   
			
			 // add identifier of null for the row
			 if(document.getElementById("appendSign").value=="")
			 	temp = i;
			 else 
			 	temp = "," + i;
		     document.getElementById("appendSign").value += temp;
			
		} else {
						
				if (document.getElementById("eventDate_"+i).value == ""){
					alert(message);
					document.getElementById("eventDate_"+i).focus();
					initData();
					return false;
				}
					
		}
					
	}
	
	return true;
}

function initData() {

	document.getElementById("appendSign").value = "";
}        