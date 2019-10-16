<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.ess.dao.EssDAO" %>
<%@ page import="com.ait.sqlmap.util.ObjectBindUtil" %>
<%@ page import="com.ait.hrm.bean.PersonalInfo" %>
<%@ page import="com.ait.core.exception.*" %>
<%@ page import="org.apache.log4j.Logger" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%
	String menu_code="ess0510";
	try{
		PersonalInfo personalInfo=new PersonalInfo();
		ObjectBindUtil.setFormBean(request,personalInfo);
		personalInfo.setUpdatedBy(admin.getAdminID());
		EssDAO essdao = new EssDAO();
		essdao.affimPersonalInfoApply(personalInfo);
	}catch(Exception e){
		Logger.getLogger(getClass()).error(e.toString()+" 基本信息决裁失败 ");
	    throw new GlRuntimeException("基本信息决裁失败!", e);
	}
	response.sendRedirect("/ess/ess0510.jsp?menu_code="+menu_code);
%>
