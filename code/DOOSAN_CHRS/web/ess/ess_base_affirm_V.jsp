<%@ page contentType="text/html; charset=UTF-8" import="com.ait.hr.bean.*,com.ait.hr.entity.*,java.util.*,java.util.Vector,com.ait.util.*"%>
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" /> <!-- the list shows all departments -->
<jsp:useBean id="department" class="com.ait.hr.com.ait.gm.DateBean.bean.Department"/>
<jsp:useBean id="personal" class="com.ait.hr.entity.PersonalInfo" scope="request"/>
<jsp:useBean id="basic" class="com.ait.hr.entity.BasicInfo" scope="request"/> <!-- basic information of the selected employee -->
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>  <!-- the admin entity keeped in current session -->
<jsp:useBean id="codemap" class="java.util.HashMap"/> 
<jsp:useBean id="hrentitydao" class="com.ait.hr.dao.HrEntityDAO"/> 
<jsp:useBean id="affirmdao" class="com.ait.ess.operation.InfoAffirmOp"/> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>基本信息申请</title>
</head>
<body>
<%@ include file="/inc/hrtoolbar_b.jsp"%>
<SCRIPT type="text/javascript">
<!--hidden
function backFront(){
   document.save.submit();
	
}
 //-->
</SCRIPT>
<%
  String empid = request.getParameter("empid")!=null?request.getParameter("empid"):"";
  basic = hrentitydao.getBasicInfo(empid);
  personal = affirmdao.getPersonalAffirmListByEmpid(empid);
%>
<form name="save" action="/EssContorlServlet" method="post">
	<input type="hidden" name="command" value="InfoAffirmCommandImp"/>
	<input type="hidden" name="content" value="InfoAffirmBaseContentImp"/>
	<input type="hidden" name="empID" value="<%=basic.getEmpID()%>"/>

<span class="title1">基本信息</span>
<br>
<br>
<%@ include file="/ess/ess_view_basic_v.jsp"%>
<hr>

<span class="title1">个人信息</span>
 <br>
<td class="line_gray_01" style="padding:1 1 1 1 ">
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="14">&nbsp;</td>
      <td width="947">&nbsp;</td>
      <td width="11">&nbsp;</td>
    </tr>
    <td width="14" height="129" style=" padding:2 2 2 2 " align="center">
	   <table width="10" height="125"  border="0" cellpadding="0" cellspacing="0">
          <tr>
          </tr>
       </table>
	</td>
    <td class="line_gray_01" style="padding:1 1 1 1 ">
    <table width="100%" height="275"  border="0" cellpadding="0" cellspacing="0">
				    <% int membership = personal.getMembership();
					String member_status="";
					if(membership ==1)  member_status="是";
					else member_status="否";
				%>
	        <tr>
			    <td width="12%"  height="30"  class="info_title_01"><div align="center">姓&nbsp;&nbsp;名</div></td>
				<td width="21%" class="info_content_01"><%=personal.getChineseName()%>&nbsp;</td>
				<td  height="30"  class="info_title_01"><div align="center">性&nbsp;&nbsp;别</div></td>
				<td class="info_content_01"><%=personal.getSex()%>&nbsp;</td>
				<td width="12%"  height="30"  class="info_title_01"><div align="center">出生日期</div></td>
			    <td width="19%" class="info_content_01"><%=personal.getDOB()%>&nbsp;</td>
			</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
			    <td  height="30"  class="info_title_01"><div align="center">英文名</div></td>
				<td class="info_content_01"><%=personal.getEnglishName()%>&nbsp;</td>
			    <td class="info_title_01"><div align="center">民&nbsp;&nbsp;族</div></td>
				<td class="info_content_01"><%=personal.getNation()%>&nbsp;</td>
			    <td  height="30"  class="info_title_01"><div align="center">政治面貌</div></td>
				<td class="info_content_01"><%=personal.getPolity()%>&nbsp;</td>
			</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
			    <td  height="30"  class="info_title_01"><div align="center">学&nbsp;&nbsp;历</div></td>
			    <td class="info_content_01"><%=personal.getFinalDegree()%>&nbsp;</td>
				<td width="12%" class="info_title_01"><div align="center">婚姻状况</div></td>
				<td width="24%" class="info_content_01"><%=personal.getMaritalStatus()%>&nbsp;</td>
				<td  height="30"  class="info_title_01"><div align="center">籍&nbsp;&nbsp;贯</div></td>
				<td class="info_content_01"><%=personal.getBornPlace()%>&nbsp;</td>
			</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
			    <td class="info_title_01"><div align="center">移动电话</div></td>
				<td class="info_content_01"><%=personal.getCellPhone()%>&nbsp;</td>
				<td class="info_title_01"><div align="center">户口所在地</div></td>
				<td class="info_content_01"><%=personal.getRegPlace()%>&nbsp;</td>
				<td class="info_title_01"><div align="center">工会会员&nbsp;</div></td>
				<td class="info_content_01">&nbsp;<%=member_status%></td>
			</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
			    <td class="info_title_01"><div align="center">家庭电话</div></td>
				<td class="info_content_01"><%=personal.getHomePhone()%>&nbsp;</td>
				<td class="info_title_01"><div align="center">户口性质</div></td>
				<td class="info_content_01"><%=personal.getRegType()%>&nbsp;</td>
				<td  height="30"  class="info_title_01"><div align="center">爱&nbsp;&nbsp;好</div></td>
			    <td class="info_content_01"><%=personal.getHobby()%>&nbsp;</td>
			</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
			    <td align="center" class="info_title_01"><div align="center">电子邮件</div></td>
			    <td class="info_content_01"><%=personal.getEmail()%>&nbsp;</td>
				<td  height="30"  class="info_title_01"><div align="center">宗教信仰</div></td>
				<td class="info_content_01"><%=personal.getReligion()%>&nbsp;</td>
				<td class="info_title_01"><div align="center">护照签发日</div></td>
			    <td class="info_content_01"><%=personal.getVisaDate()%>&nbsp;</td>
			</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
			    <td width="12%" class="info_title_01"><div align="center">国&nbsp;&nbsp;籍</div></td>
				<td width="21%" class="info_content_01"><%=personal.getNationNality()%>&nbsp;</td>
				<td class="info_title_01"><div align="center">身份证号码</div></td>
			    <td class="info_content_01"><%=personal.getIdcardNo()%>&nbsp;</td>
				<td class="info_title_01"><div align="center">护照号</div></td>
			    <td class="info_content_01"><%=personal.getPassportNo()%>&nbsp;</td>
			</tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
				<td align="center" class="info_title_01"><div align="center">身份证地址</div></td>
				<td class="info_content_01" colspan="5" ><div align="left"><%=personal.getIdcardAddress()%>&nbsp;</div></td>

		    </tr>
			<tr>
				<td height="1" colspan="6"></td>
			</tr>
			<tr>
				<td align="center" class="info_title_01"><div align="center">居住地址</div></td>
				<td colspan="5" class="info_content_01"><%=personal.getHomeAddress()%>&nbsp;</td>
		    </tr>
</table>
 </table>
</form>
</body>
</html>
