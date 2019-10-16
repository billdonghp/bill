<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="com.ait.utils.*" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%@ page import="com.ait.sqlmap.util.UserConfiguration"%>
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

	UserConfiguration userConfig = UserConfiguration.getInstance("/system.properties"); 
	String[] sgNo = admin.getScreenGrantNo().split(",");
	boolean b = true;
	for (int i = 0; i < sgNo.length; i++) {
		if (("," + userConfig.getString("common.role.inspectionView").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
			b = false;
	}
	
	
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
	map.setBoolean("b", b);
	request.setAttribute("toolbarInfo", map);

%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
	  <table width="99%" border="0" cellpadding="0" cellspacing="0">            
         <tr id = "trname">
        <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">
           <ait:image src="/images/btn/Search.gif" align="absmiddle"
          	onclick="javascript:search();" style="cursor:hand" title="搜索" enTitle="search" />
         
         
            <ait:image src="/images/btn/Modify.gif"  border="0" align="absmiddle"
          	onclick="javascript:Update();" style="cursor:hand" title="修改" enTitle="update" />
         
          
         
            <ait:image src="/images/btn/Apply.gif"  border="0" align="absmiddle"
          	onclick="javascript:Save();" style="cursor:hand" title="申请" enTitle="apply" />
        
          
          
       
          </td>  
        </tr> 
      </table>
      </td>
  </tr>
</table>
