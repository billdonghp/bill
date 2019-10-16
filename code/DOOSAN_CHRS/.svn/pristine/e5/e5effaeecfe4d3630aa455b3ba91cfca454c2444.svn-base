<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.*,com.ait.utils.*" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/default.css">
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.select_edit_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.select_delete_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.associated_deleting" module="sys"></ait:message>');
</script>
</head>
<body>  
<%@ page import="com.ait.sy.sy0105.bean.*"%>
<%@ page import="com.ait.sy.sy0105.bean.Ent"%>
<jsp:include page="/inc/hrnav.jsp"/>       
<%@ include file="../inc/import.jsp"%>
<script language="javascript">
MENU_CODE='<%=menu_code%>';
<% boolean flag = false;%>
function Search() {
//            /sy/sy0110.jsp?menu_code=sy0110
	<% flag=true;%>
	var cpnyId = document.getElementById('cpnyId').value;
	location.href="/sy/sy0110.jsp?menu_code=sy0110&cpnyId="+cpnyId;
}
</script>
<link  rel="stylesheet" type="text/css" href="/css/5.css">
<%
		Ent Ent = new Ent();
		String x = request.getParameter("strPage");
		String y= request.getParameter("bigpage");
		PageControl pc=new PageControl(100,10);
		int bigpage=pc.getTmpBig(y);
		int strPage=pc.getTmpSmall(x,bigpage);
		String  cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
		if(flag){
			pc.setRowCount("SY_LOGIN_SCREEN where SCREEN_GRANT_NO<>'101' and CPNY_ID='"+ cpnyId +"' order by SCREEN_GRANT_NO desc ");
			pc.setintPage(strPage,bigpage);
			request.setAttribute("cpnyId",cpnyId);
			vlist = Biz.List(pc,cpnyId);
		}else{
			pc.setRowCount("SY_LOGIN_SCREEN where SCREEN_GRANT_NO<>'101' order by SCREEN_GRANT_NO desc ");
			pc.setintPage(strPage,bigpage);
			vlist = Biz.List(pc, cpnyId);
		}
		
		
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<input type="hidden" name="cpnyId" value="${cpnyId}">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../sy/inc/sy_toolbar_all.jsp" %>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->
		<br />
		<!-- display 3 level menu -->
		<%@ include file="../sy/inc/sy_toolbar.jsp" %>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 权限组 -->
				<ait:message messageID="display.mutual.authority_group" ></ait:message>
				</td>
			</tr>
		</table>
		<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30" class="line">
		    <table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		        <tr>
		          <td width="10%" height="30"  class="info_title_01"><!--  序号-->
		       	<ait:message messageID="display.mutual.no" ></ait:message>   
		          </td>
		          <td width="35%" height="30"  class="info_title_01"><!--权限组名称序号  -->
		      	<ait:message messageID="display.sys.authority_management.authority_group.authority_group_no" module="sys"></ait:message>    
		          </td>
		          <td width="35%" height="30"  class="info_title_01">法人区分</td>
		          <td width="35%" class="info_title_01"><!--权限组名称  -->
		          	<ait:message messageID="display.sys.authority_management.authority_group.authority_group_name" module="sys"></ait:message>
		          </td><%--
		          <td width="20%" height="30"  class="info_title_01"><!-- 活跃 -->
		         	<ait:message messageID="display.mutual.active" ></ait:message>   
		          </td>
		        --%></tr>
		        <%  
				int listNo = 1 ;             
				for ( int i = 0 ; i < vlist.size() ; i++ )
				  {
					Ent=(Ent)vlist.elementAt(i) ;
				%>
		        <tr bgcolor="#FFFFFF" onClick="HighLightTR('#E7F0EF','black','<%=Ent.getScreenGrantNo()%>','<%=menu_code%>')">
		          <td height="30" align="center" style="color: #666666;"><%=listNo+(strPage-1)*10+(bigpage-1)*100%>
		            <% listNo = listNo + 1 ; %></td>
		          <td align="center" style="color: #666666;"><%=Ent.getScreenGrantNo()%></td>
		          <td align="center" style="color: #666666;"><%=Ent.getCpnyname()!=null?Ent.getCpnyname():""%> &nbsp;</td>
		           <%      
				       admin = (AdminBean)session.getAttribute("admin");
					   String language=admin.getLanguagePreference();
					   if(language.equals("zh"))
					   {              
					  %>     
		          <td align="center" style="color: #666666;"><%=Ent.getScreenGrantName()%></td>
		          <%}else{ %>
		          <td align="center" style="color: #666666;"><%=Ent.getScreenGrantEnName()%></td>
		          <%} %>
		          <%--<td align="center" style="color: #666666;"><div align="center"> <img src="/images/a_<%=Ent.getActivity()%>.gif"></div></td>
		        --%></tr>
		        <%}%>
		      </table></td>
		  </tr>
		</table>
		<%//@ include file = "/inc/pagecontrol.jsp"%>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>

<br>
