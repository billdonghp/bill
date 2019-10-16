function CheckForm(msg)
{
	for(i=1;;i++){	
		
		if (document.getElementById("education_"+i) == null &&
			document.getElementById("language_"+i) == null &&
			document.getElementById("ITLeve_"+i) == null ) {
			break;
		}
		
		// education information validate		
		if (document.getElementById("education_"+i) != null) {
				
				if (document.getElementById("education_"+i).checked){
				
					return true;
				}
		}			
		
		// language information validate				
		if (document.getElementById("language_"+i) != null) {
				
				if (document.getElementById("language_"+i).checked){

					return true;
				}
		}			
		
		// IT level information validate		
		if (document.getElementById("ITLeve_"+i) != null) {
		
				if (document.getElementById("ITLeve_"+i).checked){

					return true;
				}
		}
		
	}

	alert(msg);          
	return false;
}