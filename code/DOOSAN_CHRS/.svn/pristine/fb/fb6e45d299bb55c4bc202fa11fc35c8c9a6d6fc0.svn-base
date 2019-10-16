<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.hrm.bean.*"%>
<%@ page import="com.ait.hrm.business.*"%>
<%@ page import="com.ait.hrm.entity.*"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsDept"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="department" class="com.ait.hrm.bean.Department" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<jsp:useBean id="employee" class="com.ait.hrm.bean.EmployeeBean" />
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%
String evTypeId="";
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
String evPeriodId="";
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;

EvsEmp evEmp=new EvsEmp();
List evsEmpList=null;
evsEmpList=(List)session.getAttribute("evsEmpList");
if(evsEmpList!=null){
	session.removeAttribute("evsEmpList");
}
HrmServices hrSevrices=HrmServices.getInstance();
List dept_list = new ArrayList();
List emp_list = new ArrayList();
String deptid = "";
String condition = (request.getParameter("condition") != null) ? request.getParameter("condition"): "";;
deptid=(request.getParameter("deptid") != null) ? request.getParameter("deptid"): "";
try {
	emp_list = evEmp.getEmployeeList(deptid, condition, evPeriodId);
	
}catch(Exception e){ e.printStackTrace() ; }

//评价期间评价类型

EvsType evsType=new EvsType(evTypeId,"");
EvsPeriod evsPeriod=new EvsPeriod(evPeriodId);
EvsDept evsDept=new EvsDept();
List lEvsPeriod=null;
List lEvsType=null;
List lEvsDept=null;
if(evPeriodId.equals("")){
			try{
				evPeriodId=evsPeriod.getCurrentEvPeriod();
			}catch(Exception e){}	
		}
try{
lEvsPeriod=evsPeriod.getEvsPeriodByYear("");
lEvsType=evsPeriod.getEvTypeByEvPeriodId(evPeriodId);
lEvsDept = evsPeriod.getEvsDeptid();
}catch(Exception e){}    

String menu_code = request.getParameter("menu_code");    
%>
<html>
<head>
<title>评价 > 基本设置 > 评价者设置</title>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<table width="99%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="left" valign="middle" style="padding:3 0 3 0 ">
	          		<ait:history />
	         	</td>
				<td  align="right" valign="middle" style="padding:3 0 3 0 ">
					<img src="/images/btn/master.gif" onclick="javascript:AddSave();" style="cursor:hand"
					    align="absmiddle">
					<img src="/images/btn/Back.gif" onclick="javascript:history.back();" style="cursor:hand"
					    align="absmiddle"> 
				</td>
			</tr>
		</table>
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
		
		<!-- display content -->
<form action="/evs/evs0105_a.jsp?menu_code=evs0105" method="POST" name="evs0105" >
<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
				<ait:message messageID="display.mutual.search_criteria" /></td>
		</tr>
	    <tr>
	      <td colspan="9">
	      	<table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	      		<tr>
			       <td class="info_title_01" >评价区间</td>
			          <td class="info_content_00" >
				          <select name="evPeriodId" onChange="evs0105.submit();">
							<%
							if(lEvsPeriod!=null){
								int lEvsPeriodSize=lEvsPeriod.size();
								for(int i=0;i<lEvsPeriodSize;i++){
									evsPeriod=(EvsPeriod)lEvsPeriod.get(i);
								%>
							<option value="<%=evsPeriod.getEvPeriodID()%>"
								<%if(evPeriodId.equals(evsPeriod.getEvPeriodID())){out.print(" selected ");}%>><%=evsPeriod.getEvPeriodName()%></option>
							<%
								}
							}
							%>
						  </select> 
			          </td>
			          <td class="info_title_01" nowrap>评价类型</td>
		          	  <td class="info_content_00">
		          			<select name="evTypeId">
								<%
								if(lEvsType!=null){
									EvsType evsType_period=new EvsType();
									int lEvsTypeSize=lEvsType.size();
									for(int i=0;i<lEvsTypeSize;i++){
										evsType_period=(EvsType)lEvsType.get(i);
									%>
								<option value="<%=evsType_period.getEvTypeID()%>"
									<%if(evTypeId.equals(evsType_period.getEvTypeID())){out.print(" selected ");}%>><%=evsType_period.getEvTypeName()%></option>
								<%
									}
								}
								%>
							</select>
		          	   </td>
			          <td class="info_content_01" >&nbsp;</td>
				 </tr>
	       		 <tr>
					 <td class="info_title_01"><!--部门 -->
				<ait:message messageID="display.mutual.dept" /></td>
			          <td class="info_content_00">
					<ait:selEvDept name="deptid" evPeriodId="<%=evPeriodId %>" all=" " selectEvDeptId="<%= deptid %>" />
					
			          </td>
			          <!--<ait:selDept name="deptid" selected="${param.deptid}" />
			          --><td class="info_title_01"><!--姓名/工号 -->
				<ait:message messageID="display.mutual.emp_no_name" /></td>
			          <td class="info_content_00"><input id="condition" name="condition" type="text" size="10" value="${param.condition}" ></td>
					  <td class="info_content_01">
					  <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:evs0105.submit();" style="cursor:hand"/></td>
			          
			    </tr>
			</table>
	      </td>
		</tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="6">员工信息</td>
			</tr>
