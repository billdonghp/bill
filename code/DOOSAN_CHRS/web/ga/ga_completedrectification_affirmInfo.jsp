<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap,java.util.*,com.ait.ga.servlet.SecurityEnvironmentCommand,com.ait.util.StringUtil" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="loginAdmin" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap" scope="request"></jsp:useBean>
<jsp:useBean id="dataMapList" class="com.ait.sqlmap.util.SimpleMap" scope="request"></jsp:useBean>
<jsp:useBean id="applyList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="affirmorList" class="java.util.ArrayList"></jsp:useBean>
<jsp:useBean id="seCommand" class="com.ait.ga.servlet.SecurityEnvironmentCommand"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>隐患整改反馈情况</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function ScenePhotos(photosid){
photosid = photosid+"1";
//window.open("/ajaxControlServlet?operation=uploadImp&correctiveplan=correctiveplan&documentno="+photosid,"","resizable,scrollbars,dependent,width=500,height=400,left=250,top=300");
window.open("/safeControlServlet?operation=securityChecks&content=viewPhoto&menu_code=${param.menu_code}&documentno="+photosid,"","resizable=no,scrollbars,dependent,width=350,height=100,left=350,top=500");
}
 function search(){
 document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=securityEnvironment&content=completedRectificationAffirmInfo";
 document.form1.submit(); 
 }
 
  function showMemo(val) {
     var memo = $('opition_'+val).innerHTML;
	var html = "<div style='background-color:#FFFFFF;height: 100%;text-align: left;'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 450
				  , height: 200
				 , shadow: true
				 , closable: true
				  , layout : 'fit'
				 , html : html
				 ,resizable : true
				 ,autoScroll:true
				 ,plain : true	
				 ,title : '决裁意见'
			});
		editDlg.setPosition(400,200);
		editDlg.show();	
} 
</SCRIPT>

