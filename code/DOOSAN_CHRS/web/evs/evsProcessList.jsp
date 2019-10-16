<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.hrm.bean.*"%>
<%@ page import="com.ait.hrm.business.*"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%

	String evTypeId = (request.getParameter("evTypeId") != null) ? request.getParameter("evTypeId"): "";
    String evPeriodId = (request.getParameter("evPeriodId") != null) ? request.getParameter("evPeriodId"): "";
    
	//评价项目评价流程
	String evProcessId="";
	EvsProcess evsProcess=null;
	List lEvsProcess=null;
	int lEvsProcessSize=0;
	if(!evTypeId.equals("")&&!evPeriodId.equals("")){
		evsProcess=new EvsProcess(evPeriodId,evTypeId);
		try{
			lEvsProcess=evsProcess.getProcessByTypePeriod();
			//第一个流程
			if(lEvsProcess!=null){
				EvsProcess evp=null;
				evp=(EvsProcess)lEvsProcess.get(0);
				if(evp!=null&&evp.getEvPeriodID()!=null){
					evProcessId=evp.getEvProcessID();
				}
			}
		}catch(Exception e){
			session.removeAttribute("evsEmpList");
		}
	}

	//被评价者列表
	
    List lEvsEmp =null;
    int lEvsEmpSize=0;
    
    String evTypeName = (request.getParameter("evTypeName") != null) ? request.getParameter("evTypeName"): "";
    String evPeriodName= (request.getParameter("evPeriodName") != null) ? request.getParameter("evPeriodName"): "";
 	evTypeName=StringUtil.toCN(evTypeName);
 	evPeriodName=StringUtil.toCN(evPeriodName);
 	
 	String ev_dept_id = "";
    try{
	    int empCount = Integer.parseInt(request.getParameter("empCount"));                 
	    String emp_id = "";
	    String evs_emp_id = "";
	    
		String ev_dept_name="";
	    String ev_emp_name = "";
	    lEvsEmp=new Vector();
	    for (int i = 0; i < empCount; i++) {
	        EvsEmp emp = new EvsEmp();
	        emp_id = (request.getParameter("empid" + i) != null) ? request.getParameter("empid" + i): "";
	        evs_emp_id = (request.getParameter("evs_emp_id" + i) != null) ? request.getParameter("evs_emp_id" + i): "";
	        if (!emp_id.equals("")) {
	            ev_dept_id = (request.getParameter("ev_dept_id"+ i) != null) ? request.getParameter("ev_dept_id"+ i): "";
	            ev_dept_name = (request.getParameter("ev_dept_name"+ i) != null) ? request.getParameter("ev_dept_name"+ i): "";
	            ev_emp_name = (request.getParameter("ev_emp_name"+ i) != null) ? request.getParameter("ev_emp_name"+ i): "";
	            if (!emp_id.equals("")&& !ev_dept_id.equals("")&& !evTypeId.equals("")&& !evPeriodId.equals("")) {
	                emp.setEvEmpID(emp_id);
	                emp.setEvEmpID2(evs_emp_id);
	                emp.setEvEmpName(StringUtil.toCN(ev_emp_name));
	                emp.setEvDeptID(ev_dept_id);
	                emp.setEvDeptName(StringUtil.toCN(ev_dept_name));
	                emp.setEvPeriodID(evPeriodId);
					emp.setEvTypeID(evTypeId);
					emp.setEvCurrentProcessID(evProcessId);
					emp.setEvPostGroupID("");
					emp.setEvPostGroupName("");
	                lEvsEmp.add(emp);
	                lEvsEmpSize++;
	            }
	        }
    }
    
    	session.setAttribute("evsEmpList",lEvsEmp);
    }catch(Exception e){
    	session.removeAttribute("evsEmpList");
    }
    
    
    //部门列表
	ArrayList dept_list=null;
	HrmServices hrServices = HrmServices.getInstance();
  	try{
  	    String cpnyid = admin.getCpnyId();
 		dept_list = (ArrayList) hrServices.getAllDept(cpnyid);
 	}catch(Exception e){
 		session.removeAttribute("evsEmpList");
 	}
%>
<html>
<head>
<title></title>
</head>
<link href="/css/default.css" rel="stylesheet" type="text/css"> 
<%if (lEvsEmpSize > 0) {%>

<div id="inner_toolbar">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="99%" align="right" valign="middle"
					style="padding:3 0 3 0 ">
					<img src="/images/btn/Save.gif"  align="absmiddle"
					onclick="javascript:AddSave();" style="cursor:hand">
					<img src="/images/btn/Back.gif"  align="absmiddle"
					onclick="javascript:location.href='/evs/evs0105_a.jsp?menu_code=evs0105'" style="cursor:hand"
					></td>
			</tr>
		</table>
</div>
<%
  } else {
    out.print("<script type='text/javascript'>alert('请先选择被评价人员');history.back();</script>");
  }
