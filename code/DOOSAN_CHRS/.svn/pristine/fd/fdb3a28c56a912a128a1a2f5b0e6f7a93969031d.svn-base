<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.ait.reports.reportservices.PaReportService"%>
<%@ page import="com.ait.pa.business.PaServices"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>

<html>
<head>
<!-- pa0101.jsp -->
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
			<%@ include file="../pa/inc/pa_toolbar_report.jsp"%>
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
		<%@ include file="../pa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
<%
		request.setAttribute("admin" , admin) ;
		
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("screenGrantNo", admin.getScreenGrantNo());
		parameterObject.setString("cpnyId",admin.getCpnyId()) ;
		
		PaReportService paReportService = new PaReportService() ;
		PaServices paServices = PaServices.getInstance() ;
		
		List paReportList = paReportService.retrievePaReportList(parameterObject) ;
		List paReportList2 = new ArrayList() ;
		List paReportList3 = new ArrayList() ;
		List paReportList4 = new ArrayList() ;
		List paReportList5 = new ArrayList() ;
		List paReportList6 = new ArrayList() ;
		List paReportList7 = new ArrayList() ;
		
		for (int i = 0; i < paReportList.size(); ++i )
		{
			SimpleMap reportMap = (SimpleMap)paReportList.get(i) ;
			
			if (   reportMap.getString("MENU_CODE").equals("report0202") || reportMap.getString("MENU_CODE").equals("report0203")
			    || reportMap.getString("MENU_CODE").equals("report0204") || reportMap.getString("MENU_CODE").equals("report0205")
			    || reportMap.getString("MENU_CODE").equals("report0206") || reportMap.getString("MENU_CODE").equals("report0207")
			     )
			{
				paReportList2.add(reportMap) ;
				
			}
			else if (  reportMap.getString("MENU_CODE").equals("report0208") || reportMap.getString("MENU_CODE").equals("report0209")
				    || reportMap.getString("MENU_CODE").equals("report0210") || reportMap.getString("MENU_CODE").equals("report0211") )
			{
				paReportList3.add(reportMap) ;
			}
			else if (  reportMap.getString("MENU_CODE").equals("report0213") || reportMap.getString("MENU_CODE").equals("report0217") )
			{
				paReportList4.add(reportMap) ;
			}
			else if (  reportMap.getString("MENU_CODE").equals("report0219") || reportMap.getString("MENU_CODE").equals("report0220") )
			{
				paReportList5.add(reportMap) ;
			}
			else if (  reportMap.getString("MENU_CODE").equals("report0212") || reportMap.getString("MENU_CODE").equals("report0226") )
			{
				paReportList6.add(reportMap) ;
			}
			else if (  reportMap.getString("MENU_CODE").equals("report0227") || reportMap.getString("MENU_CODE").equals("report0228") || reportMap.getString("MENU_CODE").equals("report0229")
			|| reportMap.getString("MENU_CODE").equals("report0230") || reportMap.getString("MENU_CODE").equals("report0231") || reportMap.getString("MENU_CODE").equals("report0232") || reportMap.getString("MENU_CODE").equals("report0233") || reportMap.getString("MENU_CODE").equals("report0234") )
			{
				paReportList7.add(reportMap) ;
				
			}
		}
		
		
		request.setAttribute("arReportList",paReportList) ;
		request.setAttribute("arReportList2",paReportList2) ;
		request.setAttribute("arReportList3",paReportList3) ;
		request.setAttribute("arReportList4",paReportList4) ;
		request.setAttribute("arReportList5",paReportList5) ;
		request.setAttribute("arReportList6",paReportList6) ;
		request.setAttribute("arReportList7",paReportList7) ;
	
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
				<ait:message messageID="display.pay.view.report.payroll_report" module="pay"></ait:message>
				</td>
			</tr>
		 </table>
		<table width="100%" border="1" cellpadding="2" cellspacing="1" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >
			
			  <tr>
			    <td rowspan="1" class="info_title_01"><!--报表查询-->
					<ait:message  messageID="display.att.view.report.report" module="ar"/></td>
			    <td colspan="8" rowspan="3" >
			    	<table width="90%" border="1" cellpadding="2" cellspacing="1" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >
			    		<c:forEach items="${arReportList}" var="report">
			      	
			      		<c:if test="${report.MENU_CODE eq 'report0201' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		&nbsp;<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!-- 年度 -->年度 : 
						     		<ait:date yearName="${report.MENU_CODE}_year" yearPlus="10" yearMinus="11"/> 
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0221' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		&nbsp;<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!-- 年度 -->年度 : 
						     		<ait:date yearName="${report.MENU_CODE}_year" yearPlus="10" yearMinus="11"/> 
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0222' }">
				      		<tr>
						    	<td height="30" colspan="2" class="info_content_00" >
						     		&nbsp;<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!-- 年度 -->年度 : 
						     		<ait:date yearName="${report.MENU_CODE}_year" yearPlus="10" yearMinus="11"/> 
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0223' }">
				      		<tr>
						    	<td height="30" colspan="2" class="info_content_00" >
						     		&nbsp;<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!-- 部门 -->部门:&nbsp;<ait:selDept name="${report.MENU_CODE}_deptid" all=" " supervisorType="pa"/>&nbsp;&nbsp;
						     		<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="${report.MENU_CODE}_Year" monthName="${report.MENU_CODE}_Month" yearPlus="10"/>&nbsp;&nbsp;
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0224' }">
				      		<tr>
						    	<td height="30" colspan="2" class="info_content_00" >
						     		&nbsp;<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!-- 部门 -->部门:&nbsp;<ait:selDept name="${report.MENU_CODE}_deptid" all=" " supervisorType="pa"/>&nbsp;&nbsp;
						     		<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="${report.MENU_CODE}_Year" monthName="${report.MENU_CODE}_Month" yearPlus="10" yearMinus="11"/>&nbsp;&nbsp;
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0225' }">
				      		<tr>
						    	<td height="30" colspan="2" class="info_content_00" >
						     		&nbsp;<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
			      							区分:&nbsp;
									    	<select name="${report.MENU_CODE}_tableName">
									        		<option value="PA_HISTORY" <c:if test="${tableName == 'PA_HISTORY'}">selected</c:if> >工资</option>
									        		<option value="PA_BONUS_HISTORY" <c:if test="${tableName == 'PA_BONUS_HISTORY'}">selected</c:if>>奖金</option>
									        </select>&nbsp;&nbsp;
			      						考勤区分:&nbsp;<ait:empDiff cpnyDiff="<%= admin.getCpnyId() %>" name="${report.MENU_CODE}_statTypeCode" />&nbsp;&nbsp;
			      						<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="${report.MENU_CODE}_Year" monthName="${report.MENU_CODE}_Month" yearPlus="10" />&nbsp;&nbsp;
			      						奖金名称:&nbsp;<ait:codeClass codeClass="BonusType" name="${report.MENU_CODE}_bonusTypeCode" />&nbsp;&nbsp;
											<select name="${report.MENU_CODE}_bonusNo">
												<c:forEach var="i" begin="1" end="9" step="1">
													<option value="${i}">${i}</option>
												</c:forEach>
											</select>
											&nbsp;&nbsp;补发工资:&nbsp;
										 <select name="${report.MENU_CODE}_supplyFlag">
												<option value="N">否</option>	
												<option value="Y">是</option>	
										 </select>
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		</c:forEach>
			      		
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
						     				区分:&nbsp;
									    	<select name="arReportList6_tableName">
									        		<option value="PA_HISTORY" <c:if test="${tableName == 'PA_HISTORY'}">selected</c:if> >工资</option>
									        		<!-- <option value="PA_REPLENISHMENT_HISTORY" <c:if test="${tableName == 'PA_REPLENISHMENT_HISTORY'}">selected</c:if> >工资(补)</option> -->
									        		<option value="PA_BONUS_HISTORY" <c:if test="${tableName == 'PA_BONUS_HISTORY'}">selected</c:if>>奖金</option>
									        </select>&nbsp;&nbsp;
						     			</td>
						     		</tr>
						     		<tr>
			      						<td>&nbsp;</td>
			      					</tr>
						     		<tr >
						     			<td>
						     				<!-- 部门 -->部门:&nbsp;<ait:selDept name="arReportList6_deptid" all=" " supervisorType="pa"/>&nbsp;&nbsp;
						     				<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="arReportList6_Year" monthName="arReportList6_Month" yearPlus="10"/>&nbsp;&nbsp;
								     		奖金名称:&nbsp;<ait:codeClass codeClass="BonusType" name="arReportList6_bonusTypeCode" />&nbsp;&nbsp;
											<select name="arReportList6_bonusNo">
												<c:forEach var="i" begin="1" end="9" step="1">
													<option value="${i}">${i}</option>
												</c:forEach>
											</select>
											&nbsp;&nbsp;补发工资:&nbsp;
											 <select name="arReportList6_supplyFlag">
													<option value="N">否</option>	
													<option value="Y">是</option>	
											 </select>
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
			      						<!-- 部门 -->部门:&nbsp;
			    						<select name="arReportList2_deptid">
			    						  	<option value="">请选择</option>
			    						  	<c:forEach var="dept" items="${paFactoryDeptList}" varStatus="i">
			    						  		<option value="${dept.DEPTID}"><c:forEach begin="1" end="${dept.DEPT_LEVEL - 2}">&nbsp;&nbsp;</c:forEach>${dept.DEPTNAME}</option>
			    						  	</c:forEach>
			    						</select>&nbsp;&nbsp;
								   		<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="arReportList2_Year" monthName="arReportList2_Month" yearPlus="10" yearMinus="11"/>&nbsp;&nbsp;
									    奖金名称:&nbsp;<ait:codeClass codeClass="BonusType" name="arReportList2_bonusTypeCode" />&nbsp;&nbsp;
											<select name="arReportList2_bonusNo">
												<c:forEach var="i" begin="1" end="9" step="1">
													<option value="${i}">${i}</option>
												</c:forEach>
											</select>
										&nbsp;&nbsp;补发工资:&nbsp;
										 <select name="arReportList2_supplyFlag">
												<option value="N">否</option>	
												<option value="Y">是</option>	
										 </select>
			      						</td>
			      					</tr>
			      					<tr>
			      						<td>&nbsp;</td>
			      					</tr>
			      					<tr >
			      						<td>
			      							<!-- 员工区分 -->员工区分:&nbsp;
					      					<c:if test="${admin.cpnyId eq '60000000'}">
					      						A职员&nbsp;<input type="checkbox" value="A职员" enValue="OFFICER" name="arReportList2_distinction"> 
				   								B职员&nbsp;<input type="checkbox" value="B职员" enValue="WORKER" name="arReportList2_distinction"> 
					      					</c:if>
					      					<c:if test="${admin.cpnyId ne '60000000'}">
					      						职员&nbsp;<input type="checkbox" value="职员" enValue="OFFICER" name="arReportList2_distinction"> 
				   								工人&nbsp;<input type="checkbox" value="工人" enValue="WORKER" name="arReportList2_distinction"> 
				   								退休返聘&nbsp;<input type="checkbox" value="退休返聘" enValue="WORKER2" name="arReportList2_distinction"> 
					      					</c:if>
			      						</td>
			      					</tr>
			      					</table>
			      				</td>
			      			</tr>
			      		</c:if>
			      		
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
			      						  <!-- 部门 -->部门:&nbsp;
			    						  <select name="arReportList3_deptid">
			    						  	<option value="">请选择</option>
			    						  	<c:forEach var="dept" items="${paFilialeDeptList}" varStatus="i">
			    						  		<option value="${dept.DEPTID}">${dept.DEPTNAME}</option>
			    						  	</c:forEach>
			    						  </select>&nbsp;&nbsp;
			    						  <!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="arReportList3_Year" monthName="arReportList3_Month" yearPlus="10" yearMinus="11"/>&nbsp;&nbsp;
			    						  奖金名称:&nbsp;<ait:codeClass codeClass="BonusType" name="arReportList3_bonusTypeCode" />&nbsp;&nbsp;
											<select name="arReportList3_bonusNo">
												<c:forEach var="i" begin="1" end="9" step="1">
													<option value="${i}">${i}</option>
												</c:forEach>
											</select>
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
			      						 <!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="arReportList7_Year" monthName="arReportList7_Month" yearPlus="10" yearMinus="11"/>&nbsp;&nbsp;
			    						 
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
			      						<td>
			      						<tr >
			      						<td>
			      							区分:&nbsp;
									    	<select name="arReportList4_tableName">
									        		<option value="PA_HISTORY" <c:if test="${tableName == 'PA_HISTORY'}">selected</c:if> >工资</option>
									        		<!-- <option value="PA_REPLENISHMENT_HISTORY" <c:if test="${tableName == 'PA_REPLENISHMENT_HISTORY'}">selected</c:if> >工资(补)</option> -->
									        		<option value="PA_BONUS_HISTORY" <c:if test="${tableName == 'PA_BONUS_HISTORY'}">selected</c:if>>奖金</option>
									        </select>
									        &nbsp;&nbsp;
			      							邮件:&nbsp;
									    	<select name="arReportList4_email">
									        		<option value="all" >全部</option>
									        		<option value="email" >有邮件</option>
									        		<option value="noEmail" >无邮件</option>
									        </select>
									        &nbsp;&nbsp;补发工资:&nbsp;
											 <select name="arReportList4_supplyFlag">
													<option value="N">否</option>	
													<option value="Y">是</option>	
											 </select>
			      						</td>
			      					</tr>
			      					<tr>
			      						<td>&nbsp;</td>
			      					</tr>
			      					<tr>
			      						<td>
			      						<!-- 部门 -->部门:&nbsp;<ait:selDept name="arReportList4_deptid" all=" " supervisorType="pa"/>&nbsp;&nbsp;
			      						<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="arReportList4_Year" monthName="arReportList4_Month" yearPlus="10" yearMinus="11"/>&nbsp;&nbsp;
			      						奖金名称:&nbsp;<ait:codeClass codeClass="BonusType" name="arReportList4_bonusTypeCode" />&nbsp;&nbsp;
											<select name="arReportList4_bonusNo">
												<c:forEach var="i" begin="1" end="9" step="1">
													<option value="${i}">${i}</option>
												</c:forEach>
											</select> &nbsp;&nbsp;
										<!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>&nbsp;:&nbsp; 
									    <input id="arReportList4_empId" name="arReportList4_empId" size="10" value="${empId}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
									    <span id="arReportList4_name"></span>
			      						</td>
			      					</tr>
			      					
			      					
			      					</table>
			      				</td>
			      			</tr>
			      		</c:if>
			      		
			      		<c:if test="${fn:length(arReportList5) > 0}">
			      			<tr align="left">
			      				<td height="30" colspan="2" >
				      				<table width="100%">
				      					<c:forEach items="${arReportList5}" var="report" varStatus="i">
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
			      						 区间：&nbsp;<ait:date yearName="arReportList5_StartYear" monthName="arReportList5_StartMonth" yearPlus="10" yearMinus="11"/> ~ <ait:date yearName="arReportList5_EndYear" monthName="arReportList5_EndMonth" yearPlus="10" yearMinus="11"/>
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
//打开水晶报表的函数
function PrintReport()
{
	var paMonth = "" ;
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
		// 工厂支给
		if (    reportType == 'report0202' || reportType == 'report0203'
			 || reportType == 'report0204' || reportType == 'report0205'
			 || reportType == 'report0206' || reportType == 'report0207'
		)
		{
			if (reportType == 'report0202'){
				url = "pa_detail_factory" ;
			}else if (reportType == 'report0203'){
				url = "pa_bonus_detail_factory" ;
			}else if (reportType == 'report0204'){
				url = "pa_detail_part_factory" ;
			}else if (reportType == 'report0205'){
				url = "pa_bonus_detail_part_factory" ;
			}else if (reportType == 'report0206'){
				url = "pa_detail_all_factory" ;
			}else if (reportType == 'report0207'){
				url = "pa_bonus_detail_all_factory" ;
			}
			
			paMonth = $("arReportList2_Year").value + $("arReportList2_Month").value ;
			var distinction = "" ;
			
			var deptid = $("arReportList2_deptid").value ;
			var bonusTypeCode = $("arReportList2_bonusTypeCode").value ;
			var bonusNo = $("arReportList2_bonusNo").value ;
			
			var supplyFlag = $("arReportList2_supplyFlag").value ;
			
			var dataArray =  document.getElementsByName("arReportList2_distinction") ;
			var size = dataArray.length ;
			for (var i = 0 ; i < size ; i ++)
			{
				if( dataArray[i].checked )
				{
					distinction += "," + dataArray[i].enValue  ;
				}
			}
			
			url = "<%= admin.getCpnyId() %>_" + url + ".asp?paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo + "&distinction=" + distinction.slice(1)+ "&supplyFlag=" + supplyFlag ;
		}
		// 支社支给
		else if (   reportType == 'report0208' || reportType == 'report0209'
				 || reportType == 'report0210' || reportType == 'report0211'
		)
		{
			if (reportType == 'report0208'){
				url = "pa_detail_filiale" ;
			}else if (reportType == 'report0209'){
				url = "pa_bonus_detail_filiale" ;
			}else if (reportType == 'report0210'){//支社工资支给明细表
				url = "pa_detail_all_filiale" ;
			}else if (reportType == 'report0211'){
				url = "pa_bonus_detail_all_filiale" ;
			}
			
			paMonth = $("arReportList3_Year").value + $("arReportList3_Month").value ;
			
			var deptid = $("arReportList3_deptid").value ;
			var bonusTypeCode = $("arReportList3_bonusTypeCode").value ;
			var bonusNo = $("arReportList3_bonusNo").value ;
			
			url = "<%= admin.getCpnyId() %>_" + url + ".asp?paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo ;
		}
		// 工资单
		else if (   reportType == 'report0213' || reportType == 'report0217')
		{
			var arReportList4_tableName = $("arReportList4_tableName").value
			
			if (reportType == 'report0213'){
				if (arReportList4_tableName == 'PA_HISTORY'){
					url = "pa_payslip_factory" ;
				}
				else if (arReportList4_tableName == 'PA_BONUS_HISTORY'){
					url = "pa_bonus_payslip_factory" ;
				}
				else {
					url = "pa_payslip_factory2" ;
				}
			}else if (reportType == 'report0217'){
				if (arReportList4_tableName == 'PA_HISTORY'){
					url = "pa_payslip_filiale" ;
				}
				else if (arReportList4_tableName == 'PA_BONUS_HISTORY'){
					url = "pa_bonus_payslip_filiale" ;
				}
				else {
					url = "pa_payslip_filiale2" ;
				}
			}
			
			paMonth = $("arReportList4_Year").value + $("arReportList4_Month").value ;
			
			var empId = $("arReportList4_empId").value ;
			var deptid = $("arReportList4_deptid").value ;
			var bonusTypeCode = $("arReportList4_bonusTypeCode").value ;
			var bonusNo = $("arReportList4_bonusNo").value ;
			var emailFlag = $("arReportList4_email").value ;
			var supplyFlag = $("arReportList4_supplyFlag").value ;
			url = "<%= admin.getCpnyId() %>_" + url + ".asp?paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo + "&empId=" + empId + "&emailFlag=" + emailFlag + "&supplyFlag=" + supplyFlag ;
		}else 
		{
			alert("此报表不支持打印功能!!!!") ;
			return ;
		}
		
		document.rpt.rptUrl.value = "http://127.0.0.1:8099/" + url ;
	
		//alert(url) ;
		//window.open("http://127.0.0.1:8099/" + url,"","top=10,left=50,width=1024,height=768, scrollbars=yes, resizable=yes,toolbar=no,titlebar=no,location=no,location=no,menubar=no");
		window.open("http://10.40.128.28:8099/" + url,"","top=10,left=50,width=1024,height=768, scrollbars=yes, resizable=yes,toolbar=no,titlebar=no,location=no,location=no,menubar=no");
	}
}

