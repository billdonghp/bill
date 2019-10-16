<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
	<%@ include file="/inc/taglibs.jsp"%>
	<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 统计查看 > 员工状态</title>
</head>

<!-- EXT 所需部分 -->
<link rel="stylesheet" type="text/css" href="../css/ext-all.css" />
<!-- LIBS -->
<script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<script type="text/javascript" src="../js/ext/ext-all.js"></script>
<body>
<a name="top"></a>

<%	
String adminId="";
if(admin!=null){
	adminId=admin.getAdminID();
}
String evDeptId="";
String evPeriodId="";
String evTypeId="";
String evProcessId="";

evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evProcessId=request.getParameter("evProcessId")!=null?request.getParameter("evProcessId"):evProcessId;


EvsPeriod evsP=new EvsPeriod();
if(evPeriodId.equals("")){
	try{
		evPeriodId=evsP.getCurrentEvPeriod();
	}catch(Exception e){}	
}
//List lEvsDept=null;
List lEvsPeriod=null;
List lEvsType=null;
List lEvsEmp=null;
EvsEmp evsEmp=new EvsEmp();
EvsMaster evsMaster=new EvsMaster();
try{
	//lEvsDept=EvsEmp.getEvEmpDeptList(evPeriodId);
	lEvsPeriod=EvsEmp.getEvEmpPeriodList();
	lEvsType=EvsEmp.getEvEmpTypeList();
	
	lEvsEmp=evsEmp.getEvEmpsByDeptPeriodType(evDeptId,evPeriodId,evTypeId,evProcessId);
	//lEvsEmp=evsMaster.getEvEmpsByMasterPeriod(evPeriodId,adminId,evDeptId,evTypeId);

	
}catch(Exception e){}

