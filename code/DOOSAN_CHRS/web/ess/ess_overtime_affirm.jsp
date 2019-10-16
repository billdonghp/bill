<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.util.NumberUtil"%>
<%@ page import="com.ait.util.DateUtil"%>
<%@ page import="com.ait.ess.bean.EssOverTimeBean"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="overTimeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="otLengthList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essOverTimeBean" class="com.ait.ess.bean.EssOverTimeBean" scope="page" />
<jsp:useBean id="essAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="essAffirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<jsp:useBean id="sDate" class="java.lang.String" scope="request" />
<jsp:useBean id="eDate" class="java.lang.String" scope="request" />

<html>
<head>
<!-- ess_overtime_affirm.jsp -->
<%@ include file="../inc/meta.jsp" %>
<style>
#leftnewstd .ellipsis_row{width:40px}
.ellipsis_row{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;
}
#leftnewstd .ellipsis_row2{width:70px}
.ellipsis_row2{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;
}
</style>
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
<script type="text/javascript">
function showMemo(val) {
	var memo = document.getElementById('memo_'+val).value;
	var html = "<div style='background-color:#FFFFFF;height: 100%'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 350
				  , height: 150
				 , shadow: true
				 , closable: true
				  ,autoScroll: true
				  , layout : 'fit'
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
} 
function showMemo2(val) {
	var memo = document.getElementById('memo2_'+val).value;
	var html = "<div style='background-color:#FFFFFF;height: 100%'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 350
				  , height: 150
				 , shadow: true
				 , closable: true
				  ,autoScroll: true
				  , layout : 'fit'
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
} 
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
	document.ApplyForm.content.value="otaffirm";
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

