<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/meta.jsp"%>
<%@ page import="com.ait.ar.bean.ArShift010,com.ait.sy.bean.*"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.hrm.business.*"%>
<%@ taglib uri="/ait-taglib" prefix="ait" %>
<%@ page import="java.util.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="ArShift010bean" scope="page" class="com.ait.ar.dao.ArShift010Bean" />
<%
/*传递登陆者ID*/
AdminBean admin = (AdminBean)session.getAttribute("admin");
ArShift010bean.setLoginID(admin.getAdminID());

ArrayList shifts=ArShift010bean.getShift010();
request.setAttribute("shifts",shifts);
ArShift010 ar=null;

SimpleMap deptMap=new SimpleMap();
List deptList=new ArrayList();
deptList=HrmServices.getInstance().retrieveDeptTree(deptMap);
request.setAttribute("dept_list",deptList);
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/dayCount.js" ></script>
<script language="javascript" src="../js/calendarcode.js"></script>
<script src="../js/prototype.js"></script>
<script language="javascript">
function add()
{
	var length=document.form1.shift_s.length;
	for(var i=0;i<document.form1.shifts.length;i++)
	{
		if(document.form1.shifts.options[i].selected)
		{
			document.form1.shift_s.options[length]=new Option(document.form1.shifts.options[i].text,document.form1.shifts.options[i].value);
		}
	}
}
function Add()
{
    if(document.form1.shiftID.value=="")
    {//"请输入员工号"
       alert('<ait:message  messageID="alert.att.maintenance.rotation.emp_id" module="ar"/>');
                  return false;
    }
    var empID=document.form1.shiftID.value;
    var personID=document.form1.person_id.value;
        //////////
        var reg=/^\d{4}-\d{1,2}-\d{2}$/;
	if(!document.form1.from_date.value.match(reg))
	{//"日期格式不正确"
		alert('<ait:message  messageID="alert.att.date_invalid" module="ar"/>');
		return;
	}
	if(!document.form1.to_date.value.match(reg))
	{//"日期格式不正确"
		alert('<ait:message  messageID="alert.att.date_invalid" module="ar"/>');
		return;
	}
	var from=document.form1.from_date.value;
	var from_y=from.substring(0,4);
	var from_m=from.substring(5,7);
	var from_d=from.substring(8,10);
	if(from_m.indexOf('-')!=-1)
	{//"请正确输入日期格式yyyy-mm-dd"
		alert('<ait:message  messageID="alert.att.enter_correct_date" module="ar"/>'+"yyyy-mm-dd");
		return;
	}
	if(from_m.indexOf('0')!=-1&&from_m.indexOf('0')==0)
	{
		from_m=from_m.substring(1,2);
	}
	if(from_d.indexOf('0')==0)
	{
		from_d=from_d.substring(1,2);
	}
	if(parseInt(from_m)>12||parseInt(from_m)<=0)
	{//"请正确输入月份"
		alert('<ait:message  messageID="alert.att.correct_month" module="ar"/>');
		return;
	}
	if(parseInt(from_d)>31||parseInt(from_d)<=0)
	{//"请正确输入天数"
		alert('<ait:message  messageID="alert.att.correct_day" module="ar"/>');
		return;
	}
	var to=document.form1.to_date.value;
	var to_y=to.substring(0,4);
	var to_m=to.substring(5,7);
	var to_d=to.substring(8,10);
	if(to_m.indexOf('-')!=-1)
	{//"请正确输入日期格式yyyy-mm-dd"
		alert('<ait:message  messageID="alert.att.enter_correct_date" module="ar"/>'+"yyyy-mm-dd");
		return ;
	}
	if(to_m.indexOf('0')!=-1&&to_m.indexOf('0')==0)
	{
		to_m=to_m.substring(1,2);
	}
	if(to_d.indexOf('0')==0)
	{
		to_d=to_d.substring(1,2);
	}
	if(parseInt(to_m)>12||parseInt(to_m)<=0)
	{//"请正确输入月份"
		alert('<ait:message  messageID="alert.att.correct_month" module="ar"/>');
		return ;
	}
	if(parseInt(to_d)>31||parseInt(to_d)<=0)
	{//"请正确输入天数"
		alert('<ait:message  messageID="alert.att.correct_day" module="ar"/>');
		return ;
	}
	var d_count=0;

	if(from_y==to_y&&from_m==to_m)//同年同月
	{
		d_count=getintervalDay(from_y,from_m,from_d,to_y,to_m,to_d);
	}
	if(from_y==to_y&&from_m!=to_m)//同年不同月
	{

		d_count=getintervalMonth(from_y,from_m,from_d,to_y,to_m,to_d);
	}

	if(from_y!=to_y)//年份不一样
	{
		d_count=getintervalYear(from_y,from_m,from_d,to_y,to_m,to_d);
	}

        /////////////////////

	var length=document.form1.shift_s.length;
	if(length==0)
	{//"请添加排班项"
		alert('<ait:message  messageID="alert.att.maintenance.rotation.add_rotaiton" module="ar"/>');
		return;
	}
	if(parseInt(length)>parseInt(d_count))
	{//"时间间隔不能小于排班项"
		alert('<ait:message  messageID="alert.att.maintenance.rotation.span_shorter" module="ar"/>');
		return ;
	}

	var str="";
	for(var i=0;i<length;i++)
	{
		if(i==length-1)
		{
		str+=document.form1.shift_s.options[i].value;
		}
		else
		{
		str+=document.form1.shift_s.options[i].value+"-";
		}

	}
	
	document.form1.action="/arControlServlet?operation=shiftset&empID=" + empID + "&personID=" + personID + "&from_date="+from+"&to_date="+to+"&d_count="+d_count+"&shift_no="+str+"&type=employee";

    document.form1.submit();
}
function del()
{
	for(var i=0;i<document.form1.shift_s.length;i++)
	{
		if(document.form1.shift_s.options[i].selected)
			document.form1.shift_s.options[i]=null;
	}

}
function up()
{
	var temp;
	var text1;
	var value1;
	for(var i=0;i<document.form1.shift_s.length;i++)
	{
		if(document.form1.shift_s.length==1)
		{//"无法执行上移操作"
			alert('<ait:message  messageID="alert.att.maintenance.rotation.invalid_up" module="ar"/>');
			return false;
		}
		if(document.form1.shift_s.options[i].selected)
		{
			if(i==0)
			{//"无法执行上移操作"
			alert('<ait:message  messageID="alert.att.maintenance.rotation.invalid_up" module="ar"/>');
			return false;
			}
			temp=i;
			text1=document.form1.shift_s.options[i-1].text;
			value1=document.form1.shift_s.options[i-1].text;
			document.form1.shift_s.options[temp-1]=new Option(document.form1.shift_s.options[i].text,document.form1.shift_s.options[i].value);
			document.form1.shift_s.options[i]=new Option(text1,value1);

		}
	}
}
function down()
{
	var temp;
	var text1;
	var value1;
	for(var i=0;i<document.form1.shift_s.length;i++)
	{
		if(document.form1.shift_s.length==1)
		{//"无法执行下移操作"
			alert('<ait:message  messageID="alert.att.maintenance.rotation.invalid_down" module="ar"/>');
			return false;
		}
		if(document.form1.shift_s.options[i].selected)
		{
			if(i==document.form1.shift_s.length-1)
			{//"无法执行下移操作"
			alert('<ait:message  messageID="alert.att.maintenance.rotation.invalid_down" module="ar"/>');
			return false;
			}
			temp=i;
			text1=document.form1.shift_s.options[i+1].text;
			value1=document.form1.shift_s.options[i+1].text;
			document.form1.shift_s.options[temp+1]=new Option(document.form1.shift_s.options[i].text,document.form1.shift_s.options[i].value);
			document.form1.shift_s.options[i]=new Option(text1,value1);
		}
	}
}

