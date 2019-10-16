<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.entity.*,com.ait.util.*,com.ait.ess.dao.*,java.util.*,com.ait.hr.entity.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/> 
<jsp:useBean id="matchapply" class="com.ait.ess.entity.MatchApply" scope="request"/> 
<jsp:useBean id="matchapply_list" class="java.util.ArrayList" scope="request"/> 
<jsp:useBean id="affirmdao" class="com.ait.ess.dao.AffirmDAO" scope="request"/> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript">
 <!--hidden
 
 function clickaction (){
     document.familyForm.submit();
	 }
 -->
</script><script language="javascript" src="../js/meizzDate.js"></script>
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

		
		Map confirmMap = new HashMap();
		confirmMap.put("1","确认通过");
		confirmMap.put("2","确认不通过");
				
	   String selectContent = request.getParameter("selectContent")!=null?StringUtil.toCN(request.getParameter("selectContent")):"";
	   String selectCondition = request.getParameter("selectCondition")!=null?request.getParameter("selectCondition"):"";
	   String OTdate = request.getParameter("OTdate")!=null?request.getParameter("OTdate"):"";
	   String applyType ="MatchApply";
	   
	   String sql ="where  match_no >=0 ";
	   if((!selectCondition.equals("")&&!selectContent.equals(""))&&(!OTdate.equals("")||OTdate!=null)){
	       sql = sql+selectCondition+" '%"+selectContent+"%' "+"and to_char(apply_time,'YYYY-MM-DD') LIKE"+"'%"+OTdate+"%'";
	   }else if(!OTdate.equals("")||OTdate!=null){
	   		sql = sql+"and to_char(apply_time,'YYYY-MM-DD') LIKE"+"'%"+OTdate+"%'";
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
	int strPage=pc.getTmpSmall(x,bigpage);
	pc.setintPage(strPage,bigpage);
%>
<div align="left">
<span class="title1">人事确认/值班信息确认</span></div>
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
				<option value="">日期：</option>
				<input onClick="setday(this);" type="text" name="OTdate" class="content" style="width:90px ">
			</td>
		</tr>
	</table>
</form>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td  height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">值班人</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">工号</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">值班类型</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <!-- td  height="30"  align="center" bgcolor="#F5F5F5">休假长度</td -->
    <td  height="30"  align="center" bgcolor="#F5F5F5">开始日期</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">结束日期</td>
    <!--  td  height="30"  align="center" bgcolor="#F5F5F5">工作内容</td -->
    <td  align="center" bgcolor="#F5F5F5">决裁情况</td>
    <td  align="center" bgcolor="#F5F5F5">人事确认</td>
  </tr>
  <% pc.setRowCount(row_sql);
	   matchapply_list = (ArrayList)affirmdao.getMatchApplyAffirmList(pc,sql);
    if(matchapply_list!=null){
    for(int i=0;i<matchapply_list.size();i++){
        matchapply = (MatchApply)matchapply_list.get(i);
  %>
  <tr align="center">
    <td  height="30"  align="center"><%=i+1%></td>
    <td  height="30"  align="center"><%=StringUtil.toUnicode(StringUtil.toISO(matchapply.getMatchPersonName()),"UTF-8")%>&nbsp;</td>
    <td  height="30"  align="center"><%=matchapply.getMatchPerson()%>&nbsp;</td>
    <td  height="30"  align="center"><%=matchapply.getMatchTypeCodeName()%>&nbsp;</td>
    <td  height="30"  align="center"><%=matchapply.getMatchApplyDate()%>&nbsp;</td>
    <!-- td  height="30"  align="center">< % =matchapply.getMatchDays()%>&nbsp;</td -->
    <td  height="30"  align="center"><%=matchapply.getMatchApplyStartDate()%>&nbsp;</td>
    <td  height="30"  align="center"><%=matchapply.getMatchApplyEndDate()%>&nbsp;</td>
    <!--  td  height="30"  align="center">< % =matchapply.getLeaveContent()%>&nbsp;</td-->
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
			
			<%if(affirmStatusList.size()<=0){out.println("&nbsp;");}%></td>
	        <td  align="center">
				<%
					if(matchapply.getActivity()==0){
						out.print("<a href=\"/ess/ess0511_t.jsp?matchApplyNo="+matchapply.getMatchApplyNo()+"&applyType="+applyType+"&adminid="+admin.getAdminID()+"&flag=1&menu_code="+menu_code+"\"><font color=\"#FF0000\">确认</font></a><font color=\"#00CC00\">/</font><a href=\"/ess/ess0511_t.jsp?matchApplyNo="+matchapply.getMatchApplyNo()+"&applyType="+applyType+"&adminid="+admin.getAdminID()+"&flag=2&menu_code="+menu_code+"\"><font color=\"#FF0000\">否决</font></a>");
					}
					
					if(matchapply.getActivity()==1||matchapply.getActivity()==2){
						out.print("<font color="+colorMap.get(String.valueOf(matchapply.getActivity()))+">"+"人事"+ "<br>"+confirmMap.get(String.valueOf(matchapply.getActivity()))+"</font>");
					}
				%>			
			</td>
  </tr>
  <%}}else{%>
  <tr align="center" height="30"><td height="30" colspan="11"><font color="#CC0000">暂时没有申请信息!</font></td></tr>
  <%}%>
  <tr align="center">
    <td height="50" colspan="11"  align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center"><%@ include file="/inc/page_ess.jsp"%></td>
        </tr>
    </table></td>
  </tr>
 </table>

</body>
</html>
