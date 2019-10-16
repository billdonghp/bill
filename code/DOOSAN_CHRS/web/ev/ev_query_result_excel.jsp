<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ev.bean.EvaluateInfo"%>
<%@ page import="com.ait.ev.bean.EvaluateAffirmor"%>
<%@ page import="com.ait.util.NumberUtil"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="evAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="evAffirmor" class="com.ait.ev.bean.EvaluateAffirmor" scope="page" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />

<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />

<jsp:useBean id="itemName" class="java.lang.String" scope="request" />

<jsp:useBean id="evaluateList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="evaluateInfo" class="com.ait.ev.bean.EvaluateInfo" scope="page" />

<html>
<head>

</head>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=EvaluateResultReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<body>




<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">	
   <tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="13"><b><font size="+2">区间查询报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>	
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		  
		   	<td class="info_title_01" nowrap>序号</td>
			<td class="info_title_01" nowrap>职号</td>
		    <td class="info_title_01" nowrap>姓名</td>	
			<td class="info_title_01" nowrap>评价月份</td>	
		    <td class="info_title_01" nowrap>部门</td>
		    <td class="info_title_01" nowrap>班组</td>
		    <td class="info_title_01" nowrap>职位
		    
			 <td nowrap="nowrap" class="info_title_01">
				评价总分</td>
			<td nowrap="nowrap" class="info_title_01">
				本次排名</td>	
				
			 <td nowrap="nowrap" class="info_title_01">
				上次排名</td>	
			 <td nowrap="nowrap" class="info_title_01">
				名次变动</td>	 		
			 <td nowrap="nowrap" class="info_title_01">
				状态</td>
			<td nowrap="nowrap" class="info_title_01" colspan = "4">
				决裁情况</td>		 	
			 
			
			 
		  
			      
		  </tr>
		<%for(int i=0;i<evaluateList.size();i++){                       
			evaluateInfo = (EvaluateInfo) evaluateList.get(i);
			evAffirmorList = evaluateInfo.getAffirmorList();
		 
		  
		   
		%>
		  <tr align="center">
		  
		    <td class="info_content_09" ><%= i + 1 %></td>
		    <td class="info_content_09" >
		    <%=evaluateInfo.getEMPID() %>
		    </td>
		    <td class="info_content_09">
		    <%=evaluateInfo.getCHINESENAME() %>
		    </td>
			<td class="info_content_09">
			<%=evaluateInfo.getMONTH() %>
			</td>
			<td class="info_content_09">
			<%=evaluateInfo.getFOURTHDEPTNAME() %>
			</td>
			<td class="info_content_09" >
			<%=evaluateInfo.getDEPTNAME() %>
			</td>
			<td class="info_content_09" >
			<%=evaluateInfo.getPOST_CODE_NAME() %>
			</td>
			 
			
			   <td class="info_content_09" >
			  <%=NumberUtil.convert(evaluateInfo.getTOTAL()) %>
			  </td>
			  <td class="info_content_09" >

			    <%=StringUtil.checkNull(evaluateInfo.getPLACE()) %>/<%=StringUtil.checkNull(evaluateInfo.getAMOUNT()) %>&nbsp;
			    </td>
			    <td class="info_content_09">
			    <%=StringUtil.checkNull(evaluateInfo.getLAST_MONTH_PLACE()) %>/<%=StringUtil.checkNull(evaluateInfo.getLAST_MONTH_AMOUNT()) %>&nbsp;
			    </td>
			       <%  
			           int placeMove = 0;
			           if(NumberUtil.parseInt(evaluateInfo.getLAST_MONTH_PLACE(),0)>0){
			           		 placeMove = NumberUtil.parseInt(evaluateInfo.getPLACE(),0) - NumberUtil.parseInt(evaluateInfo.getLAST_MONTH_PLACE(),0);
			           }
		   		       
		   		   %>
			    <td class="info_content_09" >
			    <%=placeMove%>&nbsp;
			    </td>
			 
			   <td class="info_content_09" >
				<%  
			        if( evaluateInfo.getACTIVITY().equals("1")){
			    %>
					已通过
				<%  }else if(evaluateInfo.getACTIVITY().equals("2")){
		   		 %>
		   		    已否决
                <%  }else{
		   		 %>
		   		   未决裁
                <%  }
		   		 %>
				</td>
				
				  <% if("4".equals(evaluateInfo.getACTIVITY())){ %>
			 
	
			    <%	for(int j=0;j<evAffirmorList.size();j++){     
			    evAffirmor = (EvaluateAffirmor) evAffirmorList.get(j);%>   
			    <td class="info_content_10" nowrap="nowrap">    
			  <font color="<%=(String) colorMap.get(String.valueOf(0))%>">
			    	<%=evAffirmor.getAffirmLevel()+ StringUtil.checkNull(evAffirmor.getAffirmorName()) + StringUtil.getString((4 - evAffirmor.getAffirmorName().length()) , "&nbsp;&nbsp;&nbsp;&nbsp;")  %>&nbsp;&nbsp;
			    	</font><br>
			     </td>
			    <%}%>
		    
		    <% }else{%>
		     <td class="info_content_10" nowrap="nowrap">
	
			    <%	for(int j=0;j<evAffirmorList.size();j++){            
			    	evAffirmor = (EvaluateAffirmor) evAffirmorList.get(j);%>
			    	 <td class="info_content_10" nowrap="nowrap">    
			    	<font color="<%=(String) colorMap.get(String.valueOf(evAffirmor.getAffirmFlag()))%>">
			    	<%= StringUtil.checkNull(evAffirmor.getAffirmorName()) + StringUtil.getString((4 - evAffirmor.getAffirmorName().length()) , "&nbsp;&nbsp;&nbsp;&nbsp;") + StringUtil.checkNull(evAffirmor.getUpdateDate()) %>&nbsp;&nbsp;
		    		<%= statusMap.get(String.valueOf(evAffirmor.getAffirmFlag()))%>
			    	</font><br>
			         </td>
			    <%}%>
		   
             <% }%>
		     
		  </tr>
		<%}%>
		</table>
		</tr>            
		
		</table>






</body>
</html>
