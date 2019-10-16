<%@ page contentType="text/html; charset=UTF-8" language="java" 
import="com.ait.ess.entity.*,com.ait.util.*,java.util.Date,
				com.ait.ess.dao.*,java.util.*,java.text.*,
				com.ait.hr.entity.*,java.net.URLDecoder"
 errorPage="" %>
<%@ page import="com.ait.ess.entity.OTApply" %>

<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="affirmdao" class="com.ait.ess.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="otapply_list" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="otapply" class="com.ait.ess.entity.OTApply" scope="request"/>
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
 	 document.familyForm.action="/ess/ess0607.jsp?selectContent="+selectContent+"&selectCondition="+selectCondition;
     document.familyForm.submit();
	 }
 -->
</script>
<script language="javascript" src="../js/meizzDate.js"></script>
<%@ include file="/inc/hrtoolbar_null.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<body>
<%	
		Map statusMap = new HashMap();
		statusMap.put("0","未决裁");
		statusMap.put("1","已通过");
		statusMap.put("2","未通过");
		
		Map colorMap = new HashMap();		
		colorMap.put("0","#990099");		
		colorMap.put("1","#00CC00");		
		colorMap.put("2","#CC0000");	
		
		Map confirmMap = new HashMap();
		confirmMap.put("1","确认通过");
		//confirmMap.put("2","确认未通过");
		confirmMap.put("2","已否决"); // modified 2005-05-17
		
	String applyType ="OtApply";	  
	String selectContent = request.getParameter("selectContent")!=null?StringUtil.toCN(request.getParameter("selectContent")):"";
	String selectCondition = request.getParameter("selectCondition")!=null?request.getParameter("selectCondition"):"";
	String OTdateFrom=request.getParameter("OTdateFrom")!=null?request.getParameter("OTdateFrom"):"";
	String OTdateTo=request.getParameter("OTdateTo")!=null?request.getParameter("OTdateTo"):"";
	String OTdate=request.getParameter("OTdate")!=null?request.getParameter("OTdate"):"";
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
		 
	   if((!selectCondition.equals("")&&!selectContent.equals(""))&&(!OTdateFrom.equals("")||OTdateFrom!=null)&&(!OTdateTo.equals("")||OTdateTo!=null)){
	       sql =sql+ selectCondition+" '%"+selectContent+"%'"+"and to_char(apply_ot_date,'YYYY-MM-DD') between '"+OTdateFrom+"' and '"+OTdateTo+"'";
	   }else if(!OTdateFrom.equals("")&&!OTdateTo.equals("")){
	   	sql=sql+"and to_char(apply_ot_date,'YYYY-MM-DD') between '"+OTdateFrom+"' and '"+OTdateTo+"'";
	   }
	   
	   String row_sql = " ESS_APPLY_OT_V "+sql;
    //Func func = new Func();
	String search = request.getParameter("search");
	if (search == null) {
	    search = "";
	}
	String x = "";
	x = request.getParameter("strPage");
	String y = request.getParameter("bigpage");
	PageControl pc=new PageControl(10,10);
	int bigpage=pc.getTmpBig(y);
	int strPage=pc.getTmpSmall(x,bigpage);
	pc.setintPage(strPage,bigpage);
%>
<%
     String timeFormat = "yyyy-MM-dd hh:mm:ss";
     SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
     String sDate = timeFormatter.format(new Date());
	 String yearCurrentDate = StringUtil.substring(sDate, 10);
	 String errorCode = request.getParameter("errorcode");
%>

<div align="left">
<span class="title1">决裁信息查看>加班申请决裁情况</span></div>
<form name="familyForm">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
	<table>
		<tr>
			<td align="left" width="34%">
			<input type="text" name="selectContent" style="width:90px ">&nbsp;
				<select name="selectCondition">
					<option value="">全部</option>
					<option value=" and deptname like ">部门</option>
					<option value=" and empid like ">工号</option>
					<option value=" and chinesename like ">中文姓名</option>
					<option value=" and chinese_pinyin like ">拼音姓名</option>
				</select>&nbsp;&nbsp; 
			<img align="absmiddle" src="../images/btn/search.gif" onClick="javascript:clickaction();">
			<td align="right" width="34%">
				开始日期：
				<input type="text" name="OTdateFrom"  value="<%=OTdateFrom%>" onClick="setday(this);"  class="content" style="width:90px " >
			</td>
			<td align="right">
				结束日期：
				<input type="text" name="OTdateTo"  value="<%=OTdateTo%>" onClick="setday(this);"  class="content" style="width:90px " >			
			</td>
		</tr>
	</table>
