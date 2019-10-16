<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.entity.*,com.ait.util.*,com.ait.ess.dao.*,java.util.*,com.ait.hr.entity.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/> 
<jsp:useBean id="tolerence" class="com.ait.ess.entity.Tolerence" scope="request"/> 
<jsp:useBean id="tolerence_list" class="java.util.ArrayList" scope="request"/> 
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

<body>
<%@ include file="/inc/hrtoolbar_null.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<%	
	   String selectContent = request.getParameter("selectContent")!=null?StringUtil.toCN(request.getParameter("selectContent")):"";
	   String selectCondition = request.getParameter("selectCondition")!=null?request.getParameter("selectCondition"):"";
	   String sql ="";
	   if(!selectCondition.equals("")&&!selectContent.equals("")){
	       sql = selectCondition+" '%"+selectContent+"%' ";
	   }
	   String row_sql = " ESS_TOLERENCE_APPLY "+sql;
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
<span class="title1">决裁/出差信息决裁</span></div>
<form name="familyForm">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
<tr><td align="right" width="100%"><input type="text" name="selectContent" style="width:90px ">&nbsp;<select name="selectCondition">
                                                                                        <option value="">全部</option>
                                                                                        <option value=" where deptname like ">部门</option>
                                                                                        <option value=" where post_code_name like ">职位</option>
                                                                                        <option value=" where empid like ">工号</option>
                                                                                        <option value=" where chinesename like ">中文姓名</option>
                                                                                        <option value=" where chinese_pinyin like ">拼音姓名</option>
                                                                                        <option value="  ">当前状态</option></select>&nbsp;&nbsp; <img src="../images/btn/search.gif" onClick="javascript:clickaction();"</td></tr>
</form>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td  height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">申请人</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">出差开始日期</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">出差结束日期</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">出差目的</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">决裁</td>
  </tr>
  <% pc.setRowCount(row_sql);
	   tolerence_list = (ArrayList)affirmdao.getTolerenceApplyAffirmList(pc,sql);
    if(tolerence_list!=null){
    for(int i=0;i<tolerence_list.size();i++){
        tolerence = (Tolerence)tolerence_list.get(i);
  %>
  <tr align="center">
    <td  height="30"  align="center"><%=i+1%></td>
    <td  height="30"  align="center"><a href="/ess/ess0508_v.jsp?menu_code=<%=menu_code%>&tolerenceNo=<%=tolerence.getTolerenceNo()%>"><%=tolerence.getChineseName()%>&nbsp;</a></td>
    <td  height="30"  align="center"><%=tolerence.getCreateDate()%>&nbsp;</td>
    <td  height="30"  align="center"><%=tolerence.getTolerenceFromTime()%>&nbsp;</td>
    <td  height="30"  align="center"><%=tolerence.getTolerenceToTime()%>&nbsp;</td>
    <td  height="30"  align="center"><%=tolerence.getTolerenceTarget()%>&nbsp;</td>
	        <%if(tolerence.getActivity()==0){%>
    <td  height="30"  align="center"><a href="/ess/ess0508_t.jsp?TolerenceNo=<%=tolerence.getTolerenceNo()%>&applyType=TolerenceApply&adminid=<%=admin.getAdminID()%>&flag=1&menu_code=<%=menu_code%>"><font color="#00CC00">通过</font></a><font color="#00CC00">/</font><a href="/ess/ess0508_t.jsp?TolerenceNo=<%=tolerence.getTolerenceNo()%>&applyType=TolerenceApply&adminid=<%=admin.getAdminID()%>&flag=2&menu_code=<%=menu_code%>"><font color="#00CC00">不通过</font></a></td>
	        <%}else if(tolerence.getActivity()==1){%>
    <td  height="30"  align="center"><font color="#CC0000">通过</font></td>
	        <%}else if(tolerence.getActivity()==2){%>
    <td  height="30"  align="center"><font color="#CC0000">未通过</font></td>
	        <%}%>
  </tr>
  <%}}else{%>
  <tr align="center" height="30"><td height="30" colspan="10"><font color="#CC0000">暂时没有申请信息!</font></td></tr>
  <%}%>
  <tr align="center">
    <td height="50" colspan="10"  align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center"><%@ include file="/inc/page.jsp"%></td>
        </tr>
    </table></td>
  </tr>
 </table>

</body>
</html>
