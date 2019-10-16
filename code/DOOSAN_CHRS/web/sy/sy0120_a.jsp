<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.*" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/default.css">
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.authority.authority.edit.emp_user" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.id_invalid" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.enter_id" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.maximum_length_20" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.user" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.maximum_length_50" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.password" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.maximum_length_30" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.select_group" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.select_level" module="sys"></ait:message>');
</script>
</head>              
<body>
<script language="javascript">
function checksub()
{

var message=new Array('<ait:message messageID="alert.sys.authority.authority.id_empty" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.id_first" module="sys"></ait:message>'
                   );
	if(document.EM2Form.empID.value=="") 
	{
		alert(message[0]);
		document.EM2Form.empID.focus();  
		return false;
	}
	if(document.EM2Form.userID.value=="")
	{
		alert(message[1]);
		document.EM2Form.userID.focus();            
		return false;
	}
	var url="syCheck0104.jsp?flag=add&empID="+document.EM2Form.empID.value+"&userID="+document.EM2Form.userID.value;
	window.open(url,'','width=200,height=50');
	return true;
}           
</script>
<%@ include file="/inc/importa.jsp"%>
<jsp:include page="/inc/hrnav.jsp"/>
<SCRIPT LANGUAGE="JavaScript" src="../js/sy0104.js"></SCRIPT>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"> 
		
			<!-- display toolbar -->
			<%@ include file="../sy/inc/sy_toolbar_a.jsp"%>  
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
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 添加权限 -->
				<ait:message messageID="display.sys.authority_management.authority.modify_authority" module="sys"></ait:message>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td align="center"><TABLE width="100%">
		        <TBODY>
		          <TR>
		            <TD >&nbsp;</TD>
		          </TR>
		        </TBODY>
		      </TABLE></td>
		  </tr>
		  <tr>
		    <td height="30"><TABLE width="100%" border="1" align="center" cellPadding=4 cellSpacing=0 bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" rules=all>
		        <form  name="EM2Form" method="post" action="/E<%=menu_code%>Control?flag=insert&menu_code=<%=menu_code%>">
		          <TBODY>
		            <TR>
		              <TD width="15%" height="30" align=middle class="info_title_01"><!-- 工号 -->
		              <ait:message messageID="display.sys.authority_management.authority.emp_no" module="sys"></ait:message>
		              </TD>
		              <TD width="85%"><INPUT  size=40 name=empID>
		                <input type="button"  value=<ait:message messageID="display.sys.authority_management.authority.emp_no" module="sys"></ait:message>
		                onClick="window.open('/util/SysEmpTree.jsp')"></TD>
		            </TR>
		            
		            <TR>              
		              <TD align=middle  class="info_title_01" nowrap="nowrap"><!-- 权限组 -->
		              <ait:message messageID="display.mutual.authority_group" ></ait:message>
		              </TD>                             
		              <TD><TABLE>    
		                  <TR>
		              <%
		                 ArrayList depts= new ArrayList();
		         		 admin = (AdminBean)session.getAttribute("admin");
		         		 String langu=admin.getLanguagePreference();
		               if(langu.equals("zh"))  
		               {
		                  %>   
		                   <TD><%= func.getGrant_Group() %>  
		                 <%}else{ %>  
		                  <TD><%= func.getGrant_EnGroup() %> 
		                  <%} %>   
		                  </TR>
		                </TABLE></TD>  
		            </TR>
		            <TR>
		              <TD height="774" align=middle vAlign=top class="info_title_01"><!-- 权限级 -->
		              <ait:message messageID="display.sys.authority_management.authority.authority_grade" module="sys"></ait:message>
		              </TD>
		              <TD vAlign=top><%@ include file="../sy/zpss_org.jsp"%></TD>
		            </TR>
		        </form>
		      </TABLE></td>
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

