<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="com.ait.util.DateUtil"%>
<%@page import="com.ait.utils.ToolMenu"%>
<%@page import="com.ait.utils.MenuBiz"%>
<%@page import="com.ait.evs.EvsPeriod"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="codemap_opt" class="java.util.HashMap" scope="page"/>
<jsp:useBean id="codemap_type" class="java.util.HashMap" scope="page"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
	<%@ include file="/inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 批量操作</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">

<form name="evs0109" action="evs0109_t.jsp">
<input type="hidden" name="flag" value="batchAll">
<input type="hidden" name="menu_code" value="evs0109">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
	<%@ include file="inc/evstoolbar0109.jsp"%>
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
			<tr>
				<td align="left" class="title1" colspan="10">批量操作</td>
			</tr>
		</table>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td height="2"></td>
	</tr>
	<tr>
		<td class="line">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td width="10%" height="30" class="info_title_01">
					评价年
				</td>
				<td width="30%" class="info_title_01">
					评价期间名称
				</td>
				<td width="20%" class="info_title_01">
					流程时间增量(月)
				</td>
				<td width="40%" class="info_title_01">
					选择期间
				</td>
			</tr>
			<tr>
				<td align="center" height="30" class="info_content_01">
				<select name="evYear" >
				<%
				List lYear=DateUtil.getYearList(5);
				for(int i=0;i<lYear.size();i++){
					out.print((String)lYear.get(i));
				}
				%>
				</select>
				</td>
				<td align="center" height="30" class="info_content_01">
				<input type="textfiled" name="evPeriodName" value="">
				</td>
				<td align="center" height="30" class="info_content_01">
				<select name="monthCount" >
				<%
				for(int i=0;i<13;i++){
					out.print("<option value=\""+i+"\">"+i+"</option>");
				}
				%>
				</select>
				</td>
				<td align="center" height="30" class="info_content_01">
				<select name="evPeriodIdOld" >
				<%
					EvsPeriod evPeriod=new EvsPeriod();
					List lEvPeriod=null;
					lEvPeriod=evPeriod.getEvsPeriodByYear("");
					if(lEvPeriod!=null){
						for(int i=0,k=lEvPeriod.size();i<k;i++){
							EvsPeriod evP=(EvsPeriod)lEvPeriod.get(i);
							out.print("<option value=\""+evP.getEvPeriodID()+"\">"+evP.getEvPeriodName()+"</option>");
						}
					}
				%>
				</select>
				</td>
			</tr>
			<tr>
			<td colspan="4" align="center">
			<input type="submit" name="保存" value="保存">
			<input type="reset" name="取消" value="取消">
			</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
	<td>注：(批量操作时将为相应评价期间批量增加评价项目流程，评价部门，评价者设置，评价者) </td>
	</tr>
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
</form>
</body>
</html>
