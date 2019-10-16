<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.ess.dao.EssDAO" %>
<%@ page import="com.ait.sqlmap.util.ObjectBindUtil" %>
<%@ page import="com.ait.hrm.bean.PersonalInfo" %>
<%@ page import="com.ait.core.exception.*" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="com.ait.i18n.*" %>
<%@ page import="com.ait.sy.bean.AdminBean" %>
com.ait.sy.bean
<% 
	String menu_code="ess0301";
 AdminBean admin = (AdminBean) session.getAttribute("admin");
 MessageSource messageSource = new MessageSource("ess",admin.getLocale(), "UTF-8");
String msg = messageSource.getMessage("alert.ess.overtime.awaiting_approval");
	try{
		PersonalInfo personalInfo=new PersonalInfo();
		ObjectBindUtil.setFormBean(request,personalInfo);
		EssDAO essdao = new EssDAO();   
		essdao.addEssPersonalInfo(personalInfo);
	}catch(Exception e){
		Logger.getLogger(getClass()).error(e.toString());
	    msg = "员工基本信息申请失败!";
	    throw new GlRuntimeException("员工基本信息申请失败!", e);
	}
	request.setAttribute("alert", msg);
	request.setAttribute("url","/ess/ess0301.jsp?menu_code="+menu_code);
	request.getRequestDispatcher("/inc/alertMessage.jsp").forward(request,response);
	    
%>
                   