function exportExcel()
{
	document.ApplyForm.content.value="overtimeAffirmExcel";
	document.ApplyForm.submit();
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
		          <td  class="info_title_01" nowrap="nowrap"><!-- 开始日期 -->
		    <ait:message messageID="display.mutual.start_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00" nowrap="nowrap"><input type="text" name="sDate" size="10" maxlength="10" value="${sDate}" readonly onClick="setday(this);" /></td>
		          <td  class="info_title_01"><!--  结束日期-->
		  <ait:message messageID="display.mutual.end_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00">
		          <input type="text" name="eDate" size="10" maxlength="10" value="${eDate}" readonly onClick="setday(this);" /></td>                                                                                                                      
		           
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
		          <td class="info_content_00" nowrap="nowrap">
		          <select name="deptID">
			  		<option value="">请选择</option>
			  		<c:forEach var="dept" items="${deptList}" varStatus="i">
			  			<option value="${dept.DEPTID}" <c:if test="${dept.DEPTID == deptID}">selected</c:if> >
			  				<c:forEach begin="1" end="${dept.DEPT_LEVEL}">&nbsp;&nbsp;</c:forEach>${dept.DEPTNAME}</option>
			  	    </c:forEach>
			    </select>
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}" /></td>       
	
		           <td  class="info_title_01">&nbsp;
		          </td>
		         <td  class="info_content_00">
		         &nbsp;</td>              
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
			    <td class="info_title_01" width="15"><a href="#" onclick="checkAll();" style="color:red;" >
			    <!-- 全选 -->
			  <ait:message messageID="display.mutual.select_2" module="ess"></ait:message>  	   
			    </a></td>
			    <td class="info_title_01" width="15">序号</td>
			   	<td class="info_title_01" nowrap>职号</td>
				<td class="info_title_01" nowrap>姓名
				</td>
			   <td class="info_title_01" nowrap><!-- 部门 --> <ait:message
					messageID="display.mutual.department"></ait:message></td>
				<td class="info_title_01" nowrap><!--  职级--> <ait:message
					messageID="display.mutual.post_grade"></ait:message></td>	
				<td class="info_title_01" nowrap><!--  申请日期--> <ait:message
					messageID="display.mutual.request_date" module="ess"></ait:message>
				<!-- <td class="info_title_01" nowrap>班次日期</td> -->
				<td class="info_title_01" nowrap><!-- 加班类型 -->加班<br>类型
				</td>
				<td class="info_title_01" nowrap><!-- 加班时段 --> <ait:message
					messageID="display.ess.confirmation.overtime.period" module="ess"></ait:message>
				</td>
				<td class="info_title_01" >预计<br>长度
				</td>
				
				<td class="info_title_01" >创建者
				</td>
				
				<td class="info_title_01" >已申请<br>加班
				</td>
				<td class="info_title_01" nowrap><!--  工作内容 -->
			工作<br>内容</td>
			    <td class="info_title_01" nowrap>决裁<Br>意见
			    </td>
			    <td class="info_title_01" nowrap><!--  决裁情况-->
			 <ait:message
					messageID="display.mutual.status_2" module="ess"></ait:message>   
			    </td>
			    
			  </tr>
			<%for(int i=0;i<overTimeList.size();i++){
				essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);
			    essAffirmorList = essOverTimeBean.getAffirmorList();
			    int applyNo = essOverTimeBean.getOtNo();
			    int affirmNo = 0;
			    String person_id="";
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
			    <td class="info_content_09" ><%if (essOverTimeBean.getOpFlag()>=0) {%><input type="checkbox" name="affirmNo" value="<%=affirmNo%>" <%=checkState%>>
			    <input type="hidden" name="applyNo<%=affirmNo%>" value="<%=applyNo%>">
			    <input type="hidden" name="person_id<%=person_id%>" value="<%=person_id%>">
			    <input type="hidden" id="opFlag<%=affirmNo%>" value="<%=opFlag%>"><%} else {%>&nbsp;<%}%>
			    </td>
			    <td class="info_content_09" nowrap><%= i + 1 %></td>
			    <td class="info_content_09" id="leftnewstd">
			    <span class=ellipsis_row title='<%=essOverTimeBean.getEmpId()%>'><%=essOverTimeBean.getEmpId()%></span>
			    </td>
			    <td class="info_content_09" id="leftnewstd">
			    <span class=ellipsis_row title='<%=StringUtil.checkNull(essOverTimeBean.getChineseName())%>'><%=StringUtil.checkNull(essOverTimeBean.getChineseName()) %></span>
			    </td>
			    <td class="info_content_09" id="leftnewstd">
			    <%
			    	if (essOverTimeBean.getDeptName().equals(essOverTimeBean.getFourthDeptName())){
			    %>
			    	<span class=ellipsis_row2 title='<%=StringUtil.checkNull(essOverTimeBean.getDeptName())%>'><%=StringUtil.checkNull(essOverTimeBean.getDeptName()) %></span>
			    <% 
			    	}else{
			    %>		
			    	<span class=ellipsis_row2 title='<%=StringUtil.checkNull(essOverTimeBean.getFourthDeptName())%> <%=StringUtil.checkNull(essOverTimeBean.getDeptName()) %>'>
			    	<%=StringUtil.checkNull(essOverTimeBean.getFourthDeptName())%><br><%=StringUtil.checkNull(essOverTimeBean.getDeptName()) %></span>
			    <%  } %>
			    </td>
			   
			    <td class="info_content_09" id="leftnewstd">
			    <span class=ellipsis_row title='<%=StringUtil.checkNull(essOverTimeBean.getPostGradeCode())%>'><%=StringUtil.checkNull(essOverTimeBean.getPostGradeCode()) %></span> 
			   </td>
			    <td class="info_content_09" nowrap><%=essOverTimeBean.getCreateDate()%></td>
			    <!-- <td class="info_content_09" nowrap><%=essOverTimeBean.getOtDate()%></td> -->
			    <td class="info_content_09" nowrap id="leftnewstd"><span class=ellipsis_row title='<%=StringUtil.checkNull(essOverTimeBean.getOtTypeName())%>'>
			    	<%if(essOverTimeBean.isForceType())out.println("<font color=\"red\">"); %> 
			    	<%=StringUtil.checkNull(essOverTimeBean.getOtTypeName())%>
			    	<%if(essOverTimeBean.isForceType())out.println("</font>"); %>
			    	</span>
				</td>
			    <td class="info_content_09" nowrap>
			    <table>
			    <tr>
			    <td>
			    <%=subDate(essOverTimeBean.getOtFromTime())%>
			    <input type="hidden" name="fromDate<%=affirmNo%>" value="<%=subDate(essOverTimeBean.getOtFromTime())%>">  			    
			    </td>			    
			    <td> 
			    <%=subTime(essOverTimeBean.getOtFromTime())%>
			    <input  name="fromTime<%=affirmNo%>" type="hidden" value="<%=subTime(essOverTimeBean.getOtFromTime())%>">
			    </td>
			     <tr>	           
			     <td>
			      <%=subDate(essOverTimeBean.getOtToTime())%>
			      <input type="hidden" name="toDate<%=affirmNo%>" value="<%=subDate(essOverTimeBean.getOtToTime())%>">
			      </td>			      
			      <td> 
			      <%=subTime(essOverTimeBean.getOtToTime())%>
			       <input  name="toTime<%=affirmNo%>" type="hidden" value="<%=subTime(essOverTimeBean.getOtToTime())%>">
			      </td>			      
			      </tr>
			      </table>
			      </td>
			    <td align="center" nowrap>
			    <%=essOverTimeBean.getOtLength()%>
			    <input type="hidden" name="otLength<%=affirmNo%>" value="<%=essOverTimeBean.getOtLength()%>">
			    </td>
			    
			    <td align="center" nowrap>
			    <%=essOverTimeBean.getCreChinesename()%>
			    <input type="hidden" name="otLength<%=affirmNo%>" value="<%=essOverTimeBean.getCreChinesename()%>">
			    </td>
			    
			     <td align="center" nowrap>
			     <% 
		         	if (admin.getCpnyId().equals("60000000") || admin.getCpnyId().equals("78000000")){
		         		out.println(essOverTimeBean.getAppply_ot() + "/" + essOverTimeBean.getOtTotal()) ;
		         	}else{
		         		out.println(essOverTimeBean.getOtTotal()) ;
		         	}
		         %>
				小时</td>
			    
			    <td class="info_content_00" nowrap="nowrap">
		    	<a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo('<%=essOverTimeBean.getOtNo()%>');">
		    		<span class=ellipsis_row><%=StringUtil.checkNull(essOverTimeBean.getOtRemark()) %></span>
	            </a>
		    	<input type = "hidden"
		    		   id="memo_<%=essOverTimeBean.getOtNo()%>" 
		    		   value="<%=StringUtil.checkNull(essOverTimeBean.getOtRemark()) %>" />	            
            	</td>
			    
			 <%
			 	boolean flage = false;
				for(int j=0;j<essAffirmorList.size();j++){
				    essAffirmor = (EssAffirmor) essAffirmorList.get(j);
			    	if (essAffirmor.getAffirmorId().equals(admin.getAdminID())) {
				    	if (essOverTimeBean.getOpFlag() == 0&&flage==false) {
				    		flage = true;	
			 %><td width='100' style='word-break:break-all' align="left">
				    	<textarea name="remark<%=affirmNo%>" style=" height: 25px;width:100px " type="_moz"
						onfocus="this.style.height='50px'" onblur="this.style.height='25px';"></textarea>
				</td>
			 <%}else if(essOverTimeBean.getRemark()!=null){%>
			 <td class="info_content_00" nowrap="nowrap">
		    	<a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo2('<%=essOverTimeBean.getOtNo()%>');">
		    		<span class=ellipsis_row><%=StringUtil.checkNull(essOverTimeBean.getRemark()) %></span>
	            </a>
		    	<input type = "hidden"
		    		   id="memo2_<%=essOverTimeBean.getOtNo()%>" 
		    		   value="<%=StringUtil.checkNull(essOverTimeBean.getRemark()) %>" />	            
            </td>
			 <%	}else{%><td>&nbsp;</td>
			 <%}}}
			 %>
			    <td align="center" nowrap>                              
				    <%
				    for(int j=0;j<essAffirmorList.size();j++){
				    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
				    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
				     <%=StringUtil.checkNull(essAffirmor.getAffirmorName()) + StringUtil.getString((4 - essAffirmor.getAffirmorName().length()) , "&nbsp;&nbsp;&nbsp;&nbsp;") + StringUtil.checkNull(essAffirmor.getUpdateDate()) %>&nbsp;
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
		               parameters="&operation=view&content=otaffirm&menu_code=${param.menu_code}&sDate=${sDate}&eDate=${eDate}&deptID=${deptID}&key=${key}&qryType=${qryType}" 
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
<input type="hidden" name="content" value="otaffirm" />
<input type="hidden" name="flag" value="" />
<input type="hidden" name="menu_code" value="<%=menu_code%>" />
<input type="hidden" name="ck_all" value="0" />
<input type="hidden" name="currentPage" value="${currentPage}" />
</form>
</body>
</html>
