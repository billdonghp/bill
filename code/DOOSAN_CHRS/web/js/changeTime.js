// JavaScript Document  
function changespTime(timeid,fromid,toid,dayid,fromdayid)
{
	
	var xg=parseFloat(document.all[timeid].value); 
	var tt=xg*60;
	var from=document.all[fromid].value;
	var from_hours=from.substring(0,from.indexOf(":"));
	var from_minute=from.substring(from.indexOf(":")+1,from.length);
	var to
	var to_hours
	var to_minute
	var day=document.all[dayid].value;
	var fromday=document.all[fromdayid].value;

	if(from_hours.length==2&&parseInt(from_hours)<10)
	{
		from_hours=from_hours.substring(1,2);
	}
	if(from_minute.length==2&&parseInt(from_minute)<10)
	{
		from_minute=from_minute.substring(1,2);
	}

	
	if(parseFloat(from_minute)+parseFloat(tt)<60)
	{
		to_hours=parseInt(from_hours);
		to_minute=parseFloat(parseFloat(from_minute)+parseFloat(tt));

	}
	if(parseFloat(from_minute)+parseFloat(tt)>=60)
	{
		var dyc=(parseFloat(from_minute)+parseFloat(tt))/60;
		var dys=(parseFloat(from_minute)+parseFloat(tt))%60;
		to_hours=parseInt(parseFloat(from_hours)+parseFloat(dyc));
		to_minute=parseFloat(dys)

	}
	var xz=parseFloat(parseFloat(from_hours)+parseFloat(xg)+parseFloat(parseFloat(from_minute)/60));

	if(parseFloat(xz)<24)
	{

		document.all[dayid].value=fromday;
	}
	if(parseFloat(xz)==24)
	{

		document.all[dayid].value=parseInt(parseInt(fromday)+parseInt(1));
	}
	if(parseFloat(xz)>24&&parseFloat(xz)<48)
	{

		document.all[dayid].value=parseInt(parseInt(fromday)+parseInt(1));
	}
	if(parseFloat(xz)>=48&&parseFloat(xz)<72)
	{

		document.all[dayid].value=parseInt(parseInt(fromday)+parseInt(2));
	}
	if(parseFloat(xz)>=72)
	{
		if(parseInt(day)<parseInt(parseInt(fromday)+parseInt(3)))
		{
			alert("间隔小时太大 超出范围天数");
			document.all[dayid].value="";
			return;
		}
		document.all[dayid].value=parseInt(parseInt(fromday)+parseInt(3));
	}
	if(parseInt(to_hours)==24)
	{
		

		to_hours=parseInt(to_hours)-24;
		document.all[toid].value=to_hours+":"+to_minute;
		

		return;
	}
	if(parseInt(to_hours)<24)
	{


		document.all[toid].value=to_hours+":"+to_minute;
		

		return;
	}
	if(parseInt(to_hours)>=24&&parseInt(to_hours)<48)
	{	


		to_hours=parseInt(to_hours)-24;
		document.all[toid].value=to_hours+":"+to_minute;
		
		return;
	}
	if(parseInt(to_hours)>=48&&parseInt(to_hours)<72)
	{	


		to_hours=parseInt(to_hours)-48;
		document.all[toid].value=to_hours+":"+to_minute;
		
		
		return;
	}
	if(parseInt(to_hours)>=72)
	{
		to_hours=parseInt(to_hours)-72;
		document.all[toid].value=to_hours+":"+to_minute;
		
		
		return;
	}
}

