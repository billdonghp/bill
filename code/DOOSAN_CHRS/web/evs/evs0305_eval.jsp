<%@ page contentType="text/html; charset=UTF-8" language="java"	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.Evsupcode"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.evs.EvsCraft"%>
<%@ page import="java.util.List"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"	scope="session" />
	<%@ include file="/inc/taglibs.jsp"%>
<script language="JavaScript" type="text/javascript" >
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
function showMemo(empid) {

	   //alert(empid)
       document.form1.action="/evsControlServlet?operation=retrieveEvaluate0302&EmpID="+ empid;
 	    document.form1.submit();
	   	    
} 

function Search() {
	//document.evs0302.action="/arControlServlet?operation=ar_pagecontrol&page=detail_v&menu_code=ar0201&flag=search";
	 document.evs0302.submit();
}
function ImportForExcel() {
	document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=detail_v&menu_code=ar0201&flag=report";
	document.form1.submit();
}
function checkAll()
{	
	var selected = document.evs0302.ck_all.value == "0" ? true : false;	
	var selectarg = document.getElementsByName("selectC");	
	for(var i=0;i<selectarg.length;i++)
	{    
	    selectarg[i].checked = selected;     
	 
	}
	document.evs0302.ck_all.value = selected ? "1" : "0";
}
function sendMailPart(){//发送
	
    document.evs0302.action="/evsControlServlet?operation=paEmaiSendCmdS&menu_code=evs0302&sendType=part" ; 
   
    var selectC = document.getElementsByName("selectC") ;
	var size = selectC.length ;
	var selectCnt = 0 ;
	
	for(var i = 0 ; i < size ; i ++ )
	{
		if(selectC[i].checked)
		{
			selectCnt++ ;
			break ;
		}
	}

	if(selectCnt == 0)
	{
		alert("请选择要发送邮件 的数据!!!") ;
		return ;	
	}
   
    if (confirm("是否按当前选择的人，发送邮件!!!")) {
		//beforeSubmit();
		document.evs0302.submit();
		//document.evs0302.fireSubmit();
		//afterSubmit();
	}
}
</script>
  <script src="js/echarts.min.js"></script>
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
EvsCraft evscraft = new EvsCraft();  

String adminId="";
if(admin!=null){
	adminId=admin.getAdminID();
}


String craft = "";
String zyzgdj = "";

craft=request.getParameter("craft")!=null?request.getParameter("craft"):craft;
zyzgdj=request.getParameter("zyzgdj")!=null?request.getParameter("zyzgdj"):zyzgdj;

//List lEvsDept=null;
List lEvsPeriod=null;
List lEvsType=null;
List lEvsEmp2=null;
int count=0;
List lEvsUpCode=null;

String Evaluation1="";
String Evaluation2="";

EvsPeriod evsPeriod = new EvsPeriod();
EvsEmp evsEmp=new EvsEmp();
EvsMaster evsMaster=new EvsMaster();
try{
	
	lEvsType=EvsEmp.getEvEmpTypeList();
		 
	zyzgdj = URLDecoder.decode(zyzgdj,"UTF-8");
	craft = URLDecoder.decode(craft,"UTF-8");
	
	if(adminId!=null&&(adminId.equals("1464498")||adminId.equals("1464086")||adminId.equals("9999901")
			||adminId.equals("1466731")||adminId.equals("12884220")||adminId.equals("4529845"))){
		lEvsEmp2=evsMaster.getAllEvEmpsByMasterPeriod0305(craft,zyzgdj);//大的 
		}else{
			lEvsEmp2=evsMaster.getAllEvEmpsByMasterPeriod0305admin(craft,zyzgdj,adminId);//大的 
		}
	
	
	
	System.out.println(zyzgdj);
}catch(Exception e){
	e.printStackTrace();
}
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->
		<br>
		
		<!-- display 3 level menu -->

		
		<!-- display content -->
		<br>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">评价结果</td>
			</tr>
			
		</table>
<table width="98%" border="0" align="center" cellpadding="0"	cellspacing="1">
	<tr>
		<td colspan=5 ehr class="line">
		<form action="/evs/evs0305_eval.jsp?menu_code=evs0305" method="Post" name="form1">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" bgcolor="#F5F5F5">
				<td width="5%" height="30" class="info_title_01">	序号	</td>
				<td width="6%" class="info_title_01">职号</td>
				<td width="6%" class="info_title_01">姓名</td>				
				<td width="8%" class="info_title_01">部门</td>
				<td width="12%" class="info_title_01">部门全称</td>				
				<td width="8%" class="info_title_01">工种</td>	
				<td width="10%" class="info_title_01">认证工序数量</td>
				<td width="10%" class="info_title_01">职业资格</td>
				<td width="6%" class="info_title_01">积分</td>
				<td width="6%" class="info_title_01">详情</td>
			</tr>
			<%	
				if(lEvsEmp2!=null ){
					int lEvsEmp2Size=lEvsEmp2.size();
	    			for(int i=0;i<lEvsEmp2Size;i++){
	        			EvsEmp evsEmp2_tr=(EvsEmp)lEvsEmp2.get(i);
	        	 count = evsMaster.getAllEvEmpsByMasterPeriod3(evsEmp2_tr.getEvEmpID());
	        	    
	        %> 
			<a name="<%=evsEmp2_tr.getEvEmpID()%>">
			<tr>
			
				<td height="30" class="tablecontent" ><div align="center" class="info_content_01"><%=i+1%>	</div></td>
				<td><div align="center" class="info_content_01"><%=evsEmp2_tr.getEvEmpID()%>&nbsp;</div></td>
				<td><div align="center" class="info_content_01"><%=evsEmp2_tr.getEvEmpName()%>&nbsp;</div></td>
				 <td align="center"><%=evsEmp2_tr.getEvDeptName()%></td>				
				 <td> <div align="center" class="info_content_01"><%=evsEmp2_tr.getEvGradeName()%>&nbsp;</div></td>
				<td> <div align="center" class="info_content_01"><%=evsEmp2_tr.getCRAFT()%>&nbsp;</div></td>
				 <td> <div align="center" class="info_content_01"><%=evsEmp2_tr.getSUMJCCOUNT()%>&nbsp;</div></td>
				<td> <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getZYZGDJLEVEL())%>&nbsp;</div></td>
				<td> <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getSUMSCORE())%>&nbsp;</div></td>			
				<td><a href="#" onClick="showMemo('<%=evsEmp2_tr.getEVS_EMPID()%>')">详情</a></td>
			  </tr>
			</a>
			<%
					}
				}
			%>
		</table></div>
		</td>
	</tr>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"	height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
	<tr>		
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
