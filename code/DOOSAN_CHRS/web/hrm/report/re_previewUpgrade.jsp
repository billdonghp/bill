<%@ page language="java" import="java.util.*,com.lgcn.web.*,com.lgcn.util.*,com.ait.hrm.entity.*" pageEncoding="UTF-8"%>
<jsp:useBean id="rd" class="com.lgcn.web.ReportDAO"></jsp:useBean>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%
    	String xlsKey = request.getParameter("xlsKey")!=null?request.getParameter("xlsKey"):"";
		String post_grade = StringUtil.toCN(request.getParameter("post_grade"));
		int year = NumberUtil.parseInt(new java.text.SimpleDateFormat("yyyy").format(new Date().getTime()));
		int temp = 0;
		if(post_grade.equals("助理工程师"))
			temp = 2 ;
		if(post_grade.equals("助理")||post_grade.equals("专员"))
			temp = 3 ;
		if(post_grade.equals("主管")||post_grade.equals("经理")||post_grade.equals("工程师"))
			temp = 4 ;
		if(post_grade.equals("技术工人")||post_grade.equals("班长"))
			temp = 5 ;
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=re_previewUpgrade.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
     %>
     <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
     	
     	<%-------------------------- 表头  -----------------------------%>
     	<tr>
     		<td align="center">工号</td>
     		<td align="center">姓名</td>
     		<td align="center">部门</td>
     		<td align="center">现职级</td>
     		<td align="center">入社日期</td>
     		<td align="center"><%=year-1 %>年评价情况</td>
     		<td align="center"><%=year-2 %>年评价情况</td>
     		<td align="center"><%=year-3 %>年评价情况</td>
   		</tr>
   		<%List list =  rd.getPreviewUpgradeList(post_grade,temp);%>
   		<%if(list.size()>0){ %>
   		<%for(int i = 0 ; i < list.size() ; i ++){ 
   			PreviewUpgradeReports pur = (PreviewUpgradeReports)list.get(i);
   		%>
   		<tr>
     		<td align="center"><%=pur.getEmpId() %></td>
     		<td align="center"><%=pur.getEmpName() %></td>
     		<td align="center"><%=pur.getDeptName() %></td>
     		<td align="center"><%=pur.getPost_grade() %></td>
     		<td align="center"><%=pur.getJoinDate() %></td>
     		<td align="center"><%=pur.getEvsGrade1() %></td>
     		<td align="center"><%=pur.getEvsGrade2() %></td>
     		<td align="center"><%=pur.getEvsGrade3() %></td>
   		</tr>
   		<%}}else{ %>
   		<tr>
     		<td align="center" colspan=8><font color=red>没有具备此条件的员工</font></td>
   		</tr>
   		<%} %>
   	</table>
</body>
</html>