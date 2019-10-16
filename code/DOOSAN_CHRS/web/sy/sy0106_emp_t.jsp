<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<%@ page
	import="com.ait.sy.dao.*,
			com.ait.sy.bean.*,
			com.ait.util.*"
%>
<% 
	String menu_code = request.getParameter("menu_code")!=null?request.getParameter("menu_code"):"";
	String affirmtypeId = request.getParameter("affirmtypeId")!=null?request.getParameter("affirmtypeId"):"";
	String deptcodeId = request.getParameter("deptcodeId")!=null?request.getParameter("deptcodeId"):"";
	String empId = StringUtil.checkNull(request.getParameter("empId"));
	String affirmor = request.getParameter("affirmorid")!=null?request.getParameter("affirmorid"):"Replacement";
    AffirmDAO affirmdao = new AffirmDAO();
	Affirm affirm = new Affirm();
	AdminBean adminBean = new AdminBean();
	adminBean = (AdminBean)session.getAttribute("admin");
	affirm.setCreatedBy(adminBean.getAdminID());
	affirm.setAffirmorID(affirmor);
	String x = request.getParameter("x")!=null?request.getParameter("x"):"1";
	String y= request.getParameter("y")!=null?request.getParameter("y"):"1";  
	if(!empId.equals("")){
		affirm.setAffirmObject(empId);
	}else if(!deptcodeId.equals("")){
		affirm.setAffirmObject(deptcodeId);
	}
	affirm.setAffirmTypeID(affirmtypeId);
	if(!affirm.getAffirmTypeID().equals("") && ! affirm.getAffirmObject().equals("")){
		affirmdao.updateAffirmRelation(affirm);
	}else if(affirm.getAffirmTypeID().equals("")||  affirm.getAffirmObject().equals("")){
		System.out.println("missed parameters");
	}
    response.sendRedirect("/sy/sy0130_a.jsp?menu_code="+menu_code+"&affirmtypeId="+affirmtypeId+"&deptcodeId="+deptcodeId+"&empId="+empId+"&x="+x+"&y="+y);
%>