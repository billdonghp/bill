<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.entity.*,com.ait.util.*,com.ait.ess.dao.*,java.util.*,com.ait.hr.entity.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/> 
<jsp:useBean id="matchapply" class="com.ait.ess.entity.MatchApply" scope="request"/> 
<jsp:useBean id="matchapply_list" class="java.util.ArrayList" scope="request"/> 
<jsp:useBean id="affirmdao" class="com.ait.ess.dao.AffirmDAO" scope="request"/> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript">
 <!--hidden
 
 function clickaction (){
     document.familyForm.submit();
	 }
 -->
</script>
<%!
	public boolean affirmed(String adminId,List affirmorList){
		for(int i =0; i<affirmorList.size(); i++){			
			EssAffirm essAffirm =(EssAffirm) affirmorList.get(i);
			if(adminId.equals(essAffirm.getAffirmorId()) && essAffirm.getAffirmFlag() != 0 ){
				return true;
			}
		}
		return false;
	}
%>
<body>
<%@ include file="/inc/hrtoolbar_null.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<%		
		Map statusMap = new HashMap();
		statusMap.put("0","未决裁");
		statusMap.put("1","已通过");
		statusMap.put("2","不通过");
		
		Map colorMap = new HashMap();		
		colorMap.put("0","#990099");		
		colorMap.put("1","#00CC00");		
		colorMap.put("2","#CC0000");
		
	   String selectContent = request.getParameter("selectContent")!=null?StringUtil.toCN(request.getParameter("selectContent")):"";
	   String selectCondition = request.getParameter("selectCondition")!=null?request.getParameter("selectCondition"):"";
	   String Year=request.getParameter("Year")!=null?request.getParameter("Year"):"";
	   String Month=request.getParameter("Month")!=null?request.getParameter("Month"):"";
	   String OTdate=Year+Month;
	   String applyType ="MatchApply";
	   
	   //1级决裁者没有决裁的情况下，或者已经否决的情况下，2级决裁者就看不到此条请假记录，依此类推。 
	   String sql ="where  match_no in (select apply_no from ess_affirm eo  "+
	               "where AFFIRMOR_ID = '"+admin.getAdminID()+"' and   "+
				   "APPLY_TYPE = '"+applyType+"' and not "+
				   "exists(select * from ess_affirm ei   "+
			 	 	" 			   where ei.apply_no = eo.apply_no  "+
	                "                 and ei.affirm_level < eo.affirm_level   "+
					"			   and ei.affirm_flag in (0,2)   "+
			        "  )   "+
                    "  )   ";
					
	   if((!selectCondition.equals("")&&!selectContent.equals(""))&&(!OTdate.equals("")||OTdate!=null)){
	       sql =sql + selectCondition+" '%"+selectContent+"%' "+"and to_char(APPLY_TIME,'YYYYMM') LIKE "+" '%"+OTdate+"%' ";
	   }else if(!OTdate.equals("")){
	       sql =sql + " AND TO_CHAR(APPLY_TIME,'YYYYMM') LIKE "+" '%"+OTdate+"%' ";
	   }
	String row_sql = " ESS_MATCH_APPLY_V "+sql;
    Func func = new Func();
	String search = request.getParameter("search");
	if (search == null) {
	    search = "";
	}
	String x = request.getParameter("strPage");
	String y = request.getParameter("bigpage");
	PageControl pc=new PageControl(10,10);
	int bigpage=pc.getTmpBig(y);
	//int strPage=pc.getTmpSmall(x,bigpage);
	
	int strPage=0;
	if(request.getParameter("strPage")!=null && request.getParameter("strPage").length()>0){
	    strPage=Integer.parseInt(request.getParameter("strPage"));
	}else{
	    strPage=pc.getTmpSmall(x,bigpage);
	}
	
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
<span class="title1">决裁/值班信息决裁</span></div>
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
    <td  height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td align="center" bgcolor="#F5F5F5">工号</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">值班人</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">值班类型</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <!-- td  height="30"  align="center" bgcolor="#F5F5F5">休假长度</td-->
    <td  height="30"  align="center" bgcolor="#F5F5F5">开始日期</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">结束日期</td>
    <!-- td  height="30"  align="center" bgcolor="#F5F5F5">工作内容</td -->
    <td  align="center" bgcolor="#F5F5F5">决裁情况</td>
    </tr>
  <% pc.setRowCount(row_sql);
	   matchapply_list = (ArrayList)affirmdao.getMatchApplyAffirmList(pc,sql);
    if(matchapply_list!=null){
    for(int i=0;i<matchapply_list.size();i++){
        matchapply = (MatchApply)matchapply_list.get(i);
  %>
  <tr align="center">
    <td  height="30"  align="center"><%=i+1%></td>
    <td align="center"><%=matchapply.getMatchPerson()%>&nbsp;</td>
    <td  height="30"  align="center"><%=matchapply.getMatchPersonName()%>&nbsp;</td>
    
    <td  height="30"  align="center"><%=matchapply.getMatchTypeCodeName()%>&nbsp;</td>
    <td  height="30"  align="center"><%=matchapply.getMatchApplyDate()%>&nbsp;</td>
    <!-- td  height="30"  align="center">< %=matchapply.getLeaveDays()%>&nbsp;</td-->
    <td  height="30"  align="center"><%=matchapply.getMatchApplyStartDate()%>&nbsp;</td>
    <td  height="30"  align="center"><%=matchapply.getMatchApplyEndDate()%>&nbsp;</td>
    <!--  td  height="30"  align="center">< %=matchapply.getMatchContent()%>&nbsp;</td-->
	<%
		List affirmStatusList = new ArrayList();
		affirmStatusList = affirmdao.getAffirmStatusList(applyType,matchapply.getMatchApplyNo());
	%>
	        <td  align="center">			
				<%
					for(int k=0;k<affirmStatusList.size();k++){
						EssAffirm essAffirm =(EssAffirm) affirmStatusList.get(k);
						String affirmFlag = String.valueOf(essAffirm.getAffirmFlag());
						out.println("<font color=\""+colorMap.get(affirmFlag)+"\">"+essAffirm.getAffirmorName()+" " +statusMap.get(affirmFlag)+"</font><br>" );
					}
				%>
				<% if (!affirmed(admin.getAdminID(),affirmStatusList)){ %>
			<a href="/ess/ess0803_t.jsp?matchApplyNo=<%=matchapply.getMatchApplyNo()%>&applyType=<%=applyType%>&adminid=<%=admin.getAdminID()%>&flag=1&menu_code=<%=menu_code%>&strPage=<%=strPage %>"><font color="#FF0000">通过</font></a><font color="#00CC00">/</font><a href="/ess/ess0803_t.jsp?matchApplyNo=<%=matchapply.getMatchApplyNo()%>&applyType=<%=applyType%>&adminid=<%=admin.getAdminID()%>&flag=2&menu_code=<%=menu_code%>&strPage=<%=strPage %>"><font color="#FF0000">不通过</font></a>
							<%}%>
			</td>
	</tr>
  <%}}else{%>
  <tr align="center" height="30"><td height="30" colspan="10"><font color="#CC0000">暂时没有申请信息!</font></td></tr>
  <%}%>
  <tr align="center">
    <td height="50" colspan="10"  align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center"><%@ include file="../inc/page.jsp"%></td>
        </tr>
    </table></td>
  </tr>
 </table>

</body>
</html>
