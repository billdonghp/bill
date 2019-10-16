<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="com.ait.util.DateUtil"%>
<jsp:useBean id="codemap_opt" class="java.util.HashMap" scope="page" />
<jsp:useBean id="codemap_type" class="java.util.HashMap" scope="page" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
    <%@ include file="/inc/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>评价 > 基本设置 > 评价期间设置</title>
	</head>
	<body>
		<link href="/css/default.css" rel="stylesheet" type="text/css">
		<SCRIPT LANGUAGE="JavaScript" src="/evs/js/evs0101.js"></SCRIPT>
		
		<%String ev_period_id = "";
			ev_period_id = request.getParameter("ID") != null ? request
					.getParameter("ID") : "";

			%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
	<%@ include file="inc/evstoolbar_a.jsp"%>
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
				<td align="left" class="title1" colspan="10">
					评价期间</td>
			</tr>
		</table>
	
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="1">
<form name="LGFORM" action="" method="POST" >
<input type="hidden" name="flag" value="add"> 
<input type="hidden" name="menu_code" value="<%=menu_code%>">

			<tr>
				<td height="2"></td>
			</tr>
			<tr>
				<td class="line">
					<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
						<tr align="center">

							<td width="25%" height="30" class="info_title_01">
								<div align="center">
									评价年
								</div>
							</td>
							<td width="25%" class="info_title_01">
								<div align="center">
									备注
								</div>
							</td>
							<!-- 查看标示取消 -->
							<!--<td width="15%" class="info_title_01">
								<div align="center">
									查看标示
								</div>
							</td>
							--><td width="50%" class="info_title_01">
							<!--	<div align="center">
									评价类型
								</div>
							</td>-->
						</tr>
						<tr>
							<td>
								<div align="center" height="30" class="info_content_01">
									<select name="ev_year">
										<%List lYear = DateUtil.getYearList(6);
											for (int i = 0; i < lYear.size(); i++) {
												out.print((String) lYear.get(i));
											}
								
											%>
									</select>
								</div>
							</td>
							<td>
								<div align="center" height="30" class="info_content_01">
									<input type="textfiled" name="ev_period_name" value="">
								</div>
							</td>
						 <!--
							 <td>
								<div height="30" class="info_content_01">

									<%Vector vType = SysCodeParser.getCodeByOrder("EVSTYPE");
										for (int i = 0; i < vType.size(); i++) {
											codemap_type = (HashMap) vType.get(i);
							
											%>
									<input type='checkbox' name='ev_type_id' value='<%=(String)codemap_type.get("code")%>'>
									<%=(String) codemap_type.get("name")%>
									<br>
									<%}
                                 
		%>
                             
								</div>
							</td>-->
						</tr>
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
