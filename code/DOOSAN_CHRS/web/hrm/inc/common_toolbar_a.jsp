<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table width="99%" height="45" border="0" cellpadding="0" cellspacing="0">
        <tr>
         <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td  align="right" valign="middle" style="padding:3 0 3 0 ">
		  <c:if test="${toolbarInfo.insertMenu == 1}">
          	<a href="javascript:Save();" ><img src="/images/btn/save1.gif" width="67" height="24" border="0" align="absmiddle"></a>
          	<a href="javascript:history.back();" ><img src="/images/btn/return1.gif" width="67" height="24" border="0" align="absmiddle"></a>
          </c:if>
		  </td>
        </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="kuang_zuo"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="18"><img src="/images/top_zuo.jpg" width="18" height="28"></td>
          <td background="/images/top_zuo_d2.jpg"><table border="0" cellspacing="0" cellpadding="0">
              <tr>
           		<c:forEach items="${toolbarInfo.menuentList}" var="oneResult">
 						<c:choose>
	                         <c:when test="${oneResult.menuCode == toolbarInfo.menu_code}">
								<td class="td_shen"><a href="<c:out value='${oneResult.menuUrl}&empID=${basic.empID}'/>" class="shen"><c:out value="${oneResult.menuIntro}"/></a></td>
	                         </c:when>
	                         <c:otherwise>
								<td class="td_qian"><a href="<c:out value='${oneResult.menuUrl}&empID=${basic.empID}'/>" class="qian"><c:out value="${oneResult.menuIntro}"/></a></td>
	                         </c:otherwise>     
	                    </c:choose>
				</c:forEach>
              </tr>
            </table></td>
          <td width="14" align="right"><img src="/images/top_you.jpg" width="14" height="28"></td>
        </tr>
      </table></td>
  </tr>
</table>