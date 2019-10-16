<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.util.DateUtil"%>
<%@ page import="com.ait.ess.bean.EssOverTimeBean"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="overTimeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essOverTimeBean" class="com.ait.ess.bean.EssOverTimeBean" scope="page" />
<jsp:useBean id="essAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="essAffirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />

<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head> 
<body>
<%!
    String subDate(String value){
      return value.substring(0,10);
    }
    String subTime(String value){
      return value.substring(11);
    }
%>
<script language="javascript1.5" type="text/javascript">
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

function checkAll()
{
  var selected = document.ApplyForm.ck_all.value == "0" ? true : false;
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" && !document.ApplyForm.elements[i].disabled)
    {
      document.ApplyForm.elements[i].checked = selected;
    }
  }
  document.ApplyForm.ck_all.value = selected ? "1" : "0";
}

function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.currentPage.value='0';
	document.ApplyForm.submit();
}

//单个决裁
function doAffirm_single(element, flag) {
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" && !document.ApplyForm.elements[i].disabled)
       document.ApplyForm.elements[i].checked = false;
  }  
  var row = findRow(element);  
  var jumpCyc = false;  
  for(k = 0 ; k < row.cells.length; k++){
      var objects = row.cells[k].all;
      for (j = 0; j < objects.length; j++){
        if(objects[j].type=="checkbox"){
           objects[j].checked = !objects[j].disabled;
           jumpCyc = true;
           break;
        }
      }
      if (jumpCyc) break;
  }
  doAffirm(flag);
}
//批量决裁
// flag 1 通过, 2 否决
function doAffirm(flag){
     var msg1='<ait:message messageID="alert.ess.common.cofirmpass" module="ess"></ait:message>';
	 var msg ='<ait:message messageID="alert.ess.common.checkpass" module="ess"></ait:message>';
	if (flag == "2") {
		msg ='<ait:message messageID="alert.ess.common.checkreject" module="ess"></ait:message>';
		msg1='<ait:message messageID="alert.ess.common.cofirmreject" module="ess"></ait:message>';
	}
	var existSelected = false;//是否有选择的记录
  for(var i=0;i<document.ApplyForm.elements.length;i++)    
  {
    if(document.ApplyForm.elements[i].type=="checkbox" 
        && document.ApplyForm.elements[i].checked && !document.ApplyForm.elements[i].disabled)
    {
      var affirmNo = document.ApplyForm.elements[i].value;
      var currenFlag = document.getElementById("opFlag" + affirmNo).value;
      if (currenFlag != "0" && currenFlag != flag) {//当前记录只能通过或否决，与操作不一致，忽略
          document.ApplyForm.elements[i].checked = false;
      } else
        existSelected = true;
    }
  }
  if (!existSelected){
      alert(msg);
      return false;
  }
	if (confirm(msg1)) {
		document.ApplyForm.operation.value="modify";
		document.ApplyForm.flag.value=flag;
		document.ApplyForm.submit();
	}
  
}


</script>
<%
   String qryType = request.getAttribute("qryType").toString(); 
%>
<%! String selected(String valueSel, String value){
      return valueSel.equals(value) ? "selected" : "";
    }
 %>
