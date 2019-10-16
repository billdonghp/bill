<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.ga.bean.*,com.ait.ga.cmd.visit.*,com.ait.gm.cmd.WpConfirm"%>
<%@ page import="com.ait.utils.FormUtil,com.ibm.icu.text.SimpleDateFormat,com.ait.util.*" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="NotConfirmDocumentNO" class="java.lang.String" scope="request" />
<jsp:useBean id="documentNo" class="java.lang.String" scope="request" />
<jsp:useBean id="documentid" class="java.lang.String" scope="request" />
<jsp:useBean id="isCanConfrim" class="java.lang.String" scope="request" />
<jsp:useBean id="confirmDocumentList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="documentListSize" class="java.lang.String" scope="request" />
<jsp:useBean id="wpb" class="com.ait.ga.bean.WoodProductsBean"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>刻章确认</title>
</head>
<SCRIPT type="text/javascript">
function ImportExcel() {	
   var selectFlag=false;
   var countsArr=document.getElementsByName("counts");
   var applyNo="";
   for(var i=0;i<countsArr.length;i++){
      if(countsArr[i].checked){
       selectFlag=true;
       applyNo=document.form1["applyNo_"+countsArr[i].value].value;
      }   
   }
   if(!selectFlag){
       alert("请选择要导出的信息！");
	   return;
   }
	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=wpApplyConfirmForExcel&applyNoExcel="+applyNo;
	document.form1.submit();
}

function ImportSummaryExcel(){
	 if($('startDate').value==""||$('startDate').value==null){
	   alert("请选择开始日期");
	   return;
	 }
	 if($('endDate').value==""||$('endDate').value==null){
	   alert("请选择结束日期");
	   return; 
	 }
    document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=wpApplyConfirmSummaryExcel" ;
	document.form1.submit();

}

function Search() {
		 document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=wpApplyConfirm";
		 document.form1.submit();
}

function checkAll()
	{
	    var selected = document.form1.ck_all.value == "0" ? true : false;
	    var countsArr = document.getElementsByName("counts") ;
	  	for (var i = 0; i< countsArr.length ; i++){
			countsArr[i].checked = selected ;
		
		}
	    document.form1.ck_all.value = selected ? "1" : "0";
	}

 function singleAffirm(affirmFlag,counts){
   var countsArr=document.getElementsByName("counts"); 
   countsArr[counts].checked="selected";   
     if(affirmFlag==2){
       doReject();
     }else if(affirmFlag==1){
      doAffirm();
     }else{
      doOK();
     }    
 
 }

function doAffirm(){
   var selectFlag=false;
   var countsArr=document.getElementsByName("counts");
   for(var i=0;i<countsArr.length;i++){
      if(countsArr[i].checked){
       selectFlag=true;
      }   
   }
   if(!selectFlag){
       alert("请选择要通过的信息！");
	   return;
   }
	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=wpConfirmOrdersMore&flag=1";
	document.form1.submit();
}

function doReject(){
  if(confirm("确认对已选择的信息进行否决吗？")){  
	   var selectFlag=false;
	   var countsArr=document.getElementsByName("counts");
	   for(var i=0;i<countsArr.length;i++){
	      if(countsArr[i].checked){
	           if(document.form1["confirmorIdea_"+countsArr[i].value].value==null || document.form1["confirmorIdea_"+countsArr[i].value].value==""){
			      alert("请填写否决意见！！");
			      document.form1["confirmorIdea_"+countsArr[i].value].onfocus();
			      return;
			    }
	         selectFlag=true;
	      } 
	   }  
   } 
   if(!selectFlag){
       alert("请选择要通过的信息！");
	   return;
   }
	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=wpConfirmOrdersMore&flag=2";
	document.form1.submit();
}

