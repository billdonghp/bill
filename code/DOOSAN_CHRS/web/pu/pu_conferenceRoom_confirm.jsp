<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="min" class="java.lang.String" scope="request" />
<jsp:useBean id="hour" class="java.lang.String" scope="request" />
<jsp:useBean id="Outhour" class="java.lang.String" scope="request" />
<jsp:useBean id="Outmin" class="java.lang.String" scope="request" />
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>会议室计划表</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
var str="";
 function Confirm(){
   if(document.form1.applyno.value==""){
	alert("请先选中数据！");
	return;
	}
 	if(document.form1.isUpdate1.value==1){
	alert("数据已经确认，不能重复确认！");
	return;
	}
  document.form1.action="/puControlServlet?menu_code=${param.menu_code }&operation=conferenceRoom&content=oingConfirm&flag=1&affirmno="+document.form1.applyno.value;
  document.form1.submit();

 }
 
function Update(){
if(document.form1.applyno.value==""){
	alert("请先选中数据！");
	return;
	}
	if(!confirm('是否进行修改？')){return;}
	
var today = document.form1.today.value;
var applyno=document.form1.applyno.value;
if(document.form1["hour"+applyno].value == "" || document.form1["Outhour"+applyno].value == ""){
		alert("请选择开始或者结束的具体时间！");
		return;
	}
	var staTime = document.form1["bookdate"+applyno].value;
	var endTime = document.form1["enddate"+applyno].value;
	if(staTime>endTime){
		alert("开始日期不能大于结束日期!");
		return;
	}
	var selectComeTime = document.form1["hour"+applyno].value ;
	var selectOutTime = document.form1["Outhour"+applyno].value ;
	if(staTime==endTime){		
	if(selectComeTime > selectOutTime){
		alert("开始时间不能大于结束时间!");
		return;
	}}
if(today>(document.form1["bookdate"+applyno].value+document.form1["hour"+applyno].value)){
var timeStr = document.form1["bookdate"+applyno].value+document.form1["hour"+applyno].value;
var timeStr1 = document.form1["bookdate1"+applyno].value+document.form1["hour1"+applyno].value;
if(timeStr != timeStr1){
alert("开始日期必须大于系统日期！");
 return;
 }
}
  if(document.form1["conferenceRoom"+applyno].value=='0'){
    alert("请选择要使用的会议室！");
    return;
  }
  checkRoom(document.form1["bookdate"+applyno].value,endTime = document.form1["enddate"+applyno].value,document.form1["hour"+applyno].value,document.form1["Outhour"+applyno].value,document.form1["conferenceRoom"+applyno].value,applyno);

}
 
 function viewConferenceRoomUsing(){
  document.form1.isView.value="He or She had viewing";
   window.open("/ga/ga_conferenceRoomUsing_view.jsp","","resizable,scrollbars,dependent,width=500,height=400,left=250,top=300");
 }
 
  function changeRoomNames(id,i){
  		it = i ;
		if(id != 0){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=changeRoomNames&TypeId="+id+"&recordid="+i;

		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );}else{
        	document.getElementById("equips"+i).innerHTML = '';
        }
}
var it ;
function showResponse(XmlHttpRequest){
	document.getElementById("equips" + it).innerHTML = XmlHttpRequest.responseText;
}

function checkRoom(applydate,enddate,begintime,endtime,roomname,applyno){

		if(roomname != null && roomname != ''){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=checkRoom&applydate="+applydate+"&enddate="+enddate+"&begintime="+begintime+"&endtime="+endtime+"&roomname="+roomname+"&applyno="+applyno+"&isUpdate="+document.form1.isUpdate.value;

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
		document.form1.action="/puControlServlet?menu_code=${param.menu_code}&operation=conferenceRoom&content=saveUpdate&applyno="+document.form1.applyno.value;
		document.form1.submit();
	 }   
	   
	if(document.form1.checkRoom.value=='0'){
		alert("该时间段已有其他部门申请该会议室！");
		document.form1.checkRoom.value = 1;
	}
	   
}

