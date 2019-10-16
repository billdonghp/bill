<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.dao.*,com.ait.ess.entity.*,java.util.*" errorPage="" %>
<%!
	public boolean affirmed(String adminId,List affirmorList){
		for(int i =0; i<affirmorList.size(); i++){			
			EssAffirm essAffirm =(EssAffirm) affirmorList.get(i);
			if(adminId.equals(essAffirm.getAffirmorId()) && essAffirm.getAffirmFlag() != 0 ){
				return true;
			}
		}
		return false;
	}
%>
<%
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String otapplyNo = request.getParameter("otapplyNo")!=null? request.getParameter("otapplyNo"):"";
   String applyType = request.getParameter("applyType")!=null? request.getParameter("applyType"):"";
   String adminid = request.getParameter("adminid")!=null? request.getParameter("adminid"):"";
   String flag = request.getParameter("flag")!=null? request.getParameter("flag"):"";
   
   AffirmDAO affirmdao = new AffirmDAO();
   
   String[] otapplyNoArray = request.getParameterValues("otapplyNos"); //批量决裁 李再军 2006-05-30
   if (otapplyNoArray != null){
	   for (int i=0;i<otapplyNoArray.length;i++)
	   	   if (!affirmed(adminid,affirmdao.getAffirmStatusList(applyType,otapplyNoArray[i]))&&
	   		   affirmdao.updateAffirmorFlag(otapplyNoArray[i],adminid,applyType,flag) && "1".equals(flag)&&
   			   affirmdao.isLastAffirmor(otapplyNoArray[i],adminid,applyType))//最终决裁者
    	       affirmdao.OTApplyAffirm(otapplyNoArray[i],flag,adminid);
   }
   else{
	   // added date :2006-05-17
   	   if (affirmdao.updateAffirmorFlag(otapplyNo,adminid,applyType,flag) && "1".equals(flag)&&
   			   affirmdao.isLastAffirmor(otapplyNo,adminid,applyType))//最终决裁者
           affirmdao.OTApplyAffirm(otapplyNo,flag,adminid);
   }
   
   int strPage=Integer.parseInt(request.getParameter("strPage"));
   
   response.sendRedirect("/ess/ess0801.jsp?menu_code="+menu_code+"&strPage="+strPage);
%>
