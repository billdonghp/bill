<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<%@ page import="java.util.Vector,com.ait.pa.PaCalc,com.ait.i18n.MessageSource,com.ait.util.NumberUtil"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="dataMap"  class="com.ait.sqlmap.util.SimpleMap" scope="request"></jsp:useBean>
<jsp:useBean id="planYear"  class="java.lang.String" scope="request"></jsp:useBean>

<html>
<head>
<ait:processBarJs />
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<SCRIPT LANGUAGE="JavaScript">

function synchronizePayPlan(){
 if($('baseMonth').value=="" || $('baseMonth').value==null ){
  alert("请选择工资计划年月！");
  return;
 }
  if($('planMonth').value=="" || $('planMonth').value==null ){
  alert("请同步人员计划！");
  return;
 }
 
 if($('paVersion').value=="" || $('paVersion').value==null ){
  alert("请选择已推算过的基本工资！");
  return;
 }
  if($('pbVersion').value=="" || $('pbVersion').value==null ){
  alert("请选择已推算过的奖金！");
  return;
 }
 if($('pcVersion').value=="" || $('pcVersion').value==null ){
  alert("请选择已推算过的加班费！");
  return;
 }
 if($('peVersion').value=="" || $('peVersion').value==null ){
  alert("请选择已推算过的补贴！");
  return;
 }

 document.pa.action="/paControlServlet?operation=paPlan&content=synchronizePayPlan&menu_code=pa0405";
 document.pa.submit();
}
function changeVersion(){
 document.pa.action="/paControlServlet?operation=paPlan&content=changeVersion&menu_code=pa0405";
 document.pa.submit();
}

function synchronizeCC(){

 document.pa.action="/paControlServlet?operation=paPlan&content=synchronizeCC&menu_code=pa0405";
 document.pa.submit();

}

function ImportExcel(paType){

	 if(paType=='PA' && ($('paVersion').value=="" || $('paVersion').value==null)){
	  alert("请选择已推算过的基本工资！");
	  return;
	 }
	 if(paType=='PB' && ($('pbVersion').value=="" || $('pbVersion').value==null)){
	  alert("请选择已推算过的奖金！");
	  return;
	 }
	 if(paType=='PC' && ($('pcVersion').value=="" || $('pcVersion').value==null)){
	 alert("请选择已推算过的加班费！");
	  return;
	 }
	 if(paType=='PE' && ($('peVersion').value=="" || $('peVersion').value==null)){
	 alert("请选择已推算过的补贴！");
	  return;
	 }
	 
	 document.pa.action="/paControlServlet?operation=paPlan&content=ImportExcel&menu_code=pa0405&paType="+paType;
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
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<c:if test="${errorMsg!=null}">
					<tr>
						<td align="center"><font color="red">${errorMsg}</font></td>			
			        </tr>
			</c:if>
		</table>	
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">			 
		     <tr align="center">
				<td width="15%" height="30"  class="info_title_01">计划年度</td>
				<td width="85%" class="info_content_00">
					<select name="planYear" class="pamonth">
						<%for (int i =0; i <= 1; i++){%>
							<option value="<%=year+i%>"  <%=(NumberUtil.parseInt(planYear))==(year+1)?"selected":""%>><%=year+i%></option>
						<%}%>
					</select>&nbsp;
					<!--年--><ait:message  messageID="display.mutual.year" />&nbsp;
				</td>
				<td  class="info_content_00" align="right">
				&nbsp;
				</td>
			 </tr>	
			 <tr align="center">
				<td height="30"  class="info_title_01" >同步人员计划</td>
				<td class="info_content_01">
					<a href="#"  onclick="synchronizeCC()" style="color:red">同步人员计划</a>
				</td>	
				<td  class="info_content_00" align="right">
				&nbsp;
				</td>			
			 </tr>			
			<tr align="center">
			<td width="15%" height="30"  class="info_title_01">工资计划年月</td>
			<td width="85%" class="info_content_00">					
				<select name="baseMonth" onchange="changeVersion()">
				  <option value="">请选择</option>
				  <c:forEach items="${baseMonth}" var="test" varStatus="i">
				  <option value="${test.YYYMM}" <c:if test="${test.YYYMM==YYYMM}">selected</c:if>>${test.YYYMM}</option>
				  </c:forEach>
				  </select>
				</td>
				<td  class="info_content_00" align="right">
				&nbsp;
				</td>
			</tr>
			<tr align="center">
			<td width="15%" height="30"  class="info_title_01">人员计划年月</td>
			<td width="85%" class="info_content_00">					
				<select name="planMonth">
				  <option value="">请选择</option>
				  <c:forEach items="${planMonth}" var="test" varStatus="i">
				  <option value="${test.YYYMM}" <c:if test="${test.YYYMM==YYYMM}">selected</c:if>>${test.YYYMM}</option>
				  </c:forEach>
				  </select>
				</td>
				<td  class="info_content_00" align="right">
				&nbsp;
				</td>
			</tr>
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01"> 基本工资</td>
				<td height="15"  align="center" class="info_content_00">
		         <select name="paVersion" style="width: 300px">
				  <option value="">请选择</option>
				  <c:forEach items="${paVersion}" var="test" varStatus="i">
				  <option value="${test.seqid}" <c:if test="${test.seqid==paVersionStr}">selected</c:if>>${test.versionnote}</option>
				  </c:forEach>
				  </select>
		        </td>
		        <td height="15"  align="right" class="info_content_00">	
		        <ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel('PA');" style="cursor:hand" />	         
		        </td>
			</tr>
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01">奖金</td>
				<td height="15"  align="center" class="info_content_00">
		         <select name="pbVersion" style="width: 300px">
				  <option value="">请选择</option>
				  <c:forEach items="${pbVersion}" var="test" varStatus="i">
				  <option value="${test.seqid}" <c:if test="${test.seqid==paVersionStr}">selected</c:if>>${test.versionnote}</option>
				  </c:forEach>
				  </select>
		        </td>
		        <td height="15"  align="right" class="info_content_00">	
		        <ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel('PB');" style="cursor:hand" />	         
		        </td>
			</tr>		
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01">加班费</td>
				<td height="15"  align="center" class="info_content_00">
		         <select name="pcVersion" style="width: 300px">
				  <option value="">请选择</option>
				  <c:forEach items="${pcVersion}" var="test" varStatus="i">
				  <option value="${test.seqid}" <c:if test="${test.seqid==paVersionStr}">selected</c:if>>${test.versionnote}</option>
				  </c:forEach>
				  </select>
		        </td>
		       	<td height="15"  align="right" class="info_content_00">	
		        <ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel('PC');" style="cursor:hand" />	         
		        </td>
			</tr>	
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01">补助</td>
				<td height="15"  align="center" class="info_content_00">
		         <select name="peVersion" style="width: 300px">
				  <option value="">请选择</option>
				  <c:forEach items="${peVersion}" var="test" varStatus="i">
				  <option value="${test.seqid}" <c:if test="${test.seqid==paVersionStr}">selected</c:if>>${test.versionnote}</option>
				  </c:forEach>
				  </select>
		        </td>
		        <td height="15"  align="right" class="info_content_00">	
		        <ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel('PE');" style="cursor:hand" />	         
		        </td>		       
			</tr>	
			<tr align="center">
				<td height="30"  class="info_title_01" >生成人件费计划</td>
				<td class="info_content_01">
					<a href="#"  onclick="synchronizePayPlan()" style="color:red">生成人件费计划</a>
				</td>
				<td height="15"  align="right" class="info_content_00">	
		        <ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel('END');" style="cursor:hand" />	         
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
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</form>
</body>
</html>