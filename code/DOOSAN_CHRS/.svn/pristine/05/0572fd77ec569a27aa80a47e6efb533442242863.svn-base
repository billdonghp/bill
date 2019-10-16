function CheckForm(message)
{
	for(i=1;;i++){	
		
		if (document.getElementById("qualification_"+i) == null &&
			document.getElementById("experience_"+i) == null) {
		
			break;
		}
		
		// qualification information validate		
		if (document.getElementById("qualification_"+i) != null) {
				
				if (document.getElementById("qualification_"+i).checked){
				
					return true;
				}
		}			
		
		// experience information validate		
		if (document.getElementById("experience_"+i) != null) {
				
				if (document.getElementById("experience_"+i).checked){
				
					return true;
				}
		}	
		
	}
	//"请选择要删除的信息"
	alert(message);
	return false;
}