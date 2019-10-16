<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.*" errorPage=""%>
<%@ page import="com.ait.sy.sy0102.bean.*"%>
<%@ page import="com.ait.sy.sy0102.bean.Ent"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.hrm.business.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>          
<html>
<head>                          
<title>系统维护>部门维护</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<jsp:include page="../inc/hrnav.jsp"/>
<link rel="stylesheet" type="text/css" href="/css/default.css">
</head>
<body>  
<%@ page import="com.ait.sy.sy0102.bean.*"%>
<%@ include file="../inc/import.jsp" %> 
<SCRIPT LANGUAGE="JavaScript" src="../js/sy0102.js"></SCRIPT>   
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.maintenance.dept.id_empty" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.dept.id_length" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.dept.name_empty" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.maintenance.dept.dept_length" module="sys"></ait:message>',
                    '<ait:message messageID="alert.sys.maintenance.dept.end_creation" module="sys"></ait:message>');
</script>                  
<%
	Ent Ent = new Ent();
    vlist = Biz.List();   
%>        
<%  
SimpleMap deptMap=new SimpleMap();                
List deptList=new ArrayList();
deptList=HrmServices.getInstance().retrieveDeptTree(deptMap);
request.setAttribute("dept_list",deptList);
%>

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
				<td align="left" class="title1" colspan="10"><!--添加部门  -->
	  <ait:message messageID="display.sys.basic_maintenance.dept_maintenance.departmental_information" module="sys"></ait:message>
				</td>
			</tr>
		</table>         
		<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
		  <tr>
		    <td class="line" >
		    <table  width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		        <form  name="EM2Form" method="post" action="/E<%=menu_code%>Control?flag=insert&menu_code=<%=menu_code%>" >
		            <tr>
		              <td width="15%" class="info_title_01" align=middle height="30"><!-- 部门ID -->
		      <ait:message messageID="display.sys.basic_maintenance.dept_maintenance.dept_id" module="sys"></ait:message>       
		              </td>  
		              <td width="85%" ><input  size=30 name="deptId" maxlength="10"></td>               
		            </tr>          
		            <tr>  
		              <td  class="info_title_01" align=middle height="30"><!-- 部门名称 -->
		 	<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.dept_name" module="sys"> </ait:message>            
		              </td>   
		              <td ><input type="text"  name="deptName" id="deptName" size=30 maxlength="50"></td>
		            </tr>
		            <tr>  
		              <td  class="info_title_01" align=middle height="30"><!-- 部门英文名称-->
		   	<ait:message messageID="display.sys.basic.dept.english_name" module="sys">  </ait:message>         
		              </td>   
		              <td ><input type="text"  name="deptEnName" id="deptEnName" size=30 maxlength="50"></td>
		            </tr>
		            <tr>
		              <td class="info_title_01" align=middle height="30"><!-- 创立时间 -->
		     	<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.setup_time" module="sys">  </ait:message>       
		              </td>     
		              <td ><input  type="text"  name="createdDate" size="30" readonly="true" onClick="setday(this);"></td>
		            </tr>
		            <tr>
		              <td class="info_title_01" align=middle height="30"><!-- 上级部门 -->
		         	<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.superior_dept" module="sys"> </ait:message>    
		              </td>      
		              <td >
						<ait:selDept dataListName="dept_list" name="parentDeptId" separator="&nbsp;&nbsp;" selected="${deptID}"  />
		              </td>
		           </tr>
		           </form>
		        </table>
		       </td>
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