</form>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td  align="center" bgcolor="#F5F5F5">工号</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">申请人</td>
    
    <td height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">加班日期</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">加班时间</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">加班类型</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">隔天</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">扣除时间</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">工作内容</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">决裁情况</td>
    <td  align="center" bgcolor="#F5F5F5">人事确认</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">删除</td>
  </tr>
  <% 
  	 pc.setRowCount(row_sql);
     otapply_list =(ArrayList)affirmdao.getApplyOTAffirmList(pc,sql);
     if(otapply_list.size()>0){
	    for (int i=0;i<otapply_list.size();i++){
		   otapply =(OTApply)otapply_list.get(i);
  %>
  <tr align="center">
    <td height="30"  align="center"><%=i+1%>&nbsp;</td>
    <td  align="center"><%=otapply.getEmpID()%>&nbsp;</td>
    <td height="30"  align="center"><%=otapply.getChineseName()%>&nbsp;</td>
    
    <td height="30"  align="center"><%=otapply.getCreateDate()%>&nbsp;</td>
    <td height="30"  align="center"><%=otapply.getOtApplyDate()%>&nbsp;</td>
    <td height="30"  align="center"><%=otapply.getOtStartTime()%>~<%=otapply.getOtEndTime()%></td>
    <td height="30"  align="center"><%=otapply.getOtType()%>&nbsp;</td>
    <td height="30"  align="center"><%=otapply.getOtNextDays()%></td>
    <td height="30"  align="center"><%=otapply.getOtDeductTime()%></td>
    <td height="30"  align="center"><%=otapply.getOtWorkContent()%>&nbsp;</td>	
	<%
		List affirmStatusList = new ArrayList();
		affirmStatusList = affirmdao.getAffirmStatusList(applyType,otapply.getOtApplySEQ());
	%>
				 <td  align="center"><%
					for(int k=0;k<affirmStatusList.size();k++){
						EssAffirm essAffirm =(EssAffirm) affirmStatusList.get(k);
						String affirmFlag = String.valueOf(essAffirm.getAffirmFlag());
						out.println("<font color=\""+colorMap.get(affirmFlag)+"\">"+essAffirm.getAffirmorName()+" " +statusMap.get(affirmFlag)+"</font><br>" );
					}
				%><%if(affirmStatusList.size()<=0){out.println("&nbsp;");}%></td>
	             <td  align="center">
				<%
					if(otapply.getActivity()==0){
						out.print("<font color=\"#FF0000\">人事未确认</font>");
					}
					
					if(otapply.getActivity()==1||otapply.getActivity()==2){
						out.print("<font color="+colorMap.get(String.valueOf(otapply.getActivity()))+">"+"人事"+ "<br>"+confirmMap.get(String.valueOf(otapply.getActivity()))+"</font>");
					}
				%></td>
    <td  height="30"  align="center">
    <%if(otapply.getActivity() == 0){%>
    <a href="/ess/ess0600_t.jsp?menu_code=<%=menu_code%>&NO=<%=otapply.getOtApplySEQ()%>&operate=OTApply&strPage=<%=x%>&selectContent=<%= selectContent%>&selectCondition=<%=selectCondition %>&selectContent=<%= selectContent%>&selectCondition="<%=selectCondition %>>删除</a>
    <%}else{%>&nbsp;<%}%>
    </td>
 
  </tr>
  <%}}%>
  <tr align="center">
    <td height="50" colspan="13"  align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
			  <td align="center">
			  	<%@ include file="/inc/page.jsp"%>
			  </td>
			</tr>
		</table>
	</td>
  </tr>
  </table>
  <br>
</body>
</html>
