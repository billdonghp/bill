<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="com.ait.utils.*" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<script language="javascript">
function trim(str){ //删除左右两端的空格 
return str.replace(/(^\s*)|(\s*$)/g, ""); 
} 
function Update1() {

	alert("保存后系统默认时间单位为小时!");
	
	var flag = false ;
	if(document.form1.details == null)
	{
		return ;
	}
	for (i=0;i<document.form1.details.length;i++){
		if(document.form1.details[i].checked){     
			flag = true;   
			break ;
		}
	}
	
	if(document.form1.details.checked)
	{
		flag = true;
	}
	

	
	
	if (!flag)
	{//"请选择要修改的数据!!"
		alert("请选择要修改的数据!!") ;
		return ;
	}
	
	var s = document.getElementsByName("details");
	
	for( var i = 0; i < s.length; i++ )
	{
	  if ( s[i].checked ){
	  
	  	    
	        if(($('cpnyId' + i).value == '78000000') || ($('cpnyId' + i).value == '59000000') || ($('cpnyId' + i).value == '63000000') ){
				if($('flag' + i).value == 0 ){
					alert("未决裁的考勤不能修改！");
					return;
				}
				
				if($('flag' + i).value == 1 || $('flag' + i).value == 2 ){
					alert("已通过和否决的考勤不能再次修改，只可以删除！");
					return;
				}
			    var remark = document.getElementById("remark_"+s[i].value).value;
				if(trim(remark) == '' ){
					alert("请填写修改原由！");
				    return ;
				}
			}
			
	  }
	}
	
	document.form1.action="/arControlServlet?operation=ar_modify&content=detail&menu_code=<%=request.getParameter("menu_code")%>&currentPage=<%=request.getAttribute("currentPage")%>";
	document.form1.submit();
	
	document.getElementById("updatename").style.display="none";//避免重复提交，隐藏按钮
}
</script>	
<%
	String menu_code = "";
	String rodeLevel = "";
	SimpleMap map = new SimpleMap();
	ArrayList tool_menu = null;
	if (session.getAttribute("admin") == null) {
		response.sendRedirect("/error.jsp");
	}
	menu_code = request.getParameter("menu_code");
	admin = (AdminBean) session.getAttribute("admin");
	rodeLevel = admin.getScreenGrantNo() != null ? admin.getScreenGrantNo() : "";
	ToolMenu toolmenu = new ToolMenu();
	MenuBiz menubiz = new MenuBiz();
	tool_menu = menubiz.toolMenuList(menu_code, rodeLevel);

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
	
	map.setInt("insertMenu", insertMenu);
	map.setInt("updateMenu", updateMenu);
	map.setInt("deleteMenu", deleteMenu); 
	request.setAttribute("toolbarInfo", map);

%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
	  <table width="99%" border="0" cellpadding="0" cellspacing="0">            
        <tr id = "updatename">
        <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">
          <c:if test="${toolbarInfo.insertMenu == 1}">
          	<ait:image src="/images/btn/Add.gif"  border="0" align="absmiddle"
          	onclick="javascript:Add();" style="cursor:hand" title="添加" enTitle="add" />
          </c:if>
          <c:if test="${toolbarInfo.updateMenu == 1}">
          
            <ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle" 
          	onclick="javascript:Update1();" style="cursor:hand" title="保存" enTitle="update" />
          
          </c:if>
          <c:if test="${toolbarInfo.deleteMenu == 1}">
          <ait:image src="/images/btn/Delete.gif"  border="0" align="absmiddle"
          	onclick="javascript:Delete();" style="cursor:hand" title="删除" enTitle="delete" />
          </c:if>
          <%if((admin.getEmpDiffCode().equals("C_12067_1330308")) && (admin.getCpnyId().equals("78000000"))){ %>
          <ait:image src="/images/btn/Apply.gif"  border="0" align="absmiddle" onclick="sendMailAll();" style="cursor:hand" />
          <%}%>
          </td>  
        </tr> 
      </table>
      </td>
  </tr>
</table>
