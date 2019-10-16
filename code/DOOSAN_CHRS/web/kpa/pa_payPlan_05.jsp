<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<%@ page import="java.util.Vector,com.ait.pa.PaCalc,com.ait.i18n.MessageSource,com.ait.util.NumberUtil"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="paVersion" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="pbVersion" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="pcVersion" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="peVersion" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap"  class="com.ait.sqlmap.util.SimpleMap" scope="request"></jsp:useBean>
<html>
<head>
<ait:processBarJs />
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<SCRIPT LANGUAGE="JavaScript">
function paVersionChange(str){
document.getElementById("paDiv").innerHTML=str;
}
function pbVersionChange(str){
document.getElementById("pbDiv").innerHTML=str;
}
function pcVersionChange(str){
document.getElementById("pcDiv").innerHTML=str;
}
function peVersionChange(str){
document.getElementById("peDiv").innerHTML=str;
}

function synchronizePayPlan(){
 document.pa.action="/paControlServlet?operation=paPlan&content=synchronizePayPlan&menu_code=pa0405";
 document.pa.submit();
}
</SCRIPT></head>
<body>
<%
String pamonth = request.getParameter("pamonth");
String statTypeCode = request.getParameter("statTypeCode") ;
String retroactiveTaxType = request.getParameter("retroactiveTaxType") ;
java.util.Calendar now = java.util.Calendar.getInstance();
int year = now.get(Calendar.YEAR);
int month = now.get(Calendar.MONTH) + 1;
if (pamonth == null) {
	pamonth = year + ("0" + month).substring(("0" + month).length() - 2, ("0" + month).length());
} else {
	year = Integer.parseInt(pamonth.substring(0,4));
	month = Integer.parseInt(pamonth.substring(4,6));
}
%>
<form name="pa" method="post" action="">
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
				<td align="left" class="title1" colspan="10">人件费计划</td>
			</tr>
		</table>		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		     <tr align="center">
				<td width="15%" height="30"  class="info_title_01">计划年度</td>
				<td width="85%" class="info_content_00">
					<select name="planyear" class="pamonth">
						<%for (int i =0; i <= 1; i++){%>
							<option value="<%=year+i%>"<%=i==0?" selected":""%>><%=year+i%></option>
						<%}%>
					</select>&nbsp;
					<!--年--><ait:message  messageID="display.mutual.year" />&nbsp;
				</td>
			 </tr>				
			<tr align="center">
			<td width="15%" height="30"  class="info_title_01">基准年月</td>
			<td width="85%" class="info_content_00">					
					基准年月<%=year%>&nbsp;<select name="startMonth" class="pamonth">
						<%for (int i=1;i<=12;i++){%>
							<option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>"><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
						<%}%>
					</select>&nbsp;
					<!--月--><ait:message  messageID="display.mutual.month" />~
					<select name="endMonth" class="pamonth">
						<%for (int i=1;i<=12;i++){%>
							<option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
						<%}%>
					</select>&nbsp;
					<!--月--><ait:message  messageID="display.mutual.month" />&nbsp;
				</td>
			</tr>
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01"> 基本工资</td>
				<td height="15"  align="center" class="info_content_00">
		        <select name="paVersion" onchange="paVersionChange(this.value)"><option value="">请选择</option><%for(int i=0;i<paVersion.size();i++){ dataMap=(SimpleMap)paVersion.get(i);%><option value="<%=dataMap.get("seqid")%>,<%=dataMap.get("versionnote")%>"><%=dataMap.get("seqid")%></option><%}%></select><div id="paDiv"></div>
		        </td>
			</tr>
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01">奖金</td>
				<td height="15"  align="center" class="info_content_00">
		        <select name="pbVersion" onchange="pbVersionChange(this.value)"><option value="">请选择</option><%for(int i=0;i<pbVersion.size();i++){ dataMap=(SimpleMap)pbVersion.get(i);%><option value="<%=dataMap.get("seqid")%>,<%=dataMap.get("versionnote")%>"><%=dataMap.get("seqid")%></option><%}%></select><div id="pbDiv"></div>
		        </td>
			</tr>		
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01">加班费</td>
				<td height="15"  align="center" class="info_content_00">
		        <select name="pcVersion" onchange="pcVersionChange(this.value)"><option value="">请选择</option><%for(int i=0;i<pcVersion.size();i++){ dataMap=(SimpleMap)pcVersion.get(i);%><option value="<%=dataMap.get("seqid")%>,<%=dataMap.get("versionnote")%>"><%=dataMap.get("seqid")%></option><%}%></select><div id="pcDiv"></div>
		        </td>
			</tr>	
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01">补助</td>
				<td height="15"  align="center" class="info_content_00">
		        <select name="peVersion" onchange="peVersionChange(this.value)"><option value="">请选择</option><%for(int i=0;i<peVersion.size();i++){ dataMap=(SimpleMap)peVersion.get(i);%><option value="<%=dataMap.get("seqid")%>,<%=dataMap.get("versionnote")%>"><%=dataMap.get("seqid")%></option><%}%></select><div id="peDiv"></div>
		        </td>
			</tr>	
			<tr align="center">
				<td height="30"  class="info_title_01" >生成人件费计划</td>
				<td class="info_content_01">
					<a href="#"  onclick="synchronizePayPlan()">生成人件费计划</a>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:forEach var="i" begin="1" end="9" step="1">
				<tr>
					<td class="info_content_01" height="30"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
				</tr>
			</c:forEach>
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
		<td bgcolor="#D0D0FF" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</form>
</body>
</html>