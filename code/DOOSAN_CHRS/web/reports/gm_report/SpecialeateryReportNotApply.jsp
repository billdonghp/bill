<%@ page contentType="text/html; charset=UTF-8" import="com.ait.gm.dao.* ,com.ait.sqlmap.util.SimpleMap ,com.ait.sqlmap.util.ObjectBindUtil , java.util.List"%>
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
			"attachment; filename=SpecialeateryReport--NotApply.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
	
	SimpleMap parameterObject = null;
	parameterObject = ObjectBindUtil.getData(request);
	
	String date = request.getParameter("date");
	request.setAttribute("date",date);
	
	parameterObject.set("date",date);
	
	SimpleMap sm = null;
	GMDAO gd = new GMDAO();
	int a = 0;
	List noteatapply = gd.getNotEatApply(parameterObject);
	request.setAttribute("noteatapplySize",noteatapply.size()+"");
	for(int i=0 ; i<noteatapply.size() ; i++){
		sm = (SimpleMap) noteatapply.get(i);
		String gm_type = sm.getString("GM_TYPE");
		if(gm_type == null || gm_type.toString().trim().equals("")){
			a++;
		}
	}
	
	request.setAttribute("a",a);
	request.setAttribute("noteatapply",noteatapply);
	
%>
<table border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="6"><b><font size="+2">${date }  异常就餐情况统计表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">

			<tr align="center">
				<td class="info_title_01" align="center" nowrap>序号</td>
				<td class="info_title_01" align="center" nowrap>部门</td>
				<td class="info_title_01" align="center" nowrap>职号</td>
				<td class="info_title_01" align="center" nowrap>姓名</td>
				<td class="info_title_01" align="center" nowrap>日期</td>
				<td class="info_title_01" align="center" nowrap>未申请</td>
			</tr>
			<% int aa = 0; %>
			<c:forEach items="${noteatapply}" var="all" varStatus="i">
			<% aa++ ; %>
				<tr align="center">
					<td class="info_title_01" align="center" nowrap><%=aa %></td>
					<td class="info_title_01" align="center" nowrap>${all.DEPTNAME}</td>
					<td class="info_title_01" align="center" nowrap>${all.EMPID}</td>
					<td class="info_title_01" align="center" nowrap>${all.CHINESENAME}</td>
					<td class="info_title_01" align="center" nowrap>${date}</td>
					<td class="info_title_01" align="center" nowrap>${all.GM_TYPE}</td>
				</tr>
			</c:forEach>
			<tr align="center">
					<td class="info_title_01" align="center" nowrap>合计</td>
					<td class="info_title_01" align="center" nowrap>&nbsp;</td>
					<td class="info_title_01" align="center" nowrap>&nbsp;</td>
					<td class="info_title_01" align="center" nowrap>${noteatapplySize}</td>
					<td class="info_title_01" align="center" nowrap>&nbsp;</td>
					<td class="info_title_01" align="center" nowrap>${noteatapplySize-a}</td>
				</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>