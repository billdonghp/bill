<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.util.*,com.ait.hr.entity.*,com.ait.ess.dao.*,java.sql.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="vect" class="java.util.Vector" scope="request"></jsp:useBean>
<jsp:useBean id="ht" class="java.util.Hashtable"></jsp:useBean>
<jsp:useBean id="p" class="com.ait.ess.base.PaginationSupport" scope="request"></jsp:useBean>
<jsp:useBean id="map" class="java.util.HashMap" scope="request"></jsp:useBean>
<%@ page import = "java.util.Map"%>
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
<span class="title1">决裁信息查看>健康信息决裁情况</span></div>
<!--form name="familyForm" action="/ess/ess0606.jsp">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
<tr>
<td align="right" width="100%">
<input type="text" name="selectContent" style="width:90px ">&nbsp;
<select name="selectCondition">
<option value="">全部</option>
<option value=" and deptname like ">部门</option>
<option value=" and post_code_name like ">职位</option>
<option value=" and empid like ">工号</option>
<option value=" and chinesename like ">中文姓名</option>
                                                                                        <option value=" and chinese_pinyin like ">拼音姓名</option>
<option value="  ">当前状态</option></select>&nbsp;&nbsp; <img align="absmiddle" src="../images/btn/search.gif" onClick="javascript:clickaction();"></td></tr>
</form-->
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">申请人</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">体检日期</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">身高</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">体重</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">左眼视力</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">右眼视力</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">辨色力</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">血型</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">备注</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">决裁</td>
    <!-- <td height="30"  align="center" bgcolor="#F5F5F5">删除</td> -->
  </tr>
  <%
    if(vect!=null){
    for(int i=0;i<vect.size();i++){
			Map record = (Map)vect.get(i);
  %>
  <tr align="center">
    <td height="30"  align="center"><%=i+1%>&nbsp;</td>

		<td  height="30"  align="center"><%=(String)record.get("CHINESENAME")%>&nbsp;</td>
		<td  height="30"  align="center"><%=record.get("PHYSICAL_TEST_DATE").toString()%>&nbsp;</td>
		<td  height="30"  align="center"><%=(String)record.get("HEIGHT")%>&nbsp;</td>
		<td  height="30"  align="center"><%=(String)record.get("WEIGHT")%>&nbsp;</td>
		<td  height="30"  align="center"><%=(String)record.get("L_EYE_SIGHT")%>&nbsp;</td>
		<td  height="30"  align="center"><%=(String)record.get("R_EYE_SIGHT")%>&nbsp;</td>
		<td  height="30"  align="center"><%=(String)record.get("COLOR_BLIND")%>&nbsp;</td>
		<td  height="30"  align="center"><%=(String)record.get("BLOOD_TYPE")%>&nbsp;</td>
		<td  height="30"  align="center"><%=(String)record.get("CONTENT")%>&nbsp;</td>
	        <%
	        int act = Integer.parseInt((String)record.get("ACTIVITY"));
	        if(act==0){%>
    <td  height="30"  align="center"><font color="#00CC00">未决裁</font></td>
	        <%}else if(act==1){%>
    <td  height="30"  align="center"><font color="#CC0000"><%=record.get("UPDATED_BY")%>&nbsp;通过</font></td>
	        <%}else if(act==2){%>
    <td  height="30"  align="center"><font color="#CC0000"><%=record.get("UPDATED_BY")%>&nbsp;未通过</font></td>
	        <%}%>
	<!-- <td  height="30"  align="center">
	<%if(act==0){%>
	<a href="/ess/ess0600_t.jsp?menu_code=<%=menu_code%>&NO=<%=(String)record.get("HEALTH_NO")%>&operate=health">
	删除
	</a><%}%>&nbsp;
	</td> -->
 
  </tr>
  <%}}%>
  </table>
	
	<!-- 
	<table border="0" align="center" cellpadding="0" cellspacing="0">
  <%if(p.getPagenum()/10!=0){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsHealthContentImp&menu_code=ess0606&pageno=<%=(p.getPagenum()/10)-10+1%>">
  <img src="/images/btn/p_last101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  <tr>
  <%for(int i=1;i<=p.getPagenum();i++){%>
	<td>
	<a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsHealthContentImp&menu_code=ess0606&pageno=<%=i%>">
	<font color="#666666" ><b><%=i%></b></font></a>&nbsp;</td>
  <%}%>
  <%if(p.getPagenum()/10!=0){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsHealthContentImp&menu_code=ess0606&pageno=<%=(p.getPagenum()/10)*10+1%>">
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
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsHealthContentImp&menu_code=ess0606&pageno=<%=(p.getPagenum()/10)*10-10+1%>"><img src="/images/btn/p_last101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  <td>
 <%for(int i=(((p.getCurrent()-1)/10*10)+1);i<=(((p.getCurrent()-1)/10)+1)*10 && i<=p.getPagenum();i++){%>
	<a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsHealthContentImp&menu_code=ess0606&pageno=<%=i%>"><font color="#666666" ><b><%=i%></b></font></a>&nbsp;
 <%}%>
 </td>
  <%if(p.getPagenum()/10!=0 && (((p.getCurrent()-1)/10*10)+1)+10<p.getPagenum()){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsHealthContentImp&menu_code=ess0606&pageno=<%=(p.getPagenum()/10)*10+1%>"><img src="/images/btn/p_next101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  </tr>
</table>

</body>
</html>
