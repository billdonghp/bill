<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach  items="${dept_list}" var="dept">
      <option value="<c:out value="${dept.deptID}"/>">
      <c:forEach  begin="0" end="${dept.deptLevel}">&nbsp;&nbsp;</c:forEach><c:out value="${dept.deptName}"/>
      </option>          		
</c:forEach >
