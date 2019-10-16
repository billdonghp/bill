<%@ page contentType="text/html; charset=UTF-8" language="java" 
		import="com.ait.ess.entity.*,com.ait.util.*,
				com.ait.ess.dao.*,java.util.*,
				com.ait.hr.entity.*,java.net.URLDecoder" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/> 
<jsp:useBean id="otapply" class="com.ait.ess.entity.OTApply" scope="request"/> 
<jsp:useBean id="otapply_list" class="java.util.ArrayList" scope="request"/> 
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
function seeApply(empid,name, otdate){
  var url = "/ess/ess0801_v.jsp?empid=" + empid + "&name=" + name + "&otdate=" + otdate;
  window.open(url,'加班总数查看',
              'width=620, height=400, top=200, left=200, scrollbars=yes,resizable=yes');
}	
 -->
</script>

<body>
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
	   String applyType ="OtApply";
	   //
	   String Year=request.getParameter("Year")!=null?request.getParameter("Year"):"";
	   String Month=request.getParameter("Month")!=null?request.getParameter("Month"):"";
	   String OTdate=Year+Month;
	   
	   //1级决裁者没有决裁的情况下，或者已经否决的情况下，2级决裁者就看不到此条请假记录，依此类推。
	   String sql ="where APPLY_OT_NO in (select apply_no from ess_affirm eo  "+
	               "where AFFIRMOR_ID = '"+admin.getAdminID()+"' and   "+
				   "APPLY_TYPE = '"+applyType+"' and not "+
				   "exists(select * from ess_affirm ei   "+
			 	 	" 			   where ei.apply_no = eo.apply_no  "+
	                "                 and ei.affirm_level < eo.affirm_level   "+
					"			   and ei.affirm_flag in (0,2)   "+
			        "  )   "+
                    "  )   ";
	   

	   if(!selectCondition.equals("")&&!selectContent.equals("")){
	   		//selectCondition = java.net.URLDecoder.decode(selectCondition);
	       sql = sql+selectCondition+" '%"+selectContent+"%' ";
	   }
	   String row_sql = " ESS_APPLY_OT_V "+sql;
    //Func func = new Func();
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
<div align="left">
<span class="title1">决裁/加班决裁</span></div>
<form name="familyForm">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
	<input type="hidden" name="search" value=""/>
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

<form name="OTVerdictForm" method="post">
<input type="button" id="BatchPassSubmit" value="批量通过" onclick="BatchVerdictClick(1)" >
<input type="button" id="BatchUnpassSubmit" value="批量不通过" onclick="BatchVerdictClick(2)" >
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td  height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td  align="center" bgcolor="#F5F5F5">工号</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">加班人</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">加班日期</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">加班时间</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">加班类型</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">本月累计</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">隔天</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">扣除时间</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">工作内容</td>
    <td  align="center" bgcolor="#F5F5F5">决裁情况&nbsp;</td>
    <td  width="0" ></td> <!-- 申请号 -->
    </tr>
  <% pc.setRowCount(row_sql);
	   otapply_list = (ArrayList)affirmdao.getApplyOTAffirmList(pc,sql);
    if(otapply_list!=null){
    for(int i=0;i<otapply_list.size();i++){
        otapply = (OTApply)otapply_list.get(i);
  %>
  <tr align="center">
    <td  height="30"  align="center"><%=i+1%></td>
    <td  align="center"><%=otapply.getEmpID()%>&nbsp;</td>
    <td  height="30"  align="center"><%=otapply.getChineseName()%>&nbsp;</td>
    <td  height="30"  align="center"><%=otapply.getOtApplyDate()%>&nbsp;</td>
    <td  height="30"  align="center"><%=otapply.getOtDate()%>&nbsp;</td>
    <td  height="30"  align="center"><%=otapply.getOtStartTime()%>~<%=otapply.getOtEndTime()%>&nbsp;</td>
    <td  height="30"  align="center"><%=otapply.getOtType()%>&nbsp;</td>
    <td  height="30"  align="center"><a href="#" onclick="seeApply('<%=otapply.getEmpID()%>','<%=otapply.getChineseName()%>','<%=otapply.getOtDate()%>')">查看</a>&nbsp;</td>
    <td  height="30"  align="center"><%=otapply.getOtNextDays()%>&nbsp;</td>
    <td  height="30"  align="center"><%=otapply.getOtDeductTime()%>&nbsp;</td>
    <td  height="30"  align="center"><%=otapply.getOtWorkContent()%>&nbsp;</td>
	<%
		List affirmStatusList = new ArrayList();
		affirmStatusList = affirmdao.getAffirmStatusList(applyType,otapply.getOtApplySEQ());
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
				<a href = "/ess/ess0801_t.jsp?otapplyNo=<%=otapply.getOtApplySEQ()%>&applyType=<%=applyType%>&adminid=<%=admin.getAdminID()%>&flag=1&menu_code=<%=menu_code%>&strPage=<%=strPage %>"><font color="#FF0000">通过</font></a><font color="#00CC00"> / </font><a href="/ess/ess0801_t.jsp?otapplyNo=<%=otapply.getOtApplySEQ()%>&applyType=<%=applyType%>&adminid=<%=admin.getAdminID()%>&flag=2&menu_code=<%=menu_code%>&strPage=<%=strPage %>"><font color="#FF0000">不通过</font></a>
				<%}%>
			</td>
	<td  width="0"><input type="hidden" id="otapplyNos" name="otapplyNos" value="<%=otapply.getOtApplySEQ()%>" ></td>	
	</tr>
  <%}}else{%>
  <tr align="center" height="30"><td height="30" colspan="11"><font color="#CC0000">暂时没有申请信息!</font></td></tr>
  <%}%>
  <tr align="center">
    <td height="50" colspan="12"  align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center"><%@ include file="../../inc/page_ess.jsp"%></td>
        </tr>
    </table></td>
  </tr>
 </table>
</form>

</body>

<script type="text/javascript">

function BatchVerdictClick(flag){
  document.OTVerdictForm.action="/ess/ess0801_t.jsp?applyType=<%=applyType%>&adminid=<%=admin.getAdminID()%>&flag="+flag+"&menu_code=<%=menu_code%>&strPage=<%=strPage %>";
  document.OTVerdictForm.submit();
}

</script>


</html>
