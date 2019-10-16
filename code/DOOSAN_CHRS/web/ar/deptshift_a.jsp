<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.ArShift010"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>
<%@ include file="../inc/meta.jsp"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.hrm.business.*"%>
<%@ taglib uri="/ait-taglib" prefix="ait" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="ArShift010bean" scope="page" class="com.ait.ar.dao.ArShift010Bean">
</jsp:useBean>
<jsp:useBean id="ArGroup" scope="page" class="com.ait.ar.dao.ArScheduleBean">
</jsp:useBean>

<%
ArrayList shifts=ArShift010bean.getShift010();
request.setAttribute("shifts",shifts);
ArShift010 ar=null;
HttpSession session1 = request.getSession(false);
AdminBean admin = (AdminBean) session1.getAttribute("admin");
String adminID  = admin.getAdminID();


SimpleMap deptMap=new SimpleMap();
deptMap.setString("EMPID",adminID);
List deptList=new ArrayList();
deptList=HrmServices.getInstance().retrieveDeptTree(deptMap);
request.setAttribute("dept_list",deptList);
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/dayCount.js"></script>
<script language="javascript" src="../js/calendarcode.js"></script>
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
  	//return;
	if(document.form1.person_id.value=="")
	{//"请选择部门号"
		alert('<ait:message  messageID="alert.ess.select_department" module="ar"/>');
		return ;
	}

        var empID=document.form1.person_id.value;
        //////////
        var reg=/^\d{4}-\d{1,2}-\d{2}$/;
	if(!document.form1.from_date.value.match(reg))
	{//"日期格式不正确"
		alert('<ait:message  messageID="alert.att.date_invalid" module="ar"/>');
		return ;
	}
	if(!document.form1.to_date.value.match(reg))
	{//"日期格式不正确"
		alert('<ait:message  messageID="alert.att.date_invalid" module="ar"/>');
		return ;
	}
	var from=document.form1.from_date.value;
	var from_y=from.substring(0,4);
	var from_m=from.substring(5,7);
	var from_d=from.substring(8,10);
	if(from_m.indexOf('-')!=-1)
	{//"请正确输入日期格式yyyy-mm-dd"
		alert('<ait:message  messageID="alert.att.enter_correct_date" module="ar"/>'+"yyyy-mm-dd");
		return ;
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
		return ;
	}
	if(parseInt(from_d)>31||parseInt(from_d)<=0)
	{//"请正确输入天数"
		alert('<ait:message  messageID="alert.att.correct_day" module="ar"/>');
		return ;
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
		return ;
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
	document.form1.action="/arControlServlet?operation=shiftset&empID="+empID+"&from_date="+from+"&to_date="+to+"&d_count="+d_count+"&shift_no="+str+"&type=department";

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
<form name="form1" method="post">
  <table width="500" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
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
      <td class="info_title_01"><!--部门-->
					<ait:message  messageID="display.mutual.dept"/></td>
      <td colspan="2"> 
      <ait:selDept dataListName="dept_list" name="person_id" separator="&nbsp;&nbsp;"  supervisorType="ar"/>
      </td>
      <td><ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle" onclick="javascript:Add();" style="cursor:hand" /></td>
    </tr>
	<tr>
      <td class="info_title_01"><!--班次名称-->
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
    
    
  </table>
</form>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
