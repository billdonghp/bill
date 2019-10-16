<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssOverTimeBean"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="overTimeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essOverTimeBean" class="com.ait.ess.bean.EssOverTimeBean" scope="page" />
<jsp:useBean id="essAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="essAffirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<jsp:useBean id="sDate" class="java.lang.String" scope="request" />
<jsp:useBean id="eDate" class="java.lang.String" scope="request" />
<html>
<head>
 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=detailStatisticsReport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
 %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">			
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	  <tr>	  	
	    <td class="info_title_01" nowrap>序号</td>
	    <td class="info_title_01" nowrap>工号	      
	    </td>
	    <td class="info_title_01" nowrap>加班人
	       
	    </td>
	    <td class="info_title_01" nowrap>课组
	      课组
	    </td>
	    <td class="info_title_01" nowrap>部门
	     
	    </td>
	    <td class="info_title_01" nowrap>职位
	  
	    </td>
	    <td class="info_title_01" nowrap>申请日期
	    
	    </td>
	    <td class="info_title_01" nowrap>班次日期
	    </td>
	    <td class="info_title_01" nowrap>加班类型 
	    
	    </td>
	    <td class="info_title_01" nowrap>加班时段
	   
	    </td>
	    <td class="info_title_01" nowrap>加班长度
	     
	    </td>  
	    <td class="info_title_01" nowrap>决裁情况
			
	   	<td class="info_title_01" nowrap>工作内容
			
	    </td>	  
	  </tr>
	<%for(int i=0;i<overTimeList.size();i++){
		essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);%>
	    <td class="info_content_01" nowrap><%= i + 1 %></td>
	    <td class="info_content_01" nowrap><%=essOverTimeBean.getEmpId()%></td>
	    <td class="info_content_01" nowrap>	      
		    <%=StringUtil.checkNull(essOverTimeBean.getChineseName()) %>
	   </td>
	    <td class="info_content_01" nowrap><%=StringUtil.checkNull(essOverTimeBean.getDeptName()) %>&nbsp;</td>
	    <td class="info_content_01" nowrap><%=StringUtil.checkNull(essOverTimeBean.getFourthDeptName()) %>&nbsp;</td>
	    <td class="info_content_01" nowrap><%=StringUtil.checkNull(essOverTimeBean.getPosition()) %>
	    </td>
	    <td class="info_content_01" nowrap><%=essOverTimeBean.getCreateDate()%></td>
	    <td class="info_content_01" nowrap><%= essOverTimeBean.getOtDate() %></td>
	    <td class="info_content_01" nowrap>
	       	<%if(essOverTimeBean.isForceType())out.println("<font color=\"red\">"); %> 	    	
		  <%=StringUtil.checkNull(essOverTimeBean.getOtTypeName()) %>   
	    	<%if(essOverTimeBean.isForceType())out.println("</font>"); %>
	   	</td>
	   	
	   	<% if(essOverTimeBean.getOtTypeCode().equals("WorkingOtType01")) 
	   		{
	   	%>
	    	<td class="info_content_01" nowrap><%=essOverTimeBean.getOtFromTime()%><br><%=essOverTimeBean.getOtToTime()%></td>
	    
	    <%	}
	   		else
	   		{
	   	%>
	   		<td class="info_content_01" nowrap><%= essOverTimeBean.getOtDate() %></td>
	   	<% 
	   		}
	    %>	
	    
	    <td class="info_content_01" nowrap><%=essOverTimeBean.getOtLength()%></td>
	    <td class="info_content_01" nowrap>
		    <%essAffirmorList = essOverTimeBean.getAffirmorList();
		    if (essAffirmorList.size() > 0) {
			    for(int j=0;j<essAffirmorList.size();j++){
			    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
			    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
		    		<%=essAffirmor.getAffirmorName() + " " +  StringUtil.checkNull(essAffirmor.getUpdateDate()) + " " + (String) statusMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>
			    	</font><br>
			    <%}
			} else {%>
				&nbsp;
			<%}%>
	    </td>    
	    <td align="center" nowrap><%= StringUtil.checkNull(essOverTimeBean.getOtRemark()) %></td>
	  </tr>
	<%}%>
</table>
</body>
</html>
