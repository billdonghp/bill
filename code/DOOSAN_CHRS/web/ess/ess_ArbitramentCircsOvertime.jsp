<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ait.ess.entity.EssAffirm" %>
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
<div align="left">
<span class="title1">决裁信息查看>加班申请决裁情况</span></div>
<form name="familyForm" action="/ess/ess0607.jsp">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
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
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">申请人</td>
    <td  align="center" bgcolor="#F5F5F5">工号</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">加班日期</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">加班时间</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">加班类型</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">隔天?</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">扣除时间</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">工作内容</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">决裁情况</td>
    <td  align="center" bgcolor="#F5F5F5">人事确认</td>
    <!-- <td height="30"  align="center" bgcolor="#F5F5F5">删除</td> -->
  </tr>
  <% 
     if(vect.size()>0){
	    for (int i=0;i<vect.size();i++){
		   Map record = (Map)vect.get(i);
  %>
  <tr align="center">
    <td height="30"  align="center"><%=i+1%>&nbsp;</td>
    <td height="30"  align="center"><%=(String)record.get("CHINESENAME") %>&nbsp;</td>
    <td  align="center"><%=(String)record.get("EMPID") %>&nbsp;</td>
    <td height="30"  align="center"><%=record.get("APPLY_OT_DATE").toString() %>&nbsp;</td>
    <td height="30"  align="center"><%=record.get("CREATE_DATE").toString() %>&nbsp;</td>
    <td height="30"  align="center"><%=(String)record.get("OT_FROM_TIME")%>~<%=(String)record.get("OT_TO_TIME")%></td>
    <td height="30"  align="center"><%=(String)record.get("APPLY_OT_TYPE")%>&nbsp;</td>
    <td height="30"  align="center"><%=(String)record.get("APPLY_OT_FLAG")%></td>
    <td height="30"  align="center"><%=(String)record.get("OT_DEDUCT_TIME")%></td>
    <td height="30"  align="center"><%=(String)record.get("APPLY_OT_REMARK")%>&nbsp;</td>	
<%
	String otApplyNo = (String)record.get("APPLY_OT_NO");
	String updatedBy = (String)record.get("UPDATED_BY");
	int act = Integer.parseInt((String)record.get("ACTIVITY"));
	List affirmStatusList = (List)record.get("AffirmStatusList");
%>
				 <td  align="center"><%
					for(int k=0;k<affirmStatusList.size();k++){
						EssAffirm essAffirm =(EssAffirm) affirmStatusList.get(k);
						String affirmFlag = String.valueOf(essAffirm.getAffirmFlag());
						out.println("<font color=\""+map.get(affirmFlag)+"\">"+essAffirm.getAffirmorName()+" " +map.get(affirmFlag)+"</font><br>" );
					}
				%><%if(affirmStatusList.size()<=0){out.println("&nbsp;");}%></td>
	             <td  align="center">
				<%
					if(act==0){
						out.print("<font color=\"#FF0000\">人事未确认</font>");
					}
					
					if(act==1||act==2){
						out.print("<font color="+map.get(String.valueOf(act))+">"+updatedBy+ "<br>"+map.get(String.valueOf(act))+"</font>");
					}
				%></td>
    <!-- <td  height="30"  align="center"><a href="/ess/ess0600_t.jsp?menu_code=<%=menu_code%>&NO=<%=otApplyNo%>&operate=OTApply">删除</a></td> -->
 
  </tr>
  <%}}%>
  <tr align="center">
	
	<!--
	<table border="0" align="center" cellpadding="0" cellspacing="0">
  <%if(p.getPagenum()/10!=0){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsLeaveContentImp&menu_code=ess0608&pageno=<%=(p.getPagenum()/10)-10+1%>">
  <img src="/images/btn/p_last101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  <tr>
  <%for(int i=1;i<=p.getPagenum();i++){%>
	<td>
	<a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsLeaveContentImp&menu_code=ess0608&pageno=<%=i%>">
	<font color="#666666" ><b><%=i%></b></font></a>&nbsp;</td>
  <%}%>
  <%if(p.getPagenum()/10!=0){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsLeaveContentImp&menu_code=ess0608&pageno=<%=(p.getPagenum()/10)*10+1%>">
  <img src="/images/btn/p_next101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
	</tr>
</table>
 -->
 
<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <%if(p.getPagenum()/10!=0 && p.getCurrent()>10){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsLeaveContentImp&menu_code=ess0608&pageno=<%=(p.getPagenum()/10)*10-10+1%>"><img src="/images/btn/p_last101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  <td>
 <%for(int i=(((p.getCurrent()-1)/10*10)+1);i<=(((p.getCurrent()-1)/10)+1)*10 && i<=p.getPagenum();i++){%>
	<a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsLeaveContentImp&menu_code=ess0608&pageno=<%=i%>"><font color="#666666" ><b><%=i%></b></font></a>&nbsp;
 <%}%>
 </td>
  <%if(p.getPagenum()/10!=0 && (((p.getCurrent()-1)/10*10)+1)+10<p.getPagenum()){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsLeaveContentImp&menu_code=ess0608&pageno=<%=(p.getPagenum()/10)*10+1%>"><img src="/images/btn/p_next101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  </tr>
</table>

  </tr>
  </table>
  <br>
</body>
</html>
