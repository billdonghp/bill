<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsDept"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 评价部门设置</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css"> 


<%
EvsDept evsDept=new EvsDept();
String ev_period_id="";
ev_period_id=request.getParameter("ev_period_id")!=null?request.getParameter("ev_period_id"):ev_period_id;

%>
<script language="JavaScript">
<!--
function FillEvPeriod()
{
	if (document.LGFORM.ev_period_id.value == "")
	{
		alert("请正确填写评价期间代码");
		document.LGFORM.NewEvPeriodID.focus();
	}
	else
	{	
		evPeriodId=document.LGFORM.ev_period_id.value;
		
		location.href='/evs/evs0103_t.jsp?menu_code=evs0103&flag=add&ev_period_id='+evPeriodId;
		
	}
}
//-->
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
			
		<%@ include file="inc/evs0302toolbar_v.jsp"%>
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
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
				<ait:message messageID="display.mutual.search_criteria" /></td>
		</tr>
	    <tr>
	      <td >
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       		 <tr>
	       		 	<td class="info_title_01" width="80">评价年</td>	
					<FORM name="LGFORM" method="POST" action="/evs/<%=menu_code%>.jsp">
					<td height="2" align="right" class="info_content_00">
						<input type="hidden" name="menu_code" value="<%=menu_code%>">
						<select name="ev_period_id" onchange="LGFORM.submit();">
						<%
					EvsPeriod evsP=new EvsPeriod();
					if(ev_period_id.equals("")){
						try{
							ev_period_id=evsP.getCurrentEvPeriod();
						}catch(Exception e){}	
					}
					List lEvsPeriod=null;
					try{
						lEvsPeriod=evsP.getEvsPeriodByYear("");
					}catch(Exception e){}
					if(lEvsPeriod!=null){
					int periodSize=lEvsPeriod.size();
					
					for(int i=0;i<periodSize;i++){
						EvsPeriod evsPeriod=(EvsPeriod)lEvsPeriod.get(i);
						
					%>
						<option value="<%=evsPeriod.getEvPeriodID()%>"
							<%if(evsPeriod.getEvPeriodID().equals(ev_period_id)){out.print(" selected ");}%>><%=evsPeriod.getEvPeriodName()%></option>
						<%}}%>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a onClick="FillEvPeriod()" style="cursor:hand"><font color="red">刷新</font></a>
					</td>
					</FORM>
				</tr>
	       	</table>
	      </td>
	     </tr>
	     </table>
		<!-- display 3 level menu -->
		<%@ include file="inc/evs_nav.jsp"%>
		
		<!-- display content -->
		<br>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">评价部门</td>
			</tr>
		</table>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td class="line">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td width="10%" height="20" class="info_title_01">
				<div align="center">序号</div>
				</td>
				<td width="60%" class="info_title_01">
				<div align="center">部门名称</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">活跃性</div>
				</td>
			</tr>
			<%
		
		List levsDept=evsDept.getDeptByPeriodId(ev_period_id);
		if(levsDept!=null){ 
		  int evDeptSize=levsDept.size();
		   for(int i=0;i<evDeptSize;i++){
		      evsDept=(EvsDept)levsDept.get(i);
		      
		      
		%>
			<tr>
				<td height="30" class="info_content_01">
				<div align="center"><%=i+1%>&nbsp;</div>
				</td>
				<td align="left" class="info_content_00"><%
				String ev_dept_name="";
				int ev_dept_level=evsDept.getEvDeptLevel();
				
				for(int j=0;j<ev_dept_level;j++){
					ev_dept_name=ev_dept_name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}
				out.print(ev_dept_name+evsDept.getEvDeptName());
				%></td>
				<td height="30" class="info_content_01">
				<div align="center" ><%
                          int activity;
                          activity=evsDept.getActivity()==0?1:0;                         
                          %> 
                          <a
					href="/evs/<%=menu_code%>_t.jsp?flag=update&menu_code=<%=menu_code%>&ev_period_id=<%=evsDept.getEvPeriodID()%>&ev_dept_id=<%=evsDept.getEvDeptID()%>&activity=<%=activity%>">
				<img src="../images/a_<%=evsDept.getActivity()+""%>.gif"></a> </div>
				</td>
			</tr>
			<%}}%>
		</table>
		</td>
	</tr>
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
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>