%>
<table width="100%" border="1" cellpadding="0" cellspacing="0"
	width="100%" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	<tr>
		<td colspan="8" class="info_title_01" height="30">被评价人员列表</td>
	</tr>
	<tr>
		<%
    for (int i = 0; i < lEvsEmpSize; i++) {
     EvsEmp evsEmp = (EvsEmp)lEvsEmp.get(i);
      out.print("<td class=\"info_content_00\">" + evsEmp.getEvEmpName() + "(" + evsEmp.getEvEmpID2() + ")</td>");
      if ((i + 1) % 8 == 0 && (i + 1) !=lEvsEmpSize) {
        out.print("</tr><tr>");
      }
    }
  %>
	<tr>
		<td class="info_content_00" colspan="8">评价类型--<%=evTypeName%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		评价区间--<%=evPeriodName%></td>
	</tr>
</table>
<form name="evsProcessMaster" action="" method="POST">
<input type="hidden" name="flag" value="appendMaster"> 
<input type="hidden" name="menu_code" value="ev0105"> 
<input type="hidden" name="evPeriodId" value="<%=evPeriodId%>"> 
<input type="hidden" name="evTypeId" value="<%=evTypeId%>"> 
<input type="hidden" name="evDeptId" value="<%=ev_dept_id%>">  
<table width="98%">
	<%if (lEvsProcess!=null) {
	lEvsProcessSize=lEvsProcess.size();
%>
	<tr>
		<td colspan="2"  class="info_title_01">评价者选择 
		<input type="reset" name="重选" value="重选">
	</tr>
	<tr>
		<td width="50%" class="info_content_01">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr >
							<td  class="info_title_01">评价流程</td>
							<td  class="info_title_01">操作者</td>

			</tr>
			<%
	for(int i=0;i<lEvsProcessSize;i++){
		EvsProcess evsProcess_tr=(EvsProcess)lEvsProcess.get(i);
		
	%>
			<tr>
				<td class="info_title_01"><%=evsProcess_tr.getEvProcessName()%></td>

				<td class="info_content_01">
				<input type="hidden" name="evProcessId"
					value="<%=evsProcess_tr.getEvProcessID()%>"> 
				<input type="text"
					name="evMaster" value="" onFocus="getValue(this);"></td>
			</tr>
			<%}%>
		</table>
		</td>
		<td class="info_content_01">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr>
				<td class="info_title_01"><select name="master_dept_id"
					onChange="evsMasterListI.location.href='/evs/evsMasterList.jsp?deptid='+this.value;"
					style="width：:80">
					<option>请选择部门</option>
					<%
          String deptID = request.getParameter("deptid");
          for (int i = 0; i < dept_list.size(); i++) {
           Department department = (Department) dept_list.get(i);
           
           
        %>
					<option value="<%=department.getDeptID()%>" selected>
					<%
                                                      String deptname="";
                                                      int level=department.getDeptLevel();
                                                      for(int j=0;j<level;j++){
                                                        deptname+="　";
                                                      }
                                                      %> <%=deptname+department.getDeptName()%>
					</option>
					<br/>
					<%} %>
				</select></td>
			</tr>
			<tr class="info_title_01">
				<td class="info_content_01" align="left">
				<iframe src="/evs/evsMasterList.jsp" width="210"
					name="evsMasterListI" scrolling="yes" frameborder="0" height="200"
					marginheight="0" marginwidth="0" id="evsMasterListI" bgcolor="#ffffff"> </iframe></td>
			</tr>
		</table>
		</td>
	</tr>
	<%
  }
  	else {
   out.print("<a href='/evs/evs0102.jsp?menu_code=ev0102'>请进行评价流程项目的维护</a>");
 }
%>
</table>
</form> 
</body>
</html>
<script language="JavaScript" type="text/javascript" src="">
      function getValue(t)
      {
        try{

//          if(document.evProcessMaster.Check.checked){
 //           for(i=0;i<document.all(t.id).length;i++){
//
 //             document.all(t.id)[i].value=window.evsMasterListI.evsMasterListForm.evsMasterList.value;
//            }
//          }else{
			
            t.value=evsMasterListI.evsMasterListForm.evsMasterList.value;
//          }
        }catch(e){alert(e);}
      }
      function AddSave()
      {
        try{
          document.evsProcessMaster.action="/evs/evs0105_t.jsp?menu_code=evs0105";
          document.evsProcessMaster.submit();
        }catch(e){
          alert(e);
        }
      }
</script>
