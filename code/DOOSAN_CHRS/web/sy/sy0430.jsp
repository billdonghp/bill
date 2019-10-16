<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.sql.*,com.ait.sy.bean.*,com.ait.util.StringUtil"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>        
<html>
<head>
<title>部门维护</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/default.css">
<jsp:include page="../inc/hrnav.jsp" />
<%@ page import="com.ait.sy.sy0102.bean.*"%>   
<script type="text/javascript">   

var msg=new Array('<ait:message messageID="alert.sys.select_edit_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.select_delete_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.associated_deleting" module="sys"></ait:message>');
</script>                                                 
<%@ include file="../inc/import.jsp"%>  
<script language="JavaScript1.2" type="text/JavaScript1.2">
MENU_CODE='<%=menu_code%>';                                 
</script>
<%

String key = request.getParameter("key")!=null?StringUtil.toCN(request.getParameter("key")):""; 

Ent Ent = new Ent();     
String x = request.getParameter("strPage");   
String y = request.getParameter("bigpage");
String message=request.getParameter("message")!=null?request.getParameter("message"):"";
PageControl pc = new PageControl(10, 10);  
int bigpage = pc.getTmpBig(y);
int strPage = pc.getTmpSmall(x, bigpage);
  admin = (AdminBean)session.getAttribute("admin");  
	String language=admin.getLanguagePreference();
String SEARCH_WHERE ="";

if (!key.equals("")) { //判断是否基于条件查询
	 request.setAttribute("key",key);  
  if(language.equals("zh"))
  {
	 
     SEARCH_WHERE = " where deptid like '%"  
			+ key
			+ "%' or deptname like '%"
			+ key
			+ "%' ";
  }else
  {     
      SEARCH_WHERE = " where deptid like '%"  
			+ key
			+ "%' or DEPT_EN_NAME like '%"
			+ key
			+ "%' ";
  
  }
	pc.setRowCount("HR_DEPARTMENT" + SEARCH_WHERE);    
} else {
	pc.setRowCount("HR_DEPARTMENT order by DEPTID desc ");         
}
pc.setintPage(strPage, bigpage);  
vlist = Biz.List_M(pc, key, language);
%>
  
<%if (!message.equals("")){ %>
 <%=message  %>                     
 <%}%>  
          
<table width="100%" border="0" cellspacing="0" cellpadding="0">
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

		<!-- display 3 level menu -->
		<%@ include file="../sy/inc/sy_toolbar.jsp" %>       
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--部门信息  -->
			<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.departmental_information" module="sys"></ait:message>	
				</td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"         
					cellspacing="0">
		  <tr>
		    <td>        
		    <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		        <tr>
		          <td width="6%" height="30" class="info_title_01"><!-- 序号 -->     
		          	<ait:message messageID="display.mutual.no" ></ait:message>	
		          </td>
		          <td width="8%" height="30" class="info_title_01"> <!--部门ID   -->
		       	<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.dept_id" module="sys"></ait:message>	   
		          </td>  
		          <td width="15%" height="30" class="info_title_01"> <!--部门名称  -->
		         	<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.dept_name" module="sys"></ait:message>	 
		           </td>
		          <td width="12%" height="30" class="info_title_01"> <!-- 部门成立时间  -->
		      	<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.setup_time" module="sys"></ait:message>	    
		          </td>
		          <td width="15%" height="30" class="info_title_01"><!-- 上级部门  --> 
		          	<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.superior_dept" module="sys"></ait:message>	
		          </td>                 
		          <td width="12%" height="30" class="info_title_01"> <!--添加者  -->
		         	<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.recorder" module="sys"></ait:message>	 
		           </td>
		          <td width="14%" height="30" class="info_title_01"> <!-- 添加时间  -->
		         	<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.record_time" module="sys"></ait:message>	 
		          </td>
		          <td width="11%" height="30" class="info_title_01"> <!--部门结束时间   -->
		          	<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.end_time" module="sys"></ait:message>	
		          </td>
		        </tr>
		        <%
				int listNo = 1;
				for (int i = 0; i < vlist.size(); i++) {
					Ent = (Ent) vlist.elementAt(i);    
					String deptname = Ent.getDeptName();

					if (Ent.getDeptLevel() >= 2) {
						for (int k = 1; k < Ent.getDeptLevel(); k++)
							deptname = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + deptname;
				}
				%>
		        <tr bgcolor="#FFFFFF" onClick="HighLightTR('#E7F0EF','black','<%=Ent.getDeptID()%>','<%=menu_code%>')">
		          <td height="30" align="center" style="color: #666666;"><%=listNo%><%listNo = listNo + 1;%></td>
		          <td align="center" style="color: #666666;">
		          <a href="sy_dept_order_change.jsp?parentDeptId=<%=Ent.getDeptID()%>">
		          <%=StringUtil.checkNull(Ent.getDeptID())%>
		          </a>      
		          </td>
		          <td align="center" style="color: #666666;" nowrap><%=deptname%>&nbsp;</td>
		          <td align="center" style="color: #666666;"><%=StringUtil.checkNull(Ent.getCreatedDate())%>&nbsp;</td>
		          <td align="center" style="color: #666666;"><%=StringUtil.checkNull(Ent.getParentDeptName())%>&nbsp;</td>
		          <td align="center" style="color: #666666;"><ait:content enContent="<%=StringUtil.checkNull(Ent.getChiesePinYin())%>" zhContent="<%=StringUtil.checkNull(Ent.getCreatorID())%>" koContent="<%=StringUtil.checkNull(Ent.getKor_name())%>"/>&nbsp;</td>
		          <td align="center" style="color: #666666;"><%=StringUtil.checkNull(Ent.getCreateDate())%>&nbsp;</td>
		          <td align="center" style="color: #666666;"><%=StringUtil.checkNull(Ent.getEndEddate())%>&nbsp;</td>
		        </tr>
		        <%}%>
		      </table></td>
		  </tr>
		</table>
		<%@ include file="../inc/pagecontrol_department.jsp"%>

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
