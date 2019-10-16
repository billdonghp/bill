<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.ait.reports.reportservices.PaReportService"%>
<%@ page import="com.ait.pa.business.PaServices"%>
<%@ page import="com.ait.evs.business.EvsServices"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>

<html>
<head>
<title></title>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<br>
<form name="rpt" method="post">
<input value="" name="rptUrl" type="hidden" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../evs/inc/evs_toolbar_report.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="16"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->

		<!-- display 3 level menu -->
		<%@ include file="../evs/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
<%
		request.setAttribute("admin" , admin) ; 
		EvsServices services = EvsServices.getInstance();
		List searchEvsSkilelist = services.getEvsSkilelist();
		List searchEvsSkiltypelist = services.getEvsSkiltypelist();
		List searchEvsTypeoationlist = services.getEvsTypeoationlist();
		List searchEvsQualificationlist = services.getEvsQualificationList();
		List searchEvsPurposelist = services.getEvsPurposeList();
		List searchEvsCraftlist = services.getEvsCraftList();
		
		
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("screenGrantNo", admin.getScreenGrantNo());
		parameterObject.setString("cpnyId",admin.getCpnyId()) ;
		
		PaReportService paReportService = new PaReportService() ;
		PaServices paServices = PaServices.getInstance() ;

		List evsReportList = paReportService.retrieveEvsReportList(parameterObject) ;
		List evsReportList2 = new ArrayList() ;
		List evsReportList3 = new ArrayList() ;
		List evsReportList4 = new ArrayList() ;
		List evsReportList6 = new ArrayList() ;
		List evsReportList7 = new ArrayList() ;
		
		for (int i = 0; i < evsReportList.size(); ++i )
		{
			SimpleMap reportMap = (SimpleMap)evsReportList.get(i) ;
			
			if (reportMap.getString("MENU_CODE").equals("report0535"))
			{
				evsReportList2.add(reportMap) ;
				
			}else if ( reportMap.getString("MENU_CODE").equals("report0538"))
			{
				evsReportList3.add(reportMap) ;
			}
			else if (reportMap.getString("MENU_CODE").equals("report0536"))
			{
				evsReportList4.add(reportMap) ;
			}
			else if ( reportMap.getString("MENU_CODE").equals("report0537"))
			{
				evsReportList6.add(reportMap) ;
			}
			else if ( reportMap.getString("MENU_CODE").equals("report0539"))
			{
				evsReportList7.add(reportMap) ;
			}
		}
		
		
		request.setAttribute("arReportList",evsReportList) ;
		request.setAttribute("arReportList2",evsReportList2) ;
		request.setAttribute("arReportList3",evsReportList3) ;
		request.setAttribute("arReportList4",evsReportList4) ;
		request.setAttribute("arReportList6",evsReportList6) ;
		request.setAttribute("arReportList7",evsReportList7) ;
		request.setAttribute("searchEvsSkilelist",searchEvsSkilelist) ;
		request.setAttribute("searchEvsSkiltypelist",searchEvsSkiltypelist) ;
		request.setAttribute("searchEvsTypeoationlist",searchEvsTypeoationlist) ;
		request.setAttribute("searchEvsQualificationlist",searchEvsQualificationlist) ;
		request.setAttribute("searchEvsPurposelist",searchEvsPurposelist) ;
		request.setAttribute("searchEvsCraftlist",searchEvsCraftlist) ;
		// 查询条件
		List paFilialeDeptList = new ArrayList() ;
		List paFactoryDeptList = new ArrayList() ;
		List paTypeList = new ArrayList() ;
		
	    Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1;
	
		paFilialeDeptList = paReportService.retrievePaFilialeDeptList(parameterObject) ;
		
		if (admin.getCpnyId().equals("60000000"))
			parameterObject.setInt("deptLevel",3) ;
		else
			parameterObject.setInt("deptLevel",4) ;
		
		paFactoryDeptList = paReportService.retrievePaDeptList(parameterObject) ;
		
		paTypeList = paServices.paTypeList(parameterObject);	
		
		request.setAttribute("paFilialeDeptList",paFilialeDeptList) ;
		request.setAttribute("paFactoryDeptList",paFactoryDeptList) ;
