
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsScore"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%


String adminId="";
if(admin.getAdminID()!=null){
	adminId=admin.getAdminID();
}
String flag="";
String menu_code="";
String RedirectURL="";
String evPeriodId="";
String evDeptId="";
EvsEmp evsEmp=null;
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evDeptId="+evDeptId+"&evPeriodId="+evPeriodId;


//人事确认
if(flag.equals("save")){
	try{
		if(!evPeriodId.equals("")){
		    evsEmp=new EvsEmp("",evPeriodId);
			String [] aEvEmpId=request.getParameterValues("evEmpId");
			String [] aEvGradeId=request.getParameterValues("evEmpGradeId");
			
			if(aEvEmpId!=null&&aEvGradeId!=null){
				for(int i=0;i<aEvEmpId.length;i++){
					if(!aEvEmpId[i].equals("")&&!aEvGradeId[i].equals("")){
						evsEmp.updateEvsEmp(aEvGradeId[i],aEvEmpId[i],adminId);
					}
				}
			}
		}
		
	}catch(Exception e){
	}finally{
		response.sendRedirect(RedirectURL);
	}
}
//人事确认提交
if(flag.equals("submit")){
	try{
		EvsScore evsScore = new EvsScore();
		if(!evPeriodId.equals("")){
			String [] aEvEmpId=request.getParameterValues("evEmpId");
			if(aEvEmpId!=null){
				for(int i=0;i<aEvEmpId.length;i++){
					if(!aEvEmpId[i].equals("")){
						evsScore.evsSubmit(evPeriodId,aEvEmpId[i]);
					}
				}
			}
		}
	}catch(Exception e){
	}finally{
		response.sendRedirect(RedirectURL);
	}
}
%>
