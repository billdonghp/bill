
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%@ include file="/inc/taglibs.jsp"%> 
<script language="JavaScript" type="text/javascript" src="">

  function showEvMaster(empID,evPeriodId) {
    var theUrl = "../evs/showEvsMaster.jsp?"+"empID="+empID+"&evPeriodId="+evPeriodId;
    var name = "newWin";
    var features = "status=no,menubar=no,resizable=yes,scrollbars=yes,width=390,height=400";
    window.open(theUrl,name,features);
    
  }
   function gogogo() {
   HighLightTR('#F0F1F4','black','','','');
   location.href='#'+document.all.GoEmp.value;
   
  }
 
  </script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 评价者设置</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css"> 

<%
String evDeptId="";
String evPeriodId="";
String evTypeId="";
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;

EvsPeriod evsP=new EvsPeriod();
if(evPeriodId.equals("")){
	try{
		evPeriodId=evsP.getCurrentEvPeriod();
	}catch(Exception e){}
}
//List lEvsDept=null;
List lEvsPeriod=null;
List lEvsType=null;
List lEvsEmp = new ArrayList() ;
List lEvsProcess=null;

EvsEmp evsEmp = new EvsEmp();
EvsProcess evsProcess = new EvsProcess(evPeriodId,evTypeId);
try{
	
	//lEvsDept = EvsEmp.getEvEmpDeptList(evPeriodId);
	lEvsPeriod = EvsEmp.getEvEmpPeriodList();
	lEvsType = EvsEmp.getEvEmpTypeList();
	
	if (evPeriodId != null && evPeriodId.length() > 0 && evTypeId != null && evTypeId.length() > 0){
		lEvsEmp = evsEmp.getEvEmpsByDeptPeriodType(evDeptId,evPeriodId,evTypeId);
		lEvsProcess=evsProcess.getProcessByTypePeriod();
	}
	
}catch(Exception e){e.printStackTrace() ;}
%>
<table width="100%" border="0"   cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
		<%@ include file="../evs/inc/evstoolbar_v0125.jsp"%> 
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
	       		 <form action="/evs/evs0105.jsp?menu_code=evs0105" method="Post"
		name="evs0105">
	<tr>
		<td class="info_title_01" width="80">工号/姓名</td>
		<td class="info_content_00"><input type="text" name="GoEmp" id="GoEmp" size="6"> &nbsp;<a onClick="gogogo();" style="cursor:hand">搜索</a></td>
		<td class="info_title_01" width="80">评价部门</td>
		<td class="info_content_00">
		<ait:selEvDept name="evDeptId" evPeriodId="<%=evPeriodId %>" all=" " onChange="evs0105.submit();" selectEvDeptId="<%= evDeptId %>" />
		</td>
		<td class="info_content_00">&nbsp;</td>
		</tr>
		<tr>
		<td class="info_title_01" width="80">评价区间</td>
		<td class="info_content_00">
		<select name="evPeriodId" onChange="evs0105.submit();">
			<%
                            if(lEvsPeriod!=null){
                            	int lEvsPeriodSize=lEvsPeriod.size();
                            	for(int i=0;i<lEvsPeriodSize;i++){
                            		EvsPeriod evsPeriod_sel=(EvsPeriod)lEvsPeriod.get(i);
                              %>
			<option value="<%=evsPeriod_sel.getEvPeriodID()%>"
				<%if (evPeriodId.equals(evsPeriod_sel.getEvPeriodID())) {%>
				selected="selected" <%}%>><%=evsPeriod_sel.getEvPeriodName()%></option>
			<%}}%>
		</select>
		</td>
		<td class="info_title_01" width="80">评价类型</td>
		<td class="info_content_00"><select name="evTypeId" onChange="evs0105.submit();">
			<option value="">评价类型</option>
			<%
                            if(lEvsType!=null){
                            	int lEvsTypeSize=lEvsType.size();
                            	for(int i=0;i<lEvsTypeSize;i++){
                            		EvsType evsType_sel=(EvsType)lEvsType.get(i);
                              %>
			<option value="<%=evsType_sel.getEvTypeID()%>"
				<%if (evTypeId.equals(evsType_sel.getEvTypeID())) {%>
				selected="selected" <%}%>><%=evsType_sel.getEvTypeName()%></option>
			<%}}%>
		</select>
		</td>
		<td class="info_content_00">
		<a target="_blank"
			href="/evs/excel/evs0105_excel.jsp?evDeptId=<%=evDeptId%>&evPeriodId=<%=evPeriodId%>&evTypeId=<%=evTypeId%>">
		<!--<img src="/images/ev/p_excel.gif" width="62" height="21" border="0"
			align="absmiddle" title="导出EXCEL"> --></a>
		</td>
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
					评价者设置</td>
			</tr>
		</table>
	
	
 <table width="98%" border="0" align="center" cellpadding="0"
	
			<tr>
				<td class="line" valign="top">
				<table width="100%" border="1" align="center" cellpadding="0"
					cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
					style="padding: 2px 2px 2px 2px;">
					<tr align="center" bgcolor="#F5F5F5">
						<td width="30" height="30" class="info_title_01"><a onClick="selectall();" style="cursor:hand">全选</a></td>
						<td class="info_title_01" nowrap="nowrap">
						部门名称
						</td>
						<td  class="info_title_01" nowrap="nowrap">
						工号
						</td>
						<td  class="info_title_01" nowrap="nowrap">
						姓名
						</td>
						<td width="7%" class="info_title_01">
					<!--评价类型--> 评价类型
					</td>
						<%		
							if(lEvsProcess!=null){
								int lEvsProcessSize = lEvsProcess.size();
								for(int k=0;k<lEvsProcessSize;k++){
									EvsProcess evsProcess_tr=(EvsProcess)lEvsProcess.get(k);
						%>
						<td class="info_title_01"><%= StringUtil.formatColumnName(evsProcess_tr.getEvProcessName()) %></td>
						<% 
								}
							}
						%>
						</tr>
					<%
						if(lEvsEmp!=null){
							int lEvsEmpSize=lEvsEmp.size();
			    			for(int i=0;i<lEvsEmpSize;i++){
			        			EvsEmp evsEmp_tr=(EvsEmp)lEvsEmp.get(i);
			        %>
					<tr  onClick="HighLightTR('#F0F1F4','black','<%=evsEmp_tr.getEvEmpID()%>','<%=evsEmp_tr.getEvPeriodID()%>','<%=menu_code%>')">
						<td height="30" align="center">
							<input type="checkbox" name="empid<%=i%>"value="<%= evsEmp_tr.getEvEmpID() %>">
							<input type="hidden" name="menu_code" value="<%=menu_code%>">
						</td>
						<td align="center" id="leftnewstd" title="<%=StringUtil.checkNull(evsEmp_tr.getEvDeptName())%>">
						<span class="ellipsis_row"><%=StringUtil.checkNull(evsEmp_tr.getEvDeptName())%></span>
						<input type="hidden" name="evDeptId<%=evsEmp_tr.getEvEmpID() %>" value="<%= evsEmp_tr.getEvDeptID() %>">
						<input type="hidden" name="evTypeId<%=evsEmp_tr.getEvEmpID() %>" value="<%= evsEmp_tr.getEvTypeID() %>">
						</td>
						<td align="center"  nowrap="nowrap">
						<%=evsEmp_tr.getEvEmpID2()%>
						<a name="<%=evsEmp_tr.getEvEmpID2()%>"></a>
						</td>
						<td align="center" nowrap="nowrap">
						<%= evsEmp_tr.getEvEmpName() %>
						</td>
						<td class="info_content_01">
							<%=StringUtil.checkNull(evsEmp_tr.getEvTypeName())%>&nbsp;
							</td>
						<%		
							if(lEvsProcess!=null){
								int lEvsProcessSize = lEvsProcess.size();
								for(int k=0;k<lEvsProcessSize;k++){
									EvsProcess evsProcess_tr=(EvsProcess)lEvsProcess.get(k);
									List lEvMaster=new Vector();
									boolean isEquals=false;
									String evMaster = "" ;
									String evMasterName = "" ;
		                            if(evsEmp_tr.getEvsMaster()!=null){
		                            	lEvMaster = evsEmp_tr.getEvsMaster();
		                            	int lEvMasterSize = lEvMaster.size();
		                              	for(int j = 0 ; j < lEvMasterSize ; j++){
		                                	EvsMaster m = (EvsMaster)lEvMaster.get(j);
		                                	if(m.getEvProcessID().equals(evsProcess_tr.getEvProcessID())){
		                                		isEquals=true;
		                       					evMaster = m.getEvMaster1();
		                       					evMasterName = m.getEvMasterName() ;
											}
		                   				}
		                   				if(isEquals){
		                   				%>
		                       				<td nowrap="nowrap" align="center"> 
		                       				    </br><%= StringUtil.checkNull(evMaster) %>(<%= StringUtil.checkNull(evMasterName) %>)
		                       				</td>
		                   				<%		
										}else{
										         
												out.print("<td >" + evMaster +  " '</td>");
										}
		                   			}else{
		                   				out.print("<td >" + evMasterName +   " '</td>");	
		                   			}
								}
							}
						%>
						</tr>
					<%
					   		}
						}
					%>
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
	<% 
	if(lEvsProcess!=null){
		int lEvsProcessSize = lEvsProcess.size();
		for(int k=0;k<lEvsProcessSize;k++){
			EvsProcess evsProcess_tr=(EvsProcess)lEvsProcess.get(k);
%>
<input name="evProcessId<%=k%>" type="hidden" value="<%=StringUtil.checkNull(evsProcess_tr.getEvProcessID())%>">
<input type="hidden" name="check" value="<%=k%>">
<%
		}
	}
%>
	<input type="hidden" name="flag" value="">
	<input type="hidden" name="empCount" value="<%=lEvsEmp.size()%>">
	 <input type="hidden" name="ck_all" value="0" />
	</form>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>

<script language="JavaScript" type="text/javascript" src="">
function selectall()
{
	var selected =document.evs0105.ck_all.value == "0" ? true : false;
	
  try{
	  var length=<%= lEvsEmp.size() %>;
      	   	
	  if ( length== 0 ){
	    return;
	  }
	  if (length ==1 )
	  {
	    document.evs0105.empid0.checked = selected ;
	  }
	
	  if (length>1)
	  {
	  	for (var i = 0; i < length; i++)
	    {
	      document.all('empid'+i).checked = selected ;
	    }
	  }
	}catch(e){alert(e);}

	document.evs0105.ck_all.value = selected ? "1" : "0";
}
</script>