<form name="ApplyForm" action="/essControlServlet" method="post">
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="15"></td>
			<td width="11" height="33" valign="TOP" align="RIGHT"><img
				src="../images/tablbk01_r1_c1.gif"></td>
			<td background="../images/tablbk01_r1_c2.gif">
			
				<!-- display toolbar -->
				<%@ include file="../ess/inc/ess_toolbar_affirm.jsp"%>
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
			<br>
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
		          <td  class="info_title_01" nowrap="nowrap">加班月份</td>
		          <td  class="info_content_00" nowrap="nowrap"><ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" /></td>
		         <td class="info_title_01" nowrap="nowrap"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
		          <td class="info_content_00" nowrap="nowrap">
				     <select name="qryType">
				         <option value="0" <%= selected("0", qryType) %> ><!-- 全部 -->
				   <ait:message messageID="display.ess.approval.select_all" module="ess"></ait:message>      
				         </option>   
				         <option value="1" <%= selected("1", qryType) %>><!-- 已决裁 -->
				   <ait:message messageID="display.ess.approval.verified" module="ess"></ait:message>        
				         </option>
				         <option value="2" <%= selected("2", qryType) %>><!-- 未决裁 -->
				     <ait:message messageID="display.ess.approval.pending" module="ess"></ait:message>      
				         </option>                
				     </select>
				  </td>          
		        </tr> 
		        <tr>
		         <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap"><ait:selDept name="deptID" selected="${deptID}" all="all"  supervisorType="ar"/></td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}" /></td>                  
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
		 <ait:message messageID="display.ess.approval.overtime.ot_approval" module="ess"></ait:message>  				
					<!-- 加班决裁 --></td>
				</tr>
			</table>
			<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			  <tr>
			    <td class="info_title_01" nowrap><a href="#" onclick="checkAll();" style="color:red;" >
			    <!-- 全选 -->
			  <ait:message messageID="display.mutual.select_2" module="ess"></ait:message>  	   
			    </a></td>
			    <td class="info_title_01" nowrap>序号</td>
			   	<td class="info_title_01" nowrap><!--工号  --> 
			   	<ait:message messageID="display.mutual.emp_id"></ait:message></td>
				<td class="info_title_01" nowrap><!--加班人  --> <ait:message messageID="display.ess.confirmation.overtime.employee" module="ess"></ait:message></td>
			    <td class="info_title_01" nowrap><!-- 部门 --> <ait:message messageID="display.mutual.department"></ait:message></td>
				<td class="info_title_01" nowrap><!--  职位--> <ait:message messageID="display.mutual.position"></ait:message></td>
				<td class="info_title_01" nowrap>加班月份</td>	
				<td class="info_title_01" nowrap><!--  申请日期--> <ait:message messageID="display.mutual.request_date" module="ess"></ait:message></td>
				<td class="info_title_01" nowrap>原有上限</td>
				<td class="info_title_01" nowrap>申请小时</td>
				<td class="info_title_01" nowrap>加班事由</td>
			    <td class="info_title_01" nowrap><!--  决裁情况-->
			 <ait:message
					messageID="display.mutual.status_2" module="ess"></ait:message>   
			    </td>
			  </tr>
			<%for(int i=0;i<overTimeList.size();i++){
				essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);
			    String remark = StringUtil.checkNull(essOverTimeBean.getOtRemark(),"");
			    essAffirmorList = essOverTimeBean.getAffirmorList();
			    int applyNo = essOverTimeBean.getOtNo();
			    int affirmNo = 0;
			    int opFlag = essOverTimeBean.getOpFlag();
			    if (opFlag >= 0) {
			      for(int j=0;j<essAffirmorList.size();j++){
				    essAffirmor = (EssAffirmor) essAffirmorList.get(j);
			        if (essAffirmor.getAffirmorId().equals(admin.getAdminID())) {
			            affirmNo = essAffirmor.getAffirmNo();
			            break ;
				    }  
				  }  		
				}  
				String checkState = affirmNo > 0 ? "" : "disabled";
			%>
			  <tr align="center">
			    <td class="info_content_01" nowrap><%if (essOverTimeBean.getOpFlag()>=0) {%><input type="checkbox" name="affirmNo" value="<%=affirmNo%>" <%=checkState%>>
			    <input type="hidden" name="applyNo<%=affirmNo%>" value="<%=applyNo%>">
			    <input type="hidden" name="topLimit<%=affirmNo%>" value="<%= essOverTimeBean.getLimit_ot() %>">
			    <input type="hidden" id="opFlag<%=affirmNo%>" value="<%=opFlag%>"><%} else {%>&nbsp;<%}%>
			    </td>
			    <td class="info_content_01" nowrap><%= i + 1 %></td>
			    <td class="info_content_01" nowrap><%=essOverTimeBean.getEmpId()%></td>
			    <td class="info_content_01" nowrap>
	    <ait:content enContent='<%=StringUtil.checkNull(essOverTimeBean.getChinesePinYin()) %>'  
	       zhContent= '<%=StringUtil.checkNull(essOverTimeBean.getChineseName())%>'  
	       koContent='<%=StringUtil.checkNull(essOverTimeBean.getKoreanname()) %>'>        
	       </ait:content>&nbsp;
			   </td>
			    <td class="info_content_01" nowrap>
			     <ait:content enContent='<%=StringUtil.checkNull(essOverTimeBean.getDeptEnName()) %>'  
	       zhContent= '<%=StringUtil.checkNull(essOverTimeBean.getDeptName())%>'  
	       koContent='<%=StringUtil.checkNull(essOverTimeBean.getDeptKorName())%>'>   
	       </ait:content>&nbsp;	    
			    </td>
			    <td class="info_content_01" nowrap>
			     <ait:content enContent='<%=StringUtil.checkNull(essOverTimeBean.getEnPosition())%>'  
	       zhContent= '<%=StringUtil.checkNull(essOverTimeBean.getPosition())%>'  
	       koContent='<%=StringUtil.checkNull(essOverTimeBean.getKorPosition())%>'>   
	       </ait:content>&nbsp;	 
			   </td>
			    <td class="info_content_01" nowrap><%= essOverTimeBean.getOtPlanMonth() %></td>
			    <td class="info_content_01" nowrap><%=essOverTimeBean.getCreateDate()%></td>
			    <td class="info_content_01" nowrap><%= StringUtil.checkNull(essOverTimeBean.getLimit_ot(),"&nbsp;")%></td>
				<td class="info_content_01" nowrap><input type="text" name="otLength<%=affirmNo%>" size="5" value="<%= essOverTimeBean.getOtLength() %>"/></td>
			    <td align="center" nowrap><textarea name="remark<%=affirmNo%>" style=" height:20px;width:180px " type="_moz" onfocus="this.style.height='40px'" onblur="this.style.height='20px'"><%=remark%></textarea> </td>
			    <td align="center" nowrap>                              
				    <%
				    for(int j=0;j<essAffirmorList.size();j++){
				    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
				    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
				      <ait:content enContent='<%=StringUtil.checkNull(essAffirmor.getAffirmorEnName()) %>'  
	                    zhContent= '<%=StringUtil.checkNull(essAffirmor.getAffirmorName())%>'  
	                   koContent='<%=StringUtil.checkNull(essAffirmor.getAffirmorKorName())%>'>   
	                  </ait:content>&nbsp;	
			    		<%=(String) statusMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>
			    		<%if (essAffirmor.getAffirmorId().equals(admin.getAdminID())) {
				    		if (essOverTimeBean.getOpFlag() == 0) {%>
				    			<br><span style="color:red; cursor:pointer;" 
				    			onClick="doAffirm_single(this, 1);">
				    			<!-- 通过 --><ait:message messageID="display.ess.approval.approved" module="ess"></ait:message> 
				    			</span>&nbsp;|&nbsp;<span style="color:red; cursor:pointer;" 
				    			onClick="doAffirm_single(this, 2);">
				    			<!-- 否决 --><ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message> 	</span>
			    		<%} else if (essOverTimeBean.getOpFlag() == 1) {%>
			    			<br><span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 1);">
			    			<!-- 通过 --><ait:message messageID="display.ess.approval.approved" module="ess"></ait:message> </span>
			    		<%} else if (essOverTimeBean.getOpFlag() == 2) {%>
			    			<br><span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 2);">
			    			<!--  否决--><ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message> 	</span>
						<%}
					}%>
			    	</font><br>            
			    <%}%>
		    </td>
		  </tr>
		<%}%>
		</table>
		
					  <!-- Page Navigation Start-->
					<ait:page       
		               link="/essControlServlet"
		               parameters="&operation=view&content=ottoplimitaffirm&menu_code=${param.menu_code}&year=${year}&month=${month}&deptID=${deptID}&key=${key}&qryType=${qryType}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
		            
		            <!-- Page Navigation End -->		
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="15">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
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

<input type="hidden" name="operation" value="view" />
<input type="hidden" name="content" value="ottoplimitaffirm" />
<input type="hidden" name="flag" value="" />
<input type="hidden" name="menu_code" value="<%=menu_code%>" />
<input type="hidden" name="ck_all" value="0" />
<input type="hidden" name="currentPage" value="${currentPage}" />
</form>
</body>
</html>
