<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="com.ait.ess.entity.*"%>
<%@ page import="com.ait.util.*"%>
<%@ page import="com.ait.util.PageControl"%>
<%@ page import="com.ait.ess.dao.*"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>
<%@ page import="java.util.*,com.ait.hr.entity.*"%>
<%@ page import="com.ait.utils.*"%>
<%@ page import="org.apache.log4j.Logger"%>
<jsp:useBean id="admin" class="com.ait.sy.com.ait.gm.DateBean.bean.AdminBean"
	scope="session" />
<jsp:useBean id="leaveapply" class="com.ait.ess.entity.LeaveApply"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript">
 <!--hidden
 
 function clickaction (){
     document.familyForm.submit();
	 }
 -->
</script>
<%!public boolean affirmed(String adminId, List affirmorList) {
		for (int i = 0; i < affirmorList.size(); i++) {
			EssAffirm essAffirm = (EssAffirm) affirmorList.get(i);
			if (adminId.equals(essAffirm.getAffirmorId())
					&& essAffirm.getAffirmFlag() != 0) {
				return true;
			}
		}
		return false;
	}

	%>
<%		
			Map statusMap = new HashMap();
			statusMap.put("0", "���瑁�);
			statusMap.put("1", "宸查�杩�);
			statusMap.put("2", "涓��杩�);

			Map colorMap = new HashMap();
			colorMap.put("0", "#990099");
			colorMap.put("1", "#00CC00");
			colorMap.put("2", "#CC0000");
			String applyType = "LeaveApply";

			//��〉�ㄥ����
			//get page control info from request
			PageControl pc = (PageControl) request.getAttribute("pc");
			Object bigPageStr = request.getAttribute("bigPage");
			request.setAttribute("bigPage", null);//������绌哄���
			Object strPageStr = request.getAttribute("strPage");
			request.setAttribute("strPage", null);//������绌哄��э�

			int bigPage = Integer.parseInt(bigPageStr == null ? "0"
					: bigPageStr.toString());
			int strPage = Integer.parseInt(strPageStr == null ? "0"
					: strPageStr.toString());

			String selectContent = (String) request
					.getAttribute("selectContent") != null ? StringUtil
					.toCN(request.getParameter("selectContent")) : "";
			request.setAttribute("selectContent", null);
			String selectCondition = (String) request
					.getAttribute("selectCondition") != null ? request
					.getParameter("selectCondition") : "";
			request.setAttribute("selectCondition", null);

			%>
<body>
<!--%@ include file="/inc/hrtoolbar_null.jsp"%-->
<%String menu_code = request.getParameter("menu_code");
			String rodeLevel = null;

			ArrayList tool_menu = null;
			admin = (AdminBean) session.getAttribute("admin");
			rodeLevel = admin.getScreenGrantNo() != null ? admin
					.getScreenGrantNo() : "";
			ToolMenu toolmenu = new ToolMenu();
			MenuBiz menubiz = new MenuBiz();
			tool_menu = menubiz.toolMenuList(menu_code, rodeLevel);
			String imgname = menu_code.substring(0, 2);
			int insertMenu = 0;
			int updateMenu = 0;
			int deleteMenu = 0;
			for (int i = 0; i < tool_menu.size(); i++) {
				toolmenu = (ToolMenu) tool_menu.get(i);
				if (toolmenu.getInsertr() == 1) {
					insertMenu = 1;

				}
				if (toolmenu.getUpdate() == 1) {
					updateMenu = 1;

				}
				if (toolmenu.getDelect() == 1) {
					deleteMenu = 1;
				}
			}
%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="15" valign="middle">
		<table width="100%" height="15" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="7">
				<td width="168" align="center"></td>
				<td width="56" align="center"></td>
				<td width="3">
				<td width="527" align="right" valign="middle"
					style="padding:3 0 3 0 "><!-- <a href="javascript:Save ();"><img src="/images/btn/save1.gif" width="67" height="24" border="0" align="absmiddle"> 
          </a><img src="/images/btn/return1.gif" width="67" height="24" align="absmiddle"> 
            <img src="/images/btn/homepage1.gif" width="67" height="24" align="absmiddle"> 
            <img src="/images/btn/exit1.gif" width="67" height="24" align="absmiddle">--></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="2"></td>
	</tr>
</table>
<!-- %@ include file="/inc/thirdToolBar.jsp"%-->
<script language="JavaScript" src="/js/calendarcode.js"></script>
<DIV class=text id=popupcalendar style="top:0px;left:0px"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
<%
			if (session.getAttribute("admin") == null) {
				response.sendRedirect("/error.jsp");
			}
			admin = (AdminBean) session.getAttribute("admin");
			menu_code = request.getParameter("menu_code");
			rodeLevel = admin.getScreenGrantNo() != null ? admin
					.getScreenGrantNo() : "";
			MenuEnt menuent = new MenuEnt();
			List menu_vector = menubiz.thirdmenulist(menu_code, rodeLevel);
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="kuang_zuo"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="18"><img src="/images/top_zuo.jpg" width="18" height="28"></td>
          <td background="/images/top_zuo_d2.jpg"><table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <%
										for (int i = 0; i < menu_vector.size(); i++) {
										menuent = (MenuEnt) menu_vector.get(i);
								%>
                <%
								if (menu_code.equals(menuent.getMenuCode())) {
								%>
                <td class="td_shen"><a href="<%=menuent.getMenuUrl()%>" class="shen"><%=menuent.getMenuIntro()%></a></td>
                <%
								} else {
								%>
                <td class="td_qian"><a href="<%=menuent.getMenuUrl()%>" class="qian"><%=menuent.getMenuIntro()%></a></td>
                <%
									}
									}
								%>
              </tr>
            </table></td>
          <td width="14" align="right"><img src="/images/top_you.jpg" width="14" height="28"></td>
        </tr>
      </table></td>
  </tr>
</table>

<div align="left"><img src="/images/btn/arrow_2.gif" width="6" height="10"
	align="absmiddle"> <span class="title1">�宠�/璇峰�淇℃��宠�</span></div>
<form name="familyForm" method="post"
	action="/EssContorlServlet?command=WorkArbitramentCommandImp&content=WorkArbitramentLeaveContentImp">
<input type="hidden" name="menu_code" value="<%=menu_code%>" />
<table>
	<tr>
		<td align="right" width="100%"><input type="text" name="selectContent"
			style="width:90px ">&nbsp; <select name="selectCondition">
			<option value="">�ㄩ�</option>
			<option value=" and deptname like ">�ㄩ�</option>
			<option value=" and empid like ">宸ュ�</option>
			<option value=" and chinesename like ">涓��濮��</option>
			<option value=" and chinese_pinyin like ">�奸�濮��</option>
		</select>&nbsp;&nbsp; <img align="absmiddle"
			src="../images/btn/search.gif" onClick="javascript:clickaction();"></td>
	</tr>
</table>
</form>
<table width="100%" border="1" cellpadding="0" cellspacing="0"
	bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	<tr align="center">
		<td height="30" align="center" bgcolor="#F5F5F5">搴��</td>
		<td height="30" align="center" bgcolor="#F5F5F5">浼��浜�/td>
		<td height="30" align="center" bgcolor="#F5F5F5">浼��绫诲�</td>
		<td height="30" align="center" bgcolor="#F5F5F5">�宠��ユ�</td>
		<td height="30" align="center" bgcolor="#F5F5F5">浼���垮害</td>
		<td height="30" align="center" bgcolor="#F5F5F5">寮���ユ�</td>
		<td height="30" align="center" bgcolor="#F5F5F5">缁���ユ�</td>
		<td height="30" align="center" bgcolor="#F5F5F5">浼��璇存�</td>
		<td align="center" bgcolor="#F5F5F5">�宠����</td>
	</tr>
	<%List leaveApplyList = (List) request.getAttribute("leaveApplyList");
			//Logger.getLogger(getClass()).debug("##############"+leaveApplyList.size());
			if (leaveApplyList != null) {
				for (int i = 0; i < leaveApplyList.size(); i++) {
					leaveapply = (LeaveApply) leaveApplyList.get(i);

					%>
	<tr align="center">
		<td height="30" align="center"><%=i + 1%></td>
		<td height="30" align="center"><%=leaveapply.getLeavePersonName()%>&nbsp;</td>
		<td height="30" align="center"><%=leaveapply.getLeaveTypeCodeName()%>&nbsp;</td>
		<td height="30" align="center"><%=leaveapply.getLeaveApplyDate()%>&nbsp;</td>
		<td height="30" align="center"><%=leaveapply.getLeaveDays()%>&nbsp;</td>
		<td height="30" align="center"><%=leaveapply.getLeaveApplyStartDate()%>&nbsp;</td>
		<td height="30" align="center"><%=leaveapply.getLeaveApplyEndDate()%>&nbsp;</td>
		<td height="30" align="center"><%=leaveapply.getLeaveContent()%>&nbsp;</td>
		<%
		List affirmStatusList = leaveapply.getAffirmStatusList();
%>
		<td align="center"><%
					if (affirmStatusList != null)
						for (int k = 0; k < affirmStatusList.size(); k++) {
							EssAffirm essAffirm = (EssAffirm) affirmStatusList
									.get(k);
							String affirmFlag = String.valueOf(essAffirm
									.getAffirmFlag());
							out
									.println("<font color=\""
											+ colorMap.get(affirmFlag) + "\">"
											+ essAffirm.getAffirmorName() + " "
											+ statusMap.get(affirmFlag)
											+ "</font><br>");
						}
					if (affirmStatusList != null
							&& !affirmed(admin.getAdminID(), affirmStatusList)) {
%> <a
			href="/EssContorlServlet?
								command=WorkArbitramentCommandImp&
								content=WorkArbitramentLeaveContentImp&
								leaveApplyNo=<%=leaveapply.getLeaveApplyNo()%>&
								applyType=<%=applyType%>&
								adminid=<%=admin.getAdminID()%>&
								flag=1&
								menu_code=<%=menu_code%>">
		<font color="#FF0000">���</font> </a> <font color="#00CC00">/</font> <a
			href="/EssContorlServlet?
								command=WorkArbitramentCommandImp&
								content=WorkArbitramentLeaveContentImp&
								leaveApplyNo=<%=leaveapply.getLeaveApplyNo()%>&
								applyType=<%=applyType%>&adminid=<%=admin.getAdminID()%>&
								flag=2&
								menu_code=<%=menu_code%>">
		<font color="#FF0000">涓��杩�/font></a> <%}

				%></td>
	</tr>
	<%}//for end
			} else {

%>
	<tr align="center" height="30">
		<td height="30" colspan="10"><font color="#CC0000">���娌℃��宠�淇℃�!</font></td>
	</tr>
	<%
	}
%>
	<tr align="center">
		<td height="50" colspan="10" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center"><!-- �剧ず��〉�ㄥ���俊��-->
				<table border="0" align="center" cellspacing="0">
					<tr>
						<td align="left" valign="middle"><%if (pc.isforward(bigPage)) {
%> <a
							href="/EssContorlServlet?
					  command=WorkArbitramentCommandImp&
					  content=WorkArbitramentLeaveContentImp&
					  flag=list&bigPage=<%=bigPage -1%>&
					  selectCondition=<%=selectCondition%>&
					  selectContent=<%=selectContent%>&
					  menu_code=<%=menu_code%>"
							target="_parent">
					<img src="/images/button/p_last10.gif"
							width="18" height="12" border="0" />
					</a> <%}

			%></td>
						<td align="left">
						<table border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<%for (int i = 1; i < 11; i++) {
				if (pc.getNowSmall(i, bigPage)) {

					%>
								<td><a
									href="/EssContorlServlet?
								command=WorkArbitramentCommandImp&
								content=WorkArbitramentLeaveContentImp&
								flag=list&
								bigPage=<%=bigPage%>&
								strPage=<%=i%>&
								selectCondition=<%=selectCondition%>&
								selectContent=<%=selectContent%>&
								menu_code=<%=menu_code%>">
								<font color="#666666">
								<b>
								<%=pc.getListSmall(i, bigPage)%>
								</b>
								</font>
								</a>&nbsp;</td>
								<%}
			}

			%>
							</tr>
						</table>
						</td>
						<td align="left"><%if (pc.isNextBig(bigPage)) {
%> <a	href="/EssContorlServlet?
					  command=WorkArbitramentCommandImp&
					  content=WorkArbitramentLeaveContentImp&flag=list&
					  bigPage=<%=bigPage + 1%>&
					  selectCondition=<%=selectCondition%>&
					  selectContent=<%=selectContent%>&
					  menu_code=<%=menu_code%>">
						<font color="#666666">
							<%=bigPage+1 %>
						</font> 
		</a>
		</td>
						<%}

		%>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