//评价流程列表
Vector vEvProcess=new Vector();
try{
vEvProcess=SysCodeParser.getCode("EVSPROCESS");
}catch(Exception e){}
int vEvProcessSize=vEvProcess.size();
%>
<form action="/evs/evs0305.jsp?menu_code=evs0305" method="POST" name="evs0305">
		<input type="hidden" name="ck_all" value="0" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
		<%@ include file="../evs/inc/evs0305toolbar_v.jsp"%>
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

		
		<td class="info_title_01" >工号/姓名:</td>
		<td class="info_content_00">
		<input type="text"
			name="GoEmp" id="GoEmp" size="6"> &nbsp;<a
			onClick="location.href='#'+document.all.GoEmp.value;"
			style="cursor:hand">搜索</a>
                    
		</td>
		<td class="info_title_01" >评价部门</td>
		<td class="info_content_00">
		<ait:selEvDept name="evDeptId" evPeriodId="<%=evPeriodId %>" all=" " onChange="evs0305.submit();" selectEvDeptId="<%= evDeptId %>" />
		</td>
		<td class="info_title_01" >评价类型</td> 
				<td class="info_content_00"><select id="evTypeId" name="evTypeId" onChange="evs0305.submit();">
					<option value="">评价类型</option>
			<%
                            if(lEvsType!=null){
                            	int lEvsTypeSize=lEvsType.size();
                            	for(int i=0;i<lEvsTypeSize;i++){
                            		EvsType evsType_sel=(EvsType)lEvsType.get(i);
			%>
					<option value="<%=evsType_sel.getEvTypeID()%>"
						<%if (evTypeId.equals(evsType_sel.getEvTypeID())) {%>
						selected="selected" <%}%>>
						<%=evsType_sel.getEvTypeName()%>
					</option>
			<%}}%>
		</select>
		</td>		
			</tr>
			<tr>
				<td class="info_title_01" >评价区间</td>
				<td class="info_content_00"><select id = "evPeriodId" name="evPeriodId" onChange="evs0305.submit();">

			<%
					if(lEvsPeriod!=null){
						int lEvsPeriodSize=lEvsPeriod.size();
						for(int i=0;i<lEvsPeriodSize;i++){
							EvsPeriod evsPeriod_sel=(EvsPeriod)lEvsPeriod.get(i);
			%>
					<option value="<%=evsPeriod_sel.getEvPeriodID()%>"
						<%if (evPeriodId.equals(evsPeriod_sel.getEvPeriodID())) {%> selected="selected" <%}%>>
						<%=evsPeriod_sel.getEvPeriodName()%>
					</option>
			<%}}%>
				</select></td>
				<td class="info_title_01" >评价流程</td> 
				<td class="info_content_00"><select id="evProcessId" name="evProcessId"  onChange="evs0305.submit();">
					<option value="">评价流程</option>
			<%
								for(int k=0;k<vEvProcessSize;k++){
								HashMap codemap_opt=(HashMap)vEvProcess.get(k);
			%>
					<option value="<%=codemap_opt.get("code")%>"
						<%if(evProcessId.equals((String)codemap_opt.get("code"))){out.print(" selected ");}%>>
						<%=codemap_opt.get("name")%>
					</option>
			<%}%>
				</select></td>
		<td class="info_content_00" colspan="2">
		<a target="_blank" href="/evs/excel/evs0305_excel.jsp?evDeptId=<%=evDeptId%>&evPeriodId=<%=evPeriodId%>&evProcessId=<%=evProcessId%>&evTypeId=<%=evTypeId%>">
		<img src="/images/ev/p_excel.gif"
					width="62" height="21" border="0" align="absmiddle" title="导出EXCEL"> </a>
		</td>
		<input type="hidden" name="lEvsEmpSize" value="<%=lEvsEmp.size()%>">

	</tr>
				
			</table>
		  </td>
		</tr>
		</table>
		<!-- display 3 level menu -->
		<%@ include file="inc/evs_nav.jsp"%>
		
		<!-- display content -->
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">进行情况</td>
			</tr>
		</table>
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" bgcolor="#F5F5F5">
				<td height="30" class="info_title_01"><a href="#"onclick="checkAll();" style="color:red;">全选</a></td>
				<td width="8%" class="info_title_01">员工号</td>
				<td width="10%" class="info_title_01">姓名</td>
				<td width="30%" class="info_title_01">部门</td> 
				<td width="8%" class="info_title_01">评价等级</td>
				<td width="10%" class="info_title_01">当前状态</td>
				<td width="20%" class="info_title_01">步骤期间</td>
			</tr>
			<%	
				if(lEvsEmp!=null){
					int lEvsEmpSize=lEvsEmp.size();
	    			for(int i=0;i<lEvsEmpSize;i++){
	        			EvsEmp evsEmp_tr=(EvsEmp)lEvsEmp.get(i);
	        %>

			<tr>
				<!--  onClick="HighLightTR('#F0F1F4','black','<%=evsEmp_tr.getEvEmpID()%>','<%=evsEmp_tr.getEvPeriodID()%>','<%=menu_code%>')"-->
				<input type="hidden" name="evEmpId" value="<%=StringUtil.checkNull(evsEmp_tr.getEvEmpID())%>">
				<input type="hidden" name="evEvCurrentProcessID_<%=i%>" value="<%=StringUtil.checkNull(evsEmp_tr.getEvCurrentProcessID())%>">
				<td height="30" class="info_content_01"><input type="checkbox" name="ck_<%=i%>" value="<%=evsEmp_tr.getEvEmpID()%>"/> </td>
				<td align="center" class="info_content_01">
					<%=evsEmp_tr.getEvEmpID2()%>&nbsp;
					<a href="#top">&#X0018;</a>
					<a name="<%=evsEmp_tr.getEvEmpID2()%>"></a>
				</td>
				<td align="center" class="info_content_01"><a href="#top">&#X0018;</a><a
					name="<%=evsEmp_tr.getEvEmpName()%>"></a><%=evsEmp_tr.getEvEmpName()%>&nbsp;</td>
				<td align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp_tr.getEvDeptName())%>&nbsp;</td>
				<td align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp_tr.getEvGradeName())%>&nbsp;</td>
				<td align="center" class="info_content_01">
				<%
				  String evProcesId = evsEmp_tr.getEvCurrentProcessID();
                  List lEvsProcess = null;
                  
                  EvsProcess evsProcess=new EvsProcess(evsEmp_tr.getEvPeriodID(),evsEmp_tr.getEvTypeID(),evsEmp_tr.getEvCurrentProcessID());
                  lEvsProcess=evsProcess.getProcessByTypePeriod(); 
                  
                  try{
                    	evsProcess.getProcessByTypePeriodProcess();
                    }catch(Exception e){ e.printStackTrace(); }
                  
                %>
                <select name="evProcessId_<%=evsEmp_tr.getEvEmpID()%>"  id="evProcessId" onchange="batchEvEmpProcessId(this);">
                	<%
                	if(lEvsProcess!=null){
                		int lEvsProcssSize=lEvsProcess.size();
                		for(int j = 0; j < lEvsProcssSize ; j++){
                			EvsProcess evsPro=(EvsProcess)lEvsProcess.get(j);
                			
                			%>
                			<option value="<%=evsPro.getEvProcessID()%>" <%if(evProcesId.equals(evsPro.getEvProcessID())){out.print("  selected ");}%>><%=evsPro.getEvProcessName()%></opiton>
                			<%
                		}
                	}
                	%>
                </select>
                </td>
				<td align="center" class="info_content_01"><%=StringUtil.checkNull(evsProcess.getEvProcessSDate())%>～ <%=StringUtil.checkNull(evsProcess.getEvProcessEDate())%></td>
			</tr>
			<% }}%>
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
		</form>
