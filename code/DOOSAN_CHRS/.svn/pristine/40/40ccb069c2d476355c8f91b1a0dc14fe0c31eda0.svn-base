function CheckForm(msg) {
strDeptCode=document.EM2Form.deptId.value;
	if(strDeptCode=="")
	{           
		alert(msg[0]);  
		document.EM2Form.deptId.focus();
		return false;
	}
	if(strDeptCode.length >10 )
	{
		alert(msg[1]);
		document.EM2Form.deptId.focus();
		return false;
	}

strDeptName=document.EM2Form.deptName.value;
	if(strDeptName=="")
	{
		alert(msg[2]);
		document.EM2Form.deptName.focus();
		return false;
	}
	if(strDeptName.length >50 )
	{
		alert(msg[3]);
		document.EM2Form.deptName.focus();
		return false;
	}
	if(document.EM2Form.endEddate != null){
		if( compareDate(document.EM2Form.createdDate.value,document.EM2Form.endEddate.value)!=-1 && document.EM2Form.endEddate.value != "")
		{
			alert(msg[4]);
			return false;
		}
	}
	return true;
}
  /**
函数：日期比较
功能：可以比较日期，必须是格式化成yyyy-MM-dd
return -1   a<b;
return 0    a=b;
return 1    a>b;
*/
function compareDate(a,b){

var aYear = parseInt(a.substr(0,4));
var aMonth = parseFloat(a.substr(a.indexOf('-')+1,2));
var aDay = parseFloat(a.substr(a.lastIndexOf('-')+1,2));
var bYear = parseInt(b.substr(0,4));
var bMonth = parseFloat(b.substr(b.indexOf('-')+1,2));
var bDay = parseFloat(b.substr(b.lastIndexOf('-')+1,2));
if(aYear<bYear){ return -1;}
else if(aYear==bYear){
  if(aMonth<bMonth){return -1;}
  else if(aMonth==bMonth){
    if(aDay<bDay){
    return -1;}
    else if(aDay==bDay){
    return 0;}
    else{
    return 1;}
  }
  else{return 1;}
}
else {return 1;}
}

