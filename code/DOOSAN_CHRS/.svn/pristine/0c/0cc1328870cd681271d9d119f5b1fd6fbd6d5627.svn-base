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
			"attachment; filename=SpecialeateryReport--OverTime.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
	
	SimpleMap parameterObject = null;
	parameterObject = ObjectBindUtil.getData(request);
	
	String date = request.getParameter("date");
	request.setAttribute("date",date);
	parameterObject.set("date",date);
	
	GMDAO gd = new GMDAO();
	SimpleMap sm = null;
	SimpleMap sm1 = null;
	SimpleMap sm2 = null;
	List getAllEmpDeptRtimeInfo = gd.getOtAllEmpDeptRtimeInfo(parameterObject);
	request.setAttribute("getAllEmpDeptRtimeInfoSize",getAllEmpDeptRtimeInfo.size());
	String emptypeOt = "";
	String r_timeOt = "";
	int getOtInfoSize = 0;
	int notEat = 0;
	int Eat = 0;
	for(int i=0 ; i<getAllEmpDeptRtimeInfo.size() ; i++){
		sm = (SimpleMap) getAllEmpDeptRtimeInfo.get(i);
		String empId = sm.getString("EMPID");
		parameterObject.set("empId",empId);
		
		List getOtInfo = gd.getOtInfo(parameterObject);
		getOtInfoSize = getOtInfo.size();
		
		if(getOtInfoSize == 0){
			notEat++;
		}
		if(getOtInfoSize == 1){
			Eat++;
		}
		
		sm.set("getOtInfoSize",getOtInfoSize+"");
		for(int j=0 ; j<getOtInfo.size() ; j++){
			sm1 = (SimpleMap) getOtInfo.get(j);
			emptypeOt = sm1.getString("GM_TYPE");
			r_timeOt = sm1.getString("R_TIME");
			sm.set("emptypeOt",emptypeOt);
			sm.set("r_timeOt",r_timeOt);
		}
	}
	request.setAttribute("notEat",notEat);
	request.setAttribute("Eat",Eat);
	request.setAttribute("getAllEmpDeptRtimeInfo",getAllEmpDeptRtimeInfo);
%>
<table width="70" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="9"><b><font size="+2">${date }  加班人员就餐情况表</font></b></td>
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
				<td class="info_title_01" colspan="2" align="center" nowrap>加班</td>
				<td class="info_title_01" align="center" nowrap>加班开始时间</td>
				<td class="info_title_01" align="center" nowrap>加班结束时间</td>
			</tr>
			<% int aa = 0; %>
			<c:forEach items="${getAllEmpDeptRtimeInfo}" var="all" varStatus="i">
			<% aa++; %>
				<tr align="center">
					<td class="info_title_01" align="center" nowrap><%=aa %></td>
					<td class="info_title_01" align="center" nowrap>${all.DEPTNAME}</td>
					<td class="info_title_01" align="center" nowrap>${all.EMPID}</td>
					<td class="info_title_01" align="center" nowrap>${all.CHINESENAME}</td>
					<td class="info_title_01" align="center" nowrap>${date}</td>
					<td class="info_title_01" align="center" nowrap>
						<c:if test="${all.getOtInfoSize == '0'}">未用餐</c:if>
					</td>
					<td class="info_title_01" align="center" nowrap>
						<c:if test="${all.getOtInfoSize == '1'}">${all.emptypeOt}&nbsp;${all.r_timeOt}</c:if>
					</td>
					<td class="info_title_01" align="center" nowrap>${all.FROM_TIME}</td>
					<td class="info_title_01" align="center" nowrap>${all.TO_TIME}</td>
				</tr>
			</c:forEach>
			<tr align="center">
					<td class="info_title_01" align="center" nowrap>合计</td>
					<td class="info_title_01" align="center" nowrap>&nbsp;</td>
					<td class="info_title_01" align="center" nowrap>&nbsp;</td>
					<td class="info_title_01" align="center" nowrap>${getAllEmpDeptRtimeInfoSize}</td>
					<td class="info_title_01" align="center" nowrap>&nbsp;</td>
					<td class="info_title_01" align="center" nowrap>
							${notEat}
					</td>
					<td class="info_title_01" align="center" nowrap>
							${Eat}
					</td>
					<td class="info_title_01" align="center" nowrap>&nbsp;</td>
					<td class="info_title_01" align="center" nowrap>&nbsp;</td>
				</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
