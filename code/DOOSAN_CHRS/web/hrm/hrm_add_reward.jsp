<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>LSFC&gt;人事信息&gt;奖励惩戒</title>
</head>
<script language="javascript" src="../js/hr_add_reward.js"></script>
<SCRIPT type="text/javascript">
function Save(){
	
	if(CheckForm()) {
	
		document.form1.action="hrmControlServlet?menu_code=hr0104&operation=insertReward&empID=<c:out value='${empID}'/>";
		document.form1.fireSubmit();
	}

 }
</SCRIPT>
<body>
<form name="form1" method="post">
<%@ include file="./inc/hrtoolbar_a.jsp"%>
<%@ include file="../hrm/hrm_add_basic.jsp"%>

<table width="100%" border="0" cellpadding="0">
  <tr height="20">
    <td class="title1">奖励</td>
  </tr>
  <tr height="135">
    <td>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d" >
          <tr>
            <td class="info_title_01" height="30" nowrap>奖励日期</td>
            <td class="info_title_01" nowrap>奖励类型</td>
            <td class="info_title_01" nowrap>种类</td>
            <td class="info_title_01" nowrap>人数</td>
            <!-- <td class="info_title_01" nowrap>功绩类型</td> -->
            <td class="info_title_01" nowrap>功绩内容</td>
            <td class="info_title_01" nowrap>奖励金额</td>
          </tr>
        <c:forEach items="${rewardList}" var="oneResult" varStatus="i">
        <tr>
          <td class="info_content_01" height="30" nowrap>${oneResult.rewardDate}</td>
          <td class="info_content_01" nowrap>${oneResult.rewardType}</td>
          <td class="info_content_01" nowrap>${oneResult.rewardSort}</td>
          <td class="info_content_01" nowrap>${oneResult.rewardAmount}</td>
          <!-- <td class="info_content_01" nowrap>${oneResult.achievementType}</td> -->
          <td class="info_content_01" nowrap>${oneResult.rewardContents}</td>
          <td class="info_content_01" nowrap>${oneResult.rewardBonus}</td>
          <input type="hidden" name="rewardDate_exist${i}" value="${oneResult.rewardDate}">
        </tr>
        </c:forEach>
         <c:forEach var="i" begin="1" end="3" step="1">
	        <tr>
	            <td class="info_content_01" height="30" nowrap><input type="text" size="10" class="content" name="rewardDate_${i}" id="rewardDate_${i}" onClick="setday(this);" date mask="9999-99-99"/></td>
	            <td class="info_content_01" nowrap><ait:codeClass name="rewardTypeCode_${i}" codeClass="RewardTypeCode" all="ALL"/></td>
	            <td class="info_content_01" nowrap><ait:codeClass name="rewardSortCode_${i}" codeClass="RewardSortCode" all="ALL"/></td>
	            <td class="info_content_01" nowrap><input type="text" class="content" name="rewardAmount_${i}" id="rewardAmount_${i}" /></td>
	            <!-- <td class="info_content_01" nowrap><ait:codeClass name="achievementTypeCode_${i}" codeClass="AchievementTypeCode" all="ALL"/></td> -->
	            
	            <td class="info_content_01" nowrap>
	            <input type="text" class="content" name="rewardContents_${i}" id="rewardContents_${i}" />
	            </td>
	            
	            <td class="info_content_01" nowrap><input type="text" class="content" name="rewardBonus_${i}" id="rewardBonus_${i}" /></td>
	            
	            <input type="hidden" name="createdBy_${i}" value="${adminId}">
	            <input type="hidden" name="empID_${i}" value="${empID}"/>
	        </tr>
         </c:forEach>
        </table></td>
  </tr>
  <tr height="1">
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr height="20">
    <td class="title1">惩戒</td>
  </tr>
  <tr height="135">
    <td valign="top">
	<div style="overflow:auto; width:100%; height:100%;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d" >
        <tr style="position: relative; top: expression(this.offsetParent.scrollTop);">
          <td class="info_title_01" height="30">惩戒日期</td>
          <td class="info_title_01">惩戒类型</td>
          <td class="info_title_01">违纪类型</td>
          <!-- <td class="info_title_01">惩戒权者</td> -->
          <td class="info_title_01">惩戒事由</td>
          <td class="info_title_01">赦免日期</td>
          <td class="info_title_01">人事令号</td>
        </tr>
       <c:forEach items="${punishMentList}" var="oneResult">
        <tr>
          <td class="info_content_01" height="30" nowrap>${oneResult.punishDate}</td>
          <td class="info_content_01" nowrap>${oneResult.punishType}</td>
          <td class="info_content_01" nowrap>${oneResult.violationType}</td>
          <!-- <td class="info_content_01" nowrap>${oneResult.punisher}</td> -->
          <td class="info_content_01" nowrap>${oneResult.punishResult}</td>
          <td class="info_content_01" nowrap>${oneResult.releaseDate}</td>
          <td class="info_content_01" nowrap>${oneResult.transNo}</td>
        </tr>
        </c:forEach>
        <c:if test="${punishMentListCnt<3}">
		 <c:forEach var="i" begin="1" end="${3-punishMentListCnt}" step="1">
	        <tr>
		        <td class="info_content_01" height="30" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
		        <td class="info_content_01" nowrap></td>
	        </tr>
         </c:forEach>
        </c:if>
      </table>
	  </div>
	  </td>
  </tr>
</table>
<input type="hidden" name="rewardSign" id="rewardSign">
</form>
</body>
<ait:xjos />
</html>
