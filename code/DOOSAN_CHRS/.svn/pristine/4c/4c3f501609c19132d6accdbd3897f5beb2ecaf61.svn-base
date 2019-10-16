<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.util.DateUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Calendar"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价&gt;统计查看&gt;项目查看</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="inc/evstoolbar_v.jsp"%>
<%@ include file="inc/evs_nav.jsp"%>
<%String evDeptId = "";
			String evYear = "";
			String evPeriodId = "";
			evDeptId = request.getParameter("evDeptId") != null ? request
					.getParameter("evDeptId") : evDeptId;
			evYear = request.getParameter("evYear") != null ? request
					.getParameter("evYear") : evYear;
			if ("".equals(evYear)) {
				Calendar c = Calendar.getInstance();
				evYear = new Integer(c.get(Calendar.YEAR)).toString();
			}
			EvsPeriod evsP = new EvsPeriod();
			evPeriodId = evsP.getFirstEvPeriodByEvYear(evYear);
			List lEvsDept = null;
			List lEvsEmp = null;
			EvsMaster evMaster = new EvsMaster();
			try {
				lEvsDept = EvsEmp.getEvEmpDeptList();
				lEvsEmp = evMaster.getEvEmpsByMasterPeriod(evPeriodId, admin
						.getAdminID(), evDeptId);
			} catch (Exception e) {
			}
			
			%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td height="2" class="title_line_01"></td>
	</tr>
	<tr>
		<td height="2" class="title_line_02"></td>
	</tr>
	<tr>
		<td height="2" align="right"></td>
	</tr>
	<tr>
		<td class="line">
		<form action="" method="Post"
			name="evs0306">
		<table width="60%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td bgcolor="#F5F5F5">&nbsp;</td>
				<td height="30" bgcolor="#F5F5F5">评价年份</td>
				<td>
				<div align="left" height="30" class="info_content_01"><select
					name="evYear" onchange="evs0306.submit();">
					<%List lYear = DateUtil.getYearList(5);
			for (int i = 0; i < lYear.size(); i++) {
				out.print((String) lYear.get(i));
			}

			%>
				</select></div>
				</td>
			</tr>
			<!--<tr align="center">
				<td width="10%" bgcolor="#F5F5F5"><input name="radiobutton"
					type="radio" value="all" checked></td>
				<td width="21%" height="30" bgcolor="#F5F5F5">
				<div align="left">所有员工</div>
				</td>
				<td>&nbsp;</td>
			</tr>
			-->
			<tr align="left">
				<td bgcolor="#F5F5F5"><input name="radiobutton" type="radio"　
					value="dept" checked="checked"></td>
				<td height="30" bgcolor="#F5F5F5">
				<div align="center">选择部门</div>
				</td>
				<td align="left"><select name="evDeptId" onchange="evs0306.submit();">
					<option value="">评价部门</option>
					<%if (lEvsDept != null) {
				int lEvsDeptSize = lEvsDept.size();
				Hashtable dept_h = new Hashtable();
				for (int i = 0; i < lEvsDeptSize; i++) {
					dept_h = (Hashtable) lEvsDept.get(i);

					%>
					<option value="<%=(String)dept_h.get("deptId")%>"
						<%if (((String)dept_h.get("deptId")).equals(evDeptId)){%> selected
						<%}%>><%int level = Integer.parseInt((String) dept_h
							.get("deptLevel"));
					String deptname = "";
					for (int j = 0; j < level; j++) {
						deptname += "　";
					}
					out.print(deptname + (String) dept_h.get("deptName"));

				%></option>
					<%}
			}%>
				</select></td>
			</tr>
			<tr align="left">
				<td bgcolor="#F5F5F5"><input name="radiobutton" type="radio"
					value="employee"></td>
				<td height="30" bgcolor="#F5F5F5">
				<div align="center">选择员工</div>
				</td>
				<td align="left"><select name="evEmpId">
					<option value="" selected>请选择员工</option>
					<%if (lEvsEmp != null) {
				int lEvsEmpSize = lEvsEmp.size();
				out.println(lEvsEmpSize);
				for (int i = 0; i < lEvsEmpSize; i++) {
					EvsEmp evsEmp1 = (EvsEmp) lEvsEmp.get(i);

					%>
					<option value="<%=evsEmp1.getEvEmpID()%>"><%=evsEmp1.getEvEmpID()+"--"+evsEmp1.getEvEmpName()%>
					</option>
					<%}
			}%>
				</select></td>
			</tr>
			<td colspan="3" align="center">
			<img src="/images/btn/p_inquiry.gif" width="62"
				height="21" border="0" align="absmiddle" title="查看"
				onclick="view();"> <img src="/images/ev/p_excel.gif" width="62"
				height="21" border="0" align="absmiddle" title="导出EXCEL"
				onclick="excel();"></td>
			</tr>
		</table>
	<tr>
</table>
</form>
</td>
</tr>
</table>

</body>
</html>
<script language="JavaScript">
<!--
	function view(){
		document.evs0306.action="/evs/evs0306_v.jsp?menu_code=evs0306";
		document.evs0306.target="_blank";
		document.evs0306.submit();
		
	}
	function excel(){
		document.evs0306.action="/evs/excel/evs0306_excel.jsp?menu_code=evs0306";
		document.evs0306.target="_blank";
		document.evs0306.submit();
	
	}
//-->
</script>
