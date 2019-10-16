function CheckForm(message)
{
	for(i=1;;i++){	
		
		if (document.getElementById("trainingInside_"+i) == null &&
			document.getElementById("trainingOutside_"+i) == null &&
			document.getElementById("studying_"+i) == null ) {
		
			break;
		}
		
		// training inside information validate		
		if (document.getElementById("trainingInside_"+i) != null) {
				
				if (document.getElementById("trainingInside_"+i).checked){
				
					return true;
				}
		}			
		
		// training outside information validate		
		if (document.getElementById("trainingOutside_"+i) != null) {
				
				if (document.getElementById("trainingOutside_"+i).checked){
				
					return true;
				}
		}	
		
		// studying validate		
		/*
		if (document.getElementById("studying_"+i) != null) {
				
				if (document.getElementById("studying_"+i).checked){
				
					return true;
				}
		}	*/
		
	}
	//"请选择要删除的信息"
	alert(message);
	return false;
}