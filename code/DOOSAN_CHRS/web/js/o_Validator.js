function validateDate1(string){  
	//YYYY-MM-DD格式
	if (string == "")
	{
		return true;
	}
	var pattern = /^\d{4}\-\d{2}-\d{2}$/; 
	flag = pattern.test(string); 
	if(flag) 
	{ 
		return true; 
	} 
	else 
	{ 
		return false; 
	} 
}

function validateDate2(string){  
	//Y4位，M和D可为1或2位
	if (string == "")
	{
		return true;
	}
	var pattern = /^\d{4}\-\d{1,2}-\d{1,2}$/; 
	flag = pattern.test(string); 
	if(flag) 
	{ 
		return true; 
	} 
	else 
	{ 
		return false; 
	} 
}

function IsNumber(string,sign)
{
	//数字，不包含小数点，可包含正符号
	var number;
	if (string==null) return false;
	if ((sign!=null) && (sign!='-') && (sign!='+'))
	{
		alert('IsNumber(string,sign)的参数出错：\nsign为null或"-"或"+"');
		return false;
	}
	number = new Number(string);
	if (isNaN(number))
	{
		return false;
	}
	else if ((sign==null) || (sign=='-' && number<0) || (sign=='+' && number>0))
	{
		return true;
	}
	else
		return false;
}

function IsEmail(string)
{
	//邮箱
	if (string == "")
	{
		return true;
	}
	var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; 
	flag = pattern.test(string); 
	if(flag) 
	{ 
		return true; 
	} 
	else 
	{ 
		return false; 
	} 
}

function NumCount_4(string){  
	//4个数字
	if (string == "")
	{
		return true;
	}
	var pattern =  /^\d{4}$/; 
	if (string == "")
	{
		return true;
	}
	flag = pattern.test(string); 
	if(flag) 
	{ 
		return true; 
	} 
	else 
	{ 
		return false; 
	} 
}