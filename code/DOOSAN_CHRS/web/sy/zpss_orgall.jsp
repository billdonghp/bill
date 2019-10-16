<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.sy.sy0102.bean.*"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.ait.utils.*"%>
<SCRIPT language=JavaScript>
var ID;
ID='';
var MENU_CODE;
MENU_CODE='';
</SCRIPT>
<%
 String cpnyId=request.getParameter("cpnyid");
	String checkFlag="";
	Deptree Biz = new Deptree();
	//Func func= new Func();
	Deptree Ent = new Deptree();
	Vector vlist = Biz.getDeptAll(cpnyId);
    
%>

<table width="483" height="100%" border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td width="276" valign="top">
    <table width="676" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="276" height="0" align="center" class="left_title"></td>
        </tr>
      </table>
      <table id="t_deptlist" border="0" cellspacing="0" cellpadding="0">
        <% 
    com.ait.utils.IntPair intPair = com.ait.util.HRUtil.getMaxAndMinDeptLevel();
    int maxLevel = intPair.getMax();
    int minLevel = intPair.getMin();
    admin = (AdminBean)session.getAttribute("admin");
	String language=admin.getLanguagePreference();
  ///if(language.equals("zh"))  
 /// {  
   for ( int i = 0 ; i < vlist.size() ; i++ )
	{
	  Ent=(Deptree)vlist.elementAt(i);
	  int maxColspan = maxLevel + 2;
	  int deptLevel = Integer.parseInt(Ent.getDeptLevel());
%>
        <tr>
          <%for (int j = 0; j <= deptLevel - 1; j++) {%>
          <td></td>
          <%} %>
          <td><img src="/images/left_menubullet_sub_node.gif" width="14" height="22"></td>
          <td><img src="/images/left_menubullet_sub_m.gif" width="14" height="22"></td>
          <td nowrap colspan="<%=maxColspan - deptLevel%>" class="left_menu"><%=Ent.getDeptName()%></td>
          <td nowrap valign="middle" class="left_menu"><input type="hidden" name="deptLevel" value="<%=deptLevel%>">
            <input type="hidden" name="parentDptId" value="<%=Ent.getParentDeptId()%>">
            <span class="left_menu_1depth"><a href="#"><img src="/images/Content_icon_arrowright.gif" width="10" height="11" border="0"></a></span>
            
            <%checkFlag="";for(int z=0;z<depts.size();z++){ String s_str=(String)depts.get(z);if(s_str.equals(Ent.getDeptId())){ checkFlag="checked"; break;}}%>
            <input type="checkbox" name="dp" value="<%=Ent.getDeptId()%>" onClick="doCheck(this, '<%=deptLevel%>')" <%=checkFlag%>></td>
        </tr>
        <!-- language.equals("en") -->
       <%}%>
      <%-- }else{%>                  
        <%           
        for ( int i = 0 ; i < vlist.size() ; i++ )
    	{
    	  Ent=(Deptree)vlist.elementAt(i);
    	  int maxColspan = maxLevel + 2;
    	  int deptLevel = Integer.parseInt(Ent.getDeptLevel());
    %>
                                     
            <tr>
              <%for (int j = 0; j <= deptLevel - 1; j++) {%>            
              <td></td>
              <%} %>
              <td><img src="/images/left_menubullet_sub_node.gif" width="14" height="22"></td>
              <td><img src="/images/left_menubullet_sub_m.gif" width="14" height="22"></td>
              <td nowrap colspan="<%=maxColspan - deptLevel%>" class="left_menu"><%=Ent.getDeptEnName()%></td>
              <td nowrap valign="middle" class="left_menu"><input type="hidden" name="deptLevel" value="<%=deptLevel%>">
                <input type="hidden" name="parentDptId" value="<%=Ent.getParentDeptId()%>">
                <span class="left_menu_1depth"><a href="/sy/zpss_org_man.jsp?DeptID=<%=Ent.getDeptId()%>"><img src="/images/Content_icon_arrowright.gif" width="10" height="11" border="0"></a></span>
                <%checkFlag="";for(int z=0;z<depts.size();z++){ String s_str=(String)depts.get(z);if(s_str.equals(Ent.getDeptId())){ checkFlag="checked"; break;}}%>
                <input type="checkbox" name="dp" value="<%=Ent.getDeptId()%>" onClick="doCheck(this, '<%=deptLevel%>')" <%=checkFlag%>></td>
            </tr>
            <%} } %>    
      --%></table></td>
    <td width="10" valign="top" background="/images/left_back_02.gif"></td>             
  </tr>
</table>
<SCRIPT language="JavaScript">   
    var hiddenName = "parentDptId";
    var levelName = "deptLevel";
    function findObject(object , tag){
      if(object == null || typeof(object) != "object") return null ;
      var node = object.parentElement ;
      if(node == null) return null ;
      if(node.tagName == tag)
        return node ;
      else
       return findObject(node , tag) ;
    }
    function findRow(object){
       return findObject(object , "TR") ;
    }
    
	function doCheck(ckObj, deptLevl){
	   var v = ckObj.value;
	   var rowNum = findRow(ckObj).rowIndex;
	   doSubCheck(rowNum, v, ckObj.checked, deptLevl);
	}

   function doSubCheck(rowNum, v, bchecked, deptLevl){
      for(var i = rowNum + 1 ; i < document.all.t_deptlist.rows.length ; i++){
          var row = document.all.t_deptlist.rows[i];
		  var colIndex =  row.cells.length - 1;
          var objects = row.cells[colIndex].all;
	  var currentLevel = objects[0].value;
		  var currentParent = objects[1].value;
		  if (currentParent == v){
	      objects[5].checked = bchecked;
			    doSubCheck(i, objects[5].value, bchecked, currentLevel);
			    selected = true;
			    continue;
		  } 
		  if (deptLevl >= currentLevel) {
		     return;
		  }   
      }
    }  	  
</SCRIPT>