%>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="11"><!-- 工资报表 -->
				评价报表
				</td>
			</tr>
		 </table>
		<table width="100%" border="1" cellpadding="2" cellspacing="1" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >
			
			  <tr>
			    <td rowspan="1" class="info_title_01"><!--报表查询-->
					<ait:message  messageID="display.att.view.report.report" module="ar"/></td>
			    <td colspan="8" rowspan="3" >
			    	<table width="90%" border="1" cellpadding="2" cellspacing="1" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >
			    	
			    	<c:if test="${fn:length(arReportList3) > 0}">
			      			<tr>
			      				<td height="30" colspan="2" >
				      				<table width="100%">
				      					<c:forEach items="${arReportList3}" var="report" varStatus="i">
				      					<tr >
					      					<td height="30" colspan="2" class="info_content_00" >
								     			<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
								     		</td>
				      					</tr>
				      					</c:forEach>
				      				</table>
			      				</td>
			      				
			      				<td height="30" colspan="2" class="info_content_00" >
						     	<table width="100%">
						     		<tr >
						     	 <td> 
						     		年度 ：&nbsp; 
						     		<ait:date yearName="report0538_year"yearPlus="10" /> &nbsp;
						     		至&nbsp;&nbsp;<ait:date yearName="report0538_yearS"yearPlus="10" />&nbsp;&nbsp;
						     		<!-- 部门 -->部门 ：&nbsp; <ait:selDept2 name="report0538_deptid" all=" " supervisorType="evs"/>&nbsp;&nbsp;
						     		             
				 					 <!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>&nbsp;:&nbsp; 
									    <input id="arReportList4_empId" name="arReportList4_empId" size="10" value="${empId}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
									    <span id="arReportList4_name"></span>&nbsp;&nbsp;
									      工种：&nbsp; <ait:select dataListName="searchEvsCraftlist" name="craftid" disabled="false" all="all" />&nbsp;&nbsp;
						     			 职业资格：&nbsp; <ait:select dataListName="searchEvsQualificationlist" name="qualificationid" disabled="false" all="all" />&nbsp;&nbsp;
						     			  培训目标：&nbsp; <ait:select dataListName="searchEvsPurposelist" name="purposeid" disabled="false" all="all" />
			                        </td>
						     		</tr>
						     	</table>
						     	</td> 
			      			</tr>
			      		</c:if>
			    	
			    	
			    	<c:if test="${fn:length(arReportList2) > 0}">
			      			<tr>
			      				<td height="30" colspan="2" >
				      				<table width="100%">
				      					<c:forEach items="${arReportList2}" var="report" varStatus="i">
				      					<tr >
					      					<td height="30" colspan="2" class="info_content_00" >
								     			<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
								     		</td>
				      					</tr>
				      					</c:forEach>
				      				</table>
			      				</td>
			      				
			      				<td height="30" colspan="2" class="info_content_00" >
			      					<table width="100%">
			      				  <tr >
							     	    <td>
								     		<!-- 年度 -->年度 ：&nbsp; 
								     		<ait:date yearName="report0535_year"yearPlus="10" /> &nbsp;
						     		至&nbsp;&nbsp;&nbsp;<ait:date yearName="report0535_yearS"yearPlus="10" />
							     	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							     	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							     	     	技能类型数量：&nbsp;
										 <select name='skilltypenum'> 
											 <option value=''>请选择</option>
											 <option value='1'>1</option>
											 <option value='2'>2</option>
											 <option value='3'>3</option>
											 <option value='4'>4</option>
											 <option value='5'>5</option>
											 <option value='6'>6</option>
											 <option value='7'>7</option>
											 <option value='8'>8</option>
										 </select>
										 &nbsp;&nbsp;
							     	     	工序数量：&nbsp;
										 <select name='processnum'> 
										     <option value=''>请选择</option> 
											 <option value='1'>1</option>
											 <option value='2'>2</option>
											 <option value='3'>3</option>
											 <option value='4'>4</option>
											 <option value='5'>5</option>
											 <option value='6'>6</option>
											 <option value='7'>7</option>
											 <option value='8'>8</option>
										 </select>
							           </td>
						     	  </tr>
						     	  <tr></tr>
						     	  <tr> 
						     	    <td>
						     	    <!-- 部门 -->
						     	    部门 ：&nbsp; <ait:selDept2 name="report0535_deptid" all=" " supervisorType="evs"/>&nbsp;&nbsp;

						     	       技能类型数量：&nbsp;>=&nbsp;
										 <select name='skilltype'> 
											 <option value=''>请选择</option>
											 <option value='2'>2</option>
											 <option value='3'>3</option>
											 <option value='4'>4</option>
											 <option value='5'>5</option>
											 <option value='6'>6</option>
											 <option value='7'>7</option>
											 <option value='8'>8</option>
										 </select>
										 &nbsp;&nbsp;
								      工序数量：&nbsp;>=&nbsp;
										 <select name='process'> 
										     <option value=''>请选择</option> 
											 <option value='2'>2</option>
											 <option value='3'>3</option>
											 <option value='4'>4</option>
											 <option value='5'>5</option>
											 <option value='6'>6</option>
											 <option value='7'>7</option>
											 <option value='8'>8</option>
										 </select>
							           </td>
						     	  </tr>
			      					</table>
			      				</td>
			      			</tr>
			      		</c:if>
			    	
			    	   <c:if test="${fn:length(arReportList4) > 0}">
			      			<tr>
			      				<td height="30" colspan="2" >
				      				<table width="100%">
				      					<c:forEach items="${arReportList4}" var="report" varStatus="i">
				      					<tr >
					      					<td height="30" colspan="2" class="info_content_00" >
								     			<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
								     		</td>
				      					</tr>
				      					</c:forEach>
				      				</table>
			      				</td>
			      				
			      				<td height="30" colspan="2" class="info_content_00" >
			      					<table width="100%">
			      					<tr >
			      						<tr>
			      						<td>
									    		<!-- 年度 -->年度 ：&nbsp; 
								     		<ait:date yearName="report0536_year"yearPlus="10" /> &nbsp;
						     		至&nbsp;&nbsp;&nbsp;<ait:date yearName="report0536_yearS"yearPlus="10" />&nbsp;&nbsp;&nbsp;
							     	     <!-- 部门 -->
						     	                     部门 ：&nbsp; <ait:selDept2 name="report0536_deptid" all=" " supervisorType="evs"/>&nbsp;&nbsp;
			      							技能类型：&nbsp;
									    	  <ait:select dataListName="searchEvsSkiltypelist" name="skiltypeid"
											 disabled="false"
											all="all" />
									       &nbsp; &nbsp;&nbsp;技能等级：&nbsp;
											 <ait:select dataListName="searchEvsSkilelist" name="skileid"
											 disabled="false"
											all="all" />
			      						</td>
			      					</tr>
			      					</table>
			      				</td>
			      			</tr>
			      		</c:if>
			    	
			    	
			      		<c:if test="${fn:length(arReportList6) > 0}">
			      			<tr>
			      				<td height="30" colspan="2" >
				      				<table width="100%">
				      					<c:forEach items="${arReportList6}" var="report" varStatus="i">
				      					<tr >
					      					<td height="30" colspan="2" class="info_content_00" >
								     			<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
								     		</td>
				      					</tr>
				      					</c:forEach>
				      				</table>
			      				</td>
			      				
			      				<td height="30" colspan="2" class="info_content_00" >
						     	<table width="100%">
						     		<tr >
						     	 <td>
						     		<!-- 年度 -->年度 ：&nbsp; 
						     		<ait:date yearName="report0537_year"yearPlus="10" /> &nbsp;
						     		至&nbsp;&nbsp;&nbsp;<ait:date yearName="report0537_yearS"yearPlus="10" />&nbsp;&nbsp;&nbsp;
						     				<!-- 部门 -->部门 ：&nbsp; <ait:selDept2 name="report0537_deptid" all=" " supervisorType="evs"/>&nbsp;&nbsp;
						     				 作业类型：&nbsp;
									    	  <ait:select dataListName="searchEvsTypeoationlist" name="typeoationid"
											 disabled="false"
											all="all" />
						     			</td>
						     		</tr>
						     	</table>
						     	</td> 
			      			</tr>
			      		</c:if>
			      		
			      		<c:if test="${fn:length(arReportList7) > 0}">
			      			<tr>
			      				<td height="30" colspan="2" >
				      				<table width="100%">
				      					<c:forEach items="${arReportList7}" var="report" varStatus="i">
				      					<tr >
					      					<td height="30" colspan="2" class="info_content_00" >
								     			<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
								     		</td>
				      					</tr>
				      					</c:forEach>
				      				</table>
			      				</td>
			      				
			      				<td height="30" colspan="2" class="info_content_00" >
						     	<table width="100%">
						     		<tr >
						     	 <td>
						     		<!-- 年度 -->年度 ：&nbsp; 
						     		<ait:date yearName="report0539_year"yearPlus="10" /> &nbsp;
						     		至&nbsp;&nbsp;&nbsp;<ait:date yearName="report0539_yearS"yearPlus="10" />&nbsp;&nbsp;&nbsp;
						     				<!-- 部门 -->部门 ：&nbsp; <ait:selDept2 name="report0539_deptid" all=" " supervisorType="evs"/>&nbsp;&nbsp;
						     			</td>
						     		</tr>
						     	</table>
						     	</td> 
			      			</tr>
			      		</c:if>
			      		
			    	</table>
			    </td>
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:forEach var="i" begin="1" end="3" step="1">
				<tr>
					<td class="info_content_01" height="30"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
				</tr>
			</c:forEach>
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
<iframe id='iemp' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:200;width:370; height:210; z-index:2;visibility: hidden;">   
</div>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--  

