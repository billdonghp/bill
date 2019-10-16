<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<% 

   MilitaryService militaryService = new MilitaryService();
   String empID = request.getParameter("empID")!=null? request.getParameter("empID"):"";
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String military_type = request.getParameter("military_type")!=null? request.getParameter("military_type"):"";
   String military_level = request.getParameter("military_level")!=null? request.getParameter("military_level"):"";
   String military_area = request.getParameter("military_area")!=null? request.getParameter("military_area"):"";
   String military_start = request.getParameter("military_start")!=null? request.getParameter("military_start"):"";
   String military_end = request.getParameter("military_end")!=null? request.getParameter("military_end"):"";
	    militaryService.setMilitaryAreaCode(military_area);
        militaryService.setStartTime(military_start); 
        militaryService.setEndTime(military_end);
        militaryService.setMilitaryLevelCode(military_level);
        militaryService.setMilitaryTypeCode(military_type); 
   EssDAO essdao = new EssDAO();
   essdao.addEssMilitaryService(militaryService,empID);
   response.sendRedirect("/ess/ess0305.jsp?menu_code="+menu_code);
%>
