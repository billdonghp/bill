<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.util.*,com.ait.hr.entity.*,com.ait.ess.dao.*,java.sql.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="affirmdao" class="com.ait.ess.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="family_list" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="family" class="com.ait.hr.entity.Family" scope="request"/>
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
	 function deleteApply(familyNo,menuCode){
	 	if(confirm("你确定要删除此申请吗？")){
	 		location.href="/ess/ess0600_t.jsp?menu_code="+menuCode+"&NO="+familyNo+"&operate=family"
		}
	 }
 -->
</script>
<%@ include file="/inc/hrtoolbar_null.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<body>
<%  
	String selectContent = request.getParameter("selectContent")!=null?StringUtil.toCN(request.getParameter("selectContent")):"";
	String selectCondition = request.getParameter("selectCondition")!=null?request.getParameter("selectCondition"):"";
	String sql =" where empid='"+admin.getAdminID()+"'";
	   if(!selectCondition.equals("")&&!selectContent.equals("")){
	       sql =sql + selectCondition+" '%"+selectContent+"%' ";
	   }
	   String row_sql = " ESS_FAMILY_V  "+sql;
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
<span class="title1">决裁信息查看>家庭信息决裁情况</span></div>
<!--form name="familyForm" action="/ess/ess0602.jsp">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
<tr><td align="right" width="100%"><input type="text" name="selectContent" style="width:90px ">&nbsp;<select name="selectCondition">
                                                                                        <option value="">全部</option>
                                                                                        <option value=" and deptname like ">部门</option>
                                                                                        <option value=" and post_code_name like ">职位</option>
                                                                                        <option value=" and empid like ">工号</option>
                                                                                        <option value=" and chinesename like ">中文姓名</option>
                                                                                        <option value=" and chinese_pinyin like ">拼音姓名</option>
                                                                                        <option value="  ">当前状态</option></select>&nbsp;&nbsp; <img align="absmiddle" src="../images/btn/search.gif" onClick="javascript:clickaction();"></td>
</tr>
</form-->
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">申请者</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">关系</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">姓名</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">出生日期</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">国籍</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">学历</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">工作单位</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">家庭地址</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">联系电话</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">决裁情况</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">删除</td>
  </tr>
  <% pc.setRowCount(row_sql);
     family_list =(ArrayList)affirmdao.getFamilyAffirmList(pc,sql);
     if(family_list.size()>0){
	    for (int i=0;i<family_list.size();i++){
		   family =(Family)family_list.get(i);
  %>
  <tr align="center">
    <td height="30"  align="center"><%=i+1%>&nbsp;</td>
    <td  height="30"  align="center"><%=StringUtil.toUnicode(StringUtil.toISO(family.getChineseName()),"UTF-8")%>&nbsp;</td>
    <td  height="30"  align="center"><%=family.getRelationalType()%>&nbsp;</td>
    <td  height="30"  align="center"><%=family.getFMName()%>&nbsp;</td>
    <td  height="30"  align="center"><%=family.getFMBornDate()%>&nbsp;</td>
    <td  height="30"  align="center"><%=family.getFMNationality()%>&nbsp;</td>
    <td  height="30"  align="center"><%=family.getFMEducation()%>&nbsp;</td>
    <td  height="30"  align="center"><%=family.getFMCompanyName()%>&nbsp;</td>
    <td  height="30"  align="center"><%=family.getFMHomeAddress()%>&nbsp;</td>
    <td  height="30"  align="center"><%=family.getFMPhone()%>&nbsp;</td>
	        <%if(family.getActivity()==0){%>
    <td  height="30"  align="center"><font color="#00CC00">未决裁</font></td>
	        <%}else if(family.getActivity()==1){%>
    <td  height="30"  align="center"><font color="#CC0000"><%=family.getModifierID()%><br>通过</font></td>
	        <%}else if(family.getActivity()==2){%>
    <td  height="30"  align="center"><font color="#CC0000"><%=family.getModifierID()%><br>未通过</font></td>
	        <%}%>
	<!--  <td height="30"  align="center">dd<%if(family.getActivity()==0){%><a href="javascript:deleteApply(<%=family.getFamilyNo()%>,'<%=menu_code%>');">删除</a><%}%>&nbsp;</td> -->
  	<td  height="30"  align="center"><%if(family.getActivity()==0){%><a href="/ess/ess0600_t.jsp?menu_code=<%=menu_code%>&NO=<%=family.getFamilyNo()%>&operate=family">删除</a><%}%>&nbsp;</td>							
  </tr>
  <%}}%>
  </table>
</body>

</html>
