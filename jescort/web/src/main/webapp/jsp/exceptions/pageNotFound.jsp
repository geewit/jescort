<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head><title><c:out value="${requestScope.exception.message}" default="pageNotFound"/></title></head>
<div class="errors">
    <div class="error">${exception.class.name}</div>
    <c:if test="${not empty exception.message}"> <c:forEach var="stackTrace" items="${exception.stackTrace}">
        <pre style="overflow:hidden;"><c:out value="${stackTrace}"/></pre>
    </c:forEach> </c:if>
</div>