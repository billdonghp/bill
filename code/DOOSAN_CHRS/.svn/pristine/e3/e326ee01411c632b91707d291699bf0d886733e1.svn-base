<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.dao.*,java.util.*,com.ait.util.*,com.ait.hr.entity.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/> 
<jsp:useBean id="family" class="com.ait.hr.entity.Family" scope="request"/> 
<jsp:useBean id="family_list" class="java.util.ArrayList" scope="request"/> 
<jsp:useBean id="affirmdao" class="com.ait.ess.dao.AffirmDAO" scope="request"/> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>

<body>
<%@ include file="/inc/hrtoolbar_null.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<script language="javascript">
 <!--hidden
 
 function clickaction (){
     document.familyForm.submit();
	 }
 -->
</script>
	<% 
	   String selectContent = request.getParameter("selectContent")!=null?StringUtil.toCN(request.getParameter("selectContent")):"";
	   String selectCondition = request.getParameter("selectCondition")!=null?request.getParameter("selectCondition"):"";
	   String sql ="";
	   if(!selectCondition.equals("")&&!selectContent.equals("")){
	       sql = selectCondition+" '%"+selectContent+"%' ";
	   }
	   String row_sql = " ESS_FAMILY_V "+sql;
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
<span class="title1">人事确认/家庭情况确认</span></div>
<form name="familyForm">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
	<table>
		<tr>
			<td align="right" width="100%"><input type="text" name="selectContent" style="width:90px ">&nbsp;
				<select name="selectCondition">
					<option value="">全部</option>
					<option value=" where deptname like ">部门</option>
					<option value=" where empid like ">工号</option>
					<option value=" where chinesename like ">中文姓名</option>
					<option value=" where chinese_pinyin like ">拼音姓名</option>
				</select>&nbsp;&nbsp; <img align="absmiddle" src="../images/btn/search.gif" onClick="javascript:clickaction();">
			</td>
		</tr>
	</table>
</form>
  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr align="center">
      <td  height="30"  align="center" bgcolor="#F5F5F5">序号</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">申请者</td>
      <td  align="center" bgcolor="#F5F5F5">工号</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">关系</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">姓名</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">出生日期</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">国籍</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">最终学历</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">工作单位</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">家庭地址</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">联系电话</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">决裁</td>
    </tr>
    <%pc.setRowCount(row_sql);
	   family_list = (ArrayList)affirmdao.getFamilyAffirmList(pc,sql);
    if(family_list!=null){
    for(int i=0;i<family_list.size();i++){
        family = (Family) family_list.get(i);
  %>
    <tr align="center">
      <td  height="30"  align="center"><%=i+1%></td>
      <td  height="30"  align="center"><%=family.getChineseName()%>&nbsp;</td>
      <td  align="center"><%=family.getEmpID()%>&nbsp;</td>
      <td  height="30"  align="center"><%=family.getRelationalType()%>&nbsp;</td>
      <td  height="30"  align="center"><%=family.getFMName()%>&nbsp;</td>
      <td  height="30"  align="center"><%=family.getFMBornDate()%>&nbsp;</td>
      <td  height="30"  align="center"><%=family.getFMNationality()%>&nbsp;</td>
      <td  height="30"  align="center"><%=family.getFMEducation()%>&nbsp;</td>
      <td  height="30"  align="center"><%=family.getFMCompanyName()%>&nbsp;</td>
      <td  height="30"  align="center"><%=family.getFMHomeAddress()%>&nbsp;</td>
      <td  height="30"  align="center"><%=family.getFMPhone()%>&nbsp;</td>
      <%if(family.getActivity()==0){%>
      <td  height="30"  align="center"><a href="/ess/ess0501_t.jsp?familyNO=<%=family.getFamilyNo()%>&flag=1&menu_code=<%=menu_code%>"><font color="#00CC00">通过</font></a><font color="#00CC00">&nbsp;/&nbsp;</font><a href="/ess/ess0501_t.jsp?familyNO=<%=family.getFamilyNo()%>&flag=2&menu_code=<%=menu_code%>"><font color="#00CC00">不通过</font></a></td>
      <%}else if(family.getActivity()==1){%>
      <td  height="30"  align="center"><font color="#990099"><%=family.getModifierID()%>&nbsp;已通过</font></td>
      <%}else if(family.getActivity()==2){%>
      <td  height="30"  align="center"><font color="#CC0000"><%=family.getModifierID()%>&nbsp;未通过</font></td>
      <%}%>
    </tr>
    <%}}else{%>
    <tr align="center" height="30">
      <td height="30" colspan="12"><font color="#CC0000">暂时没有申请信息!</font></td>
    </tr>
    <%}%>
    <tr align="center">
      <td height="50" colspan="12"  align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td align="center"><%@ include file="/inc/page_ess.jsp"%></td>
          </tr>
      </table></td>
    </tr>
  </table>
</body>
</html>