var time=null;
function SearchEmp(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft;
		  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    
    if(size<40){
    	layeri.style.height = 48;
		layer.style.height = 48; 
    }else if(size<210){
		layeri.style.height = size;
		layer.style.height = size;  
    }else{
    	layeri.style.height = 210;
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText.replace('*','&');
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('shiftID').value=cell.childNodes[0].firstChild.nodeValue;
		$('person_id').value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
}



function excel(){
	var from=document.form1.from_date.value;
	var to=document.form1.to_date.value;
	var deptid=document.form1.deptid.value;
	if(!from==''&&!to==''){
		window.open("/reports/ar_report/ar_shift_reports.jsp?from="+from+"&to="+to+"&deptid="+deptid);
	}else{
	//'请选择开始结束日期！'
		alert('<ait:message  messageID="alert.att.select_start_end" module="ar"/>');
	}	
}		

</script>
</head>

<body>

<table width="500" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form name="form1" method="post">
	<tr>
      <td class="info_title_01" width="20%"><!--开始时间-->
					<ait:message  messageID="display.mutual.start_date_2"/></td>
      <td width="30%"><input name="from_date" type="text" size="10" readonly onclick="setday(this);">
<!--      <img src="../images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onclick="showCalendar('form1','from_date');" Style="cursor:hand"></td>-->
      <td class="info_title_01"  width="20%"><!--结束时间-->
					<ait:message  messageID="display.mutual.end_time"/></td>
      <td width="30%"><input name="to_date" type="text" size="10" readonly onclick="setday(this);">

    </tr>
    <tr>
      <td class="info_title_01"><!--员工号-->
					<ait:message  messageID="display.mutual.emp_id_2"/></td>
      <td colspan="2"><input name="shiftID" id="shiftID" type="text" size="10" onKeyUp="SearchEmp(this.value,this.id)" />
      <input name="person_id" id="person_id" type="hidden">
      </td>
      <td><ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle" onclick="javascript:Add();" style="cursor:hand" /></td>
    </tr>
	<tr>
      <td valign="top" class="info_title_01"><!--班次名称-->
					<ait:message  messageID="display.att.maintenance.shift.shifts" module="ar"/></td>
      <td colspan="3" valign="top">
      <table width="350" border="0" cellspacing="0" cellpadding="0">
        <tr>
        
          <td width="39%" valign="top">
	          <select name="shifts" size="10" style="width:100% ">
	          	<c:forEach items="${shifts}" var="shifts">
	          		<option value="${shifts.shift_no}">
	          			<ait:content enContent="${shifts.shift_En_Name}" zhContent="${shifts.shift_Name}" koContent="${shifts.shift_Kor_Name}"/>
	          		</option>
	          	</c:forEach>
	          </select>
          </td>
          
          <td width="21%" align="center"><!-- 上移 添加 删除 下移 -->
          <input type="button" style="width:80px" value='<ait:message  messageID="display.att.maintenance.shift.ascending" module="ar"/>' onClick="up()"><br>
          <input type="button" style="width:80px" value='<ait:message  messageID="display.mutual.add"/>' onClick="add()"><br>
          <input type="button" style="width:80px" value='<ait:message  messageID="display.mutual.delete"/>' onClick="del()"><br>
          <input type="button" style="width:80px" value='<ait:message  messageID="display.att.maintenance.shift.descending" module="ar"/>' onClick="down()">
          </td>
          <td width="40%" valign="top"><select name="shift_s" size="10" style="width:100% " >
          </select></td>
        </tr>
      </table></td>
    </tr>
</form>
</table>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>

	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">   
	</div>

</body>
</html>
