<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="com.ait.util.DateUtil"%>
<%@ page import="com.ait.evs.EvsCraft"%>
<jsp:useBean id="codemap_opt" class="java.util.HashMap" scope="page" />
<jsp:useBean id="codemap_type" class="java.util.HashMap" scope="page" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
    <%@ include file="/inc/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>评价 > 统计查询 > 工匠技师等级设置</title>
    
    <script language="javascript">
function Save()
{
  var str="";
  if(document.LGFORM.chinesename.value=="")
  {//请输入基本代码名称
  	alert('请输入姓名');
	return;
  }
  if(document.LGFORM.pjwd1.value=="")
  {//请输入基本代码名称
  	alert('请输入评价维度');
	return;
  }

  document.LGFORM.action="/evsControlServlet?operation=evsGjjsdjsave&menu_code=<%=request.getParameter("menu_code")%>";
  //alert(document.LGFORM.action);
  document.LGFORM.submit();
}
</script>
    
	</head>
	<body>
		<link href="/css/default.css" rel="stylesheet" type="text/css">
		<SCRIPT LANGUAGE="JavaScript" src="/evs/js/evs0101.js"></SCRIPT>
		
		<%String evscodeid = "";
			evscodeid = request.getParameter("ID") != null ? request
					.getParameter("ID") : "";

			%>
       <%
EvsCraft evscraft=new EvsCraft();
%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
	<%@ include file="inc/evstoolbar_a_new.jsp"%>
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
		<%@ include file="inc/evs_nav.jsp"%>
		
		<!-- display content -->
		<br>
		
  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
		
	 <form name="LGFORM" action="" method="POST" >
       <input type="hidden" name="flag" value="add">
        <input type="hidden" name="codeflag" value="CT"> 
       <input type="hidden" name="menu_code" value="<%=menu_code%>">
			<tr>
				<td align="left" class="title1" colspan="10">
					工匠技师查询</td>
			</tr>
		</table>
	
		  <table width="100%" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		   <tr>
		      <td width="15%" class="info_title_01"><!-- 基本代码 -->工匠技师姓名</td>
		      <td width="75%" class="info_content_00"><input name=chinesename type="text" size="15" ></td>
		    </tr>
		   <tr>
		      <td class="info_title_01"><!-- 工序-->
					工种
		      </td>
		        <td class="info_content_00"> 
		      
		            <c:forEach items="${searchExpiredLine}" var="EVSCT"   varStatus="s">
							 <input	type="checkbox" name="isChecked" value="${EVSCT.CODE_ID}" class="check" /> 
							 ${EVSCT.CODE_NAME}
					</c:forEach>
		        </td>
		    </tr>
		   
		    <tr>
		      <td width="15%" class="info_title_01">评价年度</td>
		      <td width="75%" class="info_content_00"><input name=evperiodid type="text" size="20" ></td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01">评价维度1</td>
		      <td width="75%" class="info_content_00"><input name=pjwd1 type="text" size="20" ></td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01">评价维度2</td>
		      <td width="75%" class="info_content_00"><input name=pjwd2 type="text" size="20" ></td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01">评价维度3</td>
		      <td width="75%" class="info_content_00"><input name=pjwd3 type="text" size="20" ></td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01">评价维度4</td>
		      <td width="75%" class="info_content_00"><input name=pjwd4 type="text" size="20" ></td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01">总分</td>
		      <td width="75%" class="info_content_00"><input name=sumstore type="text" size="20" ></td>
		    </tr>
		    <tr>
		      <td class="info_title_01">创建者</td>
		      <td width="75%" class="info_content_00"> <%=admin.getChineseName()%>
		      <input name="empid" type="hidden" value="<%=admin.getAdminID()%>"/>
		        </td>
		    </tr>
		     
		   
		  </table>
     <table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="10">
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
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
	</body>
</html>
