<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.*" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/default.css">
</head>
<body>
<%@ page import="com.ait.sy.sy0103.bean.*"%>
<%@ page import="com.ait.evs.evs0126.bean.*"%>                  
<jsp:include page="../inc/hrnavevs.jsp"/>
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.select_edit_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.select_delete_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.associated_deleting" module="sys"></ait:message>');
</script>
		<%@ include file="../inc/importevs.jsp"%>
		<%
		EntEvsCode Ent = new EntEvsCode();
		vlist = Biz.getLevelFirst();
	%>
<script language="JavaScript1.2" type="text/JavaScript1.2">
MENU_CODE='<%=menu_code%>';
function getDetailCodeList(para,depth){
	param_date.location.href = "evs_code_detail.jsp?code_id="+para + "&menu_code=<%=request.getParameter("menu_code")%>+&depth="+depth;
	ID = para;
}
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../evs/inc/evs_toolbar_all_001.jsp" %>
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

		<!-- display 3 level menu -->
		<%@ include file="../evs/inc/evs_toolbar.jsp" %>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 系统代码 -->
				
				</td>
			</tr>
		</table>
		<table width="100%"  border="0" cellpadding="0" cellspacing="0">
		
		  <tr>
		    <td class="line">
		    <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		        <tr>
		          <td width="200" height="30"  class="info_title_01"><!--基本代码  -->
		              <ait:message messageID="display.sys.basic_maintenance.code_management.basic_code" module="sys"></ait:message>
		          </td>
		          <td height="30"  class="info_title_01"><!-- 明晰代码 -->
		           <ait:message messageID="display.sys.basic_maintenance.code_management.code_description" module="sys"></ait:message>   
		          </td>
		        </tr>    
		        <tr bgcolor="#FFFFFF">
		          <td width="200">
		          <select name="dynamicGroup" style="width: 220px" size="25" id="dynamicGroup" onChange="getDetailCodeList(this.options[this.selectedIndex].value,this.options[this.selectedIndex].depth)" class="check">
				  <%
				   admin = (AdminBean)session.getAttribute("admin");
				   String language=admin.getLanguagePreference();  
				   if(language.equals("zh"))    
			   {
			   %>
			  <%for ( int i = 0 ; i < vlist.size() ; i++ )                 
			  {  
				Ent=(EntEvsCode)vlist.elementAt(i) ;
				String basicName = Ent.getBasicName();
				int depth = Ent.getDepth();            
				for(int j=0;j<=depth;j++){
					basicName = "&nbsp;&nbsp;&nbsp;"+basicName;
				}
			%>
			<option value="<%=Ent.getBasicCode() %>" depth="<%=depth %>"><%if(depth==0){out.println("<font color='red'>");}%><%=basicName%></font></option>
			<%}}else{%>
			  <% for ( int j = 0 ; j < vlist.size() ; j++ )
			  {
				Ent=(EntEvsCode)vlist.elementAt(j) ;
				String basicEnName = Ent.getCodeEnName();
				int depth = Ent.getDepth();
				for(int k=0;k<=depth;k++){  
					basicEnName = "&nbsp;&nbsp;&nbsp;"+basicEnName;
				}
			%>
			<option value="<%=Ent.getBasicCode() %>"  depth="<%=depth %>"><%if(depth==0){out.println("<font color='red'>");}%><%=basicEnName%></font></option>
			<%
			} }%>
	          </select></td>
	          <td ><iframe width="100%" height="350px" marginwidth="0" marginheight="0" frameborder="0" name="param_date"></iframe></td>
	        </tr>
	
	    </table></td>
	  </tr>
	  <tr>  
	    <td bgcolor="#FFFFFF"></td>
	  </tr>
	  
	</table>

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

