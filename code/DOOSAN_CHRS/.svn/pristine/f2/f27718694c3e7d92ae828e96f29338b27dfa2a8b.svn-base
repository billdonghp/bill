
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
	
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsCraft"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" 
     scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 工种设置</title>
    <script language="javascript">
function Update()
{
	
	var  evscodeid = document.getElementById("evscodeid").value;
	
    if (evscodeid=='')
    {
            alert("请在列表中选择要修改的项目.");
            return;
    }
    else
    {
        alert(ID)
               document.form1.action="/evsControlServlet?operation=evsgzupdate&evscodeid="+ID+"&menu_code=<%=request.getParameter("menu_code")%>";
	  	document.form1.submit();
    }
}
function Deleter()
{
	  
    if (ID=='')
    {
            alert("请在列表中选择要修改的项目.");
            return;
    }
    else
    {
        
      if( confirm("删除后,相关信息也将被清空!\n\n      确定要删除吗?") ) {
        document.form1.action="/evsControlServlet?operation=evsircraftdeletegz&evscodeid="+ID+"&menu_code=<%=request.getParameter("menu_code")%>";
	  	document.form1.submit();
      }
      else
      {
       return;}
    }

}
</script>

</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css"> 

<%
EvsCraft evscraft=new EvsCraft();
String code_name="";
code_name=request.getParameter("code_name")!=null?request.getParameter("code_name"):code_name;
if(code_name.equals("")){
	code_name=code_name+"";
}
%>
<table width="100%" border="0"   cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
		<%@ include file="../evs/inc/evs0125toolbar_v.jsp"%> 
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
	       		 	<td class="info_title_01" width="80">工种</td> 
	       		 	<FORM name="LGFORM" method="POST" action="/evs/<%=menu_code%>.jsp" >
					<td height="2" align="right" class="info_content_00">
					<input type="hidden"
						name="menu_code" value="<%=menu_code%>">
					<select name="code_name"
						onchange="LGFORM.submit();">
						<option value='' <%if(code_name.equals("")){out.print(" selected ");}%>>工种</option>
						<%
					List lEvcodename = evscraft.getEvscodeList();
					if(lEvcodename!=null){
					int evCodeSize=lEvcodename.size();
					
					for(int i=0;i<evCodeSize;i++){
						String codename="";
						codename=(String)lEvcodename.get(i);
					%>
						<option value=<%=codename%>
							<%if(code_name.equals(codename)){out.print(" selected ");}%>><%=codename%></option>
						<%}}%>
					</select>
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
				<td align="left" class="title1" colspan="10">
					工种设置</td>
			</tr>
		</table>
	
	<form name="form1" method="post" action="">	
 <table width="98%" border="0" align="center" cellpadding="0"
	       cellspacing="1">
	 <tr>
		<td class="line">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" onClick="HighLightTR('#F0F1F4','black','','')">
				<td width="8%" height="30" class="info_title_01">
				<div align="center">序号</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">工种</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">工种中文名称</div>
				</td>
				 <td width="15%" class="info_title_01">
				<div align="center">工种英文名称</div>
				</td>
				 <td width="17%" class="info_title_01">
				<div align="center">创建日期</div>
				</td>
				 <td width="17%" class="info_title_01">
				<div align="center">创建人</div>
				</td>
				<td width="17%" class="info_title_01">
				<div align="center">修改日期</div>
				</td>
				 <td width="17%" class="info_title_01">
				<div align="center">修改人</div>
				</td>
			</tr>
		              
	            <%
					List levcodeid=evscraft.getEvsParCodename(code_name);
					if(levcodeid!=null){ 
					   for(int i=0;i<levcodeid.size();i++){
					      evscraft=(EvsCraft)levcodeid.get(i);
					      	      
				 %>
						<tr	onClick="HighLightTR('#F0F1F4','black','<%=evscraft.getEvscodeid()%>','<%=menu_code%>')">
							<td height="30" >
							<div align="center">&nbsp;<%=i+1%></div>
							</td>
							<td>
							<div align="center" height="30" ><%=evscraft.getEvscodeid()%><input name="evscodeid" type="hidden" value="<%=evscraft.getEvscodeid()%>"/>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evscraft.getEvscodename()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evscraft.getEvsencodename()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evscraft.getCreatetime()%>&nbsp;</div>
							</td>
							 <td>
							<div align="center" height="30" ><%=evscraft.getEmpid()%><input name="cpnyId" type="hidden" value="<%=evscraft.getCpnyid()%>"/>&nbsp;</div>
							</td>
							 <td>
								<div align="center" height="30" ><%=evscraft.getUpdatetime()%>&nbsp;</div>
							</td>
							 <td>
							<div align="center" height="30" ><%=evscraft.getUempid()%>&nbsp;</div>
							</td>
						</tr>
						<%}}%>
		
		
		</table>
		</td>
		
	</tr>
	
	<tr>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		
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
</form>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
