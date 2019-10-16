

//elemName checkbox elements index checked true
function setCheckboxChecked(elemName,index){
  var ckElems = document.getElementsByName(elemName);
  if (ckElems != null && ckElems.length != null &&
    index >=0 && index < ckElems.length){
    ckElems(index).checked=true;
  }
}

//dateStr
//dateStr YYYY-MM-DD
//YYYY-MM-DD
function addMonth(dateStr,offset){
  var year = parseInt(dateStr.substring(0,4));
  var month = dateStr.substring(5,7);
  if (month.substring(0,1)=='0')
  	month = parseInt(month.substring(1,2));
  else
    month = parseInt(month);
  var day = dateStr.substring(8,10);
  year = (month+offset>12? year+Math.floor((month+offset)/12):year).toString();
  month = month+offset>12?(month+offset)%12:month+offset;
  if (month<10)
    month = '0'+month.toString();
  else
    month = month.toString();
  return year + '-' + month + '-' + day;
}




// validate repetition
function validateRept(name, separator, message)
{
	validateRept(name, separator, message, null);
}

// validate repetition
function validateRept(name, separator, message, specialSeptr)
{
	var arrayData = getArray(name, separator);
	var existData = null;
	
	// get exist data
	if(specialSeptr != null)
		existData = getArray(name, separator+specialSeptr);

	if(arrayData == null || arrayData.length == 0)
		return true;

	for(i=1;i<=arrayData.length;i++){

		if(document.getElementById(name + separator + i).value == "")
			continue;
						
		// delete current element
		var tempArray = arrayData.del(i-1);

		// search index in other array element
		var result = searchIndex(tempArray, document.getElementById(name + separator + i).value);

		if (result == -1 && existData != null && existData.length != 0) {
			
			result = searchIndex(existData, document.getElementById(name + separator + i).value);
		}

		if( result > -1) {
			
			document.getElementById(name + separator + i).focus();
			alert(message);
			return false;
		}
		
	}
	
	return true;
}

// get array in form
function getArray(name, separator)
{
	var arrayData = new Array();
	for(i=1;;i++){
		
		if(document.getElementById(name + separator + i) == null)
			break;
		arrayData[i-1] = document.getElementById(name + separator + i).value;
	}
	return arrayData;
}

// search index of key in array 
function searchIndex(arrayData, key)       
{
  var reg = new RegExp(key,[""]);
  var strData = arrayData.toString().replace(reg,"┢").replace(/[^,┢]/g,"");

  return strData.indexOf("┢");

}

//n表示第几项,从0开始算起
Array.prototype.del=function(n) {  
  
  //prototype为对象原型,注意这里为对象增加自定义方法的方法
  if(n<0)  //如果n<0,则不进行任何操作
    return this;
  else
    return this.slice(0,n).concat(this.slice(n+1,this.length));
    /*
      concat方法：返回一个新数组，这个新数组是由两个或更多数组组合而成的。
      　　　　　　这里就是返回this.slice(0,n)/this.slice(n+1,this.length)
     　　　　　　组成的新数组，这中间，刚好少了第n项。
      slice方法： 返回一个数组的一段，两个参数，分别指定开始和结束的位置。
    */
}

function efocus(event) {
    var elem = Event.element(event);
    elem.style.border = '1 solid #9e9e9e';
}

function eblur(event) {
    var elem = Event.element(event);
    elem.style.border = '1px solid #cccccc';
}

function ekeydown(event) {
    if (event.keyCode == Event.KEY_RETURN)
        event.keyCode = Event.KEY_TAB;
}