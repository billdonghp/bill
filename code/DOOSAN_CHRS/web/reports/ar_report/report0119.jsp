<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<%@ page import="com.ait.reports.reportservices.ArReportService" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
 	response.setHeader("Content-Type",
 			"application/vnd.ms-excel; charset=UTF-8");
 	response.setHeader("Content-Disposition",
 			"attachment; filename=report0119.xls");
 	response.setHeader("Pragma", "public");
 	response.setHeader("Cache-Control", "max-age=0");

 	SimpleMap parameterObject = new SimpleMap();
 	parameterObject.setString("cpnyId", admin.getCpnyId());
 	parameterObject.setString("supervisorId", admin.getAdminID());
    
    parameterObject.setString("yearMonth", (String)request.getAttribute("yearMonth"));
    

 %>

	<body>
		
		<table border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="23" rowspan="2">
		  	 <font size="+2">${fn:substring(yearMonth, 0, 4)}年${fn:substring(yearMonth, 4, 6)}月 缺勤明细名单</font></td>
		  </tr>
		 <tr align="center">
		 </tr>
	<tr>
	<font size="+1">
		  	<td align="center"><font size="+1">部门</font></td>
		  	<td align="center"><font size="+1">丧假</font></td>
		  	<td align="center"><font size="+1">哺乳假</font></td>
		  	<td align="center"><font size="+1">产假护理假</font></td>
		  	<td align="center"><font size="+1">产前检查</font></td>
		  	<td align="center"><font size="+1">出差</font></td>
		  	<td align="center"><font size="+1">结婚登记假</font></td>
		  	<td align="center"><font size="+1">婚假</font></td>
		  	<td align="center"><font size="+1">年休假</font></td>
		  	<td align="center"><font size="+1">调休</font></td>
		  	<td align="center"><font size="+1">培训</font></td>
		  	<td align="center"><font size="+1">研修</font></td>
		  	<td align="center"><font size="+1">工伤假</font></td>
		  	<td align="center"><font size="+1">事假</font></td>
		  	<td align="center"><font size="+1">病假</font></td>
		  	<td align="center"><font size="+1">病休</font></td>
		  	<td align="center"><font size="+1">休职</font></td>
		  	<td align="center"><font size="+1">因公外出</font></td>
		  	<td align="center"><font size="+1">迟到</font></td>
		  	<td align="center"><font size="+1">早退</font></td>
		  	<td align="center"><font size="+1">旷工</font></td>
		  	<td align="center"><font size="+1">产假</font></td>
		  	<td align="center"><font size="+1">岗位保留</font></td>
		  	</font>
	</tr>
			<%
				ArReportService service = new ArReportService();

				List deptList = service.retrieveReport0119Dept(parameterObject);

				

				List tempList = new ArrayList();

				for (int j = 0; j < deptList.size(); j++) {
				SimpleMap deptMap = (SimpleMap) deptList.get(j);
			%>
			<tr>
              <td>
					
					<%=deptMap.getString("DEPTNAME")%>	
					&nbsp;
					
				</td>
				<%
					List arStaItem = service
								.retrieveReport0119ArStaItem(parameterObject);

						for (int i = 0; i < arStaItem.size(); i++) {
							
							SimpleMap arStaItemMap = (SimpleMap) arStaItem.get(i);

							parameterObject.setString("deptId", deptMap
									.getString("DEPTID"));

							parameterObject.setString("arStaItem", arStaItemMap
									.getString("AR_STA_ITEM"));
							SimpleMap arEmp = new SimpleMap();
							arEmp = service
									.retrieveReport0119ArEmpList(parameterObject);
						    request.setAttribute("arEmp",arEmp);
				%>
				<td>
					${arEmp.CONTENT}
					&nbsp;
				</td>


				<%
					}
				%>
			</tr>
			<%
				}
			%>


		</table>
	</body>
</html>

