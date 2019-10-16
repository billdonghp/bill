<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="employeeListForFlag" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>


<html>
<head>
<!-- pa0106.jsp  工资系统 > 工资查看 > 部门别工资支给明细   -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>部门别工资支给明细查看</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript"> 
function Search(){
	document.form1.action="/paControlServlet?operation=paDetailView&content=B_viewDetail&&menu_code=${param.menu_code}";
	document.form1.submit();
}
function ImportExcel()
{
	document.form1.action = "/paControlServlet?operation=paDetailView&content=ImportExcelDepartment&menu_code=${param.menu_code}";
    document.form1.submit(); 
}
</SCRIPT>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_none.jsp"%>
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
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				部门别工资支给明细查看
				</td>
			</tr>  
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>		 
		<form action="" name="form1" method="post">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >		
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	      <tr>
	        <td align="center" class="info_content_01" nowrap="nowrap">
	      		部门:&nbsp;
	      		<ait:selDept name="dept" supervisorType="pa" selected="${dept}"/>        
	        </td>      
	        <td align="center" class="info_content_01" nowrap="nowrap">
	        	<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="startyear" monthName="startmonth" yearSelected="${startyear}" monthSelected="${startmonth}" yearPlus="10" />
	        	&nbsp;--&nbsp;<ait:date yearName="endyear" monthName="endmonth" yearSelected="${endyear}" monthSelected="${endmonth}" yearPlus="10" />
	        </td>	        
		    <td align="center" class="info_content_01" nowrap="nowrap">
				<ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />	
				<ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel();" style="cursor:hand"/>			
		    </td>
	        </tr>
	      </table>	      
	      </td>
		</tr>
		</table>
		</form>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr>
		<td class="info_title_01" nowrap >部门编号</td>
		<td class="info_title_01" nowrap >部门名称</td>
		<td class="info_title_01" nowrap >平均工资</td>
		<td class="info_title_01" nowrap >人数</td>	
		</tr>
		<c:forEach items="${basicList}" var="basic" varStatus="y">
		<td class="info_content_01" nowrap >&nbsp;${basic.DEPTID}</td>
		<td class="info_content_01" nowrap >&nbsp;${basic.DEPARTMENT}</td>
		<td class="info_content_01" nowrap >&nbsp;${basic.AVG_THIS_ACTUAL_WAGE}</td>
		<td class="info_content_01" nowrap >&nbsp;${basic.COUNTEMPID}</td>		
		</c:forEach>
		</table>
		<form action="" name="form2" method="post"> 
		<input name="ck_all" value="0" type="hidden"/>		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr>
		            <td class="info_title_00" nowrap >工资月</td>	
		            <td class="info_title_00" nowrap >类别</td>	
		            <td class="info_title_00" nowrap >人数</td>		          
					<td class="info_title_00" nowrap >基本工资</td>
		            <td class="info_title_00" nowrap >职务津贴</td>
				    <td class="info_title_00" nowrap >住宅补助</td>
		            <td class="info_title_00" nowrap >特殊补助</td>
					<td class="info_title_00" nowrap >基本合计</td>
		            <td class="info_title_00" nowrap >业绩工资2</td>
		            <td class="info_title_00" nowrap >业绩工资3</td>
		            <td class="info_title_00" nowrap >其他支给</td>
		            <td class="info_title_00" nowrap >支给错误</td>
		            <td class="info_title_00" nowrap >夜班补助</td>
		            <td class="info_title_00" nowrap >值班补助</td>
		            <td class="info_title_00" nowrap >迟早减除</td>
		            <td class="info_title_00" nowrap >未勤减除</td>
		            <td class="info_title_00" nowrap >事病减除</td>
		            <td class="info_title_00" nowrap >休假减除</td>
		            <td class="info_title_00" nowrap >休职减除</td>
		            <td class="info_title_00" nowrap >旷工减除</td>
					<td class="info_title_00" nowrap >其他减除</td>
		            <td class="info_title_00" nowrap >住宅减除</td>
		            <td class="info_title_00" nowrap >减除错误</td>
		            <td class="info_title_00" nowrap >放假减除</td>
		            <td class="info_title_00" nowrap >试用扣除</td>
		            <td class="info_title_00" nowrap >支给合计</td>
		            <td class="info_title_00" nowrap >养老保险</td>
		            <td class="info_title_00" nowrap >医疗保险</td>
		            <td class="info_title_00" nowrap >待业保险</td>
		            <td class="info_title_00" nowrap >公积金</td>
		            <td class="info_title_00" nowrap >所得税</td>
		            <td class="info_title_00" nowrap >减除合计</td>
		            <td class="info_title_00" nowrap >实领工资</td>
		          </tr>
		          <c:forEach items="${viewDetailList}" var="test" varStatus="i">
		          <tr>
		            <td class="info_content_00" nowrap >${test.PA_MONTH}</td>
		            <td class="info_content_00" nowrap >${test.BONUS_TYPE}</td>
		            <td class="info_content_00" nowrap >${test.COUNTEMPID}</td>		            
					<td class="info_content_00" nowrap >${test.BASE_PAY}</td>
		            <td class="info_content_00" nowrap >${test.DUTIES_ALLOWANCE}</td>
				    <td class="info_content_00" nowrap >${test.RESIDENTIAL_GRANTS}</td>
		            <td class="info_content_00" nowrap >${test.SPECIAL_GRANTS}</td>
					<td class="info_content_00" nowrap >${test.TOTAL_BASIC}</td>
		            <td class="info_content_00" nowrap >${test.PERFORMANCE_PAY2}</td>
		            <td class="info_content_00" nowrap >${test.PERFORMANCE_PAY3}</td>
		            <td class="info_content_00" nowrap >${test.TO_THE_OTHER}</td>
		            <td class="info_content_00" nowrap >${test.STICKS_TO_THE_WRONG}</td>
		            <td class="info_content_00" nowrap >${test.NIGHT_DUTY_SUBSIDY}</td>
		            <td class="info_content_00" nowrap >${test.DUTY_SUBSIDIES}</td>
		            <td class="info_content_00" nowrap >${test.LATE_EARLY_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.NOT_ATTENDANCE_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.LEAVE_SICK_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.VACATION_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.LEVEL_OFF_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.ABSENTEEISM_MINUS}</td>
					<td class="info_content_00" nowrap >${test.OTHER_LESS}</td>
		            <td class="info_content_00" nowrap >${test.RESIDENTIAL_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.REDUCE_ERRORS}</td>
		            <td class="info_content_00" nowrap >${test.HOLIDAY_MINUS_ALL}</td>
		            <td class="info_content_00" nowrap >${test.TRIAL_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.THIS_TOTAL_SUPPORT}</td>
		            <td class="info_content_00" nowrap >${test.PERSONAL_PENSION}</td>
		            <td class="info_content_00" nowrap >${test.PERSONAL_MEDICAL}</td>
		            <td class="info_content_00" nowrap >${test.PERSONAL_UNEMPLOYED}</td>
		            <td class="info_content_00" nowrap >${test.PERSONAL_HOUSING_FUND}</td>
		            <td class="info_content_00" nowrap >${test.THIS_ACTUAL_TAX}</td>
		            <td class="info_content_00" nowrap >${test.TOTAL_DEDUCTIONS}</td>
		            <td class="info_content_00" nowrap >${test.THIS_ACTUAL_WAGE}</td>
		          </tr>
		          </c:forEach>
		</table>
		</form>
		
		 <!-- Page Navigation Start-->
					<ait:page       
		               link="/paControlServlet"
		               parameters="&operation=paDetailView&content=B_viewDetail&menu_code=${param.menu_code}&startyear=${startyear}&startmonth=${startmonth}&endyear=${endyear}&endmonth=${endmonth}&dept=${dept}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/> 
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
<iframe id='iemp' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:200;width:370; height:210; z-index:2;visibility: hidden;">   
</div>
</body>

</html>