//打开excel的函数
function ImportExcel(){
    var paMonth = "";
    var paYear = "" ;
	var reportType ="";
	var url = "" ;
	var supplyFlag ="";
               
	var check = document.getElementsByName("check") ;           
	for (i = 0 ; i < check.length ; i++){  
	  
		 if (check[i].checked){
		 	
		 	reportType = check[i].value;
		 }
	} 
	if (reportType == ""){
		//请选择报表
		alert('<ait:message messageID="alert.att.view.report.select_report" module="ar"/>');
	}else{
		  if (reportType == 'report0535')//评价报表1  按年度区间、多技能(类型)数、多工序数查询
		{
			var year = $("report0535_year").value;
			var yearEnd = $("report0535_yearS").value;
			var deptid = $("report0535_deptid").value;
			var skilltypenum = $("skilltypenum").value;
			var processnum = $("processnum").value; 
			var skilltype = $("skilltype").value;
			var process = $("process").value; 
			var distinction = ""; 
			url = "/reportControlServlet?operation=" + reportType + "&year=" + year + "&yearEnd=" + yearEnd + "&skilltypenum=" + skilltypenum + "&processnum=" + processnum + "&skilltype=" + skilltype + "&process=" + process + "&deptid=" + deptid;

		}
		else if (reportType == 'report0536')//评价报表2  按年度区间、技能类型、技能等级
		{
			var year = $("report0536_year").value;
			var yearEnd = $("report0536_yearS").value;
			var deptid = $("report0536_deptid").value;
			var skiltypeid = $("skiltypeid").value;
			var skileid = $("skileid").value;
			var distinction = ""; 
			url = "/reportControlServlet?operation=" + reportType + "&year=" + year + "&yearEnd=" + yearEnd + "&skiltypeid=" + skiltypeid + "&skileid=" + skileid + "&deptid=" + deptid;

		
		}
		else if (reportType == 'report0537')//评价报表3  按年度区间、部门，作业类型
		{
			var year = $("report0537_year").value;
			var yearEnd = $("report0537_yearS").value; 
			var deptid = $("report0537_deptid").value ;
			var typeoationid = $("typeoationid").value;
			var distinction = ""; 
			url = "/reportControlServlet?operation=" + reportType + "&year=" + year + "&yearEnd=" + yearEnd + "&skiltypeid=" + typeoationid + "&deptid=" + deptid;

		
		}else if (reportType == 'report0538')//评价报表4  按部门，人员
		{
			var year = $("report0538_year").value;
			var yearEnd = $("report0538_yearS").value;
			var deptid = $("report0538_deptid").value ;
			var empId = $("arReportList4_empId").value ;
			var craftid = $("craftid").value;
			var qualificationid = $("qualificationid").value; 
			var purposeid = $("purposeid").value;
			var distinction = ""; 
			url = "/reportControlServlet?operation=" + reportType + "&empId=" + empId + "&deptid=" + deptid + "&year=" + year + "&yearEnd=" + yearEnd + "&craftid=" + craftid + "&qualificationid=" + qualificationid + "&purposeid=" + purposeid;;

		
		}
		else if (reportType == 'report0539')//评价报表5  按部门，人员,平均分数
		{
		    //alert("11111111111111");
			var year = $("report0539_year").value; 
			var yearEnd = $("report0539_yearS").value; 
			var deptid = $("report0539_deptid").value ;
			  
			var distinction = ""; 
			url = "/reportControlServlet?operation=" + reportType + "&year=" + year + "&yearEnd=" + yearEnd + "&deptid=" + deptid;

		
		}
		else if (reportType == 'report0225')//银行账号
		{
			paMonth = $(reportType + "_Year").value + $(reportType + "_Month").value ;
			var bonusTypeCode = $(reportType + "_bonusTypeCode").value ;
			var bonusNo = $(reportType + "_bonusNo").value ;
			var statTypeCode = $(reportType + "_statTypeCode").value ;
			var tableName = $(reportType + "_tableName").value ;
			var supplyFlag = $(reportType + "_supplyFlag").value ;
			
			url = "/reportControlServlet?operation=" + reportType + "&statTypeCode=" + statTypeCode + "&paMonth=" + paMonth
				+ "&tableName=" + tableName + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo + "&supplyFlag=" + supplyFlag;
		}
		else
		{
			alert("此报表不支持导出excel功能!!!!") ;
			return ;
		}
		
		//alert(url) ;
		window.open(url ,"");
	}
}

