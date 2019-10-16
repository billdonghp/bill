<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>

<%

   Qualification qualification = new Qualification();
   String empID = request.getParameter("empID")!=null? request.getParameter("empID"):"";
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String qualificationNumber = request.getParameter("qualificationNumber")!=null? request.getParameter("qualificationNumber"):"";
   //String qualificationName = request.getParameter("qualificationName")!=null? request.getParameter("qualificationName"):"";
   String qualificationType = request.getParameter("qualificationType")!=null? request.getParameter("qualificationType"):"";
   String qualificationGrade = request.getParameter("qualificationGrade")!=null? request.getParameter("qualificationGrade"):"";
   String qualificationInstitute = request.getParameter("qualificationInstitute")!=null? request.getParameter("qualificationInstitute"):"";
   String qualificationDate = request.getParameter("qualificationDate")!=null? request.getParameter("qualificationDate"):"";
   qualification.setQualificationNumber(qualificationNumber);
   //qualification.setQualName(qualificationName);
   qualification.setQualificationNameCode(qualificationType);
   qualification.setGradeObtain(qualificationGrade);
   qualification.setQualInstitute(qualificationInstitute);
   qualification.setEndDate(qualificationDate);
   EssDAO essdao = new EssDAO();
   essdao.addEssQualification(qualification,empID);
   response.sendRedirect("/ess/ess0303.jsp?menu_code="+menu_code);
   
%>
