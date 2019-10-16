function CheckForm()
{
	for(i=1;;i++){	
		
		if (document.getElementById("reward_"+i) == null) {
		
			break;
		}
		
		// reward information validate		
		if (document.getElementById("reward_"+i) != null) {
				
				if (document.getElementById("reward_"+i).checked){
				
					return true;
				}
		}			
		
	}

	alert("请选择要删除的信息");
	return false;
}