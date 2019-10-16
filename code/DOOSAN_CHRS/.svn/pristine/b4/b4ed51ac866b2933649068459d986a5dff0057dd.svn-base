// JavaScript Document
function changeTime(type,where,id,lock)
{


var time=document.all[id].value;


if(lock=="Y")
{
	alert("此项不允许做任何变更");
	return time;
}
if(time.length!=5)
{
	if(parseInt(time.substring(0,time.indexOf(":")))<10&&time.substring(0,time.indexOf(":")).length==1)
	{
		time="0"+time;
	}
	if(parseInt(time.substring(time.indexOf(":")+1,5))<10&&time.substring(time.indexOf(":")+1,5).length==1)
	{
		var temph=time.substring(0,time.indexOf(":"));
		var tempm=time.substring(time.indexOf(":")+1,5);
		time=temph+":0"+tempm;
	}
}
var hours=time.substring(0,2);
var minute=time.substring(3,5);
if(hours.indexOf('0')!=-1&&parseInt(hours)<10)
{
	hours=hours.substring(1,2);
}
if(minute.indexOf('0')!=-1&&parseInt(minute)<10)
{
	minute=minute.substring(1,2);
}
var temp;
	if(type==1)//改变小时
	{
		if(where==1)//增加
		{
			if(parseInt(hours)<23)
			{
				hours=parseInt(hours)+parseInt(1);
				if(parseInt(hours)<10){
				hours="0"+hours;
				}
				if(parseInt(minute)<10){
				minute="0"+minute;
				}
				temp=hours+":"+minute;
				document.all[id].value=temp;
				return;
			}
			if(parseInt(hours)==23)
			{
				if(parseInt(minute)<10){
				minute="0"+minute;
				}
				temp="00:"+minute;
				document.all[id].value=temp;
				return;
			}
		}
		if(where==2)//减少
		{
			if(parseInt(hours)>0)
			{
				hours=parseInt(hours)-parseInt(1);
				
				if(parseInt(hours)<10){
				hours="0"+hours;
				}
				if(parseInt(minute)<10){
				minute="0"+minute;
				}
				temp=hours+":"+minute;
				document.all[id].value=temp;
				return;
			}
			if(parseInt(hours)==0)
			{
				if(parseInt(minute)<10){
				minute="0"+minute;
				}
				temp="23:"+minute;
				document.all[id].value=temp;
				return;
			}
		}
	}
	if(type==2)//改变分钟
	{
		if(where==1)//增加
		{
			if(parseInt(minute)<59)
			{
				minute=parseInt(minute)+parseInt(1);
				if(parseInt(hours)<10){
				hours="0"+hours;
				}
				if(parseInt(minute)<10){
				minute="0"+minute;
				}
				temp=hours+":"+minute;
				document.all[id].value=temp;
				return;
			}
			if(parseInt(minute)==59)
			{
				hours=changeHours(hours,1,1);
				temp=hours+":00";
				document.all[id].value=temp;
				return;
			}
		}
		if(where==2)//减少
		{
			if(parseInt(minute)>0)
			{	
				minute=parseInt(minute)-parseInt(1);
				if(parseInt(hours)<10){
				hours="0"+hours;
				}
				if(parseInt(minute)<10){
				minute="0"+minute;
				}
				temp=hours+":"+minute;
				document.all[id].value=temp;
				return;
			}
			if(parseInt(minute)==0)
			{
				hours=changeHours(hours,2,1);
				temp=hours+":59";
				document.all[id].value=temp;
				return;
			}
		}
		
	}
}
function changeHours(hours,type,i)
{
var hours=hours;
if(hours.indexOf('0')!=-1&&parseInt(hours)<10)
{
	hours=hours.substring(1,2);
}

if(type==1)//加
{
	if(parseInt(hours)<23)
	{
		hours=parseInt(hours)+parseInt(1);
		if(parseInt(hours)<10){
		hours="0"+hours;
		}
		return hours;
	}
	if(parseInt(hours)==23)
	{
		return "00";
	}
}
if(type==2)//减
{
	if(parseInt(hours)>0)
	{
		hours=parseInt(hours)-parseInt(1);
		if(parseInt(hours)<10){
		hours="0"+hours;
		}
		return hours;
	}
	if(parseInt(hours)==0)
	{
		return "23";
	}
}
}