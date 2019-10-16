<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="com.ait.util.DateUtil"%>
<%@ page import="com.ait.evs.EvsCraft"%>
<jsp:useBean id="codemap_opt" class="java.util.HashMap" scope="page" />
<jsp:useBean id="codemap_type" class="java.util.HashMap" scope="page" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
    <%@ include file="/inc/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>评价 > 基本设置 > 工种设置</title>
    
    <script language="javascript">
function Save()
{
  document.LGFORM.action="/evsControlServlet?operation=evsgzsave2&menu_code=<%=request.getParameter("menu_code")%>";
  document.LGFORM.submit();
}
</script>
    
	</head>
	<body>
		<link href="/css/default.css" rel="stylesheet" type="text/css">
		<SCRIPT LANGUAGE="JavaScript" src="/evs/js/evs0101.js"></SCRIPT>
		
		<%String evscodeid = "";
			evscodeid = request.getParameter("ID") != null ? request
					.getParameter("ID") : "";

			%>
       <%
EvsCraft evscraft=new EvsCraft();
%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
	<%@ include file="inc/evstoolbar_a_new.jsp"%>
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
		<%@ include file="inc/evs_nav.jsp"%>
		
		<!-- display content -->
		<br>
		
  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
		
	 <form name="LGFORM" action="" method="POST" >
       <input type="hidden" name="flag" value="add">
        <input type="hidden" name="codeflag" value="CT"> 
       <input type="hidden" name="menu_code" value="<%=menu_code%>">
			<tr>
				<td align="left" class="title1" colspan="10">
					工种设置</td>
			</tr>
		</table>
	
		  <table width="100%" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01">工种</td>
		      <td width="75%" class="info_content_00">${searchLine.CODE_ID}</td>
		      <input type="hidden" name="evscodeid"  value="${searchLine.CODE_ID}"/>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01"><!-- Line-->
					 工种名称</td>
		      <td width="75%" class="info_content_00"><input name="evscodename" type="text" size="20" value="${searchLine.CODE_NAME}"></td>
		    </tr>
		     <tr>
		      <td width="15%" class="info_title_01"><!-- Line-->
					 工种英文名称</td>
		      <td width="75%" class="info_content_00"><input name="evsencodename" type="text" size="20" value="${searchLine.CODE_EN_NAME}"></td>
		    </tr>
		    <tr>
		     		      
		  </tr>
		    
		    <tr>
		      <td class="info_title_01"><!-- 创建者-->
					创建者</td>
		      <td width="75%" class="info_content_00"> <%=admin.getChineseName()%><input name="empid" type="hidden" value="<%=admin.getAdminID()%>"/>
		        </td>
		    </tr>
		     <tr>
		      <td width="15%" class="info_title_01"><!-- 法人区分 -->法人区分</td>
		      <!-- <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" all="all"/>为公司ID查询列表 -->
		      <td width="75%" class="info_content_00">
		           <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" all="all" selected="${searchLine.CPNY_ID}"/>
		      </td> 
		    </tr>
		  </table>
     <table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="10">
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
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
	</body>
</html>
