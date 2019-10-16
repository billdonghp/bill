<%@ page contentType="text/html; charset=UTF-8" import="com.ait.gm.dao.* ,com.ait.sqlmap.util.SimpleMap ,com.ait.sqlmap.util.ObjectBindUtil , java.util.List,com.ait.sy.bean.AdminBean"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
			response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=SpecialeateryReport-NonNormal.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
	
	SimpleMap parameterObject = null;
	parameterObject = ObjectBindUtil.getData(request);
	AdminBean admin= new AdminBean();
	admin=(AdminBean)request.getSession(false).getAttribute("admin");
	String cpnyId = admin.getCpnyId();
	String date = request.getParameter("date");
	request.setAttribute("date",date);
	parameterObject.set("date",date);	
	parameterObject.set("cpnyId",cpnyId);	
	GMDAO gd = new GMDAO();
	List getNonNormalAllEmpRtimeInfo = gd.getNonNormalAllEmpRtimeInfo(parameterObject);
	request.setAttribute("getNonNormalAllEmpRtimeInfo",getNonNormalAllEmpRtimeInfo);
%>
<table width="70" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="8"><b><font size="+2">${date}  非正常就餐情况表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<table width="100%" border="1" cellpadding="0" cellspacing="0">

			<tr align="center">
				<td class="info_title_01" align="center" nowrap>序号</td>
				<td class="info_title_01" align="center" nowrap>职号</td>
				<td class="info_title_01" align="center" nowrap>姓名</td>
				<td class="info_title_01" align="center" nowrap>职位</td>
				<td class="info_title_01" align="center" nowrap>课组</td>
				<td class="info_title_01" align="center" nowrap>部门</td>
				<td class="info_title_01" align="center" nowrap>就餐时间</td>
				<td class="info_title_01" align="center" nowrap>下班时间</td>
			</tr>		
			<c:forEach items="${getNonNormalAllEmpRtimeInfo}" var="all" varStatus="i">			
				<tr align="center">
					<td class="info_title_01" align="center" nowrap>${i.index+1}</td>
					<td class="info_title_01" align="center" nowrap>${all.EMPID}</td>
					<td class="info_title_01" align="center" nowrap>${all.CHINESENAME}</td>
					<td class="info_title_01" align="center" nowrap>${all.CODE_NAME}</td>
					<td class="info_title_01" align="center" nowrap>${all.DEPTNAME}</td>
					<td class="info_title_01" align="center" nowrap>${all.DEPT}</td>
					<td class="info_title_01" align="center" nowrap>${all.EATTIME}</td>
					<td class="info_title_01" align="center" nowrap>${all.ARTIME}</td>
				</tr>
			</c:forEach>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
