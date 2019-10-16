<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<%@ page import="java.util.Vector,com.ait.pa.PaReport"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<%@ page import="com.ait.sy.common.PaHelper"%>
<%@ page import="com.ait.utils.FormUtil,java.util.*,com.ait.util.NumberUtil"%>
<jsp:useBean id="seqChar" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="payPlan01Serche" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap" scope="request"></jsp:useBean>
<jsp:useBean id="planyear" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="startMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="endMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="thisYearRoseMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="nextYearRoseMonth" class="java.lang.String" scope="request"></jsp:useBean>

<html>
<head>
<title>基本工资推算</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
    String pamonth = request.getParameter("pamonth");
    java.util.Calendar now = java.util.Calendar.getInstance();
	int year = now.get(now.YEAR);
	int month = now.get(now.MONTH)+1;
	if (pamonth ==null) {
	pamonth = year+("0"+month).substring(("0"+month).length()-2,("0"+month).length());
	}else{
	year = Integer.parseInt(pamonth.substring(0,4));
	month = Integer.parseInt(pamonth.substring(4,6));
	}

%>

<SCRIPT LANGUAGE="JavaScript">
var str="";
function Search(){
if(Check()!=null && Check()!=""){
alert(str);
}else{ 
 document.form1.action="/paControlServlet?operation=paPlan&content=payPlan01Serche&menu_code=pa0401";
 document.form1.submit();
}

}

function Save(){
if(Check()!=null && Check()!=""){
alert(str);
}else if(document.form1.versionNote.value=="" ||document.form1.versionNote.value==null){
alert("请输入版本信息！");
}else{ 
 document.form1.action="/paControlServlet?operation=paPlan&content=payPlan01Save&menu_code=pa0401";
 document.form1.submit();
}
}
function Check(){
if(document.form1.thisyear.value==document.form1.planyear.value){
if(document.form1.thisYearRoseMonth.value<document.form1.endMonth.value){
 return str="本年度上涨月必须大于基准月区间！";
}
if(document.form1.thisYearRate.value==""||document.form1.thisYearRate.value==null){
 return str="本年度上涨比率不能为空！";
}
}else{
if(document.form1.thisYearRoseMonth.value<document.form1.endMonth.value){
 return str="本年度上涨月必须大于基准月区间！";
}
if(document.form1.thisYearRate.value==""||document.form1.thisYearRate.value==null){
 return str="本年度上涨比率不能为空！";
}
if(document.form1.nextYearRate.value==""||document.form1.nextYearRate.value==null){
 return str="下年度上涨比率不能为空！";
}
}
}

</SCRIPT>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
String today=timeFormatter.format(d);

%>
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
	<form name="form1" action="" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		<td height="15"  align="center" class="info_title_01">
		  计划年度<select name="planyear" class="pamonth"><%
			for (int i=0;i<2;i++){
			%><option value="<%=year+i%>" <%=(NumberUtil.parseInt(planyear))==(year+1)?"selected":""%>><%=year+i%></option>
			<%}%>			
		      </select>
		      <!--年-->
			<ait:message  messageID="display.mutual.year" />			
		</td>		   
		<td height="15"  align="center" class="info_title_01">
			基准年月<%=year%>
			<input name="thisyear" value="<%=year%>" type="hidden">
					<ait:message  messageID="display.mutual.year" />
		      <select name="startMonth" class="pamonth"><%
			  	for (int i=1;i<=12;i++){
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=(NumberUtil.parseInt(startMonth))==(i)?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select>月～ 
		      <select name="endMonth" class="pamonth"><%
			  	for (int i=1;i<=12;i++){
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=(NumberUtil.parseInt(endMonth))==(i)?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select>月
		  &nbsp;&nbsp;
		   版本信息<textarea name="versionNote" style=" height: 20px;width:100px " type="_moz"  onfocus="this.style.height='40px';this.style.width='250px'" onblur="this.style.height='20px';;this.style.width='100px'"></textarea>
		  </td>
		  <td height="15"  align="center" class="info_title_01" rowspan="2" valign="bottom">		 
		  <ait:image src="/images/btn/Search.gif"  border="0" onclick="javascript:Search();" style="cursor:hand"/>		  
		  <ait:image src="/images/btn/Save.gif"  border="0" onclick="javascript:Save();" style="cursor:hand"/>
		  </td>

		 </td>
		</tr>
		<tr>
		<td height="15"  align="center" class="info_title_01">
			本年度上涨月
		    <%=year%><ait:message  messageID="display.mutual.year" />
		      <select name="thisYearRoseMonth" class="pamonth"><%
			  	for (int i=1;i<=12;i++){
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=(NumberUtil.parseInt(thisYearRoseMonth))==(i)?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select>月
		     上涨比率<input name="thisYearRate" value="${thisYearRate}" type="text" style="width:40px">%
		 </td>
		 <td height="15"  align="center" class="info_title_01">
			下年度上涨月
		    <%=year+1%><ait:message  messageID="display.mutual.year" />
		      <select name="nextYearRoseMonth" class="pamonth"><%
			  	for (int i=1;i<=12;i++){
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=(NumberUtil.parseInt(nextYearRoseMonth))==(i)?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select>月
		     上涨比率<input name="nextYearRate" value="${nextYearRate}" type="text" style="width:40px">%
		 </td>
		</tr>
		</table>
	<br>
	<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--计算结果-->
					基本工资推算</td>
			</tr>
	</table>
	<br>	
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5" style='position: relative; top: expression(this.offsetParent.scrollTop);'>
		      
		      <td nowrap="nowrap" class="info_title_01">
				编号</td>
		      <td nowrap="nowrap" class="info_title_01">
				部门</td>		      
		      <td nowrap="nowrap" class="info_title_01">
				职群</td>	
			  <td nowrap="nowrap" class="info_title_01">
				职级</td>
			  <td nowrap="nowrap" class="info_title_01">
				工资项目</td>			      
			<%for(int i=0;i<12;i++){ %>	
			  <td nowrap="nowrap" class="info_title_01">
			  <%=i+1%>月</td>	
		    <% }%>	
			 </tr>
			<%for(int i=0;i<payPlan01Serche.size();i++){ 
			dataMap =(SimpleMap)payPlan01Serche.get(i);%>
			 <tr>
			 <td nowrap="nowrap" class="info_content_00"><%=i+1%></td>	
			 <td nowrap="nowrap" class="info_content_00"><%=dataMap.get("deptname") %></td>
			 <td nowrap="nowrap" class="info_content_00"><%=dataMap.get("code_name") %></td>	
			 <td nowrap="nowrap" class="info_content_00"><%=dataMap.get("post_grade_name") %></td>	
			 <td nowrap="nowrap" class="info_content_00"><%=dataMap.get("paytype") %></td>
			 <%for(int s=1;s<13;s++){ %>			
			 <td nowrap="nowrap" class="info_content_00"><%=dataMap.get("month"+s) %></td>
			 <%} %>						 				
			 </tr>
			<%} %>		
		</table>
	</form>	
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#D0D0FF" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">

</SCRIPT>