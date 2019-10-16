<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<%@ page import="com.ait.utils.FormUtil,com.ibm.icu.text.SimpleDateFormat" %>
<%@ page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date" %>
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" /> 

<jsp:useBean id="visiterNo" class="java.lang.String" scope="request" /> 
<jsp:useBean id="temp" class="java.lang.String" scope="request" /> 
<jsp:useBean id="applyNo" class="java.lang.String" scope="request" />
<jsp:useBean id="applyId" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>会议室申请</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
var str="";
function CheckNumberLength(){
 var text=document.form1.Purposeofuse.value.replace(/[^\x00-\xff]/g,"**");
 if(text.length>500){
 return true;
 }else{
 return false;
 }
}

var type = null;

function Save() {
var today = document.form1.today.value;

if($('starttime').value==""|| $('starttime').value==null){
		alert("请输入开始时间！");
		return;
	}
	if($('endtime').value=="" || $('endtime').value==null){
	alert("请输入结束时间！");
	return;
	}
	var staTime = document.form1.bookdate.value;
	var endTime = document.form1.enddate.value;
	if(staTime>endTime){
		alert("开始日期不能大于结束日期!");
		return;
	}
	var selectComeTime = $('starttime').value;
	var selectOutTime = $('endtime').value ;
	if(selectComeTime > selectOutTime){
		alert("开始时间不能大于结束时间!");
		return;
	}
if(today>(document.form1.bookdate.value+$('starttime').value)){
alert("开始日期必须大于系统日期！");
 return;
}

   if(document.form1.conferenceRoom.value=='0'){
    alert("请选择要使用的会议室！");
    return;
   }
   
   
///   if(document.form1.conferenceRoom.value=='10000015' || document.form1.conferenceRoom.value=='46')
///   {
      
///     if('2012-08-31' >= (document.form1.bookdate.value)){
///     	alert("大家好！\n由于公司征用会议室，您所预定的会议室1、3均在2012年8月底前不能使用。\n如您有重大会议安排，请与R&C Mgt部门联系。\n给您的工作带来不便，望您谅解！\n\nR&C Mgt");
///     	return;
///     }
        
///   }
   
   if(document.form1.bookNumber.value==''){
    alert("请输入使用人数！");
    return;
   }
   if(Number(document.form1.bookNumber.value) > Number(document.form1.peoples_allow.value)){
   if(!confirm('您所申请的人数已经超出会议室可容纳的人数，是否继续申请！'))
   return;
   }
   if(document.form1.cpnyId.value == '59000000'){
	    alert("\n a.请遵守预定时间，并严格遵守会议室使用制度。\n b.如您和您部门预定会议室迟到30分钟以上视为放弃使用。\n c.如您和您部门预定会议室未使用次数超过3次，则取消您预定会议室一周的权限。\n d.请爱护会议室各项设施，并在会议完毕后关闭空调、电灯等。\n \n \n   请关爱我们的办公室 \n\n \n   我同意以上会议室管理制度，并严格遵守。");
   }
   
   checkRoom(document.form1.bookdate.value,document.form1.enddate.value,$('starttime').value,$('endtime').value,document.form1.conferenceRoom.value);
   

}

 function   CheckNumber(tempvalue)       {   
    var   patrn=/^[0-9]{0,3}$/;
    if  (!patrn.test(tempvalue)){   
       alert("输入1-3位数字");   
       return  false;   
      }   
       return true; 
  }  

 function viewConferenceRoomUsing(){
  document.form1.isView.value="He or She had viewing";
   window.open("/ga/ga_conferenceRoomUsing_view.jsp?cpnyId="+document.form1.companyId.value,"","resizable,scrollbars,dependent,width=700,height=400,left=250,top=300");
 }
 
 function changeRoomNames(id){
		if(id != 0){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=changeRoomNames&TypeId="+id;

		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );}else{
        	$('equips').innerHTML = '';
        }
}

 function changemeetroomList(id){
		if(id != 0){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=changemeetroomList&TypeId="+id;

		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse2}
        );}
}
 function companyChange(){
	// alert(document.form1.companyId.value);
		document.form1.action='/gaControlServlet?menu_code=ga0105&operation=conferenceRoom&content=conferenceRoomApplyPage&companyId='+document.form1.companyId.value;
		document.form1.submit();	
	}
