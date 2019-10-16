
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="java.util.*"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.util.*"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%
String evPeriodId="";
String evTypeId="";
String flag="";
String menu_code="";
String evDeptId="";
String RedirectURL="";
String cpnyid = admin.getCpnyId();
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evPeriodId="+evPeriodId+"&evDeptId="+evDeptId+"&evTypeId="+evTypeId;

if(flag.equals("appendMaster")){
	List lEvsEmp=(List)session.getAttribute("evsEmpList");
	EvsEmp evsEmp=new EvsEmp();
	try{
		if(lEvsEmp!=null){
			int lEvsEmpSize=lEvsEmp.size();
			String[] evProcessId = request.getParameterValues("evProcessId");
			String[] evMaster = request.getParameterValues("evMaster");
			for(int i = 0 ; i < evMaster.length ; i++){
				if(evMaster[i].indexOf("-")>0){
					evMaster[i] = evMaster[i].substring(0,evMaster[i].indexOf("-"));
				}
			}
			for(int i=0;i<lEvsEmpSize;i++){
				EvsEmp evsEmp_i=(EvsEmp)lEvsEmp.get(i);
				String evEmpId="";
				String evPeriod_i="";
				evEmpId=evsEmp_i.getEvEmpID();
				evPeriod_i=evsEmp_i.getEvPeriodID();
				List lEvMaster=new Vector();
				if (evProcessId != null&& evMaster != null) {
 					for (int m = 0; m < evProcessId.length; m++) {
						EvsMaster evsMaster=new EvsMaster();
                        evsMaster.setEvProcessID(evProcessId[m]);
                        evsMaster.setEvPeroidID(evPeriod_i);
                        evsMaster.setEvEmpID(evEmpId);
                        //如果是为评价者为SELF那评价者为自己
                        if (evMaster[m].equals("self")) {
							evsMaster.setEvMaster(evEmpId);
                        } else {
                            evsMaster.setEvMaster(evMaster[m]);
                        }
                        lEvMaster.add(evsMaster); 
                   }
               }
               evsEmp_i.setEvsMaster(lEvMaster);
			}
			evsEmp.addEvsEmp(lEvsEmp,cpnyid);
		}
	}catch(Exception e){
	}
	finally{
		session.removeAttribute("evsEmpList");
		response.sendRedirect(RedirectURL);
	}
}
if(flag.equals("update")){
  
	String empId = (request.getParameter("empId") != null) ? request.getParameter("empId").trim(): "";
	
	EvsEmp evEmp=new EvsEmp(empId,evPeriodId);
	evEmp.getEvEmpByEmpIdPeriod();
	EvsEmp evEmpNew=new EvsEmp(empId,evPeriodId);
	String[] check = request.getParameterValues("check");
	List lEvMaster=new Vector();
	try{
		evEmpNew.setEvDeptID(evDeptId);
		evEmpNew.setEvTypeID(evTypeId);
		if (check != null) {
		        for (int i = 0; i < check.length; i++) {
		                String evProcessId = "";
		                String evMaster = "";
		                evProcessId = (request.getParameter("evProcessId"+ check[i]) != null) ? request.getParameter("evProcessId"+ check[i]).trim(): "";
		                evMaster = (request.getParameter("evMaster"+ check[i]) != null) ? request.getParameter("evMaster"+ check[i]).trim(): "";
		                if (!evProcessId.equals("")&&!empId.equals("")&&!evPeriodId.equals("")) {
		                        EvsMaster evsMaster = new EvsMaster();
		                      	evsMaster.setEvProcessID(evProcessId);
		                        evsMaster.setEvPeroidID(evPeriodId);
		                        evsMaster.setEvEmpID(empId);
		                        //如果是为评价者为SELF那评价者为自己
		                        
		                        if (evMaster.equals("self")) {
									evsMaster.setEvMaster(empId);
		                        } else {
		                            evsMaster.setEvMaster(evMaster);
		                        }
		                        lEvMaster.add(evsMaster); 
						}
				}
		}
		evEmpNew.setEvsMaster(lEvMaster);
		evEmp.updateEvEmp(evEmpNew,cpnyid);
	}catch(Exception e){
		e.printStackTrace();
	}finally{	
		response.sendRedirect(RedirectURL);
	}
}
if(flag.equals("del")){
  
	String empid2 = (request.getParameter("ID") != null) ? request.getParameter("ID").trim(): "";
	String evPeriodId2=(request.getParameter("ID2") != null) ? request.getParameter("ID2").trim(): "";
	try{
		EvsEmp evsEmp=new EvsEmp();
		evsEmp.delEvsEmp(empid2,evPeriodId2);
		RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evPeriodId="+evPeriodId2+"&evDeptId="+evDeptId+"&evTypeId="+evTypeId;
		
	}catch(Exception e){
	}finally{	
		response.sendRedirect(RedirectURL);
	}
}
%>
