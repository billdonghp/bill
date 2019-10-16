<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.*,com.ait.sy.sy0101.bean.*" errorPage="" %>
<jsp:include page="/inc/hrnav.jsp"/>
<%@ include file="/inc/taglibs.jsp"%>
<%@ include file="../inc/import.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/default.css">
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.maintenance.basic.enter_no" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.basic.maximum_length" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.basic.screen_narrative" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.basic.screen_length" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.basic.enter_parent" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.basic.menu_number" module="sys"></ait:message>');
</script> 
</head>
<body>
<%@page import="java.util.*"%>
<%                       
Biz bbiz=new Biz();
boolean flag=true;
if(menu_code=="")
flag=false;
String menu_intro="";
if(request.getParameter("m_code")!=null)
{
	String m_code=request.getParameter("m_code");
	menu_intro=bbiz.getMenu_intro(m_code);

}
ArrayList nv=bbiz.getMenu_parent_code(request);
ArrayList names=(ArrayList)nv.get(0);
ArrayList values=(ArrayList)nv.get(1);
%>
<%@page import="com.ait.sy.sy0101.bean.*"%>
<SCRIPT LANGUAGE="JavaScript" src="../js/sy0101.js"></SCRIPT>
<link href="css/default.css" rel="stylesheet" type="text/css">

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
				<td align="left" class="title1" colspan="10"><!-- 添加屏幕 -->
			<ait:message messageID="display.sys.basic.screen.edit" module="sys"></ait:message> 	
				</td>
			</tr>
		</table>
		<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td class="line"><table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		        <form  name="EM2Form" method="post" action="/E<%=menu_code%>Control?flag=insert&menu_code=<%=menu_code%>" onSubmit="return CheckForm()" >
		          <tr>
		            <td width="15%" height="30" class="info_title_01"><div align="left"><!-- 屏幕编号 -->
		    <ait:message messageID="display.sys.basic_maintenance.screen_management.screen_no" module="sys"></ait:message>         
		            </div></td>
		            <td width="85%" class="tablecontent"><div align="left">
		                <input width="300" name="menuCode" type="text" >
		              </div></td>
		          </tr>
		          <tr>
		            <td height="30" class="info_title_01"><div align="left"><!-- 屏幕解释 -->
		    <ait:message messageID="display.sys.basic_maintenance.screen_management.screen_narrative" module="sys"></ait:message>         
		            </div></td>
		            <td class="tablecontent"><div align="justify">
		                <input width="300" name="menuIntro" type="text" >
		              </div></td>
		          </tr>
		          <tr>
		            <td height="30" class="info_title_01"><div align="left"><!--英文屏幕解释  -->
		     <ait:message messageID="display.sys.basic.screen.english_name" module="sys"></ait:message>        
		            </div></td>
		            <td class="tablecontent"><div align="justify">
		                <input width="300" name="menuEnIntro" type="text" >   
		              </div></td>
		          </tr>
		          <tr>
		            <td height="30" class="info_title_01"><div align="left">URL</div></td>
		            <td class="tablecontent"><div align="justify">
		                <input width="300" name="menu_Url" type="text" >
		              </div></td>
		          </tr>
		          <tr>
		            <td height="30" class="info_title_01"><div align="left"><!-- 父级屏幕号 -->
		       <ait:message messageID="display.sys.basic.screen.superior_screen" module="sys"></ait:message>      
		            </div></td>
		            <td class="tablecontent"><div align="justify">
		                <select name="menuParentCode"  width="200">
		                  <option value="0">select</option>        
		                  <%if(names.size()==values.size()){%>
		                  <%for(int i=0;i<names.size();i++){%>
		                  <%if(menu_intro.equals((String)names.get(i))){%>
		                  <option value="<%=(String)values.get(i)%>" selected><%=(String)names.get(i)%></option>
		                  <%continue;}%>
		                  <option value="<%=(String)values.get(i)%>"><%=(String)names.get(i)%></option>
		                  <%}%>
		                  <%}%>
		                </select>
		              </div></td>
		          </tr>
		        </form>
		      </table></td>
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
</body>
</html>
