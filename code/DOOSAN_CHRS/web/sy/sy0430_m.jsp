<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.sy.bean.*" errorPage=""%>
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
</head>
<body>
<jsp:include page="../inc/hrnav.jsp" />
<%@ include file="../inc/import.jsp"%>
<%
Ent Ent1;
Vector vlist1 = Biz.Detail(no);                     
Ent1 = (Ent) vlist1.elementAt(0);
%>
<%
SimpleMap deptMap=new SimpleMap();
List deptList=new ArrayList();
deptList=HrmServices.getInstance().retrieveDeptTree(deptMap);
request.setAttribute("dept_list",deptList);
%>
<SCRIPT LANGUAGE="JavaScript" src="/js/sy0102.js"></SCRIPT>
<!-- tool bar -->
<%@ include file="../sy/sy0102save.jsp" %>   

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
				<td align="left" class="title1" colspan="10"><!-- 部门修改 -->
		 <ait:message messageID="display.sys.basic.dept.edit" module="sys">	</ait:message>	
				</td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td class="line">
		    <form name="EM2Form" method="post" action="/E<%=menu_code%>Control?flag=updata&menu_code=<%=menu_code%>&no=<%=no%>" onSubmit="return CheckForm()">
		        <TABLE width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		          <input type="hidden" name="createdDate" value="<%=Ent1.getCreatedDate()%>" />
		          <TBODY>
		            <tr>
		              <td width="15%" align=middle  height="30" class="info_title_01"><!--部门ID  --> 
		        <ait:message messageID="display.sys.basic_maintenance.dept_maintenance.dept_id" module="sys"> </ait:message>      
		               </td>          
		              <td width="85%">
		                  <input type="text" size=30 name="deptId" value="<%=Ent1.getDeptID()%>" readonly="readonly">
		                </td>
		            </tr>
		            <tr>
		              <td align=middle  height="30" class="info_title_01"><!-- 部门名称 -->  
		          <ait:message messageID="display.sys.basic_maintenance.dept_maintenance.dept_name" module="sys"> </ait:message>    
		              </td>
		              <td >
		                  <input type="text"  size=30 name="deptName" value="<%=Ent1.getDeptName()%>">
		                </td>
		            </tr>
		             <tr>    
		              <td  align=middle height="30" class="info_title_01"><!--部门英文名称  -->
		        <ait:message messageID="display.sys.basic.dept.english_name" module="sys">  </ait:message>     
		              </td>   
		              <td ><input type="text" name="deptEnName" value="<%=(String)Ent1.getDeptEnName()%>"></td>           
		            </tr>
		            <tr>
		              <td align=middle  height="30" class="info_title_01"> <!-- 创立时间 --> 
		         <ait:message messageID="display.sys.basic_maintenance.dept_maintenance.setup_time" module="sys">  </ait:message>    
		              </td>
					 <td width="227"><%=Ent1.getCreatedDate()%>&nbsp;</td>
		
		            </tr>
		            <tr>   
		              <td align=middle  height="30" class="info_title_01"><!-- 结束时间  -->
		        <ait:message messageID="display.sys.basic_maintenance.dept_maintenance.end_time" module="sys">   </ait:message>    
		               </td>
		              <td >
		              <input  type="text" name="endEddate" size="30" value="<%=Ent1.getEndEddate()%>"  onClick="setday(this);">
		               </td>
		            </tr>
		            <tr>
		              <td align=middle  height="30" class="info_title_01"><!--  上级部门-->
		       <ait:message messageID="display.sys.basic.dept.superior_dept" module="sys">   </ait:message>     
		              </td>                    
		              <td >
		              <%request.setAttribute("deptid",Ent1.getParentDeptNo());%>
		              <ait:selDept dataListName="dept_list" name="parentDeptId" separator="&nbsp;&nbsp;" selected="${deptid}" includeOverdue="Y"  /></td>
		            </tr>
		          </tbody>
		        </TABLE>
		      </form></td>
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
