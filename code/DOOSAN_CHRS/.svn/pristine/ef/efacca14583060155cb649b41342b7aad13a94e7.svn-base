function CheckForm(array)
{            

	for(i=1;;i++){	
		
			if (document.getElementById("qualificationNameCode_"+i) == null &&
				document.getElementById("prevCpnyName_@"+i) == null) {
			
				break;
			}
			
			// qualification information validate		
			if (document.getElementById("qualificationNameCode_"+i) != null) {
					
					if (document.getElementById("qualificationNameCode_"+i).value == ""){
					//"资格证名称为必输项"
						alert (array[0]);
						document.getElementById("qualificationNameCode_"+i).focus();
						return false;
					}
			}
			
			// experience information validate		
			if (document.getElementById("prevCpnyName_@"+i) != null) {
					
					if (document.getElementById("startDate_@"+i).value == ""){
					//"开始日期为必输项"
						alert (array[1]);
						document.getElementById("startDate_@"+i).focus();
						return false;
					}
			}
		}
	
	// call validateRept function in "commFuncs.js" file 
	//"资格证名称不可以重复"
	if(!validateRept("qualificationNameCode", "_", array[2]))
		return false;
	
	// call validateRept function in "commFuncs.js" file 
	//"开始日期不可以重复"
	return validateRept("startDate", "_@", array[3]);
	
}