function changespDay(type,timeid,fromid,toid,dayid,fromdayid)
{

	var from=document.all[fromid].value;
	var from_hours=from.substring(0,from.indexOf(":"));
	var from_minute=from.substring(from.indexOf(":")+1,from.length);
	var to=document.all[toid].value;
	var to_hours=to.substring(0,to.indexOf(":"));
	var to_minute=to.substring(to.indexOf(":")+1,to.length);
	var jg=parseInt(document.all[dayid].value);
	var fdayid=document.all[fromdayid].value;
	if(parseInt(jg)<fdayid)
	{
		alert("结束日期不能小于开始日期");
		return;
	}
	var jgTime=parseFloat(document.all[timeid].value);
	var tempjg;
	if(from_hours.length==2&&parseInt(from_hours)<10)
	{
		from_hours=from_hours.substring(1,2);
	}
	if(to_hours.length==2&&parseInt(to_hours)<10)
	{
		to_hours=to_hours.substring(1,2);
	}
	if(from_minute.length==2&&parseInt(from_minute)<10)
	{
		from_minute=from_minute.substring(1,2);
	}
	if(to_minute.length==2&&parseInt(to_minute)<10)
	{
		to_minute=to_minute.substring(1,2);
	}
	jg=parseInt(parseInt(jg)-parseInt(fdayid));
	if(parseInt(jg)==0)
	{	
		tempjg=parseInt(to_hours)-parseInt(from_hours);
	}
	if(parseInt(jg)==1)
	{
		
		tempjg=24-parseInt(from_hours)+parseInt(to_hours);

	}
	if(parseInt(jg)==2)
	{	
		tempjg=48-parseInt(from_hours)+parseInt(to_hours);//08:00  20:00
	}
	if(parseInt(jg)==3)
	{
		tempjg=72-parseInt(from_hours)+parseInt(to_hours);
	}
	if(parseInt(tempjg)>72)
	{
		tempjg=72;
	}
	
	if(tempjg<0)
	{
		alert("结束时间不能小于开始时间");
		document.all[dayid].value="";
		return;
	}
	if(tempjg==0)
	{
		
		if(parseInt(to_minute)<=parseInt(from_minute)){
			alert("结束时间不能小于等于开始时间");
			document.all[dayid].value="";
			return;
		}
		if(parseInt(to_minute)>parseInt(from_minute)){
			var ck=ruond(type,parseInt(parseInt(to_minute)-parseInt(from_minute)));
			var asd=ck;
			asd+="_";
			if(asd.indexOf(".")==-1)
			{

			document.all[timeid].value=parseFloat(ck)+".0";
			}
			else
			{

			document.all[timeid].value=parseFloat(ck);
			}
			return;
		}
	}
	alert(tempjg);
	if(tempjg>0)//07:02-----:08:03
	{
		if(parseInt(to_minute)<parseInt(from_minute))
		{
			var temp_hours=parseInt(tempjg)-parseInt(1);//0
			var temp_minute=parseInt(60-parseInt(from_minute)+parseInt(to_minute));
			var temp_i=ruond(type,temp_minute);
			var ck=parseInt(temp_hours)+parseFloat(temp_i);
			var asd=ck;
			asd+="_";
			if(asd.indexOf(".")==-1)
			{

			document.all[timeid].value=parseFloat(ck)+".0";
			}
			else
			{

			document.all[timeid].value=parseFloat(ck);
			}
			return ;
		}
		if(parseInt(to_minute)>parseInt(from_minute))
		{
			var temp_hours=parseInt(tempjg);//0
			var temp_minute=parseInt(parseInt(to_minute)-parseInt(from_minute));
			var temp_i=ruond(type,temp_minute);
			var ck=parseInt(temp_hours)+parseFloat(temp_i);
			var asd=ck;
			asd+="_";
			if(asd.indexOf(".")==-1)
			{

			document.all[timeid].value=parseFloat(ck)+".0";
			}
			else
			{

			document.all[timeid].value=parseFloat(ck);
			}
			return ;
		}
		if(parseInt(to_minute)==parseInt(from_minute))
		{
			var temp_hours=parseInt(tempjg);//0
			var ck=temp_hours;
			var asd=ck;
			asd+="_";
			if(asd.indexOf(".")==-1)
			{
			document.all[timeid].value=parseFloat(ck)+".0";
			}
			else
			{
			document.all[timeid].value=parseFloat(ck);
			}
			return;
		}
	}
}

function ruond(type,temp_minute)
{
	if(parseFloat(type)==0.5)
	{
		if(parseInt(temp_minute)<15)
		{
			return 0.0;
		}
		if(parseInt(temp_minute)>=15&&parseInt(temp_minute)<=30)
		{
			return 0.5;
		}
		if(parseInt(temp_minute)>30&&parseInt(temp_minute)<45)
		{
			return 0.5;
		}
		if(parseInt(temp_minute)>=45)
		{
			return 1.0;
		}
	}
	if(parseFloat(type)==1)
	{
		if(parseInt(temp_minute)<=30)
		{

			return 0.0;
		}
		if(parseInt(parseInt(to_minute)-parseInt(from_minute))>30)
		{

			return 1.0;
		}
	}
}