</body>
</html>
<script language="JavaScript" type="text/javascript" src="">
	//查看个人分数明细
	function showMarkDetail(evEmpId , evPeriodId)
	{
		var url = '/ajaxControlServlet';
		var pars = 'operation=getEvItemMarkCmd&evEmpId='+ evEmpId + "&evPeriodId=" + evPeriodId;
		
		new Ajax.Request(url, {
					method: 'post',
					parameters : pars,
					onSuccess : function(XmlHttpRequest) {
						//alert(XmlHttpRequest.responseText);
							var html = "<div>";
								html += XmlHttpRequest.responseText;
								html +="</div>";
							var	editDlg = new Ext.Window({
										modal: true
										 , width: 300
										  , height: 'auto'
										 , shadow: true
										 , closable: true
										  , layout : 'fit'
										 , html : html
										 , title : '评价分数信息'
									});
							editDlg.show();
					}
				} );
	}


	function showEvItemProcessMark(evEmpID,evPeriodId,evTypeId) {
		var theUrl = "../evs/evsItemProcessMark.jsp?"+"evEmpID="+evEmpID+"&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;
	    var name = "newWin";
	    var features = "status=no,menubar=no,resizable=yes,scrollbars=yes,width=500,height=350";
    	window.open(theUrl,name,features);
	}

	function Modify(){
		//var evDeptId = document.getElementById("evDeptId").value;
		//var evTypeId = document.getElementById("evTypeId").value;
		//var evProcessId = document.getElementById("evProcessId").value;
		//var evPeriodId = document.getElementById("evPeriodId").value;

	//	document.evs0305.action='/evs/<%=menu_code%>_t.jsp';
		document.evs0305.action='/evs/evs0305_t.jsp?menu_code=evs0305';
		//alert('aaa'||document.evs0305.action);
        document.evs0305.submit();
	}
	
	function Delete(){
	    var flag = false ;
		var size = <%= lEvsEmp != null ? lEvsEmp.size() : 0 %> ;
      	     	
      	for (var i=0; i<size; i++){
    		if (document.evs0305("ck_" + i).checked){
    		    flag = true;
				///alert(document.evs0305("ck_" + i).value);
				if(document.evs0305("evEvCurrentProcessID_" + i).value == 'EVPROCESS010'){
					alert("评价完毕信息不能删除");
					return ;
				}
    		}
      	}
      	if (!flag)
		{
			alert("请选择要删除的数据!") ;
			return ;
		}else{
		  if(confirm('确定删除评价信息？')){
		  	document.evs0305.action='/evs/evs0305_d.jsp?menu_code=evs0305';
           document.evs0305.submit();
		  }
		}
      		
		
	}

	function checkAll()
    {
        var selected = document.evs0305.ck_all.value == "0" ? true : false;
        var size = <%= lEvsEmp != null ? lEvsEmp.size() : 0 %> ;
      	for (var i=0; i<size; i++){
    		document.evs0305("ck_" + i).checked = selected ;
    	
    	}
        document.evs0305.ck_all.value = selected ? "1" : "0";
    }

    function batchEvEmpProcessId(evEmpProcessId)
    {
    	var size = <%= lEvsEmp != null ? lEvsEmp.size() : 0 %> ;
      	for (var i=0; i<size; i++){
    		if (document.evs0305("ck_" + i).checked){
				var selectSize = document.evs0305("evProcessId_" + document.evs0305("ck_" + i).value).length ;
				for (var j=0; j<selectSize; j++){
					if (document.evs0305("evProcessId_" + document.evs0305("ck_" + i).value).options[j].value == evEmpProcessId.value){
						document.evs0305("evProcessId_" + document.evs0305("ck_" + i).value).options[j].selected = true ;
						break;
					}
				}
    		}
      	}
    }
	
</script>