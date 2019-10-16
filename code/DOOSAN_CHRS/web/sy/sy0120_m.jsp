<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.ait.utils.*"%>
<%@ page import="com.ait.sy.sy0104.bean.*"%>
<%@ page import="com.ait.sy.sy0102.bean.*"%>
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
<jsp:include page="/inc/hrnav.jsp"/>
<%@ include file="../inc/importa.jsp"%>                            
<SCRIPT language=JavaScript
      src="../js/table.js"></SCRIPT>
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
	var url="syCheck0104.jsp?flag=update&empID="+document.EM2Form.empID.value+"&userID="+document.EM2Form.userID.value;
	window.open(url,'','width=200,height=50');
	return true;
}
</script>
<%	
	menu_code = request.getParameter("menu_code");
	Vector vlist2 = new Vector();
	com.ait.sy.sy0104.bean.Biz Biz2 = new com.ait.sy.sy0104.bean.Biz();
	String no = func.chkNull(request.getParameter("no"));
	String cpnyid=func.chkNull(request.getParameter("cpnyid"));
	int int_no=new Integer(no).intValue();
	ArrayList groups=(ArrayList)func.getGroup(int_no);
	ArrayList depts=(ArrayList)Biz2.getQuanxian(no);
	
	String searchopt = request.getParameter("searchopt");
	if (searchopt== null ){
		searchopt="";
	}
	else{
		searchopt = func.isoStr(searchopt);
	}
	String searchcontent = request.getParameter("searchcontent");
	if (searchcontent== null ){
		searchcontent="";
	}
	else{
		searchcontent = func.isoStr(searchcontent);
	}
	String fromdate = request.getParameter("fromdate");
	if (fromdate== null ){
		fromdate="";
	}
	String todate = request.getParameter("todate");
	if (todate== null ){
		todate="";
	}

%>
<%
    com.ait.sy.sy0104.bean.Ent Ent2 = new com.ait.sy.sy0104.bean.Ent();
	vlist2 = Biz2.Detail(no,cpnyid);
	Ent2=(com.ait.sy.sy0104.bean.Ent)vlist2.elementAt(0);
%>
<SCRIPT LANGUAGE="JavaScript" src="../js/sy0104_m.js"></SCRIPT>


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
				<td align="left" class="title1" colspan="10"><!-- 修改权限 -->
				<ait:message messageID="display.sys.authority_management.authority.modify_authority" module="sys"></ait:message>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30" align="left"><TABLE width="100%" border="1" align="center" cellPadding=4 cellSpacing=0 bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" rules=all>
		        <form  name="EM2Form" method="post" action="/E<%=menu_code%>Control?flag=updata&menu_code=<%=menu_code%>&no=<%=no%>" onSubmit="return CheckForm()" >
		          <TBODY>
		            <TR>
		              <TD height="30" align=middle class="info_title_01"><!--工号  -->
		              <ait:message messageID="display.sys.authority_management.authority.emp_no" module="sys"></ait:message>
		              </TD>
		              <TD><INPUT  size=40 type="hidden" name=empID value="<%=Ent2.getEmpID()%>">
		                <%=Ent2.getUserID()%></TD>		             
		            </TR>
		            <tr>
		              <TD height="30" align=middle class="info_title_01">法人区分</TD>
		              <TD><%=Ent2.getCpnyname()!=null?Ent2.getCpnyname():""%>&nbsp;</TD>
		            </tr>
		            
		            <TR>
		              <TD align=middle height=29 class="info_title_01"><!--权限组  -->
		              <ait:message messageID="display.mutual.authority_group"></ait:message>
		              </TD>
		              <TD><TABLE>
		                  <TR>
		                     <%
		         		 admin = (AdminBean)session.getAttribute("admin");
		         		 String langu=admin.getLanguagePreference();
		               if(langu.equals("zh"))  
		               {
		                  %>
		                    <TD><%=func.getGrant_Group_select(groups,func.chkNull(request.getParameter("cpnyid")))%>
		                 <%}else{ %>  
		                  <TD><%=func.getGrant_Group_en_select(groups,func.chkNull(request.getParameter("cpnyid")))%>    
		                  <%} %>     
		                  </TR>   
		                </TABLE></TD>
		            </TR> 
		            <TR>
		              <TD height="266" align=middle vAlign=top class="info_title_01"><!-- 权限级 -->
		              <ait:message messageID="display.sys.authority_management.authority.authority_grade" module="sys"></ait:message>
		              </TD>
		              <TD vAlign=top><%@ include file="../sy/zpss_orgall.jsp"%></TD>
		            </TR>
		          </TBODY>                 
		        </FORM>
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
