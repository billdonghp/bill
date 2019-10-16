<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%
			int no = 0;
			
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/meizzDate.js"></script>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/calendarcode.js"></script>
<script language="javascript">
	<%
		String up_row = request.getParameter("affRow");
		if(up_row != null && !"".equals(up_row.trim()))
			out.print("alert('成功更新 "+up_row+" 条打卡记录');");
	%>
	function Update(){
	  var hh = document.form1.hh.value;
	  var mm = document.form1.mm.value;
	  var ss = document.form1.ss.value; 
	  if(hh == '00' && mm == '00' && ss == '00'){
	  	alert("[刷卡时间修改]不能都选择为'00'");
	  	
	  }else{
		  document.form1.action="/arControlServlet?operation=arrecordupdate&no=<%=no%>&menu_code=<%=request.getParameter("menu_code")%>";
		  document.form1.submit();
		  
	  }
	  
	}
	
	function Batch(){
		var inputBox = document.form1.date;
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
		showsearch.style.top = iBtop+iBheight+6;
		showsearch.style.left = iBleft;
		document.emp_list.location.href = "/inc/EssSearchEmployeeForCard.jsp?EmpIds="+document.form1.EmpIds.value
										  +"&date="+document.form1.date.value
										  +"&shh="+document.form1.shh.value
										  +"&smm="+document.form1.smm.value
										  +"&sss="+document.form1.sss.value
										  +"&ehh="+document.form1.ehh.value
										  +"&emm="+document.form1.emm.value
										  +"&ess="+document.form1.ess.value
										  +"&doorType="+document.form1.doorType.value;
		showsearch.style.visibility='visible';
	}
</script>
</head>
<body>

<div id="showsearch" style="position:absolute; top:0px; left: 0px; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
	<iframe name="emp_list" width="370" height="200"  frameborder="0" marginwidth="0" marginheight="0" hspace="0" vspace="0"></iframe>
</div>
<form name="form1" method="POST">

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
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">打卡维护</td>
			</tr>
		 </table>
		<table width="100%" border="1" cellspacing="0" cellpadding="12"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="20%" class="info_title_01">日期</td>
				<td width="80%">
					<input type="text" name="date" value="" onClick="setday(this);" readonly="true" style="width:90px " >
				</td>
			</tr>
			<tr>
				<td class="info_title_01">刷卡时间段</td>
				<td>
					<select name="shh">
						<% for(int i=0; i < 24; i++) { %>
							<option value="<%=i<10 ? "0"+i : i %>"><%=i<10 ? "0"+i : i %></option>
						<% } %>
					</select>
					 ：
					 <select name="smm">
						<% for(int i=0; i < 60; i++) { %>
							<option value="<%=i<10 ? "0"+i : i %>"><%=i<10 ? "0"+i : i %></option>
						<% } %>
					</select>
					： 
					<select name="sss">
						<% for(int i=0; i < 60; i++) { %>
							<option value="<%=i<10 ? "0"+i : i %>"><%=i<10 ? "0"+i : i %></option>
						<% } %>
					</select>&nbsp;~&nbsp;
					<select name="ehh">
						<% for(int i=0; i < 24; i++) { %>
							<option value="<%=i<10 ? "0"+i : i %>"><%=i<10 ? "0"+i : i %></option>
						<% } %>
					</select>
					 ：
					 <select name="emm">
						<% for(int i=0; i < 60; i++) { %>
							<option value="<%=i<10 ? "0"+i : i %>"><%=i<10 ? "0"+i : i %></option>
						<% } %>
					</select>
					： 
					<select name="ess">
						<% for(int i=0; i < 60; i++) { %>
							<option value="<%=i<10 ? "0"+i : i %>"><%=i<10 ? "0"+i : i %></option>
						<% } %>
					</select>
				</td>
			</tr>
			<tr>
				<td class="info_title_01">进门卡或出门卡</td>
				<td>
				<select name="doorType">
					<option value="IN">进门卡</option>
					<option value="OUT">出门卡</option>
				</select>
				</td>
			</tr>
			<tr>
				<td><a href="#" onclick="Batch();"><font color="red">员工搜索</font></a></td>
				<td><div id="EmpName">&nbsp;</div><input type="hidden" name="EmpIds" size="10" value="" /></td>
			</tr>
			<tr>
				<td class="info_title_01">刷卡时间修改</td>
				<td>
				<table width="100%" height="100%" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>
							<select name="hh">
								<% for(int i=0; i < 24; i++) { %>
									<option value="<%=i<10 ? "0"+i : i %>"><%=i<10 ? "0"+i : i %></option>
								<% } %>
							</select>
							 ：
							 <select name="mm">
								<% for(int i=0; i < 60; i++) { %>
									<option value="<%=i<10 ? "0"+i : i %>"><%=i<10 ? "0"+i : i %></option>
								<% } %>
							</select>
							： 
							<select name="ss">
								<% for(int i=0; i < 60; i++) { %>
									<option value="<%=i<10 ? "0"+i : i %>"><%=i<10 ? "0"+i : i %></option>
								<% } %>
							</select>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="info_title_01">备注</td>
				<td>
					<TEXTAREA name="remark" cols="50" rows="4"></TEXTAREA>
				</td>
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

</form>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
