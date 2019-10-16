<%@ page contentType="text/html; charset=UTF-8" language="java"	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.Evsupcode"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.evs.EvsCraft"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"	scope="session" />
	<%@ include file="/inc/taglibs.jsp"%>
	<%@ include file="../inc/meta.jsp"%>
	<!-- LIBS -->
<script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<script type="text/javascript" src="../js/ext/ext-all.js"></script>
<script language="JavaScript" type="text/javascript" >
  function showEvItemProcessMark(evEmpID,evPeriodId,evTypeId) {
    var theUrl = "../evs/evsItemProcessMark.jsp?"+"evEmpID="+evEmpID+"&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;
    var name = "newWin";
    var features = "status=no,menubar=no,resizable=yes,scrollbars=yes,width=500,height=350";
    window.open(theUrl,name,features);
  }
 
function exportEXL()
{
	//if(document.form1.DEPTID.value=="" && document.form1.key.value=="" && document.form1.R_START_TIME.value=="" && document.form1.R_END_TIME.value=="") {
		//"请选择导出的Excel条件范围."
	//		alert('<ait:message  messageID="alert.att.maintenance.visit.select_criteria" module="ar"/>');
	//		return;
	//	}
	document.evs0302.action="/evsControlServlet?operation=exportAttRecordReport&menu_code=${param.menu_code}";
	 document.evs0302.submit();
}
function showMemo(empid) {

	  // alert(empid)
       document.evs0302.action="/evsControlServlet?operation=retrieveEvaluate0302&EmpID="+ empid;
 	    document.evs0302.submit();
	   	    
} 

