<%@page import="com.ait.util.StringUtil"%>

<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>

<%
String evDeptId = StringUtil.checkNull(request.getParameter("evDeptId"));
String evTypeId = StringUtil.checkNull(request.getParameter("evTypeId"));
String evPeriodId = StringUtil.checkNull(request.getParameter("evPeriodId"));
String RedirectURL="evs0305.jsp?menu_code=evs0305&evDeptId="+evDeptId+"&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;

String[] evEmpId = request.getParameterValues("evEmpId");
int ok=0;
try{
	for(int i = 0 ; i < evEmpId.length ; i++){
		String evEmpevProcessId = StringUtil.checkNull(request.getParameter("evProcessId_" + evEmpId[i])) ;
		
		if(evEmpevProcessId != null && !evEmpevProcessId.equals("") && !evPeriodId.equals("") && !evEmpId[i].equals("")){
			EvsEmp evsEmp=new EvsEmp(evEmpId[i],evPeriodId);
			ok = evsEmp.updateEvsEmpProcess(evEmpevProcessId);
		}		
	}
}catch(Exception e){
	e.printStackTrace() ;
	ok=4466;
}
finally{
	if(ok==4466){
%>
		<script type="text/javascript">
			alert("修改失败");
		</script>
<%	
		}else{
%>
		<script type="text/javascript">
			alert("修改成功");
		</script>
<%	
		}		
	response.sendRedirect(RedirectURL);
}

%>
