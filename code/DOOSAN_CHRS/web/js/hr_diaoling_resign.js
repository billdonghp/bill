function CheckForm()
{
	var c = 0;
	var tags = document.getElementsByName("selectedTag");
	if (tags == null || tags.length == null){
		alert("没有做任何人的退社操作");
		return false;
	}
	
	for (var i=0; i<tags.length; i++){
		if(tags(i).checked == true){
			c++;
			var dateElem = document.getElementsByName("resign_date"+i);
			if (dateElem(0).value==null || dateElem(0).value==""){
				alert("请输入退社日期");
				dateElem(0).focus();
				return false;
			}
			var TypeElem = document.getElementsByName("resign_type"+i);
			if (TypeElem(0).value==null || TypeElem(0).value==""){
				alert("请选择退社类型");
				TypeElem(0).focus();
				return false;
			}
		}
	}
	
	if (c==0){
		alert("没有做任何人的退社操作");
		return false;
	}
	return true;
}