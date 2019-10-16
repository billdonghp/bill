<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<%@ page
	import="com.ait.sy.dao.*,
			com.ait.sy.bean.*,
			com.ait.util.*"
%>
<% 
	String menu_code = request.getParameter("menu_code")!=null?request.getParameter("menu_code"):"";
	String affirmtypeId = request.getParameter("affirmtypeId")!=null?request.getParameter("affirmtypeId"):"";
	String cpnyIdGz = request.getParameter("cpnyIdGz")!=null?request.getParameter("cpnyIdGz"):"";
	
	String typeIdGz = request.getParameter("typeIdGz")!=null?request.getParameter("typeIdGz"):"";
	
	String affirmor = request.getParameter("affirmorid")!=null?request.getParameter("affirmorid"):"Replacement";
    AffirmDAONew affirmdao = new AffirmDAONew();
	Affirm affirm = new Affirm();
	AdminBean adminBean = new AdminBean();
	adminBean = (AdminBean)session.getAttribute("admin");
	affirm.setCreatedBy(adminBean.getAdminID());
	affirm.setAffirmorID(affirmor);
	
	affirm.setAffirmTypeID(affirmtypeId);
	affirm.setCpnyIdGz(cpnyIdGz);
	affirm.setTypeIdGz(typeIdGz);
	if(!affirm.getAffirmTypeID().equals("") && ! affirm.getCpnyIdGz().equals("")){
		affirmdao.updateAffirmRelation(affirm);
	}else if(affirm.getAffirmTypeID().equals("")||  affirm.getCpnyIdGz().equals("")){
		System.out.println("missed parameters");
	}
    response.sendRedirect("/sy/sy0140_a_new.jsp?menu_code="+menu_code+"&typeIdGz="+typeIdGz+"&cpnyIdGz="+cpnyIdGz+"&affirmtypeId="+affirmtypeId);
%>