//打开excel的函数
function ImportExcel(){
    var paMonth = "" ;
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
		//职位别起点工资
		if (reportType == 'report0201') 
		{
			var year = $(reportType + "_year").value
			url = "/reports/pa_report/pa_position_wage.jsp?year=" + year ;
		}
		else if (reportType == 'report0221')//人员区分人件费统计
		{
			var year = $(reportType + "_year").value
			url = "/reportControlServlet?operation=" + reportType + "&year=" + year ;
		}
		else if (reportType == 'report0222')//薪资类别人件费统计
		{
			var year = $(reportType + "_year").value
			url = "/reportControlServlet?operation=" + reportType + "&year=" + year ;
		}
		else if (reportType == 'report0223')//工人工资明细
		{
			paMonth = $(reportType + "_Year").value + $(reportType + "_Month").value ;
			var deptid = $(reportType + "_deptid").value ;
			
			url = "/reportControlServlet?operation=" + reportType + "&paMonth=" + paMonth + "&deptid=" + deptid ;
		}
		else if (reportType == 'report0224')//离职工人公积金明细
		{
			paMonth = $(reportType + "_Year").value + $(reportType + "_Month").value ;
			var deptid = $(reportType + "_deptid").value ;
			
			url = "/reportControlServlet?operation=" + reportType + "&paMonth=" + paMonth + "&deptid=" + deptid ;
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
		else if (reportType == 'report0227' || reportType == 'report0228' || reportType == 'report0229' || reportType == 'report0230' || 
		         reportType == 'report0231' || reportType == 'report0232' || reportType == 'report0233' || reportType == 'report0234' )//
		{
			paMonth = $("arReportList7_Year").value + $("arReportList7_Month").value ;
			
			
			url = "/reportControlServlet?operation=" + reportType + "&paMonth=" + paMonth  ;
		}
		// 工厂支给
		else if (    reportType == 'report0202' || reportType == 'report0203'
				  || reportType == 'report0204' || reportType == 'report0205'
				  || reportType == 'report0206' || reportType == 'report0207'
		)
		{
			if (reportType == 'report0202'){
				url = "pa_detail_factory" ;
			}else if (reportType == 'report0203'){
				url = "pa_bonus_detail_factory" ;
			}else if (reportType == 'report0204'){
				url = "pa_detail_part_factory" ;
			}else if (reportType == 'report0205'){
				url = "pa_bonus_detail_part_factory" ;
			}else if (reportType == 'report0206'){
				url = "pa_detail_all_factory" ;
			}else if (reportType == 'report0207'){
				url = "pa_bonus_detail_all_factory" ;
			}
			
			paMonth = $("arReportList2_Year").value + $("arReportList2_Month").value ;
			var distinction = "" ;
			
			var deptid = $("arReportList2_deptid").value ;
			var bonusTypeCode = $("arReportList2_bonusTypeCode").value ;
			var bonusNo = $("arReportList2_bonusNo").value ;
			
			var dataArray =  document.getElementsByName("arReportList2_distinction") ;
			var supplyFlag = $("arReportList2_supplyFlag").value ;
			
			var size = dataArray.length ;
			for (var i = 0 ; i < size ; i ++)
			{
				if( dataArray[i].checked )
				{
					distinction += "," + dataArray[i].value  ;
				}
			}
			
			
			url = "/reportControlServlet?operation=" + reportType + "&paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo + "&distinction=" + encodeURIComponent(distinction.slice(1))+"&supplyFlag="+supplyFlag ;
		}		
				
		// 支社支给
		else if (   reportType == 'report0208' || reportType == 'report0209'
				 || reportType == 'report0210' || reportType == 'report0211'
		)
		{
			if (reportType == 'report0208'){
				url = "pa_detail_filiale" ;
			}else if (reportType == 'report0209'){
				url = "pa_bonus_detail_filiale" ;
			}else if (reportType == 'report0210'){
				url = "pa_detail_all_filiale" ;
			}else if (reportType == 'report0211'){
				url = "pa_bonus_detail_all_filiale" ;
			}
			
			paMonth = $("arReportList3_Year").value + $("arReportList3_Month").value ;
			
			var deptid = $("arReportList3_deptid").value ;
			var bonusTypeCode = $("arReportList3_bonusTypeCode").value ;
			var bonusNo = $("arReportList3_bonusNo").value ;
			
			url = "/reportControlServlet?operation=" + reportType + "&paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo ;
		}
		else if( reportType == 'report0219' || reportType == 'report0220' )
		{
			if (reportType == 'report0219'){
				url = "pa_detail_factory_average" ;
			}else if (reportType == 'report0220'){
				url = "pa_detail_filiale_average" ;
			}
			
			var paStartMonth = $("arReportList5_StartYear").value + $("arReportList5_StartMonth").value;
			var paEndMonth = $("arReportList5_EndYear").value + $("arReportList5_EndMonth").value; 
			
			url = "/reportControlServlet?operation=" + reportType + "&paStartMonth=" + paStartMonth + "&paEndMonth=" + paEndMonth ;
		}
		else if ( reportType == 'report0212' || reportType == 'report0226')
		{
			url = 'pa_detail_no_email'
			
			var tableName = $("arReportList6_tableName").value ;
			var deptid = $("arReportList6_deptid").value ;
			var bonusTypeCode = $("arReportList6_bonusTypeCode").value ;
			var bonusNo = $("arReportList6_bonusNo").value ; 
			paMonth = $("arReportList6_Year").value + $("arReportList6_Month").value ;
			var supplyFlag = $("arReportList6_supplyFlag").value ;
			
			if ( tableName == 'PA_HISTORY' )
			{
				url = "/reportControlServlet?operation=" + reportType + "&paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=BonusType&bonusNo=0&noEmail=" + tableName + "&supplyFlag=" + supplyFlag  ;
			}
			else if ( tableName == 'PA_REPLENISHMENT_HISTORY' )
			{
				//url = "/reportControlServlet?operation=pa_detail_replenishment&paMonth=" + paMonth + "&deptid=" + deptid + "&statTypeCode=" +  statTypeCode + "&retroactiveTaxType=RetroactiveTaxType01&noEmail=" + tableName  ;
			}
			else if ( tableName == 'PA_BONUS_HISTORY' )
			{
				url = "/reportControlServlet?operation=" + reportType + "B&paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo + "&noEmail=" + tableName  ;
			}
						
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
     	var pars = "operation=paSearchEmployeeAll&condition=" + encodeURIComponent(condition);
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
			  