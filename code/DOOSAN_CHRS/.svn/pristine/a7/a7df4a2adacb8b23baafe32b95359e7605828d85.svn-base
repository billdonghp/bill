<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.entity.*,java.util.*,com.ait.ess.dao.*" errorPage="" %>
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
   String leaveApplyNo = request.getParameter("leaveApplyNo")!=null? request.getParameter("leaveApplyNo"):"";
   String applyType = request.getParameter("applyType")!=null? request.getParameter("applyType"):"";
   String adminid = request.getParameter("adminid")!=null? request.getParameter("adminid"):"";
   String flag = request.getParameter("flag")!=null? request.getParameter("flag"):"";
   
   AffirmDAO affirmdao = new AffirmDAO();
   
   String[] leaveApplyNoArray = request.getParameterValues("leaveApplyNos"); //批量决裁 李再军 2006-05-30
   if (leaveApplyNoArray != null){
		for (int i=0;i<leaveApplyNoArray.length;i++)	   
			   if (!affirmed(adminid,affirmdao.getAffirmStatusList(applyType,leaveApplyNoArray[i]))&&
					   affirmdao.updateAffirmorFlag(leaveApplyNoArray[i],adminid,applyType,flag) && 
					   "1".equals(flag) &&
				   	   affirmdao.isLastAffirmor(leaveApplyNoArray[i],adminid,applyType))//最终决裁者
				       affirmdao.LeaveApplyAffirm(leaveApplyNoArray[i],flag,adminid);
   }
   else{
	   // added date :2006-05-18
	   if (affirmdao.updateAffirmorFlag(leaveApplyNo,adminid,applyType,flag) && "1".equals(flag) &&
	   		affirmdao.isLastAffirmor(leaveApplyNo,adminid,applyType))//最终决裁者
	        affirmdao.LeaveApplyAffirm(leaveApplyNo,flag,adminid);
	   
   }
   
   int strPage=Integer.parseInt(request.getParameter("strPage"));
   
   response.sendRedirect("/ess/ess0802.jsp?menu_code="+menu_code+"&strPage="+strPage);
%>