function showResponse(XmlHttpRequest){

	$('equips').innerHTML = XmlHttpRequest.responseText;
	document.getElementById("peoples_allow").value = document.getElementById("peoples_allow1").value;
	
}
function showResponse2(XmlHttpRequest){

	$('bookdate1').innerHTML = XmlHttpRequest.responseText;
	document.getElementById("bookdate").value = document.getElementById("bookdate2").value;
	document.getElementById("Purposeofuse").value = document.getElementById("purpro").value;
	document.getElementById("bookNumber").value = document.getElementById("num").value;
	alert(document.getElementById("endtime1").value);
	document.getElementById("starttime").value = document.getElementById("starttime1").value;
	document.getElementById("endtime").value = document.getElementById("endtime1").value;
}


function checkRoom(applydate,enddate,begintime,endtime,roomname){

		if(roomname != null && roomname != ''){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=checkRoom&applydate="+applydate+"&enddate="+enddate+"&begintime="+begintime+"&endtime="+endtime+"&roomname="+roomname;

		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse1}
        );}else{
        	$('checkRoomInfo').innerHTML = '';
        }
}

function showResponse1(XmlHttpRequest){	
	$('checkRoomInfo').innerHTML = XmlHttpRequest.responseText;
	if(document.form1.checkRoom.value!='0'){
		document.form1.checkRoom.value = document.form1.checkRooms1.value;
	}
	//alert(document.form1.checkRoom.value+document.form1.temp.value+document.form1.num.value);
	if(document.form1.checkRoom.value=='1'){
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=conferenceRoom&content=andconferenceRoomApply&visitertemp="+document.form1.visiterTemp.value+"&visiterno="+document.form1.visiterNo.value;
		document.form1.submit();
	   }   
	   
	if(document.form1.checkRoom.value=='0'){
		alert("该时间段已有其他部门申请该会议室，请查看详情后确认！");
		document.form1.checkRoom.value = 1;
	   }
	   
}

function checkTimeValue(str){
  var z=/^(([0-1][0-9])|([2-2][0-3])):([0-5][0-9])$/;
  if(!z.test($(str).value)){
   alert("请输入正确的时间格式(xx:xx)");
   $(str).value="";
   return false;  
  }

}

function Cycle(){

var str1 = "<select id='CYCLE_TYPE_SIZE' name='CYCLE_TYPE_SIZE'>"+
         "<option value=''>请选择</option>"+
         "<option value='1'>星期一</option>"+
         "<option value='2'>星期二</option>"+
         "<option value='3'>星期三</option>"+
         "<option value='4'>星期四</option>"+
         "<option value='5'>星期五</option>"+
         "<option value='6'>星期六</option>"+
         "<option value='0'>星期日</option>"+
         "</select>";     

var str2 = "<select id='CYCLE_TYPE_SIZE' name='CYCLE_TYPE_SIZE'>"+
         "<option value=''>请选择</option>"+
         "<option value='1'>1日</option>"+
         "<option value='2'>2日</option>"+
         "<option value='3'>3日</option>"+
         "<option value='4'>4日</option>"+
         "<option value='5'>5日</option>"+
         "<option value='6'>6日</option>"+
         "<option value='7'>7日</option>"+
         "<option value='8'>8日</option>"+
         "<option value='9'>9日</option>"+
         "<option value='10'>10日</option>"+
         "<option value='11'>11日</option>"+
         "<option value='12'>12日</option>"+
         "<option value='13'>13日</option>"+
         "<option value='14'>14日</option>"+
         "<option value='15'>15日</option>"+
         "<option value='16'>16日</option>"+
         "<option value='17'>17日</option>"+
         "<option value='18'>18日</option>"+
         "<option value='19'>19日</option>"+
         "<option value='20'>20日</option>"+
         "<option value='21'>21日</option>"+
         "<option value='22'>22日</option>"+
         "<option value='23'>23日</option>"+
         "<option value='24'>24日</option>"+
         "<option value='25'>25日</option>"+
         "<option value='26'>26日</option>"+
         "<option value='27'>27日</option>"+
         "<option value='28'>28日</option>"+
         "<option value='29'>29日</option>"+
         "<option value='30'>30日</option>"+
         "<option value='31'>31日</option>"+
         "</select>";

    
         
         document.getElementById("cycleTypeDiv").innerHTML = str;
         

	if(document.form1.CYCLE_TYPE.value == "CycleType01"){  
	  document.getElementById("cycleTypeDiv").innerHTML = str1;
	       
	}else if(document.form1.CYCLE_TYPE.value == "CycleType02"){
	  document.getElementById("cycleTypeDiv").innerHTML = str2;
	}
}


