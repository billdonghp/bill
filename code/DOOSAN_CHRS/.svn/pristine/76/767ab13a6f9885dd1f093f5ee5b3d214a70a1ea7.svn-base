<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ait.ar.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ait.util.*"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="arData" scope="request" class="java.util.ArrayList" />

<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	request.setAttribute("cpnyDiff",admin1.getCpnyId());
	int year = Integer.parseInt(request.getAttribute("year").toString());
	int month = Integer.parseInt(request.getAttribute("month").toString());
	String key = request.getAttribute("key").toString();
	List<SimpleMap> columns = (List<SimpleMap>)request.getAttribute("columns");
	SimpleMap arRow = new SimpleMap();
	
%>
<script language="javascript">

function ImportExcel()
{
	document.form1.action="/arControlServlet?operation=exportArHistoryReport&menu_code=${param.menu_code}";
  	document.form1.submit(); 
}

function Save() {
  
  	var checkList = document.getElementsByName("check") ;
  	var checkSize = checkList.length ;
  	var flag = false ;
  	
  	for (var i = 0 ; i < checkSize ; ++i)
  	{
  		if (checkList[i].checked)
  		{
  			flag = true ;
  			break ;
  		}
  	}
  	
  	if (!flag) {
  		alert("请选择要修改的数据!!!");
  		return;
  	}
  	
  	
  	document.form1.action="/arControlServlet?operation=historyupd&menu_code=ar0106&currentPage=<%=request.getAttribute("currentPage")%>";
  	document.form1.submit(); 
}


function Search() {
  document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=monthly_v&menu_code=ar0106";
  document.form1.submit();
}

function CheckAll() {
	if (document.form1.check) {
		if (document.form1.check[0]) {
			for (i=0;i<document.form1.check.length;i++)
				document.form1.check[i].checked = document.form1.checkAll.checked;
		} else {
			document.form1.check.checked = document.form1.checkAll.checked;
		}
	} 
}
</script>
<form name="form1" method="post" action="">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_s_2.jsp"%>
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
			<td class="title1"><!-- 查询条件-->
					<ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	       		<tr>
					<td class="info_title_01" ><!-- 考勤月-->
					<ait:message  messageID="display.att.maintenance.personal_calendar.timing" module="ar"/></td>
				    <td class="info_content_00" >
				    <select name="year" class="pamonth">
					<%for (int i=-4;i<=4;i++){%>
						<option value="<%=year+i%>" <%=i==0?"selected":""%>><%=year+i%></option>
					<%}%>
				      </select><!-- 年-->
					<ait:message  messageID="display.mutual.year"/>&nbsp;&nbsp;
				      <select name="month" class="pamonth">
				    <%for (int i=1;i<=12;i++){%>
				    	<option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
					<%}%>
				      </select>
					</td>
					<td class="info_title_01" ><!-- 员工区分 -->员工区分</td>
					<td class="info_content_00" ><ait:empDiff  name="empDiffType"  cpnyDiff="${cpnyDiff}" selected="${empDiffType}" all="all"/> </td>
					<td class="info_title_01" ><!-- 部门 -->
					<ait:message  messageID="display.mutual.department"/></td>
					<td class="info_content_00" ><ait:selDept name="deptID" selected="${deptID}" all="请选择"  supervisorType="ar"/></td>
					<td class="info_title_01" ><!-- 工号/姓名-->
					<ait:message  messageID="display.mutual.emp_no_name"/></td>
				    <td class="info_content_00" ><input name="key" type="text" size="10" maxlength="10" value="<%=key%>" align="middle"></td>
				    <td class="info_content_00" ><ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel();" style="cursor:hand" /></td>
				</tr>
			</table>
	      </td>
		</tr>
		</table>
			
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--考勤汇总-->
					<ait:message  messageID="display.att.maintenance.sheet.total_sheet" module="ar"/></td>
			</tr>
		  </table>
		  <table width = "100%" border="1" cellspacing="0" cellpadding="1" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr bgcolor="#F5F5F5">
				<TD class="info_title_01"><input type="checkbox" name="checkAll" onClick="CheckAll();" /></TD>
				<TD class="info_title_00" style="text-align:right"><!-- 工号-->
					<ait:message  messageID="display.mutual.emp_id"/></TD>
				<TD class="info_title_00"  style="text-align:right"><!-- 姓名-->
					<ait:message  messageID="display.mutual.name"/></TD>
			<c:forEach items="${columns}" var="column" varStatus="i">
					<td class="info_title_00" style="text-align:right"><ait:content enContent="${column.ITEM_EN_NAME}" zhContent="${column.ITEM_NAME}" koContent="${column.ITEM_KOR_NAME}"/>
					(${column.UNIT})
					&nbsp;</td>
			</c:forEach>
		    </tr>
		<%for(int i=0; i<arData.size();i++) {
			arRow = (SimpleMap) arData.get(i); %>
			<tr>
				<td>			
				<% if( arRow.getInt("MOTHLYLOCK") == 0 ){ %>
				<input type="checkbox" name="check" value="<%=arRow.getString("EMPID")%>" />
				<%} else { %>
					&nbsp;
				<% } %>
				</td>
				<td nowrap align="center"><%=arRow.getString("EMPID")%></td>
				<td nowrap align="center">
				<%if(admin1.getLanguagePreference().equals("zh")){%>
					<%=arRow.getString("CHINESENAME")%> 
				<%}else if(admin1.getLanguagePreference().equals("ko")){%>
					<%=arRow.getString("KOREANNAME")%>
				<%}else{%>
					<%=arRow.getString("PINYINNAME")%>
				<%} %>
				
				</td>
			<%for(SimpleMap column:columns) {%>
				<td align="center">
				<input type="text" name="<%=column.getString("COLUMN_NAME")+"_"+arRow.getString("EMPID")%>" value="<%=arRow.getString(column.getString("COLUMN_NAME"))%>" style="width:30px" onChange="javascript:if(document.form1.check[<%=i%>]){document.form1.check[<%=i%>].checked=true;}else{document.form1.check.checked=true;}"/>
				</td>
			<%}%>
			</tr>
		<%}%>
		      <!-- Page Navigation Start-->
			        <ait:page       
			               link="/arControlServlet"
			               parameters="&operation=ar_pagecontrol&page=monthly_v&menu_code=ar0106&key=${key}&arMonth=${arMonth}&year=${year}&month=${month}&deptID=${deptID}&empDiffType=${empDiffType}" 
			               total="${resultCount}"
			               currentpage="${currentPage}"
			               pagesize= "${pageSize}"
			               beginlabel="paging_prv10"
			               endlabel="paging_next10"
			               prevlabel="paging_prv"
			               nextlabel="paging_next"
			               pagegroupsize="${pageGroupsize}"
			               useJS="false"/>      
			            
			            <!-- Page Navigation End -->
		  
		</form>
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(arData) < 3}">
				<c:forEach var="i" begin="1" end="${3-fn:length(arData)}"
					step="1">
					<tr>
						<td class="info_content_01" height="30"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<input type="hidden" name="statNo" value="0" />
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
