<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<% 

   Family family = new Family();
   String empID = request.getParameter("empID")!=null? request.getParameter("empID"):"";
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String familyrelation = request.getParameter("familyrelation")!=null? request.getParameter("familyrelation"):"";
   String chinesename = request.getParameter("chinesename")!=null? request.getParameter("chinesename"):"";
   String degree = request.getParameter("degree")!=null? request.getParameter("degree"):"";
   String country = request.getParameter("country")!=null? request.getParameter("country"):"";
   String borndate = request.getParameter("borndate")!=null?request.getParameter("borndate"):"";
   String companyName = request.getParameter("companyName")!=null? request.getParameter("companyName"):"";
   String phone = request.getParameter("phone")!=null? request.getParameter("phone"):"";
   String address = request.getParameter("address")!=null? request.getParameter("address"):"";
   family.setRelationalTypeCode(familyrelation);
   family.setFMName(StringUtil.toCN(chinesename));
   family.setFMDegree(degree);
   family.setFMNationalityCode(country);
   family.setFMBornDate(borndate);
   family.setFMCompanyName(StringUtil.toCN(companyName));
   family.setFMPhone(phone);
   family.setFMHomeAddress(StringUtil.toCN(address));
   family.setModifierID(admin.getAdminID());
   EssDAO essdao = new EssDAO();
   essdao.addEssFamily(family,empID);
   response.sendRedirect("/ess/ess0302.jsp?menu_code="+menu_code);
%>