</SCRIPT>

<body>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");		
String today=timeFormatter.format(d);
String today1=timeFormatter1.format(d);
%>
<form name="form1" method="post" action="">
<input type="hidden" name="peoples_allow" value="0" id="peoples_allow">
<input type="hidden" name="temp" value="0">
<input type="hidden" name="isView" value="">		
<input type="hidden" name="checkRoom" value="1">
<input type="hidden" name="applyNo" value="${applyNo}">

<input type="hidden" name="applyId" value="${applyId}">

<input type="hidden" name="visiterTemp" value="${temp}">	
<input type="hidden" name="visiterNo" value="${visiterNo}">	
<input type="hidden" name="today" value="<%=today1%>">	

<input type="hidden" name="cpnyId" value="<%=admin.getCpnyId() %>">	

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/toolbar_apply.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->
	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" id = 'checkRoomInfo' colspan="10">
				会议室申请<font color="red" size="2">${declaration}</font>
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table id = "operateTable" width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap" class="info_title_01">
				会议室法人 </td>
			<td nowrap="nowrap" class="info_content_00" align="left">
			<ait:codeClass codeClass="EMP_DIFF" name="companyId" selected="${companyId}" onChange="companyChange();"/>
      		</td>
		      <td nowrap="nowrap" class="info_title_01">
				会议室 </td>
			<td nowrap="nowrap" class="info_content_00" align="left">
      		<select name="conferenceRoom" onchange=" changeRoomNames(this.value); ">
				<option value="0">请选择</option>
				<c:forEach items="${roomname}" var="roomname" varStatus="i">
					<option value="${roomname.ID}">
						${roomname.ROOMNAME}
					</option>
				</c:forEach>
			</select></td>
				<%--				 	    
			  <td width="15%" class="info_title_01">
				决裁者</td>
			  --%>
			  <td nowrap="nowrap" class="info_title_01">
				设备</td>
			<td nowrap="nowrap" class="info_content_00" id = 'equips' align="left">&nbsp;</td>			  
		      <td nowrap="nowrap" class="info_title_01">
				与会人数</td>
			<td nowrap="nowrap" class="info_content_00" align="left"><input type="text" name="bookNumber" id="bookNumber" style="width:150px"  onkeyup="CheckNumber(this.value)"></td>
			 </tr>
		    <tr>
			  <td nowrap="nowrap" class="info_title_01">
				开始日期</td>
				<td nowrap="nowrap" class="info_content_00" align="left"><input id = "bookdate" type="text" name="bookdate" class="content" style="width:80px "  value="<%=today%>" onClick="setday(this);"></td>
		      <td nowrap="nowrap" class="info_title_01">
				开始时间</td>
				<td nowrap="nowrap" class="info_content_00" align="left">
<!--		      <ait:time name="starttime" spacing="30"/>-->
		      <input name="starttime" id="starttime" value="" type="text" style="width:35px" onblur="checkTimeValue(this);">&nbsp; (时间24小时制，如13:30)
		      </td>
			  <td nowrap="nowrap" class="info_title_01">
				结束日期</td>
				<td nowrap="nowrap" class="info_content_00" align="left">
				<input id = "enddate" type="text" name="enddate" class="content" style="width:80px "  value="<%=today%>" onClick="setday(this);"></td>
			  <td nowrap="nowrap" class="info_title_01">
				结束时间</td>
			 <td nowrap="nowrap" class="info_content_00" align="left">
