<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<%@ page import="com.ait.reports.reportservices.ArReportService" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="arDataList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="arAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="arAffirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<%@ page import="com.ait.ar.bean.ArDetailBack"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="arDetailInfo" class="com.ait.ar.bean.ArDetailBack" scope="page" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />

<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		
		response.reset();
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0125.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		
		Date arDateStarted = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateStarted")) ;
		Date arDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateEnd")) ;
		
		
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="11" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="14" ><b><font size="+1"><fmt:formatDate value="<%= arDateStarted %>" pattern="yyyy年M月d日"/>~
		  	<fmt:formatDate value="<%= arDateEnd %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;异常勤态信息表</font></b></td>
		  </tr>
		  
		  
		  <tr align="center">
		    <td align="center">部门</td>
		  	<td align="center">所属</td>
		  	<td align="center">职号</td>
		  	<td align="center">姓名</td>
		    <td align="center">日期</td>
		    <td align="center">班次</td>
		    <td align="center">项目</td>
		    <td align="center">开始时间</td>
		    <td align="center">结束时间</td>
		    <td align="center">长度</td>
		    <td align="center">修改原由</td>
		    <td align="center" colspan="4">决裁情况</td>
		  </tr>
			<%for(int i=0;i<arDataList.size();i++){                       
			arDetailInfo = (ArDetailBack) arDataList.get(i);
			arAffirmorList = arDetailInfo.getAffirmorList();
		    %>
		    <tr>
		     <td align="center">
		     <%=arDetailInfo.getEnDept() %>
		    </td>
		    <td align="center">
		     <%=arDetailInfo.getDeptName() %>
		    </td>
		    <td align="center">
		     <%=arDetailInfo.getEmpID() %>
		    </td>
		    <td align="center">
		     <%=arDetailInfo.getChineseName() %>
		    </td>
		    <td align="center">
		     <%=arDetailInfo.getAr_date_str() %>
		    </td>
		    <td align="center">
		     <%=arDetailInfo.getShiftName() %>
		    </td>
		    <td align="center">
		     <%=arDetailInfo.getItemName() %>
		    </td>
		    <td align="center">
		    <%=StringUtil.checkNull(arDetailInfo.getFromTime(),"未刷卡")%>
		    </td>
		    <td align="center">
		    <%=StringUtil.checkNull(arDetailInfo.getToTime(),"未刷卡")%>
		    </td>
		     <td align="center">
		     <%=arDetailInfo.getQuantity() %><%=arDetailInfo.getUnitName() %>
		    </td>
		      <td align="center">
		     <%=arDetailInfo.getRemark() %>
		    </td>
		    
		      <%	for(int j=0;j<arAffirmorList.size();j++){     
			    arAffirmor = (EssAffirmor) arAffirmorList.get(j);%>   
			        <td align="left">
			    
			    	<%=arAffirmor.getAffirmLevel()+ StringUtil.checkNull(arAffirmor.getAffirmorName()) + StringUtil.getString((4 - arAffirmor.getAffirmorName().length()) , "&nbsp;&nbsp;")  + (String) statusMap.get(String.valueOf(arAffirmor.getAffirmFlag())) %>
			    	
			    	<br>
			       </td> 
			    <%}%>
			   
		    </tr>
		 
		     <% }%>
		</table>
	</body>
</html>

