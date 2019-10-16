<%@page import="com.ait.util.StringUtil"%>
<%@page import="com.ait.util.NumberUtil"%>

<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>

<%
String evDeptId = StringUtil.checkNull(request.getParameter("evDeptId"));
String evTypeId = StringUtil.checkNull(request.getParameter("evTypeId"));
String evPeriodId = StringUtil.checkNull(request.getParameter("evPeriodId"));
String RedirectURL="evs0305.jsp?menu_code=evs0305&evDeptId="+evDeptId+"&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;



int lEvsEmpSize = NumberUtil.parseInt(request.getParameter("lEvsEmpSize"),0);
System.out.println(evPeriodId+evTypeId+"+++++"+lEvsEmpSize+"---");
int ok=0;
try{
	for(int i = 0 ; i < lEvsEmpSize ; i++){
	    String evEmpId = StringUtil.checkNull(request.getParameter("ck_"+i));
	    ///System.out.println(evTypeId);
		
	    if(evEmpId != null && !evEmpId.equals("")){
	       EvsEmp evsEmp=new EvsEmp(evEmpId,evPeriodId);
	       ok = evsEmp.deltetEvsEmpInfo();  
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
			alert("删除失败");
		</script>
<%	
		}else{
%>
		<script type="text/javascript">
			alert("删除成功");
		</script>
<%	
		}		
	response.sendRedirect(RedirectURL);
}

%>