function Search() {
	document.evs0302.action="";
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

 String adminid ="";
EvsCraft evscraft = new EvsCraft();  

Calendar c = Calendar.getInstance();
String year = new Integer(c.get(Calendar.YEAR)).toString();
String evDeptId="";
String evPeriodId="";
String evTypeId="";
String GoEmp ="";
String period = "";
String periodf = "";
String periodt = "";
String craft = "";
String line = "";
String jobcontent = "";
String zyzgdj = "";
String gxjndj = "";
String sumstoref = "";
String sumstoret = "";

String deptke = "";
String deptzhi = "";
String deptzu = "";
String bumenk ="1";

evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
period=request.getParameter("period")!=null?request.getParameter("period"):period;
periodf=request.getParameter("periodf")!=null?request.getParameter("periodf"):periodf;
periodt=request.getParameter("periodt")!=null?request.getParameter("periodt"):periodf;
GoEmp=request.getParameter("GoEmp")!=null?request.getParameter("GoEmp"):GoEmp;
craft=request.getParameter("craft")!=null?request.getParameter("craft"):craft;
line=request.getParameter("line")!=null?request.getParameter("line"):line;
jobcontent=request.getParameter("jobcontent")!=null?request.getParameter("jobcontent"):jobcontent;
zyzgdj=request.getParameter("zyzgdj")!=null?request.getParameter("zyzgdj"):zyzgdj;
gxjndj=request.getParameter("gxjndj")!=null?request.getParameter("gxjndj"):gxjndj;
sumstoref=request.getParameter("sumstoref")!=null?request.getParameter("sumstoref"):sumstoref;
sumstoret=request.getParameter("sumstoret")!=null?request.getParameter("sumstoret"):sumstoret;

deptke=request.getParameter("deptke")!=null?request.getParameter("deptke"):deptke;
deptzhi=request.getParameter("deptzhi")!=null?request.getParameter("deptzhi"):deptzhi;
deptzu=request.getParameter("deptzu")!=null?request.getParameter("deptzu"):deptzu;


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
	
	lEvsType=EvsEmp.getEvEmpTypeList();
	
	System.out.println(evDeptId);
	
	
	//if(evDeptId!=null&&!evDeptId.equals("")){
		if(admin.getAdminID()!=null&&(admin.getAdminID().equals("1464498")||admin.getAdminID().equals("1464086")||admin.getAdminID().equals("9999901")
				||admin.getAdminID().equals("1466731")||admin.getAdminID().equals("12884220")||admin.getAdminID().equals("4529845")))
			{
			 if(evDeptId!=null&&!evDeptId.equals("")){
			  lEvsEmp2=evsMaster.getAllEvEmpsByMasterPeriod2(evPeriodId,period,GoEmp,evDeptId,craft,line,jobcontent,zyzgdj,gxjndj,sumstoref,sumstoret,deptke,deptzhi,deptzu);//大的 
			 }
			  bumenk ="0";
			}
			else{
				adminid = admin.getAdminID();
				lEvsEmp2=evsMaster.getAllEvEmpsByMasterPeriod2admin(empID,evPeriodId,period,GoEmp,evDeptId,craft,line,jobcontent,zyzgdj,gxjndj,sumstoref,sumstoret,deptke,deptzhi,deptzu);//大的 					
			}
	//}
	
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
					<input name="ck_all" value="0" type="hidden"/> 
					<tr>
						<td class="info_title_01" >部门</td>						
					   <td class="info_content_00">
							<ait:selEvDept name="evDeptId" evPeriodId="<%=adminid %>" all=" "   selectEvDeptId="<%= evDeptId %>" />
						</td>
						<!--<td class="info_title_01" >课:</td>
						<td class="info_content_00">
						<input type="text"	name="deptke" id="deptke" value="<%=deptke%>" size="6"> 				                    
						</td>
						<td class="info_title_01" >职:</td>
						<td class="info_content_00">
						<input type="text"	name="deptzhi" id="deptzhi" value="<%=deptzhi%>" size="6"> 				                    
						</td>
						<td class="info_title_01" >组:</td>
						<td class="info_content_00">
						<input type="text"	name="deptzu" id="deptzu" value="<%=deptzu%>" size="6"> 				                    
						</td>
						
						--><td class="info_title_01" >工号/姓名:</td>
						<td class="info_content_00">
						<input type="text"	name="GoEmp" id="GoEmp" value="<%=GoEmp%>" size="6"> 				                    
						</td>
						<td class="info_title_01" width="80">职业资格等级</td> 
					<td height="2" align="right" class="info_content_00">
						<input type="hidden"	name="codeflag" value="CT">
					<select name="zyzgdj" id = "zyzgdj"	>
						<option value='' <%if(craft.equals("")){out.print(" selected ");}%>>请选择</option>
						<%
					List lEvzyzgdjname = evscraft.getEvszyzgdjList(craft); 
					if(lEvzyzgdjname!=null){
					int evCodeSize=lEvzyzgdjname.size();
					
					for(int i=0;i<evCodeSize;i++){
						String codename3="";
						codename3=(String)lEvzyzgdjname.get(i);
					%>
						<option value=<%=codename3%>
							<%if(zyzgdj.equals(codename3)){out.print(" selected ");}%>><%=codename3%></option>
						<%}}%>
					</select>
					</td>
					<td class="info_title_01" >年度</td>
						<td class="info_content_00" colspan="3">
					  		<select name="evPeriodId" >
					            <option value="">请选择</option>
					 			 <%
								  if(lEvsPeriod!=null){
								    int lEvsPeriodSize=lEvsPeriod.size();
								    for(int i=0;i<lEvsPeriodSize;i++){
								      EvsPeriod eriod_t=(EvsPeriod)lEvsPeriod.get(i);
								%>
					                        <option value="<%=eriod_t.getEvYear()%>" <%if (eriod_t.getEvYear().equals(evPeriodId)) {%> selected="selected"<%}%>><%=eriod_t.getEvYear()%></option>
								<%
								    }
								  }
								%>
					                    </select>          
					                    至        
							
					  		<select name="period" >
					            <option value="">请选择</option>
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
					</tr>
					<tr>
					<td class="info_title_01" >累积积分:</td>
						<td class="info_content_00">
						<input type="text"	name="sumstoref" id="sumstoref" value="<%=sumstoref%>" size="6">至
						 <input type="text"	name="sumstoret" id="sumstoret" value="<%=sumstoret%>" size="6">
						</td>
						<td class="info_title_01" width="80">工种</td> 
					<td height="2" align="right" class="info_content_00">
						<input type="hidden"	name="codeflag" value="CT">
					<select name="craft" id = "craft"	onchange="Search()">
						<option value='' <%if(craft.equals("")){out.print(" selected ");}%>>请选择</option>
						<%
					List lEvcodename = evscraft.getEvscodeList();
					if(lEvcodename!=null){
					int evCodeSize=lEvcodename.size();
					
					for(int i=0;i<evCodeSize;i++){
						String codename="";
						codename=(String)lEvcodename.get(i);
					%>
						<option value=<%=codename%>
							<%if(craft.equals(codename)){out.print(" selected ");}%>><%=codename%></option>
						<%}}%>
					</select>
					</td>
					
					<td class="info_title_01" width="80">岗位</td> 
					<td height="2" align="right" class="info_content_00">					
					<select name="line"	onchange="Search();">
						<option value='' <%if(line.equals("")){out.print(" selected ");}%>>请选择</option>
						<%
					List lEvLinecodename = evscraft.getEvscodeLineListb(craft); 
					if(lEvLinecodename!=null){
					int evCodeSize=lEvLinecodename.size();
					
					for(int i=0;i<evCodeSize;i++){
						String codename1="";
						codename1=(String)lEvLinecodename.get(i);
					%>
						<option value=<%=codename1%>
							<%if(line.equals(codename1)){out.print(" selected ");}%>><%=codename1%></option>
						<%}}%>
					</select>
					</td>
						<td class="info_title_01" width="80">工序</td> 
					<td height="2" align="right" class="info_content_00">					
					<select name="jobcontent"	>
						<option value='' <%if(line.equals("")){out.print(" selected ");}%>>请选择</option>
						<%
					List lEvjccodename = evscraft.getEvscodeJcListb(line);  
					if(lEvjccodename!=null){
					int evCodeSize=lEvjccodename.size();
					
					for(int i=0;i<evCodeSize;i++){
						String codename2="";
						codename2=(String)lEvjccodename.get(i);
					%>
						<option value=<%=codename2%>
							<%if(jobcontent.equals(codename2)){out.print(" selected ");}%>><%=codename2%></option>
						<%}}%>
					</select>
					</td>
					<td class="info_title_01" width="80">工序技能等级</td> 
					<td height="2" align="right" class="info_content_00">
						<input type="hidden"	name="codeflag" value="CT">
					<select name="gxjndj" id = "gxjndj"	>
						<option value='' <%if(craft.equals("")){out.print(" selected ");}%>>请选择</option>
						<%
					List lEvgxjndjname = evscraft.getEvsgxjndjList();
					if(lEvgxjndjname!=null){
					int evCodeSize=lEvgxjndjname.size();
					
					for(int i=0;i<evCodeSize;i++){
						String codename0="";
						codename0=(String)lEvgxjndjname.get(i);
					%>
						<option value=<%=codename0%>
							<%if(gxjndj.equals(codename0)){out.print(" selected ");}%>><%=codename0%></option>
						<%}}%>
					</select>
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
				<td align="left" class="title1" colspan="10">评价结果</td>
			</tr>
			
		</table>
<table width="98%" border="0" align="center" cellpadding="0"	cellspacing="1">
			<tr>
				<td width ="98%" colspan=2 height ="250" align="left" id="main1"></td>
				
			</tr>
			<tr>
				
				<td width ="65%" height ="250" align="left" id="main2"></td>
				<td width ="30%" height ="250"  align="left" id="main3"></td>
			</tr>
	<tr>
		<td colspan=5 ehr class="line">
<script language="JavaScript">
<!--
document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-158) + ';\">')
//-->
</script>
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" bgcolor="#F5F5F5">
			<td nowrap="nowrap" class="info_title_01"><a href="#" onclick="checkAll();" style="color:red">全选</a></td>
				<td width="5%" height="30" class="info_title_01">	序号	</td>
				<td width="5%" class="info_title_01">年度</td>
				<td width="6%" class="info_title_01">职号</td>
				<td width="6%" class="info_title_01">姓名</td>				
				<td width="15%" class="info_title_01">部门</td>
				<td width="15%" class="info_title_01">课</td>			
				<td width="15%" class="info_title_01">职</td>				
				<td width="15%" class="info_title_01">组</td>					
				<td width="15%" class="info_title_01">工种</td>				
				<td width="15%" class="info_title_01">岗位</td>				
				<td width="15%" class="info_title_01">工序</td>
				<td width="15%" class="info_title_01">工序技能等级</td>
				<td width="15%" class="info_title_01">职业资格</td>
				<td width="15%" class="info_title_01">积分</td>
				<td width="15%" class="info_title_01">详情</td>
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
			 <td class="info_content_01">
				<input type="checkbox" name="selectC" value="<%=evsEmp2_tr.getSETUPCODENO()%>"/></td>
				<td height="30" class="tablecontent" ><div align="center" class="info_content_01"><%=i+1%>	</div></td>
				<td><div align="center" class="info_content_01"><%=evsEmp2_tr.getEvPeriodID()%></div></td>
				<td><div align="center" class="info_content_01"><%=evsEmp2_tr.getEvEmpID()%>&nbsp;</div></td>
				<td><div align="center" class="info_content_01"><%=evsEmp2_tr.getEvEmpName()%>&nbsp;</div></td>
				 <td align="center" class="info_content_01"><%=evsEmp2_tr.getEvDeptName()%></td>				
				 <td> <div align="center" class="info_content_01"><%=evsEmp2_tr.getDeptke()%>&nbsp;</div></td>				 
				 <td> <div align="center" class="info_content_01"><%=evsEmp2_tr.getDeptzhi()%>&nbsp;</div></td>				 
				 <td> <div align="center" class="info_content_01"><%=evsEmp2_tr.getDeptzu()%>&nbsp;</div></td>
				<td> <div align="center" class="info_content_01"><%=evsEmp2_tr.getCRAFT()%>&nbsp;</div></td>
				<td> <div align="center" class="info_content_01"><%=evsEmp2_tr.getLINE()%>&nbsp;</div></td>
				<td> <div align="center" class="info_content_01"><%=evsEmp2_tr.getJOBCONTENT()%>&nbsp;</div></td>
				 <td> <div align="center" class="info_content_01"><%=evsEmp2_tr.getSKILLLEVEL()%>&nbsp;</div></td>
				<td> <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getZYZGDJLEVEL())%>&nbsp;</div></td>
				<td> <div align="center" class="info_content_01"><%=StringUtil.checkNull(evsEmp2_tr.getSKILLSCORE())%>&nbsp;</div></td>			
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
<script>

