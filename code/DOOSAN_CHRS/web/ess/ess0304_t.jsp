<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<% 

   ForeignLanguage foreignLanguage = new ForeignLanguage();
   String empID = request.getParameter("empID")!=null? request.getParameter("empID"):"";
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String languageTypecode = request.getParameter("languageTypecode")!=null? request.getParameter("languageTypecode"):"";
   String LanguageLevelCode = request.getParameter("LanguageLevelCode")!=null? request.getParameter("LanguageLevelCode"):"";
   String languageQulificationName = StringUtil.checkNull(request.getParameter("languageQulificationName"));
   String languageQualificationDate = request.getParameter("languageQualificationDate")!=null? request.getParameter("languageQualificationDate"):"";
   String languageResult = request.getParameter("languageResult")!=null? request.getParameter("languageResult"):"";
   String qualificationInstitute = request.getParameter("qualificationInstitute")!=null? StringUtil.toCN(request.getParameter("qualificationInstitute")):"";
   
   foreignLanguage.setLanguageTypeCode(languageTypecode);
   foreignLanguage.setLanguageLevelCode(LanguageLevelCode);
   foreignLanguage.setExamNameCode(languageQulificationName);
   foreignLanguage.setObtainedDate(languageQualificationDate);
   foreignLanguage.setMark(StringUtil.toCN(languageResult));
   foreignLanguage.setQualificationInstitute((qualificationInstitute));
   EssDAO essdao = new EssDAO();
   essdao.addEssForeignLanguage(foreignLanguage,empID);
   response.sendRedirect("/ess/ess0304.jsp?menu_code="+menu_code);
%>
