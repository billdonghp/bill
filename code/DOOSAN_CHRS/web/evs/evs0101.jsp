
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
	
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" 
     scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 评价期间设置</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css"> 

<%
EvsPeriod evsPeriod=new EvsPeriod();
String ev_year="";
ev_year=request.getParameter("ev_year")!=null?request.getParameter("ev_year"):ev_year;
Calendar ca = new GregorianCalendar();
int year = ca.get(Calendar.YEAR);
if(ev_year.equals("")){
	ev_year=year+"";
}
%>
<table width="100%" border="0"   cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
		<%@ include file="../evs/inc/evstoolbar_v.jsp"%> 
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
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
				<ait:message messageID="display.mutual.search_criteria" /></td>
		</tr>
	    <tr>
	      <td >
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       		 <tr>
	       		 	<td class="info_title_01" width="80">评价年</td>
	       		 	<FORM name="LGFORM" method="POST" action="/evs/<%=menu_code%>.jsp" >
					<td height="2" align="right" class="info_content_00">
					<input type="hidden"
						name="menu_code" value="<%=menu_code%>">
					<select name="ev_year"
						onchange="LGFORM.submit();">
						<option value='' <%if(ev_year.equals("")){out.print(" selected ");}%>>评价年</option>
						<%
					List lEvYear=evsPeriod.getEvsYearList();
					if(lEvYear!=null){
					int evYearSize=lEvYear.size();
					
					for(int i=0;i<evYearSize;i++){
						String Year="";
						Year=(String)lEvYear.get(i);
					%>
						<option value=<%=Year%>
							<%if(ev_year.equals(Year)){out.print(" selected ");}%>><%=Year%></option>
						<%}}%>
					</select>
					</td>
					</FORM>
				</tr>
			</table>
	      </td>
	</tr>
	</table>
	
	<!-- display 3 level menu -->
	  <%@ include file="inc/evs_nav.jsp"%>
	<!-- display content -->
		<br>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					评价期间</td>
			</tr>
		</table>
	
	
 <table width="98%" border="0" align="center" cellpadding="0"
	       cellspacing="1">
	 <tr>
		<td class="line">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" onClick="HighLightTR('#F0F1F4','black','','')">
				<td width="15%" height="30" class="info_title_01">
				<div align="center">序号</div>
				</td>
				<td width="20%" class="info_title_01">
				<div align="center">评价年</div>
				</td>
				<td width="20%" class="info_title_01">
				<div align="center">备注</div>
				</td>
				 <td width="15%" class="info_title_01">
				<div align="center">创建日期</div>
				</td>
				 <td width="17%" class="info_title_01">
				<div align="center">创建者</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">修改日期</div>
				</td>
				 <td width="17%" class="info_title_01">
				<div align="center">修改者</div>
				</td>
			</tr>
			<%
		
		List levsPeriod=evsPeriod.getEvsPeriodByYear(ev_year);
		if(levsPeriod!=null){ 
		   for(int i=0;i<levsPeriod.size();i++){
		      evsPeriod=(EvsPeriod)levsPeriod.get(i);
		      
		      
		%>
			<tr
				onClick="HighLightTR('#F0F1F4','black','<%=evsPeriod.getEvPeriodID()%>','<%=menu_code%>')">
				<td height="30" >
				<div align="center">&nbsp;<%=i+1%></div>
				</td>
				<td>
				<div align="center" height="30" ><%=evsPeriod.getEvYear()%>&nbsp;</div>
				</td>
				<td>
				<div align="center" height="30" ><%=evsPeriod.getEvPeriodName()%>&nbsp;</div>
				</td>
				<td>
				 <div align="center" height="30" ><%=evsPeriod.getCreatetime()%>&nbsp;</div>
				</td>
				<td>
				<div align="center" height="30" ><%=evsPeriod.getEmpid()%>&nbsp;</div>							
				</td>
				<td>
				<div align="center" height="30" ><%=evsPeriod.getUpdatetime()%>&nbsp;</div>
				</td>
				<td>
				<div align="center" height="30" ><%=evsPeriod.getUempid()%>&nbsp;</div>							
				</td>
			</tr>
			<%}}%>
		</table>
		</td>
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
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
