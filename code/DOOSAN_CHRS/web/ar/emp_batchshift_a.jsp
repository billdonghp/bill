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

function checkAll()
{   
	

    var size = document.getElementsByName("checkFalg").length ;
    var array_empid = document.getElementById("empIds").value.split(",");
    var empid_size = array_empid.length ;
    
    //alert(size + empid_size) ;
    /*
    var selected = document.form1.ck_all.value == "0" ? true : false;
	document.form1.ck_all.value = selected ? "1" : "0";
    if(size + empid_size > 100 && selected)
	{
			alert("排班人数不能超过100人!!!") ;
			return ;
	}
	*/
    var empId_t = "" ;
	var empName_t = "" ;
    var checkFlag ;
    var cell ;
  	for (var i=0; i< size; i++){
  		checkFlag = document.getElementsByName("checkFalg")[i] ;
		
		cell = document.getElementById(checkFlag.value) ;
		
		if(!updateValue(cell))
		{
			return ;
		}		
	}
}

function Reset() {
	
	var selected = false;
	document.form1.ck_all.value = selected ? "1" : "0";
    var size = document.getElementsByName("checkFalg").length ;
	for (var i=0; i< size; i++){
  		checkFlag = document.getElementsByName("checkFalg")[i] ; 
  		checkFlag.checked = selected ;	
	}
	
	
	document.getElementById("shiftID").value = "";
	document.getElementById("empIds").value = "";
	document.getElementById("empName").innerText = "";
}

var time=null;
function Batch(condition,id){		
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
     	var pars = "operation=shiftHrmSearchEmployee&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layer1 = $('divi');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6+17;
		layer.style.left = iBleft - 370 ;  
		layeri.style.top = iBtop+iBheight+6+17;
		layeri.style.left = iBleft - 370 ;
		layer1.style.top = iBtop+iBheight+3;
		layer1.style.left = iBleft - 370 ;
		
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){

	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[5].firstChild.nodeValue;
    size = size*25+30;
    
    if(size<=30){
    	layeri.style.height = 48 + 25;
		layer.style.height = 48; 
    }else if(size<210){
		layeri.style.height = size + 25;
		layer.style.height = size;  
    }else{
    	layeri.style.height = 210  + 25;
		layer.style.height = 210; 
    }
	
	layer.innerHTML=XmlHttpRequest.responseText.replace('*','&');
    layer.style.visibility = 'visible';
    layer1.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	layer1.style.visibility = 'hidden';
	if(document.getElementById('empIds').value=='' && document.getElementById('empName').innerText!=''){
		Reset();
	}
}

function updateValue(cell) {

	var checkFlag = cell.childNodes[0].firstChild
	empid = cell.childNodes[1].firstChild.nodeValue;
	person_id = cell.childNodes[3].firstChild.nodeValue;
	empname = cell.childNodes[2].firstChild.nodeValue;
	var empId_t = "" ;
	var empName_t = "" ;
	var msg ='<ait:message messageID="alert.ess.overtime.has_been_added_to_list"  module="ess"></ait:message>';
	var array_empid = document.getElementById("empIds").value.split(",");
	var array_name =  document.getElementById("empName").innerText.split(",");  
	var size = array_empid.length ;
	
	if(document.getElementById("empIds").value.indexOf(empid) != -1)
	{
		
		for (i = 0 ; i < size ; i++){    
			if(empid == array_empid[i]){
				if(confirm("是否删除,工号[" + empid + "],姓名[" + empname + "] 的员工?")){
					checkFlag.checked = false ;
					continue ;
				}
			}
			if(array_empid[i].length > 0 )
			{
				empId_t += array_empid[i] + "," ;
			    empName_t += array_name[i] + "," ; 
			}  
		}
		document.getElementById("empIds").value = empId_t.substring(0,empId_t.lastIndexOf(",")) ;
		document.getElementById("person_id").value = empId_t.substring(0,empId_t.lastIndexOf(",")) ;
	    document.getElementById("empName").innerText = empName_t ;
	}else
	{
		if(size == 100)
		{
			alert("排班人数不能超过100人!!!") ;
			checkFlag.checked = false ;
			return false ;
		}
		checkFlag.checked = true ;  
		if (document.getElementById("empIds").value == ''){
			document.getElementById("empIds").value += empid;
			document.getElementById("person_id").value += person_id;
			}
		else{
			document.getElementById("empIds").value += "," + empid ;
			document.getElementById("person_id").value += "," + person_id ;
	}
		document.getElementById("empName").innerText += empname + ",";
	}
	
	return true ;
	
}




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
    if(document.form1.empIds.value=="")
    {//"请输入员工号"
       alert('<ait:message  messageID="alert.att.maintenance.rotation.emp_id" module="ar"/>');
                  return false;
    }
    var empID=document.form1.shiftID.value;
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
	document.form1.action="/arControlServlet?operation=shiftset&empID="+document.form1.person_id.value
			+"&from_date="+from+"&to_date="+to+"&d_count="+d_count+"&shift_no="+str+"&type=emp_batch&deptid";
	
	//alert(document.form1.action) ;
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

</script>
</head>

<body>

<table width="600" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form name="form1" method="post">
	<input type="hidden" name="empIds" value="" />
	<input type="hidden" name="ck_all" value="0" />
	<input type="hidden" name="person_id" id="person_id">
	<tr >
		<td class="info_title_01" ><!-- 姓名 --><ait:message messageID="display.ess.attendance_request.ot_request.requested_by"  module="ess"></ait:message></td>
	    <td colspan="3">&nbsp;<span id="empName"></span></td>
	</tr>
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
      <td class="info_title_01" ><!--员工号-->
					<ait:message  messageID="display.mutual.emp_id_2"/></td>
      <td colspan="2"><input name="shiftID" id="shiftID" type="text" size="10" onKeyUp="Batch(this.value,this.id);" />
	  </td>
	  <td><ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle" onclick="javascript:Add();" style="cursor:hand" /></td>
    </tr>
	<tr>
      <td valign="top" class="info_title_01"><!--班次名称-->
					<ait:message  messageID="display.att.maintenance.shift.shifts" module="ar"/></td>
      <td colspan="3" valign="top">
      <table width="350" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="39%" valign="top"><select name="shifts" size="10" style="width:100% ">
          	<c:forEach items="${shifts}" var="shifts">
          		<option value="${shifts.shift_no}">
          			<ait:content enContent="${shifts.shift_En_Name}" zhContent="${shifts.shift_Name}" koContent="${shifts.shift_Kor_Name}"/>
          		</option>
          	</c:forEach>
          </select></td>
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

	<div id='divi' style="position:absolute;overflow:auto; top:100;width:370; height:25; z-index:1; visibility: hidden;">
		<table width="370" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		    <tr>
		      <td width="20%" height="20" class="info_search_02">&nbsp;</td>
		      <td width="20%" align="center" onClick="Reset();" style="cursor:pointer;"><!-- 清除  -->
		     <ait:message messageID="display.ess.attendance_request.ot_request.bulk_request.clear_all" module="ess"></ait:message>
		     </td>
		      <td width="20%" class="info_search_02">&nbsp;</td>
		      <td width="20%" align="center" onClick="layerClose();" style="cursor:pointer;"><!--  关闭-->
		      <ait:message messageID="display.ess.attendance_request.ot_request.bulk_request.close" module="ess"></ait:message>
		      </td>
		      <td width="20%" class="info_search_02">&nbsp;</td>
		    </tr>
	  	</table>
	</div>
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:225; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:200; z-index:2; visibility: hidden;">   
	</div>

</body>
</html>
