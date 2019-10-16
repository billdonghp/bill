
function CheckForm() {
//strScreenGrantNo = document.EM2Form.screenGrantNo.value;
//	if (strScreenGrantNo=="" )
//	{
//		alert("请输入权限组号！");
//		document.EM2Form.screenGrantNo.focus();
//		return false;
//	}
//	if (strScreenGrantNo.length >20 )
//	{
//		alert("您输入的权限组号太长，请在20个字符以内！");
//		document.EM2Form.screenGrantNo.focus();
//		return false;
//	}
//
//strScreenGrantName = document.EM2Form.screenGrantName.value;
//	if (strScreenGrantName=="" )
//	{
//		alert("请输入权限组描述！");
//		document.EM2Form.screenGrantName.focus();
//		return false;
//	}
//	if (strScreenGrantName.length >50 )
//	{
//		alert("您输入的权限组描述太长，请在50个字符以内！");
//		document.EM2Form.screenGrantName.focus();
//		return false;
//	}
//	return true;
//}


function reValue() {

for (var i=0;i<document.EM2Form.ReValue.length;i++)
    {
	if (document.EM2Form.ReValue[i].checked){
		document.EM2Form.ReValue_r.value=document.EM2Form.ReValue.value+","+document.EM2Form.ReValue[i].value;
	}
	}
	for (var i=0;i<document.EM2Form.contentValue.length;i++)
    {
	if (document.EM2Form.contentValue[i].checked){
		document.EM2Form.content_r.value=document.EM2Form.contentValue.value+","+document.EM2Form.contentValue[i].value;
	}
	}
	//alert("ReValue_r---"+document.EM2Form.ReValue_r);
	//alert("content_r---"+document.EM2Form.content_r);
document.EM2Form.submit();
}
}
