<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" 
	import="java.sql.*,
	com.ait.sy.bean.*,
	com.ait.sy.sy0104.bean.*,
	com.ait.sy.sy0104.bean.Ent,
	com.ait.util.StringUtil" %>
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
<jsp:include page="/inc/hrnav.jsp" />
<%@ include file="../inc/import.jsp"%>
<script language="javascript">
MENU_CODE='<%=menu_code%>';
function Search()
{
     document.searchForm1.action="/sy/<%=menu_code%>.jsp?";
     document.searchForm1.submit();
}
</script>
<%		
		Ent ent = new Ent();
		String x = request.getParameter("strPage")!=null?request.getParameter("strPage"):"1";
		String y= request.getParameter("bigpage")!=null?request.getParameter("bigpage"):"1";     
		PageControl pc=new PageControl(10,10);
		int bigpage=pc.getTmpBig(y);
		int strPage=pc.getTmpSmall(x,bigpage);
		String key=request.getParameter("key")!=null?request.getParameter("key"):"";
        String where =request.getParameter("where")!=null?request.getParameter("where"):"";
		String SQL ="" ; //" AND INSTR(SCREEN_GRANT_NO,'100') = 0";
		where=func.isoStr(where);
		if(!where.equals("") && !key.equals("")){
              SQL = " AND SY_ADMIN." + where + " LIKE '%" +key +"%' ";
              if(where.equals("CHINESENAME")){
                SQL = " AND( HR_EMPLOYEE_V.CHINESENAME LIKE '%" + key + "%' OR LOWER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE LOWER('%"+key+"%') )";
              }
	       ///   if(where.equals("DUTY")){
	       	  ///	SQL = " and (select DUTY from hr_emp_BASICINFO_V where empid=adminid) like '%" + key +"%'";
	        ///  }
              if(where.equals("SCREEN_GRANT_NAME")){
                SQL =" AND (SYS_GET_SCREEN_NAME(SY_ADMIN.ADMINID) LIKE '%" +key +"%'OR sys_get_screen_en_name(SY_ADMIN.ADMINID) LIKE"+"'%"+key+"%')";
              }
        }                   
		//pc.setRowCount("SY_ADMIN, SY_LOGIN_SCREEN, HR_EMPLOYEE_V "
		//		+ "WHERE SY_LOGIN_SCREEN.SCREEN_GRANT_NO = SY_ADMIN.SCREEN_GRANT_NO "  
		//		+ "AND SY_ADMIN.ADMINID = HR_EMPLOYEE_V.EMPID "
		//		+ " " + SQL);
		pc.setRowCount(" (SELECT DISTINCT *  FROM  SY_ADMIN, HR_EMPLOYEE_V "
				+ "WHERE SY_ADMIN.ADMINID = HR_EMPLOYEE_V.person_id  AND  (HR_EMPLOYEE_V.date_left BETWEEN TO_DATE(TO_CHAR(add_months(SYSDATE, -1), 'YYYY-MM-DD'), 'YYYY-MM-DD') "+    
                  "  AND TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD') OR HR_EMPLOYEE_V.DATE_LEFT IS NULL) AND HR_EMPLOYEE_V.cpny_id = '"+admin.getCpnyId()+"' 	"
				+ " " + SQL+")");
		pc.setintPage(strPage,bigpage);                 
		vlist = Biz.ListSQL(pc,SQL);  
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../sy/inc/sy_toolbar_notUpdateButton.jsp" %>
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
				<td align="left" class="title1" colspan="10"><!--权限  -->
				   <ait:message messageID="display.sys.authority_management.authority" module="sys"></ait:message>
				</td>
			</tr>
		</table>
		<table width="100%"  border="0"  cellpadding="0" cellspacing="0">
		  <tr>
		    <td colspan="2" class="line">
		    <table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		        <tr>
		          <td height="30" class="info_title_01" ><!-- 序号 -->
		           <ait:message messageID="display.mutual.no" ></ait:message>  
		          </td>
		          <td height="30" class="info_title_01"><!-- 员工号 -->
		          <ait:message messageID="display.mutual.emp_id_2" ></ait:message>   
		          </td>
		          <td height="30" class="info_title_01"><!-- 姓名 -->
		           <ait:message messageID="display.mutual.name"></ait:message>  
		          </td>
		          <td height="30" class="info_title_01">法人区分</td>		     
		          <td height="30" class="info_title_01"><!--  部门-->
		            <ait:message messageID="display.mutual.department" ></ait:message> 
		          </td>
		          <!--<td height="30" class="info_title_01"> 登录名 
		             <ait:message messageID="display.sys.authority_management.authority.login_id" module="sys"></ait:message>
		          </td>
		          <td height="30" class="info_title_01"> 密码 
		             <ait:message messageID="display.sys.authority_management.authority.password" module="sys"></ait:message>
		          </td>
		          --><td height="30" class="info_title_01"><!--  权限组-->
		             <ait:message messageID="display.mutual.authority_group" ></ait:message>
		          </td>  
		        </tr>                    
		        <%
				int listNo = 1 ;
				for ( int i = 0 ; i < vlist.size() ; i++ ){
					ent=(Ent)vlist.elementAt(i) ;
				%>
		        <tr align="center"  bgcolor="#FFFFFF" onClick="HighLightTR('#E7F0EF','black','<%=ent.getLoginNo()%>','<%=menu_code%>','<%=ent.getCpnyId()%>')">
		          <td height="30" align="center" style="color: #666666;">
		          <%=listNo+(strPage-1)*10+(bigpage-1)*100%>     
		          <% listNo = listNo + 1 ;%></td>  
		          <td ><%=ent.getUserID()%></td>                
		           <%
				   admin = (AdminBean)session.getAttribute("admin");
				   String language=admin.getLanguagePreference();
				   if(language.equals("zh"))  
				   {
				   %>
		          <td align="center" style="color: #666666;"><%=ent.getChineseName()%></td>
		          <td align="center" style="color: #666666;"><%=ent.getCpnyname()!=null?ent.getCpnyname():""%>&nbsp;</td>
		          <td align="center" style="color: #666666;"><%=StringUtil.checkNull(ent.getDepartMent())%>&nbsp;</td>
		          <!--<td align="center" style="color: #666666;"><%=ent.getUserID()%></td>
		          <td align="center" style="color: #666666;"><%=ent.getPassWord()%></td>
		          --><td align="center" style="color: #666666;"><%=ent.getGrantName()%></td>
		          <%}else{ %>
		          
		           <td align="center" style="color: #666666;"><%=ent.getChinisenamePingYing() %></td>
		          <td align="center" style="color: #666666;"><%=ent.getDepartMentEnName()%></td>  
		          <!--<td align="center" style="color: #666666;"><%=ent.getUserID()%></td>
		          <td align="center" style="color: #666666;"><%=ent.getPassWord()%></td>
		          --><td align="center" style="color: #666666;"><%=ent.getGrantEnName()%></td>
		        <%} %>
		        </tr>
		        <%}%>
		      </table></td>
		  </tr>              
		  <tr>
		    <td><%@ include file = "../inc/pagecontrol.jsp"%></td>
		    <td width="48%">
		    <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
		        <form name="searchForm1"  method="post" action="">
		          <input name="menu_code" type="hidden" value="<%=menu_code%>">
		          <tr>
		            <td height="30" align="right"><input name="key" type="text" value="<%=key%>">
		              <select name="where">
		                <option value="USERNAME" <%if(where.equals("USERNAME")){%> selected <%}%>><!-- 工号 -->
		                   <ait:message messageID="display.mutual.emp_id_2" ></ait:message>
		                </option>
		                <option value="CHINESENAME"<%if(where.equals("CHINESENAME")){%> selected <%}%>><!--中文名  -->
		                   <ait:message messageID="display.mutual.name" ></ait:message>
		                </option>
		                <%--<option value="USERNAME"<%if(where.equals("USERNAME")){%> selected <%}%>><!-- 登陆名 -->
		                   <ait:message messageID="display.sys.authority_management.authority.login_id" module="sys"></ait:message>          
		                </option>
		                --%>
		                <option value="SCREEN_GRANT_NAME"<%if(where.equals("SCREEN_GRANT_NAME")){%> selected <%}%>><!-- 权限组 -->
		                   <ait:message messageID="display.mutual.authority_group" ></ait:message>
		                </option>
		                <%--<option value="DUTY" <%if(where.equals("DUTY")){out.print("selected");}%>>职责</option>\--%>
		                </select>
		            </td>
		            <td width="90" align="center">
		            <ait:image src="/images/btn/Search.gif" align="absmiddle" onclick="javascript:Search();" style="cursor:hand" />               
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
