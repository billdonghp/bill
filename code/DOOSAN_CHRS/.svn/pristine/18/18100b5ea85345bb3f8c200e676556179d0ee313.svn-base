<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.util.*,com.ait.ess.entity.*,com.ait.ess.dao.*,java.sql.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="affirmdao" class="com.ait.ess.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="matchapply_list" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="matchApply" class="com.ait.ess.entity.MatchApply" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript">
 <!--hidden
 
 function clickaction (){
     document.matchForm.submit();
	 }
 -->
</script>
<%@ include file="/inc/hrtoolbar_null.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<body>
<%  	
		Map statusMap = new HashMap();
		statusMap.put("0","未决裁");
		statusMap.put("1","已通过");
		statusMap.put("2","不通过");
		
		Map colorMap = new HashMap();		
		colorMap.put("0","#990099");		
		colorMap.put("1","#00CC00");		
		colorMap.put("2","#CC0000");
	
		
		Map confirmMap = new HashMap();
		confirmMap.put("1","确认通过");
		confirmMap.put("2","确认不通过");
		
			
	   String applyType ="MatchApply";

	String selectContent = request.getParameter("selectContent")!=null?StringUtil.toCN(request.getParameter("selectContent")):"";
	String selectCondition = request.getParameter("selectCondition")!=null?request.getParameter("selectCondition"):"";
	String sql = "where  empid in " + 
         "((SELECT empid " + 
         " FROM hr_employee_v " +
         "   WHERE empid IN (SELECT supervised_deptid " +
         "                    FROM ar_supervisor_info " +
         "                   WHERE ar_supervisor_id = '"+admin.getAdminID()+"')) " +
         "UNION (select empid from hr_employee where empid='"+admin.getAdminID()+"') UNION " +
         "((SELECT empid " +
         "    FROM hr_employee_v " +
         "   WHERE deptid IN (SELECT     deptid " +
         "                          FROM hr_department " +
         "                    START WITH deptid IN (SELECT supervised_deptid " +
         "                                            FROM ar_supervisor_info " +
         "                                           WHERE ar_supervisor_id = '"+admin.getAdminID()+"') " +
         "                    CONNECT BY PRIOR deptid = parent_dept_id)) " +
         " MINUS " +
         " (SELECT empid " +
         "    FROM hr_employee_v " +
         "   WHERE empid IN (SELECT supervised_deptid " +
         "                     FROM ar_supervisor_info " +
         "                    WHERE ar_supervisor_id <> '"+admin.getAdminID()+"')))) ";

	   if(!selectCondition.equals("")&&!selectContent.equals("")){
	       sql =sql + selectCondition+" '%"+selectContent+"%' ";
	   }
	   String row_sql = " ESS_MATCH_APPLY_V  "+sql;
    Func func = new Func();
	String search = request.getParameter("search");
	if (search == null) {
	    search = "";
	}
	String x = request.getParameter("strPage");
	String y = request.getParameter("bigpage");
	PageControl pc=new PageControl(10,10);
	int bigpage=pc.getTmpBig(y);
	int strPage=pc.getTmpSmall(x,bigpage);
	pc.setintPage(strPage,bigpage);
%>
<div align="left">
<span class="title1">决裁信息查看>值班申请决裁情况</span></div>
<form name="matchForm" action="/ess/ess0610.jsp">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
	<table>
		<tr>
			<td align="right" width="100%"><input type="text" name="selectContent" style="width:90px ">&nbsp;
				<select name="selectCondition">
					<option value="">全部</option>
					<option value=" and deptname like ">部门</option>
					<option value=" and empid like ">工号</option>
					<option value=" and chinesename like ">中文姓名</option>
					<option value=" and chinese_pinyin like ">拼音姓名</option>
				</select>&nbsp;&nbsp; <img align="absmiddle" src="../images/btn/search.gif" onClick="javascript:clickaction();">
			</td>
		</tr>
	</table>
</form>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">值班人</td>
    <td  align="center" bgcolor="#F5F5F5">工号</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">值班类型</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <!--  td height="30"  align="center" bgcolor="#F5F5F5">休假长度</td -->
    <td height="30"  align="center" bgcolor="#F5F5F5">开始日期</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">结束日期</td>
    <!--  td height="30"  align="center" bgcolor="#F5F5F5">休假事由</td -->
    <td height="30"  align="center" bgcolor="#F5F5F5">决裁情况</td>
    <td  align="center" bgcolor="#F5F5F5">人事确认</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">删除</td>
  </tr>
  <% pc.setRowCount(row_sql);
     matchapply_list =(ArrayList)affirmdao.getMatchApplyAffirmList(pc,sql);
     if(matchapply_list.size()>0){
	    for (int i=0;i<matchapply_list.size();i++){
		   matchApply =(MatchApply)matchapply_list.get(i);
  %>
  <tr align="center">
    <td height="30"  align="center"><%=i+1%>&nbsp;</td>
    <td height="30"  align="center"><%=StringUtil.toUnicode(StringUtil.toISO(matchApply.getMatchPersonName()),"UTF-8")%>&nbsp;</td>
    <td  align="center"><%=matchApply.getMatchPerson()%>&nbsp;</td>
    <td height="30"  align="center"><%=matchApply.getMatchTypeCodeName()%>&nbsp;</td>
    <td height="30"  align="center"><%=matchApply.getMatchApplyDate()%>&nbsp;</td>
    <!-- td height="30"  align="center"><  %  = matchApply.getMatchDays()%>&nbsp;</td -->
    <td height="30"  align="center"><%=matchApply.getMatchApplyStartDate()%>&nbsp;</td>
    <td height="30"  align="center"><%=matchApply.getMatchApplyEndDate()%>&nbsp;</td>
    <!-- td height="30"  align="center"><! %=matchApply.getLeaveContent()%>&nbsp;</td --> 
	<%
		List affirmStatusList = new ArrayList();
		affirmStatusList = affirmdao.getAffirmStatusList(applyType,matchApply.getMatchApplyNo());
	%>
	        <td  align="center">			
				<%
					for(int k=0;k<affirmStatusList.size();k++){
						EssAffirm essAffirm =(EssAffirm) affirmStatusList.get(k);
						String affirmFlag = String.valueOf(essAffirm.getAffirmFlag());
						out.println("<font color=\""+colorMap.get(affirmFlag)+"\">"+essAffirm.getAffirmorName()+" " +statusMap.get(affirmFlag)+"</font><br>" );
					}
				%>
			<%if(affirmStatusList.size()<=0){out.println("&nbsp;");}%></td>
	        <td  align="center">
				<%
					if(matchApply.getActivity()==0){
						out.print("<font color=\"#FF0000\">人事未确认</font>");
					}					
					if(matchApply.getActivity()==1||matchApply.getActivity()==2){
						out.print("<font color="+colorMap.get(String.valueOf(matchApply.getActivity()))+">"+matchApply.getUpdatedBy()+ "<br>"+confirmMap.get(String.valueOf(matchApply.getActivity()))+"</font>");
					}
				%>	</td>
    <td  height="30"  align="center"><a href="/ess/ess0600_t.jsp?menu_code=<%=menu_code%>&NO=<%=matchApply.getMatchApplyNo()%>&operate=matchApply">删除</a></td>
  </tr>
  <%}}%>
   <tr align="center">
    <td height="50" colspan="13"  align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
			  <td align="center"><%@ include file="/inc/page.jsp"%></td>
			</tr>
		</table>
	</td>
  </tr>
 
  </table>
</body>
</html>
