<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../inc/taglibs.jsp"%>  
<table width="100%" border="0" cellpadding="0" cellspacing="0"　class="dr_d" height="154">
  <tr>
    <td class="title1" colspan="2" >
    	<!-- 基本信息 -->
    	<ait:message  messageID="display.ess.staff_info.basic_info" module="ess"/>
    </td>
  </tr>         
  <tr>
    <td width="115">
	<table  border="0" cellpadding="0" cellspacing="1" class="dr_d">
        <tr>
          <td width="101" height="121" align="center" valign="middle" bgcolor="#E7F0EF" style="padding:2px;">
		  <img align="middle" src="upload/photo/<c:out value='${basic.empID}'/>.jpg" width="100" height="120"></td>
        </tr>
      </table>          
    </td>

    <td><table width="100%" height="121"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
        <tr>
          <td width="13%" class="info_title_01"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
          <td width="14%" class="info_content_00" nowrap style="padding-top: 2px;padding-bottom: 2px;">
          <ait:content enContent='${basic.pinyinName}' zhContent='${basic.chineseName}' koContent='${basic.koreanName}'/>
          (<c:out value='${basic.pinyinName}'/>)</td>
          <td width="13%" class="info_title_01" ><!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
          <td width="15%" class="info_content_00" style="padding-top: 2px;padding-bottom: 2px;">
          <c:out value='${basic.empID}'/>
          <td width="13%" class="info_title_01" ><!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
          <td colspan="3" class="info_content_00" style="padding-top: 0px;padding-bottom: 0px;">
	          <!--<c:out value='${basic.deptFullName}'/>-->
	          <ait:content enContent='${basic.englishDept}' zhContent='${basic.deptFullName}' koContent='${basic.korDept}'/>
          </td>
        </tr>              
        <tr>
          <td class="info_title_01" ><!-- 员工类别 --><ait:message  messageID="display.ess.staff_info.basic_info.staff_type" module="ess"/></td>
          <td class="info_content_00" ><!--<c:out value='${basic.joinType}'/>-->
           	<ait:content enContent='${basic.enJoinType}' zhContent='${basic.joinType}' koContent='${basic.korJoinType}'/>
          </td>
          <td class="info_title_01" ><!-- 职群 --><ait:message  messageID="display.mutual.post_group"/></td>
          <td class="info_content_00" ><!--<c:out value='${basic.postGroup}'/>-->
          	<ait:content enContent='${basic.enPostGroup}' zhContent='${basic.postGroup}' koContent='${basic.korPostGroup}'/>
          </td>
          <td class="info_title_01" ><!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
          <td class="info_content_00"><!--<c:out value='${basic.post}'/>--> 
          	<ait:content enContent='${basic.englishPost}' zhContent='${basic.post}' koContent='${basic.korPost}'/>
         </td>
        </tr>
        <tr>
          <td class="info_title_01"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
          <td class="info_content_00" >
          	<ait:content enContent='${basic.englishPosition}' zhContent='${basic.position}' koContent='${basic.korPosition}'/>
          </td>
          <td class="info_title_01" ><!-- 职级 --><ait:message  messageID="display.mutual.post_grade"/></td>
          <td class="info_content_00" ><!--<c:out value='${basic.postGrade}'/>-->
          	<ait:content enContent='${basic.enPostGrade}' zhContent='${basic.postGrade}' koContent='${basic.korPostGrade}'/>
          </td>
          <td class="info_title_01" ><!-- 级号 --><ait:message  messageID="display.mutual.pay_grade"/></td>
          <td class="info_content_00" ><c:out value='${basic.postCoef}'/></td>
        </tr>
        <tr>
          <td class="info_title_01" ><!-- 员工状态 --><ait:message  messageID="display.mutual.staff_status"/></td>
          <td class="info_content_00" ><!--<c:out value='${basic.status}'/>-->
          	<ait:content enContent='${basic.englishStatus}' zhContent='${basic.status}' koContent='${basic.korStatus}'/>
          </td>
          <td class="info_title_01" ><!--入社日期--><ait:message  messageID="display.ess.staff_info.basic_info.hire_date"   module="ess"/></td>
          <td class="info_content_00" >${basic.joinDate}</td>
          <td class="info_title_01" ><!--卡号--><ait:message  messageID="display.mutual.id_card"/></td>  
          <td class="info_content_00" ><c:out value='${basic.cardNO}'/></td>
        </tr>
      </table></td>                           
  </tr>
</table>
<br>