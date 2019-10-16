
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
<title>评价 > 基本设置 > 机种设置</title>
    <script language="javascript">
function Save()
{
 var  code_name1 = document.LGFORM.code_name1.value;
  document.LGFORM.submit();
}

function Add()
	{
	  document.LGFORM.action="/evsControlServlet?operation=evsircraftadd&menu_code=<%=request.getParameter("menu_code")%>";
	  document.LGFORM.submit();
	}
	
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
               document.form1.action="/evsControlServlet?operation=evsircraftupdate&evscodeid="+ID+"&menu_code=<%=request.getParameter("menu_code")%>";
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
        document.form1.action="/evsControlServlet?operation=evsircraftdelete&evscodeid="+ID+"&menu_code=<%=request.getParameter("menu_code")%>";
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
EvsCraft evscraft = new EvsCraft();  
String code_name1="";
code_name1=request.getParameter("code_name1")!=null?request.getParameter("code_name1"):code_name1;
if(code_name1.equals("")){
	code_name1=code_name1+"";
}
String code_name2="";
code_name2=request.getParameter("code_name2")!=null?request.getParameter("code_name2"):code_name2;
if(code_name2.equals("")){
	code_name2=code_name2+"";
}
String code_name3="";
code_name3=request.getParameter("code_name3")!=null?request.getParameter("code_name3"):code_name3;
if(code_name3.equals("")){
	code_name3=code_name3+"";
}
%>
<table width="100%" border="0"   cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
		<%@ include file="../evs/inc/evs0127toolbar_v.jsp"%> 
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
						<input type="hidden"
						name="codeflag1" value="CT">
						<input type="hidden"
						name="codeflag" value="LE">
					<select name="code_name1"  id = "code_name1"
						onchange="Save()">
						<option value='' <%if(code_name1.equals("")){out.print(" selected ");}%>>工种</option>
						<%
					List lEvcodename = evscraft.getEvscodeList();
					if(lEvcodename!=null){
					int evCodeSize=lEvcodename.size();
					
					for(int i=0;i<evCodeSize;i++){
						String codename1="";
						codename1=(String)lEvcodename.get(i);
					%>
						<option value=<%=codename1%>
							<%if(code_name1.equals(codename1)){out.print(" selected ");}%>><%=codename1%></option>
						<%}}%>
					</select>
					</td>
					</FORM>
					<td class="info_title_01" width="80">Line</td> 
	       		 	<FORM name="LGFORM1" method="POST" action="/evs/<%=menu_code%>.jsp" >
					<td height="2" align="right" class="info_content_00">
					<input type="hidden"
						name="menu_code" value="<%=menu_code%>">
					<select name="code_name2"
						onchange="LGFORM1.submit();">
						<option value='' <%if(code_name2.equals("")){out.print(" selected ");}%>>Line</option>
						<%
					List lEvLinecodename = evscraft.getEvscodeLineList();
					if(lEvLinecodename!=null){
					int evCodeSize=lEvLinecodename.size();
					
					for(int i=0;i<evCodeSize;i++){
						String codename2="";
						codename2=(String)lEvLinecodename.get(i);
					%>
						<option value=<%=codename2%>
							<%if(code_name2.equals(codename2)){out.print(" selected ");}%>><%=codename2%></option>
						<%}}%>
					</select>
					</td>
					</FORM>
					<td class="info_title_01" width="80">机种</td> 
	       		 	<FORM name="LGFORM2" method="POST" action="/evs/<%=menu_code%>.jsp" >
					<td height="2" align="right" class="info_content_00">
					<input type="hidden"
						name="menu_code" value="<%=menu_code%>">
					<select name="code_name3"
						onchange="LGFORM2.submit();">
						<option value='' <%if(code_name3.equals("")){out.print(" selected ");}%>>机种</option>
						<%
					List lEvAircraftcodename = evscraft.getEvscodeAircraftList();
					if(lEvAircraftcodename!=null){
					int evCodeSize=lEvAircraftcodename.size();
					
					for(int i=0;i<evCodeSize;i++){
						String codename3="";
						codename3=(String)lEvAircraftcodename.get(i);
					%>
						<option value=<%=codename3%>
							<%if(code_name3.equals(codename3)){out.print(" selected ");}%>><%=codename3%></option>
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
					机种设置</td>
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
				<td width="10%" height="30" class="info_title_01">
				<div align="center">序号</div>
				</td>
				<td width="12%" class="info_title_01">
				<div align="center">工种</div>
				</td>
				<td width="12%" class="info_title_01">
				<div align="center">Line</div>
				</td>
				<td width="13%" class="info_title_01">
				<div align="center">机种CODE</div>
				</td>
				<td width="13%" class="info_title_01">
				<div align="center">机种名称</div>
				</td>
				<td width="11%" class="info_title_01">
				<div align="center">机种英文名称</div>
				</td>
				 <td width="13%" class="info_title_01">
				<div align="center">创建日期</div>
				</td>
				 <td width="13%" class="info_title_01">
				<div align="center">添加者</div>
				</td>
				 <td width="38%" class="info_title_01">
				<div align="center">法人区分</div>
				</td>
			</tr>
		            
	            <%
	                if(code_name1!=null || code_name2!=null || code_name3!=null)
	                {
					  List levlinecodeid=evscraft.getEvsAircraftCodename(code_name1,code_name2,code_name3);
					  if(levlinecodeid!=null){ 
					     for(int i=0;i<levlinecodeid.size();i++){
					        evscraft=(EvsCraft)levlinecodeid.get(i);
	                
	                
				 %>
						<tr
							onClick="HighLightTR('#F0F1F4','black','<%=evscraft.getEvscodeid()%>','<%=menu_code%>')">
							<td height="30" >
							<div align="center">&nbsp;<%=i+1%></div>
							
							</td>
							<td>
							<div align="center" height="30" ><%=evscraft.getEvsparentcode2()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evscraft.getEvsparentcode()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evscraft.getEvscodeid()%>&nbsp;</div>
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
							<div align="center" height="30" ><%=evscraft.getEmpid()%>&nbsp;</div>
							</td>
							 <td>
							<div align="center" height="30" ><%=evscraft.getCpnyname()%><input name="cpnyId" type="hidden" value="<%=evscraft.getCpnyid()%>"/>&nbsp;</div>
							</td>
						</tr>
						<input type="hidden" name="evscodeid" value="<%=evscraft.getEvscodeid()%>" />
						<%}}}%>
						
		</table>
		</form>
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
