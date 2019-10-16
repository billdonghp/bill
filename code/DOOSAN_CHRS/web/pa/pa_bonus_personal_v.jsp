<%@ page contentType="text/html; charset=UTF-8" 
	import="com.ait.hrm.business.HrmServices,
	com.ait.hrm.bean.BasicInfo,
	java.util.*,
	com.ait.pa.PaParam,
	com.ait.sqlmap.util.SimpleMap,
	com.ait.util.StringUtil"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
	<head>
		<title></title>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<link href="../css/default.css" rel="stylesheet" type="text/css">
			
			<script language="JavaScript" type="text/JavaScript">			
			function Save(){
			
				document.pa.submit();
				
			}			
			</script>		
	</head>
<body>

	<Br>
	<%
		String pamonth = request.getParameter("pamonth");
		HrmServices services=HrmServices.getInstance();
		SimpleMap deptMap=new SimpleMap();
		List dept_list=null;
		dept_list =services.retrieveDeptTree(deptMap);
		request.setAttribute("dept_list", dept_list);
		
		String personId = request.getParameter("personId");
		BasicInfo basic = (BasicInfo) services.retrieveBasicInfo(personId);
		request.setAttribute("basic", basic);
		
		String deptid = request.getParameter("deptid");
		PaParam paparam = new PaParam();
		Vector v = new Vector();
		v = paparam.getPaBonusParamList(pamonth,basic);
		request.setAttribute("PaParamList",v);
		if (v.size() !=0) {
	%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->
		
		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 个人别录入-->
					<ait:message  messageID="display.pay.maintenance.setting.import" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%"  border="0" cellpadding="0" cellspacing="0">
		<form name="pa" action="pa_personal_t.jsp" method="post">
		<input type="hidden" name="flag" value="pa_bonus_modify">
		<input type="hidden" name="menu_code" value="<%=menu_code%>">
		<input type="hidden" name="pamonth" value="<%=pamonth%>">
		<input type="hidden" name="deptid" value="">
		<tr>
		    <td class="line_gray_01" align="center">
			<table width="100%"  border="0" cellpadding="0" cellspacing="1">
			  <tr>
			  	<td  height="30" class="info_title_01" align="center" width="15%">工号</td>
			  	<td  height="30" class="info_content_01" width="10%">${basic.empID}</td>
			  	<td  height="30" class="info_title_01" align="center" width="15%">姓名</td>
			  	<td  height="30" class="info_content_01" width="10%">${basic.chineseName}</td>
			  	<td  height="30" class="info_title_01" align="center" width="15%">部门</td>
			  	<td  height="30" class="info_content_01" width="10%">${basic.department}</td>
			  	<td  height="30" class="info_title_01" align="center" width="15%">考勤月</td>
			  	<td  height="30" class="info_content_01" width="10%">${param.pamonth}</td>
			  </tr>
		      <tr><%int i=0; %>
		      	<c:forEach items="${PaParamList}" var="paParamList">
		        <td  height="30" class="info_title_01" align="center" width="15%">
		        <ait:content enContent="${paParamList.param_en_name}" zhContent="${paParamList.param_name}" koContent="${paParamList.param_kor_name}"/>
		        </td>
		        <td  height="30" class="info_content_01" width="10%">
				<input name="param_no" type="hidden" value="${paParamList.param_no}">
				<input name="param_data_no" type="hidden" value="${paParamList.param_date_no}">
				<input name="return_value" type="text" value="${paParamList.return_value}" style="width:100%">
				</td>
				<%	if ((i+1)%4==0&&(i+1)!=v.size()){ out.print("</tr><tr>");}
					i++;%>
				</c:forEach>
			<%	if (v.size()%4!=0){out.print("<td  height=\"30\" class=\"info_content_01\" colspan=\""+(4-v.size()%4)*2+"\"></td>");}
				%>
		        </tr>
				<input name="personId" type="hidden" value="<%=personId%>" />
				<input name="chineseName" type="hidden" value="<c:out value='${basic.chineseName}'/>" />
				<input name="pinyinName" type="hidden" value="<c:out value='${basic.pinyinName}'/>" />
				<input name="korName" type="hidden" value="<c:out value='${basic.koreanName}'/>" />
		    </table>
			</td>
	  </tr></form>
	</table>
	<%}%>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>

</body>
</html>
