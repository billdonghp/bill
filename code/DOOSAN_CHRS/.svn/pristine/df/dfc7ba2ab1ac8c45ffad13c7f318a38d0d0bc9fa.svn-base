<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>快件资费标准</title>
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
	document.form1.action="/gm/gm_AddExpressInstall_Setup.jsp?menu_code=${param.menu_code}";
	document.form1.submit();
}

function Update() {

	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=expressManger&content=ViewexpressInstallUpdate&id="+document.form1.temp.value;
	document.form1.submit();
}

function Delete() {
	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.form1.action="/gmControlServlet?operation=expressManger&content=expressInstallDel&menu_code=${param.menu_code}&id="+document.form1.temp.value;
		document.form1.submit();
	}
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
	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					快件资费标准
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
		      
		      <td nowrap="nowrap" class="info_title_01">
				序号</td>
			  <td nowrap="nowrap" class="info_title_01">
				发件城市</td>
			  <td nowrap="nowrap" class="info_title_01">
				寄达城市</td>
			  <td nowrap="nowrap" class="info_title_01">
				邮件费</td>
			<td nowrap="nowrap" class="info_title_01">
				邮件类型</td>
		    </tr>
		   	 	<c:forEach items="${list}" var="list" varStatus="j">
			    <tr onClick="band('#E7F0EF','black','${list.ID}')" align="center">
			    	<td align="center" height="30px">
			    		&nbsp;${list.ORDERNUM}
				      </td>
				      <td align="center">
				      &nbsp;${list.GIVE_CITY}
				      </td>
				      <td align="center">
				      		&nbsp;${list.ARRIVE_CITY}
				      </td>
				      <td align="center">
				      		&nbsp;${list.EMAIL_EXPENSES}&nbsp;元
				      </td>
				      <td align="center">
				      		&nbsp;${list.EXPENSES_TYPE_NAME}
				      </td>
			    </tr>
			    </c:forEach>
		    <tr align="center"> 
			 </tr>
		 </table>
		 <!-- Page Navigation Start-->
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
</form>
</body>
</html>