<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="java.util.Hashtable"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="vect" class="java.util.Vector" scope="request"></jsp:useBean>
<jsp:useBean id="ht" class="java.util.Hashtable"></jsp:useBean>
<jsp:useBean id="p" class="com.ait.ess.base.PaginationSupport" scope="request"></jsp:useBean>
<jsp:useBean id="map" class="java.util.HashMap" scope="request"></jsp:useBean>


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


<div align="left">
<span class="title1">人事确认/家庭情况确认</span></div>
<form name="familyForm" action="/EssContorlServlet">
	<input type="hidden" name="command" value="InfoAffirmCommandImp"/>
	<input type="hidden" name="content" value="InfoAffirmFamilyContentImp"/>
	<table>
		<tr>
			<td align="right" width="100%"><input type="text" name="selectContent" style="width:90px ">&nbsp;
				<select name="selectCondition">
					<option value="">全部</option>
					<option value=" DEPTNAME LIKE ">部门</option>
					<option value=" EMPID LIKE ">工号</option>
					<option value=" CHINESENAME LIKE ">中文姓名</option>
					<option value=" CHINESE_PINYIN LIKE ">拼音姓名</option>
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
      <td  height="30"  align="center" bgcolor="#F5F5F5">学历</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">工作单位</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">家庭地址</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">联系电话</td>
      <td  height="30"  align="center" bgcolor="#F5F5F5">决裁</td>
    </tr>
<%
   for(int i=0;i<vect.size();i++){
	   
	   ht=(Hashtable)vect.get(i);
 
%>
    <tr align="center">
      <td  height="30"  align="center"><%=i+1%></td>
      <td  height="30"  align="center"><%=(String)ht.get("CHINESENAME")%></td>
      <td  align="center"><%=(String)ht.get("EMPID")%></td>
      <td  height="30"  align="center"><%=(String)ht.get("FAM_TYPE_CODE_NAME")%></td>
      <td  height="30"  align="center"><%=(String)ht.get("FAM_NAME")%></td>
      <td  height="30"  align="center"><%=(String)ht.get("FAM_BORNDATE")!=null && (String)ht.get("FAM_BORNDATE")!=""?((String)ht.get("FAM_BORNDATE")).substring(0,((String)ht.get("FAM_BORNDATE")).indexOf(" ")):""%></td>
      <td  height="30"  align="center"><%=(String)ht.get("FAM_NATIONALITY_CODE_NAME")%></td>
      <td  height="30"  align="center"><%=(String)ht.get("FAM_EDUC_CODE_NAME")%></td>
      <td  height="30"  align="center"><%=(String)ht.get("FAM_COMPANY_NAME")%></td>
      <td  height="30"  align="center"><%=(String)ht.get("FAM_HOME_ADDR")%></td>
      <td  height="30"  align="center"><%=(String)ht.get("FAM_PHONE")%></td>
      <%
      int activity=Integer.parseInt((String)ht.get("ACTIVITY")); 
 %>
      <%if(activity==0){%>
      <td  height="30"  align="center"><a href="/EssContorlServlet?command=InfoAffirmCommandImp&content=InfoAffirmFamilyViewContentImp&familyNO=<%=(String)ht.get("FAMILY_NO") %>&flag=1&menu_code=ess0501"><font color="#00CC00">通过</font></a><font color="#00CC00">&nbsp;/&nbsp;</font><a href="/EssContorlServlet?command=InfoAffirmCommandImp&content=InfoAffirmFamilyViewContentImp&familyNO=<%=(String)ht.get("FAMILY_NO") %>&flag=2&menu_code=ess0501"><font color="#00CC00">不通过</font></a></td>
      <%}else if(activity==1){%>
      <td  height="30"  align="center"><font color="#990099">&nbsp;已通过</font></td>
      <%}else if(activity==2){%>
      <td  height="30"  align="center"><font color="#CC0000">&nbsp;未通过</font></td>
      <%}%>
    </tr>
<%
   }
%>  

  </table>
 <table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <%if(p.getPagenum()/10!=0 && p.getCurrent()>10){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=InfoAffirmCommandImp&content=InfoAffirmFamilyContentImp&menu_code=ess0501&pageno=<%=(p.getPagenum()/10)*10-10+1%>"><img src="/images/btn/p_last101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  <td>
 <%for(int i=(((p.getCurrent()-1)/10*10)+1);i<=(((p.getCurrent()-1)/10)+1)*10 && i<=p.getPagenum();i++){%>
	<a href="/EssContorlServlet?command=InfoAffirmCommandImp&content=InfoAffirmFamilyContentImp&menu_code=ess0501&pageno=<%=i%>"><font color="#666666" ><b><%=i%></b></font></a>&nbsp;
 <%}%>
 </td>
  <%if(p.getPagenum()/10!=0 && (((p.getCurrent()-1)/10*10)+1)+10<p.getPagenum()){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=InfoAffirmCommandImp&content=InfoAffirmFamilyContentImp&menu_code=ess0501&pageno=<%=(p.getPagenum()/10)*10+1%>"><img src="/images/btn/p_next101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  </tr>
</table>

</body>
</html>