function search(){
document.form1.action="puControlServlet?menu_code=${param.menu_code}&operation=conferenceRoom&content=conferenceRoomConfirm";
document.form1.submit();
}

 function band(backColor,textColor,applyno,ISCONFIRM)
	{
		 var t;
		 if(typeof(preEl)!='undefined')
		 {
		 preEl.bgColor=orgBColor;
	
		 try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
		 }
		 var el = event.srcElement;
		 el = el.parentElement;
		 orgBColor = el.bgColor;
		 orgTColor = el.style.color;
		 el.bgColor=backColor;
		 try{ChangeTextColor(el,textColor);}catch(e){;}
		 preEl = el;
		 document.form1.applyno.value=applyno;
		 document.form1.isUpdate1.value=ISCONFIRM;		
	} 
	
	 function band1(backColor,textColor,applyno,ISCONFIRM)
	{	 
		 var t;
		 if(typeof(preEl)!='undefined')
		 {
		 preEl.bgColor=orgBColor;
	
		 try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
		 }
		 var el = event.srcElement;
		 el = el.parentElement;
		 orgBColor = el.bgColor;
		 orgTColor = el.style.color;
		 el.bgColor=backColor;
		 try{ChangeTextColor(el,textColor);}catch(e){;}
		 preEl = el;
		 document.form1.applyno.value=applyno;	
		 document.form1["Unlock"+applyno].value=1;
		 document.form1.isUpdate1.value=ISCONFIRM;	
	} 

function checkAll()
{
    var selected = document.form1.ck_all.value == "0" ? true : false;
    var affirmno = document.getElementsByName("affirmno") ;
  	for (var i = 0; i< affirmno.length ; i++){
		affirmno[i].checked = selected ;
	
	}
    document.form1.ck_all.value = selected ? "1" : "0";
}

