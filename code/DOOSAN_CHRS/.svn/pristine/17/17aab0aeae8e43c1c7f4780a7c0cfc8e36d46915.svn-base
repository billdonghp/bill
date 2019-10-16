<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.util.*,com.ait.ess.dao.*,com.ait.ess.entity.*,java.sql.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<% 
  OTApply OTapply = new OTApply();

  String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
  String emp_length = request.getParameter("emp_length")!=null? request.getParameter("emp_length"):"";
  String adminID = request.getParameter("adminID")!=null? request.getParameter("adminID"):"";
  emp_length = !emp_length.equals("")?emp_length:"0";
  EssDAO essdao  = new EssDAO();
  for (int i=0;i<NumberUtil.parseInt(emp_length); i++){
    String checkbox = request.getParameter("checkbox"+i)!=null? request.getParameter("checkbox"+i):"";
    String empid = request.getParameter("empid"+i)!=null? request.getParameter("empid"+i):"";
    String otdate = request.getParameter("otdate"+i)!=null? request.getParameter("otdate"+i):"";
    String anotherDay = request.getParameter("anotherDay"+i)!=null? request.getParameter("anotherDay"+i):"";
    String deductTime = request.getParameter("deductTime"+i)!=null? request.getParameter("deductTime"+i):"";
    String start_time = request.getParameter("start_time"+i)!=null? request.getParameter("start_time"+i):"";
    String end_time = request.getParameter("end_time"+i)!=null? request.getParameter("end_time"+i):"";
    String OtContent = request.getParameter("OtContent"+i)!=null? request.getParameter("OtContent"+i):""; 
	String otTypeCode = StringUtil.checkNull(request.getParameter("otTypeCode"+i));
	if(request.getParameter("checkbox"+i)!=null){
	OTapply.setOTDate(otdate);
    OTapply.setOTStartTime(start_time);
    OTapply.setOTEndTime(end_time);
    OTapply.setOTDeductTime(deductTime);
    OTapply.setOTWorkContent(OtContent);
    OTapply.setOtNextDays(NumberUtil.parseInt(anotherDay));
	OTapply.setOtTypeCode(otTypeCode);
   	OTapply.setCreatedBy(admin.getAdminID());
    essdao.addOTApplyAffirmItem(OTapply,empid,"OtApply");
	}
  }
  response.sendRedirect("/ess/ess0204.jsp?menu_code="+menu_code);


%>