<!--			   <ait:time name="endtime" spacing="30"/>-->
 			<input name="endtime" id="endtime" value="" type="text" style="width:35px" onblur="checkTimeValue(this);">&nbsp; (时间24小时制，如13:30)
		      </td>
		      </tr>
		      <tr>
				<%--
			  <td nowrap="nowrap" class="info_title_01">
				多天操作</td>	
			  --%><td nowrap="nowrap" class="info_title_01">
				申请部门</td>
			<td nowrap="nowrap" class="info_content_00" ><%=admin.getDepartment()%></td>
			  <td nowrap="nowrap" class="info_title_01" align="left">
				申请人</td>
			<td nowrap="nowrap" class="info_content_00" align="left"><%=admin.getChineseName()%></td>     
			  <td nowrap="nowrap" class="info_title_01">
				与会人员</td>	
			<td nowrap="nowrap" class="info_content_00" align="left"><ait:codeClass name="peopleclass" codeClass="conference_people"/></td> 
			  <td nowrap="nowrap" class="info_title_01">
			    与会领导</td>
			  <td nowrap="nowrap" class="info_content_00" align="left"><input type="text" name="peopleclass_up" style="width:150px" value=""></td>
			  </tr>		      
		      <tr>
		       <%if("60000000".equals(admin.getCpnyId())){%>
				<td nowrap="nowrap" class="info_title_01">
					周期类型
				</td>
				<td nowrap="nowrap" class="info_content_00">
			
					<select name="CYCLE_TYPE"  onchange="Cycle()">
						    <option value="">请选择</option>
							<option value="CycleType01">星期</option>
							<option value="CycleType02">日期</option>
					</select>&nbsp;&nbsp;
					
				</td>
				<td nowrap="nowrap" class="info_title_01">
					频率
				</td>
				<td nowrap="nowrap" class="info_content_00">
				<div id="cycleTypeDiv" name="cycleTypeDiv">
				</td>
			<%}%>	
			  <td nowrap="nowrap" class="info_title_01">
				预订详情</td>
			<td nowrap="nowrap" class="info_content_00"><span style="color:red; cursor:pointer;" onclick="viewConferenceRoomUsing()">点击查看</span></td>
			<td nowrap="nowrap" class="info_title_01">
				会议主题</td>
			<td  nowrap="nowrap" class="info_content_00" align="left">
<!--		      <input name="Purposeofuse" type="text" class="content"  style="width:80px">-->
		      <textarea name="Purposeofuse" id="Purposeofuse" style=" height: 25px;width:250px " type="_moz"  onfocus="this.style.height='40px'" onblur="this.style.height='25px'"></textarea></td>
		      </td>
			<td nowrap="nowrap" class="info_title_01">
				其它要求</td>
			<td nowrap="nowrap" class="info_content_00" align="left"><input type="text" name="otherRequest" style="width:150px" value=""></td>
		    </tr>		  
		    
		    <tr>
		   
				
			</tr>
		 </table>
		 
		 <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" id = 'checkRoomInfo' colspan="10">
				参观者信息
				</td>
			</tr>     
		</table>
		 <table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
			  <td nowrap="nowrap" class="info_title_01" style="width:100px ">
				参观者申请编号</td>
			<td nowrap="nowrap" class="info_content_00" style="width:200px ">
		      <select name="meetroomList" onchange=" changemeetroomList(this.value); ">
						<option value="0">请选择</option>
						<c:forEach items="${meetroomList}" var="meetroomList" varStatus="i">
							<option value="${meetroomList.GA_VISITER_APPLY_ID}">
								${meetroomList.GA_VISITER_APPLY_ID}
							</option>
						</c:forEach>
					</select>
		      </td>
		    <td nowrap="nowrap" class="info_title_01">&nbsp;</td>
			<td nowrap="nowrap" class="info_content_00" >&nbsp;</td>
			<td nowrap="nowrap" class="info_title_01">&nbsp;</td>
			<td nowrap="nowrap" class="info_content_00" >&nbsp;</td>
			<td nowrap="nowrap" class="info_title_01">&nbsp;</td>
			<td nowrap="nowrap" class="info_content_00" >&nbsp;</td>
			</tr>
		</table>
		 
		 <table width="100%" border="0" cellspacing="0" cellpadding="10">		
				<c:forEach var="i" begin="1" end="9">
					<tr>
						<td class="info_content_01">&nbsp;</td>					
					</tr>
				</c:forEach>			
		
		</table>
		 <br />
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
<div name="bookdate1" id="bookdate1"></div>
</form>	
</body>
<ait:xjos />
</html>
