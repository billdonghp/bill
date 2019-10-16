<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.util.*" %>
<%@ page import="com.ait.utils.*" %>
<%@ page import="com.ait.ess.entity.*" %>
<%@ page import="com.ait.ess.dao.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="affirmdao" class="com.ait.ess.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="leaveapply_list" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="leaveApply" class="com.ait.ess.entity.LeaveApply" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript">
 <!--hidden
 
 function clickaction (){
  	 var selectContent = document.familyForm.selectContent.value;
 	 var selectCondition = document.familyForm.selectCondition.value;
 	 document.familyForm.action="/ess/ess0608.jsp?selectContent="+selectContent+"&selectCondition="+selectCondition;
     document.familyForm.submit();
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
		//confirmMap.put("2","确认不通过");
		confirmMap.put("2","已否决"); // modified 2005-05-17
		
			
	   String applyType ="LeaveApply";

	String selectContent = request.getParameter("selectContent")!=null?StringUtil.toCN(request.getParameter("selectContent")):"";
	String selectCondition = request.getParameter("selectCondition")!=null?request.getParameter("selectCondition"):"";
	String Year=request.getParameter("Year")!=null?request.getParameter("Year"):"";
	String Month=request.getParameter("Month")!=null?request.getParameter("Month"):"";
	String OTdate=Year+Month;
	String OTdateFrom="";
	String OTdateTo="";

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

	   if((!selectCondition.equals("")&&!selectContent.equals(""))&&(!OTdate.equals("")||OTdate!=null)){
	       sql =sql + selectCondition+" '%"+selectContent+"%' "+"and to_char(APPLY_TIME,'YYYYMM') LIKE "+" '%"+OTdate+"%' ";
	   }else if(!OTdate.equals("")){
	       sql =sql + " AND TO_CHAR(APPLY_TIME,'YYYYMM') LIKE "+" '%"+OTdate+"%' ";
	   }
	String row_sql = " ESS_LEAVE_APPLY_V  "+sql;
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
<%java.util.Calendar calendar = java.util.Calendar.getInstance();
            int year = calendar.get(calendar.YEAR);
            int month;
            if(Month==""){
            	month = calendar.get(calendar.MONTH) + 1;
            	}else{
            	month=Integer.parseInt(Month);
            	}
            	
            	%>
<div align="left">
<span class="title1">决裁信息查看>休假申请决裁情况</span></div>
<form name="familyForm">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
	<table>
		<tr>
			<td align="left" width="34%"><input type="text" name="selectContent" style="width:90px ">&nbsp;
				<select name="selectCondition">
					<option value="">全部</option>
					<option value=" and deptname like ">部门</option>
					<option value=" and empid like ">工号</option>
					<option value=" and chinesename like ">中文姓名</option>
					<option value=" and chinese_pinyin like ">拼音姓名</option>
				</select>&nbsp;&nbsp; <img align="absmiddle" src="../images/btn/search.gif" onClick="javascript:clickaction();">
			</td>
			<td align="right" width="34%">
			<select name="Year" class="pamonth" values="Year">
					<%for (int i = -4; i <= 4; i++) {%>
						<option value="<%=year+i%>" <%=i==0?"selected":""%>><%=year+ i%></option>
					<%}%>
		    </select> 年 			
		    <select name="Month" class="pamonth" values="Month">
				<%for (int i = 1; i <= 12; i++) {%>
				<option	value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>"
				<%=i==month?"selected":""%>><%=("0" + i).substring(("0" + i).length() - 2, ("0" + i).length())%>
				</option>
				<%}%>
		    </select> 月  
			</td>
		</tr>
	</table>
</form>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="35" height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td width="34"  align="center" bgcolor="#F5F5F5">工号</td>
    <td width="54" height="30"  align="center" bgcolor="#F5F5F5">休假人</td>
    
    <td width="60" height="30"  align="center" bgcolor="#F5F5F5">请假类型</td>
    <td width="64" height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <%--<td width="37" height="30"  align="center" bgcolor="#F5F5F5">请假长度</td>
    --%><td width="69" height="30"  align="center" bgcolor="#F5F5F5">开始日期</td>
    <td width="78" height="30"  align="center" bgcolor="#F5F5F5">结束日期</td>
    <td width="105" height="30"  align="center" bgcolor="#F5F5F5">请假事由</td>
    <td width="81" height="30"  align="center" bgcolor="#F5F5F5">决裁情况</td>
    <td width="66"  align="center" bgcolor="#F5F5F5">人事确认</td>
    <td width="45" height="30"  align="center" bgcolor="#F5F5F5">删除</td>
  </tr>
  <% pc.setRowCount(row_sql);
     leaveapply_list =(ArrayList)affirmdao.getLeaveApplyAffirmList(pc,sql);
     if(leaveapply_list.size()>0){
	    for (int i=0;i<leaveapply_list.size();i++){
		   leaveApply =(LeaveApply)leaveapply_list.get(i);
  %>
  <tr align="center">
    <td height="30"  align="center"><%=i+1%>&nbsp;</td>
    <td  align="center"><%=StringUtil.editNbsp(leaveApply.getLeavePerson())%></td>
    <td height="30"  align="center"><%=leaveApply.getLeavePersonName()%>&nbsp;</td>
    
    <td height="30"  align="center"><%=leaveApply.getLeaveTypeCodeName()%>&nbsp;</td>
    <td height="30"  align="center"><%=leaveApply.getLeaveApplyDate()%>&nbsp;</td>
    <%--<td height="30"  align="center"><%//=leaveApply.getLeaveDays()%>&nbsp;</td>
    --%><td height="30"  align="center"><%=leaveApply.getLeaveApplyStartDate()%>&nbsp;</td>
    <td height="30"  align="center"><%=leaveApply.getLeaveApplyEndDate()%>&nbsp;</td>
    <td height="30"  align="center"><%=leaveApply.getLeaveContent()%>&nbsp;</td>
	<%
		List affirmStatusList = new ArrayList();
		affirmStatusList = affirmdao.getAffirmStatusList(applyType,leaveApply.getLeaveApplyNo());
	%>
    <td  align="center">			
		<%
			for(int k=0;k<affirmStatusList.size();k++){
				EssAffirm essAffirm =(EssAffirm) affirmStatusList.get(k);
				String affirmFlag = String.valueOf(essAffirm.getAffirmFlag());
				out.println("<font color=\""+colorMap.get(affirmFlag)+"\">"+essAffirm.getAffirmorName()+" " +statusMap.get(affirmFlag)+"</font><br>" );
			}
		%>
	<%if(affirmStatusList.size()<=0){out.println("&nbsp;");}%>
	</td>
       <td  align="center">
		<%
			if(leaveApply.getActivity()==0){
				out.print("<font color=\"#FF0000\">人事未确认</font>");
			}
			
			if(leaveApply.getActivity()==1||leaveApply.getActivity()==2){
				out.print("<font color="+colorMap.get(String.valueOf(leaveApply.getActivity()))+">"+leaveApply.getUpdatedBy()+ "<br>"+confirmMap.get(String.valueOf(leaveApply.getActivity()))+"</font>");
			}
		%>	
	</td>
	<td  height="30"  align="center">
		<%if(leaveApply.getActivity() == 0){%>
	    <a href="/ess/ess0600_t.jsp?menu_code=<%=menu_code%>&NO=<%=leaveApply.getLeaveApplyNo()%>&operate=leaveApply&strPage=<%=x%>&selectContent=<%= selectContent%>&selectCondition=<%=selectCondition %>&selectContent=<%= selectContent%>&selectCondition='<%=selectCondition %>'">删除</a>
	    <%}else{%>&nbsp;<%}%>
    </td>
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