</table>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	
	<tr align="center">
		<td width="10%" height="30" class="info_title_01">全选&nbsp;&nbsp;<input type="checkbox"
			name="checkall" value="all" onClick="selectall()"></td>
		<td width="10%" height="30" class="info_title_01">中文姓名</td>
		<td width="10%" class="info_title_01">工号</td>
		<td width="10%" class="info_title_01">职级</td>
		<td class="info_title_01">部门</td>
	</tr>
	<%if(emp_list.size() > 0){
		
        // show all employees
        for(int i=0; i<emp_list.size();i++){
          employee = (EmployeeBean) emp_list.get(i);
          
          %>
	<tr align="center">
		<td class="info_content_01"><input type="checkbox" name="empid<%=i%>"
			value="<%=employee.getPersonID()%>"></td>
		<td height="30" class="info_content_01">
		<%= employee.getChineseName() %>
		<input name="ev_emp_name<%=i%>" type="hidden"
			value="<%=employee.getChineseName()%>" >
		</td>
		<td class="info_content_01"><%=employee.getEmpID()%>&nbsp;</td>
		<td class="info_content_01"><%=employee.getPostGroupName()%>&nbsp;</td>
		<td class="info_content_01"><%=employee.getDepartment()%>&nbsp;
		<input type="hidden" name="evs_emp_id<%=i%>"
			value="<%=employee.getEmpID()%>">
		<input type="hidden" name="ev_postGroup_id<%=i%>"
			value="<%=employee.getPostGroupId()%>">
		<input type="hidden" name="ev_postGroup_name<%=i%>"
			value="<%=employee.getPostGroupName()%>">
		<input type="hidden" name="ev_dept_id<%=i%>"
			value="<%=employee.getDeptID()%>">
		<input type="hidden" name="ev_dept_name<%=i%>"
			value="<%=employee.getDepartment()%>">	
		</td>
			
	</tr>
	<%}}%>
	<input type="hidden" name="flag" value="appendMaster">
	<input type="hidden" name="empCount" value="<%=emp_list.size()%>">
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
<input type="hidden" name="evTypeName" value="">
<input type="hidden" name="evPeriodName" value="">
</form> 
</body>
</html>
<script language="JavaScript" type="text/javascript" src="">

function selectall()
{
  try{
	  var length=<%=emp_list.size()%>;
	  document.evs0105.checkall.checked = document.evs0105.checkall.checked|0;

	  if ( length== 0 ){
	    return;
	  }
	  if (length ==1 )
	  {
	    document.evs0105.empid0.checked=document.evs0105.checkall.checked ;
	  }
	
	  if (length>1)
	  {
	  	for (var i = 0; i < length; i++)
	    {
	      document.all('empid'+i).checked=document.evs0105.checkall.checked;
	    }
	  }
	}catch(e){alert(e);}
}
function AddSave()
{	
	try{
	  if(checkForm()){
	   document.evs0105.action="/evs/evsProcessList.jsp";
	  document.evs0105.evTypeName.value=document.evs0105.evTypeId[document.evs0105.evTypeId.selectedIndex].text;
	  document.evs0105.evPeriodName.value=document.evs0105.evPeriodId[document.evs0105.evPeriodId.selectedIndex].text;
	  document.evs0105.submit();
	  }
	}catch(e){alert(e);}
}
function checkForm(){
	if(document.evs0105.evPeriodId.value==''){
		alert("请选择评价期间！");
		return false;
	}
	if(document.evs0105.evTypeId.value==''){
		alert("请选择评价类型");
		return false;
	}
	return true;
}
</script>
