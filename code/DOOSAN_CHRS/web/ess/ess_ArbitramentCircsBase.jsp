<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Hashtable"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="vect" class="java.util.Vector" scope="request"></jsp:useBean>
<jsp:useBean id="ht" class="java.util.Hashtable"></jsp:useBean>
<jsp:useBean id="p" class="com.ait.ess.base.PaginationSupport" scope="request"></jsp:useBean>
<jsp:useBean id="map" class="java.util.HashMap" scope="request"></jsp:useBean>
<html>
<head>

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
<%@ include file="/inc/hrtoolbar_a.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%> 
<div align="left">
<span class="title1">决裁信息查看>基本信息决裁情况</span></div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td height="30"  align="center" bgcolor="#F5F5F5">序号</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">申请者</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5">申请内容</td>
    <td height="30"  align="center" bgcolor="#F5F5F5">决裁情况</td>
  </tr>
  <%for(int i=0;i<vect.size();i++){
  ht=(Hashtable)vect.get(i);
  %>
  <tr>
  <td><%=++i%></td>
  <td><%=(String)ht.get("CHINESENAME")%></td>
  <td><%=((String)ht.get("CREATE_DATE")).substring(0,((String)ht.get("CREATE_DATE")).indexOf(" "))%></td>
  <td>查看内容</td>
  <td><%=(String)map.get((String)ht.get("ACTIVITY"))%></td>
  </tr>
  <%}%>
  </table>
<!--
  <table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <%if(p.getPagenum()/10!=0 && p.getCurrent()>10){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsBaseContentImp&menu_code=ess0601&pageno=<%=(p.getPagenum()/10)*10-10+1%>"><img src="/images/btn/p_last101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  <td>
  <%for(int i=(((p.getCurrent()-1)/10*10)+1);i<=(((p.getCurrent()-1)/10)+1)*10 && i<=p.getPagenum();i++){%>
	<a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsBaseContentImp&menu_code=ess0601&pageno=<%=i%>"><font color="#666666" ><b><%=i%></b></font></a>&nbsp;</td>
  <%}%>
  <%if(p.getPagenum()/10!=0 && (((p.getCurrent()-1)/10*10)+1)+10<p.getPagenum()){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsBaseContentImp&menu_code=ess0601&pageno=<%=(p.getPagenum()/10)*10+1%>"><img src="/images/btn/p_next101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  </tr>
</table>
-->
<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <%if(p.getPagenum()/10!=0 && p.getCurrent()>10){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsBaseContentImp&menu_code=ess0601&pageno=<%=(p.getPagenum()/10)*10-10+1%>"><img src="/images/btn/p_last101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  <td>
 <%for(int i=(((p.getCurrent()-1)/10*10)+1);i<=(((p.getCurrent()-1)/10)+1)*10 && i<=p.getPagenum();i++){%>
	<a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsBaseContentImp&menu_code=ess0601&pageno=<%=i%>"><font color="#666666" ><b><%=i%></b></font></a>&nbsp;
 <%}%>
 </td>
  <%if(p.getPagenum()/10!=0 && (((p.getCurrent()-1)/10*10)+1)+10<p.getPagenum()){%>
  <td width="11%">
  <a href="/EssContorlServlet?command=ArbitramentCircsCommandImp&content=ArbitramentCircsBaseContentImp&menu_code=ess0601&pageno=<%=(p.getPagenum()/10)*10+1%>"><img src="/images/btn/p_next101.gif" width="18" height="12" border="0" align="absmiddle"></a>
  </td>
  <%}%>
  </tr>
</table>

</body>
</html>