<body>
<form name="form1" method="post" action="">
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/gatoolbar_none.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->
	    <table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="title1"><!-- 查询条件 -->
		<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>		
				</td>
			</tr>
		    <tr>        
		      <td colspan="9">
		      <table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
		        <tr>
		          <td  class="info_title_01" nowrap="nowrap"><!-- 开始日期 -->
		    <ait:message messageID="display.mutual.start_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00" nowrap="nowrap"><input type="text" name="startDate" style="width:70px" value="${startDate}" readonly onClick="setday(this);" /></td>
		          <td  class="info_title_01"><!--  结束日期-->
		  <ait:message messageID="display.mutual.end_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00">
		          <input type="text" name="endDate" style="width:70px" value="${endDate}" readonly onClick="setday(this);" /></td>                                                                                                                      
		           
		       	  <td class="info_title_01" nowrap="nowrap"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
		          <td class="info_content_00" nowrap="nowrap">
				     <select name="qryType">
				         <option value="">全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未决裁</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已通过</option>
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决</option>                
				     </select>
				  </td>                
		        </tr> 
		        <tr>
		         <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <ait:selDept name="deptid" supervisorType="pa" all="all" selected="${deptid}"/>
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}"  title="输入姓名或者职号"/></td>       
	
		           <td  class="info_title_01">&nbsp;
		          </td>
		         <td  class="info_content_00">
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />
		         </td>              
		        </tr>      
		        </table>
		      </td>
			</tr>
			</table>
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				隐患整改反馈情况
				</td>
			</tr>     
		</table>			
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		       <td nowrap="nowrap" class="info_title_01">
				编号</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请人</td>
		      <td nowrap="nowrap" class="info_title_01">
				部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				整改要求日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				实际完成日期</td>	
			  <td nowrap="nowrap" class="info_title_01">
				整改情况及结果（少于500个汉字）</td>
		      <td nowrap="nowrap" class="info_title_01">
				整改完成照片</td>			 
			  <td nowrap="nowrap" class="info_title_01">
				决裁</td>
			  <td nowrap="nowrap" class="info_title_01">
				确认</td>
			  <td nowrap="nowrap" class="info_title_01">意见</td>
			  <td nowrap="nowrap" class="info_title_01">操作</td>
		    </tr>			
		    <c:forEach items="${applyList}" var="all" varStatus="i">
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >${all.SECURITYCHECKSNO }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${all.CHINESENAME }</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${all.DEPTNAME }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${all.COMPLETIONDATE }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${all.OVERDATE }</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${all.CRFNOTE }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01"><span style="color:red; cursor:pointer;" onClick="ScenePhotos('${all.SECURITYCHECKSNO }')">查看整改完成照片</span></td>
		       <td nowrap="nowrap" class="info_content_01">
				     <c:forEach items="${all.affirmorList}" var="view" varStatus="j">
				      	${view.AFFIRM_LEVEL}${view.CHINESENAME}
				      	<c:if test="${view.AFFIRM_FLAG==0}"><font color="#00CC00">未通过</font></c:if>
				      	<c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
				      	<c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
				      	<br>
			      	 </c:forEach>
			      </td>
			      <td nowrap="nowrap" class="info_content_01">
			      	<c:if test="${all.ISRECTIFICATION == 0 || all.ISRECTIFICATION == 3}"><font color="red">未确认 </font></c:if>
			      	<c:if test="${all.ISRECTIFICATION == 1}"><font color="#00CC00">已确认</font></c:if>
			      	<c:if test="${all.ISRECTIFICATION == 2}"><font color="red">以否决</font></c:if>
			      </td>
				<div id="opition_${i.index}" style="display: none;">
					<c:forEach items="${all.affirmorList}" var="view">
							  <p>
							  <font color="#990099">${view.AFFIRM_LEVEL}${view.CHINESENAME}</font>
					          <c:if test="${view.AFFIRM_FLAG==0}"><font color="#00CC00">未决裁</font></c:if>
						      <c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
						      <c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
					          &nbsp;${view.AFFIRMORIDEA}
					          </p>
							
					</c:forEach>
					<p>
					<c:if test="${all.CHINESENAME1!=null}"><font color="#990099">${all.CHINESENAME1}</font></c:if>
					<c:if test="${all.CHINESENAME1==null}"><font color="#990099">c&nbsp;&nbsp;担当</font> </c:if>							
					<c:if test="${all.ISRECTIFICATION == 3 || all.ISRECTIFICATION == 0}"><font color="red">未确认</font></c:if>
			      	<c:if test="${all.ISRECTIFICATION == '1' }"><font color="#00CC00">已确认</font></c:if>
			      	<c:if test="${all.ISRECTIFICATION == '2'}"><font color="red">以否决</font></c:if>
		          	&nbsp;${all.CONFIRMIDEA}				          	
		          	</p>
				
			</div>
			<td class="info_content_01">
			<a href="#" onClick="showMemo('${i.index}')">详情</a></td>
			<td nowrap="nowrap" align="center" class="info_content_01" >
			<c:if test="${all.AFFIRM_FLAG==0 && all.isfalg == '0'}">
			<a href="gaControlServlet?menu_code=${param.menu_code}&operation=securityEnvironment&content=DelAffirmInfo&SECURITYCHECKSNO=${all.SECURITYCHECKSNO }" onclick="return(confirm('确认删除吗？这将清空所有的相关信息！'))"><font color="red">删除</font></a> 
			</c:if>
			<c:if test="${all.AFFIRM_FLAG != 0}">&nbsp;</c:if>
			</td>
			      </c:forEach>			    
			 <input type="hidden" name="currentPage" value="${currentPage}">	   		
		 </table>
		 		
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gaControlServlet"
		               parameters="&operation=securityEnvironment&content=completedRectificationAffirmInfo&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&qryType=${qryType}&deptid=${deptid}&key=${key}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</form>

</body>

</html>
