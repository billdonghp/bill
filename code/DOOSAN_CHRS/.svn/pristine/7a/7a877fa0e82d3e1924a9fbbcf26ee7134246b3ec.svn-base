<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.Evsupcode"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
	<%@ include file="/inc/taglibs.jsp"%>
<script language="JavaScript" type="text/javascript" src="">
  function showEvItemProcessMark(evEmpID,evPeriodId,evTypeId) {
    var theUrl = "../evs/evsItemProcessMark.jsp?"+"evEmpID="+evEmpID+"&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;
    var name = "newWin";
    var features = "status=no,menubar=no,resizable=yes,scrollbars=yes,width=500,height=350";
    window.open(theUrl,name,features);
  }
 
function exportEXL()
{
   evDeptId=  document.getElementById("evDeptId").value
   evTypeId= document.getElementById("evTypeId").value;
   evPeriodId= document.getElementById("evPeriodId").value;
    window.location="/evs/evs0302_report.jsp?evDeptId="+evDeptId+"&evTypeId="+evTypeId+"&evPeriodId="+evPeriodId;
}
  </script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 统计查看 > 成绩查询</title>
</head>
<body>
<a name="top"></a>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%
  String empID = "";
  if (admin != null)
    empID = admin.getAdminID();
  else
    throw new JspException("无效访问");


%>
<%
String period = "";
String adminId="";
if(admin!=null){
	adminId=admin.getAdminID();
}
String evDeptId="";
String evPeriodId="";
String evTypeId="";
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
period=request.getParameter("period")!=null?request.getParameter("period"):evPeriodId;

EvsPeriod evsP=new EvsPeriod();
if(evPeriodId.equals("")){
	try{
		evPeriodId=evsP.getCurrentEvPeriod();
	}catch(Exception e){}
}
//List lEvsDept=null;
List lEvsPeriod=null;
List lEvsType=null;
List lEvsEmp2=null;
int count=0;
List lEvsUpCode=null;

String Evaluation1="";
String Evaluation2="";
Hashtable ht = new Hashtable();
EvsPeriod evsPeriod = new EvsPeriod();
EvsEmp evsEmp=new EvsEmp();
EvsMaster evsMaster=new EvsMaster();
try{
	//lEvsDept=EvsEmp.getEvEmpDeptList(evPeriodId);
    //lEvsPeriod=EvsEmp.getEvEmpPeriodList();
	lEvsType=EvsEmp.getEvEmpTypeList();
	//lEvsEmp=evsEmp.getEvEmpsByDeptPeriodType(evDeptId,evPeriodId,evTypeId);
	//lEvsEmp=evsMaster.getAllEvEmpsByMasterPeriod(evPeriodId,adminId,evDeptId,evTypeId);//小的
	lEvsEmp2=evsMaster.getAllEvEmpsByMasterPeriod2(evPeriodId,adminId,evDeptId,evTypeId);//大的
	//lEvsUpCode = evsupcode.getEvSetUpCodeList();
	//Evaluation1 = evsMaster.getEvaluation1("2428");
		
	//System.out.println(Evaluation1);
}catch(Exception e){
	e.printStackTrace();
}
%>
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
				<%
			  
			  lEvsPeriod=evsPeriod.getEvsPeriodByYear("");
			  int grant=0;
			  grant=evsPeriod.getEvsPeriodGrant(empID);
			  
			 
			%>
					<form action="/evs/evs0302.jsp?menu_code=evs0302" method="Post" name="evs0302">
					<tr>
						<td class="info_title_01" >评价部门</td>
						<td class="info_content_00">
							<ait:selEvDept name="evDeptId" evPeriodId="<%=evPeriodId %>" all=" " onChange="evs0302.submit();" selectEvDeptId="<%= evDeptId %>" />
						</td>
						<td class="info_title_01" >评价年度</td>
						<td class="info_content_00">
					  		<select name="Period" onChange="ev0302.submit();">
					            <option value="">评价年度</option>
					 			 <%
								  if(lEvsPeriod!=null){
								    int lEvsPeriodSize=lEvsPeriod.size();
								    for(int i=0;i<lEvsPeriodSize;i++){
								      EvsPeriod eriod_t=(EvsPeriod)lEvsPeriod.get(i);
								%>
					                        <option value="<%=eriod_t.getEvYear()%>" <%if (eriod_t.getEvYear().equals(period)) {%> selected="selected"<%}%>><%=eriod_t.getEvYear()%></option>
								<%
								    }
								  }
								%>
					                    </select>                  
							</td>
						<td class="info_title_01" >工号/姓名:</td>
						<td class="info_content_00">
						<input type="text"
							name="GoEmp" id="GoEmp" size="6"> &nbsp;<a
							onClick="location.href='#'+document.all.GoEmp.value;"
							style="cursor:hand">搜索</a>
				                    
						</td>
						<td class="info_content_00">
						<img src="/images/ev/p_excel.gif" width="62" height="21" border="0"
									align="absmiddle" title="导出EXCEL"
									onclick="exportEXL();">
						</td>
					</tr>
					</form>
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
				<td align="left" class="title1" colspan="10">评价结果</td>
			</tr>
		</table>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">

	<tr>
		<td class="line">