var evDeptId = document.evs0302.evDeptId.value;
//var deptke = document.evs0302.deptke.value;
//var deptzhi = document.evs0302.deptzhi.value;
//var deptzu = document.evs0302.deptzu.value;

var craft = document.evs0302.craft.value;
var line = document.evs0302.line.value;
var jobcontent = document.evs0302.jobcontent.value;

function loadData1(option1) {
	var url = "/ajaxControlServlet" ;

	var names=[];    //
	var nums=[];    //
	//alert(deptzu);
   if(evDeptId!=null&&evDeptId!=""){
   new Ajax.Request(url, {
		parameters : new Hash({
			'operation' : 'getevsSetupempcount',
			'evDeptId' : evDeptId
		}),
		onSuccess : function(transport) {
			
		   var hash = $H(transport.responseJSON);
		   var empDiffs = hash.get("empDiff");
		   var param = hash.get("param");
		   
	   		
			empDiffs.each(
			   		function(empDiff){
			   			var obj = $H(empDiff);
			   			
			   			names.push(obj.get("CHINESENAME"));
			   			nums.push(obj.get("COUNT0"));
			   			//content += "<option value='"+ obj.get("ID") +"'>"+ obj.get("NAME") + "</option>";
			   			//alert(names[0]);
			   		}
		   		);
		   		
           myChart1.setOption(option1);  
           myChart1.setOption({        //加载数据图表
               xAxis: {
                   data: names
               },
               series: [{
                   // 根据名字对应到相应的系列
                   name: '工序数', 
                   data: nums
               }]
           });	   
          
		}
	});
   }
}			
function loadData2(option2) {
	var url = "/ajaxControlServlet" ;

	var names=[];    //
	var nums=[];    //
	//alert(deptzu);
	if(evDeptId!=null&&evDeptId!=""){
   new Ajax.Request(url, {
		parameters : new Hash({
			'operation' : 'getevsSetupempcount2',
			'evDeptId' : evDeptId
		}),
		onSuccess : function(transport) {
			
		   var hash = $H(transport.responseJSON);
		   var empDiffs = hash.get("empDiff");
		   var param = hash.get("param");
		   
	   		
			empDiffs.each(
			   		function(empDiff){
			   			var obj = $H(empDiff);
			   			
			   			names.push(obj.get("JOBCONTENT"));
			   			nums.push(obj.get("COUNT0"));
			   			//content += "<option value='"+ obj.get("ID") +"'>"+ obj.get("NAME") + "</option>";
			   			//alert(names[0]);
			   		}
		   		);
		   		
           myChart2.setOption(option2);  
           myChart2.setOption({        //加载数据图表
               xAxis: {
                   data: names
               },
               series: [{
                   // 根据名字对应到相应的系列
                   name: '人员数', 
                   data: nums
               }]
           });	   
          
		}
	});
	}
}	
function loadData3(option3) {
	var url = "/ajaxControlServlet" ;

	var names=[];    //
	var nums=[];    //
	//alert(deptzu);
	if(evDeptId!=null&&evDeptId!=""){
   new Ajax.Request(url, {
		parameters : new Hash({
			'operation' : 'getevsSetupempcount3',
			'evDeptId' : evDeptId
		}),
		onSuccess : function(transport) {
			
		   var hash = $H(transport.responseJSON);
		   var empDiffs = hash.get("empDiff");
		   var param = hash.get("param");
		   
	   		
			empDiffs.each(
			   		function(empDiff){
			   			var obj = $H(empDiff);
			   			
			   			names.push(obj.get("DEPTZU"));
			   			nums.push(obj.get("COUNT0"));
			   			//content += "<option value='"+ obj.get("ID") +"'>"+ obj.get("NAME") + "</option>";
			   			//alert(names[0]);
			   		}
		   		);
		   		
           myChart3.setOption(option3);  
           myChart3.setOption({        //加载数据图表
               xAxis: {
                   data: names
               },
               series: [{
                   // 根据名字对应到相应的系列
                   name: '人员数', 
                   data: nums
               }]
           });	   
          
		}
	});
	}
}
		var myChart1 = echarts.init(document.getElementById('main1'));
		option1 = {
		    title : {
		        text: '人员',
		        padding: [0, 0, 10, 200] 
		     
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		    	left: '1%',  
		    	
		        data:['工序数']
		    },

		    calculable : false,
		   
		    xAxis : [
		        {
		        	name: '人员',
		        	nameGap: 15, // 坐标轴名称与轴线之间的距离
		            type : 'category',
		            boundaryGap : false,
		            data : []
		        }
		    ],
		    yAxis : [
		        {
		        	show: true,  // 是否显示
		            position: 'left', // y轴位置
		            offset: 0, // y轴相对于默认位置的偏移
		            nameGap: 15, // 坐标轴名称与轴线之间的距离
			        type : 'value',
		            axisLabel : {
		                formatter: '{value}'
		            }
		        }
		    ],
		    series : [
		        {
		            name:'工序数',
		            type:'bar',
		            data:[],
		            itemStyle: {    // 图形的形状
		                color: 'blue',
		                barBorderRadius: [18, 18, 0 ,0]
		              },
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        },
		    
		    ]
		};
		loadData1(option1);
		//myChart1.setOption(option1);
		
			var myChart2 = echarts.init(document.getElementById('main2'));
			option2 = {
				    title : {
				        text: '工序',
				        padding: [0, 0, 10, 200]
				     
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				    	left: '1%', 
				        data:['人员数']
				    },

				    calculable : true,
				    xAxis : [
				        {
				        	name:'工序',
				            type : 'category',
				            boundaryGap : false,
				            data : []
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value}'
				            }
				        }
				    ],
				    series : [
				        {
				            name:'人员数',
				            type:'bar',
				            data:[],
				            itemStyle: {    // 图形的形状
				                color: 'blue',
				                barBorderRadius: [18, 18, 0 ,0]
				              },
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name: '平均值'}
				                ]
				            }
				        },
				    
				    ]
		};
			loadData2(option2);
			myChart2.setOption(option2);
			var myChart3 = echarts.init(document.getElementById('main3'));
			option3 = {
				    title : {
				        text: '一线通',
				        padding: [0, 0, 10, 200]
				     
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				    	left: '1%', 
				        data:['人员数']
				    },

				    calculable : true,
				    xAxis : [
				        {
				        	name:'班组',
				            type : 'category',
				            boundaryGap : false,
				            data :[]
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value}'
				            }
				        }
				    ],
				    series : [
				        {
				            name:'人员数',
				            type:'bar',
				            data:[],
				            itemStyle: {    // 图形的形状
				                color: 'blue',
				                barBorderRadius: [18, 18, 0 ,0]
				              },
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name: '平均值'}
				                ]
				            }
				        },
				    
				    ]
		};
			loadData3(option3);
		//	myChart3.setOption(option3);
</script>

