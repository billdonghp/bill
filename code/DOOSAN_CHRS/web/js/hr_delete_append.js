function CheckForm(message)
{
	for(i=1;;i++){	
		
		if (document.getElementById("append_"+i) == null) {
		
			break;
		}
		
		// append information validate		
		if (document.getElementById("append_"+i) != null) {
				
				if (document.getElementById("append_"+i).checked){
				
					return true;
				}
		}			
		
	}
	//"请选择要删除的信息"
	alert(message);
	return false;
}