<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="RoomSetupList" class="java.util.ArrayList" scope="request"/>
<!--<jsp:useBean id="listMM" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listHH" class="java.util.ArrayList" scope="request"/>
--><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>会议室基本信息登录</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function band(backColor,textColor,i)
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
	document.form1.temp.value=i;
} 

function Add() {
	document.form1.action="/puControlServlet?menu_code=${param.menu_code}&operation=Roomsetup&content=addRoomSetup";
	document.form1.submit();
}

function Update() {

	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	document.form1.action="/puControlServlet?operation=Roomsetup&content=updateRoomSetup&menu_code=${param.menu_code}&id="+document.form1.temp.value;
	document.form1.submit();
}

function Delete() {
	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.form1.action="/puControlServlet?operation=Roomsetup&content=delRoomSetup&menu_code=${param.menu_code}&id="+document.form1.temp.value;
		document.form1.submit();
	}
}
function search(){
	document.form1.action="/puControlServlet?menu_code=${param.menu_code}&operation=Roomsetup&content=viewRoomSetup";
	document.form1.submit();
}
</SCRIPT>

<body>
<form name="form1" method="post">
<input type="hidden" name="temp" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_all.jsp"%>
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
<table width="100%" height="30" border="0" cellspacing="1"
			cellpadding="0">
			<tr>
				<td class="title1"><!-- 查询条件 --> <ait:message
					messageID="display.mutual.search_criteria" module="ess"></ait:message>
				</td>
			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td nowrap="nowrap" class="info_title_01">所属法人</td>
						<td nowrap="nowrap" class="info_content_00">									
   					       <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="search();"/>
						</td>	
					</tr>
				</table>
				</td>
			</tr>
		</table>	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					会议室基本信息登录
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      
		      <td nowrap="nowrap" class="info_title_01" >
				会议室</td>
			  <td nowrap="nowrap" class="info_title_01">
				可容纳人数</td>
			  <td nowrap="nowrap" class="info_title_01">
				设备提供</td>
			  <td nowrap="nowrap" class="info_title_01">
				管理担当</td>	
		    </tr>
		   	 	<c:forEach items="${RoomSetupList}" var="RoomSetupList" varStatus="j">
			    <tr onClick="band('#E7F0EF','black','${RoomSetupList.ID}')" align="center">
			    	<td align="center" height="30px">
			    		&nbsp;${RoomSetupList.ROOMNAME}
				      </td>
				      <td align="center">
				      &nbsp;${RoomSetupList.PEOPLES}
				      </td>
				      <td align="center">
				      		&nbsp;${RoomSetupList.EQUIP}
				      </td>
				       <td align="center">
				      		&nbsp;${RoomSetupList.CONFIRMORNAME}
				      </td>
			    </tr>
			    </c:forEach>
		    <tr align="center"> 
			 </tr>
		 </table>
		 <!-- Page Navigation Start-->
	<ait:page       
     link="/puControlServlet"
     parameters="&operation=Roomsetup&content=viewRoomSetup&menu_code=${param.menu_code}"
     total="${recordCount}"
     currentpage="${currentPage}"
     pagesize= "${pageSize}"
     beginlabel="paging_prv10"
     endlabel="paging_next10"
     prevlabel="paging_prv"
     nextlabel="paging_next"
     pagegroupsize="${pageGroupsize}"
     useJS="false"/>      
	<!-- Page Navigation End -->
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
</form>
</body>
</html>