<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<% 

   Health health = new Health();
   String empID = request.getParameter("empID")!=null? request.getParameter("empID"):"";
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String health_test_date = request.getParameter("health_test_date")!=null? request.getParameter("health_test_date"):"";
   String health_height = request.getParameter("health_height")!=null? request.getParameter("health_height"):"";
   String health_weight = request.getParameter("health_weight")!=null? request.getParameter("health_weight"):"";
   String health_left_eye = request.getParameter("health_left_eye")!=null? request.getParameter("health_left_eye"):"";
   String health_right_eye = request.getParameter("health_right_eye")!=null? request.getParameter("health_right_eye"):"";
   String health_colour = request.getParameter("health_colour")!=null? request.getParameter("health_colour"):"";
   String health_blood = request.getParameter("health_blood")!=null? request.getParameter("health_blood"):"";
   String health_content = request.getParameter("health_content")!=null? request.getParameter("health_content"):"";
            health.setPhysicalTestDate(health_test_date);
            health.setHeight(health_height);
            health.setWeight(health_weight);
            health.setLeftEyesight(health_left_eye); 
            health.setRightEyesight(health_right_eye);
            health.setSeshi(StringUtil.toCN(health_colour));
            health.setBloodTypeCode(health_blood);
            health.setContent(StringUtil.toCN(health_content));
   EssDAO essdao = new EssDAO();
   essdao.addEssHealth(health,empID);
   response.sendRedirect("/ess/ess0306.jsp?menu_code="+menu_code);
%>
