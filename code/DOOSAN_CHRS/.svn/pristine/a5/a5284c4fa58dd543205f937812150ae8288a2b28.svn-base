function CheckForm()
{
	var c = 0;
	var tags = document.getElementsByName("selectedTag");
	if (tags == null || tags.length == null){
		alert("没有做任何人的奖励操作");
		return false;
	}
	
	for (var i=0; i<tags.length; i++){
		if(tags(i).checked == true){
			c++;
			var typeElem = document.getElementsByName("reward_type"+i);
			if (typeElem(0).value==null || typeElem(0).value==""){
				alert("请输入奖励类型");
				typeElem(0).focus();
				return false;
			}
			var dateElem = document.getElementsByName("reward_date"+i);
			if(dateElem(0).value==null || dateElem(0).value==""){
				alert("请输入奖励日期");
				dateElem(0).focus();
				return false;
			}
			var contentsElem = document.getElementsByName("reward_contents"+i);
			if(contentsElem(0).value==null || contentsElem(0).value==""){
				alert("请输入奖励原因");
				contentsElem(0).focus();
				return false;
			}
		}
	}
	
	if (c==0){
		alert("没有做任何人的奖励操作");
		return false;
	}
	
	return true;
}