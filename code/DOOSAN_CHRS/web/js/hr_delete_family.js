function CheckForm(message)
{
	for(i=1;;i++){	
		
		if (document.getElementById("family_"+i) == null) {
		
			break;
		}
		
		// family information validate		
		if (document.getElementById("family_"+i) != null) {
				
				if (document.getElementById("family_"+i).checked){
				
					return true;
				}
		}			
		
	}

	// military service information validate
	if ( document.getElementById("military") != null ) {

		 if (document.getElementById("military").checked) {

		 	return true;
		 } 
	} 
	
	alert(message);
	return false;
}