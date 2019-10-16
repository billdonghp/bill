<%@page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@page import="com.ait.util.StringUtil"%>
<%@page import="com.ait.evs.EvsColumn"%>
<%@page import="com.ait.evs.EvsPeriod"%>
<%@page import="com.ait.evs.EvsMaster"%>
<%@page import="com.ait.evs.EvsEmp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Calendar"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价&gt;统计查看&gt;项目查看</title>
<style type="text/css">
  <!--
    .style1 {color: #FF00FF}
  -->
</style>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%String itemCode1 = "EVITEM001";
int no_count=0;
			String evDeptId = "";
			String evYear = "";
			String evPeriodId = "";
			String evEmpId = "";
			List lEvsEmp = null;
			EvsMaster evMaster = new EvsMaster();
			evYear = request.getParameter("evYear") != null ? request
					.getParameter("evYear") : evYear;
			if ("".equals(evYear)) {
				Calendar c = Calendar.getInstance();
				evYear = new Integer(c.get(Calendar.YEAR)).toString();
			}
			EvsPeriod evsP = new EvsPeriod();
			evPeriodId = evsP.getFirstEvPeriodByEvYear(evYear);
			if ("dept".equals(StringUtil.checkNull(request
					.getParameter("radiobutton")))) {
				evDeptId = request.getParameter("evDeptId") != null ? request
						.getParameter("evDeptId") : evDeptId;
				try {
					lEvsEmp = evMaster.getEvEmpsByMasterPeriod(evPeriodId,
							admin.getAdminID(), evDeptId, "", "");
				} catch (Exception e) {
				}
			}
			if ("employee".equals(StringUtil.checkNull(request
					.getParameter("radiobutton")))) {
				evEmpId = StringUtil.checkNull(request.getParameter("evEmpId"));
				lEvsEmp = new Vector();
				EvsEmp evEmp = new EvsEmp(evEmpId, evPeriodId);
				evEmp.getEvEmpByEmpIdPeriod();
				lEvsEmp.add(evEmp);
			}
			EvsColumn evsColumn = new EvsColumn();
			if (null != lEvsEmp) {
				for (int e = 0, m = lEvsEmp.size(); e < m; e++) {
					EvsEmp evsEmp = (EvsEmp) lEvsEmp.get(e);

					%>
<table width="100%" border="0">
<tr>
<td>					

<table width="100%" border="1" align="right" cellpadding="1"
	cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
		<tr bgcolor="#F5F5F5"　>
		<td colspan="11"><font style="bold"><strong>部门:<%=evsEmp.getEvDeptName()%>		姓名:<%=evsEmp.getEvEmpName()%>		工号:<%=evsEmp.getEvEmpID()%></strong></font></td>
	</tr>
	<tr bgcolor="#F5F5F5">
		<!--<td rowspan="2" align="center" valign="middle" width="80">目标类别</td>
		--><td rowspan="2" align="center" valign="middle" width="30">序号</td>
		<td rowspan="2" align="center" valign="middle" width="100">评价项目</td>
		<td rowspan="2" align="center" valign="middle" width="120">细分评价内容</td>
		<td rowspan="2" align="center" valign="middle" width="30">比重%</td>
		<td colspan="3" align="center" valign="middle" width="200">详细目标</td>
		<td colspan="3" align="center" valign="middle" width="200">评价尺度(年度目标）</td>
	</tr>
	<tr bgcolor="#F5F5F5">
		<td align="center">上半年目标</td>
		<td align="center">下半年目标</td>
		<td align="center">全年度目标</td>
		<td align="center">上</td>
		<td align="center">中</td>
		<td align="center">下</td>
	</tr>
	<tr>
		<%Vector detail_v1 = evsColumn.getDetailIDByItemID(
							evPeriodId, evsEmp.getEvEmpID(), itemCode1);
					if (detail_v1.size() > 0) {
						
%>
		<!--<td align="center" valign="middle" rowspan="<%//=detail_v1.size()+1%>">业务目标</td>
		--><%}else{
		no_count++;
		%>
		<td colspan="10"><font color="red">目标未录入！</font></td>
		<%
		}
					for (int i = 0; i < detail_v1.size(); i++) {
						int detial_seq = ((Integer) detail_v1.get(i))
								.intValue();
						Hashtable column_h = evsColumn
								.getYearEvsColumnByDetailID(detial_seq);
						%>
	<tr>
		<%if (column_h != null) {%>
		<td align="center"><%=i + 1%></td>
		<td align="center">&nbsp;<%=column_h.get("column001")%></td>
		<td align="center">&nbsp;<%=column_h.get("column002")%></td>
		<td align="center">&nbsp;<a id="ColumnDetailProp"><%=column_h.get("detailProp")%></a></td>
		<td align="center">&nbsp;<%=column_h.get("column003")%></td>
		<td align="center">&nbsp;<%=column_h.get("column004")%></td>
		<td align="center">&nbsp;<%=column_h.get("column005")%></td>
		<td align="center">&nbsp;<%=column_h.get("column006")%></td>
		<td align="center">&nbsp;<%=column_h.get("column007")%></td>
		<td align="center">&nbsp;<%=column_h.get("column008")%></td>
		<%}else{%>
		<td colspan="10"><font color="red">无！</font></td>	
		<%}%>
	</tr>
	<%}%>
</table>
</td>
</tr>
</table>
<%}
			}

		%>
共有 <%=no_count%>　人目标未输入		
</body>
</html>