//打开页面数据
function ImportExcel2(){
    var paMonth = "";
    var paYear = "" ;
	var reportType ="";
	var url = "" ;
	var supplyFlag ="";
    var name="searchEvsEmp";           
	var check = document.getElementsByName("check") ;           
	for (i = 0 ; i < check.length ; i++){  
	  
		 if (check[i].checked){
		 	
		 	reportType = check[i].value;
		 }
	} 
	if (reportType == ""){
		//请选择报表
		alert('<ait:message messageID="alert.att.view.report.select_report" module="ar"/>');
	}else{
		  if (reportType == 'report0535')//评价报表1  按年度区间、多技能(类型)数、多工序数查询
		{
			var year = $("report0535_year").value;
			var yearEnd = $("report0535_yearS").value;
			var deptid = $("report0535_deptid").value;
			var skilltypenum = $("skilltypenum").value;
			var processnum = $("processnum").value; 
			var skilltype = $("skilltype").value;
			var process = $("process").value; 
			var distinction = ""; 
			//url = "/reportControlServlet?operation=" + reportType + "&year=" + year + "&skilltypenum=" + skilltypenum + "&processnum=" + processnum + "&skilltype=" + skilltype + "&process=" + process;
		      url="/reportControlServlet?operation=report0535001&year=" + year + "&yearEnd=" + yearEnd + "&skilltypenum=" + skilltypenum + "&processnum=" + processnum + "&skilltype=" + skilltype + "&process=" + process + "&deptid=" + deptid;
            var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=700,height=500";
          window.open(url,name,features);
		}
		else if (reportType == 'report0536')//评价报表2  按年度区间、技能类型、技能等级
		{
			var year = $("report0536_year").value;
			var yearEnd = $("report0536_yearS").value;
			var deptid = $("report0536_deptid").value;
			var skiltypeid = $("skiltypeid").value;
			var skileid = $("skileid").value;
			var distinction = ""; 
			//url = "/reportControlServlet?operation=" + reportType + "&year=" + year + "&skiltypeid=" + skiltypeid + "&skileid=" + skileid;
            url="/reportControlServlet?operation=report0536001&year=" + year + "&yearEnd=" + yearEnd + "&skiltypeid=" + skiltypeid + "&skileid=" + skileid + "&deptid=" + deptid;
            var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=700,height=500";
          window.open(url,name,features);
		
		}
		else if (reportType == 'report0537')//评价报表3  按年度区间、部门，作业类型
		{
			var year = $("report0537_year").value; 
			var yearEnd = $("report0537_yearS").value; 
			var deptid = $("report0537_deptid").value ;
			var typeoationid = $("typeoationid").value;
			//url = "/reportControlServlet?operation=" + reportType + "&year=" + year + "&skiltypeid=" + typeoationid + "&deptid=" + deptid;
            url="/reportControlServlet?operation=report0537001&year=" + year + "&yearEnd=" + yearEnd + "&typeoationid=" + typeoationid + "&deptid=" + deptid;
            var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=700,height=500";
          window.open(url,name,features);
		
		}else if (reportType == 'report0538')//评价报表4  按部门，人员
		{
			var year = $("report0538_year").value; 
			var yearEnd = $("report0538_yearS").value; 
			var deptid = $("report0538_deptid").value ;
			var empId = $("arReportList4_empId").value ;
			var craftid = $("craftid").value;
			var qualificationid = $("qualificationid").value;
			var purposeid = $("purposeid").value;
			//url = "/reportControlServlet?operation=" + reportType + "&empId=" + empId + "&deptid=" + deptid;
            url="/reportControlServlet?operation=report0538001&empId=" + empId + "&deptid=" + deptid + "&year=" + year + "&yearEnd=" + yearEnd + "&craftid=" + craftid +"&qualificationid=" + qualificationid + "&purposeid=" + purposeid;
            var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=700,height=500";
          window.open(url,name,features);
		
		}
		else if (reportType == 'report0539')//评价报表5  按部门，人员,平匀分数
		{
			var year = $("report0539_year").value; 
			var yearEnd = $("report0539_yearS").value; 
			var deptid = $("report0539_deptid").value ;
			//url = "/reportControlServlet?operation=" + reportType + "&year=" + year + "&deptid=" + deptid;
 			url="/reportControlServlet?operation=report0539001&year=" + year + "&yearEnd=" + yearEnd + "&deptid=" + deptid;
            var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=700,height=500";
          window.open(url,name,features);
		}
		else
		{
			alert("此报表不支持导出打印功能!!!!") ;
			return ;
		}
		
		//alert(url) ;
		///window.open(url ,"");
	}
}


var time=null;
function SearchEmp(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=evsSearchEmployeeAll&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft-270;  
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft-270;
		  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
    	layeri.style.height = 48;
		layer.style.height = 48; 
    }else if(size<210){
		layeri.style.height = size;
		layer.style.height = size;  
    }else{
    	layeri.style.height = 210;
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText.replace("*","&");
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('arReportList4_empId').value=cell.childNodes[0].firstChild.nodeValue;
		$('arReportList4_name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
		layerClose();
}
//-->

</SCRIPT>
			  