function doAffirm(){
	var msg1='<ait:message messageID="alert.ess.common.nodatatopass" module="ess"></ait:message>';
	
	var msg3='<ait:message messageID="alert.ess.common.checkpass" module="ess"></ait:message>';

	var msg5='<ait:message messageID="alert.ess.common.cofirmpass" module="ess"></ait:message>';

	var affirmno = document.getElementsByName("affirmno");
	if (affirmno == null || affirmno.length == null || affirmno.length == 0){
	  	alert(msg1);
		return false;
	}
	var c = 0;
	for (var i=0; i<affirmno.length; i++){
		if(affirmno[i].checked == true){
			c++;
			break ;
		}
	}
	if (c==0){
	  	alert(msg3);
		return false;
	}                                       
    if (confirm(msg5)) {
	document.form1.action="/puControlServlet?menu_code=${param.menu_code }&operation=conferenceRoom&content=oingConfirm&flag=1";
	document.form1.submit();
	}
}
function Delete(){
	
	var affirmno = document.getElementsByName("affirmno");
	if (affirmno == null || affirmno.length == null || affirmno.length == 0){
	  	alert("没有要删除的记录");
		return false;
	}
	var c = 0;
	for (var i=0; i<affirmno.length; i++){
		if(affirmno[i].checked == true){
			c++;
			break ;
		}
	}
	if (c==0){
	  	alert("请选择要删除的记录");
		return false;
	}                                       
    if (confirm("确定要删除已选择的信息？")) {
      document.form1.action="/puControlServlet?menu_code=${param.menu_code }&operation=conferenceRoom&content=deleteApply&applyno="+document.form1.applyno.value;
      document.form1.submit();
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
</SCRIPT>

<body>
<%
	Date d = new Date();
	SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat timeFormatter1 = new SimpleDateFormat(
			"yyyy-MM-ddHH:mm");
	String today = timeFormatter.format(d);
	String today1 = timeFormatter1.format(d);
%>
<form name="form1" method="post" action=""><input type="hidden"
	name="ck_all" value="0" /> <input type="hidden" name="menu_code"
	value="${param.menu_code }"> <input type="hidden" name="isView" value="">
<input type="hidden" name="applyno" value=""> <input
	type="hidden" name="checkRoom" value="1"> <input type="hidden"
	name="isUpdate" value="isUpdate"> <input type="hidden"
	name="isUpdate1" value=""> <input name="today"
	value="<%=today1%>" type="hidden">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_deleteAndModifyAndOk.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info -->
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">查询条件</td>
			</tr>
		</table>
		<table width="100%" height="30" border="0" cellpadding="0"
			cellspacing="1" class="dr_d">
			<tr>
				<td class="info_title_01" nowrap="nowrap"><!-- 开始日期 --> <ait:message
					messageID="display.mutual.start_date" module="ess"></ait:message></td>
				<td class="info_content_00" nowrap="nowrap"><input type="text"
					name="startDate" style="width: 70px" value="${startDate}"
					onClick="setday(this);" /></td>
				<td class="info_title_01"><!--  结束日期--> <ait:message
					messageID="display.mutual.end_date" module="ess"></ait:message></td>
				<td class="info_content_00"><input type="text" name="endDate"
					style="width: 70px" value="${endDate}" onClick="setday(this);" /></td>
				<td  class="info_title_01">会议室法人</td>
		         <td  class="info_content_00"><ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="search();"/></td>
				<td class="info_title_01">会议室</td>
				<td class="info_content_00">
	      		<select name="conferenceRoom" onchange=" changeRoomNames(this.value); " selected="${conferenceRoom}">
					<option value="">请选择</option>
					<c:forEach items="${roomname}" var="roomname" varStatus="i">
							<c:choose>
								<c:when test="${roomname.ID==conferenceRoom}">
									<option value="${roomname.ID}" selected>${roomname.ROOMNAME}</option>
								</c:when>
								<c:otherwise>
									<option value="${roomname.ID}">${roomname.ROOMNAME}</option>
								</c:otherwise>
							</c:choose>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>	             
				<td class="info_title_01" nowrap="nowrap"><!-- 部门 --> <ait:message
					messageID="display.mutual.department" module="ess"></ait:message></td>
				<td class="info_content_00" nowrap="nowrap"><ait:selDeptByCpnyId
					name="deptid" supervisorType="pa" all="all" cpnyId ="${cpnyId}" selected="${deptid}" />
				</td>

				<td class="info_title_01" nowrap="nowrap"><!-- 关键字 --> <ait:message
					messageID="display.mutual.key_word" module="ess"></ait:message></td>
				<td class="info_content_00" nowrap="nowrap"><input type="text"
					name="key" value="${key}" title="输入姓名或者职号" /></td>
				<td class="info_title_01" nowrap="nowrap">状态</td>
				<td class="info_content_00" nowrap="nowrap"><select
					name="qryType">
					<option value="3">全部</option>
					<option value="0" <c:if test="${qryType==0}">selected</c:if>>未确认</option>
					<option value="1" <c:if test="${qryType==1}">selected</c:if>>已确认</option>
				</select></td>				
				<td class="info_title_01" nowrap="nowrap">&nbsp;</td>
				<td class="info_content_00"><img src="../images/btn/Search.gif"
					style="cursor: pointer;" onclick="search();"></td>
			</tr>
		</table>
		<!-- display 3 level menu --> <%@ include
			file="../inc/thirdToolBar.jsp"%> <!-- display content -->

		<br>

		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" id='checkRoomInfo' colspan="10">
				会议室确认</td>
			</tr>
		</table>
		<%
			if (!errorMsg.equals("")) {
		%>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td align="center"><font color="red"><%=errorMsg%></font></td>
			</tr>
		</table>
		<%
			}
		%>

		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">
				<td class="info_title_01" nowrap><a href="#"
					onclick="checkAll();" style="color: red;"><!-- 全选 --> <ait:message
					messageID="display.mutual.select_2" module="ess" /></a></td>
				<td nowrap="nowrap" class="info_title_01">编号</td>
				<td nowrap="nowrap" class="info_title_01">申请时间</td>
				<td nowrap="nowrap" class="info_title_01">开始日期</td>
				<td nowrap="nowrap" class="info_title_01">开始时间</td>
				<td nowrap="nowrap" class="info_title_01">结束日期</td>
				<td nowrap="nowrap" class="info_title_01">结束时间</td>
				<td nowrap="nowrap" class="info_title_01">申请部门</td>
				<td nowrap="nowrap" class="info_title_01">申请人</td>
				<td nowrap="nowrap" class="info_title_01">与会人员</td>
				<td nowrap="nowrap" class="info_title_01">与会领导</td>
				<td nowrap="nowrap" class="info_title_01">与会人数</td>
				<td nowrap="nowrap" class="info_title_01">会议室</td>
				<td nowrap="nowrap" class="info_title_01">设备</td>
				<td nowrap="nowrap" class="info_title_01">其它要求</td>
				<td nowrap="nowrap" class="info_title_01">会议主题</td>
				<td nowrap="nowrap" class="info_title_01">状态</td>
				<td nowrap="nowrap" class="info_title_01">意见</td>
			</tr>
			<c:forEach items="${conferenceRoomConfirmList}" var="varTest"
				varStatus="i">
				<tr
					onclick="band1('#E7F0EF','black','${varTest.APPLYNO}','${varTest.ISCONFIRM}')">
					<td nowrap="nowrap" align="center">&nbsp;<c:if
						test="${ varTest.ISCONFIRM==0}">
						<input type="checkbox" name="affirmno" value="${varTest.APPLYNO}" />
					</c:if>&nbsp;</td>
					<input type="hidden" name="Unlock${varTest.APPLYNO}" value="0">
					<td nowrap="nowrap" align="center">&nbsp;${varTest.CONFERENCENO}&nbsp;</td>
					<td nowrap="nowrap" align="center">&nbsp;${varTest.APPLYDATE}&nbsp;</td>
					<td nowrap="nowrap" align="center">&nbsp;<input type="text"
						size="8" name="bookdate${varTest.APPLYNO}"
						value="${varTest.BOOKDATE}" readonly onClick="setday(this);">
						<input type="hidden" name="bookdate1${varTest.APPLYNO}" value="${varTest.BOOKDATE}">
					<c:choose>
						<c:when test="${varTest.BOOKDATE!=varTest.BEFORE_BOOKDATE}">
							<br>原信息：<font color="red">${varTest.BEFORE_BOOKDATE}</font>
						</c:when>
					</c:choose>&nbsp;</td>
					<td nowrap="nowrap" align="center">&nbsp; <!--		      <ait:time name="bookstarttime${varTest.APPLYNO}" spacing="30" selected="${varTest.BOOKSTARTTIME}"/>-->
					<input name="hour${varTest.APPLYNO}" id="hour${varTest.APPLYNO}" value="${varTest.hour }:${varTest.min }" type="text" style="width:35px" onblur="checkTimeValue(this);"/>
					 <c:choose>
						<c:when
							test="${varTest.BOOKSTARTTIME!=varTest.BEFORE_BOOKSTARTTIME}">
							<br>原信息：<font color="red">${varTest.BEFORE_BOOKSTARTTIME}</font>
						</c:when>
					</c:choose>&nbsp;
					<input type="hidden" name="hour1${varTest.APPLYNO}" value="${varTest.hour}">
					<input type="hidden" name="min1${varTest.APPLYNO}" value="${varTest.min}">
					</td>
					<td nowrap="nowrap" align="center">&nbsp; <input type="text"
						size="8" name="enddate${varTest.APPLYNO}"
						value="${varTest.ENDDATE}" readonly onClick="setday(this);">
					<c:choose>
						<c:when test="${varTest.ENDDATE!=varTest.BEFORE_ENDDATE}">
							<br>原信息：<font color="red">${varTest.BEFORE_ENDDATE}</font>
						</c:when>
					</c:choose>&nbsp;</td>
					<td nowrap="nowrap" align="center">&nbsp; <!--		      <ait:time name="bookendtime${varTest.APPLYNO}" spacing="30" selected="${varTest.BOOKENDTIME}" />-->
					<input name="Outhour${varTest.APPLYNO}" id="Outhour${varTest.APPLYNO}" value="${varTest.endhour }:${varTest.endmin }" type="text" style="width:35px" onblur="checkTimeValue(this);"/>
<!--					<select name="Outhour${varTest.APPLYNO}"
						id="Outhour${varTest.APPLYNO}">
						<option value="">小时</option>
						<c:forEach items="${listHH}" var="lhh" varStatus="i">
							<option value="${lhh}"
								<c:if test="${lhh == varTest.endhour}">selected="selected" </c:if>>${lhh}</option>
						</c:forEach>
					</select> : <select name="Outmin${varTest.APPLYNO}"
						id="Outmin${varTest.APPLYNO}">
						<option value="">分钟</option>
						<c:forEach items="${listMM}" var="lmm" varStatus="i">
							<option value="${lmm}"
								<c:if test="${lmm == varTest.endmin}">selected="selected" </c:if>>${lmm}</option>
						</c:forEach>
					</select>--> <c:choose>
						<c:when test="${varTest.BOOKENDTIME!=varTest.BEFORE_BOOKENDTIME}">
							<br>原信息：<font color="red">${varTest.BEFORE_BOOKENDTIME}</font>
						</c:when>
					</c:choose>&nbsp;
					</td>
					<td nowrap="nowrap" align="center">&nbsp;${varTest.DEPTNAME}
					&nbsp;</td>
					<td nowrap="nowrap" align="center">&nbsp;${varTest.CHINESENAME}&nbsp;</td>
					<td nowrap="nowrap" align="center">&nbsp;${varTest.PEOPLECLASS}&nbsp;</td>
					<td nowrap="nowrap" align="center">&nbsp;${varTest.PEOPLECLASS_UP}&nbsp;</td>
					<td nowrap="nowrap" align="center">&nbsp;<input
						name="booknumber${varTest.APPLYNO}" value="${varTest.BOOKNUMBER}"
						style="width: 30px">&nbsp;</td>
					<td nowrap="nowrap" align="center">&nbsp; <select
						name="conferenceRoom${varTest.APPLYNO}"
						onchange=" changeRoomNames(this.value,${varTest.APPLYNO}); ">
						<option value="0">请选择</option>
						<c:forEach items="${roomname}" var="roomname" varStatus="i">
							<c:choose>
								<c:when test="${roomname.ROOMNAME==varTest.ROOMNAME}">
									<option value="${roomname.ID}" selected>${roomname.ROOMNAME}</option>
								</c:when>
								<c:otherwise>
									<option value="${roomname.ID}">${roomname.ROOMNAME}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> <c:choose>
						<c:when
							test="${varTest.CONFERENCEROOM!=varTest.BEFORE_CONFERENCEROOM}">
							<br>原信息：<font color="red">${varTest.BEFORE_ROOMNAME}</font>
						</c:when>
					</c:choose>&nbsp;</td>
					<td nowrap="nowrap" id='equips${varTest.APPLYNO}'><c:forEach
						items="${varTest.equipsList}" var="equipsList" varStatus="j">
						<c:forEach items="${equipsList.EQUIP}" var="equip" varStatus="k">
							<%
								String ischeck = "";
							%>
							<c:forEach items="${varTest.equipsApplyList}"
								var="equipsApplyList" varStatus="m">
								<c:forEach items="${equipsApplyList.EQUIPS}" var="equips"
									varStatus="n">
									<c:if test="${equip==equips}">
										<%
											ischeck = "checked";
										%>
									</c:if>
								</c:forEach>
							</c:forEach>
							<input type='checkbox' name="UP${varTest.APPLYNO}"
								value="${equip}" <%=ischeck %> />${equip}<br>

						</c:forEach>
					</c:forEach>&nbsp;</td>
					<td nowrap="nowrap" align="center">&nbsp;${varTest.OTHERREQUEST}&nbsp;</td>
					<td nowrap="nowrap" align="center">&nbsp;${varTest.PURPOSEOFUSE}&nbsp;
					</td>
					<td nowrap="nowrap" align="center"><c:if
						test="${varTest.ISCONFIRM==1}">
						<font color="#00CC00">已确认</font>
					</c:if> <c:if test="${varTest.ISCONFIRM==0}">
						<font color="red">未确认</font>
					</c:if></td>

					<td nowrap="nowrap" align="center">&nbsp; <textarea
						name="affirmorIdea_${varTest.APPLYNO}"
						style="height: 40px; width: 100px" type="_moz"
						onfocus="this.style.height='50px'"
						onblur="this.style.height='40px';">${varTest.AFFIRMORIDEA}</textarea>&nbsp;</td>
				</tr>
				<input type="hidden" name="roomname${varTest.APPLYNO}"
					value="${varTest.ROOMNAME}">
			</c:forEach>
			<input type="hidden" name="currentPage" value="${currentPage}">
		</table>

		<!-- Page Navigation Start--> <ait:page link="/puControlServlet"
			parameters="&operation=conferenceRoom&content=conferenceRoomConfirm&menu_code=${param.menu_code}&qryType=${qryType }&startDate=${startDate }&endDate=${endDate }&deptid=${deptid }&key=${key }&conferenceRoom=${conferenceRoom}"
			total="${resultCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupsize}"
			useJS="false" /></td>
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
</form>

</body>

</html>
