
<%@ page contentType="text/html; charset=UTF-8" language="java"	errorPage=""%>
	
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsGxjndj"%>
<%@ page import="com.ait.evs.EvsCraft"%>
<%@ page import="com.ait.evs.Gxjsdj"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"     scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>技能评价 > 统计查询 > 工匠技工查询</title>
    <script language="javascript">
function Save()
{
 var  code_name = document.LGFORM.code_name.value;
  document.LGFORM.submit();
}
function Update()
{
	//alert(ID+"aaa"+status);
    if (ID=='')
    {
            alert("请在列表中选择要修改的项目.");
            return;
    }
    else
    {
    	 if (status=='2')
    	    {
    	            alert("修改决裁中，请等待决裁");
    	            return;
    	    }else{
    	    	// alert(ID)
    	        document.form1.action="/evsControlServlet?operation=evsGjjsdjupdate&evscodeid="+ID+"&menu_code=<%=request.getParameter("menu_code")%>";
    		  	document.form1.submit();
        	    }      	    
    	
    }
}
function Add()
	{
	  document.LGFORM.action="/evsControlServlet?operation=evsgjjsdjadd&menu_code=<%=request.getParameter("menu_code")%>";
	  document.LGFORM.submit();
	}
function Search() {
	//document.evs0302.action="/arControlServlet?operation=ar_pagecontrol&page=detail_v&menu_code=ar0201&flag=search";
	 document.form1.submit();
}
function Delete()
{
    if (ID=='')
    {
            alert("请在列表中选择要删除的项目.");
    }
    else
    {
        alert("11");
            if( confirm("删除后,相关信息也将被清空!\n\n      确定要删除吗?") ) {
                    url='/evsControlServlet?operation=evsGjjsdjdelete&menu_code='+MENU_CODE+'&ID='+ID+'&ID2='+ID2;
                    location.href=url;
            }
            else
            {
            return;
            }
    }

}
</script>

</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css"> 

<%
EvsCraft evscraft = new EvsCraft();  
EvsGxjndj evsGxjndj = new EvsGxjndj();  
Gxjsdj evsGxjsdj = new Gxjsdj();  
Gxjsdj evsGxjndj0 = new Gxjsdj();  
Gxjsdj evsGxjndj1 = new Gxjsdj();  
String code_name="";
code_name=request.getParameter("code_name")!=null?request.getParameter("code_name"):code_name;
if(code_name.equals("")){
	code_name=code_name+"";
}
String code_name1="";
code_name1=request.getParameter("code_name1")!=null?request.getParameter("code_name1"):code_name1;
if(code_name1.equals("")){
	code_name1=code_name1+"";
}
EvsPeriod evsPeriod=new EvsPeriod();
String ev_year="";
ev_year=request.getParameter("ev_year")!=null?request.getParameter("ev_year"):ev_year;
Calendar ca = new GregorianCalendar();
int year = ca.get(Calendar.YEAR);
if(ev_year.equals("")){
	ev_year=year+"";
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
 		<%@ include file="../evs/inc/evs0307toolbar_v.jsp"%>  
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
	       		 	<FORM name="LGFORM1" method="POST" action="/evs/<%=menu_code%>.jsp" >
					<td height="2" align="right" class="info_content_00">
					<input type="hidden"
						name="menu_code" value="<%=menu_code%>">
					<select name="ev_year"	onchange="LGFORM.submit();">
						<option value='' <%if(ev_year.equals("")){out.print(" selected ");}%>>评价年</option>
						<%
					List lEvYear=evsPeriod.getEvsYearList();
					if(lEvYear!=null){
					int evYearSize=lEvYear.size();
					
					for(int i=0;i<evYearSize;i++){
						String Year="";
						Year=(String)lEvYear.get(i);
					%>
						<option value=<%=Year%>
							<%if(ev_year.equals(Year)){out.print(" selected ");}%>><%=Year%></option>
						<%}}%>
					</select>
					</td>
					</FORM>
	       		 	<td class="info_title_01" width="80">工种</td> 
	       		 	<FORM name="LGFORM" method="POST" action="/evs/<%=menu_code%>.jsp" >
					<td height="2" align="right" class="info_content_00">
					<input type="hidden"
						name="menu_code" value="<%=menu_code%>">
						<input type="hidden"
						name="codeflag" value="CT">
					<select name="code_name" id = "code_name"
						onchange="Save()">
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
					工匠技工录入查询</td>
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
				
				<td width="15%" class="info_title_01">
				<div align="center">工匠技工姓名</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">评价年度</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">工种</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">评价维度1</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">评价维度2</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">评价维度3</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">评价维度4</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">总分</div>
				</td>
				 <td width="15%" class="info_title_01">
				<div align="center">创建日期</div>
				</td>
				 <td width="17%" class="info_title_01">
				<div align="center">创建者</div>
				</td>
				 <td width="15%" class="info_title_01">
				 <div align="center">修改日期</div>
				</td>
				 <td width="17%" class="info_title_01">
				<div align="center">修改者</div>
				</td>
				
			</tr>
		            
	            <%
	            	List levStypecodeid1 = evsGxjsdj.getEvsGxjsDJRemark(code_name,code_name1);
				  
				 	 if(levStypecodeid1!=null){ 
					     
					    	 evsGxjndj1=(Gxjsdj)levStypecodeid1.get(0);
					     
					  }
	                if(code_name!=null)
	                {
					  List levStypecodeid = evsGxjsdj.getEvsGxjsDJCodes(code_name,code_name1);
					  if(levStypecodeid!=null){ 
					     for(int i=0;i<levStypecodeid.size();i++){
					    	 evsGxjndj0=(Gxjsdj)levStypecodeid.get(i);
	                
	                
				 %>
						<tr
							onClick="HighLightTR('#F0F1F4','black','<%=evsGxjndj0.getCodeNo()%>','<%=evsGxjndj0.getStatus()%>','<%=menu_code%>')">
							<td height="30" >
							<div align="center">&nbsp;<%=i+1%></div>
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getEmpName()%></div>
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getEvperiodid()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getCodeid()%>&nbsp;
							<input name="evscodeid" type="hidden" value="<%=evsGxjndj0.getCodeNo()%>"/></div>
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getPJWD1()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getPJWD2()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getPJWD3()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getPJWD4()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getSUMSTORE()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getCreateDate()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getCreateBy()%>&nbsp;</div>							
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getUpdatetime()%>&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" ><%=evsGxjndj0.getUempid()%>&nbsp;</div>							
							</td>
							
						</tr>
						<%}}}%>
						<tr>
				<td colspan="3" class="info_title_01"  align="left" height="20px">评价规则<font
					color="red">(必填)</font></td>
				
				</tr>	
				<tr>
				
					<td colspan="13"><textarea name="gjjsRemark" readonly
						style="height: 60px; width: 800px"><%=evsGxjndj1.getRemark()%></textarea></td>
				</tr>	
			
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
</form>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
