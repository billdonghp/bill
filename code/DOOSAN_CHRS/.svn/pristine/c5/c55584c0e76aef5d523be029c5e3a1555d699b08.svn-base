<%@ page contentType="text/html; charset=UTF-8" import="com.ait.sqlmap.util.ObjectBindUtil"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.util.*"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="securityChecksList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
			response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=SecurityChecksReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
	
	SimpleMap parameterObject = null;
	parameterObject = ObjectBindUtil.getData(request);
	
%>
<table border="1" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="11"><b><font size="+2">安全检查记录</font></b></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
			  <td nowrap="nowrap" class="info_title_01">
				编号</td>    
		      <td nowrap="nowrap" class="info_title_01">
				检查人</td>
			  <td nowrap="nowrap" class="info_title_01">
				检查日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				分类</td>
		      <td nowrap="nowrap" class="info_title_01">
				地点</td>
			  <td nowrap="nowrap" class="info_title_01">
				隐患及不符合通报</td>
			  <td nowrap="nowrap" class="info_title_01">
				责任部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				整改要求</td>	
			  <td nowrap="nowrap" class="info_title_01">
				问题点及不符合项</td>	
		      <td nowrap="nowrap" class="info_title_01">
				要求完成日期 </td>
		      <td nowrap="nowrap" class="info_title_01">
				计划完成日期 </td>
		      <td nowrap="nowrap" class="info_title_01">
				实际完成日期 </td>
			  <td nowrap="nowrap" class="info_title_01">
				进展状态</td>	
				 	     
		    </tr>
		    <%for(int i=0;i<securityChecksList.size();i++){
		    	dataMap=(SimpleMap)securityChecksList.get(i);
		    %>
		    <tr onClick="band('#E7F0EF','black','<%=dataMap.get("DOCUMENTNO").toString()%>',<%=dataMap.get("ISCOMPLETEDRECTIFICATION")%>)" height="30">
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("DOCUMENTNO"),"&nbsp;")%></td>
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("CHINESENAME"),"&nbsp;")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("SECURITYCHECKSDATE"),"&nbsp;")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("HIDDENDANGERSTYPE_NAME"),"&nbsp;")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("LOCATION"),"&nbsp;")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("HIDDENDANGERS_NAME"),"&nbsp;")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("DEPTNAME"),"&nbsp;")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("RECTIFICATION_NAME"),"&nbsp;")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("BRIEF"),"&nbsp;")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("RECTIFICATION_DATE"),"&nbsp;")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("PLANCOMPLETIONDATE"),"&nbsp;")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("OVERDATE"),"&nbsp;")%></td>	
		     <td nowrap="nowrap" align="center">		   
		     <%if(dataMap.get("ISRECTIFICATION").equals("1")){ %><font color="#00CC00">确认完成</font><% }%>
		    	<%if(dataMap.get("ISRECTIFICATION").equals("2")){ %><font color="#990099">退回</font><% }%>
		    	<%if(dataMap.get("ISRECTIFICATION").equals("0") && (dataMap.get("ISPLAN").equals("0"))){ %><font color="#990099">已通知</font><% }%>
		    	<%if((dataMap.get("ISCOMPLETEDRECTIFICATION").equals("0")) && (dataMap.get("ISPLAN").equals("1"))){ %><font color="#990099">计划反馈</font><% }%>
		    	<%if((dataMap.get("ISCOMPLETEDRECTIFICATION").equals("1")) && (dataMap.get("ISRECTIFICATION").equals("0"))){ %><font color="#990099">整改反馈</font><% }%>
		    	<%if(dataMap.get("ISRECTIFICATION").equals("3")){ %><font color="#990099">未确认<%} %>	 
		     </td>	
		    </tr>
		    <%} %>	
		 </table>
		</td>
	</tr>
</table>
</body>
</html>