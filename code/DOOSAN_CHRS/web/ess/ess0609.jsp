<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.util.*,com.ait.ess.entity.*,com.ait.ess.dao.*,java.sql.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="affirmdao" class="com.ait.ess.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="tolerenceApply_list" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="tolerenceApply" class="com.ait.ess.entity.Tolerence" scope="request"/>
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
<%@ include file="/inc/hrtoolbar_null.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<body>
<%  
	String selectContent = request.getParameter("selectContent")!=null?StringUtil.toCN(request.getParameter("selectContent")):"";
	String selectCondition = request.getParameter("selectCondition")!=null?request.getParameter("selectCondition"):"";
	String sql ="  where empid='"+admin.getAdminID()+"'";
	   if(!selectCondition.equals("")&&!selectContent.equals("")){
	       sql =sql + selectCondition+" '%"+selectContent+"%' ";
	   }
	   String row_sql = " ESS_TOLERENCE_APPLY_V  "+sql;
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
<span class="title1">决裁信息查看>出差申请决裁情况</span></div>
<form name="familyForm" action="/ess/ess0609.jsp">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
<tr><td align="right" width="100%"><input type="text" name="selectContent" style="width:90px ">&nbsp;<select name="selectCondition">
                                                                                        <option value="">全部</option>
                                                                                        <option value=" and deptname like ">部门</option>
                                                                                        <option value=" and post_code_name like ">职位</option>
                                                                                        <option value=" and empid like ">工号</option>
                                                                                        <option value=" and chinesename like ">中文姓名</option>
                                                                                        <option value=" and chinese_pinyin like ">拼音姓名</option>
                                                                                        <option value="  ">当前状态</option></select>&nbsp;&nbsp; <img src="../images/btn/search.gif" onClick="javascript:clickaction();"</td></tr>
</form>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">申请人</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">开始日期</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">结束日期</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">出差目的</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">决裁情况</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">删除</td>
  </tr>
  <% pc.setRowCount(row_sql);
     tolerenceApply_list =(ArrayList)affirmdao.getTolerenceApplyAffirmList(pc,sql);
     if(tolerenceApply_list.size()>0){
	    for (int i=0;i<tolerenceApply_list.size();i++){
		   tolerenceApply =(Tolerence)tolerenceApply_list.get(i);
  %>
  <tr align="center">
    <td height="30"  align="center"><%=i+1%>&nbsp;</td>
    <td height="30"  align="center"><a href="/ess/ess0508_v.jsp?menu_code=<%=menu_code%>&tolerenceNo=<%=tolerenceApply.getTolerenceNo()%>"><%=tolerenceApply.getChineseName()%>&nbsp;</a></td>
    <td height="30"  align="center"><%=tolerenceApply.getTolerenceFromTime()%>&nbsp;</td>
    <td height="30"  align="center"><%=tolerenceApply.getTolerenceToTime()%>&nbsp;</td>
    <td height="30"  align="center"><%=tolerenceApply.getTolerenceTarget()%>&nbsp;</td>
	        <%if(tolerenceApply.getActivity()==0){%>
    <td  height="30"  align="center"><font color="#00CC00">未决裁</font></td>
	        <%}else if(tolerenceApply.getActivity()==1){%>
    <td  height="30"  align="center"><font color="#CC0000">通过</font></td>
	        <%}else if(tolerenceApply.getActivity()==2){%>
    <td  height="30"  align="center"><font color="#CC0000">未通过</font></td>
	        <%}%>
	<td  height="30"  align="center"><a href="/ess/ess0600_t.jsp?menu_code=<%=menu_code%>&NO=<%=tolerenceApply.getTolerenceNo()%>&operate=tolerence">删除</a></td>
  </tr>
  <%}}%>
  </table>
</body>
</html>
