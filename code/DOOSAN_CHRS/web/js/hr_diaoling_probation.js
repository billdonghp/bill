function CheckForm(){   
	var c = 0;
	var tags = document.getElementsByName("selectedTag");
	if (tags == null || tags.length == null){
		alert("没有做任何人的转正操作");
		return false;
	}
	
	for (var i=0; i<tags.length; i++){
		if(tags(i).checked == true){
			c++;
			var dateElem = document.getElementsByName("enddate"+i);
			if (dateElem(0).value==null || dateElem(0).value==""){
				alert("请输入转正日期");
				dateElem(0).focus();
				return false;
			}
			var markElem = document.getElementsByName("probation_mark"+i);
			if(markElem(0).value!=null && markElem(0).value!="" && isNaN(parseFloat(markElem(0).value))){
				alert("考核分数必须为数值");
				markElem(0).focus();
				return false;
			}
		}
	}
	
	if (c==0){
		alert("没有做任何人的转正操作");
		return false;
	}
	
	return true;
}
