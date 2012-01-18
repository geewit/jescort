<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head><title><c:out value="${exception.message}" default="dataAccessFailure"/></title></head>
<div class="errors">
    <div class="error"><c:out value="${exception.message}"/></div>
</div>