function changeText(type,timeid,fromid,toid,dayid,fromdayid)
{
	
	var from=document.all[fromid].value;
	var from_hours=from.substring(0,from.indexOf(":"));
	var from_minute=from.substring(from.indexOf(":")+1,from.length);
	var to=document.all[toid].value;
	var to_hours=to.substring(0,to.indexOf(":"));
	var to_minute=to.substring(to.indexOf(":")+1,to.length);
	var xg=parseFloat(document.all[timeid].value);

	var jg=parseInt(document.all[dayid].value);//18:00 22:00
	var fdayid=document.all[fromdayid].value;
	var jg1=parseInt(document.all[dayid].value);
	var tempjg;
	if(from_hours.length==2&&parseInt(from_hours)<10)
	{
		from_hours=from_hours.substring(1,2);
	}
	if(to_hours.length==2&&parseInt(to_hours)<10)
	{
		to_hours=to_hours.substring(1,2);
	}
	if(from_minute.length==2&&parseInt(from_minute)<10)
	{
		from_minute=from_minute.substring(1,2);
	}
	if(to_minute.length==2&&parseInt(to_minute)<10)
	{
		to_minute=to_minute.substring(1,2);
	}

	jg=parseInt(parseInt(jg)-parseInt(fdayid));

	if(parseInt(jg)==0)
	{	

		tempjg=parseInt(to_hours)-parseInt(from_hours);
	}
	if(parseInt(jg)==1)
	{

		tempjg=24-parseInt(from_hours)+parseInt(to_hours);
	}
	if(parseInt(jg)==2)
	{

		tempjg=48-parseInt(from_hours)+parseInt(to_hours);
	}
	if(parseInt(jg)==3)
	{
		tempjg=72-parseInt(from_hours)+parseInt(to_hours);
	}
	if(parseInt(tempjg)>72)
	{
		tempjg=72;
	}
	if(tempjg<0)
	{
		alert("结束时间不能小于开始时间");
		return;
	}
	if(tempjg==0)
	{
		if(parseInt(to_minute)<=parseInt(from_minute)){
			alert("结束时间不能小于等于开始时间");
			return;
		}
		if(parseInt(to_minute)>parseInt(from_minute)){
			var ck=ruond(type,parseInt(parseInt(to_minute)-parseInt(from_minute)));
			var asd=ck;
			asd+="_";
			if(asd.indexOf(".")==-1)
			{

			document.all[timeid].value=parseFloat(ck)+".0";
			}
			else
			{
			document.all[timeid].value=parseFloat(ck);
			}
			return;
		}
	}
	if(tempjg>0)//07:02-----:08:03
	{
		if(parseInt(to_minute)<parseInt(from_minute))
		{
			var temp_hours=parseInt(tempjg)-parseInt(1);//0
			var temp_minute=parseInt(60-parseInt(from_minute)+parseInt(to_minute));
			var temp_i=ruond(type,temp_minute);
			var ck=parseInt(temp_hours)+parseFloat(temp_i);
			var asd=ck;
			asd+="_";
			if(asd.indexOf(".")==-1)
			{

			document.all[timeid].value=parseFloat(ck)+".0";
			}
			else
			{

			document.all[timeid].value=parseFloat(ck);
			}
			return ;
		}
		if(parseInt(to_minute)>parseInt(from_minute))
		{
			var temp_hours=parseInt(tempjg);//0
			var temp_minute=parseInt(parseInt(to_minute)-parseInt(from_minute));
			var temp_i=ruond(type,temp_minute);
			var ck=parseInt(temp_hours)+parseFloat(temp_i);
			var asd=ck;
			asd+="_";
			if(asd.indexOf(".")==-1)
			{

			document.all[timeid].value=parseFloat(ck)+".0";
			}
			else
			{

			document.all[timeid].value=parseFloat(ck);
			}
			return ;
		}
		if(parseInt(to_minute)==parseInt(from_minute))
		{
			var temp_hours=parseInt(tempjg);//0
			var ck=temp_hours;
			var asd=ck;
			asd+="_";
			if(asd.indexOf(".")==-1)
			{

			document.all[timeid].value=parseFloat(ck)+".0";
			}
			else
			{

			document.all[timeid].value=parseFloat(ck);
			}
			return;
		}
	}
}