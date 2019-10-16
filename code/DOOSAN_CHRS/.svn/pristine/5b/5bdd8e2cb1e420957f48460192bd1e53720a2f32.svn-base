<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.*"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="result" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="startdate" class="java.lang.String" scope="request" />
<jsp:useBean id="enddate" class="java.lang.String" scope="request" />

<jsp:useBean id="allEateryType" class="java.util.ArrayList" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">
function Search() {
	document.ApplyForm.action="/gmControlServlet?menu_code=${param.menu_code}&operation=EateryPeople&flag=1";
	document.ApplyForm.submit();	
}


function ImportExcel() {
	document.ApplyForm.action="/gmControlServlet?operation=EateryPeople&flag=2";
	document.ApplyForm.submit();	
} 

function checkRadioReport1(){
	if(document.ApplyForm.check.value="eateryReport"){
		document.ApplyForm.action="/gmControlServlet?operation=EateryPeople&menu_code=${param.menu_code}";
		document.ApplyForm.submit();
	}
}

function checkRadioReport2(){
	if(document.ApplyForm.check.value="SpecialeateryReport"){
		document.ApplyForm.action="/gm/Specialeatery.jsp?menu_code=${menu_code}";
		document.ApplyForm.submit();	
	}
}

function checkRadioReport3(){
	if(document.ApplyForm.check.value="DifferenceDetailseateryReport"){
		document.ApplyForm.action="/gm/DifferenceDetailseateryReport.jsp?menu_code=${menu_code}";
		document.ApplyForm.submit();	
	}
}

function checkRadioReport4(){
	if(document.ApplyForm.check.value="YeareateryReport"){
		document.ApplyForm.action="/gm/YeareateryReportSet.jsp?menu_code=${menu_code}";
		document.ApplyForm.submit();	
	}
}

function checkRadioReport5(){
	if(document.ApplyForm.check.value="MontheateryReport"){
		document.ApplyForm.action="/gm/MonthEateryReportSet.jsp?menu_code=${menu_code}&applydate="+document.ApplyForm.applydate.value;
		document.ApplyForm.submit();	
	}
}
</script> 
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");		
String today=timeFormatter.format(d);
String today1=timeFormatter1.format(d);
%>
<form name="ApplyForm" action="" method="post">
<input type="hidden" name="applydate" value="<%=today%>">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_search.jsp"%>
			
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
			<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>
			</td>
		</tr>
	    <tr>
	      <td  align="center" colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
				<tr>
					<td nowrap="nowrap" align="center" class="info_title_01">
						请选择报表类型
					</td>
					<td nowrap="nowrap" align="center" class="info_content_00">
						就餐人数统计报表<input type="radio" name="check" checked="checked" value="eateryReport" onclick="checkRadioReport1()"/>
					</td>
					<td nowrap="nowrap" align="center" class="info_content_00">
						特殊情况报表<input type="radio" name="check" value="SpecialeateryReport" onclick="checkRadioReport2()"/>
					</td>
					<!--
					<td width="15%" align="center" class="info_content_00">
						就餐差异明细对比表<input type="radio" name="check" value="DifferenceDetailseateryReport" onclick="checkRadioReport3()"/>
					</td>
					-->
					<td nowrap="nowrap" align="center" class="info_content_00">
						年间就餐统计表<input type="radio" name="check" value="YeareateryReport" onclick="checkRadioReport4()"/>
					</td>
					<td nowrap="nowrap" align="center" class="info_content_00">
						就餐消费月别结算表<input type="radio" name="check" value="MontheateryReport" onclick="checkRadioReport5()"/>
					</td>
					<td nowrap="nowrap" class="info_content_00">
						<img src="/images/btn/Excel_Exp.gif" onclick="ImportExcel()"/>
					</td>
				</tr>
				<tr>
	          <td nowrap="nowrap" class="info_title_01"><!--  开始日期-->
	          	开始日期 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="startdate" value="${startdate}" onClick="setday(this);" readonly  style="width:70px">
	          </td>	          
	          <td nowrap="nowrap" class="info_content_00">
	          &nbsp;
	          </td>
	          <td nowrap="nowrap" class="info_title_01"><!--结束日期  -->
	             结束日期
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="enddate" value="${enddate}" onClick="setday(this);" readonly style="width:70px">
	          </td>
	          <td nowrap="nowrap" class="info_content_00"><!--结束日期  -->
	             &nbsp;
	          </td>
	        </tr>
	      </table>
	      </td>
		</tr>
		</table>

		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					就餐人数统计表
				</td>
			</tr>
		</table>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	  
	  <tr>
	  	<td class="info_title_01" nowrap colspan="1">&nbsp;</td>
	  	<td class="info_title_01" nowrap colspan="5">员工就餐</td>
	  	<td class="info_title_01" nowrap colspan="5">客人就餐(临时卡)</td>
	  </tr>
	  
	  <tr>
	  	<td class="info_title_01" nowrap colspan="1">部门</td>
	  	
	  	<c:forEach items="${allEateryType}" var="all" varStatus="i">
	  		<td class="info_title_01" nowrap colspan="1">${all.GM_TYPE}</td>
	  	</c:forEach>
	  	<td class="info_title_01" nowrap colspan="1">总人数</td>
	  	
	  	<c:forEach items="${allEateryType}" var="all" varStatus="i">
	  		<td class="info_title_01" nowrap colspan="1">${all.GM_TYPE}</td>
	  	</c:forEach>
	  	<td class="info_title_01" nowrap colspan="1">总人数</td>
	  </tr>
	  <%
	  		SimpleMap map = null;
	  		SimpleMap map1 = null;
	  		SimpleMap map2 = null;
	  		String dept = "";
	  		String numEmp = "";
	  		String numEmpTotal = "";
	  		String numProvisionalEmp = "";
	  		String numProvisionalTotal = "";
	  		String eateryType = "";
	  		for(int i=0 ; i<result.size() ; i++){
	  			map = (SimpleMap) result.get(i);
	  			dept = map.getString("DEPTNAME");
	  			
	  			numEmpTotal = map.getString("员工就餐合计");
	  			numProvisionalTotal = map.getString("客人就餐合计");
	  %>
	  <tr>
	  		<%if(dept != null){ %>
	  		<td align="center" style="color: #666666;">
	  			<%=dept %>
	  		</td>
	  		<%} %>
	  		<%if(dept == null){ %>
	  		<td align="center" style="color: #666666;">
	  			合计
	  		</td>
	  		<%} %>
	  			<%
	  				for(int j=0 ; j<allEateryType.size() ; j++){
	  					map1 = (SimpleMap) allEateryType.get(j);
	  					eateryType = map1.getString("GM_TYPE");
	  					numEmp = map.getString(eateryType+"人数");
	  			%>
		  		<td align="center" style="color: #666666;">
		  			<%=numEmp %>
		  		</td>
		  		<% 
	  				}
		  		%>
		  		<td align="center" style="color: #666666;">
		  			<%=numEmpTotal %>
		  		</td>
		  		<%
	  				for(int j=0 ; j<allEateryType.size() ; j++){
	  					map2 = (SimpleMap) allEateryType.get(j);
	  					eateryType = map2.getString("GM_TYPE");
	  					numProvisionalEmp = map.getString("临时"+eateryType);
	  			%>
		  		<td align="center" style="color: #666666;">
		  			<%=numProvisionalEmp %>
		  		</td>
		  		<% 
	  				}
		  		%>
		  		<td align="center" style="color: #666666;">
		  			<%=numProvisionalTotal %>
		  		</td>
	  </tr>
	  <%
	  		}
	  %>
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