<script language="JavaScript">
<!--
document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-158) + ';\">')
//-->
</script>
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" bgcolor="#F5F5F5">
				<td width="5%" height="30" class="info_title_01">
				序号
				</td>
				<td width="6%" class="info_title_01">
				员工姓名
				</td>
				<td width="8%" class="info_title_01">
				评价类型
				</td>
				<td width="15%" class="info_title_01">
				部门名称
				</td>
				<td width="15%" class="info_title_01">
				当前状态
				</td>
				
				<td width="15%" class="info_title_01">
				工种名称
				</td>
				<td width="15%" class="info_title_01">
				技能类型
				</td>
				<td width="15%" class="info_title_01">
				技能Line
				</td>
				<td width="15%" class="info_title_01">
				机种名称
				</td>
				<td width="15%" class="info_title_01">
				工序名称
				</td>
				<td width="15%" class="info_title_01">
				作业内容
				</td>
				<!--  <td width="15%" class="info_title_01">
				作业类型
				</td>-->
				<td width="15%" class="info_title_01">
				职业资格
				</td>
				<td width="15%" class="info_title_01">
				技能积分
				</td>
				<td width="15%" class="info_title_01">
				熟练度
				</td>
				<td width="15%" class="info_title_01">
				设备保全
				</td>
				<td width="15%" class="info_title_01">
				作业基准书
				</td>
				<td width="15%" class="info_title_01">
				作业品质
				</td>
				<td width="15%" class="info_title_01">
				标准动作
				</td>
				<td width="15%" class="info_title_01">
				综合得分 
				</td>
				<td width="15%" class="info_title_01">
				技能等级
				</td>
				<td width="15%" class="info_title_01">
				主要问题点
				</td>
				<td width="15%" class="info_title_01">
				培训目标
				</td>
			</tr>
			<%	
				if(lEvsEmp2!=null ){
					int lEvsEmp2Size=lEvsEmp2.size();
	    			for(int i=0;i<lEvsEmp2Size;i++){
	        			EvsEmp evsEmp2_tr=(EvsEmp)lEvsEmp2.get(i);
	        	 count = evsMaster.getAllEvEmpsByMasterPeriod3(evsEmp2_tr.getEvEmpID());
	        	    
	        %> 
			<a name="<%=evsEmp2_tr.getEvEmpID()%>">
			<tr >
				<td height="30" class="tablecontent" >
				<div align="center" class="info_content_01"><%=i+1%>
				</td>
				<td>
				<div align="center" class="info_content_01"><%=evsEmp2_tr.getEvEmpName()%></div>
				</td>
				<td>
				<div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getEvTypeName())%>&nbsp;</div>
				
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getEvDeptName())%>&nbsp;</div>
				</td>
				 <td align="center"><%=evsEmp2_tr.getEvCurrentProcessName()%></td>
				
				 <td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getCRAFT())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getSKILLTYPE())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getLINE())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getAIRCRAFT())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getPROCESS())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getJOBCONTENT())%>&nbsp;</div>
				</td>
				<!-- 
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getTYPEOPERATION())%>&nbsp;</div>
				</td>-->
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getQUALIFICATION())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getSKILLSCORE())%>&nbsp;</div>
				</td>
				
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getPROFICIENCY())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getSHEOPERCYQ())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getOPERATIONCOM())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getQUALITYOFWORK())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getSTANDARDACTION())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getCOMPOSITE())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getSKILLLEVEL())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getREMARK())%>&nbsp;</div>
				</td>
				<td>
				   <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getPURPOSE())%>&nbsp;</div>
				</td>
				
			</tr>
			</a>
			<%
					}
				}
			%>
		</table></div>
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

</body>
</html>