function doOK(){
   var selectFlag=false;
   var countsArr=document.getElementsByName("counts");
   for(var i=0;i<countsArr.length;i++){
      if(countsArr[i].checked){
       selectFlag=true;
      }   
   }
   if(!selectFlag){
       alert("请选择要通过的信息！");
	   return;
   }
	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=wpConfirmOrdersMore&flag=3";
	document.form1.submit(); 
}
function viewFile(fileAddress){
window.open(fileAddress,"","menubar=no, toolbar=no, scrollbars=no, status=no, resizable=yes, location=no, directories=no, copyhistory=no" );
}
</SCRIPT>
<body>
<%java.util.Date d = new java.util.Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
String today=timeFormatter.format(d);
String year=today.substring(0,4);    
String month=StringUtil.checkNull((NumberUtil.parseInt(today.substring(5, 7))-1));  
String day=today.substring(8,10);
String backInMotnDate="";
try {
	backInMotnDate=timeFormatter.format(timeFormatter.parse(year+"-"+month+"-"+day));
} catch (Exception e) {	
	e.printStackTrace();
}
%>
<form name="form1" method="post" action="">
<input type="hidden" name="ck_all" value="0" />			
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/gmtoolbar_affirm.jsp"%>
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
				     <select name="status">
			         	<option value="4" <c:if test="${status==4}">selected</c:if>>全部</option>
			         	<option value="0" <c:if test="${status==0}">selected</c:if>>未确认</option>
			          	<option value="1" <c:if test="${status==1}">selected</c:if>>进行中</option>
			          	<option value="3" <c:if test="${status==3}">selected</c:if>>已完成</option>
			          	<option value="2" <c:if test="${status==2}">selected</c:if>>已否决</option>
			          </select>
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:Search();" style="cursor:hand" />
		         <img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImportSummaryExcel();"/>
				  </td>                
		        </tr> 
		        <tr>		        
		         <td  class="info_title_01">法人区分</td>
		         <td  class="info_content_00">
		          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="Search();"/>
		         </td> 
		         <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <ait:selDeptByCpnyId name="deptid" supervisorType="hr" all="all" cpnyId ="${cpnyId}" selected="${deptid}"/>
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}"  title="输入姓名、职号、编号"/></td>                   
		        </tr>      
		        </table>
		      </td>
			</tr>
			</table>	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		<br>	
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				刻章确认
				</td>
			</tr>     
		</table>
		<!-- display content -->
			
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>   
		<form name="form2" method="post" action="">	
		<input type="hidden" name="tempAffirmNo" value=""/>
		<input type="hidden" name="tempAffirmNote" value=""/>
		<table width="100%"  border="1"cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		   <tr align="center" bgcolor="#F5F5F5">
		   	   <td nowrap="nowrap" class="info_title_01">
				<a href="#"onclick="checkAll();" style="color:red;"><ait:message messageID="display.mutual.select_2" module="ess" /></a>
		      </td>
		      <td nowrap="nowrap" class="info_title_01">
				申请类型</td>		     
		      <td nowrap="nowrap" class="info_title_01">
				职号</td>
			  <td nowrap="nowrap" class="info_title_01">
				姓名</td>
			  <td nowrap="nowrap" class="info_title_01">
				部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请日期</td>	
		      <td nowrap="nowrap" class="info_title_01">
				希望完成日期</td>		      
		      <td nowrap="nowrap" class="info_title_01">
				制品</td>
			  <td nowrap="nowrap" class="info_title_01">
				用途</td>			 
			  <td nowrap="nowrap" class="info_title_01">
				决裁情况</td>				 
			   <td nowrap="nowrap" class="info_title_01">
				操作</td>	
			  <td nowrap="nowrap" class="info_title_01">
				意见</td>			  	
		    </tr>
		    <input type="hidden" name="documentListSize" value="${documentListSize}"/>
		    <c:forEach items="${confirmList}" var="test" varStatus="i">
		    <tr>
		      <td nowrap="nowrap" align="center" class="info_content_01" ><c:if test="${test.CONFIRM_FLAG!=2 && test.CONFIRM_FLAG!=3 }"><input type="checkbox" name="counts" value="${i.index}"></c:if>&nbsp;</td>
		      <input type="hidden" name="applyNo_${i.index}" value="${test.APPLY_NO}">	
		      <td nowrap="nowrap"   class="info_content_01">${test.APPLYTYPE}</td>     
		      <td nowrap="nowrap"   class="info_content_01" >${test.EMPID}</td>
		      <td nowrap="nowrap"  class="info_content_01" >${test.CHINESENAME}</td>
		      <td nowrap="nowrap"  class="info_content_01" >${test.DEPTNAME}</td>
		      <td nowrap="nowrap"  class="info_content_01" >${test.APPLY_DATE}</td>
		      <td nowrap="nowrap"   class="info_content_01" >${test.HOPE_COMPLETED_DATE}</td>
		      <td nowrap="nowrap" >
		      <table align="left">
		      <c:forEach items="${test.woodProductsList}" var="woodProdust" varStatus="j">		     
		      <tr>
		      <td nowrap="nowrap" class="info_content_06">${j.index+1}</td>
		      <td nowrap="nowrap" class="info_content_06">制品名称:<font color="red">${woodProdust.WPNAME}</font></td>
		      <td nowrap="nowrap" class="info_content_06">制品数量:<font color="red">${woodProdust.WPNUMBER}个</font></td>
		      <%--<td nowrap="nowrap" class="info_content_06">单价:<font color="red">${woodProdust.MEASUREMENT_UNIT_PRICE}/元</font></td>
		      --%>
		      <td nowrap="nowrap" class="info_content_06">小计:<font color="red">${woodProdust.WP_SUMPRICE}元</font></td>
		      <td nowrap="nowrap" class="info_content_06">
		       <c:if test="${woodProdust.FILEPATH!=null}">
	            样式：<a href="#" onclick="viewFile('${woodProdust.FILEPATH}');"><font color="red">${woodProdust.FILENAME}</font></a>
	           </c:if>	 
	           <c:if test="${woodProdust.FILEPATH==null}">
	            &nbsp;
	           </c:if>  
	          </td>		      
		      </tr>
		      </c:forEach>
		      </table>
		      </td>
		      <td nowrap="nowrap" class="info_textarea_content">&nbsp;${test.NOTE}</td>
		      <td nowrap="nowrap">
              <table align="center">                          
              <c:forEach items="${test.affirmorList}" var="affirmor" varStatus="f">
		      <tr>
		      <td nowrap="nowrap">
		          <font color="#990099">${affirmor.AFFIRM_LEVEL}${affirmor.AFFIRMOR_NAME}</font>
		          <c:if test="${affirmor.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
		          <c:if test="${affirmor.AFFIRM_FLAG==1}"><font color="#00CC00">已决裁</font></c:if>
		          <c:if test="${affirmor.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
		      </td>
		      </tr>		      
		      </c:forEach>		      
		      </table>
		      </td>
		    <td nowrap="nowrap" class="info_content_01">
		    
			    <c:if test="${test.CONFIRM_FLAG==0}">
			    <span style="color:red; cursor:pointer;" onClick="singleAffirm(1,'${i.index}');">通过</span>&nbsp;&nbsp;	
			    <span style="color:red; cursor:pointer;" onClick="singleAffirm(2,'${i.index}');">否决</span>					   
				</c:if>
				
				<c:if test="${test.CONFIRM_FLAG==1}">
				    <font color="#990099">进行中</font>&nbsp;
				    <span style="color:red; cursor:pointer;" onClick="singleAffirm(3,'${i.index}');">否决</span>	
				</c:if>
				
				<c:if test="${test.CONFIRM_FLAG==2}">
				    <font color="#990099">已否决</font>
				</c:if>
				
				<c:if test="${test.CONFIRM_FLAG==3}">
				    <font color="#990099">已完成</font>
			    </c:if>
		    </td>
		    <td nowrap="nowrap" class="info_content_01">
		    	<textarea name="confirmorIdea_${i.index}" style="width:100px"  style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${test.CONFIRMORIDEA}</textarea>
		    </td>		    	    		    
		    </tr>
		    </c:forEach>	
		    <input type="hidden" name="currentPage" value="${currentPage}">	   
		 </table>
		 </form>
		 					 <!-- Page Navigation Start-->

					<ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=wpCommand&content=wpApplyConfirm&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&status=${status}&deptid=${deptid}" 
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
</body>
</html>
