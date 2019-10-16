<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.Vector,com.ait.pa.Paitem"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"></jsp:useBean>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_none.jsp"%>
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
		<%@ include file="../pa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--工资公式-->
					<ait:message  messageID="display.pay.maintenance.formula.formula" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		  <tr align="center">
		    <td width="15%"  height="30"  align="center" class="info_title_01">
		    <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		      <tr align="center">
		        <td nowrap height="30"  align="center" class="info_title_01"><!-- 向上移动 -->
		        <img src="/images/btn/up.gif"  height="15" style="cursor:hand " onClick="javascript:ChangeOrder('up');" alt='<ait:message  messageID="display.pay.formula.up" module="pay"/>'>
		        </td>
		        <td nowrap align="center" class="info_title_01"><!--工资计算项目列表-->
					<ait:message  messageID="display.pay.maintenance.formula.list" module="pay"/></td>
		        <td nowrap align="center" class="info_title_01"><!-- 向下移动 -->
		        <img src="/images/btn/down.gif"  height="15" style="cursor:hand " onClick="javascript:ChangeOrder('down');" alt='<ait:message  messageID="display.pay.formula.down" module="pay"/>'>
		        </td>
		      </tr>
		    </table></td>
		    <td width="75%" align="center" class="info_title_01"><!--工资项目-->
					<ait:message  messageID="display.pay.maintenance.formula.description" module="pay"/> </td>
		    <td width="10%" align="center" class="info_title_01">
		    <ait:image src="/images/btn/Tool.gif"  border="0" align="absmiddle"
          	onclick="window.open('/paControlServlet?operation=pa_util','pa_util','toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,width=754,height=320,left=240,top=0');" style="cursor:hand" />
		   </td>
		  </tr>
		<% 	Paitem paitem = new Paitem();
			Vector vlist = new Vector();
			vlist = paitem.List();
			request.setAttribute("vlist",vlist);%>  
		<tr align="center" >
		    <td  align="center" class="info_content_01" style="padding:5 5 5 5 " valign="top">
		    <%if (vlist.size()!=0){	%>
		    	<select name="select" id="param_no" size="20" style="width:100% " onChange="changedata(this.value)">
		      		<c:forEach items="${vlist}" var="vlist">
		      			<option value="${vlist.pa_item_no}">
		      				<ait:content enContent="${vlist.item_en_name}" zhContent="${vlist.item_name}" koContent="${vlist.item_kor_name}"/>
		      			</option>
					</c:forEach>
				</select>
			<%}%>
			</td>
		    <td colspan="2"  align="center" class="info_content_01" style="padding:5 5 5 5 "><iframe  width="100%" height="100%" marginwidth="0" marginheight="0" frameborder="0" name="formular_date"></iframe></td>
		  </tr>
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
<SCRIPT LANGUAGE="JavaScript">
<!--
function changedata(para){
	formular_date.location.href = "formular_data.jsp?pa_item_no="+para;
	}
function ChangeOrder(flag){
 var ID = document.all("param_no").value;
	if (ID!=''){
	location.href="<%=menu_code%>_t.jsp?menu_code=<%=menu_code%>&flag="+flag+"&item_no="+ID;
	}
	else{
	//"请选择工资项目"
		alert('<ait:message  messageID="alert.pay.select_item_list" module="pay"/>');
	}
}
//-->
</SCRIPT>
