
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.evs.Constants"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsDept"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsScore"%>
<%@ page import="com.ait.evs.EvsDeptDefaultGrade"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%


String adminId="";
if(admin.getAdminID()!=null){
	adminId=admin.getAdminID();
}
String evProcessId="";
String flag="";
String menu_code="";
String RedirectURL="";
String evPeriodId="";
String evDeptId="";
String evTypeId="";
EvsDeptDefaultGrade evsDDG=null;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evTypeId = evTypeId.startsWith(",") ? evTypeId.substring(1,evTypeId.length()) : evTypeId;
evProcessId=request.getParameter("evProcessId")!=null?request.getParameter("evProcessId"):evProcessId;
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evDeptId="+evDeptId+"&evPeriodId="+evPeriodId+"&evProcessId="+evProcessId+"&evTypeId="+evTypeId;

evsDDG=new EvsDeptDefaultGrade(evDeptId,evPeriodId,evProcessId,evTypeId,adminId);

//相对化
if(flag.equals("defaultGrade")){
	try{
			
		  if(evProcessId.equals(Constants.EVPROCESS015)||evProcessId.equals(Constants.EVPROCESS008) || evProcessId.equals(Constants.EVPROCESS009)){
		  	 
			 EvsDept evsDept=new EvsDept();
	         String evDeptGradeId ="";
	         if(evsDept.getDeptGradeIdByDeptPeriod(evPeriodId,evDeptId)!=null){
	         	evDeptGradeId=evsDept.getDeptGradeIdByDeptPeriod(evPeriodId,evDeptId);
	         }
	        if(evDeptGradeId.equals("")||evDeptGradeId==null){
	        	
	        %>
			     <SCRIPT LANGUAGE="JavaScript" type="">
				alert("请先进行部门评价");
				history.back();;
				</SCRIPT>
			<%
	        }else{
				 evsDDG.deptDefaultGrade();
				 response.sendRedirect(RedirectURL);
			}
		//}
		//else if(evProcessId.equals(Constants.EVPROCESS008)){
		//	evsDDG.deptDefaultGrade();
		//	response.sendRedirect(RedirectURL);
		}else{
			response.sendRedirect(RedirectURL);
		}
	}catch(Exception e){
	}
}
//提交
if(flag.equals("submit")){
	try{
		//如果是评价
		if(evProcessId.equals(Constants.EVPROCESS009)){
			EvsScore evsScore = new EvsScore();
		if(!evPeriodId.equals("")){
			
			List lEvEmp=new Vector();
			String [] aEvEmpId=request.getParameterValues("evEmpId");
			String [] aEvGradeId=request.getParameterValues("evEmpGradeId");
			if(aEvEmpId!=null && aEvGradeId!=null){
				
				EvsEmp emp=new EvsEmp();
				for(int i=0;i<aEvEmpId.length;i++){
					if(!aEvEmpId[i].equals("")){
						
						EvsEmp evEmp=new EvsEmp(aEvEmpId[i],evPeriodId);
						evEmp.setEvGradeID(aEvGradeId[i]);
						lEvEmp.add(evEmp);
						
						evsScore.evsSubmit(evPeriodId,aEvEmpId[i]);
					}
				}
				emp.updateEvsEmpGrade(lEvEmp,adminId);
			}
		}
		}else{
			evsDDG.defaultGradeSubmit();
		}
	}catch(Exception e){
	}finally{
		response.sendRedirect(RedirectURL);
	}
}

//更新等级
if(flag.equals("save")){
	try{
		EvsEmp evsEmp=null;
		if(!evPeriodId.equals("")){
		    evsEmp=new EvsEmp("",evPeriodId);
			String [] aEvEmpId=request.getParameterValues("evEmpId");
			String [] aEvGradeId=request.getParameterValues("evEmpGradeId");
			List lEvEmp=new Vector();
			if(aEvEmpId!=null && aEvGradeId!=null){
				EvsEmp emp=new EvsEmp();
				for(int i=0;i<aEvEmpId.length;i++){
					
					EvsEmp evEmp=new EvsEmp(aEvEmpId[i],evPeriodId);
					evEmp.setEvGradeID(aEvGradeId[i]);
					lEvEmp.add(evEmp);
				}
				emp.updateEvsEmpGrade(lEvEmp,adminId);
			}
		}
		if(evProcessId.equals(Constants.EVPROCESS015)||evProcessId.equals(Constants.EVPROCESS008)){
			%>
			 <SCRIPT LANGUAGE="JavaScript" type="">
				
				window.open('evsRate.jsp?evProcessId=<%=evProcessId%>&evDeptId=<%=evDeptId%>&evPeriodId=<%=evPeriodId%>&evTypeId=<%=evTypeId%>','state','toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,width=300,height=80,left=10,top=0');
				
				location.href='<%=RedirectURL%>';
			</SCRIPT>
			<%
		}else{
			%>
			 <SCRIPT LANGUAGE="JavaScript" type="">
				
				location.href='<%=RedirectURL%>';
			</SCRIPT>
			<%
		}
	}catch(Exception e){
	}finally{
		//response.sendRedirect(RedirectURL);
	}
}

%>
