<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="com.ait.utils.*" %>
<%@ page import="com.ait.sy.sy0102.bean.*"%>
<link rel="stylesheet" type="text/css" href="../css/default.css">
<%
	String parentDeptId = request.getParameter("parentDeptId");	
	Biz biz=new Biz();
	List deptList=biz.ListByParentNo(parentDeptId);
	request.setAttribute("deptList",deptList); 
%>
<SCRIPT type="text/javascript">
function Save(){
	document.form1.submit();
}
function isNum(obj){
	if(isNaN(obj.value)){
		//alert("序号只能为数字！");
		alert(<ait:message  messageID="alert.sys.numeric_only" module="sys"/>) ;
		obj.value="";
		obj.focus();
	}
}
</SCRIPT>
<html>
<head>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>  
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
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><ait:message  messageID="display.sys.basic_maintenance.dept_maintenance.sequence" module="sys"/><!-- 部门排序 --></td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<form name="form1" method="post" action="/Esy0430Control?flag=updataDeptOrder&menu_code=sy0102">
		<tr align="center">
			<td height="30" class="info_title_01" ><ait:message  messageID="display.mutual.dept"/><!-- 部门 --></td>
			<td height="30" class="info_title_01" ><ait:message  messageID="display.sys.basic.dept.english_name" module="sys"/><!-- 部门英文名  --></td>
			<td height="30" class="info_title_01" ><ait:message  messageID="display.sys.basic_maintenance.dept_maintenance.sequence" module="sys"/><!-- 部门排序 --></td>
		</tr>  
		<%
		if(deptList!=null){ 
			for(int i=0,j=deptList.size();i<j;i++){
				Ent ent=(Ent)deptList.get(i);
			%>
			<tr>
			<td class="info_content_03" width="30%"><%=ent.getDeptName()%></td>
			<td class="info_content_03" width="30%"><%=ent.getDeptEnName() %></td>
			<td width="70%" align="left"> 
				<input type="text" size="6" name="orderNo_<%=ent.getDeptID()%>" value="<%=ent.getOrderNo()%>" onchange="isNum(this);">
				<input type="hidden" name="deptId" value="<%=ent.getDeptID()%>">
			</td>
			</tr>
			<%
			}
		}
		%>
		</form>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(deptList) < 9}">
				<c:forEach var="i" begin="1" end="${9-fn:length(deptList)}"
					step="1">
					<tr>
						<td class="info_content_01" height="30"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
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



</body>
</html>
