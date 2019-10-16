<%@ page import="java.util.*"%>
<%@ page import="com.ait.utils.*"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>
<SCRIPT language=JavaScript>
var ID;
ID='';
var MENU_CODE;
</SCRIPT>
<SCRIPT language=JavaScript src="../js/table.js"></SCRIPT>
<%
	    AdminBean admin = new AdminBean();          
	    if (session.getAttribute("admin") == null) {    
		response.sendRedirect("/expired.jsp");
	    }
	    admin = (AdminBean) session.getAttribute("admin");      
	    String empID = "";
	    String userID = "";
	    String chineseName = "";  
	    String deptID = "";
	    String roleID = "";
	    //String rodeLevel = "";
	    String passWord = "";
	    empID = admin.getAdminID();
	    userID = admin.getUsername();
	    chineseName = admin.getChineseName();
	    deptID = admin.getDeptID();
	    //rodeLevel=admin.getScreenGrantNo();
	    roleID = admin.getLoginDeptID();
	    passWord = admin.getPassword();
%>
<%
	    String menu_code = "";
	    menu_code = request.getParameter("menu_code");
	    Vector vlist = new Vector();
	    BizEvsCode Biz = new BizEvsCode();
	    Func func = new Func();
	    String no = func.chkNull(request.getParameter("no"));
	    String searchopt = request.getParameter("searchopt");
	    if (searchopt == null) {
		searchopt = "";
	    } else {
		searchopt = func.isoStr(searchopt);
	    }
	    String searchcontent = request.getParameter("searchcontent");
	    if (searchcontent == null) {
		searchcontent = "";
	    } else {
		searchcontent = func.isoStr(searchcontent);
	    }
	    String fromdate = request.getParameter("fromdate");
	    if (fromdate == null) {
		fromdate = "";
	    }
	    String todate = request.getParameter("todate");
	    if (todate == null) {
		todate = "";
	    }
%>
